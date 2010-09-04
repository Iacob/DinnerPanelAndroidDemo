package luoyong.dinnerpanel.dao.model.enumwrapper;

import luoyong.dinnerpanel.dao.model.BillItemStatus;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class BillItemStatusEnum {

   private BillItemStatus status = null;

   public BillItemStatusEnum(BillItemStatus status) {
      this.status = status;
   }

   public BillItemStatus getEnum() {
      return this.status;
   }

   @Override
   public String toString() {
      if (this.status == null) {
         return null;
      }else if (this.status.name() == null) {
         return null;
      }else if (this.status.name().equals("W")) {
         return "待处理";
      }else if (this.status.name().equals("P")) {
         return "处理中";
      }else if (this.status.name().equals("F")) {
         return "已完成";
      }else if (this.status.name().equals("C")) {
         return "已撤销";
      }else if (this.status.name().equals("R")) {
         return "退菜";
      }else {
         return null;
      }
   }
}
