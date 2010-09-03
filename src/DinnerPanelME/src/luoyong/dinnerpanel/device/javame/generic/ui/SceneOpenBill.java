package luoyong.dinnerpanel.device.javame.generic.ui;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class SceneOpenBill implements Scene {

   private MIDlet midlet = null;
   private Display currentDisplay = null;

   private Form form = null;

   public SceneOpenBill(MIDlet midlet) {
      this.midlet = midlet;
      currentDisplay = Display.getDisplay(this.midlet);

      ChoiceGroup billItemUIList = new ChoiceGroup(
              "账单条目列表", ChoiceGroup.EXCLUSIVE);
      billItemUIList.append("账单条目一", null);
      billItemUIList.append("账单条目二", null);

      form = new Form("账单详细信息");
      form.append(new StringItem("账单附注", "要求一\n要求二"));
      form.append(billItemUIList);
      form.append("价格：");
   }

   public void setMIDlet(MIDlet midlet) {
      this.midlet = midlet;
      currentDisplay = Display.getDisplay(this.midlet);
   }

   public void show() {
      currentDisplay.setCurrent(form);
   }

}
