package luoyong.dinnerpanel.dao.model;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public enum OperatorStatus {

   O,Q,P;

   @Override
   public String toString() {
      if (this.name() == null) {
         return null;
      }else if (this.name().equals("O")) {
         return "在职";
      }else if (this.name().equals("Q")) {
         return "离职";
      }else if (this.name().equals("P")) {
         return "审核中";
      }else {
         return null;
      }
   }
}
