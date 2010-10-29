package luoyong.dinnerpanel.android.util;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import luoyong.dinnerpanel.android.DinnerPanelApplicationContext;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class AnimationUtil {

   public static Animation createToNorthWestOutAnimation() {
      TranslateAnimation animation =  new TranslateAnimation(
              Animation.RELATIVE_TO_PARENT, 0,
              Animation.RELATIVE_TO_PARENT, -1,
              Animation.RELATIVE_TO_PARENT, 0,
              Animation.RELATIVE_TO_PARENT, -1);
      animation.setDuration(500);
      return animation;
   }

   public static Animation createFromSouthEastInAnimation() {
      TranslateAnimation animation =  new TranslateAnimation(
              Animation.RELATIVE_TO_PARENT, 1,
              Animation.RELATIVE_TO_PARENT, 0,
              Animation.RELATIVE_TO_PARENT, 1,
              Animation.RELATIVE_TO_PARENT, 0);
      animation.setDuration(500);
      return animation;
   }

   public static Animation createToNorthOutAnimation() {
      TranslateAnimation animation =  new TranslateAnimation(
              Animation.RELATIVE_TO_PARENT, 0,
              Animation.RELATIVE_TO_PARENT, 0,
              Animation.RELATIVE_TO_PARENT, 0,
              Animation.RELATIVE_TO_PARENT, -1);
      animation.setDuration(500);
      return animation;
   }

   public static Animation createFromSouthInAnimation() {
      TranslateAnimation animation =  new TranslateAnimation(
              Animation.RELATIVE_TO_PARENT, 0,
              Animation.RELATIVE_TO_PARENT, 0,
              Animation.RELATIVE_TO_PARENT, 1,
              Animation.RELATIVE_TO_PARENT, 0);
      animation.setDuration(500);
      return animation;
   }

   public static Animation createFromNorthInAnimation() {
      TranslateAnimation animation =  new TranslateAnimation(
              Animation.RELATIVE_TO_PARENT, 0,
              Animation.RELATIVE_TO_PARENT, 0,
              Animation.RELATIVE_TO_PARENT, -1,
              Animation.RELATIVE_TO_PARENT, 0);
      animation.setDuration(500);
      return animation;
   }

   public static Animation createToSouthOutAnimation() {
      TranslateAnimation animation =  new TranslateAnimation(
              Animation.RELATIVE_TO_PARENT, 0,
              Animation.RELATIVE_TO_PARENT, 0,
              Animation.RELATIVE_TO_PARENT, 0,
              Animation.RELATIVE_TO_PARENT, 1);
      animation.setDuration(500);
      return animation;
   }

   public static Animation createFadeInAnimation(DinnerPanelApplicationContext context) {
      Animation animation = android.view.animation.AnimationUtils.loadAnimation(
              context.getActivityContext().getApplicationContext(),
              android.R.anim.fade_in);
      animation.setDuration(1000);
      return animation;
   }

   public static Animation createFadeOutAnimation(DinnerPanelApplicationContext context) {
      Animation animation = android.view.animation.AnimationUtils.loadAnimation(
              context.getActivityContext().getApplicationContext(),
              android.R.anim.fade_out);
      animation.setDuration(1000);
      return animation;
   }
}
