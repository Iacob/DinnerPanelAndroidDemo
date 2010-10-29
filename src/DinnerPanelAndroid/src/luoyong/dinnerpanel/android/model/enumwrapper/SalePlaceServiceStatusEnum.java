package luoyong.dinnerpanel.android.model.enumwrapper;

import luoyong.dinnerpanel.android.model.SalePlaceServiceStatus;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class SalePlaceServiceStatusEnum {

   private SalePlaceServiceStatus status = null;

   public SalePlaceServiceStatusEnum(SalePlaceServiceStatus status) {
      this.status = status;
   }

   public SalePlaceServiceStatus getEnum() {
      return this.status;
   }

   @Override
   public String toString() {

      SalePlaceServiceStatus salePlaceServiceStatus = this.getEnum();

      if (salePlaceServiceStatus == null) {
         return null;
      }else if (salePlaceServiceStatus.name() == null) {
         return null;
      }else if (salePlaceServiceStatus.name().equals("A")) {
         return "启用";
      }else if (salePlaceServiceStatus.name().equals("T")) {
         return "占用";
      }else if (salePlaceServiceStatus.name().equals("B")) {
         return "预定";
      }else if (salePlaceServiceStatus.name().equals("F")) {
         return "故障";
      }else if (salePlaceServiceStatus.name().equals("N")) {
         return "无人管理";
      }else {
         return null;
      }
   }
}
