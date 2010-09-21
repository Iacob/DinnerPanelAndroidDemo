package luoyong.dinnerpanel.ui.component;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class IconButton extends JButton {

   public IconButton(String text, String iconClassPath) {
      super(text);

      if ((iconClassPath != null) && (iconClassPath.length() > 0)) {

         URL iconURL = this.getClass().getResource(iconClassPath);
         ImageIcon icon = new ImageIcon(iconURL);

         this.setIcon(icon);
      }
   }

   public IconButton(String text) {
      super(text);
   }

   public IconButton() {
   }
}
