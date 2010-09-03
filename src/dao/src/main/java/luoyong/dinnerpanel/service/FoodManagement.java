package luoyong.dinnerpanel.service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import luoyong.dinnerpanel.dao.EntityManagerBuilder;
import luoyong.dinnerpanel.dao.model.ExistKey;
import luoyong.dinnerpanel.dao.model.Food;
import luoyong.dinnerpanel.dao.model.FoodCategory;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class FoodManagement {

   public void addFoodCategory(FoodCategory foodCategory) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         em.getTransaction().begin();
         foodCategory.setEk(ExistKey.E);
         em.persist(foodCategory);
         em.getTransaction().commit();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {
         }
      }
   }
   
   public FoodCategory getFoodCategory(Long id) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         Query query = em.createNamedQuery(
                 FoodCategory.QUERY_GET_FOOD_CATEGORY);
         query.setParameter(1, id);
         List<FoodCategory> categoryList = query.getResultList();
         Iterator<FoodCategory> categoryIterator = categoryList.iterator();
         if (categoryIterator.hasNext()) {
            return categoryIterator.next();
         }else {
            return null;
         }
      } finally {
         try {
            em.close();
         }catch(Throwable t) {}
      }
   }

   public FoodCategory getFoodCategory(FoodCategory foodCategory) {
      if (foodCategory != null) {
         return this.getFoodCategory(foodCategory.getId());
      }else {
         return null;
      }
   }

   public void updateFoodCategory(FoodCategory c) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         em.getTransaction().begin();
         em.merge(c);
         em.getTransaction().commit();
//         Query query = em.createNamedQuery(
//                 FoodCategory.QUERY_GET_FOOD_CATEGORY);
//         query.setParameter(1, c.getId());
//         List<FoodCategory> categoryList = query.getResultList();
//         Iterator<FoodCategory> categoryIterator = categoryList.iterator();
//         if (categoryIterator.hasNext()) {
//            FoodCategory category = categoryIterator.next();
//            // Update database.
//            em.getTransaction().begin();
//            category.setName(c.getName());
//            em.merge(category);
//            em.getTransaction().commit();
//         }
      } finally {
         try {
            em.close();
         }catch(Throwable t) {}
      }
   }

   public void removeFoodCategory(Long id) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         em.getTransaction().begin();
         Query query = em.createNamedQuery(
                 FoodCategory.QUERY_REMOVE_FOOD_CATEGORY);
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

   public void removeFoodCategory(FoodCategory c) {
      this.removeFoodCategory(c.getId());
   }

   public List<FoodCategory> getAllFoodCategories() {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         Query query = em.createNamedQuery(
                 FoodCategory.QUERY_GET_ALL_FOOD_CATEGORIES);
         return query.getResultList();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {
         }
      }
   }

   public void addFoodInformation(Food food) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         em.getTransaction().begin();
         food.setEk(ExistKey.E);
         em.persist(food);
         em.getTransaction().commit();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {
         }
      }
   }

   public Food getFoodInformation(Long id) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         Query query = em.createNamedQuery(Food.QUERY_GET_FOOD_INFORMATION);
         query.setParameter(1, id);
         List<Food> foodList = query.getResultList();
         Iterator<Food> foodIterator = foodList.iterator();
         if (foodIterator.hasNext()) {
            return foodIterator.next();
         }else {
            return null;
         }
      } finally {
         try {
            em.close();
         }catch(Throwable t) {}
      }
   }

   public Food getFoodInformation(Food food) {
      return this.getFoodInformation(food.getId());
   }
   
   public List<Food> getFoodListFromFoodCategory(FoodCategory foodCategory) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         Query query = em.createNamedQuery(
                 Food.QUERY_GET_ALL_FOOD_FROM_FOOD_CATEGORY);
         FoodCategory category = this.getFoodCategory(foodCategory);
         if (category == null) {
            // Return an empty list.
            return new LinkedList();
         }
         query.setParameter(1, category);
         return query.getResultList();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {
         }
      }
   }

   public void removeFoodInformation(Long id) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         em.getTransaction().begin();
         Query query = em.createNamedQuery(
                 Food.QUERY_REMOVE_FOOD_INFORMATION);
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

   public void removeFoodInformation(Food food) {
      this.removeFoodInformation(food.getId());
   }

   public void updateFood(Food f) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         em.getTransaction().begin();
         em.merge(f);
         em.getTransaction().commit();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {
         }
      }
   }

   public List<Food> searchFoodByName(String name) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         Query query = em.createNamedQuery(
                 Food.QUERY_SEARCH_FOOD_INFORMATION_BY_NAME);
         name = "%" + name.replaceAll("%", "") + "%";
         query.setParameter(1, name);
         return query.getResultList();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {
         }
      }
   }

   public List<Food> searchFoodByCode(String code) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         Query query = em.createNamedQuery(
                 Food.QUERY_SEARCH_FOOD_INFORMATION_BY_CODE);
         code = code.replaceAll("%", "") + "%";
         query.setParameter(1, code);
         return query.getResultList();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {
         }
      }
   }

   public List<Food> searchFoodByTag(String tag) {
      EntityManager em = null;
      try {
         em = EntityManagerBuilder.buildEntityManager();
         Query query = em.createNamedQuery(
                 Food.QUERY_SEARCH_FOOD_INFORMATION_BY_TAG);
         tag = "%" + tag.replaceAll("%", "") + "%";
         query.setParameter(1, tag);
         return query.getResultList();
      } finally {
         try {
            em.close();
         } catch (Throwable t) {
         }
      }
   }

   public List<Food> searchFoodByKeyword(String keyword) {
      
      List<Food> foodList = new LinkedList();

      List<Food> foodListByName = this.searchFoodByName(keyword);
      if (foodListByName != null) {
         foodList.addAll(foodListByName);
      }
      
      List<Food> foodListByTag = this.searchFoodByTag(keyword);
      if (foodListByTag != null) {
         foodList.addAll(foodListByTag);
      }
      
      return foodList;
   }
}
