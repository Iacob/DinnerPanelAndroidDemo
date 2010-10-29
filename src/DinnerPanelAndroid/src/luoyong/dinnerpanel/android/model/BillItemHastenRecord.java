package luoyong.dinnerpanel.android.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class BillItemHastenRecord implements Serializable {
   
   private Long id;
   private Long billId;
   private Long billItemId;
   private Date hastenTime;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getBillId() {
      return billId;
   }

   public void setBillId(Long billId) {
      this.billId = billId;
   }

   public Long getBillItemId() {
      return billItemId;
   }

   public void setBillItemId(Long billItemId) {
      this.billItemId = billItemId;
   }

   public Date getHastenTime() {
      return hastenTime;
   }

   public void setHastenTime(Date hastenTime) {
      this.hastenTime = hastenTime;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      }
      if (obj.getClass() != this.getClass()) {
         return false;
      }
      BillItemHastenRecord other = (BillItemHastenRecord)obj;
      if (other.getId() == null) {
         return false;
      }
      if (this.getId() == null) {
         return false;
      }
      if (!other.getId().equals(this.getId())) {
         return false;
      }
      return true;
   }
}
