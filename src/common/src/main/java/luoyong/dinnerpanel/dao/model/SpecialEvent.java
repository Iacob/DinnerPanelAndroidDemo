package luoyong.dinnerpanel.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
@Entity
@Table(name="special_event")
public class SpecialEvent implements Serializable {

   @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
   @Column(length=1, name="ek")
   private ExistKey ek;

   @Id
   @GeneratedValue(strategy=GenerationType.AUTO,
      generator="sequence_special_event")
   @Column(name="id")
   private Long id;

   @Column(length=1000, name="title")
   private String title;

   @Column(length=3000, name="detail")
   private String detail;

   @Column(name="happened_time")
   @Temporal(javax.persistence.TemporalType.TIMESTAMP)
   private Date happenedTime;

   @Column(name="cost")
   private BigDecimal cost;

   @Column(length=1, name="type")
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
