package luoyong.dinnerpanel.dao.model.enumwrapper;

import luoyong.dinnerpanel.dao.model.OperatorGroup;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class OperatorGroupEnum {

   private OperatorGroup group = null;

   public OperatorGroupEnum(OperatorGroup group) {
      this.group = group;
   }

   public OperatorGroup getEnum() {
      return this.group;
   }

   @Override
   public String toString() {
      if (this.group == null) {
         return null;
      }else if (this.group.name() == null) {
         return null;
      }else if (this.group.name().equals("customer")) {
         return "顾客";
      }else if (this.group.name().equals("operator")) {
         return "操作员";
      }else if (this.group.name().equals("kitchen")) {
         return "後厨人员";
      }else if (this.group.name().equals("clerk")) {
         return "文员";
      }else if (this.group.name().equals("manager")) {
         return "经理";
      }else if (this.group.name().equals("admin")) {
         return "系统管理员";
      }else {
         return null;
      }
   }
}
