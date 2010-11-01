package luoyong.dinnerpanel.web.api;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import luoyong.dinnerpanel.dao.model.Food;
import luoyong.dinnerpanel.dao.model.FoodCategory;
import luoyong.dinnerpanel.service.FoodManagement;
import luoyong.dinnerpanel.rwscommon.util.JsonBeanUtil;
import luoyong.dinnerpanel.rwscommon.util.RWSUtil;
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

   @Path("customer/get-food-category/{food-category-id}")
   @Produces("application/json")
   @GET
   public String getFoodCategory(
           @PathParam("food-category-id") String foodCategoryIdString) {

      JSONObject result = new JSONObject();

      JSONArray resultArray = new JSONArray();

      if ((foodCategoryIdString == null)
              || (foodCategoryIdString.trim().length() < 1)) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "餐品分类ID为空");
         return result.toString();
      }

      Long foodCategoryId = null;
      try {
         foodCategoryId = Long.valueOf(foodCategoryIdString);
      }catch(Throwable t) {}

      if (foodCategoryId == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "餐品分类ID不是合法整数");
         return result.toString();
      }

      FoodCategory foodCategory
              = foodManagement.getFoodCategory(foodCategoryId);

      if (foodCategory == null) {
         // Returne empty result.
         RWSUtil.setJsonObjectResult(result, 0, resultArray);
         return result.toString();
      }else {
         JSONObject jsonObject = JsonBeanUtil.beanToJsonObject(foodCategory);
         if (jsonObject == null) {
            // Return empty result.
            RWSUtil.setJsonObjectResult(result, 0, resultArray);
            return result.toString();
         }else {
            // Return the selected food category in JSON form.
            resultArray.put(jsonObject);
            RWSUtil.setJsonObjectResult(result, 0, resultArray);
            return result.toString();
         }
      }
   }

   @Path("customer/get-all-food-categories")
   @Produces("application/json")
   @GET
   public String getAllFoodCategories() {

      JSONObject result = new JSONObject();
      
      JSONArray resultArray = new JSONArray();
      
      List<FoodCategory> foodCategoryList
              = foodManagement.getAllFoodCategories();

      if (foodCategoryList == null) {
         RWSUtil.setJsonObjectResult(result, 0, resultArray);
         return result.toString();
      }

      for (FoodCategory foodCategory : foodCategoryList) {
         if (foodCategory != null) {
            resultArray.put(JsonBeanUtil.beanToJsonObject(foodCategory));
         }
      }

      RWSUtil.setJsonObjectResult(result, 0, resultArray);
      return result.toString();
   }

   @Path("operator/search-food-by-code/{code}")
   @Produces("application/json")
   @GET
   public String searchFoodByCode(@PathParam("code") String code) {

      JSONObject result = new JSONObject();
      
      JSONArray resultArray = new JSONArray();

      if ((code == null) || (code.trim().length() < 1)) {
         RWSUtil.setJsonObjectErrorMessage(
                 result, 1, "搜索中使用的餐品代号不能为空");
         return result.toString();
      }

      List<Food> foodList = foodManagement.searchFoodByCode(code);

      if (foodList == null) {
         RWSUtil.setJsonObjectResult(result, 0, resultArray);
         return result.toString();
      }

      for (Food food : foodList) {
         if ((food != null) && (food.getId() != null)) {

            JSONObject jsonObjectFood = foodInformationToJsonObject(food);

            if (jsonObjectFood != null) {
               resultArray.put(jsonObjectFood);
            }
         }
      }

      RWSUtil.setJsonObjectResult(result, 0, resultArray);
      return result.toString();
   }

   @Path("customer/search-available-food-by-code/{code}")
   @Produces("application/json")
   @GET
   public String searchAvailableFoodByCode(@PathParam("code") String code) {

      JSONObject result = new JSONObject();

      JSONArray resultArray = new JSONArray();

      if ((code == null) || (code.trim().length() < 1)) {
         RWSUtil.setJsonObjectErrorMessage(
                 result, 1, "搜索中使用的餐品代号不能为空");
         return result.toString();
      }

      List<Food> foodList = foodManagement.searchAvailableFoodByCode(code);

      if (foodList == null) {
         RWSUtil.setJsonObjectResult(result, 0, resultArray);
         return result.toString();
      }

      for (Food food : foodList) {
         if ((food != null) && (food.getId() != null)) {

            JSONObject jsonObjectFood = foodInformationToJsonObject(food);

            if (jsonObjectFood != null) {
               resultArray.put(jsonObjectFood);
            }
         }
      }

      RWSUtil.setJsonObjectResult(result, 0, resultArray);
      return result.toString();
   }

   @Path("operator/search-food-by-keyword/{keyword}")
   @Produces("application/json")
   @GET
   public String searchFoodByKeyword(@PathParam("keyword") String keyword) {

      JSONObject result = new JSONObject();

      JSONArray resultArray = new JSONArray();

      if ((keyword == null) || (keyword.trim().length() <= 0)) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "搜索中使用的关键词不能为空");
         return result.toString();
      }

      // Search food by keyword.
      List<Food> foodList = foodManagement.searchFoodByKeyword(keyword);

      if (foodList == null) {
         RWSUtil.setJsonObjectResult(result, 0, resultArray);
         return result.toString();
      }

      for (Food food : foodList) {
         if ((food != null) && (food.getId() != null)) {

            JSONObject jsonObjectFood = foodInformationToJsonObject(food);

            if (jsonObjectFood != null) {
               resultArray.put(jsonObjectFood);
            }
         }
      }

      RWSUtil.setJsonObjectResult(result, 0, resultArray);
      return result.toString();
   }

   @Path("customer/search-available-food-by-keyword/{keyword}")
   @Produces("application/json")
   @GET
   public String searchAvailableFoodByKeyword(
           @PathParam("keyword") String keyword) {

      JSONObject result = new JSONObject();

      JSONArray resultArray = new JSONArray();

      if ((keyword == null) || (keyword.trim().length() <= 0)) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "搜索中使用的关键词不能为空");
         return result.toString();
      }

      // Search available food by keyword.
      List<Food> foodList
              = foodManagement.searchAvailableFoodByKeyword(keyword);

      if (foodList == null) {
         RWSUtil.setJsonObjectResult(result, 0, resultArray);
         return result.toString();
      }

      for (Food food : foodList) {
         if ((food != null) && (food.getId() != null)) {

            JSONObject jsonObjectFood = foodInformationToJsonObject(food);

            if (jsonObjectFood != null) {
               resultArray.put(jsonObjectFood);
            }
         }
      }

      RWSUtil.setJsonObjectResult(result, 0, resultArray);
      return result.toString();
   }

   @Path("operator/search-food-by-name/{food-name}")
   @Produces("application/json")
   @GET
   public String searchFoodByName(@PathParam("food-name") String foodName) {

      JSONObject result = new JSONObject();

      JSONArray resultArray = new JSONArray();

      if ((foodName == null) || (foodName.trim().length() <= 0)) {
         RWSUtil.setJsonObjectErrorMessage(
                 result, 1, "搜索中使用的餐品名称不能为空");
         return result.toString();
      }

      List<Food> foodList = foodManagement.searchFoodByName(foodName);

      if (foodList == null) {
         RWSUtil.setJsonObjectResult(result, 0, resultArray);
         return result.toString();
      }

      for (Food food : foodList) {
         
         if ((food != null) && (food.getId() != null)) {

            JSONObject jsonObjectFood = foodInformationToJsonObject(food);
            
            if (jsonObjectFood != null) {
               resultArray.put(jsonObjectFood);
            }
         }
      }

      RWSUtil.setJsonObjectResult(result, 0, resultArray);
      return result.toString();
   }

   @Path("customer/search-available-food-by-name/{food-name}")
   @Produces("application/json")
   @GET
   public String searchAvailableFoodByName(
           @PathParam("food-name") String foodName) {

      JSONObject result = new JSONObject();

      JSONArray resultArray = new JSONArray();

      if ((foodName == null) || (foodName.trim().length() <= 0)) {
         RWSUtil.setJsonObjectErrorMessage(
                 result, 1, "搜索中使用的餐品名称不能为空");
         return result.toString();
      }

      List<Food> foodList = foodManagement.searchAvailableFoodByName(foodName);

      if (foodList == null) {
         RWSUtil.setJsonObjectResult(result, 0, resultArray);
         return result.toString();
      }

      for (Food food : foodList) {

         if ((food != null) && (food.getId() != null)) {

            JSONObject jsonObjectFood = foodInformationToJsonObject(food);
            
            if (jsonObjectFood != null) {
               resultArray.put(jsonObjectFood);
            }
         }
      }

      RWSUtil.setJsonObjectResult(result, 0, resultArray);
      return result.toString();
   }

   @Path("manager/add-food-category/{food-category-name}")
   @Produces("application/json")
   @GET
   public String addFoodCategory(
           @PathParam("food-category-name") String foodCategoryName) {

      JSONObject result = new JSONObject();

      if ((foodCategoryName == null)
              || (foodCategoryName.trim().length() < 1)) {

         RWSUtil.setJsonObjectErrorMessage(result, 1, "餐品分类名称不能为空");
         return result.toString();
      }

      FoodCategory foodCategory = new FoodCategory();
      foodCategory.setName(foodCategoryName);

      foodManagement.addFoodCategory(foodCategory);

      RWSUtil.setJsonObjectReturnCode(result, 0);
      return result.toString();
   }

   @Path("manager/update-food-category/{food-category-id}/{food-category-name}")
   @Produces("application/json")
   @GET
   public String updateFoodCategory(
           @PathParam("food-category-id") String foodCategoryIdString,
           @PathParam("food-category-name")String foodCategoryName) {

      JSONObject result = new JSONObject();

      if ((foodCategoryIdString == null)
              || (foodCategoryName == null)
              || (foodCategoryIdString.trim().length() < 1)
              || (foodCategoryName.trim().length() < 1)) {

         RWSUtil.setJsonObjectErrorMessage(
                 result, 1, "餐品分类ID和餐品分类名称不能为空");
         return result.toString();
      }

      Long foodCategoryId = null;
      try {
         foodCategoryId = Long.valueOf(foodCategoryIdString);
      }catch(Throwable t) {}
      
      if (foodCategoryId == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "餐品分类ID不是合法的整数");
         return result.toString();
      }

      FoodCategory foodCategory
              = foodManagement.getFoodCategory(foodCategoryId);
      if (foodCategory == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "所要更新的餐品分类不存在");
         return result.toString();
      }
      
      foodCategory.setName(foodCategoryName);

      foodManagement.updateFoodCategory(foodCategory);

      RWSUtil.setJsonObjectReturnCode(result, 0);
      return result.toString();
   }

   @Path("manager/remove-food-category/{food-category-id}")
   @Produces("application/json")
   @GET
   public String removeFoodCategory(
           @PathParam("food-category-id") String foodCategoryIdString) {

      JSONObject result = new JSONObject();

      if ((foodCategoryIdString == null)
              || (foodCategoryIdString.trim().length() < 1)) {

         RWSUtil.setJsonObjectErrorMessage(result, 1, "餐品分类ID不能为空");
         return result.toString();
      }

      Long foodCategoryId = null;
      try {
         foodCategoryId = Long.valueOf(foodCategoryIdString);
      }catch(Throwable t) {}
      
      if (foodCategoryId == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "餐品分类ID不是合法的整数");
         return result.toString();
      }

      foodManagement.removeFoodCategory(foodCategoryId);

      RWSUtil.setJsonObjectReturnCode(result, 0);
      return result.toString();
   }

   @Path("manager/add-food-information")
   @Produces("application/json")
   @POST
   public String addFoodInformation(String foodInfoString) {

      JSONObject result = new JSONObject();

      if ((foodInfoString == null) || (foodInfoString.trim().length() < 1)) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "上传的餐品信息不能为空");
         return result.toString();
      }

      JSONObject foodInfoJsonObject = null;
      try {
         foodInfoJsonObject = new JSONObject(foodInfoString);
      }catch(Throwable t) {
         t.printStackTrace(System.err);
      }

      if (foodInfoJsonObject == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "上传的餐品信息格式不正确");
         return result.toString();
      }

      Food food = new Food();
      this.jsonObjectToFoodInformation(foodInfoJsonObject, food);

      foodManagement.addFoodInformation(food);

      RWSUtil.setJsonObjectReturnCode(result, 0);
      return result.toString();
   }

   @Path("operator/get-food-information/{food-id}")
   @Produces("application/json")
   @GET
   public String getFoodInformation(@PathParam("food-id") String foodIdString) {

      JSONObject result = new JSONObject();

      JSONArray resultArray = new JSONArray();

      if ((foodIdString == null) || (foodIdString.trim().length() < 1)) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "餐品ID不能为空");
         return result.toString();
      }

      Long foodId = null;
      try {
         foodId = Long.valueOf(foodIdString);
      }catch(Throwable t) {}

      if (foodId == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "餐品ID不是合法的整数");
         return result.toString();
      }

      Food food = foodManagement.getFoodInformation(foodId);

      if (food == null) {
         RWSUtil.setJsonObjectResult(result, 0, resultArray);
         return result.toString();
      }

      JSONObject foodJsonObject =  foodInformationToJsonObject(food);
      
      if (foodJsonObject == null) {
         RWSUtil.setJsonObjectResult(result, 0, resultArray);
         return result.toString();
      }else {

         // Return food information in json form.
         resultArray.put(foodJsonObject);
         RWSUtil.setJsonObjectResult(result, 0, resultArray);
         return result.toString();
      }
   }

   @Path("customer/get-available-food-information/{food-id}")
   @Produces("application/json")
   @GET
   public String getAvailableFoodInformation(
           @PathParam("food-id") String foodIdString) {

      JSONObject result = new JSONObject();

      JSONArray resultArray = new JSONArray();

      if ((foodIdString == null) || (foodIdString.trim().length() < 1)) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "餐品ID不能为空");
         return result.toString();
      }

      Long foodId = null;
      try {
         foodId = Long.valueOf(foodIdString);
      }catch(Throwable t) {}

      if (foodId == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "餐品ID不是合法的整数");
         return result.toString();
      }

      Food food = foodManagement.getAvailableFoodInformation(foodId);

      if (food == null) {
         RWSUtil.setJsonObjectResult(result, 0, resultArray);
         return result.toString();
      }

      JSONObject foodJsonObject =  foodInformationToJsonObject(food);

      if (foodJsonObject == null) {
         RWSUtil.setJsonObjectResult(result, 0, resultArray);
         return result.toString();
      }else {

         // Return food information in json form.
         resultArray.put(foodJsonObject);
         RWSUtil.setJsonObjectResult(result, 0, resultArray);
         return result.toString();
      }
   }

   @Path("operator/get-food-list-from-food-category/{food-category-id}")
   @Produces("application/json")
   @GET
   public String getFoodListFromFoodCategory(
           @PathParam("food-category-id") String foodCategoryIdString) {

      JSONObject result = new JSONObject();

      JSONArray resultArray = new JSONArray();

      if ((foodCategoryIdString == null)
              || (foodCategoryIdString.trim().length() < 1)) {

         RWSUtil.setJsonObjectErrorMessage(result, 1, "餐品分类ID不能为空");
         return result.toString();
      }

      Long foodCategoryId = null;
      try {
         foodCategoryId = Long.valueOf(foodCategoryIdString);
      }catch(Throwable t) {}

      if (foodCategoryId == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "餐品分类ID不是合法的整数");
         return result.toString();
      }

      FoodCategory foodCategory
              = foodManagement.getFoodCategory(foodCategoryId);

      if (foodCategory == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "所指定的餐品分类不存在");
         return result.toString();
      }

      List<Food> foodList
              = foodManagement.getFoodListFromFoodCategory(foodCategory);

      if (foodList == null) {
         RWSUtil.setJsonObjectResult(result, 0, resultArray);
         return result.toString();
      }

      JSONObject foodJsonObject = null;
      for (Food food : foodList) {
         if (food != null) {
            foodJsonObject = foodInformationToJsonObject(food);
            if (foodJsonObject != null) {
               resultArray.put(foodJsonObject);
            }
         }
      }

      RWSUtil.setJsonObjectResult(result, 0, resultArray);
      return result.toString();
   }

   @Path(
      "customer/get-available-food-list-from-food-category/{food-category-id}")
   @Produces("application/json")
   @GET
   public String getAvailableFoodListFromFoodCategory(
           @PathParam("food-category-id") String foodCategoryIdString) {

      JSONObject result = new JSONObject();

      JSONArray resultArray = new JSONArray();

      if ((foodCategoryIdString == null)
              || (foodCategoryIdString.trim().length() < 1)) {

         RWSUtil.setJsonObjectErrorMessage(result, 1, "餐品分类ID不能为空");
         return result.toString();
      }

      Long foodCategoryId = null;
      try {
         foodCategoryId = Long.valueOf(foodCategoryIdString);
      }catch(Throwable t) {}

      if (foodCategoryId == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "餐品分类ID不是合法的整数");
         return result.toString();
      }

      FoodCategory foodCategory
              = foodManagement.getFoodCategory(foodCategoryId);

      if (foodCategory == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "所指定的餐品分类不存在");
         return result.toString();
      }

      List<Food> foodList = foodManagement.getAvailableFoodListFromFoodCategory(
              foodCategory);

      if (foodList == null) {
         RWSUtil.setJsonObjectResult(result, 0, resultArray);
         return result.toString();
      }

      JSONObject foodJsonObject = null;
      for (Food food : foodList) {
         if (food != null) {
            
            foodJsonObject = foodInformationToJsonObject(food);
            
            if (foodJsonObject != null) {
               resultArray.put(foodJsonObject);
            }
         }
      }

      RWSUtil.setJsonObjectResult(result, 0, resultArray);
      return result.toString();
   }

   @Path("manager/remove-food-information/{food-id}")
   @Produces("application/json")
   @GET
   public String removeFoodInformation(
           @PathParam("food-id") String foodIdString) {

      JSONObject result = new JSONObject();

      if ((foodIdString ==  null) || (foodIdString.trim().length() < 1)) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "餐品信息ID不能为空");
         return result.toString();
      }

      Long foodId = null;
      try {
         foodId = Long.valueOf(foodIdString);
      }catch(Throwable t) {}

      if (foodId == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "餐品信息ID不是合法的整数");
         return result.toString();
      }

      foodManagement.removeFoodInformation(foodId);
      
      RWSUtil.setJsonObjectReturnCode(result, 0);
      return result.toString();
   }

   @Path("manager/update-food-information")
   @Consumes("application/json")
   @Produces("application/json")
   @POST
   public String updateFood(String foodInfoString) {

      JSONObject result = new JSONObject();

      if ((foodInfoString == null) || (foodInfoString.trim().length() < 1)) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "上传的餐品信息不能为空");
         return result.toString();
      }

      JSONObject foodInfoJsonObject = null;
      try {
         foodInfoJsonObject = new JSONObject(foodInfoString);
      }catch(Throwable t) {
         t.printStackTrace(System.err);
      }

      if (foodInfoJsonObject == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "上传的餐品信息格式不正确");
         return result.toString();
      }

      Food food = new Food();
      this.jsonObjectToFoodInformation(foodInfoJsonObject, food);

      Food foodInSystem = foodManagement.getFoodInformation(food);
      if (foodInSystem == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "所要更新的餐品信息不存在");
         return result.toString();
      }
      
      foodManagement.updateFood(food);

      RWSUtil.setJsonObjectReturnCode(result, 0);
      return result.toString();
   }

   private JSONObject foodInformationToJsonObject(Food f) {
      
      if (f == null) {
         return null;
      }

      // Convert food information to json object.
      JSONObject foodJsonObject = JsonBeanUtil.beanToJsonObject(f);

      if ((foodJsonObject != null)
              && (f.getCategory() != null)
              && (f.getCategory().getId() != null)) {

         // Convert food category information to json object.
         JSONObject foodCategoryJsonObject
                 = JsonBeanUtil.beanToJsonObject(f.getCategory());

         // Put food category information into food information json object.
         try {
            foodJsonObject.put("category", foodCategoryJsonObject);
         } catch (JSONException ex) {
            ex.printStackTrace(System.err);
         }

         // Put food information tags into food information json object.
         try {
            Set<String> foodTagsSet = f.getTags();
            
            if (foodTagsSet != null) {

               JSONArray foodTagsJsonArray = new JSONArray();

               for (String foodTag : foodTagsSet) {
                  if (foodTag != null) {
                     foodTagsJsonArray.put(foodTag);
                  }
               }

               foodJsonObject.put("tags", foodTagsJsonArray);
            }
            
         } catch (JSONException ex) {
            ex.printStackTrace(System.err);
         }
      }

      return foodJsonObject;
   }

   private void jsonObjectToFoodInformation(JSONObject foodJsonObject, Food f) {

      if ((foodJsonObject == null) || (f == null)) {
         return;
      }

      // Extract information from json object to food information instance.
      JsonBeanUtil.jsonObjectToBean(foodJsonObject, f);

      // Get food category json object.
      JSONObject foodCategoryJsonObject = null;
      try {
         foodCategoryJsonObject = foodJsonObject.getJSONObject("category");
      }catch(Throwable t) {
         t.printStackTrace(System.err);
      }

      // Extract information from food category json object
      //  to food category instance.
      FoodCategory foodCategory = null;
      if (foodCategoryJsonObject != null) {
         foodCategory = new FoodCategory();
         JsonBeanUtil.jsonObjectToBean(foodCategoryJsonObject, foodCategory);
      }

      // Put food category information into food information.
      f.setCategory(foodCategory);

      // Handle food information tags.
      Set<String> foodTagsSet = new HashSet<String>();
      try {
         // Get food information tags in json array format.
         JSONArray foodTagsJsonArray = foodJsonObject.getJSONArray("tags");
         if (foodTagsJsonArray != null) {
            // Extract food information tags from json array.
            int foodTagsCount = foodTagsJsonArray.length();
            String foodTag = null;
            for (int i = 0; i < foodTagsCount; i++) {
               foodTag = null;
               try {
                  foodTag = foodTagsJsonArray.getString(i);
               } catch (Throwable t) {
                  t.printStackTrace(System.err);
               }

               if (foodTag != null) {
                  foodTagsSet.add(foodTag);
               }
            }
         }
      }catch(Throwable t) {
         t.printStackTrace(System.err);
      }
      f.setTags(foodTagsSet);
   }
}
