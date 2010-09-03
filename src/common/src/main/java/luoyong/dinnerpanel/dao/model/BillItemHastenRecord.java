package luoyong.dinnerpanel.dao.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
@Entity
@Table
@NamedQueries({
   @NamedQuery(
      name = BillItemHastenRecord.QUERY_CALCULATE_BILL_ITEM_HASTEN_COUNT,
      query = "select count(h) from BillItemHastenRecord h "
         + "where h.billItemId=?1")
})
public class BillItemHastenRecord implements Serializable {

   public static final String QUERY_CALCULATE_BILL_ITEM_HASTEN_COUNT
           = "calculate_bill_item_hasten_count";

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO,
      generator = "sequence_bill_item_hasten_record")
   private Long id;
   
   @Column
   private Long billId;
   
   @Column
   private Long billItemId;

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
