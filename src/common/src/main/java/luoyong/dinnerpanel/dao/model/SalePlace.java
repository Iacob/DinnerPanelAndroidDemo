package luoyong.dinnerpanel.dao.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
@Entity
@Table
@NamedQueries({@NamedQuery(name=SalePlace.QUERY_GET_ALL_SALE_PLACES,
      query="select s from SalePlace s "
         + "where s.ek=luoyong.dinnerpanel.dao.model.ExistKey.E "
         + "order by s.name"),
   @NamedQuery(name=SalePlace.QUERY_REMOVE_SALE_PLACE,
      query="update SalePlace s "
         + "set s.ek=luoyong.dinnerpanel.dao.model.ExistKey.D "
         + "where s.id=?1"),
   @NamedQuery(name=SalePlace.QUERY_GET_SALE_PLACE,query="select s "
      + "from SalePlace s "
      + "where s.id = ?1 and s.ek=luoyong.dinnerpanel.dao.model.ExistKey.E")
})
public class SalePlace implements Serializable {

   public static final String QUERY_GET_ALL_SALE_PLACES = "get_all_sale_places";
   public static final String QUERY_REMOVE_SALE_PLACE = "remove_sale_place";
   public static final String QUERY_GET_SALE_PLACE = "get_sale_place";
   

   @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
   @Column(length=1)
   private ExistKey ek;

   @Id
   @GeneratedValue(strategy=GenerationType.AUTO,
      generator="sequence_sale_place")
   private Long id;

   @Column(length=100)
   private String name;

   @Column(length=3000)
   private String description;

   @Column(length=1)
   private String type;

   @ManyToOne
   private SaleSite saleSite;

   @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
   @Column(length=1)
   private SalePlaceServiceStatus serviceStatus;

   @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
   @Column(length=1)
   private SalePlaceStatus status;

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

   public SaleSite getSaleSite() {
      return saleSite;
   }

   public void setSaleSite(SaleSite saleSite) {
      this.saleSite = saleSite;
   }

   public SalePlaceServiceStatus getServiceStatus() {
      return serviceStatus;
   }

   public void setServiceStatus(SalePlaceServiceStatus serviceStatus) {
      this.serviceStatus = serviceStatus;
   }

   public SalePlaceStatus getStatus() {
      return status;
   }

   public void setStatus(SalePlaceStatus status) {
      this.status = status;
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
      SalePlace other = (SalePlace)obj;
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
