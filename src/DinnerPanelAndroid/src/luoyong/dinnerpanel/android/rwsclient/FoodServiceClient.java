package luoyong.dinnerpanel.android.rwsclient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import luoyong.dinnerpanel.android.model.Food;
import luoyong.dinnerpanel.android.model.FoodCategory;
import luoyong.dinnerpanel.android.rwscommon.info.RemoteAuthorizationException;
import luoyong.dinnerpanel.android.rwscommon.info.RemoteBusinessLogicException;
import luoyong.dinnerpanel.android.rwscommon.info.RemoteConnectionException;
import luoyong.dinnerpanel.android.rwscommon.info.RemoteInformationException;
import luoyong.dinnerpanel.android.rwscommon.util.JsonBeanUtil;
import luoyong.dinnerpanel.android.rwscommon.util.RWSUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class FoodServiceClient {

   public void addFoodCategory(FoodCategory c) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (c == null) {
         throw new RemoteBusinessLogicException("餐品分类信息不能为空");
      }

      String foodCategoryName = c.getName();

      if (foodCategoryName == null) {
         throw new RemoteBusinessLogicException("餐品分类名称不能为空");
      }

      String url = null;
      try {
         url = RWSURLHolder.getBaseURL()
                 + RWSURLHolder.FOOD_ADD_FOOD_CATEGORY
                 + "/"
                 + URLEncoder.encode(foodCategoryName, "UTF-8");
      } catch (UnsupportedEncodingException ex) {
         throw new RemoteInformationException("字符编码系统不支持", ex);
      }

      RWSUtil.getRWSResultViaGET(url);
   }

   public FoodCategory getFoodCategory(Long id) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (id == null) {
         throw new RemoteBusinessLogicException("餐品分类ID不能为空");
      }

      String url = null;
      url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.FOOD_GET_FOOD_CATEGORY
              + "/"
              + id.longValue();

      JSONArray resultArray = RWSUtil.getRWSResultViaGET(url);

      if (resultArray == null) {
         return null;
      }

      if (resultArray.length() == 0) {
         return null;
      }
      
      JSONObject jsonObject = null;

      try {
         jsonObject = resultArray.getJSONObject(0);
      }catch(JSONException ex) {
         throw new RemoteInformationException("服务器返回的结果格式不正确", ex);
      }

      if (jsonObject == null) {
         return null;
      }

      FoodCategory foodCategory = new FoodCategory();
      JsonBeanUtil.jsonObjectToBean(jsonObject, foodCategory);

      return foodCategory;
   }

   public FoodCategory getFoodCategory(FoodCategory c) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (c == null) {
         throw new RemoteBusinessLogicException("餐品分类信息不能为空");
      }

      return this.getFoodCategory(c.getId());
   }

   public void updateFoodCategory(FoodCategory c) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (c == null) {
         throw new RemoteBusinessLogicException("餐品分类信息不能为空");
      }

      Long foodCategoryId = c.getId();

      if (foodCategoryId == null) {
         throw new RemoteBusinessLogicException("餐品分类ID不能为空");
      }

      String foodCategoryName = c.getName();

      if (foodCategoryName == null) {
         throw new RemoteBusinessLogicException("餐品分类名称不能为空");
      }

      String url = null;
      try {
         url = RWSURLHolder.getBaseURL()
                 + RWSURLHolder.FOOD_UPDATE_FOOD_CATEGORY
                 + "/"
                 + foodCategoryId.longValue()
                 + "/"
                 + URLEncoder.encode(foodCategoryName, "UTF-8");
      } catch (UnsupportedEncodingException ex) {
         throw new RemoteInformationException("字符编码系统不支持", ex);
      }

      RWSUtil.getRWSResultViaGET(url);
   }

   public void removeFoodCategory(Long id) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (id == null) {
         throw new RemoteBusinessLogicException("餐品分类ID不能为空");
      }

      String url = null;
      url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.FOOD_REMOVE_FOOD_CATEGORY
              + "/"
              + id.longValue();

      RWSUtil.getRWSResultViaGET(url);
   }

   public void removeFoodCategory(FoodCategory c) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (c == null) {
         throw new RemoteBusinessLogicException("餐品分类信息不能为空");
      }

      this.removeFoodCategory(c.getId());
   }

   public List<FoodCategory> getAllFoodCategories() throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      String url = null;
      url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.FOOD_GET_ALL_FOOD_CATEGORIES;

      JSONArray jsonArray = RWSUtil.getRWSResultViaGET(url);

      if (jsonArray == null) {
         return null;
      }

      JsonBeanUtil.JSONArrayExtractor<FoodCategory> extractor
              = new JsonBeanUtil.JSONArrayExtractor<FoodCategory>(jsonArray) {

         @Override
         protected FoodCategory getInstance() {
            return new FoodCategory();
         }
      };

      return extractor.extract();
   }

   public void addFoodInformation(Food f) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (f == null) {
         throw new RemoteBusinessLogicException("餐品信息不能为空");
      }

      JSONObject jsonObject = JsonBeanUtil.beanToJsonObject(f);

      if (jsonObject == null) {
         throw new RemoteBusinessLogicException("上传的餐品信息不能为空");
      }

      String url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.FOOD_ADD_FOOD_INFORMATION;

      RWSUtil.getRWSResultViaPOST(url, jsonObject.toString());
   }

   public Food getFoodInformation(Long id) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (id == null) {
         throw new RemoteBusinessLogicException("餐品信息ID不能为空");
      }

      String url = null;
      url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.FOOD_GET_FOOD_INFORMATION
              + "/"
              + id.longValue();

      JSONArray resultArray = RWSUtil.getRWSResultViaGET(url);

      if (resultArray == null) {
         return null;
      }

      if (resultArray.length() == 0) {
         return null;
      }

      JSONObject jsonObject = null;

      try {
         jsonObject = resultArray.getJSONObject(0);
      }catch(JSONException ex) {
         throw new RemoteInformationException("服务器返回的结果格式不正确", ex);
      }

      if (jsonObject == null) {
         return null;
      }

      Food food = new Food();
      this.jsonObjectToFoodInformation(jsonObject, food);

      // Get and set food category to food information.
      Long foodCategoryId = null;
      if (jsonObject.has("foodCategoryId")) {
         try {
            // Get food category ID.
            foodCategoryId = jsonObject.getLong("foodCategoryId");
            // Get food category information.
            FoodCategory foodCategory = this.getFoodCategory(foodCategoryId);
            // Set food category.
            food.setCategory(foodCategory);
         }catch(JSONException ex) {
            food.setCategory(null);
            throw new RemoteInformationException(
                    "服务器返回的餐品所属的餐品分类信息格式不正确", ex);
         }
      }else {
         food.setCategory(null);
      }

      return food;
   }

   public Food getFoodInformation(Food f) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (f == null) {
         throw new RemoteBusinessLogicException("餐品信息不能为空");
      }

      return this.getFoodInformation(f.getId());
   }

   public Food getAvailableFoodInformation(Long id) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (id == null) {
         throw new RemoteBusinessLogicException("餐品信息ID不能为空");
      }

      String url = null;
      url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.FOOD_GET_AVAILABLE_FOOD_INFORMATION
              + "/"
              + id.longValue();

      JSONArray resultArray = RWSUtil.getRWSResultViaGET(url);

      if (resultArray == null) {
         return null;
      }

      if (resultArray.length() == 0) {
         return null;
      }

      JSONObject jsonObject = null;

      try {
         jsonObject = resultArray.getJSONObject(0);
      }catch(JSONException ex) {
         throw new RemoteInformationException("服务器返回的结果格式不正确", ex);
      }

      if (jsonObject == null) {
         return null;
      }

      Food food = new Food();
      // Extract content of json object into food information model.
      this.jsonObjectToFoodInformation(jsonObject, food);

      return food;
   }

   public Food getAvailableFoodInformation(Food f) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (f == null) {
         throw new RemoteBusinessLogicException("餐品信息不能为空");
      }

      return this.getAvailableFoodInformation(f.getId());
   }

   public List<Food> getFoodListFromFoodCategory(Long foodCategoryId) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (foodCategoryId == null) {
         throw new RemoteBusinessLogicException("餐品分类ID不能为空");
      }

      String url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.FOOD_GET_FOOD_LIST_FROM_FOOD_CATEFORY
              + "/"
              + foodCategoryId.longValue();

      JSONArray jsonArray = RWSUtil.getRWSResultViaGET(url);

      if (jsonArray == null) {
         return null;
      }

      JsonBeanUtil.JSONArrayExtractor<Food> extractor
              = new JsonBeanUtil.JSONArrayExtractor<Food>(jsonArray) {

         @Override
         protected Food getInstance() {
            return new Food();
         }

         @Override
         protected void extractJsonObjectToBean(
                 JSONObject jsonObject, Food bean) {

            jsonObjectToFoodInformation(jsonObject, bean);
         }
      };

      return extractor.extract();
   }

   public List<Food> getFoodListFromFoodCategory(FoodCategory c) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (c == null) {
         throw new RemoteBusinessLogicException("餐品分类信息不能为空");
      }

      return this.getFoodListFromFoodCategory(c.getId());
   }

   public List<Food> getAvailableFoodListFromFoodCategory(
           Long foodCategoryId) throws
            RemoteConnectionException,
            RemoteAuthorizationException,
            RemoteInformationException,
            RemoteBusinessLogicException {

      if (foodCategoryId == null) {
         throw new RemoteBusinessLogicException("餐品分类ID不能为空");
      }

      String url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.FOOD_GET_AVAILABLE_FOOD_LIST_FROM_FOOD_CATEFORY
              + "/"
              + foodCategoryId.longValue();

      JSONArray jsonArray = RWSUtil.getRWSResultViaGET(url);

      if (jsonArray == null) {
         return null;
      }

      JsonBeanUtil.JSONArrayExtractor<Food> extractor
              = new JsonBeanUtil.JSONArrayExtractor<Food>(jsonArray) {

         @Override
         protected Food getInstance() {
            return new Food();
         }

         @Override
         protected void extractJsonObjectToBean(
                 JSONObject jsonObject, Food bean) {

            jsonObjectToFoodInformation(jsonObject, bean);
         }
      };

      return extractor.extract();
   }

   public List<Food> getAvailableFoodListFromFoodCategory(FoodCategory c) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (c == null) {
         throw new RemoteBusinessLogicException("餐品分类信息不能为空");
      }

      return this.getAvailableFoodListFromFoodCategory(c.getId());
   }

   public void removeFoodInformation(Long id) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (id == null) {
         throw new RemoteBusinessLogicException("餐品ID不能为空");
      }

      String url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.FOOD_REMOVE_FOOD_INFORMATION
              + "/"
              + id.longValue();

      RWSUtil.getRWSResultViaGET(url);
   }

   public void removeFoodInformation(Food f) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (f == null) {
         throw new RemoteBusinessLogicException("餐品信息不能为空");
      }

      this.removeFoodInformation(f.getId());
   }

   public void updateFoodInformation(Food f) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (f == null) {
         throw new RemoteBusinessLogicException("餐品信息不能为空");
      }

      JSONObject jsonObject = this.foodInformationToJsonObject(f);

      if (jsonObject == null) {
         throw new RemoteBusinessLogicException("上传的餐品信息不能为空");
      }

      String url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.FOOD_UPDATE_FOOD;

      RWSUtil.getRWSResultViaPOST(url, jsonObject.toString());
   }

   public List<Food> searchFoodByName(String foodName) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if ((foodName == null) || (foodName.trim().length() < 1)) {
         throw new RemoteBusinessLogicException("搜索词不能为空");
      }

      String url = null;
      try {
         url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.FOOD_SEARCH_FOOD_BY_NAME
              + "/"
              + URLEncoder.encode(foodName, "UTF-8");
      } catch (UnsupportedEncodingException ex) {
         throw new RemoteInformationException("字符编码系统不支持", ex);
      }

      JSONArray jsonArray = RWSUtil.getRWSResultViaGET(url);

      if (jsonArray == null) {
         return null;
      }

      JsonBeanUtil.JSONArrayExtractor<Food> extractor
              = new JsonBeanUtil.JSONArrayExtractor<Food>(jsonArray) {

         @Override
         protected Food getInstance() {
            return new Food();
         }

         @Override
         protected void extractJsonObjectToBean(
                 JSONObject jsonObject, Food bean) {

            jsonObjectToFoodInformation(jsonObject, bean);
         }
      };

      return extractor.extract();
   }

   public List<Food> searchAvailableFoodByName(String foodName) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if ((foodName == null) || (foodName.trim().length() < 1)) {
         throw new RemoteBusinessLogicException("搜索词不能为空");
      }

      String url = null;
      try {
         url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.FOOD_SEARCH_AVAILABLE_FOOD_BY_NAME
              + "/"
              + URLEncoder.encode(foodName, "UTF-8");
      } catch (UnsupportedEncodingException ex) {
         throw new RemoteInformationException("字符编码系统不支持", ex);
      }

      JSONArray jsonArray = RWSUtil.getRWSResultViaGET(url);

      if (jsonArray == null) {
         return null;
      }

      JsonBeanUtil.JSONArrayExtractor<Food> extractor
              = new JsonBeanUtil.JSONArrayExtractor<Food>(jsonArray) {

         @Override
         protected Food getInstance() {
            return new Food();
         }

         @Override
         protected void extractJsonObjectToBean(
                 JSONObject jsonObject, Food bean) {

            jsonObjectToFoodInformation(jsonObject, bean);
         }
      };

      return extractor.extract();
   }

   public List<Food> searchFoodByCode(String code) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if ((code == null) || (code.trim().length() < 1)) {
         throw new RemoteBusinessLogicException("搜索词不能为空");
      }

      String url = null;
      try {
         url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.FOOD_SEARCH_FOOD_BY_NAME
              + "/"
              + URLEncoder.encode(code, "UTF-8");
      } catch (UnsupportedEncodingException ex) {
         throw new RemoteInformationException("字符编码系统不支持", ex);
      }

      JSONArray jsonArray = RWSUtil.getRWSResultViaGET(url);

      if (jsonArray == null) {
         return null;
      }

      JsonBeanUtil.JSONArrayExtractor<Food> extractor
              = new JsonBeanUtil.JSONArrayExtractor<Food>(jsonArray) {

         @Override
         protected Food getInstance() {
            return new Food();
         }

         @Override
         protected void extractJsonObjectToBean(
                 JSONObject jsonObject, Food bean) {

            jsonObjectToFoodInformation(jsonObject, bean);
         }
      };

      return extractor.extract();
   }

   public List<Food> searchAvailableFoodByCode(String code) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if ((code == null) || (code.trim().length() < 1)) {
         throw new RemoteBusinessLogicException("搜索词不能为空");
      }

      String url = null;
      try {
         url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.FOOD_SEARCH_AVAILABLE_FOOD_BY_NAME
              + "/"
              + URLEncoder.encode(code, "UTF-8");
      } catch (UnsupportedEncodingException ex) {
         throw new RemoteInformationException("字符编码系统不支持", ex);
      }

      JSONArray jsonArray = RWSUtil.getRWSResultViaGET(url);

      if (jsonArray == null) {
         return null;
      }

      JsonBeanUtil.JSONArrayExtractor<Food> extractor
              = new JsonBeanUtil.JSONArrayExtractor<Food>(jsonArray) {

         @Override
         protected Food getInstance() {
            return new Food();
         }

         @Override
         protected void extractJsonObjectToBean(
                 JSONObject jsonObject, Food bean) {

            jsonObjectToFoodInformation(jsonObject, bean);
         }
      };

      return extractor.extract();
   }

   public List<Food> searchFoodByKeyword(String keyword) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if ((keyword == null) || (keyword.trim().length() < 1)) {
         throw new RemoteBusinessLogicException("搜索词不能为空");
      }

      String url = null;
      try {
         url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.FOOD_SEARCH_FOOD_BY_NAME
              + "/"
              + URLEncoder.encode(keyword, "UTF-8");
      } catch (UnsupportedEncodingException ex) {
         throw new RemoteInformationException("字符编码系统不支持", ex);
      }

      JSONArray jsonArray = RWSUtil.getRWSResultViaGET(url);

      if (jsonArray == null) {
         return null;
      }

      JsonBeanUtil.JSONArrayExtractor<Food> extractor
              = new JsonBeanUtil.JSONArrayExtractor<Food>(jsonArray) {

         @Override
         protected Food getInstance() {
            return new Food();
         }

         @Override
         protected void extractJsonObjectToBean(
                 JSONObject jsonObject, Food bean) {

            jsonObjectToFoodInformation(jsonObject, bean);
         }
      };

      return extractor.extract();
   }

   public List<Food> searchAvailableFoodByKeyword(String keyword) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if ((keyword == null) || (keyword.trim().length() < 1)) {
         throw new RemoteBusinessLogicException("搜索词不能为空");
      }

      String url = null;
      try {
         url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.FOOD_SEARCH_AVAILABLE_FOOD_BY_NAME
              + "/"
              + URLEncoder.encode(keyword, "UTF-8");
      } catch (UnsupportedEncodingException ex) {
         throw new RemoteInformationException("字符编码系统不支持", ex);
      }

      JSONArray jsonArray = RWSUtil.getRWSResultViaGET(url);

      if (jsonArray == null) {
         return null;
      }

      JsonBeanUtil.JSONArrayExtractor<Food> extractor
              = new JsonBeanUtil.JSONArrayExtractor<Food>(jsonArray) {

         @Override
         protected Food getInstance() {
            return new Food();
         }

         @Override
         protected void extractJsonObjectToBean(
                 JSONObject jsonObject, Food bean) {

            jsonObjectToFoodInformation(jsonObject, bean);
         }
      };

      return extractor.extract();
   }

   private JSONObject foodInformationToJsonObject(Food f) {

      if (f == null) {
         return null;
      }

      JSONObject foodJsonObject = JsonBeanUtil.beanToJsonObject(f);

      if ((foodJsonObject != null)
              && (f.getCategory() != null)
              && (f.getCategory().getId() != null)) {

         JSONObject foodCategoryJsonObject
                 = JsonBeanUtil.beanToJsonObject(f.getCategory());

         try {
            foodJsonObject.put("category", foodCategoryJsonObject);
         } catch (JSONException ex) {
            ex.printStackTrace(System.err);
         }

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

      JsonBeanUtil.jsonObjectToBean(foodJsonObject, f);

      // Handle food category.
      JSONObject foodCategoryJsonObject = null;
      try {
         foodCategoryJsonObject = foodJsonObject.getJSONObject("category");
      }catch(Throwable t) {
         t.printStackTrace(System.err);
      }

      FoodCategory foodCategory = null;
      if (foodCategoryJsonObject != null) {
         foodCategory = new FoodCategory();
         JsonBeanUtil.jsonObjectToBean(foodCategoryJsonObject, foodCategory);
      }

      f.setCategory(foodCategory);

      // Handle food information tags.
      Set<String> foodTagsSet = new HashSet<String>();
      try {
          JSONArray foodTagsJsonArray = foodJsonObject.getJSONArray("tags");
          if (foodTagsJsonArray != null) {
             int foodTagsCount = foodTagsJsonArray.length();
             String foodTag = null;
             for (int i=0; i<foodTagsCount; i++) {
                foodTag = null;
                try {
                  foodTag = foodTagsJsonArray.getString(i);
                }catch(Throwable t) {
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
