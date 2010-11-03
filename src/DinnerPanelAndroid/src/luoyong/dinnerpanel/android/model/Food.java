package luoyong.dinnerpanel.android.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class Food implements Serializable {
   
   private ExistKey ek;
   private Long id;
   private String name;
   private String abbreviation;
   private String code;
   private String description;
   private FoodCategory category;
   private BigDecimal price;
   private FoodStatus status;
   private Set<String> tags;
   private byte[] picture;
   private Integer recommend;

   public String getAbbreviation() {
      return abbreviation;
   }

   public void setAbbreviation(String abbreviation) {
      this.abbreviation = abbreviation;
   }

   public FoodCategory getCategory() {
      return category;
   }

   public void setCategory(FoodCategory category) {
      this.category = category;
   }

   public String getCode() {
      return code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
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

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public BigDecimal getPrice() {
      return price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public FoodStatus getStatus() {
      return status;
   }

   public void setStatus(FoodStatus status) {
      this.status = status;
   }

   public Set<String> getTags() {
      return tags;
   }

   public void setTags(Set<String> tags) {
      this.tags = tags;
   }

   public byte[] getPicture() {
      return picture;
   }

   public void setPicture(byte[] picture) {
      this.picture = picture;
   }

   public Integer getRecommend() {
      return recommend;
   }

   public void setRecommend(Integer recommend) {
      this.recommend = recommend;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      }
      if (obj.getClass() != this.getClass()) {
         return false;
      }
      Food other = (Food)obj;
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
