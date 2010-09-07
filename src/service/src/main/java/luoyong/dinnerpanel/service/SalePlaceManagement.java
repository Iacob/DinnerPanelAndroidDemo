package luoyong.dinnerpanel.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import luoyong.dinnerpanel.dao.EntityManagerBuilder;
import luoyong.dinnerpanel.dao.model.ExistKey;
import luoyong.dinnerpanel.dao.model.SalePlace;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class SalePlaceManagement {

   public void addSalePlace(SalePlace salePlace) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         em.getTransaction().begin();
         salePlace.setEk(ExistKey.E);
         salePlace.setId(null);
         em.persist(salePlace);
         em.getTransaction().commit();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {
         }
      }
   }

   public void updateSalePlace(SalePlace salePlace) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         em.getTransaction().begin();
         em.merge(salePlace);
         em.getTransaction().commit();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {
         }
      }
   }

   public void removeSalePlace(Long id) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         em.getTransaction().begin();
         Query query = em.createNamedQuery(SalePlace.QUERY_REMOVE_SALE_PLACE);
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

   public void removeSalePlace(SalePlace salePlace) {
      this.removeSalePlace(salePlace.getId());
   }

   public List<SalePlace> getAllSalePlaces() {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         Query query = em.createNamedQuery(SalePlace.QUERY_GET_ALL_SALE_PLACES);
         return query.getResultList();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {
         }
      }
   }

   public SalePlace getSalePlace(Long id) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         Query query = em.createNamedQuery(SalePlace.QUERY_GET_SALE_PLACE);
         query.setParameter(1, id);
         List<SalePlace> salePlaceList = query.getResultList();
         for (SalePlace salePlace : salePlaceList) {
            return salePlace;
         }
         return null;
      } finally {
         try {
            em.close();
         }catch(Throwable t) {}
      }
   }

   public SalePlace getSalePlace(SalePlace salePlace) {
      return this.getSalePlace(salePlace.getId());
   }
}
