package luoyong.dinnerpanel.rwscommon.info;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class RemoteConnectionException extends RWSException {

   public RemoteConnectionException(
           String errorId, String message, Throwable cause) {
      
      super(errorId, message, cause);
   }

   public RemoteConnectionException(String message, Throwable cause) {
      super(message, cause);
   }

   public RemoteConnectionException(String errorId, String message) {
      super(errorId, message);
   }

   public RemoteConnectionException(String message) {
      super(message);
   }
}
