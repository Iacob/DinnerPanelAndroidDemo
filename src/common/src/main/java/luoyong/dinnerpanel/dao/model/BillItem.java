package luoyong.dinnerpanel.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
@Entity
@Table(name="bill_item")
@NamedQueries({@NamedQuery(name=BillItem.QUERY_REMOVE_ITEM_FROM_BILL,
      query="update BillItem i "
         + "set i.ek=luoyong.dinnerpanel.dao.model.ExistKey.D "
         + "where i.id=?1 "
         + "and i.ek=luoyong.dinnerpanel.dao.model.ExistKey.E "
         + "and i.status<>luoyong.dinnerpanel.dao.model.BillItemStatus.C "
         + "and i.status<>luoyong.dinnerpanel.dao.model.BillItemStatus.R"),
   @NamedQuery(name=BillItem.QUERY_CANCEL_ITEM_FROM_BILL,
      query="update BillItem i "
         + "set i.status=luoyong.dinnerpanel.dao.model.BillItemStatus.C "
         + "where i.id=?1 and i.ek=luoyong.dinnerpanel.dao.model.ExistKey.E "
         + "and i.status=luoyong.dinnerpanel.dao.model.BillItemStatus.W"),
   @NamedQuery(name=BillItem.QUERY_MARK_BILL_ITEM_COMPLETE,
      query="update BillItem i "
         + "set i.status=luoyong.dinnerpanel.dao.model.BillItemStatus.F "
         + "where i.id=?1 and i.ek=luoyong.dinnerpanel.dao.model.ExistKey.E "
         + "and i.status<>luoyong.dinnerpanel.dao.model.BillItemStatus.C "
         + "and i.status<>luoyong.dinnerpanel.dao.model.BillItemStatus.R"),
   @NamedQuery(name=BillItem.QUERY_MARK_BILL_ITEM_PROCESSING,
      query="update BillItem i "
         + "set i.status=luoyong.dinnerpanel.dao.model.BillItemStatus.P "
         + "where i.id=?1 and i.ek=luoyong.dinnerpanel.dao.model.ExistKey.E "
         + "and i.status=luoyong.dinnerpanel.dao.model.BillItemStatus.W"),
   @NamedQuery(name=BillItem.QUERY_MARK_BILL_ITEM_RETURNED,
      query="update BillItem i "
         + "set i.status=luoyong.dinnerpanel.dao.model.BillItemStatus.R "
         + "where i.id=?1 and i.ek=luoyong.dinnerpanel.dao.model.ExistKey.E "
         + "and i.status=luoyong.dinnerpanel.dao.model.BillItemStatus.F"),
   @NamedQuery(name=BillItem.QUERY_GET_BILL_ITEM_INFORMATION,query="select i "
      + "from BillItem i "
      + "where i.id=?1 and i.ek=luoyong.dinnerpanel.dao.model.ExistKey.E "
      + "and i.status<>luoyong.dinnerpanel.dao.model.BillItemStatus.C "
      + "and i.status<>luoyong.dinnerpanel.dao.model.BillItemStatus.R "
      + "and i.bill is not null "
      + "and i.bill.ek=luoyong.dinnerpanel.dao.model.ExistKey.E "
      + "and i.bill.status <> luoyong.dinnerpanel.dao.model.BillStatus.C"),
   @NamedQuery(name=BillItem.QUERY_GET_ALL_BILL_ITEMS_FROM_BILL,
      query="select i from BillItem i "
         + "where i.bill=?1 "
         + "and i.ek=luoyong.dinnerpanel.dao.model.ExistKey.E "
         + "and i.status<>luoyong.dinnerpanel.dao.model.BillItemStatus.C "
         + "and i.status<>luoyong.dinnerpanel.dao.model.BillItemStatus.R "
         + "and i.bill is not null "
         + "and i.bill.ek=luoyong.dinnerpanel.dao.model.ExistKey.E "
         + "and i.bill.status <> luoyong.dinnerpanel.dao.model.BillStatus.C"),
   @NamedQuery(name=BillItem.QUERY_CALCULATE_BILL_PRICE,
      query="select sum(i.price) from BillItem i "
         + "where i.bill=?1 "
         + "and i.ek=luoyong.dinnerpanel.dao.model.ExistKey.E "
         + "and i.status<>luoyong.dinnerpanel.dao.model.BillItemStatus.C "
         + "and i.status<>luoyong.dinnerpanel.dao.model.BillItemStatus.R "
         + "and i.bill is not null "
         + "and i.bill.ek=luoyong.dinnerpanel.dao.model.ExistKey.E "
         + "and i.bill.status <> luoyong.dinnerpanel.dao.model.BillStatus.C"),
   @NamedQuery(name=BillItem.QUERY_SET_COMMENT_TO_BILL_ITEM,
      query="update BillItem i set i.comment=?1 "
         + "where i.id = ?2 "
         + "and i.ek=luoyong.dinnerpanel.dao.model.ExistKey.E "
         + "and i.status=luoyong.dinnerpanel.dao.model.BillItemStatus.W "
         + "and i.bill is not null "
         + "and i.bill.ek=luoyong.dinnerpanel.dao.model.ExistKey.E "
         + "and i.bill.status <> luoyong.dinnerpanel.dao.model.BillStatus.C"),
   @NamedQuery(name=BillItem.QUERY_WRITE_BILL_ITEM_HASTEN_COUNT,
      query="update BillItem i set i.hastenCount=?1 "
         + "where i.id = ?2 "
         + "and i.ek=luoyong.dinnerpanel.dao.model.ExistKey.E "
         + "and i.status<>luoyong.dinnerpanel.dao.model.BillItemStatus.C "
         + "and i.bill is not null "
         + "and i.bill.ek=luoyong.dinnerpanel.dao.model.ExistKey.E "
         + "and i.bill.status <> luoyong.dinnerpanel.dao.model.BillStatus.C")
})
public class BillItem implements Serializable {

