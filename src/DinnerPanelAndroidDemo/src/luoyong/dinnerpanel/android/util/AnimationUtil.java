package luoyong.dinnerpanel.android.util;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

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
}
