package luoyong.dinnerpanel.web.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.json.JSONArray;

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
      JSONArray result = new JSONArray();
      result.put("success");
      return result.toString();
   }
}
