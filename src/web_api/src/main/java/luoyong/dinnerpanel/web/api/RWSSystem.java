package luoyong.dinnerpanel.web.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import luoyong.dinnerpanel.rwscommon.util.RWSUtil;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
@Path("system")
public class RWSSystem {

   public RWSSystem() {
   }

   @Path("get-protocol-version")
   @Produces("text/plain")
   @GET
   public String getProtocolVersion() {
      return "1.0";
   }

   @Path("customer/test-login-customer")
   @Produces("application/json")
   @GET
   public String testLoginCustomer() {
      JSONObject result = new JSONObject();
      JSONArray resultArray = new JSONArray();
      resultArray.put("success");
      RWSUtil.setJsonObjectResult(result, 0, resultArray);
      return result.toString();
   }

   @Path("operator/test-login-operator")
   @Produces("application/json")
   @GET
   public String testLoginOperator() {
      JSONObject result = new JSONObject();
      JSONArray resultArray = new JSONArray();
      resultArray.put("success");
      RWSUtil.setJsonObjectResult(result, 0, resultArray);
      return result.toString();
   }

   @Path("kitchen/test-login-kitchen")
   @Produces("application/json")
   @GET
   public String testLoginKitchen() {
      JSONObject result = new JSONObject();
      JSONArray resultArray = new JSONArray();
      resultArray.put("success");
      RWSUtil.setJsonObjectResult(result, 0, resultArray);
      return result.toString();
   }

   @Path("clerk/test-login-clerk")
   @Produces("application/json")
   @GET
   public String testLoginClerk() {
      JSONObject result = new JSONObject();
      JSONArray resultArray = new JSONArray();
      resultArray.put("success");
      RWSUtil.setJsonObjectResult(result, 0, resultArray);
      return result.toString();
   }

   @Path("manager/test-login-manager")
   @Produces("application/json")
   @GET
   public String testLoginManager() {
      JSONObject result = new JSONObject();
      JSONArray resultArray = new JSONArray();
      resultArray.put("success");
      RWSUtil.setJsonObjectResult(result, 0, resultArray);
      return result.toString();
   }

   @Path("admin/test-login-admin")
   @Produces("application/json")
   @GET
   public String testLoginAdmin() {
      JSONObject result = new JSONObject();
      JSONArray resultArray = new JSONArray();
      resultArray.put("success");
      RWSUtil.setJsonObjectResult(result, 0, resultArray);
      return result.toString();
   }
}
