package luoyong.dinnerpanel.device.javame.generic;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import luoyong.dinnerpanel.device.javame.authentication.CredentialHolder;
import luoyong.dinnerpanel.device.javame.baseurl.BaseURLHolder;
import luoyong.dinnerpanel.device.javame.generic.model.SalePlace;
import luoyong.dinnerpanel.device.javame.generic.service.RemoteConnectionException;
import luoyong.dinnerpanel.device.javame.generic.service.RemoteInformationFormatException;
import luoyong.dinnerpanel.device.javame.generic.service.SalePlaceService;
import luoyong.dinnerpanel.device.javame.generic.ui.Scene;
import luoyong.dinnerpanel.device.javame.generic.ui.SceneLogin;
import luoyong.dinnerpanel.device.javame.generic.ui.SceneSwitcher;

/**
 * 
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class MainMidlet extends MIDlet {

   private boolean initialized = false;

   public void startApp() {

      if (!initialized) {
         SceneSwitcher.init(this);
         initialized = true;
      }

      SceneSwitcher.switchToSceneLogin();
   }

   public void pauseApp() {
   }

   public void destroyApp(boolean unconditional) {
   }
}
