package luoyong.dinnerpanel.device.javame.generic.ui;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class SceneBillListFromSalePlace implements Scene {

   private MIDlet midlet = null;
   private Display currentDisplay = null;

   private List billUIList = null;

   public SceneBillListFromSalePlace(MIDlet midlet) {
      this.midlet = midlet;
      this.currentDisplay = Display.getDisplay(this.midlet);

      billUIList = new List("关联账单", List.IMPLICIT);

      Command selectCommand = new Command("选择", "选择账单", Command.OK, 0);

      billUIList.append("20:00", null);
      billUIList.setSelectCommand(selectCommand);
      billUIList.addCommand(selectCommand);
      billUIList.setCommandListener(new CommandListener() {
         public void commandAction(Command c, Displayable d) {
            System.out.println(
                    billUIList.getSelectedIndex() + "th bill selected.");

            SceneSwitcher.switchToSceneOpenBill();
         }
      });
   }

   public void setMIDlet(MIDlet midlet) {
      this.midlet = midlet;
      this.currentDisplay = Display.getDisplay(this.midlet);
   }

   public void show() {
      currentDisplay.setCurrent(billUIList);
   }
}
