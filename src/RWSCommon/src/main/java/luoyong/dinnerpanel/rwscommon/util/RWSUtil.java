package luoyong.dinnerpanel.rwscommon.util;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class RWSUtil {

   public static void setJsonObjectReturnCode(JSONObject jsonObject, int returnCode) {
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
}
