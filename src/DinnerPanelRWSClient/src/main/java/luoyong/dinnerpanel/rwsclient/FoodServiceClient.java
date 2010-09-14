package luoyong.dinnerpanel.rwsclient;

import luoyong.dinnerpanel.dao.model.Food;
import luoyong.dinnerpanel.rwscommon.util.JsonBeanUtil;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class FoodServiceClient {

   public void addFoodCategory(Food f) {
      
      JsonBeanUtil.beanToJsonObject(f);
      // TODO Communicate with server.
   }
}
