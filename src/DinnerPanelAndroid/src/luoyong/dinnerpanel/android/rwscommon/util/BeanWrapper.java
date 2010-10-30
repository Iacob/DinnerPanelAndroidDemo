package luoyong.dinnerpanel.android.rwscommon.util;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class BeanWrapper {

   private Object wrappedObject = null;

   private ReadableBeanProperty[] readableProperties = null;
   private WritableBeanProperty[] writableProperties = null;

   private Pattern propertyNamePattern = Pattern.compile("^(.)(.*)$");

   public BeanWrapper(Object object) {
      
      this.wrappedObject = object;

      Pattern readablePropertyPattern = Pattern.compile("^get(.+)$");
      Pattern readableBooleanPropertyPattern = Pattern.compile("^is(.+)$");
      Pattern writablePropertyPattern = Pattern.compile("^set(.+)$");
      
      if (this.wrappedObject == null) {
         return;
      }

      Class objectType = object.getClass();
      if (objectType == null) {
         return;
      }
      Method[] objectMethods = objectType.getMethods();
      if (objectMethods == null) {
         return;
      }
      
      LinkedList<ReadableBeanProperty> readablePropertiesList
              = new LinkedList<ReadableBeanProperty>();
      for (Method method : objectMethods) {
         Matcher matcher = readablePropertyPattern.matcher(method.getName());
         if (matcher.find()) {

            if (!"Class".equals(matcher.group(1))) {

               readablePropertiesList.add(
                       new ReadableBeanProperty(
                          this.toBeanName(matcher.group(1)),
                          method,
                          method.getReturnType()));
            }
         }else {
            matcher = readableBooleanPropertyPattern.matcher(method.getName());
            if (matcher.find()) {
               readablePropertiesList.add(
                    new ReadableBeanProperty(
                       this.toBeanName(matcher.group(1)),
                       method,
                       method.getReturnType()));
            }
         }
      }
      readableProperties = readablePropertiesList.toArray(
              new ReadableBeanProperty[readablePropertiesList.size()]);
      
      LinkedList<WritableBeanProperty> writablePropertiesList
              = new LinkedList<WritableBeanProperty>();
      for (Method method : objectMethods) {
         Matcher matcher = writablePropertyPattern.matcher(method.getName());

         if (matcher.find()
                 && (method.getParameterTypes() != null)
                 && (method.getParameterTypes().length > 0)) {

            writablePropertiesList.add(
                    new WritableBeanProperty(
                       this.toBeanName(matcher.group(1)),
                       method,
                       method.getParameterTypes()[0]));
         }
      }
      writableProperties = writablePropertiesList.toArray(
              new WritableBeanProperty[writablePropertiesList.size()]);
   }

   public ReadableBeanProperty[] getReadableProperties() {
      return readableProperties;
   }

   public WritableBeanProperty[] getWritableProperties() {
      return writableProperties;
   }

   private String toBeanName(String str) {
      if (str == null) {
         return null;
      }
      if (str.length() < 2) {
         return str.toLowerCase();
      }

      Matcher matcher = propertyNamePattern.matcher(str);
      if (matcher.find()) {
         return (matcher.group(1).toLowerCase() + matcher.group(2));
      }

      return str;
      
   }

   public Object get(ReadableBeanProperty property) {
      if (property.getMethod() == null) {
         return null;
      }
      try {
         return property.getMethod().invoke(this.wrappedObject);
      } catch (Throwable t) {
         t.printStackTrace(System.err);
         return null;
      }
   }

   public void set(WritableBeanProperty property, Object value) {
      if (property.getMethod() == null) {
         return;
      }

      if ((property.getMethod().getParameterTypes() == null)
              || (property.getMethod().getParameterTypes().length < 1)) {

         return;
      }

      try {
         property.getMethod().invoke(this.wrappedObject, value);
      } catch (Throwable t) {
         t.printStackTrace(System.err);
         return;
      }
   }

   public class ReadableBeanProperty {

      private String name = null;
      private Method method = null;
      private Class type = null;

      protected ReadableBeanProperty(String name, Method method, Class type) {
         this.name = name;
         this.method = method;
         this.type = type;
      }

      public Method getMethod() {
         return method;
      }

      public String getName() {
         return name;
      }

      public Class getType() {
         return type;
      }
   }

   public class WritableBeanProperty {

      private String name = null;
      private Method method = null;
      private Class type = null;

      protected WritableBeanProperty(String name, Method method, Class type) {
         this.name = name;
         this.method = method;
         this.type = type;
      }

      public Method getMethod() {
         return method;
      }

      public String getName() {
         return name;
      }

      public Class getType() {
         return type;
      }
   }
}
