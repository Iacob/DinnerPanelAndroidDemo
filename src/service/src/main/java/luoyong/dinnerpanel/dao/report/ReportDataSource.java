package luoyong.dinnerpanel.dao.report;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import luoyong.dinnerpanel.dao.EntityManagerBuilder;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import org.apache.commons.beanutils.PropertyUtils;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class ReportDataSource implements JRDataSource {

   private EntityManager em = null;

   private String queryString = null;
   private Object paramters[] = null;

   private Iterator<Object> resultIterator = null;

   private Object currentObject = null;

   public ReportDataSource(String queryString, Object ... parameters) {
      // Get the query string.
      this.queryString = queryString;
      this.paramters = parameters;
   }

   public void startQuery() {
      if (em == null) {
         em = EntityManagerBuilder.buildEntityManager();
      }
      Query query = em.createQuery(this.queryString);
      // Set parameters.
      if (this.paramters != null) {
         int currentParamIndex = 0;
         for (Object param : this.paramters) {
            query.setParameter(currentParamIndex, param);
            currentParamIndex++;
         }
      }
      List<Object> resultList = query.getResultList();
      this.resultIterator = resultList.iterator();
   }
   
   @Override
   public boolean next() throws JRException {
      if (this.resultIterator.hasNext()) {
         this.currentObject = this.resultIterator.next();
         return true;
      }else {
         this.currentObject = null;
         return false;
      }
   }
   
   @Override
   public Object getFieldValue(JRField jrField) throws JRException {
      try {
         if (jrField == null) {
            return null;
         }
         if (jrField.getName() == null) {
            return null;
         }
         String fieldName = jrField.getName();
         String[] propNames = fieldName.split("\\.");
         if ((propNames == null) && (propNames.length == 0)) {
            return null;
         }
         Object currentProp = null;
         String currentPropName = null;
         currentPropName = propNames[0];
         if (currentPropName == null) {
            return null;
         }
         Pattern patternArray = Pattern.compile("^\\[([0-9]+)\\$]");
         Pattern patternIndexedProp = Pattern.compile("^(.+)\\[([0-9]+)\\]$");
         Matcher matcherArray = patternArray.matcher(currentPropName);
         Matcher matcherProp = null;
         if (matcherArray.find()) {
            int indexArray = Integer.parseInt(matcherArray.group(1));
            currentProp = ((Object[])this.currentObject)[indexArray];
            if (currentProp == null) {
               return null;
            }
         }else {
            matcherProp = patternIndexedProp.matcher(currentPropName);
            if (matcherProp.find()) {
               currentProp = PropertyUtils.getProperty(this.currentObject,
                       matcherProp.group(1));
               if (currentProp == null) {
                  return null;
               }
               int indexArray = Integer.parseInt(matcherProp.group(2));
               currentProp = ((Object[])currentProp)[indexArray];
               if (currentProp == null) {
                  return null;
               }
            } else {
               currentProp = PropertyUtils.getProperty(this.currentObject,
                       currentPropName);
               if (currentProp == null) {
                  return null;
               }
            }
         }


         for (int i=1; i<propNames.length; i++) {

            currentPropName = propNames[i];
            if (currentPropName == null) {
               return null;
            }
            
            matcherProp = patternIndexedProp.matcher(currentPropName);
            if (matcherProp.find()) {
               currentProp = PropertyUtils.getProperty(currentProp,
                       matcherProp.group(1));
               if (currentProp == null) {
                  return null;
               }
               int indexArray = Integer.parseInt(matcherProp.group(2));
               currentProp = ((Object[])currentProp)[indexArray];
               if (currentProp == null) {
                  return null;
               }
            } else {
               currentProp = PropertyUtils.getProperty(currentProp,
                       currentPropName);
               if (currentProp == null) {
                  return null;
               }
            }
         }
         return currentProp;
      } catch (IllegalAccessException ex) {
         ex.printStackTrace(System.err);
      } catch (InvocationTargetException ex) {
         ex.printStackTrace(System.err);
      } catch (NoSuchMethodException ex) {
         ex.printStackTrace(System.err);
      }
      // Return null if cannot get specified property.
      return null;
   }

   public void close() {
      try{
         em.close();
      }catch(Throwable t) {}
   }

}
