package luoyong.dinnerpanel.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
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

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
@Entity
@Table(name="bill")
@NamedQueries({
   @NamedQuery(name = Bill.QUERY_CHECKOUT_BILL,
      query = "update Bill b set b.checkoutTime=?1 "
         + "where b.id=?2 "
         + "and b.ek=luoyong.dinnerpanel.dao.model.ExistKey.E "
         + "and b.status <> luoyong.dinnerpanel.dao.model.BillStatus.C"),
   @NamedQuery(name = Bill.QUERY_GET_CURRENT_BILL_FROM_SALE_PLACE,
      query = "select b from Bill b "
         + "where b.salePlaceId=?1 "
         + "and b.checkoutTime is null "
         + "and b.ek=luoyong.dinnerpanel.dao.model.ExistKey.E "
         + "and b.status <> luoyong.dinnerpanel.dao.model.BillStatus.C "
         + "order by b.boughtTime"),
   @NamedQuery(name = Bill.QUERY_GET_CURRENT_BILL_COUNT_FROM_SALE_PLACE,
      query = "select count(b) from Bill b "
         + "where b.salePlaceId=?1 "
         + "and b.checkoutTime is null "
         + "and b.ek=luoyong.dinnerpanel.dao.model.ExistKey.E "
         + "and b.status <> luoyong.dinnerpanel.dao.model.BillStatus.C"),
   @NamedQuery(name = Bill.QUERY_GET_BILL_INFORMATION,
      query = "select b from Bill b "
         + "where b.id=?1 "
         + "and b.status <> luoyong.dinnerpanel.dao.model.BillStatus.C "
         + "and b.ek=luoyong.dinnerpanel.dao.model.ExistKey.E"),
   @NamedQuery(name = Bill.QUERY_SET_COMMENT_TO_BILL,
      query = "update Bill b set b.comment=?1 "
         + "where b.id=?2 "
         + "and b.ek=luoyong.dinnerpanel.dao.model.ExistKey.E "
         + "and b.status <> luoyong.dinnerpanel.dao.model.BillStatus.C"),
   @NamedQuery(name = Bill.QUERY_SET_SELLING_PRICE_TO_BILL,
      query = "update Bill b set b.sellingPrice=?1 "
         + "where b.id=?2 "
         + "and b.ek=luoyong.dinnerpanel.dao.model.ExistKey.E "
         + "and b.status <> luoyong.dinnerpanel.dao.model.BillStatus.C "
         + "and b.checkoutTime is null"),
   @NamedQuery(name = Bill.QUERY_WRITE_BILL_PRICE,
      query = "update Bill b set b.price=?1 "
         + "where b.id=?2 "
         + "and b.ek=luoyong.dinnerpanel.dao.model.ExistKey.E "
         + "and b.status <> luoyong.dinnerpanel.dao.model.BillStatus.C "
         + "and b.checkoutTime is null"),
   @NamedQuery(name = Bill.QUERY_REMOVE_BILL,
      query = "update Bill b set b.ek=luoyong.dinnerpanel.dao.model.ExistKey.D "
         + "where b.id=?1 "
         + "and b.status <> luoyong.dinnerpanel.dao.model.BillStatus.C"),
   @NamedQuery(name = Bill.QUERY_MARK_BILL_AS_SENT,
      query = "update Bill b "
         + "set b.status=luoyong.dinnerpanel.dao.model.BillStatus.S "
         + "where b.id=?1 and b.ek=luoyong.dinnerpanel.dao.model.ExistKey.E "
         + "and b.status <> luoyong.dinnerpanel.dao.model.BillStatus.C "
         + "and b.checkoutTime is null"),
   @NamedQuery(name = Bill.QUERY_CANCEL_BILL,
      query = "update Bill b "
         + "set b.status=luoyong.dinnerpanel.dao.model.BillStatus.C "
         + "where b.id=?1 and b.ek=luoyong.dinnerpanel.dao.model.ExistKey.E "
         + "and b.status <> luoyong.dinnerpanel.dao.model.BillStatus.C "
         + "and b.checkoutTime is null")
})
public class Bill implements Serializable {

