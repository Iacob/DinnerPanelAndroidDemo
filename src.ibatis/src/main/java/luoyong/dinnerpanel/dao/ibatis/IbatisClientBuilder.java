package luoyong.dinnerpanel.dao.ibatis;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import java.io.InputStream;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class IbatisClientBuilder {

   private static SqlMapClient sqlMapClient = null;

   public static SqlMapClient buildClient() {

      if (sqlMapClient == null) {

         InputStream inputStream = null;

         try {
            inputStream = Thread.currentThread().getClass().getResourceAsStream(
                    "/luoyong/dinner/dao/ibatis/config.xml");
            sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(inputStream);
            return sqlMapClient;
         } catch (Throwable t) {
            t.printStackTrace(System.err);
            return null;
         } finally {
            try {
               inputStream.close();
            } catch (Throwable t) {
            }
         }
      } else {
         return sqlMapClient;
      }
   }
}
