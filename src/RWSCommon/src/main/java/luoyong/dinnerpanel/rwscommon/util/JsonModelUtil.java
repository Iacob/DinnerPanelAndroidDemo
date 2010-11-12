package luoyong.dinnerpanel.rwscommon.util;

import java.util.HashSet;
import java.util.Set;
import luoyong.dinnerpanel.dao.model.Operator;
import luoyong.dinnerpanel.dao.model.OperatorGroup;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class JsonModelUtil {

   public static JSONObject operatorInfoToJsonObject(Operator o) {
      
      if (o == null) {
         return null;
      }
      
      JSONObject jsonObject = JsonBeanUtil.beanToJsonObject(o);
      
      if (jsonObject == null) {
         return null;
      }

      // Get operator group information.
      Set<OperatorGroup> operatorGroupSet = o.getGroups();

      if (operatorGroupSet != null) {

         // Make a new operator group json array.
         JSONArray operatorGroupArray = new JSONArray();

         for (OperatorGroup group : operatorGroupSet) {

            if (group != null) {

               // Add operator group information to operator group json array.
               operatorGroupArray.put(group.name());
            }
         }

         // Put the operator group json array into operator json object.
         try {
            jsonObject.put("groups", operatorGroupArray);
         } catch (Throwable t) {
            t.printStackTrace(System.err);
         }
      }

      return jsonObject;
   }

   public static void jsonObjectToOperatorInfo(
           JSONObject jsonObject, Operator o) {

      if (o == null) {
         return;
      }

      if (jsonObject == null) {
         return;
      }

      JsonBeanUtil.jsonObjectToBean(jsonObject, o);

      JSONArray operatorGroupJsonArray = jsonObject.optJSONArray("groups");

      if (operatorGroupJsonArray != null) {

         Set<OperatorGroup> operatorGroupSet = new HashSet<OperatorGroup>();

         OperatorGroup operatorGroup = null;
         int operatorGroupJsonArrayLength = operatorGroupJsonArray.length();
         for (int i=0; i<operatorGroupJsonArrayLength; i++) {
            operatorGroup = Enum.valueOf(OperatorGroup.class,
                    operatorGroupJsonArray.optString(i));
            if (operatorGroup != null) {
               operatorGroupSet.add(operatorGroup);
            }
         }
      }
   }
}
