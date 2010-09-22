package luoyong.dinnerpanel.dao.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
@Entity
@Table(name="bill_item_hasten_record")
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
   @Column(name="id")
   private Long id;
   
   @Column(name="bill")
   private Long billId;
   
   @Column(name="bill_item")
   private Long billItemId;

   @Column(name="hasten_time")
   @Temporal(TemporalType.TIMESTAMP)
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
