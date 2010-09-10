package luoyong.dinnerpanel.web.api;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import luoyong.dinnerpanel.dao.model.Food;
import luoyong.dinnerpanel.dao.model.FoodCategory;
import luoyong.dinnerpanel.service.FoodManagement;
import luoyong.dinnerpanel.web.util.JsonBeanUtil;
import org.json.JSONArray;
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

   @Path("customer/get-food-category/{food-category-id}")
   @Produces("application/json")
   @GET
   public String getFoodCategory(
           @PathParam("food-category-id") String foodCategoryIdString) {

      JSONArray result = new JSONArray();

      if ((foodCategoryIdString == null)
              || (foodCategoryIdString.trim().length() < 1)) {
         
         return result.toString();
      }

      Long foodCategoryId = null;
      try {
         foodCategoryId = Long.valueOf(foodCategoryIdString);
      }catch(Throwable t) {}

      if (foodCategoryId == null) {
         return result.toString();
      }

      FoodCategory foodCategory
              = foodManagement.getFoodCategory(foodCategoryId);

      if (foodCategory == null) {
         return result.toString();
      }else {
         JSONObject jsonObject = JsonBeanUtil.beanToJsonObject(foodCategory);
         if (jsonObject == null) {
            return result.toString();
         }else {
            // Return the selected food category in JSON form.
            result.put(jsonObject);
            return result.toString();
         }
      }
   }

   @Path("customer/get-all-food-categories")
   @Produces("application/json")
   @GET
   public String getAllFoodCategories() {
      
      JSONArray result = new JSONArray();
      
      List<FoodCategory> foodCategoryList
              = foodManagement.getAllFoodCategories();

      if (foodCategoryList == null) {
         return result.toString();
      }

      for (FoodCategory foodCategory : foodCategoryList) {
         if (foodCategory != null) {
            result.put(JsonBeanUtil.beanToJsonObject(foodCategory));
         }
      }

      return result.toString();
   }

   @Path("customer/search-food-by-code/{code}")
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

            JSONObject jsonObjectFood = JsonBeanUtil.beanToJsonObject(food);
            resultArray.put(jsonObjectFood);
         }
      }

      return resultArray.toString();
   }

   @Path("customer/search-food-by-keyword/{keyword}")
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

            JSONObject jsonObjectFood = JsonBeanUtil.beanToJsonObject(food);
            resultArray.put(jsonObjectFood);
         }
      }

      return resultArray.toString();
   }

   @Path("customer/search-food-by-name/{food-name}")
   @Produces("application/json")
   @GET
   public String searchFoodByName(@PathParam("food-name") String foodName) {

      JSONArray resultArray = new JSONArray();

      if ((foodName == null) || (foodName.trim().length() <= 0)) {
         return resultArray.toString();
      }

      List<Food> foodList = foodManagement.searchFoodByName(foodName);

      if (foodList == null) {
         return resultArray.toString();
      }

      for (Food food : foodList) {
         
         if ((food != null) && (food.getId() != null)) {

            JSONObject jsonObjectFood = JsonBeanUtil.beanToJsonObject(food);
            if (jsonObjectFood != null) {
               resultArray.put(jsonObjectFood);
            }
         }
      }

      return resultArray.toString();
   }

   @Path("manager/add-food-category/{food-category-name}")
   @Produces("application/json")
   @GET
   public void addFoodCategory(
           @PathParam("food-category-name") String foodCategoryName) {

      if ((foodCategoryName == null)
              || (foodCategoryName.trim().length() < 1)) {
         
         return;
      }

      FoodCategory foodCategory = new FoodCategory();
      foodCategory.setName(foodCategoryName);

      foodManagement.addFoodCategory(foodCategory);
   }

   @Path("manager/update-food-category/{food-category-id}/{food-category-name}")
   @Produces("application/json")
   @GET
   public void updateFoodCategory(
           @PathParam("food-category-id") String foodCategoryIdString,
           @PathParam("food-category-name")String foodCategoryName) {

      if ((foodCategoryIdString == null)
              || (foodCategoryName == null)
              || (foodCategoryIdString.trim().length() < 1)
              || (foodCategoryName.trim().length() < 1)) {

         return;
      }

      Long foodCategoryId = null;
      try {
         foodCategoryId = Long.valueOf(foodCategoryIdString);
      }catch(Throwable t) {}
      
      if (foodCategoryId == null) {
         return;
      }

      FoodCategory foodCategory = new FoodCategory();
      if (foodCategory == null) {
         return;
      }

      foodCategory.setId(foodCategoryId);
      foodCategory.setName(foodCategoryName);

      foodManagement.updateFoodCategory(foodCategory);
   }

   @Path("manager/remove-food-category/{food-category-id}")
   @Produces("application/json")
   @GET
   public void removeFoodCategory(
           @PathParam("food-category-id") String foodCategoryIdString) {

      if ((foodCategoryIdString == null)
              || (foodCategoryIdString.trim().length() < 1)) {

         return;
      }

      Long foodCategoryId = null;
      try {
         foodCategoryId = Long.valueOf(foodCategoryIdString);
      }catch(Throwable t) {}
      
      if (foodCategoryId == null) {
         return;
      }

      foodManagement.removeFoodCategory(foodCategoryId);
   }

   @Path("manager/add-food-information")
   @Produces("application/json")
   @POST
   public void addFoodInformation(String foodInfoString) {

      if ((foodInfoString == null) || (foodInfoString.trim().length() < 1)) {
         return;
      }

      JSONObject foodInfoJsonObject = null;
      try {
         foodInfoJsonObject = new JSONObject(foodInfoString);
      }catch(Throwable t) {
         t.printStackTrace(System.err);
      }

      if (foodInfoJsonObject == null) {
         return;
      }

      Food food = new Food();
      JsonBeanUtil.jsonObjectToBean(foodInfoJsonObject, food);

      foodManagement.addFoodInformation(food);
   }

   @Path("customer/get-food-information/{food-id}")
   @Produces("application/json")
   @GET
   public String getFoodInformation(@PathParam("food-id") String foodIdString) {

      JSONArray result = new JSONArray();

      if ((foodIdString == null) || (foodIdString.trim().length() < 1)) {
         return result.toString();
      }

      Long foodId = null;
      try {
         foodId = Long.valueOf(foodIdString);
      }catch(Throwable t) {}

      if (foodId == null) {
         return result.toString();
      }

      Food food = foodManagement.getFoodInformation(foodId);

      if (food == null) {
         return result.toString();
      }

      JSONObject foodJsonObject =  JsonBeanUtil.beanToJsonObject(food);
      if (foodJsonObject == null) {
         return result.toString();
      }else {
         // Return food information in json form.
         result.put(foodJsonObject);
         return result.toString();
      }
   }

   @Path("customer/get-food-list-from-food-category/{food-category-id}")
   @Produces("application/json")
   @GET
   public String getFoodListFromFoodCategory(
           @PathParam("food-category-id") String foodCategoryIdString) {

      JSONArray result = new JSONArray();

      if ((foodCategoryIdString == null)
              || (foodCategoryIdString.trim().length() < 1)) {
         
         return result.toString();
      }

      Long foodCategoryId = null;
      try {
         foodCategoryId = Long.valueOf(foodCategoryIdString);
      }catch(Throwable t) {}

      if (foodCategoryId == null) {
         return result.toString();
      }

      FoodCategory foodCategory
              = foodManagement.getFoodCategory(foodCategoryId);

      if (foodCategory == null) {
         return result.toString();
      }

      List<Food> foodList
              = foodManagement.getFoodListFromFoodCategory(foodCategory);

      if (foodList == null) {
         return result.toString();
      }

      JSONObject foodJsonObject = null;
      for (Food food : foodList) {
         if (food != null) {
            foodJsonObject = JsonBeanUtil.beanToJsonObject(food);
            if (foodJsonObject != null) {
               result.put(foodJsonObject);
            }
         }
      }

      return result.toString();
   }

   @Path("manager/remove-food-information/{food-id}")
   @Produces("application/json")
   @GET
   public void removeFoodInformation(
           @PathParam("food-id") String foodIdString) {

      if ((foodIdString ==  null) || (foodIdString.trim().length() < 1)) {
         return;
      }

      Long foodId = null;
      try {
         foodId = Long.valueOf(foodIdString);
      }catch(Throwable t) {}

      if (foodId == null) {
         return;
      }

      foodManagement.removeFoodInformation(foodId);
   }

   @Path("manager/update-food-information")
   @Consumes("application/json")
   @Produces("application/json")
   @POST
   public void updateFood(String foodInfoString) {

      if ((foodInfoString == null) || (foodInfoString.trim().length() < 1)) {
         return;
      }

      JSONObject foodInfoJsonObject = null;
      try {
         foodInfoJsonObject = new JSONObject(foodInfoString);
      }catch(Throwable t) {
         t.printStackTrace(System.err);
      }

      if (foodInfoJsonObject == null) {
         return;
      }

      Food food = new Food();
      JsonBeanUtil.jsonObjectToBean(foodInfoJsonObject, food);

      Food foodInSystem = foodManagement.getFoodInformation(food);
      if (foodInSystem == null) {
         return;
      }
      
      foodManagement.updateFood(food);
   }
}
