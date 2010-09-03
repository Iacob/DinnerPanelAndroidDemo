package luoyong.dinnerpanel.device.javame.generic.model;

import java.util.Date;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class BillItem {
   
   private String ek;
   
   private Long id;
   
   private Bill bill;
   
   private Long foodId;
   
   private String foodName;

   private Date addedTime;
   
   private Integer foodCount;
   
   private Double price;
   
   private Long operatorId;
   
   private String operatorName;
   
   private Integer hastenCount;
   
   private String comment;
   
   private String status;

   public Date getAddedTime() {
      return addedTime;
   }

   public void setAddedTime(Date addedTime) {
      this.addedTime = addedTime;
   }

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

   public String getEk() {
      return ek;
   }

   public void setEk(String ek) {
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

   public Double getPrice() {
      return price;
   }

   public void setPrice(Double price) {
      this.price = price;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }
}
