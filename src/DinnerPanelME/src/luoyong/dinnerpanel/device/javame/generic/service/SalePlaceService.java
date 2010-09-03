package luoyong.dinnerpanel.device.javame.generic.service;

import java.io.IOException;
import luoyong.dinnerpanel.device.javame.baseurl.BaseURLHolder;
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
public class SalePlaceService {

   public SalePlace[] getAllSalePlaces()
           throws RemoteConnectionException, RemoteInformationFormatException {

      String path = "/sale-place/list-all";

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
      SalePlace result[] = new SalePlace[resultJsonArraySize];
      for (int i=0; i<resultJsonArraySize; i++) {
         
         JsonValue value = resultJsonArray.get(i);
         
         if (JsonMEUtil.isJsonObject(value)) {
            // Prepair new sale place object.
            SalePlace salePlace = new SalePlace();
            // Prepair sale place in json format.
            JsonObject objValue = (JsonObject)value;
            salePlace.setId(JsonMEUtil.getLong(objValue.get("id")));
            salePlace.setName(JsonMEUtil.getString(objValue.get("name")));
            salePlace.setDescription(
                    JsonMEUtil.getString(objValue.get("description")));
            salePlace.setServiceStatus(
                    JsonMEUtil.getString(objValue.get("serviceStatus")));
            salePlace.setStatus(JsonMEUtil.getString(objValue.get("status")));
            salePlace.setType(JsonMEUtil.getString(objValue.get("type")));

            result[i] = salePlace;
         }else {
            result[i] = null;
         }
      }

      return result;
   }
}
