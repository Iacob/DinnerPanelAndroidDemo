package luoyong.dinnerpanel.ui.main;

import luoyong.dinnerpanel.dao.EntityManagerBuilder;
import luoyong.dinnerpanel.ui.MainFrame;

/**
 *
 * @author 
 */
public class App {

   public static void main(String[] args) {

      EntityManagerBuilder.buildEntityManager();

      MainFrame.showMainFrame();
   }
}
