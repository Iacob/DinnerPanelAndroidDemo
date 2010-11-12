package luoyong.dinnerpanel.android;

import android.os.AsyncTask;
import luoyong.dinnerpanel.android.util.AnimationUtil;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class SwtichViewAsyncTask extends AsyncTask {

   private static final int LOOP_CYCLE = 2000;
   
   private int totalLoopTime = 0;

   private int waitingPeriod = 10000;
   
   private boolean resetTimer = false;
   private boolean stopped = false;

   private DinnerPanelApplicationContext dpAppContext = null;

   public SwtichViewAsyncTask(DinnerPanelApplicationContext dpAppContext) {
      this.dpAppContext = dpAppContext;
   }

   @Override
   protected Object doInBackground(Object... arg0) {

      for (;;) {

         if (stopped) {
            android.util.Log.v("DINNER PANEL MESSAGE",
                    "SwitchViewAsyncTask stopped.");
            break;
         }
         
         try {
            Thread.sleep(LOOP_CYCLE);
            // Reset the total loop time if the timer is reset.
            if (resetTimer) {
               totalLoopTime = 0;
               resetTimer = false;
               android.util.Log.v("DINNER PANEL MESSAGE",
                    "SwitchViewAsyncTask timer reset.");
               continue;
            }
            totalLoopTime += LOOP_CYCLE;
            if (totalLoopTime >= this.waitingPeriod) {
               totalLoopTime = 0;
               // Tell main view to show broadcast view.
               this.publishProgress("Show broadcast view.");
            }
            android.util.Log.v("Loop Cycle", "A cycle reached.");
         } catch (Throwable t) {
            android.util.Log.e("Thread Error", "Thread error.", t);
         }
      }
      return "Switch view task stopped.";
   }

   @Override
   protected void onProgressUpdate(Object... values) {
      dpAppContext.getMainView().setInAnimation(
              AnimationUtil.createFadeInAnimation(dpAppContext));
      dpAppContext.getMainView().setOutAnimation(
              AnimationUtil.createFadeOutAnimation(dpAppContext));
      
      // Show the broadcast view.
      if (dpAppContext.getMainView().getDisplayedChild()
              != BackgroundScene.VIEW_INDEX) {
         
         dpAppContext.getMainView().setDisplayedChild(BackgroundScene.VIEW_INDEX);
      }
   }

   public void stop() {
      this.stopped = true;
   }

   public void resetTimer() {
      this.resetTimer = true;
   }

   public int getLoopCycle() {
      return LOOP_CYCLE;
   }
}
