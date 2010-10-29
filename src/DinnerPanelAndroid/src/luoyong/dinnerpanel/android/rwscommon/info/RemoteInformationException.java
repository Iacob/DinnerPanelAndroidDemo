package luoyong.dinnerpanel.android.rwscommon.info;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class RemoteInformationException extends RWSException {

   public RemoteInformationException(
           String errorId, String message, Throwable cause) {

      super(errorId, message, cause);
   }

   public RemoteInformationException(String message, Throwable cause) {
      super(message, cause);
   }

   public RemoteInformationException(String errorId, String message) {
      super(errorId, message);
   }

   public RemoteInformationException(String message) {
      super(message);
   }
}
