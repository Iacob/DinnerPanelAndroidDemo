package luoyong.dinnerpanel.android.model;

import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class Operator implements Serializable {
   
   private ExistKey ek;
   private String id;
   private String name;
   private String password;
   private String description;
   private OperatorStatus status;
   private String loginPassword;
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
