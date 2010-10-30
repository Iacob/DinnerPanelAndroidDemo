package luoyong.dinnerpanel.android;

import android.view.View;
import android.widget.ViewFlipper;
import luoyong.dinnerpanel.android.scene.SceneCurrentBill;
import luoyong.dinnerpanel.android.scene.SceneFoodCategoryList;
import luoyong.dinnerpanel.android.scene.SceneFoodList;
import luoyong.dinnerpanel.android.scene.SceneLogin;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class DinnerPanelApplicationContext {

   private MainActivity activityContext = null;
   private ViewFlipper mainview = null;

   private SceneLogin sceneLogin = null;
   private SceneCurrentBill sceneCurrentBill = null;
   private SceneFoodCategoryList sceneFoodCategoryList = null;
   private SceneFoodList sceneFoodList = null;

   public static int CURRENT_SCENE_INDEX = 0;
   
   public DinnerPanelApplicationContext(MainActivity activity) {
      
      this.activityContext = activity;
      this.mainview
              = (ViewFlipper)this.activityContext.findViewById(R.id.mainview);

      sceneLogin = new SceneLogin(this);
      sceneCurrentBill = new SceneCurrentBill(this);
      sceneFoodCategoryList = new SceneFoodCategoryList(this);
      sceneFoodList = new SceneFoodList(this);
   }

   public MainActivity getActivityContext() {
      return activityContext;
   }

   public SceneCurrentBill getSceneCurrentBill() {
      return sceneCurrentBill;
   }

   public SceneFoodCategoryList getSceneFoodCategoryList() {
      return sceneFoodCategoryList;
   }

   public SceneFoodList getSceneFoodList() {
      return sceneFoodList;
   }

   public SceneLogin getSceneLogin() {
      return sceneLogin;
   }

   public ViewFlipper getMainView() {
      return this.mainview;
   }

   public View findViewByIdFromMainView(int id) {
      return this.mainview.findViewById(id);
   }

   public android.content.res.Resources getResources() {
      return this.activityContext.getResources();
   }

   public void showCurrentScene() {
      this.mainview.setDisplayedChild(CURRENT_SCENE_INDEX);
   }
}
