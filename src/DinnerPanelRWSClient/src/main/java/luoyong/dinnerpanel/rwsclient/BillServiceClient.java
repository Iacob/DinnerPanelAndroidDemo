package luoyong.dinnerpanel.rwsclient;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import luoyong.dinnerpanel.dao.model.Bill;
import luoyong.dinnerpanel.dao.model.BillItem;
import luoyong.dinnerpanel.dao.model.SalePlace;
import luoyong.dinnerpanel.rwscommon.info.RemoteAuthorizationException;
import luoyong.dinnerpanel.rwscommon.info.RemoteBusinessLogicException;
import luoyong.dinnerpanel.rwscommon.info.RemoteConnectionException;
import luoyong.dinnerpanel.rwscommon.info.RemoteInformationException;
import luoyong.dinnerpanel.rwscommon.util.JsonBeanUtil;
import luoyong.dinnerpanel.rwscommon.util.RWSUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class BillServiceClient {

   public BillServiceClient() {
   }

   public void makeBill(Long salePlaceId) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (salePlaceId == null) {
         throw new RemoteBusinessLogicException("餐桌ID不能为空");
      }

      String url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.BILL_MAKE_BILL_FOR_SALE_PLACE
              + "/"
              + salePlaceId.longValue();

      RWSUtil.getRWSResultViaGET(url);
   }

   public void removeBill(Long id) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (id == null) {
         throw new RemoteBusinessLogicException("账单ID不能为空");
      }

      String url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.BILL_REMOVE_BILL
              + "/"
              + id.longValue();

      RWSUtil.getRWSResultViaGET(url);
   }

   public void removeBill(Bill b) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException  {
      
      this.removeBill(b.getId());
   }

   public void setCommentToBill(Long id, String comment) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (id == null) {
         throw new RemoteBusinessLogicException("账单ID不能为空");
      }

      String url = null;
      try {
         url = RWSURLHolder.getBaseURL()
                 + RWSURLHolder.BILL_SET_COMMENT_TO_BILL
                 + "/"
                 + id.longValue();
         if (comment != null) {
            url = url + "?" +URLEncoder.encode(comment, "UTF-8");
         }
      } catch (UnsupportedEncodingException ex) {
         throw new RemoteInformationException("字符编码系统不支持", ex);
      }

      RWSUtil.getRWSResultViaGET(url);
   }

   public void setCommentToBill(Bill b, String comment) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {
      
      this.setCommentToBill(b.getId(), comment);
   }

   public void setSellingPriceToBill(Long id, BigDecimal sellingPrice) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (id == null) {
         throw new RemoteBusinessLogicException("账单ID不能为空");
      }

      String url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.BILL_SET_SELLING_PRICE_TO_BILL
              + "/"
              + id.longValue()
              + "/"
              + sellingPrice.doubleValue();

      RWSUtil.getRWSResultViaGET(url);
   }

   public void setSellingPriceToBill(Bill b, BigDecimal sellingPrice) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {
      
      this.setSellingPriceToBill(b.getId(), sellingPrice);
   }

   public void markBillAsSent(Long id) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {
      
      if (id == null) {
         throw new RemoteBusinessLogicException("账单ID不能为空");
      }

      String url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.BILL_MARK_BILL_AS_SENT
              + "/"
              + id.longValue();

      RWSUtil.getRWSResultViaGET(url);
   }

   public void markBillAsSent(Bill b) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {
      
      this.markBillAsSent(b.getId());
   }

   public void cancelBill(Long id) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (id == null) {
         throw new RemoteBusinessLogicException("账单ID不能为空");
      }

      String url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.BILL_CANCEL_BILL
              + "/"
              + id.longValue();

      RWSUtil.getRWSResultViaGET(url);
   }

   public void cancelBill(Bill b) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {
      
      this.cancelBill(b.getId());
   }

   public void addItemToBill(BillItem i) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (i == null) {
         throw new RemoteBusinessLogicException("账单条目信息不能为空");
      }

      Bill bill = i.getBill();
      if (bill == null) {
         throw new RemoteBusinessLogicException("账单信息不能为空");
      }

      Long billId = bill.getId();
      if (bill == null) {
         throw new RemoteBusinessLogicException("账单ID不能为空");
      }

      Long foodId = i.getFoodId();
      if (bill == null) {
         throw new RemoteBusinessLogicException("餐品ID不能为空");
      }

      Integer itemCount = i.getFoodCount();

      String comment = i.getComment();

      String url = null;
      try {
         url = RWSURLHolder.getBaseURL()
                 + RWSURLHolder.BILL_ADD_ITEM_TO_BILL
                 + "/"
                 + billId.longValue()
                 + "/"
                 + foodId.longValue()
                 + "?";
         if (itemCount != null) {
            url = url + "&item-count=" + itemCount.intValue();
         }
         if (comment != null) {
            url = url + "&comment=" + URLEncoder.encode(comment, "UTF-8");
         }
      } catch (UnsupportedEncodingException ex) {
         throw new RemoteInformationException("字符编码系统不支持", ex);
      }

      RWSUtil.getRWSResultViaGET(url);
   }

   public void removeItemFromBill(Long id) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (id == null) {
         throw new RemoteBusinessLogicException("账单条目ID不能为空");
      }

      String url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.BILL_REMOVE_ITEM_FROM_BILL
              + "/"
              + id.longValue();

      RWSUtil.getRWSResultViaGET(url);
   }

   public void removeItemFromBill(BillItem item) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {
      
      this.removeItemFromBill(item.getId());
   }

   public void cancelItemFromBill(Long id) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (id == null) {
         throw new RemoteBusinessLogicException("账单条目ID不能为空");
      }

      String url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.BILL_CANCEL_ITEM_FROM_BILL
              + "/"
              + id.longValue();

      RWSUtil.getRWSResultViaGET(url);
   }

   public void cancelItemFromBill(BillItem item) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {
      
      this.cancelItemFromBill(item.getId());
   }

   public void markBillItemComplete(Long id) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (id == null) {
         throw new RemoteBusinessLogicException("账单条目ID不能为空");
      }

      String url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.BILL_MARK_BILL_ITEM_COMPLETE
              + "/"
              + id.longValue();

      RWSUtil.getRWSResultViaGET(url);
   }

   public void markBillItemComplete(BillItem item) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {
      
      this.markBillItemComplete(item.getId());
   }

   public void markBillItemProcessing(Long id) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (id == null) {
         throw new RemoteBusinessLogicException("账单条目ID不能为空");
      }

      String url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.BILL_MARK_BILL_ITEM_PROCESSING
              + "/"
              + id.longValue();

      RWSUtil.getRWSResultViaGET(url);
   }

   public void markBillItemProcessing(BillItem item) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {
      
      this.markBillItemProcessing(item.getId());
   }

   public void markBillItemReturned(Long id) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (id == null) {
         throw new RemoteBusinessLogicException("账单条目ID不能为空");
      }

      String url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.BILL_MARK_BILL_ITEM_RETURNED
              + "/"
              + id.longValue();

      RWSUtil.getRWSResultViaGET(url);
   }

   public void markBillItemReturned(BillItem item) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {
      
      this.markBillItemReturned(item.getId());
   }

   public void setCommentToBillItem(Long id, String comment) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (id == null) {
         throw new RemoteBusinessLogicException("账单条目ID不能为空");
      }

      String url = null;
      try {
         url = RWSURLHolder.getBaseURL()
                 + RWSURLHolder.BILL_SET_COMMENT_TO_BILL_ITEM
                 + "/"
                 + id.longValue();
         if (comment != null) {
            url = url + "?" +URLEncoder.encode(comment, "UTF-8");
         }
      } catch (UnsupportedEncodingException ex) {
         throw new RemoteInformationException("字符编码系统不支持", ex);
      }

      RWSUtil.getRWSResultViaGET(url);
   }

   public void setCommentToBillItem(BillItem i, String comment) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {
      
      this.setCommentToBillItem(i.getId(), comment);
   }

   public List<BillItem> getAllBillItemsFromBill(Bill b) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (b == null) {
         throw new RemoteBusinessLogicException("账单信息不能为空");
      }

      if (b.getId() == null) {
         throw new RemoteBusinessLogicException("账单ID不能为空");
      }

      String url = null;
      url = RWSURLHolder.getBaseURL()
           + RWSURLHolder.BILL_GET_ALL_BILL_ITEMS_FROM_BILL
           + "/"
           + b.getId().longValue();

      JSONArray jsonArray = RWSUtil.getRWSResultViaGET(url);

      if (jsonArray == null) {
         return null;
      }

      JsonBeanUtil.JSONArrayExtractor<BillItem> extractor
              = new JsonBeanUtil.JSONArrayExtractor<BillItem>(jsonArray) {

         @Override
         protected BillItem getInstance() {
            return new BillItem();
         }
      };

      return extractor.extract();
   }

   public void hastenFoodInBill(BillItem i) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (i == null) {
         throw new RemoteBusinessLogicException("账单条目信息不能为空");
      }

      if (i.getId() == null) {
         throw new RemoteBusinessLogicException("账单条目ID不能为空");
      }

      String url = null;
      url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.BILL_HASTEN_FOOD_IN_BILL
              + "/"
              + i.getId().longValue();

      RWSUtil.getRWSResultViaGET(url);
   }

   public void checkoutBill(Long id) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (id == null) {
         throw new RemoteBusinessLogicException("账单ID不能为空");
      }

      String url = null;
      url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.BILL_CHECKOUT_BILL
              + "/"
              + id.longValue();

      RWSUtil.getRWSResultViaGET(url);
   }

   public void checkoutBill(Bill bill) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {
      
      this.checkoutBill(bill.getId());
   }

   public Bill getBillInformation(Long id) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (id == null) {
         throw new RemoteBusinessLogicException("账单ID不能为空");
      }

      String url = null;
      url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.BILL_GET_BILL
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

      Bill bill = new Bill();
      JsonBeanUtil.jsonObjectToBean(jsonObject, bill);

      return bill;
   }

   public Bill getBillInformation(Bill bill) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {
      
      return this.getBillInformation(bill.getId());
   }

   public Bill getBillInformation(BillItem i) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (i == null) {
         throw new RemoteBusinessLogicException("账单条目信息不能为空");
      }

      if (i.getId() == null) {
         throw new RemoteBusinessLogicException("账单条目ID不能为空");
      }

      String url = null;
      url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.BILL_GET_BILL_INFORMATION_FROM_BILL_ITEM
              + "/"
              + i.getId().longValue();

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

      Bill bill = new Bill();
      JsonBeanUtil.jsonObjectToBean(jsonObject, bill);

      return bill;
   }

   public BillItem getBillItemInformation(Long id) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (id == null) {
         throw new RemoteBusinessLogicException("账单条目ID不能为空");
      }
      
      String url = null;
      url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.BILL_GET_BILL_ITEM
              + "/"
              + id;

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

      BillItem billItem = new BillItem();
      JsonBeanUtil.jsonObjectToBean(jsonObject, billItem);

      return billItem;
   }

   public BillItem getBillItemInformation(BillItem item) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {
      
      return this.getBillItemInformation(item.getId());
   }

   public List<Bill> getCurrentBillFromSalePlace(Long salePlaceId) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {
      
      if (salePlaceId == null) {
         throw new RemoteBusinessLogicException("餐桌ID不能为空");
      }

      String url = null;
      url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.BILL_GET_CURRENT_BILL_FROM_SALE_PLACE
              + "/"
              + salePlaceId.longValue();

      JSONArray jsonArray = RWSUtil.getRWSResultViaGET(url);

      if (jsonArray == null) {
         return null;
      }

      JsonBeanUtil.JSONArrayExtractor<Bill> extractor
              = new JsonBeanUtil.JSONArrayExtractor<Bill>(jsonArray) {

         @Override
         protected Bill getInstance() {
            return new Bill();
         }
      };

      return extractor.extract();
   }

   public List<Bill> getCurrentBillFromSalePlace(SalePlace salePlace) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {
      
      return this.getCurrentBillFromSalePlace(salePlace.getId());
   }

   public Long getCurrentBillCountFromSalePlace(Long salePlaceId) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (salePlaceId == null) {
         throw new RemoteBusinessLogicException("餐桌ID不能为空");
      }

      String url = null;
      url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.BILL_GET_CURRENT_BILL_COUNT_FROM_SALE_PLACE
              + "/"
              + salePlaceId.longValue();

      JSONArray resultArray = RWSUtil.getRWSResultViaGET(url);

      if (resultArray == null) {
         return null;
      }

      if (resultArray.length() == 0) {
         return null;
      }

      Long currentBillCount = null;

      try {
         currentBillCount = resultArray.getLong(0);
      }catch(JSONException ex) {
         throw new RemoteInformationException("服务器返回的结果格式不正确", ex);
      }

      if (currentBillCount == null) {
         throw new RemoteInformationException("服务器返回的账单数量不正确");
      }

      return currentBillCount;
   }

   public Long getCurrentBillCountFromSalePlace(SalePlace salePlace) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {
      
      return this.getCurrentBillCountFromSalePlace(salePlace.getId());
   }

   public BigDecimal calculateBillPrice(Bill b) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (b == null) {
         throw new RemoteBusinessLogicException("账单信息不能为空");
      }

      if (b.getId() == null) {
         throw new RemoteBusinessLogicException("账单ID不能为空");
      }

      String url = null;
      url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.BILL_CALCULATE_BILL_PRICE
              + "/"
              + b.getId().longValue();

      JSONArray resultArray = RWSUtil.getRWSResultViaGET(url);

      if (resultArray == null) {
         return null;
      }

      if (resultArray.length() == 0) {
         return null;
      }

      BigDecimal billPrice = null;

      try {
         double billPriceDouble = resultArray.getDouble(0);
         billPrice = new BigDecimal(billPriceDouble);
      }catch(JSONException ex) {
         throw new RemoteInformationException("服务器返回的结果格式不正确", ex);
      }

      if (billPrice == null) {
         throw new RemoteInformationException("服务器返回的账单价格不正确");
      }

      return billPrice;
   }

   public Long calculateBillItemHastenCount(Long billItemId) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (billItemId == null) {
         throw new RemoteBusinessLogicException("账单条目ID不能为空");
      }

      String url = null;
      url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.BILL_CALCULATE_BILL_ITEM_HASTEN_COUNT
              + "/"
              + billItemId.longValue();

      JSONArray resultArray = RWSUtil.getRWSResultViaGET(url);

      if (resultArray == null) {
         return null;
      }

      if (resultArray.length() == 0) {
         return null;
      }

      Long hastenCount = null;

      try {
         hastenCount = resultArray.getLong(0);
      }catch(JSONException ex) {
         throw new RemoteInformationException("服务器返回的结果格式不正确", ex);
      }

      if (hastenCount == null) {
         throw new RemoteInformationException("服务器返回的催菜次数不正确");
      }

      return hastenCount;
   }

   public Long calculateBillItemHastenCount(BillItem i) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {
      
      return this.calculateBillItemHastenCount(i.getId());
   }
}
