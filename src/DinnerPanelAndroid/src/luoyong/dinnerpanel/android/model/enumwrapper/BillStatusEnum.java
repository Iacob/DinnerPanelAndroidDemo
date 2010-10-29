package luoyong.dinnerpanel.android.model.enumwrapper;

import luoyong.dinnerpanel.android.model.BillStatus;

/**
 * Wrapper for enum type BillStatus.
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class BillStatusEnum {

   private BillStatus status = null;

   public BillStatusEnum(BillStatus status) {
      this.status = status;
   }

   public BillStatus getEnum() {
      return this.status;
   }

   @Override
   public String toString() {

      BillStatus billStatus = this.getEnum();

      if (billStatus == null) {
         return null;
      }else if (billStatus.name() == null) {
         return null;
      }else if (billStatus.name().equals("S")) {
         return "已送出";
      }else if (billStatus.name().equals("N")) {
         return "未送出";
      }else if (billStatus.name().equals("C")) {
         return "已取消";
      }else {
         return null;
      }
   }
}
