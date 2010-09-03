package luoyong.dinnerpanel.device.javame.generic.model;

import java.util.Hashtable;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class Food {
   
   private String ek;
   
   private Long id;
   
   private String name;
   
   private String abbreviation;
   
   private String code;
   
   private String description;
   
   //private FoodCategory category;
   
   private Double price;
   
   private String status;
   
   private Hashtable tags;

   public String getAbbreviation() {
      return abbreviation;
   }

   public void setAbbreviation(String abbreviation) {
      this.abbreviation = abbreviation;
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

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
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

   public Hashtable getTags() {
      return tags;
   }

   public void setTags(Hashtable tags) {
      this.tags = tags;
   }
}
