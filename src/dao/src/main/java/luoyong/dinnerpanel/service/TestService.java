package luoyong.dinnerpanel.service;

import java.util.List;
import luoyong.dinnerpanel.dao.EntityManagerBuilder;
import luoyong.dinnerpanel.dao.model.Bill;
import luoyong.dinnerpanel.dao.model.BillItem;
import luoyong.dinnerpanel.dao.model.SalePlace;

/**
 *
 * @author yong
 */
public class TestService {

   public void test() {
      EntityManagerBuilder.buildEntityManager();
      System.out.println("Initialized.");

      BillManagement m = new BillManagement();

//      Bill bill = new Bill();
//      bill.setId(1L);
      BillItem item = new BillItem();
      item.setId(151L);
      m.removeItemFromBill(item);
      
   }
}
