package luoyong.dinnerpanel.web.api;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import luoyong.dinnerpanel.dao.model.Bill;
import luoyong.dinnerpanel.dao.model.BillItem;
import luoyong.dinnerpanel.dao.model.BillItemStatus;
import luoyong.dinnerpanel.dao.model.BillStatus;
import luoyong.dinnerpanel.dao.model.Food;
import luoyong.dinnerpanel.dao.model.SalePlace;
import luoyong.dinnerpanel.service.BillManagement;
import luoyong.dinnerpanel.service.FoodManagement;
import luoyong.dinnerpanel.service.SalePlaceManagement;
import luoyong.dinnerpanel.web.util.JsonBeanUtil;
import luoyong.dinnerpanel.web.util.RWSUtil;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
@Path("bill")
public class RWSBill {

   private BillManagement billManagement = null;
   private SalePlaceManagement salePlaceManagement = null;
   private FoodManagement foodManagement = null;

   public RWSBill() {

      billManagement = new BillManagement();
      salePlaceManagement = new SalePlaceManagement();
      foodManagement = new FoodManagement();
   }

   @Path("operator/get-current-bill-from-sale-place/{sale-place-id}")
   @Produces("application/json")
   @GET
   public String getCurrentBillFromSalePlace(
           @PathParam("sale-place-id") String salePlaceIdString) {

      JSONObject result = new JSONObject();

      JSONArray resultArray = new JSONArray();

      if ((salePlaceIdString == null)
              || (salePlaceIdString.trim().length() < 1)) {
         
         RWSUtil.setJsonObjectErrorMessage(result, 1, "餐桌ID不能为空");
         return result.toString();
      }

      Long salePlaceId = null;
      try {
         salePlaceId = Long.valueOf(salePlaceIdString);
      }catch(Throwable t) {
      }

      if (salePlaceId == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "餐桌ID不是合法的整数");
         return result.toString();
      }

      List<Bill> billList
              = billManagement.getCurrentBillFromSalePlace(salePlaceId);

      if (billList == null) {
         RWSUtil.setJsonObjectResult(result, 0, resultArray);
         return result.toString();
      }

      JSONObject jsonObjectBill = null;

      for (Bill bill : billList) {

         if ((bill != null) && (bill.getId() != null)) {

            jsonObjectBill = JsonBeanUtil.beanToJsonObject(bill);
            if (jsonObjectBill != null) {
               resultArray.put(jsonObjectBill);
            }
         }
      }

