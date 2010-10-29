package luoyong.dinnerpanel.android.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class BillItem implements Serializable {
   
   private ExistKey ek;
   private Long id;
   private Bill bill;
   private Long foodId;
   private Date addedTime;
   private String foodName;
   private Integer foodCount;
   private BigDecimal price;
   private Long operatorId;
   private String operatorName;
   private Integer hastenCount;
   private String comment;
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
