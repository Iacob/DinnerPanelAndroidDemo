package luoyong.dinnerpanel.android.model.enumwrapper;

import luoyong.dinnerpanel.android.model.ExistKey;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class ExistKeyEnum {

   private ExistKey key = null;

   public ExistKeyEnum(ExistKey existKey) {
      this.key = existKey;
   }

   public ExistKey getEnum() {
      return this.key;
   }

   @Override
   public String toString() {

      ExistKey existKey = this.getEnum();

      if (existKey == null) {
         return null;
      }else if (existKey.name() == null) {
         return null;
      }else if (existKey.name().equals("E")) {
         return "存在";
      }else if (existKey.name().equals("D")) {
         return "不存在";
      }else {
         return null;
      }
   }
}
