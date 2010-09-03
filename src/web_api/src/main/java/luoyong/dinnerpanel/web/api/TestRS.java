package luoyong.dinnerpanel.web.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
@Path("TestRS")
public class TestRS {

   @Path("test")
   @GET
   public String test() {
      return "Hello.";
   }
}
