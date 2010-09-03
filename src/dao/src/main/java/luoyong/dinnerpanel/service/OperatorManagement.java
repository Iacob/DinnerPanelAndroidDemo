package luoyong.dinnerpanel.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import luoyong.dinnerpanel.dao.EntityManagerBuilder;
import luoyong.dinnerpanel.dao.model.ExistKey;
import luoyong.dinnerpanel.dao.model.Operator;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class OperatorManagement {

   public void addOperator(Operator operator) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         em.getTransaction().begin();
         operator.setEk(ExistKey.E);
         em.persist(operator);
         em.getTransaction().commit();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {
         }
      }
   }

   public void updateOperatorInformation(Operator operator) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         em.getTransaction().begin();
         em.merge(operator);
         em.getTransaction().commit();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {}
      }
   }

   public void removeOperatorInformation(String id) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         em.getTransaction().begin();
         Query query = em.createNamedQuery(
                 Operator.QUERY_REMOVE_OPERATOR_INFORMATION);
         query.setParameter(1, id);
         query.executeUpdate();
         em.getTransaction().commit();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {
         }
      }
   }

   public void removeOperatorInformation(Operator operator) {
      this.removeOperatorInformation(operator.getId());
   }

   public Operator getOperatorInformation(String id) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         Query query = em.createNamedQuery(
                 Operator.QUERY_GET_OPERATOR_INFORMATION);
         query.setParameter(1, id);
         List<Operator> operatorList = query.getResultList();
         if (operatorList != null) {
            for(Operator operator : operatorList) {
               return operator;
            }
         }
         return null;
      } finally {
         try {
            em.close();
         }catch(Throwable t) {}
      }
   }

   public Operator getOperatorInformation(Operator operator) {
      return this.getOperatorInformation(operator.getId());
   }

   public List<Operator> getAllOperatorInformation() {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         Query query = em.createNamedQuery(
                 Operator.QUERY_GET_ALL_OPERATOR_INFORMATION);
         return query.getResultList();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {
         }
      }
   }
}
