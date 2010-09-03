package luoyong.dinnerpanel.device.javame.generic.ui;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class SceneSwitcher {

   private static MIDlet midlet = null;

   private static Scene currentScene = null;
   private static SceneLogin sceneLogin = null;
   private static SceneSalePlaceSelect sceneSalePlaceSelect = null;
   private static SceneBillListFromSalePlace sceneBillListFromSalePlace = null;
   private static SceneOpenBill sceneOpenBill = null;

   public static void init(MIDlet midlet) {
      SceneSwitcher.midlet = midlet;

      sceneLogin = new SceneLogin(SceneSwitcher.midlet);
      currentScene = sceneLogin;

      sceneSalePlaceSelect = new SceneSalePlaceSelect(SceneSwitcher.midlet);

      sceneBillListFromSalePlace
              = new SceneBillListFromSalePlace(SceneSwitcher.midlet);

      sceneOpenBill = new SceneOpenBill(SceneSwitcher.midlet);
   }

   public static void switchToSceneLogin() {
      currentScene = sceneLogin;
      currentScene.show();
   }

   public static void switchToSceneSalePlaceSelect() {
      currentScene = sceneSalePlaceSelect;
      currentScene.show();
   }

   public static void switchToSceneSceneBillListFromSalePlace() {
      currentScene = sceneBillListFromSalePlace;
      currentScene.show();
   }

   public static void switchToSceneOpenBill() {
      currentScene = sceneOpenBill;
      currentScene.show();
   }
}
