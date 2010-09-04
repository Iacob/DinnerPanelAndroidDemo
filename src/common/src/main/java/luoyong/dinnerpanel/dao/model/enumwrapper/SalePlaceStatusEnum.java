package luoyong.dinnerpanel.dao.model.enumwrapper;

import luoyong.dinnerpanel.dao.model.SalePlaceStatus;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class SalePlaceStatusEnum {

   private SalePlaceStatus status = null;

   public SalePlaceStatusEnum(SalePlaceStatus status) {
      this.status = status;
   }

   public SalePlaceStatus getEnum() {
      return this.status;
   }

   @Override
   public String toString() {

      SalePlaceStatus salePlaceStatus = this.getEnum();

      if (salePlaceStatus == null) {
         return null;
      }else if (salePlaceStatus.name() == null) {
         return null;
      }else if (salePlaceStatus.name().equals("A")) {
         return "启用";
      }else if (salePlaceStatus.name().equals("U")) {
         return "停用";
      }else if (salePlaceStatus.name().equals("P")) {
         return "审核中";
      }else {
         return null;
      }
   }
}
