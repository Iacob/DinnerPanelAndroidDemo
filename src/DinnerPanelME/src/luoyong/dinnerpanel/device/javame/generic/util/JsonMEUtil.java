package luoyong.dinnerpanel.device.javame.generic.util;

import luoyong.toolbox.json.me.JsonArray;
import luoyong.toolbox.json.me.JsonBoolean;
import luoyong.toolbox.json.me.JsonNumber;
import luoyong.toolbox.json.me.JsonObject;
import luoyong.toolbox.json.me.JsonString;
import luoyong.toolbox.json.me.JsonValue;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class JsonMEUtil {

   public static final String DEFAULT_CHARACTER_SET = "UTF-8";

   public static boolean isJsonArray(JsonValue value) {
      if (value == null) {
         return false;
      }
      if (value instanceof JsonArray) {
         return true;
      }else {
         return false;
      }
   }

   public static boolean isJsonObject(JsonValue value) {
      if (value == null) {
         return false;
      }
      if (value instanceof JsonObject) {
         return true;
      }else {
         return false;
      }
   }

   public static Boolean getBoolean(JsonValue value) {
      if (value == null) {
         return null;
      }
      if (value instanceof JsonBoolean) {
         if (((JsonBoolean)value).getValue()) {
            return Boolean.TRUE;
         }else {
            return Boolean.FALSE;
         }
      }else {
         return null;
      }
   }

   public static Double getDouble(JsonValue value) {
      if (value == null) {
         return null;
      }
      if (value instanceof JsonNumber) {
         return new Double(((JsonNumber)value).getNumber());
      }else {
         return null;
      }
   }

   public static Integer getInteger(JsonValue value) {
      if (value == null) {
         return null;
      }
      if (value instanceof JsonNumber) {
         return new Integer((int)((JsonNumber)value).getNumber());
      }else {
         return null;
      }
   }

   public static Long getLong(JsonValue value) {
      if (value == null) {
         return null;
      }
      if (value instanceof JsonNumber) {
         return new Long((long)((JsonNumber)value).getNumber());
      }else {
         return null;
      }
   }

   public static String getString(JsonValue value) {
      if (value == null) {
         return null;
      }
      if (value instanceof JsonString) {
         return ((JsonString)value).getContent();
      }else {
         return null;
      }
   }
}
