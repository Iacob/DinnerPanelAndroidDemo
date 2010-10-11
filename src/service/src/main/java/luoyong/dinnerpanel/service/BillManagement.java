package luoyong.dinnerpanel.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import luoyong.dinnerpanel.dao.EntityManagerBuilder;
import luoyong.dinnerpanel.dao.model.Bill;
import luoyong.dinnerpanel.dao.model.BillItem;
import luoyong.dinnerpanel.dao.model.BillItemHastenRecord;
import luoyong.dinnerpanel.dao.model.BillItemStatus;
import luoyong.dinnerpanel.dao.model.ExistKey;
import luoyong.dinnerpanel.dao.model.SalePlace;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class BillManagement {

   public void makeBill(Bill bill) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         em.getTransaction().begin();
         bill.setEk(ExistKey.E);
         em.persist(bill);
         em.getTransaction().commit();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {}
      }
   }

   public void removeBill(Long id) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         em.getTransaction().begin();
         Query query = em.createNamedQuery(Bill.QUERY_REMOVE_BILL);
         query.setParameter(1, id);
         query.executeUpdate();
         em.getTransaction().commit();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {}
      }
   }

   public void removeBill(Bill b) {
      this.removeBill(b.getId());
   }

   public void setCommentToBill(Long id, String comment) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         em.getTransaction().begin();
         Query query = em.createNamedQuery(Bill.QUERY_SET_COMMENT_TO_BILL);
         query.setParameter(1, comment);
         query.setParameter(2, id);
         query.executeUpdate();
         em.getTransaction().commit();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {}
      }
   }

   public void setCommentToBill(Bill b, String comment) {
      this.setCommentToBill(b.getId(), comment);
   }

   public void setSellingPriceToBill(Long id, BigDecimal sellingPrice) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         em.getTransaction().begin();
         Query query = em.createNamedQuery(
                 Bill.QUERY_SET_SELLING_PRICE_TO_BILL);
         query.setParameter(1, sellingPrice);
         query.setParameter(2, id);
         query.executeUpdate();
         em.getTransaction().commit();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {}
      }
   }

   public void setSellingPriceToBill(Bill b, BigDecimal sellingPrice) {
      this.setSellingPriceToBill(b.getId(), sellingPrice);
   }

   public void markBillAsSent(Long id) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         em.getTransaction().begin();
         Query query = em.createNamedQuery(Bill.QUERY_MARK_BILL_AS_SENT);
         query.setParameter(1, id);
         query.executeUpdate();
         em.getTransaction().commit();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {}
      }
   }

   public void markBillAsSent(Bill b) {
      this.markBillAsSent(b.getId());
   }

   public void cancelBill(Long id) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         em.getTransaction().begin();
         Query query = em.createNamedQuery(Bill.QUERY_CANCEL_BILL);
         query.setParameter(1, id);
         query.executeUpdate();
         em.getTransaction().commit();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {}
      }
   }

   public void cancelBill(Bill b) {
      this.cancelBill(b.getId());
   }

   public void addItemToBill(BillItem item) {

      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         em.getTransaction().begin();
         item.setEk(ExistKey.E);
         em.persist(item);
         em.getTransaction().commit();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {}
      }
   }

   public void removeItemFromBill(Long id) {

      // If ID parameter is null, return immediately.
      if (id == null) {
         return;
      }

      // If correspond bill item does not exists, return immediately.
      BillItem billItem = this.getBillItemInformation(id);
      if (billItem == null) {
         return;
      }

      // Processed bill item does not allowed to be removed. Return immediately.
      if ((billItem.getStatus() != null)
              && ((billItem.getStatus().equals(BillItemStatus.P))
               || (billItem.getStatus().equals(BillItemStatus.F)))) {
         
         return;
      }

      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         em.getTransaction().begin();
         Query query = em.createNamedQuery(
                 BillItem.QUERY_REMOVE_ITEM_FROM_BILL);
         query.setParameter(1, id);
         query.executeUpdate();
         em.getTransaction().commit();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {}
      }
   }

   public void removeItemFromBill(BillItem item) {
      this.removeItemFromBill(item.getId());
   }

   public void cancelItemFromBill(Long id) {

      // If ID parameter is null, return immediately.
      if (id == null) {
         return;
      }

      // If correspond bill item does not exists, return immediately.
      BillItem billItem = this.getBillItemInformation(id);
      if (billItem == null) {
         return;
      }

      // Processed bill item does not allowed to be cancelled.
      // Return immediately.
      if ((billItem.getStatus() != null)
              && ((billItem.getStatus().equals(BillItemStatus.P))
               || (billItem.getStatus().equals(BillItemStatus.F)))) {

         return;
      }

      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         em.getTransaction().begin();
         Query query = em.createNamedQuery(
                 BillItem.QUERY_CANCEL_ITEM_FROM_BILL);
         query.setParameter(1, id);
         query.executeUpdate();
         em.getTransaction().commit();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {}
      }
   }

   public void cancelItemFromBill(BillItem item) {
      this.cancelItemFromBill(item.getId());
   }

   public void markBillItemComplete(Long id) {

      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         em.getTransaction().begin();
         Query query = em.createNamedQuery(
                 BillItem.QUERY_MARK_BILL_ITEM_COMPLETE);
         query.setParameter(1, id);
         query.executeUpdate();
         em.getTransaction().commit();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {}
      }
   }

   public void markBillItemComplete(BillItem item) {
      this.markBillItemComplete(item.getId());
   }

   public void markBillItemProcessing(Long id) {

      // If ID parameter is null, return immediately.
      if (id == null) {
         return;
      }

      // If correspond bill item does not exists, return immediately.
      BillItem billItem = this.getBillItemInformation(id);
      if (billItem == null) {
         return;
      }

      // Finished bill item does not allowed to be marked as processing.
      // Return immediately.
      if ((billItem.getStatus() != null)
              && (billItem.getStatus().equals(BillItemStatus.F))) {

         return;
      }

      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         em.getTransaction().begin();
         Query query = em.createNamedQuery(
                 BillItem.QUERY_MARK_BILL_ITEM_PROCESSING);
         query.setParameter(1, id);
         query.executeUpdate();
         em.getTransaction().commit();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {}
      }
   }

   public void markBillItemProcessing(BillItem item) {
      this.markBillItemProcessing(item.getId());
   }

   public void markBillItemReturned(Long id) {

      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         em.getTransaction().begin();
         Query query = em.createNamedQuery(
                 BillItem.QUERY_MARK_BILL_ITEM_RETURNED);
         query.setParameter(1, id);
         query.executeUpdate();
         em.getTransaction().commit();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {}
      }
   }

   public void markBillItemReturned(BillItem item) {
      this.markBillItemReturned(item.getId());
   }

   public void setCommentToBillItem(Long id, String comment) {

      // If ID parameter is null, return immediately.
      if (id == null) {
         return;
      }

      // If correspond bill item does not exists, return immediately.
      BillItem billItem = this.getBillItemInformation(id);
      if (billItem == null) {
         return;
      }

      // Processed bill item does not allowed to be set comment.
      // Return immediately.
      if ((billItem.getStatus() != null)
              && ((billItem.getStatus().equals(BillItemStatus.P))
               || (billItem.getStatus().equals(BillItemStatus.F)))) {

         return;
      }

      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         em.getTransaction().begin();
         Query query = em.createNamedQuery(
                 BillItem.QUERY_SET_COMMENT_TO_BILL_ITEM);
         query.setParameter(1, comment);
         query.setParameter(2, id);
         query.executeUpdate();
         em.getTransaction().commit();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {}
      }
   }

   public void setCommentToBillItem(BillItem i, String comment) {
      this.setCommentToBillItem(i.getId(), comment);
   }

   public List<BillItem> getAllBillItemsFromBill(Bill b) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         Query query = em.createNamedQuery(
                 BillItem.QUERY_GET_ALL_BILL_ITEMS_FROM_BILL);
         query.setParameter(1, b);
         return query.getResultList();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {
         }
      }
   }

   public void hastenFoodInBill(BillItem item) {

      // If parameter is null, return immediately.
      if (item == null) {
         return;
      }

      // If correspond bill item does not exists, return immediately.
      BillItem billItem = this.getBillItemInformation(item);
      if (billItem == null) {
         return;
      }

      // Finished food item does not need to be hastened. Return immediately.
      if ((billItem.getStatus() != null)
              && (billItem.getStatus().equals(BillItemStatus.F))) {

         return;
      }

      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         
         BillItemHastenRecord record = new BillItemHastenRecord();
         item = this.getBillItemInformation(item);
         if (item != null) {
            record.setBillId(item.getBill().getId());
            record.setBillItemId(item.getId());
            record.setHastenTime(new Date()); // Set hasten time.
            em.getTransaction().begin();
            em.persist(record);
            em.getTransaction().commit();
         }else {
            return;
         }
      } finally {
         try {
            em.close();
         } catch (Throwable t) {}
      }
   }

   public void checkoutBill(Long id) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         em.getTransaction().begin();
         Query query = em.createNamedQuery(Bill.QUERY_CHECKOUT_BILL);
         query.setParameter(1, new Date());
         query.setParameter(2, id);
         query.executeUpdate();
         em.getTransaction().commit();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {}
      }
   }

   public void checkoutBill(Bill bill) {
      this.checkoutBill(bill.getId());
   }

   public Bill getBillInformation(Long id) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         Query query = em.createNamedQuery(Bill.QUERY_GET_BILL_INFORMATION);
         query.setParameter(1, id);
         List<Bill> billList = query.getResultList();
         if (billList != null) {
            for (Bill bill : billList) {
               return bill;
            }
         }
         return null;
      } finally {
         try {
            em.close();
         } catch (Throwable t) {}
      }
   }

   public Bill getBillInformation(Bill bill) {
      return this.getBillInformation(bill.getId());
   }

   public Bill getBillInformation(BillItem i) {
      
      if (i == null) {
         return null;
      }

      i = this.getBillItemInformation(i);

      if (i == null) {
         return null;
      }

      Bill bill = i.getBill();

      if (bill == null) {
         return null;
      }

      bill = this.getBillInformation(bill);

      return bill;
   }

   public BillItem getBillItemInformation(Long id) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         Query query = em.createNamedQuery(
                 BillItem.QUERY_GET_BILL_ITEM_INFORMATION);
         query.setParameter(1, id);
         List<BillItem> billItemList = query.getResultList();
         if (billItemList != null) {
            for (BillItem billItem : billItemList) {
               return billItem;
            }
         }
         return null;
      } finally {
         try {
            em.close();
         } catch (Throwable t) {}
      }
   }

   public BillItem getBillItemInformation(BillItem item) {
      return this.getBillItemInformation(item.getId());
   }

   public List<Bill> getCurrentBillFromSalePlace(Long id) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         Query query = em.createNamedQuery(
                 Bill.QUERY_GET_CURRENT_BILL_FROM_SALE_PLACE);
         query.setParameter(1, id);
         return query.getResultList();
      } finally {
         try {
            em.close();
         }catch(Throwable t) {}
      }
   }

   public List<Bill> getCurrentBillFromSalePlace(SalePlace salePlace) {
      return this.getCurrentBillFromSalePlace(salePlace.getId());
   }

   public Long getCurrentBillCountFromSalePlace(Long id) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         Query query = em.createNamedQuery(
                 Bill.QUERY_GET_CURRENT_BILL_COUNT_FROM_SALE_PLACE);
         query.setParameter(1, id);
         
         Object resultObject = query.getSingleResult();
         if (resultObject == null) {
            return null;
         }else {
            return (Long)resultObject;
         }
         
      } finally {
         try {
            em.close();
         }catch(Throwable t) {}
      }
   }

   public Long getCurrentBillCountFromSalePlace(SalePlace salePlace) {
      return this.getCurrentBillCountFromSalePlace(salePlace.getId());
   }

   public BigDecimal calculateBillPrice(Bill b) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         Query query = em.createNamedQuery(BillItem.QUERY_CALCULATE_BILL_PRICE);
         query.setParameter(1, b);

         Object resultObject = query.getSingleResult();
         if (resultObject == null) {
            return null;
         }else {
            return (BigDecimal)query.getSingleResult();
         }
      } finally {
         try {
            em.close();
         }catch(Throwable t) {}
      }
   }

   public void writeBillPrice(Bill b) {

      Bill bill = this.getBillInformation(b);

      // Check if specified bill exists.
      if (bill == null) {
         return;
      }

      // Calculate bill price.
      BigDecimal billPrice = this.calculateBillPrice(bill);

      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         em.getTransaction().begin();
         Query query = em.createNamedQuery(Bill.QUERY_WRITE_BILL_PRICE);
         query.setParameter(1, billPrice);
         query.setParameter(2, bill.getId());
         query.executeUpdate();
         em.getTransaction().commit();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {}
      }
   }

   public Long calculateBillItemHastenCount(Long billItemId) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         Query query = em.createNamedQuery(
                 BillItemHastenRecord.QUERY_CALCULATE_BILL_ITEM_HASTEN_COUNT);
         query.setParameter(1, billItemId);

         Object resultObject = query.getSingleResult();
         if (resultObject == null) {
            return null;
         }else {
            return (Long)resultObject;
         }

      } finally {
         try {
            em.close();
         }catch(Throwable t) {}
      }
   }

   public Long calculateBillItemHastenCount(BillItem i) {
      return this.calculateBillItemHastenCount(i.getId());
   }

   public void writeBillItemHastenCount(Long billItemId) {

      Long billItemHastenCount = this.calculateBillItemHastenCount(billItemId);

      Integer billItemHastenCountInteger = null;
      if (billItemHastenCount == null) {
         billItemHastenCountInteger = null;
      }else {
         billItemHastenCountInteger
                 = new Integer(billItemHastenCount.intValue());
      }

      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         em.getTransaction().begin();
         Query query = em.createNamedQuery(
                 BillItem.QUERY_WRITE_BILL_ITEM_HASTEN_COUNT);
         query.setParameter(1, billItemHastenCountInteger);
         query.setParameter(2, billItemId);
         query.executeUpdate();
         em.getTransaction().commit();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {}
      }
   }

   public void writeBillItemHastenCount(BillItem i) {
      this.writeBillItemHastenCount(i.getId());
   }
}
