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
import luoyong.dinnerpanel.web.util.JsonBeanUtil;
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
      
      List<SalePlace> salePlaceList = salePlaceManagement.getAllSalePlaces();
      JSONArray resultArray = new JSONArray();

      if (salePlaceList == null) {
         // Return an empty json array.
         return resultArray.toString();
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
      
      return resultArray.toString();
   }

   @Path("manager/add-sale-place")
   @Consumes("application/json")
   @Produces("application/json")
   @POST
   public void addSalePlace(String salePlaceInfo) {

      if ((salePlaceInfo == null) || (salePlaceInfo.trim().length() < 1)) {
         return;
      }

      JSONObject salePlaceJsonObject = null;
      try {
         salePlaceJsonObject = new JSONObject(salePlaceInfo);
      }catch(Throwable t) {
         t.printStackTrace(System.err);
      }

      if (salePlaceJsonObject == null) {
         return;
      }

      SalePlace salePlace = new SalePlace();
      JsonBeanUtil.jsonToObject(salePlaceJsonObject, salePlace);

      salePlaceManagement.addSalePlace(salePlace);
   }

   @Path("manager/update-sale-place")
   @Consumes("application/json")
   @Produces("application/json")
   @POST
   public void updateSalePlace(String salePlaceInfo) {

      if ((salePlaceInfo == null) || (salePlaceInfo.trim().length() < 1)) {
         return;
      }

      JSONObject salePlaceJsonObject = null;
      try {
         salePlaceJsonObject = new JSONObject(salePlaceInfo);
      }catch(Throwable t) {
         t.printStackTrace(System.err);
      }

      if (salePlaceJsonObject == null) {
         return;
      }

      SalePlace salePlace = new SalePlace();
      JsonBeanUtil.jsonToObject(salePlaceJsonObject, salePlace);

      SalePlace salePlaceInSystem = salePlaceManagement.getSalePlace(salePlace);
      if (salePlaceInSystem == null) {
         return;
      }

      salePlaceManagement.updateSalePlace(salePlace);
   }

   @Path("manager/remove-sale-place/{sale-place-id}")
   @Produces("application/json")
   @GET
   public void removeSalePlace(
           @PathParam("sale-place-id") String salePlaceIdString) {

      if ((salePlaceIdString ==  null)
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

      salePlaceManagement.removeSalePlace(salePlaceId);
   }

   @Path("customer/get-sale-place/{sale-place-id}")
   @Produces("application/json")
   @GET
   public String getSalePlace(
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

      SalePlace salePlace = salePlaceManagement.getSalePlace(salePlaceId);

      if (salePlace == null) {
         return result.toString();
      }

      JSONObject salePlaceJsonObject
              =  JsonBeanUtil.beanToJsonObject(salePlace);
      if (salePlaceJsonObject == null) {
         return result.toString();
      }else {
         // Return sale place information in json form.
         result.put(salePlaceJsonObject);
         return result.toString();
      }
   }
}
