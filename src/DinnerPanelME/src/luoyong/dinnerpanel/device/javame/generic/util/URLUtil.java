package luoyong.dinnerpanel.device.javame.generic.util;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import luoyong.dinnerpanel.device.javame.authentication.CredentialHolder;
import luoyong.dinnerpanel.device.javame.bytecontainer.ByteContainer;
import luoyong.toolbox.encoder.me.Base64Encoder;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class URLUtil {
   
   public static URLResponse readURLAsStringUseCredential(String url)
           throws IOException {

      HttpConnection conn = null;
      InputStream inputStream = null;

      try {
         
         conn = (HttpConnection) Connector.open(url);
         conn.setRequestMethod(HttpConnection.GET);
         
         String authInfo = (CredentialHolder.getUsername()==null?
               "":CredentialHolder.getUsername())
            + ":"
            + (CredentialHolder.getPassword()==null?
               "":CredentialHolder.getPassword());
         // Send the credential to remote server.
         conn.setRequestProperty("Authorization",
                 "Basic " + Base64Encoder.encode(authInfo.getBytes()));

         inputStream = conn.openInputStream();

         byte[] byteBuffer = null;
         int byteCount = 0;

         ByteContainer byteContainer = new ByteContainer();

         while(byteCount > -1) {
            byteBuffer = new byte[500];
            byteCount = inputStream.read(byteBuffer);
            if (byteCount > -1) {
               byteContainer.addCell(byteBuffer, byteCount);
            }
         }

         int responseCode = conn.getResponseCode();

         return new URLResponse(responseCode, byteContainer.getBytes());
      }finally {
         try {
            inputStream.close();
         }catch(Throwable t) {}
         try {
            conn.close();
         }catch(Throwable t) {}
      }
   }
}
