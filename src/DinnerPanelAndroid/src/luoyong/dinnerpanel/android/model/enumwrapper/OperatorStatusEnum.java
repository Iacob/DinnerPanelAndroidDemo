package luoyong.dinnerpanel.android.model.enumwrapper;

import luoyong.dinnerpanel.android.model.OperatorStatus;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class OperatorStatusEnum {

   private OperatorStatus status = null;

   public OperatorStatusEnum(OperatorStatus status) {
      this.status = status;
   }

   public OperatorStatus getEnum() {
      return this.status;
   }

   @Override
   public String toString() {

      OperatorStatus operatorStatus = this.getEnum();

      if (operatorStatus == null) {
         return null;
      }else if (operatorStatus.name() == null) {
         return null;
      }else if (operatorStatus.name().equals("O")) {
         return "在职";
      }else if (operatorStatus.name().equals("Q")) {
         return "离职";
      }else if (operatorStatus.name().equals("P")) {
         return "审核中";
      }else {
         return null;
      }
   }
}
