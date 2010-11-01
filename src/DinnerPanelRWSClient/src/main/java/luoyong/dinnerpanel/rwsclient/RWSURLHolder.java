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
           = "/food/operator/get-food-information";
   public static final String FOOD_GET_AVAILABLE_FOOD_INFORMATION
           = "/food/customer/get-available-food-information";
   public static final String FOOD_GET_FOOD_LIST_FROM_FOOD_CATEFORY
           = "/food/operator/get-food-list-from-food-category";
   public static final String FOOD_GET_AVAILABLE_FOOD_LIST_FROM_FOOD_CATEFORY
           = "/food/customer/get-available-food-list-from-food-category";
   public static final String FOOD_REMOVE_FOOD_INFORMATION
           = "/food/manager/remove-food-information";
   public static final String FOOD_UPDATE_FOOD
           = "/food/manager/update-food-information";
   public static final String FOOD_SEARCH_FOOD_BY_NAME
           = "/food/operator/search-food-by-name";
   public static final String FOOD_SEARCH_AVAILABLE_FOOD_BY_NAME
           = "/food/customer/search-available-food-by-name";
   public static final String FOOD_SEARCH_FOOD_BY_CODE
           = "/food/operator/search-food-by-code";
   public static final String FOOD_SEARCH_AVAILABLE_FOOD_BY_CODE
           = "/food/customer/search-available-food-by-code";
   public static final String FOOD_SEARCH_FOOD_BY_KEYWORD
           = "/food/operator/search-food-by-keyword";
   public static final String FOOD_SEARCH_AVAILABLE_FOOD_BY_KEYWORD
           = "/food/customer/search-available-food-by-keyword";

   public static final String SALE_PLACE_ADD_SALE_PLACE
           = "/sale-place/manager/add-sale-place";
   public static final String SALE_PLACE_UPDATE_SALE_PLACE
           = "/sale-place/manager/update-sale-place";
   public static final String SALE_PLACE_REMOVE_SALE_PLACE
           = "/sale-place/manager/remove-sale-place";
   public static final String SALE_PLACE_GET_ALL_SALE_PLACES
           = "/sale-place/operator/sale-place/list-all";
   public static final String SALE_PLACE_GET_SALE_PLACE
           = "/sale-place/customer/get-sale-place";

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

   public static final String BILL_GET_CURRENT_BILL_FROM_SALE_PLACE
           = "/bill/operator/get-current-bill-from-sale-place";
   public static final String BILL_GET_BILL = "/bill/customer/get-bill";
   public static final String BILL_GET_BILL_INFORMATION_FROM_BILL_ITEM = "/bill/customer/get-bill-information-from-bill-item";
   public static final String BILL_GET_ALL_BILL_ITEMS_FROM_BILL
           = "/bill/customer/get-all-bill-items-from-bill";
   public static final String BILL_ADD_ITEM_TO_BILL
           = "/bill/customer/add-item-to-bill";
   public static final String BILL_CANCEL_ITEM_FROM_BILL
           = "/bill/customer/cancel-item-from-bill";
   public static final String BILL_HASTEN_FOOD_IN_BILL
           = "/bill/operator/hasten-food-in-bill";
   public static final String BILL_CHECKOUT_BILL
           = "/bill/operator/checkout-bill";
   public static final String BILL_CANCEL_BILL
           = "/bill/operator/cancel-bill";
   public static final String BILL_MAKE_BILL_FOR_SALE_PLACE
           = "/bill/operator/make-bill-for-sale-place";
   public static final String BILL_SET_COMMENT_TO_BILL
           = "/bill/operator/set-comment-to-bill";
   public static final String BILL_GET_CURRENT_BILL_COUNT_FROM_SALE_PLACE
           = "/bill/customer/get-current-bill-count-from-sale-place";
   public static final String BILL_SET_SELLING_PRICE_TO_BILL
           = "/bill/operator/set-selling-price-to-bill";
   public static final String BILL_SET_COMMENT_TO_BILL_ITEM
           = "/bill/customer/set-comment-to-bill-item/";
   public static final String BILL_GET_BILL_ITEM
           = "/bill/customer/get-bill-item";
   public static final String BILL_CALCULATE_BILL_PRICE
           = "/bill/customer/calculate-bill-price";
   public static final String BILL_REMOVE_BILL
           = "/bill/manager/remove-bill";
   public static final String BILL_MARK_BILL_AS_SENT
           = "/bill/operator/mark-bill-as-sent";
   public static final String BILL_REMOVE_ITEM_FROM_BILL
           = "/bill/manager/remove-item-from-bill";
   public static final String BILL_MARK_BILL_ITEM_COMPLETE
           = "/bill/kitchen/mark-bill-item-complete";
   public static final String BILL_MARK_BILL_ITEM_PROCESSING
           = "/bill/kitchen/mark-bill-item-processing";
   public static final String BILL_MARK_BILL_ITEM_RETURNED
           = "/bill/kitchen/mark-bill-item-returned";
   public static final String BILL_CALCULATE_BILL_ITEM_HASTEN_COUNT
           = "/bill/operator/calculate-bill-item-hasten-count";

   public static void setBaseURL(String baseURL) {
      _BASE_URL = baseURL;
   }

   public static String getBaseURL() {
      return _BASE_URL;
   }
}
