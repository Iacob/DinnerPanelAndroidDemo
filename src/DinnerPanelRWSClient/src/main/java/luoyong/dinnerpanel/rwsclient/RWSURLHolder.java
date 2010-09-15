package luoyong.dinnerpanel.rwsclient;

/**
 *
 * @author 
 */
public class RWSURLHolder {

   private static String _BASE_URL = null;

   public static final String FOOD_ADD_FOOD_CATEGORY
           = "/food/manager/add-food-category";
   public static final String FOOD_GET_FOOD_CATEGORY
           = "/food/customer/get-food-category";
   public static final String FOOD_UPDATE_FOOD_CATEGORY
           = "/food/manager/update-food-category";
   public static final String FOOD_REMOVE_FOOD_CATEGORY
           = "/food/manager/remove-food-category";
   public static final String FOOD_GET_ALL_FOOD_CATEGORIES
           = "/food/customer/get-all-food-categories";
   public static final String FOOD_ADD_FOOD_INFORMATION
           = "/food/manager/add-food-information";
   public static final String FOOD_GET_FOOD_INFORMATION
           = "/food/customer/get-food-information";
   public static final String FOOD_GET_FOOD_LIST_FROM_FOOD_CATEFORY
           = "/food/customer/get-food-list-from-food-category";
   public static final String FOOD_REMOVE_FOOD_INFORMATION
           = "/food/manager/remove-food-information";
   public static final String FOOD_UPDATE_FOOD
           = "/food/manager/update-food-information";
   public static final String FOOD_SEARCH_FOOD_BY_NAME
           = "/food/customer/search-food-by-name";
   public static final String FOOD_SEARCH_FOOD_BY_CODE
           = "/food/customer/search-food-by-code";
   public static final String FOOD_SEARCH_FOOD_BY_KEYWORD
           = "/food/search-food-by-keyword";

   public static final String SALE_PLACE_ADD_SALE_PLACE
           = "/manager/add-sale-place";
   public static final String SALE_PLACE_UPDATE_SALE_PLACE
           = "/manager/update-sale-place";
   public static final String SALE_PLACE_REMOVE_SALE_PLACE
           = "/manager/remove-sale-place";
   public static final String SALE_PLACE_GET_ALL_SALE_PLACES
           = "/operator/sale-place/list-all";
   public static final String SALE_PLACE_GET_SALE_PLACE
           = "/customer/get-sale-place";

   public static final String OPERATOR_ADD_OPERATOR
           = "/operatorManagement/admin/add-operator";
   public static final String OPERATOR_UPDATE_OPERATOR_INFORMATION
           = "/operatorManagement/admin/update-operator-information";
   public static final String OPERATOR_REMOVE_OPERATOR_INFORMATION
           = "/operatorManagement/admin/remove-operator-information";
   public static final String OPERATOR_GET_OPERATOR_INFORMATION
           = "/operatorManagement/admin/get-operator-information";
   public static final String OPERATOR_GET_ALL_OPERATOR_INFORMATION
           = "/operatorManagement/admin/get-all-operator-information";

   public static void setBaseURL(String baseURL) {
      _BASE_URL = baseURL;
   }

   public static String getBaseURL() {
      return _BASE_URL;
   }
}
