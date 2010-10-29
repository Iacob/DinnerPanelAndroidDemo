package luoyong.dinnerpanel.android.model;

import java.io.Serializable;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class SaleSite implements Serializable {
   
   private ExistKey ek;
   private Long id;
   private String name;
   private String description;
   private String status;

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

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
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
      SaleSite other = (SaleSite)obj;
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
