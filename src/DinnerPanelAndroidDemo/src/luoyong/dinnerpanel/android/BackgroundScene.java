package luoyong.dinnerpanel.android;

import android.os.AsyncTask;
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

   private int loopTime = 5000;
   
   private int totalLoopTime = 0;
   private boolean resetTimer = false;

   private static int waitingPeroid = 7000;

   private boolean taskStarted = false;

   public BackgroundScene(DinnerPanelApplicationContext applicationContext) {
      this.dpAppContext = applicationContext;
   }

   public void show() {

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

      if (!taskStarted) {

         // Start switch view thread.
         AsyncTask switchViewTask = new AsyncTask() {

            @Override
            protected Object doInBackground(Object... arg0) {

               for (;;) {
                  try {
                     Thread.sleep(loopTime);
                     // Reset the total loop time if the timer is reset.
                     if (resetTimer) {
                        totalLoopTime = 0;
                        resetTimer = false;
                        continue;
                     }
                     totalLoopTime += loopTime;
                     if (totalLoopTime > BackgroundScene.getWaitingPeroid()) {
                        totalLoopTime = 0;
                        // Send message to tell main view to show broadcast ad.
                        dpAppContext.getActivityContext().messageHandler
                                .sendEmptyMessage(0x151);
                     }
                     android.util.Log.v("Loop Cycle", "A cycle reached.");
                  } catch (Throwable t) {
                     android.util.Log.e("Thread Error", "Thread error.", t);
                  }
               }
            }
         };
         switchViewTask.execute("Hello thread.");
         taskStarted = true;
      }
   }

   public void resetTimer() {
      this.resetTimer = true;
   }

   public static int getWaitingPeroid() {
      return waitingPeroid;
   }

   public static void setWaitingPeroid(int waitingPeroid) {
      BackgroundScene.waitingPeroid = waitingPeroid;
   }
}
