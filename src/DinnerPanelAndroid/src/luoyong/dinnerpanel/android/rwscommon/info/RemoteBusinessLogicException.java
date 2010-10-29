package luoyong.dinnerpanel.android.rwscommon.info;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class RemoteBusinessLogicException extends RWSException {

   public RemoteBusinessLogicException(
           String errorId, String message, Throwable cause) {
      
      super(errorId, message, cause);
   }

   public RemoteBusinessLogicException(String message, Throwable cause) {
      super(message, cause);
   }

   public RemoteBusinessLogicException(String errorId, String message) {
      super(errorId, message);
   }

   public RemoteBusinessLogicException(String message) {
      super(message);
   }

}
