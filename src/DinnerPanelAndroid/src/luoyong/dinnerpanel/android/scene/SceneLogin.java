package luoyong.dinnerpanel.android.scene;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import luoyong.dinnerpanel.android.DinnerPanelApplicationContext;
import luoyong.dinnerpanel.android.R;
import luoyong.dinnerpanel.android.rwsclient.RWSURLHolder;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class SceneLogin {

   public static final int SCENE_INDEX = 0;
   
   private static String TEXT_SERVER_URL = null;
   private static String TEXT_SERVER_USERNAME = null;
   private static String TEXT_SERVER_PASSWORD = null;

   private DinnerPanelApplicationContext dpAppContext = null;

   private EditText editTextServerURL = null;
   private EditText editTextServerUsername = null;
   private EditText editTextServerPassword = null;

   public SceneLogin(DinnerPanelApplicationContext dpAppContext) {
      this.dpAppContext = dpAppContext;

      editTextServerURL = (EditText)dpAppContext.getActivityContext()
              .findViewById(R.id.scene_login_editext_server_url);
      editTextServerUsername = (EditText)dpAppContext.getActivityContext()
                .findViewById(R.id.scene_login_editext_server_username);
      editTextServerPassword = (EditText)dpAppContext.getActivityContext()
                .findViewById(R.id.scene_login_editext_server_password);

      Button buttonConfirm = (Button)dpAppContext.getActivityContext()
              .findViewById(R.id.scene_login_button_confirm);

      buttonConfirm.setOnClickListener(new OnClickListener() {
         public void onClick(View view) {

            TEXT_SERVER_URL = editTextServerURL.getText().toString();
            TEXT_SERVER_USERNAME = editTextServerUsername.getText().toString();
            TEXT_SERVER_PASSWORD = editTextServerPassword.getText().toString();

            // Set the base URL.
            RWSURLHolder.setBaseURL(TEXT_SERVER_URL);

            // Set server username and password.
            Authenticator.setDefault(new Authenticator() {
               @Override
               protected PasswordAuthentication getPasswordAuthentication() {
                  return new PasswordAuthentication(
                     TEXT_SERVER_USERNAME, TEXT_SERVER_PASSWORD.toCharArray());
               }
            });

            // TODO Change this after bill operation API complete.
            SceneLogin.this.dpAppContext.getSceneFoodCategoryList().show();
         }
      });

      
      
   }

   public void show() {
      // Set this scene as current scene.
      DinnerPanelApplicationContext.CURRENT_SCENE_INDEX = SCENE_INDEX;
      // Show current scene.
      this.dpAppContext.showCurrentScene();
   }
}
