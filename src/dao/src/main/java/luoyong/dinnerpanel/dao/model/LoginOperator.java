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
@Table(name="login_operator")
public class LoginOperator implements Serializable {

   @Id
   @Column(name="user_id", length=100)
   private String userId;
   @Column(name="password", length=100)
   private String password;

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getUserId() {
      return userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }
}
