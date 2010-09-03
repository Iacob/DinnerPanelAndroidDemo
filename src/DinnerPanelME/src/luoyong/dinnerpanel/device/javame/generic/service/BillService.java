package luoyong.dinnerpanel.device.javame.generic.service;

import java.io.IOException;
import luoyong.dinnerpanel.device.javame.baseurl.BaseURLHolder;
import luoyong.dinnerpanel.device.javame.generic.model.Bill;
import luoyong.dinnerpanel.device.javame.generic.model.BillItem;
import luoyong.dinnerpanel.device.javame.generic.model.SalePlace;
import luoyong.dinnerpanel.device.javame.generic.util.JsonMEUtil;
import luoyong.dinnerpanel.device.javame.generic.util.URLResponse;
import luoyong.dinnerpanel.device.javame.generic.util.URLUtil;
import luoyong.toolbox.json.me.ByteCache;
import luoyong.toolbox.json.me.ByteHolder;
import luoyong.toolbox.json.me.JsonArray;
import luoyong.toolbox.json.me.JsonObject;
import luoyong.toolbox.json.me.JsonParser;
import luoyong.toolbox.json.me.JsonValue;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class BillService {

   public Bill[] getCurrentBillFromSalePlace(Long id)
           throws RemoteConnectionException, RemoteInformationFormatException {

      String path = "/bill/get-current-bill-from-sale-place/" + id;

      URLResponse urlResponse = null;

      try {
         urlResponse = URLUtil.readURLAsStringUseCredential(
                 BaseURLHolder.getBaseURL() + path);
      }catch(IOException ex) {
         throw new RemoteConnectionException("Connection error.");
      }

      if ((urlResponse == null) || (urlResponse.getByteContent() == null)) {
         throw new RemoteConnectionException(
                 "Remote server does not return anything.");
      }

      ByteHolder jsonByteHolder = new ByteCache(urlResponse.getByteContent());

      JsonValue jsonValue = null;
      try {
         jsonValue = JsonParser.parseJsonText(jsonByteHolder);
      } catch (Throwable ex) {
         throw new RemoteInformationFormatException(ex.getMessage());
      }

      if (!JsonMEUtil.isJsonArray(jsonValue)) {
         throw new RemoteInformationFormatException(
                 "Remote did not return array result.");
      }

      JsonArray resultJsonArray = (JsonArray)jsonValue;
      int resultJsonArraySize = resultJsonArray.getSize();
      if (resultJsonArraySize < 1) {
         return null;
      }

      Bill result[] = new Bill[resultJsonArraySize];
      for (int i=0; i<resultJsonArraySize; i++) {

         JsonValue value = resultJsonArray.get(i);

         if (JsonMEUtil.isJsonObject(value)) {
            // Prepair new sale place object.
            Bill bill = new Bill();
            // Prepair sale place in json format.
            JsonObject objValue = (JsonObject)value;
            bill.setId(JsonMEUtil.getLong(objValue.get("id")));
            bill.setPrice(JsonMEUtil.getDouble(objValue.get("price")));
            bill.setStatus(JsonMEUtil.getString(objValue.get("status")));
            bill.setComment(JsonMEUtil.getString(objValue.get("comment")));

            result[i] = bill;
         }else {
            result[i] = null;
         }
      }

      return result;
   }

   public Bill[] getCurrentBillFromSalePlace(SalePlace s)
           throws RemoteConnectionException, RemoteInformationFormatException {
      
      return this.getCurrentBillFromSalePlace(s.getId());
   }

   public Bill getBillInformation(Long id)
           throws RemoteConnectionException, RemoteInformationFormatException {
      
      String path = "/bill/get-bill/" + id;

      URLResponse urlResponse = null;

      try {
         urlResponse = URLUtil.readURLAsStringUseCredential(
                 BaseURLHolder.getBaseURL() + path);
      }catch(IOException ex) {
         throw new RemoteConnectionException("Connection error.");
      }

      if ((urlResponse == null) || (urlResponse.getByteContent() == null)) {
         throw new RemoteConnectionException(
                 "Remote server does not return anything.");
      }

      ByteHolder jsonByteHolder = new ByteCache(urlResponse.getByteContent());

      JsonValue jsonValue = null;
      try {
         jsonValue = JsonParser.parseJsonText(jsonByteHolder);
      } catch (Throwable ex) {
         throw new RemoteInformationFormatException(ex.getMessage());
      }

      if (!JsonMEUtil.isJsonArray(jsonValue)) {
         throw new RemoteInformationFormatException(
                 "Remote did not return array result.");
      }

      JsonArray resultJsonArray = (JsonArray)jsonValue;
      int resultJsonArraySize = resultJsonArray.getSize();
      if (resultJsonArraySize < 1) {
         return null;
      }
      // More than one result is not allowed.
      if (resultJsonArraySize > 1) {
         throw new RemoteInformationFormatException(
                 "Return more than one result is not allowed here.");
      }

      JsonValue value = resultJsonArray.get(0);
      // Prepair new sale place object.
      Bill bill = new Bill();

      if (JsonMEUtil.isJsonObject(value)) {

         // Prepair sale place in json format.
         JsonObject objValue = (JsonObject) value;
         bill.setId(JsonMEUtil.getLong(objValue.get("id")));
         bill.setPrice(JsonMEUtil.getDouble(objValue.get("price")));
         bill.setStatus(JsonMEUtil.getString(objValue.get("status")));
         bill.setComment(JsonMEUtil.getString(objValue.get("comment")));

         return bill;
      } else {

         return null;
      }
   }

   public Bill getBillInformation(Bill b)
           throws RemoteConnectionException, RemoteInformationFormatException {
      
      return this.getBillInformation(b.getId());
   }

   public BillItem getBillItemInformation(Long id)
           throws RemoteConnectionException, RemoteInformationFormatException {

      String path = "/bill/get-bill-item/" + id;

      URLResponse urlResponse = null;

      try {
         urlResponse = URLUtil.readURLAsStringUseCredential(
                 BaseURLHolder.getBaseURL() + path);
      }catch(IOException ex) {
         throw new RemoteConnectionException("Connection error.");
      }

      if ((urlResponse == null) || (urlResponse.getByteContent() == null)) {
         throw new RemoteConnectionException(
                 "Remote server does not return anything.");
      }

      ByteHolder jsonByteHolder = new ByteCache(urlResponse.getByteContent());

      JsonValue jsonValue = null;
      try {
         jsonValue = JsonParser.parseJsonText(jsonByteHolder);
      } catch (Throwable ex) {
         throw new RemoteInformationFormatException(ex.getMessage());
      }

      if (!JsonMEUtil.isJsonArray(jsonValue)) {
         throw new RemoteInformationFormatException(
                 "Remote did not return array result.");
      }

      JsonArray resultJsonArray = (JsonArray)jsonValue;
      int resultJsonArraySize = resultJsonArray.getSize();
      if (resultJsonArraySize < 1) {
         return null;
      }
      // More than one result is not allowed.
      if (resultJsonArraySize > 1) {
         throw new RemoteInformationFormatException(
                 "Return more than one result is not allowed here.");
      }

      JsonValue value = resultJsonArray.get(0);
      // Prepair new sale place object.
      BillItem billItem = new BillItem();

      if (JsonMEUtil.isJsonObject(value)) {

         // Prepair sale place in json format.
         JsonObject objValue = (JsonObject) value;
         

         billItem.setId(JsonMEUtil.getLong(objValue.get("id")));
         // billItem.setAddedTime(); // Ignore added time.
         billItem.setFoodId(JsonMEUtil.getLong(objValue.get("foodId")));
         billItem.setFoodName(JsonMEUtil.getString(objValue.get("foodName")));
         billItem.setFoodCount(
                 JsonMEUtil.getInteger(objValue.get("foodCount")));
         billItem.setPrice(JsonMEUtil.getDouble(objValue.get("price")));
         billItem.setOperatorId(JsonMEUtil.getLong(objValue.get("operatorId")));
         billItem.setOperatorName(
                 JsonMEUtil.getString(objValue.get("operatorName")));
         billItem.setHastenCount(JsonMEUtil.getInteger(
                 objValue.get("hastenCount")));
         billItem.setComment(JsonMEUtil.getString(objValue.get("comment")));
         billItem.setStatus(JsonMEUtil.getString(objValue.get("status")));

         return billItem;
      } else {

         return null;
      }
   }

   public BillItem getBillItemInformation(BillItem i)
           throws RemoteConnectionException, RemoteInformationFormatException {

      return this.getBillItemInformation(i.getId());
   }

   /**
    * 
    * @param id Bill id.
    * @return
    */
   public BillItem[] getAllBillItemsFromBill(Long id)
           throws RemoteConnectionException, RemoteInformationFormatException {
      
      String path = "/bill/get-all-bill-items-from-bill/" + id;

      URLResponse urlResponse = null;

      try {
         urlResponse = URLUtil.readURLAsStringUseCredential(
                 BaseURLHolder.getBaseURL() + path);
      }catch(IOException ex) {
         throw new RemoteConnectionException("Connection error.");
      }

      if ((urlResponse == null) || (urlResponse.getByteContent() == null)) {
         throw new RemoteConnectionException(
                 "Remote server does not return anything.");
      }

      ByteHolder jsonByteHolder = new ByteCache(urlResponse.getByteContent());

      JsonValue jsonValue = null;
      try {
         jsonValue = JsonParser.parseJsonText(jsonByteHolder);
      } catch (Throwable ex) {
         throw new RemoteInformationFormatException(ex.getMessage());
      }

      if (!JsonMEUtil.isJsonArray(jsonValue)) {
         throw new RemoteInformationFormatException(
                 "Remote did not return array result.");
      }

      JsonArray resultJsonArray = (JsonArray)jsonValue;
      int resultJsonArraySize = resultJsonArray.getSize();
      if (resultJsonArraySize < 1) {
         return null;
      }

      BillItem result[] = new BillItem[resultJsonArraySize];
      for (int i=0; i<resultJsonArraySize; i++) {

         JsonValue value = resultJsonArray.get(i);

         if (JsonMEUtil.isJsonObject(value)) {
            // Prepair new sale place object.
            BillItem billItem = new BillItem();
            // Prepair sale place in json format.
            JsonObject objValue = (JsonObject)value;
            billItem.setId(JsonMEUtil.getLong(objValue.get("id")));
            billItem.setFoodId(JsonMEUtil.getLong(objValue.get("foodId")));
            billItem.setFoodName(
                    JsonMEUtil.getString(objValue.get("foodName")));
            billItem.setFoodCount(
                    JsonMEUtil.getInteger(objValue.get("foodCount")));
            billItem.setHastenCount(
                    JsonMEUtil.getInteger(objValue.get("hastenCount")));
            billItem.setPrice(JsonMEUtil.getDouble(objValue.get("price")));
            billItem.setComment(JsonMEUtil.getString(objValue.get("comment")));
            billItem.setStatus(JsonMEUtil.getString(objValue.get("status")));

            result[i] = billItem;
         }else {
            result[i] = null;
         }
      }

      return result;
   }

   public BillItem[] getAllBillItemsFromBill(Bill b)
           throws RemoteConnectionException, RemoteInformationFormatException {
      
      return this.getAllBillItemsFromBill(b.getId());
   }

   /**
    * 
    * @param id bill item id
    */
   public void cancelItemFromBill(Long id)
           throws RemoteConnectionException, RemoteInformationFormatException {
      
      String path = "/bill/cancel-item-from-bill/" + id;

      try {
         URLUtil.readURLAsStringUseCredential(
                 BaseURLHolder.getBaseURL() + path);
      }catch(IOException ex) {
         throw new RemoteConnectionException("Connection error.");
      }
   }

   public void cancelItemFromBill(BillItem i)
           throws RemoteConnectionException, RemoteInformationFormatException {
      
      this.cancelItemFromBill(i.getId());
   }

   /**
    * 
    * @param id bill item id
    */
   public void hastenFoodInBill(Long id)
           throws RemoteConnectionException, RemoteInformationFormatException {
      
      String path = "/bill/hasten-food-in-bill/" + id;

      try {
         URLUtil.readURLAsStringUseCredential(
                 BaseURLHolder.getBaseURL() + path);
      }catch(IOException ex) {
         throw new RemoteConnectionException("Connection error.");
      }
   }

   public void hastenFoodInBill(BillItem i)
           throws RemoteConnectionException, RemoteInformationFormatException {
      
      this.hastenFoodInBill(i.getId());
   }

   /**
    * 
    * @param id bill id
    */
   public void checkoutBill(Long id)
           throws RemoteConnectionException, RemoteInformationFormatException {
      
      String path = "/bill/checkout-bill/" + id;

      try {
         URLUtil.readURLAsStringUseCredential(
                 BaseURLHolder.getBaseURL() + path);
      }catch(IOException ex) {
         throw new RemoteConnectionException("Connection error.");
      }
   }

   public void checkoutBill(Bill b)
           throws RemoteConnectionException, RemoteInformationFormatException {
      
      this.checkoutBill(b.getId());
   }

   /**
    * 
    * @param id sale place id
    */
   public void makeBill(Long salePlaceId)
           throws RemoteConnectionException, RemoteInformationFormatException {
      
      String path = "/bill/make-bill-for-sale-place/" + salePlaceId;

      try {
         URLUtil.readURLAsStringUseCredential(
                 BaseURLHolder.getBaseURL() + path);
      }catch(IOException ex) {
         throw new RemoteConnectionException("Connection error.");
      }
   }

   public void makeBill(SalePlace s)
           throws RemoteConnectionException, RemoteInformationFormatException {
      
      this.makeBill(s.getId());
   }

   public void cancelBill(Long id)
           throws RemoteConnectionException, RemoteInformationFormatException {
      
      String path = "/bill/cancel-bill/" + id;

      try {
         URLUtil.readURLAsStringUseCredential(
                 BaseURLHolder.getBaseURL() + path);
      }catch(IOException ex) {
         throw new RemoteConnectionException("Connection error.");
      }
   }

   public void cancelBill(Bill b)
           throws RemoteConnectionException, RemoteInformationFormatException {
      
      this.cancelBill(b.getId());
   }

   public int getCurrentBillCountFromSalePlace(Long salePlaceId)
           throws RemoteConnectionException, RemoteInformationFormatException {
      
      String path = "/bill/get-current-bill-count-from-sale-place/"
              + salePlaceId;

      URLResponse urlResponse = null;

      try {
         urlResponse = URLUtil.readURLAsStringUseCredential(
                 BaseURLHolder.getBaseURL() + path);
      }catch(IOException ex) {
         throw new RemoteConnectionException("Connection error.");
      }

      if ((urlResponse == null) || (urlResponse.getByteContent() == null)) {
         throw new RemoteConnectionException(
                 "Remote server does not return anything.");
      }

      ByteHolder jsonByteHolder = new ByteCache(urlResponse.getByteContent());

      JsonValue jsonValue = null;
      try {
         jsonValue = JsonParser.parseJsonText(jsonByteHolder);
      } catch (Throwable ex) {
         throw new RemoteInformationFormatException(ex.getMessage());
      }

      if (!JsonMEUtil.isJsonArray(jsonValue)) {
         throw new RemoteInformationFormatException(
                 "Remote did not return array result.");
      }

      JsonArray resultJsonArray = (JsonArray)jsonValue;
      int resultJsonArraySize = resultJsonArray.getSize();
      if (resultJsonArraySize < 1) {
         return 0;
      }
      // More than one result is not allowed.
      if (resultJsonArraySize > 1) {
         throw new RemoteInformationFormatException(
                 "Return more than one result is not allowed here.");
      }

      JsonValue value = resultJsonArray.get(0);
      // Prepair new sale place object.

      Double resultDouble = JsonMEUtil.getDouble(value);
      if (resultDouble == null) {
         return 0;
      }else {
         return resultDouble.intValue();
      }
   }

   public int getCurrentBillCountFromSalePlace(SalePlace s)
           throws RemoteConnectionException, RemoteInformationFormatException {
      
      return this.getCurrentBillCountFromSalePlace(s.getId());
   }

   public Double calculateBillPrice(Long id)
           throws RemoteConnectionException, RemoteInformationFormatException {

      String path = "/bill/calculate-bill-price/" + id;

      URLResponse urlResponse = null;

      try {
         urlResponse = URLUtil.readURLAsStringUseCredential(
                 BaseURLHolder.getBaseURL() + path);
      }catch(IOException ex) {
         throw new RemoteConnectionException("Connection error.");
      }

      if ((urlResponse == null) || (urlResponse.getByteContent() == null)) {
         throw new RemoteConnectionException(
                 "Remote server does not return anything.");
      }

      ByteHolder jsonByteHolder = new ByteCache(urlResponse.getByteContent());

      JsonValue jsonValue = null;
      try {
         jsonValue = JsonParser.parseJsonText(jsonByteHolder);
      } catch (Throwable ex) {
         throw new RemoteInformationFormatException(ex.getMessage());
      }

      if (!JsonMEUtil.isJsonArray(jsonValue)) {
         throw new RemoteInformationFormatException(
                 "Remote did not return array result.");
      }

      JsonArray resultJsonArray = (JsonArray)jsonValue;
      int resultJsonArraySize = resultJsonArray.getSize();
      if (resultJsonArraySize < 1) {
         return null;
      }
      // More than one result is not allowed.
      if (resultJsonArraySize > 1) {
         throw new RemoteInformationFormatException(
                 "Return more than one result is not allowed here.");
      }

      JsonValue value = resultJsonArray.get(0);
      // Prepair new sale place object.

      Double resultDouble = JsonMEUtil.getDouble(value);

      return resultDouble;
   }

   public Double calculateBillPrice(Bill b)
           throws RemoteConnectionException, RemoteInformationFormatException {
      
      return this.calculateBillPrice(b.getId());
   }
}
