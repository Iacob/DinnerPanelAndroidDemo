package luoyong.dinnerpanel.web.api;

import java.util.Iterator;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import luoyong.dinnerpanel.dao.model.SalePlace;
import luoyong.dinnerpanel.service.SalePlaceManagement;
import org.json.JSONArray;
import org.json.JSONException;
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

   @Path("list-all")
   @Produces("application/json")
   @GET
   public String listAll() {
      List<SalePlace> salePlaceList = salePlaceManagement.getAllSalePlaces();
      JSONArray resultArray = new JSONArray();

      if (salePlaceList == null) {
         // Return an empty json array.
         return resultArray.toString();
      }

      for (SalePlace salePlace : salePlaceList) {

         if ((salePlace != null) && (salePlace.getId() != null)) {
            try {
               resultArray.put(new JSONObject()
                       .put("id", salePlace.getId())
                       .put("name", salePlace.getName())
                       .put("description", salePlace.getDescription())
                       .put("saleSiteId",
                              (salePlace.getSaleSite()==null)?
                                 null:salePlace.getSaleSite().getId())
                       .put("serviceStatus",
                              (salePlace.getServiceStatus()==null)?
                                 null:salePlace.getServiceStatus().name())
                       .put("status", (salePlace.getStatus()==null)?
                          null:salePlace.getStatus().name())
                       .put("type", salePlace.getType()));
            }catch(JSONException ex) {
               ex.printStackTrace(System.err);
            }
         }
      }
      return resultArray.toString();
   }

   
}
