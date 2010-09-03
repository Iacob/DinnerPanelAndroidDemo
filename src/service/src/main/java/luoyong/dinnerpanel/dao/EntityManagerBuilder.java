package luoyong.dinnerpanel.dao;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class EntityManagerBuilder {

   private static EntityManagerFactory entityManagerFactory = null;

   public static EntityManagerFactory buildEntityManagerFactory(
           Map<String, String> propertiesMap) {

      if (entityManagerFactory != null) {
         entityManagerFactory.close();
      }

      entityManagerFactory = Persistence.createEntityManagerFactory(
              "dinner_panel_main_persistence_unit", propertiesMap);
      return entityManagerFactory;
   }

   public static EntityManager buildEntityManager() {
      if (entityManagerFactory == null) {
         HashMap<String, String> propertiesMap = new HashMap<String, String>();
         propertiesMap.put("javax.persistence.jdbc.url",
                 "jdbc:derby://localhost:1527/dinner_panel;create=true");
         propertiesMap.put("javax.persistence.jdbc.password", "admin");
         propertiesMap.put("javax.persistence.jdbc.driver",
                 "org.apache.derby.jdbc.ClientDriver");
         propertiesMap.put("javax.persistence.jdbc.user", "admin");

         buildEntityManagerFactory(propertiesMap);
      }
      return entityManagerFactory.createEntityManager();
   }
}
