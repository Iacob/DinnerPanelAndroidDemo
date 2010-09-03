package luoyong.dinnerpanel.device.javame.generic.util;

import java.io.UnsupportedEncodingException;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class URLResponse {

   public static final String DEFAULT_CHARSET = "UTF-8";

   private int responseCode = 0;
   private byte[] byteContent = null;

   public URLResponse() {
   }

   public URLResponse(int responseCode, byte[] byteContent) {
      this.responseCode = responseCode;
      this.byteContent = byteContent;
   }

   public byte[] getByteContent() {
      return this.byteContent;
   }

   public String getStringContent(String charset)
           throws UnsupportedEncodingException {
      
      return new String(byteContent, charset);
   }

   public int getResponseCode() {
      return responseCode;
   }

   public void setResponseCode(int responseCode) {
      this.responseCode = responseCode;
   }
}
