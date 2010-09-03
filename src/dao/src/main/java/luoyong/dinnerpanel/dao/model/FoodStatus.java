package luoyong.dinnerpanel.dao.model;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public enum FoodStatus {

   A, U, P;

   @Override
   public String toString() {
      if (this.name() == null) {
         return "未知";
      }else if (this.name().equals("A")) {
         return "启用";
      }else if (this.name().equals("U")) {
         return "停用";
      }else if (this.name().equals("P")) {
         return "审核中";
      }else {
         return "未知";
      }
   }
}
