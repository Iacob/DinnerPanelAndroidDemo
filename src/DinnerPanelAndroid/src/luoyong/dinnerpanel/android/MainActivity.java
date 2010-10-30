package luoyong.dinnerpanel.android;

import android.app.Activity;
import android.os.Bundle;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import luoyong.dinnerpanel.android.rwsclient.RWSURLHolder;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class MainActivity extends Activity {

   private DinnerPanelApplicationContext dpAppContext = null;

   @Override
   public void onCreate(Bundle icicle) {
      super.onCreate(icicle);

      this.setContentView(R.layout.main);

      dpAppContext = new DinnerPanelApplicationContext(this);

      // Test code begins.
      Authenticator.setDefault(new Authenticator() {
         @Override
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(
                    "user_admin", "pass".toCharArray());
         }
      });
      // Test code ends.

      dpAppContext.showCurrentScene();
   }
}
