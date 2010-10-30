package luoyong.dinnerpanel.android.rwscommon.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import luoyong.dinnerpanel.android.rwscommon.info.RemoteAuthorizationException;
import luoyong.dinnerpanel.android.rwscommon.info.RemoteBusinessLogicException;
import luoyong.dinnerpanel.android.rwscommon.info.RemoteConnectionException;
import luoyong.dinnerpanel.android.rwscommon.info.RemoteInformationException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class RWSUtil {

   public static void setJsonObjectReturnCode(
           JSONObject jsonObject, int returnCode) {
      
      if (jsonObject != null) {
         try {
            jsonObject.put(
                    RWSConstant.FIELD_RETURN_CODE, new Integer(returnCode));
         }catch(Throwable t) {
            t.printStackTrace(System.err);
         }
      }
   }

   public static void setJsonObjectErrorId(JSONObject jsonObject, int errorId) {
      if (jsonObject != null) {
         try {
            jsonObject.put(
                    RWSConstant.FIELD_ERROR_ID, new Integer(errorId));
         }catch(Throwable t) {
            t.printStackTrace(System.err);
         }
      }
   }

   public static void setJsonObjectErrorId(
           JSONObject jsonObject, int returnCode, int errorId) {
      
      setJsonObjectReturnCode(jsonObject, returnCode);
      setJsonObjectErrorId(jsonObject, errorId);
   }

   public static void setJsonObjectErrorMessage(
           JSONObject jsonObject, String errorMessage) {
      
      if (jsonObject != null) {
         try {
            jsonObject.put(
                    RWSConstant.FIELD_ERROR_MESSAGE, errorMessage);
         }catch(Throwable t) {
            t.printStackTrace(System.err);
         }
      }
   }

   public static void setJsonObjectErrorMessage(
           JSONObject jsonObject, int returnCode, String errorMessage) {

      setJsonObjectReturnCode(jsonObject, returnCode);
      setJsonObjectErrorMessage(jsonObject, errorMessage);
   }

   public static void setJsonObjectErrorIdWithMessage(
           JSONObject jsonObject,
           int returnCode,
           int errorId,
           String errorMessage) {

      setJsonObjectReturnCode(jsonObject, returnCode);
      setJsonObjectErrorId(jsonObject, errorId);
      setJsonObjectErrorMessage(jsonObject, errorMessage);
   }

   public static void setJsonObjectResult(
           JSONObject jsonObject, JSONArray result) {

      if (jsonObject != null) {
         try {
            jsonObject.put(
                    RWSConstant.FIELD_RESULT, result);
         }catch(Throwable t) {
            t.printStackTrace(System.err);
         }
      }
   }

   public static void setJsonObjectResult(
           JSONObject jsonObject, int returnCode, JSONArray result) {

      setJsonObjectReturnCode(jsonObject, returnCode);
      setJsonObjectResult(jsonObject, result);
   }

   public static JSONArray getRWSResultViaGET(URL httpURL) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException  {

      if (httpURL == null) {
         throw new RemoteConnectionException("URL must not be null.");
      }

      byte[] serverByteMessage = null;

      HttpURLConnection httpURLConnection = null;

      InputStream serverInputStream = null;

      try {
         // Connect to URL.
         URLConnection urlConnection = null;

         try {
            urlConnection = httpURL.openConnection();
         } catch (IOException ex) {
            throw new RemoteConnectionException("无法连接到服务器", ex);
         }

         if (urlConnection == null) {
            throw new RemoteConnectionException("无法连接到服务器");
         }

         // Detect if the connection is HTTP connection.
         if (!(urlConnection instanceof HttpURLConnection)) {
            throw new RemoteConnectionException("和服务器的连接不是HTTP连接");
         }

         httpURLConnection = (HttpURLConnection) urlConnection;

         // Set request method.
         try {
            httpURLConnection.setRequestMethod("GET");
         } catch (ProtocolException ex) {
            throw new RemoteConnectionException("服务器无法支持GET方法", ex);
         }

         // Preparing to receive message from server.

         try {
            serverInputStream = httpURLConnection.getInputStream();
         } catch (IOException ex) {

            int httpResponseCode = 0;

            try {

               httpResponseCode = httpURLConnection.getResponseCode();

               if (httpResponseCode == 401) {
                  throw new RemoteAuthorizationException("没有权限");
               }
            } catch (IOException e) {
            }

            throw new RemoteConnectionException(
                    "无法从服务器读入数据，服务器返回：HTTP" + httpResponseCode, ex);
         }

         // Receiving message from server.

         int serverReadCount = 0;
         byte serverReadBuffer[] = new byte[512];
         ByteArrayOutputStream byteArrayOutputStream
                 = new ByteArrayOutputStream();

         for (;;) {
            try {
               serverReadCount = serverInputStream.read(serverReadBuffer);
               // Quit the read loop if reach the end of the stream.
               if (serverReadCount < 0) {
                  break;
               }
            } catch (IOException ex) {

               throw new RemoteConnectionException("从服务器读入数据时发生错误", ex);
            }
            byteArrayOutputStream.write(serverReadBuffer, 0, serverReadCount);
         }

         serverByteMessage = byteArrayOutputStream.toByteArray();
         
      }finally {
         // Close server input stream.
         try {
            serverInputStream.close();
         } catch (Throwable t) {}

         // Close HTTP URL connection.
         try {
            httpURLConnection.disconnect();
         } catch (Throwable t) {}
      }

      // Extract result from the information returned by server.
      
      try {
         String serverMessage = new String(serverByteMessage, "UTF-8");
         return getResult(serverMessage);
      } catch (UnsupportedEncodingException ex) {

         throw new RemoteInformationException("服务器返回信息的编码格式不正确", ex);
      }
   }

   public static JSONArray getRWSResultViaGET(String urlString) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException  {

      if (urlString == null) {
         throw new RemoteConnectionException("URL为空");
      }

      URL url = null;
      
      try {
         url = new URL(urlString);
      } catch (MalformedURLException ex) {
         throw new RemoteConnectionException("URL格式不正确", ex);
      }

      return getRWSResultViaGET(url);
   }

   public static JSONArray getRWSResultViaPOST(
           URL httpURL, String clientMessage) throws
            RemoteConnectionException,
            RemoteAuthorizationException,
            RemoteInformationException,
            RemoteBusinessLogicException  {

      if (httpURL == null) {
         throw new RemoteConnectionException("URL must not be null.");
      }

      byte[] serverByteMessage = null;

      HttpURLConnection httpURLConnection = null;

      InputStream serverInputStream = null;

      try {

         // Connect to URL.
         URLConnection urlConnection = null;

         try {
            urlConnection = httpURL.openConnection();
         }catch(IOException ex) {
            throw new RemoteConnectionException("无法连接到服务器", ex);
         }

         if (urlConnection == null) {
            throw new RemoteConnectionException("无法连接到服务器");
         }

         // Detect if the connection is HTTP connection.
         if (!(urlConnection instanceof HttpURLConnection)) {
            throw new RemoteConnectionException("和服务器的连接不是HTTP连接");
         }

         httpURLConnection = (HttpURLConnection)urlConnection;

         // Set request method.
         try {
            httpURLConnection.setRequestMethod("POST");
         }catch(ProtocolException ex) {

            throw new RemoteConnectionException("服务器无法支持POST方法", ex);
         }

         httpURLConnection.setDoOutput(true);
         httpURLConnection.setRequestProperty(
                 "Content-Type", "application/json");

         // Send message to server.

         if (clientMessage != null) {

            OutputStream serverOutputStream = null;

            try {
               serverOutputStream = httpURLConnection.getOutputStream();
               try {
                  serverOutputStream.write(clientMessage.getBytes("UTF-8"));
               }catch (UnsupportedEncodingException ex) {
                  throw new RemoteInformationException(
                          "字符编码格式系统不支持", ex);
               }
            }catch(IOException ex) {

               int httpResponseCode = 0;

               try {

                  httpResponseCode = httpURLConnection.getResponseCode();

                  if (httpResponseCode == 401) {

                     throw new RemoteAuthorizationException("没有权限");
                  }
               }catch(IOException e) {
               }

               throw new RemoteConnectionException(
                       "无法向服务器发送数据，服务器返回：HTTP"
                           + httpResponseCode, ex);
            }finally {

               // Close server output stream.
               try {
                  serverOutputStream.close();
               } catch (Throwable t) {}
            }
         }


         // Preparing to receive information from server.

         try {
            serverInputStream = httpURLConnection.getInputStream();
         }catch(IOException ex) {

            int httpResponseCode = 0;

            try {

               httpResponseCode = httpURLConnection.getResponseCode();

               if (httpResponseCode == 401) {
                  throw new RemoteAuthorizationException("没有权限");
               }
            }catch(IOException e) {
            }

            throw new RemoteConnectionException(
                    "无法从服务器读入数据，服务器返回：HTTP" + httpResponseCode, ex);
         }

         // Receiving information from server.

         int serverReadCount = 0;
         byte serverReadBuffer[] = new byte[512];
         ByteArrayOutputStream byteArrayOutputStream
                 = new ByteArrayOutputStream();

         for (;;) {
            try {
               serverReadCount = serverInputStream.read(serverReadBuffer);
               // Quit the read loop if reach the end of the stream.
               if (serverReadCount < 0) {
                  break;
               }
            } catch (IOException ex) {
               
               throw new RemoteConnectionException("从服务器读入数据时发生错误", ex);
            }
            byteArrayOutputStream.write(serverReadBuffer, 0, serverReadCount);
         }

         serverByteMessage = byteArrayOutputStream.toByteArray();


      }finally {

         // Close server input stream.
         try {
            serverInputStream.close();
         } catch (Throwable t) {}

         // Close HTTP URL connection.
         try {
            httpURLConnection.disconnect();
         } catch (Throwable t) {}
      }

      // Extract result from the message sent by server.

      try {
         String serverMessage = new String(serverByteMessage, "UTF-8");
         return getResult(serverMessage);
      } catch (UnsupportedEncodingException ex) {
         throw new RemoteInformationException("服务器返回信息的编码格式不正确", ex);
      }
   }

   public static JSONArray getRWSResultViaPOST(
           String urlString, String clientMessage) throws
            RemoteConnectionException,
            RemoteAuthorizationException,
            RemoteInformationException,
            RemoteBusinessLogicException  {

      if (urlString == null) {
         throw new RemoteConnectionException("URL为空");
      }

      URL url = null;

      try {
         url = new URL(urlString);
      } catch (MalformedURLException ex) {
         throw new RemoteConnectionException("URL格式不正确", ex);
      }

      return getRWSResultViaPOST(url, clientMessage);
   }

   private static JSONArray getResult(String serverString) throws
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (serverString == null) {
         throw new RemoteInformationException("服务器返回空结果");
      }

      // Encapsulate server message to JSON object.

      JSONObject jsonObject = null;
      try {
         jsonObject = new JSONObject(serverString);
      } catch (JSONException ex) {
         throw new RemoteInformationException("服务器返回的信息格式不正确", ex);
      }

      if (!jsonObject.has(RWSConstant.FIELD_RETURN_CODE)) {
         throw new RemoteInformationException("服务器没有返回正确的返回值");
      }

      // Get return code.

      int returnCode = 1;

      try {
         returnCode = jsonObject.getInt(RWSConstant.FIELD_RETURN_CODE);
      }catch(JSONException ex) {
         throw new RemoteInformationException("服务器没有返回正确的返回值");
      }

      if (returnCode != 0) {

         String errorId = null;
         String errorMessage = null;
         if (jsonObject.has(RWSConstant.FIELD_ERROR_ID)) {
            try {
               errorId = jsonObject.getString(RWSConstant.FIELD_ERROR_ID);
            }catch(JSONException ex) {
               ex.printStackTrace(System.err);
            }
         }
         if (jsonObject.has(RWSConstant.FIELD_ERROR_MESSAGE)) {
            try {
               errorMessage =
                       jsonObject.getString(RWSConstant.FIELD_ERROR_MESSAGE);
            }catch(JSONException ex) {
               ex.printStackTrace(System.err);
            }
         }

         throw new RemoteBusinessLogicException(errorId, errorMessage);
      }

      // Get operation result.

      if (!jsonObject.has(RWSConstant.FIELD_RESULT)) {
         return null;
      }

      try {
         return jsonObject.getJSONArray(RWSConstant.FIELD_RESULT);
      }catch(JSONException ex) {
         throw new RemoteInformationException("服务器返回的结果格式不正确");
      }
   }
}
