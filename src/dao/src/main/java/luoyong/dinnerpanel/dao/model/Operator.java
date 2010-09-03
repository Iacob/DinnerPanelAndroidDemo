package luoyong.dinnerpanel.dao.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
@Entity
@Table
@NamedQueries({@NamedQuery(name=Operator.QUERY_REMOVE_OPERATOR_INFORMATION,
   query="update Operator o "
      + "set o.ek=luoyong.dinnerpanel.dao.model.ExistKey.D "
      + "where o.id=?1"),
   @NamedQuery(name=Operator.QUERY_GET_OPERATOR_INFORMATION,query="select o "
      + "from Operator o "
      + "where o.id=?1 and o.ek=luoyong.dinnerpanel.dao.model.ExistKey.E"),
   @NamedQuery(name=Operator.QUERY_GET_ALL_OPERATOR_INFORMATION,
      query="select o from Operator o "
         + "where o.ek=luoyong.dinnerpanel.dao.model.ExistKey.E")
})
public class Operator implements Serializable {

   public static final String QUERY_REMOVE_OPERATOR_INFORMATION
           = "remove_operator_information";
   public static final String QUERY_GET_OPERATOR_INFORMATION
           = "get_operator_information";
   public static final String QUERY_GET_ALL_OPERATOR_INFORMATION
           = "get_all_operator_information";

   @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
   @Column(length=1)
   private ExistKey ek;

   @Id
   @Column(length=50)
   private String id;

   @Column(length=100)
   private String name;

   @Column(length=100)
   private String password;

   @Column(length=3000)
   private String description;

   @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
   @Column(length=1)
   private OperatorStatus status;

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

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public OperatorStatus getStatus() {
      return status;
   }

   public void setStatus(OperatorStatus status) {
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
      Operator other = (Operator)obj;
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
