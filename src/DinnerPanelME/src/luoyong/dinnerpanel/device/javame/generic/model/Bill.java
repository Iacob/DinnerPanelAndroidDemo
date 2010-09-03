package luoyong.dinnerpanel.device.javame.generic.model;

import java.util.Date;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class Bill {
   
   private String ek;
   
   private Long id;
   
   private Date boughtTime;
   
   private Date checkoutTime;
   
   private Long salePlaceId;
   
   private String salePlaceName;
   
   private Long saleSiteId;
   
   private String saleSiteName;
   
   private Long operatorId;
   
   private String operatorName;
   
   private Double price;
   
   private Double sellingPrice;
   
   private String comment;
   
   private String status;

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

   public String getEk() {
      return ek;
   }

   public void setEk(String ek) {
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

   public Double getPrice() {
      return price;
   }

   public void setPrice(Double price) {
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

   public Double getSellingPrice() {
      return sellingPrice;
   }

   public void setSellingPrice(Double sellingPrice) {
      this.sellingPrice = sellingPrice;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }
}
