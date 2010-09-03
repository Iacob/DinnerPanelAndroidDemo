package luoyong.dinnerpanel.dao.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
@Entity
@Table
@javax.persistence.NamedQueries({
   @NamedQuery(name=FoodCategory.QUERY_GET_ALL_FOOD_CATEGORIES,
      query="select c from FoodCategory c "
         + "where c.ek=luoyong.dinnerpanel.dao.model.ExistKey.E"),
   @NamedQuery(name=FoodCategory.QUERY_GET_FOOD_CATEGORY,
      query="select c from FoodCategory c where c.id=?1 "
         + "and c.ek=luoyong.dinnerpanel.dao.model.ExistKey.E"),
   @NamedQuery(name=FoodCategory.QUERY_REMOVE_FOOD_CATEGORY,
      query="update FoodCategory c "
         + "set c.ek=luoyong.dinnerpanel.dao.model.ExistKey.D "
         + "where c.id=?1")})
public class FoodCategory implements Serializable {

   public static final String QUERY_GET_ALL_FOOD_CATEGORIES
           = "get_all_food_categories";
   public static final String QUERY_GET_FOOD_CATEGORY = "get_food_category";
   public static final String QUERY_REMOVE_FOOD_CATEGORY
           = "remove_food_category";

   @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
   @Column(length=1)
   private ExistKey ek;

   @Id
   @GeneratedValue(strategy=GenerationType.AUTO,
      generator="sequence_food_category")
   @Column(name="id")
   private Long id;
   
   @Column(name="name",length=100)
   private String name;

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

   @Override
   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      }
      if (obj.getClass() != this.getClass()) {
         return false;
      }
      FoodCategory other = (FoodCategory)obj;
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
