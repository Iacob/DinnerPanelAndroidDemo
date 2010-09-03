package luoyong.dinnerpanel.dao.model;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public enum SalePlaceServiceStatus {

   A, T, B, F, N;

   @Override
   public String toString() {
      if (this.name() == null) {
         return null;
      }else if (this.name().equals("A")) {
         return "启用";
      }else if (this.name().equals("T")) {
         return "占用";
      }else if (this.name().equals("B")) {
         return "预定";
      }else if (this.name().equals("F")) {
         return "故障";
      }else if (this.name().equals("N")) {
         return "无人管理";
      }else{
         return null;
      }
   }
}
