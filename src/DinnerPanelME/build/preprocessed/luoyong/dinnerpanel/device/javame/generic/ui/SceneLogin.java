package luoyong.dinnerpanel.device.javame.generic.ui;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import luoyong.dinnerpanel.device.javame.authentication.CredentialHolder;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class SceneLogin implements Scene {

   private MIDlet midlet = null;
   private Display display = null;

   private Form authForm = null;
   private TextField textFieldUsername = null;
   private TextField textFieldPassword = null;

   public SceneLogin(MIDlet midlet) {

      this.midlet = midlet;
      display = Display.getDisplay(midlet);

      authForm = new Form("请输入用户名和密码");

      textFieldUsername = new TextField(
              "用户名", CredentialHolder.getUsername(), 100, TextField.ANY);
      textFieldUsername.setInitialInputMode("UCB_BASIC_LATIN");
      textFieldPassword = new TextField("密码", "", 100, TextField.PASSWORD);
      textFieldPassword.setInitialInputMode("UCB_BASIC_LATIN");

      authForm.append(textFieldUsername);
      authForm.append(textFieldPassword);
      authForm.addCommand(new Command("登陆", "登录服务器", Command.OK, 0));
      authForm.setCommandListener(new CommandListener() {
         public void commandAction(Command c, Displayable d) {
            CredentialHolder.setCredential(
                    textFieldUsername.getString(),
                    textFieldPassword.getString());
            
            // Swtich to sale place list panel.
            SceneSwitcher.switchToSceneSalePlaceSelect();
         }
      });
   }

   public void setMIDlet(MIDlet midlet) {
      this.midlet = midlet;
      display = Display.getDisplay(midlet);
   }

   public void show() {
      display.setCurrent(authForm);
   }
}
