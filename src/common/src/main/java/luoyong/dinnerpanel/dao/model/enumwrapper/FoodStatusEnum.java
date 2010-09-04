package luoyong.dinnerpanel.dao.model.enumwrapper;

import luoyong.dinnerpanel.dao.model.FoodStatus;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class FoodStatusEnum {

   private FoodStatus status = null;

   public FoodStatusEnum(FoodStatus status) {
      this.status = status;
   }

   public FoodStatus getEnum() {
      return this.status;
   }

   @Override
   public String toString() {

      FoodStatus foodtatus = this.getEnum();

      if (foodtatus == null) {
         return null;
      }else if (foodtatus.name() == null) {
         return null;
      }else if (foodtatus.name().equals("A")) {
         return "启用";
      }else if (foodtatus.name().equals("U")) {
         return "停用";
      }else if (foodtatus.name().equals("P")) {
         return "审核中";
      }else {
         return null;
      }
   }
}
