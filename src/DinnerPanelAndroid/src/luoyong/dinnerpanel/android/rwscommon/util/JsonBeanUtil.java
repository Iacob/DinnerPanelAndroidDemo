package luoyong.dinnerpanel.android.rwscommon.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class JsonBeanUtil {

   public static JSONObject beanToJsonObject(Object object) {

      if (object == null) {
         return null;
      }

      JSONObject result = new JSONObject();

      // Wrapped bean.
      BeanWrapper wrapDynaBean = new BeanWrapper(object);

      // Properties from wrapped bean class.
      BeanWrapper.ReadableBeanProperty beanProperties[]
              = wrapDynaBean.getReadableProperties();
      if (beanProperties == null) {
         return result;
      }

      SimpleDateFormat simpleDateFormat
              = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

      Class fieldType = null;
      Object fieldValue = null;

      for (BeanWrapper.ReadableBeanProperty beanProperty : beanProperties) {

         if ((beanProperty != null)
                 && (beanProperty.getType() != null)
                 && (beanProperty.getName() != null)
                 && (!beanProperty.getName().equals("class"))) {

            fieldType = beanProperty.getType();

            if (fieldType.equals(String.class)) {
               try {
                  result.put(beanProperty.getName(),
                          wrapDynaBean.get(beanProperty));
               }catch(JSONException ex) {
                  ex.printStackTrace(System.err);
               }
            }else if (fieldType.equals(Long.class)) {
               try {
                  result.put(beanProperty.getName(),
                          wrapDynaBean.get(beanProperty));
               }catch(JSONException ex) {
                  ex.printStackTrace(System.err);
               }
            }else if (fieldType.equals(BigDecimal.class)) {
               try {
                  result.put(beanProperty.getName(),
                          wrapDynaBean.get(beanProperty));
               }catch(JSONException ex) {
                  ex.printStackTrace(System.err);
               }
            }else if (fieldType.equals(Date.class)) {
               try {

                  fieldValue = wrapDynaBean.get(beanProperty);
                  if (fieldValue != null) {
                     result.put(beanProperty.getName(),
                             simpleDateFormat.format((Date)fieldValue));
                  }
               }catch(JSONException ex) {
                  ex.printStackTrace(System.err);
               }
            }else if (fieldType.isEnum()) {
               try {
                  fieldValue = wrapDynaBean.get(beanProperty);
                  result.put(beanProperty.getName(),
                          fieldValue==null?null:((Enum)fieldValue).name());
               }catch(JSONException ex) {
                  ex.printStackTrace(System.err);
               }
            }else if (fieldType.equals(Integer.class)) {
               try {
                  result.put(beanProperty.getName(),
                          wrapDynaBean.get(beanProperty));
               }catch(JSONException ex) {
                  ex.printStackTrace(System.err);
               }
            }else {
               // Nothing else.
            }
         }
      }

      return result;
   }

   public static void jsonObjectToBean(JSONObject jsonObject, Object object) {
      
      if ((jsonObject == null) || (object == null)) {
         return;
      }

      // Wrapped bean.
      BeanWrapper wrapDynaBean = new BeanWrapper(object);

      // Properties from wrapped bean class.
      BeanWrapper.WritableBeanProperty beanProperties[]
              = wrapDynaBean.getWritableProperties();
      if (beanProperties == null) {
         return;
      }

      SimpleDateFormat simpleDateFormat
              = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

      Class fieldType = null;
      String propertyName = null;
      Object propertyValue = null;

      BigDecimal decimalValue = null;
      String stringValue = null;

      for (BeanWrapper.WritableBeanProperty beanProperty : beanProperties) {

         if ((beanProperty != null)
                 && (beanProperty.getType() != null)
                 && (beanProperty.getName() != null)
                 && (!beanProperty.getName().equals("class"))) {

            fieldType = beanProperty.getType();
            propertyName = beanProperty.getName();

            if (fieldType.equals(String.class)) {
               try {
                  if (jsonObject.has(propertyName)) {
                     wrapDynaBean.set(beanProperty,
                             jsonObject.getString(propertyName));
                  }
               }catch(Throwable t) {
                  t.printStackTrace(System.err);
               }
            }else if (fieldType.equals(Long.class)) {
               try {
                  if (jsonObject.has(propertyName)) {
                     wrapDynaBean.set(beanProperty,
                             jsonObject.getLong(propertyName));
                  }
               }catch(Throwable t) {
                  t.printStackTrace(System.err);
               }
            }else if (fieldType.equals(BigDecimal.class)) {
               try {
                  if (jsonObject.has(propertyName)) {
                     
                     decimalValue = new BigDecimal(
                             jsonObject.getDouble(propertyName),
                             MathContext.DECIMAL32);
                     decimalValue.setScale(2);

                     wrapDynaBean.set(beanProperty, decimalValue);
                  }
               }catch(Throwable t) {
                  t.printStackTrace(System.err);
               }
            }else if (fieldType.equals(Date.class)) {
               try {
                  if (jsonObject.has(propertyName)) {
                     stringValue = jsonObject.getString(propertyName);
                     wrapDynaBean.set(beanProperty,
                             simpleDateFormat.parse(stringValue));
                  }
               }catch(Throwable t) {
                  t.printStackTrace(System.err);
               }
            }else if (fieldType.isEnum()) {
               try {
                  if (jsonObject.has(propertyName)) {
                     propertyValue = Enum.valueOf(
                             fieldType, jsonObject.getString(propertyName));
                     wrapDynaBean.set(beanProperty, propertyValue);
                  }
               }catch(Throwable t) {
                  t.printStackTrace(System.err);
               }
            }else if (fieldType.equals(Integer.class)) {
               try {
                  if (jsonObject.has(propertyName)) {
                     wrapDynaBean.set(beanProperty,
                             new Integer(jsonObject.getInt(propertyName)));
                  }
               }catch(Throwable t) {
                  t.printStackTrace(System.err);
               }
            }else {
               // Nothing else.
            }
         }
      }
   }

   public static abstract class JSONArrayExtractor<T> {

      private JSONArray jsonArray = null;

      public JSONArrayExtractor(JSONArray jsonArray) {
         this.jsonArray = jsonArray;
      }

      abstract protected T getInstance();

      protected void extractJsonObjectToBean(
              JSONObject jsonObject, T bean) {
         
         JsonBeanUtil.jsonObjectToBean(jsonObject, bean);
      }

      public List<T> extract() {

         JSONObject currentJsonObject = null;

         T currentInstance = null;

         if (this.jsonArray == null) {
            return null;
         }

         LinkedList<T> list = new LinkedList<T>();

         int arrayLength = jsonArray.length();

         for (int i=0; i<arrayLength; i++) {

            currentInstance = this.getInstance();

            if (currentInstance == null) {
               continue;
            }

            try {
               currentJsonObject = jsonArray.getJSONObject(i);
            } catch (JSONException ex) {
               ex.printStackTrace(System.err);
               continue;
            }

            if (currentJsonObject == null) {
               continue;
            }

            this.extractJsonObjectToBean(currentJsonObject, currentInstance);

            list.add(currentInstance);
         }

         return list;
      }
   }
}
