package luoyong.dinnerpanel.dao.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
@Entity
@Table(name="login_operator_group")
public class LoginOperatorGroup implements Serializable {

   @Id
   @Column(name="user_id", length=100)
   private String userId;
   @Column(name="group_name", length=100)
   private String groupName;

   public String getGroupName() {
      return groupName;
   }

   public void setGroupName(String groupName) {
      this.groupName = groupName;
   }

   public String getUserId() {
      return userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }
}
