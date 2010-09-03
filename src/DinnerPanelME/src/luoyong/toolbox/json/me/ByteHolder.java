package luoyong.toolbox.json.me;

import java.io.UnsupportedEncodingException;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public interface ByteHolder {

   

   public byte getNextByte() throws EOFException;

   public byte getCurrentByte() throws EOFException;

   /**
    * Begin cache next bytes that not read.
    */
   public void beginCache();

   /**
    * Cache the byte currently read and begin cache next bytes.
    */
   public void beginCacheAndCacheCurrentByte();

   /**
    * Stop cache bytes.
    */
   public void endCache();

   /**
    * Get cached bytes.
    * @return cached bytes
    */
   public byte[] getCachedBytes();
   
   /**
    * Get cached bytes in form string.
    * @param charset
    * @return
    * @throws UnsupportedEncodingException
    *    the character set specified in parameter is not supported.
    */
   public String getCachedBytesAsString(String charset)
           throws UnsupportedEncodingException;

   /**
    * Get cached bytes in String form without last character removed.
    * @param charset
    * @return
    * @throws UnsupportedEncodingException
    *    the character set specified in parameter is not supported.
    */
   public String getCachedBytesAsStringWithoutTrailing(String charset)
           throws UnsupportedEncodingException;
}
