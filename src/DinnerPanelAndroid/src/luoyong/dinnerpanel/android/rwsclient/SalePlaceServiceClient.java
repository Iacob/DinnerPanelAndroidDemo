package luoyong.dinnerpanel.android.rwsclient;

import java.util.List;
import luoyong.dinnerpanel.android.model.SalePlace;
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
public class SalePlaceServiceClient {

   public void addSalePlace(SalePlace s) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (s == null) {
         throw new RemoteBusinessLogicException("餐桌信息不能为空");
      }

      JSONObject jsonObject = JsonBeanUtil.beanToJsonObject(s);

      if (jsonObject == null) {
         throw new RemoteBusinessLogicException("上传的餐桌信息不能为空");
      }

      String url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.SALE_PLACE_ADD_SALE_PLACE;

      RWSUtil.getRWSResultViaPOST(url, jsonObject.toString());
   }

   public void updateSalePlace(SalePlace s) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (s == null) {
         throw new RemoteBusinessLogicException("餐桌信息不能为空");
      }

      JSONObject jsonObject = JsonBeanUtil.beanToJsonObject(s);

      if (jsonObject == null) {
         throw new RemoteBusinessLogicException("上传的餐桌信息不能为空");
      }

      String url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.SALE_PLACE_UPDATE_SALE_PLACE;

      RWSUtil.getRWSResultViaPOST(url, jsonObject.toString());
   }

   public void removeSalePlace(Long id) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (id == null) {
         throw new RemoteBusinessLogicException("餐桌ID不能为空");
      }

      String url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.SALE_PLACE_REMOVE_SALE_PLACE
              + "/"
              + id.longValue();

      RWSUtil.getRWSResultViaGET(url);
   }

   public void removeSalePlace(SalePlace s) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (s == null) {
         throw new RemoteBusinessLogicException("餐桌信息不能为空");
      }

      this.removeSalePlace(s.getId());
   }

   public List<SalePlace> getAllSalePlaces() throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      String url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.SALE_PLACE_GET_ALL_SALE_PLACES;

      JSONArray jsonArray = RWSUtil.getRWSResultViaGET(url);

      if (jsonArray == null) {
         return null;
      }

      JsonBeanUtil.JSONArrayExtractor<SalePlace> extractor
              = new JsonBeanUtil.JSONArrayExtractor<SalePlace>(jsonArray) {

         @Override
         protected SalePlace getInstance() {
            return new SalePlace();
         }
      };

      return extractor.extract();
   }

   public SalePlace getSalePlace(Long id) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (id == null) {
         throw new RemoteBusinessLogicException("餐桌ID不能为空");
      }

      String url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.SALE_PLACE_GET_SALE_PLACE
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

      SalePlace salePlace = new SalePlace();
      JsonBeanUtil.jsonObjectToBean(jsonObject, salePlace);

      return salePlace;
   }

   public SalePlace getSalePlace(SalePlace s) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (s == null) {
         throw new RemoteBusinessLogicException("餐桌信息不能为空");
      }

      return this.getSalePlace(s.getId());
   }
}
