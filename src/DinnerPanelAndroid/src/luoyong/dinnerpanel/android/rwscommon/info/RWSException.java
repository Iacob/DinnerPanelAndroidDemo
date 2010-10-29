package luoyong.dinnerpanel.android.rwscommon.info;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class RWSException extends Exception {

   private String errorId = null;

   public RWSException(String message) {
      super(message);
   }

   public RWSException(String errorId, String message) {
      super(message);
      this.errorId = errorId;
   }

   public RWSException(String message, Throwable cause) {
      super(message, cause);
   }

   public RWSException(String errorId, String message, Throwable cause) {
      super(message, cause);
      this.errorId = errorId;
   }

   public String getErrorId() {
      return this.errorId;
   }
}
