package luoyong.dinnerpanel.android.scene;

import luoyong.dinnerpanel.android.DinnerPanelApplicationContext;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class SceneFoodList {

   public static int SCENE_INDEX = 3;

   private DinnerPanelApplicationContext dpAppContext = null;

   public SceneFoodList(DinnerPanelApplicationContext dpAppContext) {
      this.dpAppContext = dpAppContext;
   }

   public void show() {
      // Set this scene as current scene.
      DinnerPanelApplicationContext.CURRENT_SCENE_INDEX = SCENE_INDEX;
      // Show current scene.
      this.dpAppContext.showCurrentScene();
   }
}
