package luoyong.dinnerpanel.android;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import luoyong.dinnerpanel.android.util.AnimationUtil;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class BackgroundScene {

   public static final int VIEW_INDEX = 3;

   private DinnerPanelApplicationContext dpAppContext = null;

   private static SwtichViewAsyncTask switchViewTask = null;
   

   public BackgroundScene(DinnerPanelApplicationContext applicationContext) {
      this.dpAppContext = applicationContext;

      Button adViewButtonQuit = (Button)this.dpAppContext
              .getActivityContext().findViewById(
                  R.id.advertisement_view_button_quit);

      // Set the quit button.
      adViewButtonQuit.setOnClickListener(new OnClickListener() {
         public void onClick(View arg0) {
            dpAppContext.getMainView().setInAnimation(
                    AnimationUtil.createFromNorthInAnimation());
            dpAppContext.getMainView().setOutAnimation(
                    AnimationUtil.createToSouthOutAnimation());
            dpAppContext.getMainView().setDisplayedChild(1);
         }
      });

      if (switchViewTask != null) {
         // Mark the old task stop.
         android.util.Log.v("DINNER PANEL MESSAGE",
                 "Mark the old task as stop.");
         switchViewTask.stop();
         // Waiting the old task stop.
         android.util.Log.v("DINNER PANEL MESSAGE",
                 "Waiting the old task stopped.");
         try {
            Thread.sleep(switchViewTask.getLoopCycle() * 2);
         } catch (InterruptedException ex) {
            ex.printStackTrace(System.err);
         }
      }

      // Create a new task instance.
      switchViewTask = new SwtichViewAsyncTask(this.dpAppContext);
      // Start the task.
      switchViewTask.execute("Start task.");
   }

   public void show() {
   }

   public void resetTimer() {
      switchViewTask.resetTimer();
   }
}
