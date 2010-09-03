package luoyong.dinnerpanel.web.api;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import luoyong.dinnerpanel.dao.model.Food;
import luoyong.dinnerpanel.service.FoodManagement;
import luoyong.dinnerpanel.web.util.JsonBeanUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
@Path("food")
public class RWSFood {

   private FoodManagement foodManagement = null;

   public RWSFood() {

      foodManagement = new FoodManagement();
   }

   @Path("search-food-by-code/{code}")
   @Produces("application/json")
   @GET
   public String searchFoodByCode(@PathParam("code") String code) {
      
      JSONArray resultArray = new JSONArray();

      if ((code == null) || (code.trim().length() <= 0)) {
         return resultArray.toString();
      }

      List<Food> foodList = foodManagement.searchFoodByCode(code);

      if (foodList == null) {
         return resultArray.toString();
      }

      for (Food food : foodList) {
         if ((food != null) && (food.getId() != null)) {

            JSONObject jsonObjectFood = JsonBeanUtil.beanToJson(food);
            resultArray.put(jsonObjectFood);
         }
      }

      return resultArray.toString();
   }

   @Path("search-food-by-keyword/{keyword}")
   @Produces("application/json")
   @GET
   public String searchFoodByKeyword(@PathParam("keyword") String keyword) {

      JSONArray resultArray = new JSONArray();

      if ((keyword == null) || (keyword.trim().length() <= 0)) {
         return resultArray.toString();
      }

      // Search food by keyword.
      List<Food> foodList = foodManagement.searchFoodByKeyword(keyword);

      if (foodList == null) {
         return resultArray.toString();
      }

      for (Food food : foodList) {
         if ((food != null) && (food.getId() != null)) {

            JSONObject jsonObjectFood = JsonBeanUtil.beanToJson(food);
            resultArray.put(jsonObjectFood);
         }
      }

      return resultArray.toString();
   }
}