   public static final String QUERY_REMOVE_ITEM_FROM_BILL
           = "remove_item_from_bill";
   public static final String QUERY_CANCEL_ITEM_FROM_BILL
           = "cancel_item_from_bill";
   public static final String QUERY_MARK_BILL_ITEM_COMPLETE
           = "mark_bill_item_complete";
   public static final String QUERY_MARK_BILL_ITEM_PROCESSING
           = "mark_bill_item_processing";
   public static final String QUERY_MARK_BILL_ITEM_RETURNED
           = "mark_bill_item_returned";
   public static final String QUERY_GET_BILL_ITEM_INFORMATION
           = "get_bill_item_information";
   public static final String QUERY_GET_ALL_BILL_ITEMS_FROM_BILL
           = "get_bill_items_from_bill";
   public static final String QUERY_SET_COMMENT_TO_BILL_ITEM
           = "set_comment_to_bill_item";
   public static final String QUERY_CALCULATE_BILL_PRICE
           = "calculate_bill_price";
   public static final String QUERY_WRITE_BILL_ITEM_HASTEN_COUNT
           = "write_bill_item_hasten_count";

   @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
   @Column(length=1, name="ek")
   private ExistKey ek;

   @Id
   @GeneratedValue(strategy=GenerationType.AUTO,
      generator="sequence_bill_item")
   @Column(name="id")
   private Long id;

   @ManyToOne
   @JoinColumn(name="bill")
   private Bill bill;

   @Column(name="food_id")
   private Long foodId;

   @Column(name="added_time")
   @Temporal(javax.persistence.TemporalType.TIMESTAMP)
   private Date addedTime;

   @Column(length=1000, name="food_name")
   private String foodName;

   @Column(name="food_count")
   private Integer foodCount;

   @Column(name="price")
   private BigDecimal price;

   @Column(name="operator_id")
   private Long operatorId;

   @Column(length=100, name="operator_name")
   private String operatorName;

   @Column(name="hasten_count")
   private Integer hastenCount;

   @Column(length=3000, name="comment")
   private String comment;

   @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
   @Column(length=1, name="status")
   private BillItemStatus status;

   public Bill getBill() {
      return bill;
   }

   public void setBill(Bill bill) {
      this.bill = bill;
   }

   public String getComment() {
      return comment;
   }

   public void setComment(String comment) {
      this.comment = comment;
   }

   public ExistKey getEk() {
      return ek;
   }

   public void setEk(ExistKey ek) {
      this.ek = ek;
   }

   public Integer getFoodCount() {
      return foodCount;
   }

   public void setFoodCount(Integer foodCount) {
      this.foodCount = foodCount;
   }

   public Long getFoodId() {
      return foodId;
   }

   public void setFoodId(Long foodId) {
      this.foodId = foodId;
   }

   public Date getAddedTime() {
      return addedTime;
   }

   public void setAddedTime(Date addedTime) {
      this.addedTime = addedTime;
   }

   public String getFoodName() {
      return foodName;
   }

   public void setFoodName(String foodName) {
      this.foodName = foodName;
   }

   public Integer getHastenCount() {
      return hastenCount;
   }

   public void setHastenCount(Integer hastenCount) {
      this.hastenCount = hastenCount;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getOperatorId() {
      return operatorId;
   }

   public void setOperatorId(Long operatorId) {
      this.operatorId = operatorId;
   }

   public String getOperatorName() {
      return operatorName;
   }

   public void setOperatorName(String operatorName) {
      this.operatorName = operatorName;
   }

   public BigDecimal getPrice() {
      return price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public BillItemStatus getStatus() {
      return status;
   }

   public void setStatus(BillItemStatus status) {
      this.status = status;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      }
      if (obj.getClass() != this.getClass()) {
         return false;
      }
      BillItem other = (BillItem)obj;
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
