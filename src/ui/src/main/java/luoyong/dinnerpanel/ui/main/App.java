package luoyong.dinnerpanel.ui.main;

import luoyong.dinnerpanel.dao.EntityManagerBuilder;
import luoyong.dinnerpanel.ui.LoginFrame;
import luoyong.dinnerpanel.ui.MainFrame;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class App {

   public static void main(String[] args) {

      EntityManagerBuilder.buildEntityManager();

      LoginFrame loginFrame = new LoginFrame();
      loginFrame.setVisible(true);
//      MainFrame.showMainFrame();
   }
}
