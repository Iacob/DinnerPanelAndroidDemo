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
public class SceneSalePlaceSelect implements Scene {

   private MIDlet midlet = null;
   private Display currentDisplay = null;

   private List salePlaceUIList = null;

   public SceneSalePlaceSelect(MIDlet midlet) {
      this.midlet = midlet;
      currentDisplay = Display.getDisplay(midlet);

      salePlaceUIList = new List("餐桌列表", List.IMPLICIT);

      // TODO Remove next paragraph and add actual sale places.
      salePlaceUIList.append("测试餐桌1", null);
      salePlaceUIList.append("测试餐桌2", null);

      Command selectCommand = new Command("选择", "选择餐桌", Command.OK, 0);

      salePlaceUIList.setSelectCommand(selectCommand);
      salePlaceUIList.addCommand(selectCommand);

      salePlaceUIList.setCommandListener(new CommandListener() {
         public void commandAction(Command c, Displayable d) {
            System.out.println(
                    salePlaceUIList.getSelectedIndex()
                     + "th sale place selected.");

            SceneSwitcher.switchToSceneSceneBillListFromSalePlace();
         }
      });
   }

   public void setMIDlet(MIDlet midlet) {
      this.midlet = midlet;
      currentDisplay = Display.getDisplay(midlet);
   }

   public void show() {
      currentDisplay.setCurrent(salePlaceUIList);
   }
}
