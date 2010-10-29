package luoyong.dinnerpanel.android.rwscommon.info;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class RemoteAuthorizationException extends RWSException {

   public RemoteAuthorizationException(
           String errorId, String message, Throwable cause) {
      
      super(errorId, message, cause);
   }

   public RemoteAuthorizationException(String message, Throwable cause) {
      super(message, cause);
   }

   public RemoteAuthorizationException(String errorId, String message) {
      super(errorId, message);
   }

   public RemoteAuthorizationException(String message) {
      super(message);
   }
}
