package luoyong.dinnerpanel.android.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class SpecialEvent implements Serializable {
   
   private ExistKey ek;
   private Long id;
   private String title;
   private String detail;
   private Date happenedTime;
   private BigDecimal cost;
   private String type;

   public BigDecimal getCost() {
      return cost;
   }

   public void setCost(BigDecimal cost) {
      this.cost = cost;
   }

   public String getDetail() {
      return detail;
   }

   public void setDetail(String detail) {
      this.detail = detail;
   }

   public ExistKey getEk() {
      return ek;
   }

   public void setEk(ExistKey ek) {
      this.ek = ek;
   }

   public Date getHappenedTime() {
      return happenedTime;
   }

   public void setHappenedTime(Date happenedTime) {
      this.happenedTime = happenedTime;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      }
      if (obj.getClass() != this.getClass()) {
         return false;
      }
      SpecialEvent other = (SpecialEvent)obj;
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
