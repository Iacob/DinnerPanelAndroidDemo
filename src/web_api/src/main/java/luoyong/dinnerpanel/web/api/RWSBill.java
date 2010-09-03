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

   @Path("get-current-bill-from-sale-place/{sale-place-id}")
   @Produces("application/json")
   @GET
   public String getCurrentBillFromSalePlace(
           @PathParam("sale-place-id") String salePlaceIdString) {

      JSONArray resultArray = new JSONArray();

      if ((salePlaceIdString == null)
              || (salePlaceIdString.trim().length() < 1)) {
         
         return resultArray.toString();
      }

      Long salePlaceId = null;
      try {
         salePlaceId = Long.valueOf(salePlaceIdString);
      }catch(Throwable t) {
      }

      if (salePlaceId == null) {
         return resultArray.toString();
      }

      JSONObject jsonObjectBill = null;

      List<Bill> billList
              = billManagement.getCurrentBillFromSalePlace(salePlaceId);
      for (Bill bill : billList) {

         if ((bill != null) && (bill.getId() != null)) {

            jsonObjectBill = JsonBeanUtil.beanToJson(bill);
            resultArray.put(jsonObjectBill);
         }
      }

      return resultArray.toString();
   }

   @Path("get-bill/{bill-id}")
   @Produces("application/json")
   @GET
   public String getBill(@PathParam("bill-id") String billIdString) {
      JSONArray resultArray = new JSONArray();
      if ((billIdString == null) || (billIdString.trim().length() < 1)) {
         return resultArray.toString();
      }

      Long billId = null;
      try {
         billId = Long.valueOf(billIdString);
      }catch(Throwable t) {}

      // If bill id is invalid, return an empty array.
      if (billId == null) {
         return resultArray.toString();
      }

      JSONObject jsonObjectBill = null;

      Bill bill = billManagement.getBillInformation(billId);
      if (bill == null) {
         return resultArray.toString();
      }else {
         jsonObjectBill = JsonBeanUtil.beanToJson(bill);
         resultArray.put(jsonObjectBill);
      }

      return resultArray.toString();
   }

   @Path("get-all-bill-items-from-bill/{bill-id}")
   @Produces("application/json")
   @GET
   public String getAllBillItemsFromBill(
           @PathParam("bill-id") String billIdString) {

      JSONArray resultArray = new JSONArray();

      if ((billIdString == null) || (billIdString.trim().length() < 1)) {
         return resultArray.toString();
      }

      Long billId = null;
      try {
         billId = Long.valueOf(billIdString);
      }catch(Throwable t) {}

      if (billId == null) {
         return resultArray.toString();
      }

      Bill bill = billManagement.getBillInformation(billId);
      
      if (bill == null) {
         return resultArray.toString();
      }

      JSONObject jsonObjectBillItem = null;

      List<BillItem> billItemList
              = billManagement.getAllBillItemsFromBill(bill);

      for (BillItem billItem : billItemList) {
         if ((billItem != null) && (billItem.getId() != null)) {

            jsonObjectBillItem = JsonBeanUtil.beanToJson(billItem);
            resultArray.put(jsonObjectBillItem);
         }
      }

      return resultArray.toString();
   }

   @Path("add-item-to-bill/{bill-id}/{food-id}")
   @Produces("application/json")
   @GET
   public void addItemToBill(@PathParam("bill-id") String billIdString,
           @PathParam("food-id") String foodIdString,
           @QueryParam("item-count") String itemCountString,
           @QueryParam("comment") String comment) {

      if ((billIdString == null) || (billIdString.trim().length() < 1)) {
         return;
      }
      if ((foodIdString == null) || (foodIdString.trim().length() < 1)) {
         return;
      }

      // Parse bill id.
      Long billId = null;
      try {
         billId = Long.valueOf(billIdString);
      }catch(Throwable t) {}
      if (billId == null) {
         return;
      }

      Bill bill = billManagement.getBillInformation(billId);
      if (bill == null) {
         return;
      }

      // Parse food id.
      Long foodId = null;
      try {
         foodId = Long.valueOf(foodIdString);
      }catch(Throwable t) {}
      if (foodId == null) {
         return;
      }

      Food food = foodManagement.getFoodInformation(foodId);
      if (food == null) {
         return;
      }

      // Parse item count.
      int itemCount = 1;
      
      if (itemCountString != null) {
         try {
            itemCount = Integer.parseInt(itemCountString);
         }catch(Throwable t) {}
      }
      if (itemCount < 1) {
         return;
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
   }

   @Path("cancel-item-from-bill/{bill-item-id}")
   @Produces("application/json")
   @GET
   public void cancelItemFromBill(
           @PathParam("bill-item-id") String billItemIdString) {

      if ((billItemIdString == null)
              || (billItemIdString.trim().length() < 1)) {
         
         return;
      }

      Long billItemId = null;
      try {
         billItemId = Long.valueOf(billItemIdString);
      }catch(Throwable t) {}

      if (billItemId == null) {
         return;
      }

      billManagement.cancelItemFromBill(billItemId);
   }

   @Path("hasten-food-in-bill/{bill-item-id}")
   @Produces("application/json")
   @GET
   public void hastenFoodInBill(
           @PathParam("bill-item-id") String billItemIdString) {

      if ((billItemIdString == null)
              || (billItemIdString.trim().length() < 1)) {
         
         return;
      }

      Long billItemId = null;
      try {
         billItemId = Long.valueOf(billItemIdString);
      }catch(Throwable t) {}

      if (billItemId == null) {
         return;
      }

      BillItem billItem = billManagement.getBillItemInformation(billItemId);
      if (billItem == null) {
         return;
      }

      billManagement.hastenFoodInBill(billItem);
   }

   @Path("checkout-bill/{bill-id}")
   @Produces("application/json")
   @GET
   public void checkoutBill(@PathParam("bill-id") String billIdString) {

      if ((billIdString == null) || (billIdString.trim().length() < 1)) {
         return;
      }

      Long billId = null;
      try {
         billId = Long.valueOf(billIdString);
      }catch(Throwable t) {}

      if (billId == null) {
         return;
      }

      Bill bill = billManagement.getBillInformation(billId);
      if (bill == null) {
         return;
      }

      billManagement.checkoutBill(bill);
   }

   @Path("cancel-bill/{bill-id}")
   @Produces("application/json")
   @GET
   public void cancelBill(
           @PathParam("bill-id") String billIdString) {

      if ((billIdString == null) || (billIdString.trim().length() < 1)) {
         return;
      }

      Long billId = null;
      try {
         billId = Long.valueOf(billIdString);
      }catch(Throwable t) {}

      if (billId == null) {
         return;
      }

      billManagement.cancelBill(billId);
   }

   @Path("make-bill-for-sale-place/{sale-place-id}")
   @Produces("application/json")
   @GET
   public void makeBill(@PathParam("sale-place-id") String salePlaceIdString) {

      if ((salePlaceIdString == null)
              || (salePlaceIdString.trim().length() < 1)) {
         
         return;
      }

      Long salePlaceId = null;
      try {
         salePlaceId = Long.valueOf(salePlaceIdString);
      }catch(Throwable t) {}

      if (salePlaceId == null) {
         return;
      }

      SalePlace salePlace = salePlaceManagement.getSalePlace(salePlaceId);

      if (salePlace == null) {
         return;
      }

      Bill bill = new Bill();
      bill.setSalePlaceId(salePlace.getId());
      bill.setSalePlaceName(salePlace.getName());
      // TODO Add operator information.
      bill.setBoughtTime(new Date());
      bill.setStatus(BillStatus.S); // Save status to "Sent".
      billManagement.makeBill(bill);
   }

   @Path("set-comment-to-bill/{bill-id}/{comment}")
   @Produces("application/json")
   @GET
   public void setCommentToBill(@PathParam("bill-id") String billIdString,
           @PathParam("comment") String comment) {

      if ((billIdString == null) || (billIdString.trim().length() < 1)) {
         return;
      }

      Long billId = null;
      try {
         billId = Long.valueOf(billIdString);
      }catch(Throwable t) {}
      if (billId == null) {
         return;
      }

      billManagement.setCommentToBill(billId, comment);
   }

   @Path("get-current-bill-count-from-sale-place/{sale-place-id}")
   @Produces("application/json")
   @GET
   public String getCurrentBillCountFromSalePlace(
           @PathParam("sale-place-id") String salePlaceIdString) {

      JSONArray result = new JSONArray();

      if ((salePlaceIdString == null)
              || (salePlaceIdString.trim().length() < 1)) {

         return result.toString();
      }

      Long salePlaceId = null;
      
      try {
         salePlaceId = Long.valueOf(salePlaceIdString);
      }catch(Throwable t) {}
      
      if (salePlaceId == null) {
         return result.toString();
      }

      Long currentBillCount
              = billManagement.getCurrentBillCountFromSalePlace(salePlaceId);
      if (currentBillCount == null) {
         return result.toString();
      }

      result.put(salePlaceId.longValue());

      return result.toString();
   }

   @Path("set-selling-price-to-bill/{bill-id}/{selling-price}")
   @Produces("application/json")
   @GET
   public void setSellingPriceToBill(@PathParam("bill-id") String billIdString,
           @PathParam("selling-price") String sellingPriceString) {

      if ((billIdString == null) || (billIdString.trim().length() < 1)) {
         
         return;
      }
      if ((sellingPriceString == null)
              || (sellingPriceString.trim().length() < 1)) {
         
         return;
      }

      Long billId = null;
      try {
         billId = Long.valueOf(billIdString);
      }catch(Throwable t) {}
      if (billId == null) {
         return;
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
         return;
      }

      billManagement.setSellingPriceToBill(billId, sellingPrice);
   }

   @Path("set-comment-to-bill-item/{bill-item-id}/{comment}")
   @Produces("application/json")
   @GET
   public void setCommentToBillItem(
           @PathParam("bill-item-id") String billItemIdString,
           @PathParam("comment") String comment) {

      if ((billItemIdString == null)
              || (billItemIdString.trim().length() < 1)) {
         
         return;
      }

      Long billItemId = null;
      try {
         billItemId = Long.valueOf(billItemIdString);
      }catch(Throwable t) {}
      if (billItemId == null) {
         return;
      }

      billManagement.setCommentToBillItem(billItemId, comment);
   }

   @Path("get-bill-item/{bill-item-id}")
   @Produces("application/json")
   @GET
   public String getBillItemInformation(
           @PathParam("bill-item-id") String billItemIdString) {

      JSONArray result = new JSONArray();

      if ((billItemIdString == null)
              || (billItemIdString.trim().length() < 1)) {
         
         return result.toString();
      }

      Long billItemId = null;
      try {
         billItemId = Long.valueOf(billItemIdString);
      }catch(Throwable t) {}
      if (billItemId == null) {
         return result.toString();
      }
      
      BillItem billItem = billManagement.getBillItemInformation(billItemId);
      if (billItem == null) {
         return result.toString();
      }

      JSONObject jsonObjectBillItem = JsonBeanUtil.beanToJson(billItem);
      result.put(jsonObjectBillItem);

      return result.toString();
   }

   @Path("calculate-bill-price/{bill-id}")
   @Produces("application/json")
   @GET
   public String calculateBillPrice(@PathParam("bill-id") String billIdString) {

      JSONArray result = new JSONArray();

      if ((billIdString == null) || (billIdString.trim().length() < 1)) {

         return result.toString();
      }

      Long billId = null;
      try {
         billId = Long.valueOf(billIdString);
      }catch(Throwable t) {}
      if (billId == null) {
         return result.toString();
      }

      Bill bill = billManagement.getBillInformation(billId);
      if (bill == null) {
         return result.toString();
      }

      billManagement.writeBillPrice(bill);

      // Reload bill information.
      bill = billManagement.getBillInformation(billId);
      if (bill == null) {
         return result.toString();
      }

      result.put(bill.getPrice());

      return result.toString();
   }
}