   public static final String QUERY_CHECKOUT_BILL = "checkout_bill";
   public static final String QUERY_GET_CURRENT_BILL_FROM_SALE_PLACE
           = "get_current_bill_from_sale_place";
   public static final String QUERY_GET_CURRENT_BILL_COUNT_FROM_SALE_PLACE
           = "get_current_bill_count_from_sale_place";
   public static final String QUERY_GET_BILL_INFORMATION
           = "get_bill_information";
   public static final String QUERY_SET_COMMENT_TO_BILL
           = "set_comment_to_bill";
   public static final String QUERY_SET_SELLING_PRICE_TO_BILL
           = "set_selling_price_to_bill";
   public static final String QUERY_WRITE_BILL_PRICE = "write_bill_price";
   public static final String QUERY_REMOVE_BILL = "remove_bill";
   public static final String QUERY_MARK_BILL_AS_SENT = "mark_bill_as_sent";
   public static final String QUERY_CANCEL_BILL = "cancel_bill";
   
   @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
   @Column(length=1, name="ek")
   private ExistKey ek;

   @Id
   @GeneratedValue(strategy=GenerationType.AUTO,
      generator="sequence_bill")
   @Column(name="id")
   private Long id;

   @Column(name="bought_time")
   @Temporal(javax.persistence.TemporalType.TIMESTAMP)
   private Date boughtTime;

   @Column(name="checkout_time")
   @Temporal(javax.persistence.TemporalType.TIMESTAMP)
   private Date checkoutTime;

   @Column(name="sale_place_id")
   private Long salePlaceId;

   @Column(length=100, name="sale_place_name")
   private String salePlaceName;

   @Column(name="sale_site_id")
   private Long saleSiteId;

   @Column(length=100, name="sale_site_name")
   private String saleSiteName;

   @Column(name="operator_id")
   private Long operatorId;

   @Column(length=100, name="operator_name")
   private String operatorName;

   @Column(name="price")
   private BigDecimal price;

   @Column(name="selling_price")
   private BigDecimal sellingPrice;

   @Column(length=3000, name="comment")
   private String comment;

   @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
   @Column(length=1, name="status")
   private BillStatus status;
   
   public Date getBoughtTime() {
      return boughtTime;
   }

   public void setBoughtTime(Date boughtTime) {
      this.boughtTime = boughtTime;
   }

   public Date getCheckoutTime() {
      return checkoutTime;
   }

   public void setCheckoutTime(Date checkoutTime) {
      this.checkoutTime = checkoutTime;
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

   public Long getSalePlaceId() {
      return salePlaceId;
   }

   public void setSalePlaceId(Long salePlaceId) {
      this.salePlaceId = salePlaceId;
   }

   public String getSalePlaceName() {
      return salePlaceName;
   }

   public void setSalePlaceName(String salePlaceName) {
      this.salePlaceName = salePlaceName;
   }

   public Long getSaleSiteId() {
      return saleSiteId;
   }

   public void setSaleSiteId(Long saleSiteId) {
      this.saleSiteId = saleSiteId;
   }

   public String getSaleSiteName() {
      return saleSiteName;
   }

   public void setSaleSiteName(String saleSiteName) {
      this.saleSiteName = saleSiteName;
   }

   public BigDecimal getSellingPrice() {
      return sellingPrice;
   }

   public void setSellingPrice(BigDecimal sellingPrice) {
      this.sellingPrice = sellingPrice;
   }

   public BillStatus getStatus() {
      return status;
   }

   public void setStatus(BillStatus status) {
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
      Bill other = (Bill)obj;
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

   @Override
   public int hashCode() {
      int hash = 7;
      hash = 67 * hash + (this.ek != null ? this.ek.hashCode() : 0);
      hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
      return hash;
   }
}
