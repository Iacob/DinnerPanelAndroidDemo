package luoyong.dinnerpanel.android.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class Bill implements Serializable {
   
   private ExistKey ek;
   private Long id;
   private Date boughtTime;
   private Date checkoutTime;
   private Long salePlaceId;
   private String salePlaceName;
   private Long saleSiteId;
   private String saleSiteName;
   private Long operatorId;
   private String operatorName;
   private BigDecimal price;
   private BigDecimal sellingPrice;
   private String comment;
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
