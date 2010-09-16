package luoyong.dinnerpanel.ui.component;

import java.awt.Component;
import javax.swing.JOptionPane;
import luoyong.dinnerpanel.rwscommon.info.RWSException;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class RWSExceptionDialog {

   public static void showRWSExceptionDialog(
           Component parentComponent, RWSException ex, String title) {

      String message = null;

      if (ex == null) {
         message = "与服务器连接时发生错误";
      }else {
         message = ex.getMessage();
      }

      JOptionPane.showMessageDialog(
              parentComponent, message, title, JOptionPane.ERROR_MESSAGE);
   }

   public static void showRWSExceptionDialog(
           Component parentComponent, RWSException ex) {

      showRWSExceptionDialog(parentComponent, ex, "与服务器连接时发生错误");
   }
}
