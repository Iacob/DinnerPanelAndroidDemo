package luoyong.dinnerpanel.device.javame.authentication;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class CredentialHolder {

   private static String USERNAME = null;
   private static String PASSWORD = null;

   public static void setCredential(String username, String password) {
      USERNAME = username;
      PASSWORD = password;
   }

   public static String getUsername() {
      return USERNAME;
   }

   public static String getPassword() {
      return PASSWORD;
   }
}
