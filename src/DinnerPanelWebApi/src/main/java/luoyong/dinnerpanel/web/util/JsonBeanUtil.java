package luoyong.dinnerpanel.web.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.WrapDynaBean;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class JsonBeanUtil {

   public static JSONObject beanToJson(Object object) {

      if (object == null) {
         return null;
      }

      JSONObject result = new JSONObject();

      // Wrapped bean.
      WrapDynaBean wrapDynaBean = new WrapDynaBean(object);

      // Wrapped bean class.
      DynaClass dynaClass = wrapDynaBean.getDynaClass();

      // Properties from wrapped bean class.
      DynaProperty dynaProperties[] = dynaClass.getDynaProperties();

      SimpleDateFormat simpleDateFormat
              = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

      Class fieldType = null;
      Object fieldValue = null;

      for (DynaProperty dynaProperty : dynaProperties) {

         fieldType = dynaProperty.getType();

         if ((dynaProperty != null)
                 && (fieldType != null)
                 && (dynaProperty.getName() != null)
                 && (!dynaProperty.getName().equals("class"))) {

            if (fieldType.equals(String.class)) {
               try {
                  result.put(dynaProperty.getName(),
                          wrapDynaBean.get(dynaProperty.getName()));
               }catch(JSONException ex) {
                  ex.printStackTrace(System.err);
               }
            }else if (fieldType.equals(Long.class)) {
               try {
                  result.put(dynaProperty.getName(),
                          wrapDynaBean.get(dynaProperty.getName()));
               }catch(JSONException ex) {
                  ex.printStackTrace(System.err);
               }
            }else if (fieldType.equals(BigDecimal.class)) {
               try {
                  result.put(dynaProperty.getName(),
                          wrapDynaBean.get(dynaProperty.getName()));
               }catch(JSONException ex) {
                  ex.printStackTrace(System.err);
               }
            }else if (fieldType.equals(Date.class)) {
               try {

                  fieldValue = wrapDynaBean.get(dynaProperty.getName());
                  if (fieldValue != null) {
                     result.put(dynaProperty.getName(),
                             simpleDateFormat.format((Date)fieldValue));
                  }
               }catch(JSONException ex) {
                  ex.printStackTrace(System.err);
               }
            }else if (fieldType.equals(Enum.class)) {
               try {
                  fieldValue = wrapDynaBean.get(dynaProperty.getName());
                  result.put(dynaProperty.getName(),
                          fieldValue==null?null:((Enum)fieldValue).name());
               }catch(JSONException ex) {
                  ex.printStackTrace(System.err);
               }
            }else {
               try {
                  fieldValue = wrapDynaBean.get(dynaProperty.getName());
                  result.put(dynaProperty.getName(),
                          (fieldValue==null)?null:fieldValue);
               }catch(JSONException ex) {
                  ex.printStackTrace(System.err);
               }
            }
         }
      }

      return result;
   }
}
