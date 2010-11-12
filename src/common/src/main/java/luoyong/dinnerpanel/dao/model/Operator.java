package luoyong.dinnerpanel.dao.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
@Entity
@Table(name="operator")
@SecondaryTable(name="login_operator", pkJoinColumns={
   @PrimaryKeyJoinColumn(name="user_id")})
@NamedQueries({
//   @NamedQuery(name=Operator.QUERY_REMOVE_OPERATOR_INFORMATION, query="update "
//      + "Operator o "
//      + "set o.ek=luoyong.dinnerpanel.dao.model.ExistKey.D "
//      + "where o.id=?1"),
   @NamedQuery(name=Operator.QUERY_REMOVE_OPERATOR_INFORMATION,
      query="delete from Operator o where o.id=?1"),
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
   @Column(length=1, name="ek")
   private ExistKey ek;

   @Id
   @Column(length=50, name="id")
   private String id;

   @Column(length=100, name="name")
   private String name;

   @Column(length=100, name="password")
   private String password;

   @Column(length=3000, name="description")
   private String description;

   @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
   @Column(length=1, name="status")
   private OperatorStatus status;

   @Column(table="login_operator", name="password", length=100)
   private String loginPassword;

   @ElementCollection
   @CollectionTable(name="login_operator_group", joinColumns = {
      @JoinColumn(table="login_operator",
         name="user_id", referencedColumnName="id")})
   @Column(table="login_operator_group", length=100, name="group_name")
   @javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
   private Set<OperatorGroup> groups;

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

   public Set<OperatorGroup> getGroups() {
      return groups;
   }

   public void setGroups(Set<OperatorGroup> groups) {
      this.groups = groups;
   }

   public String getLoginPassword() {
      return loginPassword;
   }

   public void setLoginPassword(String loginPassword) {
      this.loginPassword = loginPassword;
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
