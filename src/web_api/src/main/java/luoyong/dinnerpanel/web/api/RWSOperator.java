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
import luoyong.dinnerpanel.rwscommon.util.JsonBeanUtil;
import luoyong.dinnerpanel.rwscommon.util.RWSUtil;
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
   public String addOperator(String operatorInfo) {

      JSONObject result = new JSONObject();

      if ((operatorInfo == null) || (operatorInfo.trim().length() < 1)) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "上传的操作员信息不能为空");
         return result.toString();
      }

      JSONObject operatorJsonObject = null;
      try {
         operatorJsonObject = new JSONObject(operatorInfo);
      }catch(Throwable t) {
         t.printStackTrace(System.err);
      }

      if (operatorJsonObject == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "上传的操作员信息不正确");
         return result.toString();
      }

      Operator operator = new Operator();
      JsonBeanUtil.jsonObjectToBean(operatorJsonObject, operator);

      operatorManagement.addOperator(operator);

      RWSUtil.setJsonObjectReturnCode(result, 0);
      return result.toString();
   }

   @Path("admin/update-operator")
   @Consumes("application/json")
   @Produces("application/json")
   @POST
   public String updateOperator(String operatorInfo) {

      JSONObject result = new JSONObject();

      if ((operatorInfo == null) || (operatorInfo.trim().length() < 1)) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "上传的操作员信息不能为空");
         return result.toString();
      }

      JSONObject operatorJsonObject = null;
      try {
         operatorJsonObject = new JSONObject(operatorInfo);
      }catch(Throwable t) {
         t.printStackTrace(System.err);
      }

      if (operatorJsonObject == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "上传的操作员信息格式不正确");
         return result.toString();
      }

      Operator operator = new Operator();
      JsonBeanUtil.jsonObjectToBean(operatorJsonObject, operator);

      Operator operatorInSystem
              = operatorManagement.getOperatorInformation(operator);
      if (operatorInSystem == null) {
         RWSUtil.setJsonObjectErrorMessage(result, 1, "所要更新的操作员信息不存在 ");
         return result.toString();
      }

      operatorManagement.updateOperatorInformation(operator);

      RWSUtil.setJsonObjectReturnCode(result, 0);
      return result.toString();
   }

   @Path("admin/remove-operator-information/{operator-id}")
   @Produces("application/json")
   @GET
   public String removeOperator(
           @PathParam("operator-id") String operatorId) {

      JSONObject result = new JSONObject();

      if ((operatorId ==  null)
              || (operatorId.trim().length() < 1)) {

         RWSUtil.setJsonObjectErrorMessage(result, 1, "操作员ID不能为空");
         return result.toString();
      }

      operatorManagement.removeOperatorInformation(operatorId);

      RWSUtil.setJsonObjectReturnCode(result, 0);
      return result.toString();
   }

   @Path("admin/get-operator-information/{operator-id}")
   @Produces("application/json")
   @GET
   public String getOperatorInformation(
           @PathParam("operator-id") String operatorId) {

      JSONObject result = new JSONObject();

      JSONArray resultArray = new JSONArray();

      if ((operatorId == null)
              || (operatorId.trim().length() < 1)) {

         RWSUtil.setJsonObjectErrorMessage(result, 1, "操作员ID不能为空");
         return result.toString();
      }

      Operator operator = operatorManagement.getOperatorInformation(operatorId);

      if (operator == null) {
         RWSUtil.setJsonObjectResult(result, 0, resultArray);
         return result.toString();
      }

      JSONObject operatorJsonObject
              =  JsonBeanUtil.beanToJsonObject(operator);
      if (operatorJsonObject == null) {
         RWSUtil.setJsonObjectResult(result, 0, resultArray);
         return result.toString();
      }else {
         // Return operator information in json form.
         resultArray.put(operatorJsonObject);
         RWSUtil.setJsonObjectResult(result, 0, resultArray);
         return result.toString();
      }
   }

   @Path("admin/get-all-operator-information")
   @Produces("application/json")
   @GET
   public String getAllOperatorInformation() {

      JSONObject result = new JSONObject();

      JSONArray resultArray = new JSONArray();

      List<Operator> operatorList
              = operatorManagement.getAllOperatorInformation();

      if (operatorList == null) {
         // Return empty result.
         RWSUtil.setJsonObjectResult(result, 0, resultArray);
         return result.toString();
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

      RWSUtil.setJsonObjectResult(result, 0, resultArray);
      return result.toString();
   }
}