      RWSUtil.setJsonObjectResult(result, 0, resultArray);
      return result.toString();
   }

   @Path("customer/get-bill/{bill-id}")
   @Produces("application/json")
   @GET
   public String getBill(@PathParam("bill-id") String billIdString) {

      JSONObject result = new JSONObject();

      JSONArray resultArray = new JSONArray();
      
      if ((billIdString == null) || (billIdString.trim().length() < 1)) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单ID不能为空");
         return result.toString();
      }

      Long billId = null;
      try {
         billId = Long.valueOf(billIdString);
      }catch(Throwable t) {}

      // If bill id is invalid, return empty result.
      if (billId == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单ID不是合法的整数");
         return result.toString();
      }

      JSONObject jsonObjectBill = null;

      Bill bill = billManagement.getBillInformation(billId);
      if (bill == null) {
         RWSUtil.setJsonObjectResult(result, 0, resultArray);
         return result.toString();
      }else {
         jsonObjectBill = JsonBeanUtil.beanToJsonObject(bill);
         if (jsonObjectBill == null) {
            RWSUtil.setJsonObjectResult(result, 0, resultArray);
            return result.toString();
         }else {
            resultArray.put(jsonObjectBill);
         }
      }

      RWSUtil.setJsonObjectResult(result, 0, resultArray);
      return result.toString();
   }

   @Path("customer/get-all-bill-items-from-bill/{bill-id}")
   @Produces("application/json")
   @GET
   public String getAllBillItemsFromBill(
           @PathParam("bill-id") String billIdString) {

      JSONObject result = new JSONObject();

      JSONArray resultArray = new JSONArray();

      if ((billIdString == null) || (billIdString.trim().length() < 1)) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单ID不能为空");
         return result.toString();
      }

      Long billId = null;
      try {
         billId = Long.valueOf(billIdString);
      }catch(Throwable t) {}

      if (billId == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单ID不是合法的整数");
         return result.toString();
      }

      Bill bill = billManagement.getBillInformation(billId);
      
      if (bill == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "所指定的账单不存在");
         return result.toString();
      }

      JSONObject jsonObjectBillItem = null;

      List<BillItem> billItemList
              = billManagement.getAllBillItemsFromBill(bill);

      if (billItemList == null) {
         RWSUtil.setJsonObjectResult(result, 0, resultArray);
         return result.toString();
      }

      for (BillItem billItem : billItemList) {
         if ((billItem != null) && (billItem.getId() != null)) {

            jsonObjectBillItem = JsonBeanUtil.beanToJsonObject(billItem);
            if (jsonObjectBillItem != null) {
               resultArray.put(jsonObjectBillItem);
            }
         }
      }

      RWSUtil.setJsonObjectResult(result, 0, resultArray);
      return result.toString();
   }

   @Path("customer/add-item-to-bill/{bill-id}/{food-id}")
   @Produces("application/json")
   @GET
   public String addItemToBill(@PathParam("bill-id") String billIdString,
           @PathParam("food-id") String foodIdString,
           @QueryParam("item-count") String itemCountString,
           @QueryParam("comment") String comment) {

      JSONObject result = new JSONObject();

      if ((billIdString == null) || (billIdString.trim().length() < 1)) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单ID不能为空");
         return result.toString();
      }
      if ((foodIdString == null) || (foodIdString.trim().length() < 1)) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "餐品信息ID不能为空");
         return result.toString();
      }

      // Parse bill id.
      Long billId = null;
      try {
         billId = Long.valueOf(billIdString);
      }catch(Throwable t) {}
      
      if (billId == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单ID不是合法的整数");
         return result.toString();
      }

      Bill bill = billManagement.getBillInformation(billId);
      if (bill == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "所指定的账单不存在");
         return result.toString();
      }

      // Parse food id.
      Long foodId = null;
      try {
         foodId = Long.valueOf(foodIdString);
      }catch(Throwable t) {}
      
      if (foodId == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "餐品信息ID不是合法的整数");
         return result.toString();
      }

      Food food = foodManagement.getFoodInformation(foodId);
      if (food == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "所指定的餐品信息不存在");
         return result.toString();
      }

      // Parse item count.
      int itemCount = 1;
      
      if (itemCountString != null) {
         try {
            itemCount = Integer.parseInt(itemCountString);
         }catch(Throwable t) {}
      }
      if (itemCount < 1) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "所指定的餐品份数必须大於零");
         return result.toString();
      }

      // Make new bill item.
      BillItem billItem = new BillItem();
      billItem.setAddedTime(new Date());
      billItem.setBill(bill);
      billItem.setFoodId(foodId);
      billItem.setFoodName(food.getName());
      billItem.setFoodCount(itemCount);
      billItem.setPrice(food.getPrice());
      // TODO Add operator information.
      //billItem.setOperatorId();
      //billItem.setOperatorName();
      billItem.setHastenCount(null);
      billItem.setComment(comment);
      billItem.setStatus(BillItemStatus.W);

      // Save the bill item.
      billManagement.addItemToBill(billItem);

      RWSUtil.setJsonObjectReturnCode(result, 0);
      return result.toString();
   }

   @Path("customer/cancel-item-from-bill/{bill-item-id}")
   @Produces("application/json")
   @GET
   public String cancelItemFromBill(
           @PathParam("bill-item-id") String billItemIdString) {

      JSONObject result = new JSONObject();

      if ((billItemIdString == null)
              || (billItemIdString.trim().length() < 1)) {
         
         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单条目ID不能为空");
         return result.toString();
      }

      Long billItemId = null;
      try {
         billItemId = Long.valueOf(billItemIdString);
      }catch(Throwable t) {}

      if (billItemId == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单条目ID不是合法的整数");
         return result.toString();
      }

      billManagement.cancelItemFromBill(billItemId);

      RWSUtil.setJsonObjectReturnCode(result, 0);
      return result.toString();
   }

   @Path("operator/hasten-food-in-bill/{bill-item-id}")
   @Produces("application/json")
   @GET
   public String hastenFoodInBill(
           @PathParam("bill-item-id") String billItemIdString) {

      JSONObject result = new JSONObject();

      if ((billItemIdString == null)
              || (billItemIdString.trim().length() < 1)) {
         
         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单条目ID不能为空");
         return result.toString();
      }

      Long billItemId = null;
      try {
         billItemId = Long.valueOf(billItemIdString);
      }catch(Throwable t) {}

      if (billItemId == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单条目ID不是合法的整数");
         return result.toString();
      }

      BillItem billItem = billManagement.getBillItemInformation(billItemId);
      
      if (billItem == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "所指定的账单条目不存在");
         return result.toString();
      }

      billManagement.hastenFoodInBill(billItem);

      RWSUtil.setJsonObjectReturnCode(result, 0);
      return result.toString();
   }

   @Path("operator/checkout-bill/{bill-id}")
   @Produces("application/json")
   @GET
   public String checkoutBill(@PathParam("bill-id") String billIdString) {

      JSONObject result = new JSONObject();

      if ((billIdString == null) || (billIdString.trim().length() < 1)) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单ID不能为空");
         return result.toString();
      }

      Long billId = null;
      try {
         billId = Long.valueOf(billIdString);
      }catch(Throwable t) {}

      if (billId == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单ID不是合法的整数");
         return result.toString();
      }

      Bill bill = billManagement.getBillInformation(billId);
      if (bill == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "所指定的账单不存在");
         return result.toString();
      }

      billManagement.checkoutBill(bill);

      RWSUtil.setJsonObjectReturnCode(result, 0);
      return result.toString();
   }

   @Path("operator/cancel-bill/{bill-id}")
   @Produces("application/json")
   @GET
   public String cancelBill(
           @PathParam("bill-id") String billIdString) {

      JSONObject result = new JSONObject();

      if ((billIdString == null) || (billIdString.trim().length() < 1)) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单ID不能为空");
         return result.toString();
      }

      Long billId = null;
      try {
         billId = Long.valueOf(billIdString);
      }catch(Throwable t) {}

      if (billId == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单ID不是合法的整数");
         return result.toString();
      }

      billManagement.cancelBill(billId);

      RWSUtil.setJsonObjectReturnCode(result, 0);
      return result.toString();
   }

   @Path("operator/make-bill-for-sale-place/{sale-place-id}")
   @Produces("application/json")
   @GET
   public String makeBill(
           @PathParam("sale-place-id") String salePlaceIdString) {

      JSONObject result = new JSONObject();

      if ((salePlaceIdString == null)
              || (salePlaceIdString.trim().length() < 1)) {
         
         RWSUtil.setJsonObjectErrorMessage(result, 1, "餐桌ID不能为空");
         return result.toString();
      }

      Long salePlaceId = null;
      try {
         salePlaceId = Long.valueOf(salePlaceIdString);
      }catch(Throwable t) {}

      if (salePlaceId == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "餐桌ID不是合法的整数");
         return result.toString();
      }

      SalePlace salePlace = salePlaceManagement.getSalePlace(salePlaceId);

      if (salePlace == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "所指定的餐桌不存在");
         return result.toString();
      }

      Bill bill = new Bill();
      bill.setSalePlaceId(salePlace.getId());
      bill.setSalePlaceName(salePlace.getName());
      // TODO Add operator information.
      bill.setBoughtTime(new Date());
      bill.setStatus(BillStatus.S); // Save status to "Sent".
      billManagement.makeBill(bill);

      RWSUtil.setJsonObjectReturnCode(result, 0);
      return result.toString();
   }

   @Path("operator/set-comment-to-bill/{bill-id}")
   @Produces("application/json")
   @GET
   public String setCommentToBill(@PathParam("bill-id") String billIdString,
           @QueryParam("comment") String comment) {

      JSONObject result = new JSONObject();

      if ((billIdString == null) || (billIdString.trim().length() < 1)) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单ID不能为空");
         return result.toString();
      }

      Long billId = null;
      try {
         billId = Long.valueOf(billIdString);
      }catch(Throwable t) {}
      
      if (billId == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "餐桌ID不是合法的整数");
         return result.toString();
      }

      billManagement.setCommentToBill(billId, comment);

      RWSUtil.setJsonObjectReturnCode(result, 0);
      return result.toString();
   }

   @Path("operator/get-current-bill-count-from-sale-place/{sale-place-id}")
   @Produces("application/json")
   @GET
   public String getCurrentBillCountFromSalePlace(
           @PathParam("sale-place-id") String salePlaceIdString) {

      JSONObject result = new JSONObject();

      JSONArray resultArray = new JSONArray();

      if ((salePlaceIdString == null)
              || (salePlaceIdString.trim().length() < 1)) {

         RWSUtil.setJsonObjectErrorMessage(result, 1, "餐桌ID不能为空");
         return result.toString();
      }

      Long salePlaceId = null;
      
      try {
         salePlaceId = Long.valueOf(salePlaceIdString);
      }catch(Throwable t) {}
      
      if (salePlaceId == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "餐桌ID不是合法的整数");
         return result.toString();
      }

      Long currentBillCount
              = billManagement.getCurrentBillCountFromSalePlace(salePlaceId);
      
      if (currentBillCount == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "返回的账单数不是合法的整数");
         return result.toString();
      }

      resultArray.put(salePlaceId.longValue());

      RWSUtil.setJsonObjectResult(result, 0, resultArray);
      return result.toString();
   }

   @Path("operator/set-selling-price-to-bill/{bill-id}/{selling-price}")
   @Produces("application/json")
   @GET
   public String setSellingPriceToBill(@PathParam("bill-id") String billIdString,
           @PathParam("selling-price") String sellingPriceString) {

      JSONObject result = new JSONObject();

      if ((billIdString == null) || (billIdString.trim().length() < 1)) {
         
         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单ID不能为空");
         return result.toString();
      }
      if ((sellingPriceString == null)
              || (sellingPriceString.trim().length() < 1)) {
         
         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单价格不能为空");
         return result.toString();
      }

      Long billId = null;
      try {
         billId = Long.valueOf(billIdString);
      }catch(Throwable t) {}
      if (billId == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单ID不是合法的整数");
         return result.toString();
      }

      BigDecimal sellingPrice = null;
      try {
         sellingPrice = new BigDecimal(sellingPriceString,
                 MathContext.DECIMAL32);
         sellingPrice.setScale(2);
      }catch(Throwable t) {
         sellingPrice = null;
      }
      if (sellingPrice == null) {
         RWSUtil.setJsonObjectErrorMessage(
                 result, 1, "输入的账单价格不是合法的整数");
         return result.toString();
      }

      billManagement.setSellingPriceToBill(billId, sellingPrice);

      RWSUtil.setJsonObjectReturnCode(result, 0);
      return result.toString();
   }

   @Path("customer/set-comment-to-bill-item/{bill-item-id}")
   @Produces("application/json")
   @GET
   public String setCommentToBillItem(
           @PathParam("bill-item-id") String billItemIdString,
           @QueryParam("comment") String comment) {

      JSONObject result = new JSONObject();

      if ((billItemIdString == null)
              || (billItemIdString.trim().length() < 1)) {
         
         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单条目ID不能为空");
         return result.toString();
      }

      Long billItemId = null;
      try {
         billItemId = Long.valueOf(billItemIdString);
      }catch(Throwable t) {}
      
      if (billItemId == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单条目ID不是合法的整数");
         return result.toString();
      }

      billManagement.setCommentToBillItem(billItemId, comment);

      RWSUtil.setJsonObjectReturnCode(result, 0);
      return result.toString();
   }

   @Path("customer/get-bill-item/{bill-item-id}")
   @Produces("application/json")
   @GET
   public String getBillItemInformation(
           @PathParam("bill-item-id") String billItemIdString) {

      JSONObject result = new JSONObject();

      JSONArray resultArray = new JSONArray();

      if ((billItemIdString == null)
              || (billItemIdString.trim().length() < 1)) {
         
         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单条目ID不能为空");
         return result.toString();
      }

      Long billItemId = null;
      try {
         billItemId = Long.valueOf(billItemIdString);
      }catch(Throwable t) {}
      
      if (billItemId == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单条目ID不是合法的整数");
         return result.toString();
      }
      
      BillItem billItem = billManagement.getBillItemInformation(billItemId);
      
      if (billItem == null) {
         RWSUtil.setJsonObjectResult(result, 0, resultArray);
         return result.toString();
      }

      JSONObject jsonObjectBillItem = JsonBeanUtil.beanToJsonObject(billItem);
      resultArray.put(jsonObjectBillItem);

      RWSUtil.setJsonObjectResult(result, 0, resultArray);
      return result.toString();
   }

   @Path("operator/calculate-bill-price/{bill-id}")
   @Produces("application/json")
   @GET
   public String calculateBillPrice(@PathParam("bill-id") String billIdString) {

      JSONObject result = new JSONObject();

      JSONArray resultArray = new JSONArray();

      if ((billIdString == null) || (billIdString.trim().length() < 1)) {

         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单ID不能为空");
         return result.toString();
      }

      Long billId = null;
      try {
         billId = Long.valueOf(billIdString);
      }catch(Throwable t) {}
      
      if (billId == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单ID不是合法的整数");
         return result.toString();
      }

      Bill bill = billManagement.getBillInformation(billId);
      
      if (bill == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "所指定的账单不存在");
         return result.toString();
      }

      billManagement.writeBillPrice(bill);

      // Reload bill information.
      bill = billManagement.getBillInformation(billId);
      
      if (bill == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "所指定的账单已不存在");
         return result.toString();
      }

      resultArray.put(bill.getPrice());

      RWSUtil.setJsonObjectResult(result, 0, resultArray);
      return result.toString();
   }

   @Path("manager/remove-bill/{bill-id}")
   @Produces("application/json")
   @GET
   public String removeBill(@PathParam("bill-id") String billIdString) {

      JSONObject result = new JSONObject();

      if ((billIdString ==  null)
              || (billIdString.trim().length() < 1)) {

         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单ID不能为空");
         return result.toString();
      }

      Long billId = null;
      try {
         billId = Long.valueOf(billIdString);
      }catch(Throwable t) {}

      if (billId == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单ID不是合法的整数");
         return result.toString();
      }

      billManagement.removeBill(billId);

      RWSUtil.setJsonObjectReturnCode(result, 0);
      return result.toString();
   }

   @Path("operator/mark-bill-as-sent/{bill-id}")
   @Produces("application/json")
   @GET
   public String markBillAsSent(@PathParam("bill-id") String billIdString) {

      JSONObject result = new JSONObject();

      if ((billIdString ==  null)
              || (billIdString.trim().length() < 1)) {

         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单ID不能为空");
         return result.toString();
      }

      Long billId = null;
      try {
         billId = Long.valueOf(billIdString);
      }catch(Throwable t) {}

      if (billId == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单ID不是合法的整数");
         return result.toString();
      }

      billManagement.markBillAsSent(billId);
      
      RWSUtil.setJsonObjectReturnCode(result, 0);
      return result.toString();
   }

   @Path("manager/remove-item-from-bill/{bill-item-id}")
   @Produces("application/json")
   @GET
   public String removeItemFromBill(
           @PathParam("bill-item-id") String billItemIdString) {

      JSONObject result = new JSONObject();

      if ((billItemIdString ==  null)
              || (billItemIdString.trim().length() < 1)) {

         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单条目ID不能为空");
         return result.toString();
      }

      Long billItemId = null;
      try {
         billItemId = Long.valueOf(billItemIdString);
      }catch(Throwable t) {}

      if (billItemId == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单条目ID不是合法的整数");
         return result.toString();
      }

      billManagement.removeItemFromBill(billItemId);

      RWSUtil.setJsonObjectReturnCode(result, 0);
      return result.toString();
   }

   @Path("kitchen/mark-bill-item-complete/{bill-item-id}")
   @Produces("application/json")
   @GET
   public String markBillItemComplete(
           @PathParam("bill-item-id") String billItemIdString) {

      JSONObject result = new JSONObject();

      if ((billItemIdString ==  null)
              || (billItemIdString.trim().length() < 1)) {

         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单条目ID不能为空");
         return result.toString();
      }

      Long billItemId = null;
      try {
         billItemId = Long.valueOf(billItemIdString);
      }catch(Throwable t) {}

      if (billItemId == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单条目ID不是合法的整数");
         return result.toString();
      }

      billManagement.markBillItemComplete(billItemId);

      RWSUtil.setJsonObjectReturnCode(result, 0);
      return result.toString();
   }

   @Path("kitchen/mark-bill-item-processing/{bill-item-id}")
   @Produces("application/json")
   @GET
   public String markBillItemProcessing(
           @PathParam("bill-item-id") String billItemIdString) {

      JSONObject result = new JSONObject();

      if ((billItemIdString ==  null)
              || (billItemIdString.trim().length() < 1)) {

         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单条目ID不能为空");
         return result.toString();
      }

      Long billItemId = null;
      try {
         billItemId = Long.valueOf(billItemIdString);
      }catch(Throwable t) {}

      if (billItemId == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单条目ID不是合法的整数");
         return result.toString();
      }

      billManagement.markBillItemProcessing(billItemId);

      RWSUtil.setJsonObjectReturnCode(result, 0);
      return result.toString();
   }

   @Path("kitchen/mark-bill-item-returned/{bill-item-id}")
   @Produces("application/json")
   @GET
   public String markBillItemReturned(
           @PathParam("bill-item-id") String billItemIdString) {

      JSONObject result = new JSONObject();

      if ((billItemIdString ==  null)
              || (billItemIdString.trim().length() < 1)) {

         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单条目ID不能为空");
         return result.toString();
      }

      Long billItemId = null;
      try {
         billItemId = Long.valueOf(billItemIdString);
      }catch(Throwable t) {}

      if (billItemId == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单条目ID不是合法的整数");
         return result.toString();
      }

      billManagement.markBillItemReturned(billItemId);

      RWSUtil.setJsonObjectReturnCode(result, 0);
      return result.toString();
   }

   @Path("operator/calculate-bill-item-hasten-count/{bill-item-id}")
   @Produces("application/json")
   @GET
   public String calculateBillItemHastenCount(
           @PathParam("bill-item-id") String billItemIdString) {

      JSONObject result = new JSONObject();

      JSONArray resultArray = new JSONArray();

      if ((billItemIdString == null)
              || (billItemIdString.trim().length() < 1)) {

         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单条目ID不能为空");
         return result.toString();
      }

      Long billItemId = null;
      try {
         billItemId = Long.valueOf(billItemIdString);
      }catch(Throwable t) {}
      
      if (billItemId == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "账单条目ID不是合法的整数");
         return result.toString();
      }

      BillItem billItem = billManagement.getBillItemInformation(billItemId);
      
      if (billItem == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "所指定的账单条目不存在");
         return result.toString();
      }

      billManagement.writeBillItemHastenCount(billItem);

      // Reload bill item information.
      billItem = billManagement.getBillItemInformation(billItemId);

      if (billItem == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "所指定的账单条目已不存在");
         return result.toString();
      }

      resultArray.put(billItem.getHastenCount());

      RWSUtil.setJsonObjectResult(result, 0, resultArray);
      return result.toString();
   }
}
