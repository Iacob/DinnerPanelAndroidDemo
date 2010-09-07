package luoyong.dinnerpanel.web.api;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import luoyong.dinnerpanel.dao.model.Operator;
import luoyong.dinnerpanel.service.OperatorManagement;
import luoyong.dinnerpanel.web.util.JsonBeanUtil;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
@Path("operatorManagement")
public class RWSOperator {

   private OperatorManagement operatorManagement = null;

   public RWSOperator() {
      operatorManagement = new OperatorManagement();
   }

   @Path("admin/add-operator")
   @Consumes("application/json")
   @Produces("application/json")
   @POST
   public void addOperator(String operatorInfo) {

      if ((operatorInfo == null) || (operatorInfo.trim().length() < 1)) {
         return;
      }

      JSONObject operatorJsonObject = null;
      try {
         operatorJsonObject = new JSONObject(operatorInfo);
      }catch(Throwable t) {
         t.printStackTrace(System.err);
      }

      if (operatorJsonObject == null) {
         return;
      }

      Operator operator = new Operator();
      JsonBeanUtil.jsonToObject(operatorJsonObject, operator);

      operatorManagement.addOperator(operator);
   }

   @Path("admin/update-operator")
   @Consumes("application/json")
   @Produces("application/json")
   @POST
   public void updateOperator(String operatorInfo) {

      if ((operatorInfo == null) || (operatorInfo.trim().length() < 1)) {
         return;
      }

      JSONObject operatorJsonObject = null;
      try {
         operatorJsonObject = new JSONObject(operatorInfo);
      }catch(Throwable t) {
         t.printStackTrace(System.err);
      }

      if (operatorJsonObject == null) {
         return;
      }

      Operator operator = new Operator();
      JsonBeanUtil.jsonToObject(operatorJsonObject, operator);

      Operator operatorInSystem
              = operatorManagement.getOperatorInformation(operator);
      if (operatorInSystem == null) {
         return;
      }

      operatorManagement.updateOperatorInformation(operator);
   }

   @Path("admin/remove-operator-information/{operator-id}")
   @Produces("application/json")
   @GET
   public void removeOperator(
           @PathParam("operator-id") String operatorsId) {

      if ((operatorsId ==  null)
              || (operatorsId.trim().length() < 1)) {

         return;
      }

      operatorManagement.removeOperatorInformation(operatorsId);
   }

   @Path("admin/get-operator-information/{operator-id}")
   @Produces("application/json")
   @GET
   public String getOperatorInformation(
           @PathParam("operator-id") String operatorId) {

      JSONArray result = new JSONArray();

      if ((operatorId == null)
              || (operatorId.trim().length() < 1)) {

         return result.toString();
      }

      Operator operator = operatorManagement.getOperatorInformation(operatorId);

      if (operator == null) {
         return result.toString();
      }

      JSONObject operatorJsonObject
              =  JsonBeanUtil.beanToJsonObject(operator);
      if (operatorJsonObject == null) {
         return result.toString();
      }else {
         // Return operator information in json form.
         result.put(operatorJsonObject);
         return result.toString();
      }
   }

   @Path("admin/get-all-operator-information")
   @Produces("application/json")
   @GET
   public String getAllOperatorInformation() {

      List<Operator> operatorList
              = operatorManagement.getAllOperatorInformation();
      JSONArray resultArray = new JSONArray();

      if (operatorList == null) {
         // Return an empty json array.
         return resultArray.toString();
      }

      JSONObject operatorJsonObject = null;

      for (Operator operator : operatorList) {

         if ((operator != null) && (operator.getId() != null)) {

            operatorJsonObject = JsonBeanUtil.beanToJsonObject(operator);

            if (operatorJsonObject != null) {
               resultArray.put(operatorJsonObject);
            }
         }
      }

      return resultArray.toString();
   }
}
