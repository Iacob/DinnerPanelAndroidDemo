package luoyong.dinnerpanel.android.rwsclient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import luoyong.dinnerpanel.android.model.Operator;
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
public class OperatorServiceClient {

   public void addOperator(Operator o) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (o == null) {
         throw new RemoteBusinessLogicException("操作员信息不能为空");
      }

      JSONObject jsonObject = JsonBeanUtil.beanToJsonObject(o);

      if (jsonObject == null) {
         throw new RemoteBusinessLogicException("上传的操作员信息不能为空");
      }

      String url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.OPERATOR_ADD_OPERATOR;

      RWSUtil.getRWSResultViaPOST(url, jsonObject.toString());
   }

   public void updateOperatorInformation(Operator o) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (o == null) {
         throw new RemoteBusinessLogicException("操作员信息不能为空");
      }

      JSONObject jsonObject = JsonBeanUtil.beanToJsonObject(o);

      if (jsonObject == null) {
         throw new RemoteBusinessLogicException("上传的操作员信息不能为空");
      }

      String url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.OPERATOR_UPDATE_OPERATOR_INFORMATION;

      RWSUtil.getRWSResultViaPOST(url, jsonObject.toString());
   }

   public void removeOperatorInformation(String id) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (id == null) {
         throw new RemoteBusinessLogicException("操作员ID不能为空");
      }

      String url = null;
      try {
         url = RWSURLHolder.getBaseURL()
                 + RWSURLHolder.OPERATOR_REMOVE_OPERATOR_INFORMATION
                 + "/"
                 + URLEncoder.encode(id, "UTF-8");
      } catch (UnsupportedEncodingException ex) {
         throw new RemoteInformationException("字符编码系统不支持", ex);
      }

      RWSUtil.getRWSResultViaGET(url);
   }

   public void removeOperatorInformation(Operator o) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (o == null) {
         throw new RemoteBusinessLogicException("操作员信息不能为空");
      }

      this.removeOperatorInformation(o.getId());
   }

   public Operator getOperatorInformation(String id) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (id == null) {
         throw new RemoteBusinessLogicException("操作员ID不能为空");
      }

      String url = null;
      try {
         url = RWSURLHolder.getBaseURL()
                 + RWSURLHolder.OPERATOR_GET_OPERATOR_INFORMATION
                 + "/"
                 + URLEncoder.encode(id, "UTF-8");
      } catch (UnsupportedEncodingException ex) {
         throw new RemoteInformationException("字符编码系统不支持", ex);
      }

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

      Operator operator = new Operator();
      JsonBeanUtil.jsonObjectToBean(jsonObject, operator);

      return operator;
   }

   public Operator getOperatorInformation(Operator o) throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      if (o == null) {
         throw new RemoteBusinessLogicException("操作员信息不能为空");
      }

      return this.getOperatorInformation(o.getId());
   }

   public List<Operator> getAllOperatorInformation() throws
           RemoteConnectionException,
           RemoteAuthorizationException,
           RemoteInformationException,
           RemoteBusinessLogicException {

      String url = RWSURLHolder.getBaseURL()
              + RWSURLHolder.OPERATOR_GET_ALL_OPERATOR_INFORMATION;

      JSONArray jsonArray = RWSUtil.getRWSResultViaGET(url);

      if (jsonArray == null) {
         return null;
      }

      JsonBeanUtil.JSONArrayExtractor<Operator> extractor
              = new JsonBeanUtil.JSONArrayExtractor<Operator>(jsonArray) {

         @Override
         protected Operator getInstance() {
            return new Operator();
         }
      };

      return extractor.extract();
   }
}
