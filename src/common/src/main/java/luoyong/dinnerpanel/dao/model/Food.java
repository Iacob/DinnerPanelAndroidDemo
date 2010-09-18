package luoyong.dinnerpanel.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
@Entity
@Table(name="food")
@NamedQueries({@NamedQuery(name=Food.QUERY_GET_FOOD_INFORMATION,
      query="select f from Food f where f.id=?1 "
         + "and f.ek=luoyong.dinnerpanel.dao.model.ExistKey.E"),
   @NamedQuery(name=Food.QUERY_REMOVE_FOOD_INFORMATION,query="update Food f "
      + "set f.ek=luoyong.dinnerpanel.dao.model.ExistKey.D "
      + "where f.id=?1"),
   @NamedQuery(name=Food.QUERY_SEARCH_FOOD_INFORMATION_BY_NAME,
      query="select f from Food f where f.name like ?1 "
         + "and f.ek=luoyong.dinnerpanel.dao.model.ExistKey.E"),
   @NamedQuery(name=Food.QUERY_SEARCH_FOOD_INFORMATION_BY_ABBREVIATION,
      query="select f from Food f where f.abbreviation like ?1 "
         + "and f.ek=luoyong.dinnerpanel.dao.model.ExistKey.E"),
   @NamedQuery(name=Food.QUERY_SEARCH_FOOD_INFORMATION_BY_CODE,
      query="select f from Food f where f.code like ?1 "
         + "and f.ek=luoyong.dinnerpanel.dao.model.ExistKey.E"),
   @NamedQuery(name=Food.QUERY_SEARCH_FOOD_INFORMATION_BY_TAG,
      query="select f from Food f, IN(f.tags) t where t like ?1 "
         + "and f.ek=luoyong.dinnerpanel.dao.model.ExistKey.E"),
   @NamedQuery(name=Food.QUERY_GET_ALL_FOOD_FROM_FOOD_CATEGORY,
      query="select f from Food f where f.category = ?1 "
         + "and f.ek=luoyong.dinnerpanel.dao.model.ExistKey.E")})
public class Food implements Serializable {

   public static final String QUERY_GET_FOOD_INFORMATION
           = "get_food_information";
   public static final String QUERY_REMOVE_FOOD_INFORMATION
           = "remove_food_information";
   public static final String QUERY_SEARCH_FOOD_INFORMATION_BY_NAME
           = "search_food_information_by_name";
   public static final String QUERY_SEARCH_FOOD_INFORMATION_BY_ABBREVIATION
           = "search_food_information_by_abbreviation";
   public static final String QUERY_SEARCH_FOOD_INFORMATION_BY_CODE
           = "search_food_information_by_code";
   public static final String QUERY_SEARCH_FOOD_INFORMATION_BY_TAG
           = "search_food_information_by_tag";
   public static final String QUERY_GET_ALL_FOOD_FROM_FOOD_CATEGORY
           = "get_all_food_from_food_category";

   @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
   @Column(length=1, name="ek")
   private ExistKey ek;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO,
      generator = "sequence_food")
   @Column(name="id")
   private Long id;

   @Column(length=100, name="name")
   private String name;

   @Column(length=100, name="abbreviation")
   private String abbreviation;

   @Column(length=100, name="code")
   private String code;

   @Column(length=3000, name="description")
   private String description;

   @ManyToOne
   @JoinColumn(name="category")
   private FoodCategory category;

   @Column(precision=10, scale=2, name="price")
   private BigDecimal price;
   
   @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
   @Column(length=1, name="status")
   private FoodStatus status;

   @ElementCollection
   private Set<String> tags;

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
