package luoyong.dinnerpanel.web.api;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import luoyong.dinnerpanel.dao.model.SalePlace;
import luoyong.dinnerpanel.service.SalePlaceManagement;
import luoyong.dinnerpanel.rwscommon.util.JsonBeanUtil;
import luoyong.dinnerpanel.rwscommon.util.RWSUtil;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
@Path("sale-place")
public class RWSSalePlace {

   private SalePlaceManagement salePlaceManagement = null;

   public RWSSalePlace() {
      salePlaceManagement = new SalePlaceManagement();
   }

   @Path("operator/list-all")
   @Produces("application/json")
   @GET
   public String getAllSalePlaces() {

      JSONObject result = new JSONObject();

      JSONArray resultArray = new JSONArray();
      
      List<SalePlace> salePlaceList = salePlaceManagement.getAllSalePlaces();

      if (salePlaceList == null) {
         // Return empty result.
         RWSUtil.setJsonObjectResult(result, 0, resultArray);
         return result.toString();
      }

      JSONObject salePlaceJsonObject = null;
      
      for (SalePlace salePlace : salePlaceList) {

         if ((salePlace != null) && (salePlace.getId() != null)) {

            salePlaceJsonObject = JsonBeanUtil.beanToJsonObject(salePlace);
            
            if (salePlaceJsonObject != null) {
               resultArray.put(salePlaceJsonObject);
            }
         }
      }
      
      RWSUtil.setJsonObjectResult(result, 0, resultArray);
      return result.toString();
   }

   @Path("manager/add-sale-place")
   @Consumes("application/json")
   @Produces("application/json")
   @POST
   public String addSalePlace(String salePlaceInfo) {

      JSONObject result = new JSONObject();

      if ((salePlaceInfo == null) || (salePlaceInfo.trim().length() < 1)) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "上传的餐桌信息不能为空");
         return result.toString();
      }

      JSONObject salePlaceJsonObject = null;
      try {
         salePlaceJsonObject = new JSONObject(salePlaceInfo);
      }catch(Throwable t) {
         t.printStackTrace(System.err);
      }

      if (salePlaceJsonObject == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "上传的餐桌信息格式不正确");
         return result.toString();
      }

      SalePlace salePlace = new SalePlace();
      JsonBeanUtil.jsonObjectToBean(salePlaceJsonObject, salePlace);

      salePlaceManagement.addSalePlace(salePlace);

      RWSUtil.setJsonObjectReturnCode(result, 0);
      return result.toString();
   }

   @Path("manager/update-sale-place")
   @Consumes("application/json")
   @Produces("application/json")
   @POST
   public String updateSalePlace(String salePlaceInfo) {

      JSONObject result = new JSONObject();

      if ((salePlaceInfo == null) || (salePlaceInfo.trim().length() < 1)) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "上传的餐桌信息不能为空");
         return result.toString();
      }

      JSONObject salePlaceJsonObject = null;
      try {
         salePlaceJsonObject = new JSONObject(salePlaceInfo);
      }catch(Throwable t) {
         t.printStackTrace(System.err);
      }

      if (salePlaceJsonObject == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "上传的餐桌信息格式不正确");
         return result.toString();
      }

      SalePlace salePlace = new SalePlace();
      JsonBeanUtil.jsonObjectToBean(salePlaceJsonObject, salePlace);

      SalePlace salePlaceInSystem = salePlaceManagement.getSalePlace(salePlace);
      if (salePlaceInSystem == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "所要更新的餐桌信息不存在");
         return result.toString();
      }

      salePlaceManagement.updateSalePlace(salePlace);

      RWSUtil.setJsonObjectReturnCode(result, 0);
      return result.toString();
   }

   @Path("manager/remove-sale-place/{sale-place-id}")
   @Produces("application/json")
   @GET
   public String removeSalePlace(
           @PathParam("sale-place-id") String salePlaceIdString) {

      JSONObject result = new JSONObject();

      if ((salePlaceIdString ==  null)
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

      salePlaceManagement.removeSalePlace(salePlaceId);

      RWSUtil.setJsonObjectReturnCode(result, 0);
      return result.toString();
   }

   @Path("customer/get-sale-place/{sale-place-id}")
   @Produces("application/json")
   @GET
   public String getSalePlace(
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
         RWSUtil.setJsonObjectErrorMessage(result, 1, "餐桌ID不是合法整数");
         return result.toString();
      }

      SalePlace salePlace = salePlaceManagement.getSalePlace(salePlaceId);

      if (salePlace == null) {
         RWSUtil.setJsonObjectResult(result, 0, resultArray);
         return result.toString();
      }

      JSONObject salePlaceJsonObject
              =  JsonBeanUtil.beanToJsonObject(salePlace);
      if (salePlaceJsonObject == null) {
         RWSUtil.setJsonObjectResult(result, 0, resultArray);
         return result.toString();
      }else {
         // Return sale place information in json form.
         resultArray.put(salePlaceJsonObject);
         RWSUtil.setJsonObjectResult(result, 0, resultArray);
         return result.toString();
      }
   }
}
