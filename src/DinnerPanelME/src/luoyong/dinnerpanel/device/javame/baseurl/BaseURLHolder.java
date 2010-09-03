package luoyong.dinnerpanel.device.javame.baseurl;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class BaseURLHolder {

   private static String _BASE_URL = null;

   public static void setBaseURL(String baseURL) {
      _BASE_URL = baseURL;
   }

   public static String getBaseURL() {
      return _BASE_URL;
   }
}
