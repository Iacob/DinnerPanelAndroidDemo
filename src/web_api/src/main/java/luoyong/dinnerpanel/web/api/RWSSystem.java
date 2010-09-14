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

   @Path("test-login")
   @Produces("application/json")
   @GET
   public String testLogin() {
      JSONObject result = new JSONObject();
      JSONArray resultArray = new JSONArray();
      resultArray.put("success");
      RWSUtil.setJsonObjectResult(result, 0, resultArray);
      return result.toString();
   }
}
