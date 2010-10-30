package luoyong.dinnerpanel.android;

import android.widget.ViewFlipper;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class DinnerPanelApplicationContext {

   private MainActivity activityContext = null;

   private ViewFlipper mainView = null;
   private BackgroundScene backgroundScene = null;

   public DinnerPanelApplicationContext(MainActivity activityContext) {
      this.activityContext = activityContext;

      backgroundScene = new BackgroundScene(this);
   }

   public MainActivity getActivityContext() {
      return activityContext;
   }

   public android.content.res.Resources getApplicationResources() {
      return this.getActivityContext().getResources();
   }

   public ViewFlipper getMainView() {
      return mainView;
   }

   // TODO: Remove this method for main view must be init in this class.
   public void setMainView(ViewFlipper mainView) {
      this.mainView = mainView;
   }

   public BackgroundScene getBackgroundScene() {
      return backgroundScene;
   }
}
