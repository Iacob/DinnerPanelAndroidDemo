package luoyong.dinnerpanel.ui.component;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import luoyong.dinnerpanel.dao.model.Bill;
import luoyong.dinnerpanel.dao.model.SalePlace;
import luoyong.dinnerpanel.dao.model.enumwrapper.BillStatusEnum;
import luoyong.dinnerpanel.service.BillManagement;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class BillListFromSalePlaceTable extends JTable
        implements AddNewActionListener, UpdateActionListener {

   BillListFromSalePlaceTableModel tableModel = null;

   SalePlace salePlace = null;

   BillManagement billManagement = null;

   LinkedList<Bill> billItemList = null;

   public BillListFromSalePlaceTable() {
      tableModel = new BillListFromSalePlaceTableModel();
      this.setModel(tableModel);
      this.setAutoCreateRowSorter(true);
      this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

      salePlace = new SalePlace();

      billManagement = new BillManagement();

      billItemList = new LinkedList<Bill>();
   }

   public void addBillToTable(Bill b) {
      if ((b == null)
              || (salePlace == null)
              || (b.getId() == null)
              || (salePlace.getId() == null)
              || (b.getSalePlaceId() == null)
              || (!salePlace.getId().equals(b.getSalePlaceId()))) {

         return;
      }

      Object rowData[] = {b.getBoughtTime(), b.getOperatorId(), b.getComment(),
         new BillStatusEnum(b.getStatus()), b.getPrice()};
      tableModel.addRow(rowData);

      billItemList.add(b);
   }

   public void addBill(Bill b) {
      if (b == null) {
         return;
      }

      // Make new bill.
      billManagement.makeBill(b);

      this.addBillToTable(b);
   }

   public void removeBillFromTable(Bill b) {
      if ((b == null) || (b.getId() == null)) {
         return;
      }

      int itemIndex = this.getBillItemIndex(b);
      if (itemIndex >= 0) {
         // Remove from local list.
         billItemList.remove(itemIndex);
         // Remove from table.
         tableModel.removeRow(itemIndex);
      }
   }

   public void removeBill(Bill b) {
      if ((b == null) || (b.getId() == null)) {
         return;
      }

      this.removeBillFromTable(b);

      billManagement.removeBill(b);
   }

   public void updateBillFromTable(Bill b) {
      if ((b == null) || (b.getId() == null)) {
         return;
      }

      if ((b == null)
              || (b.getSalePlaceId() == null)
              || (this.salePlace == null)
              || (this.salePlace.getId() == null)
              || (!b.getSalePlaceId().equals(this.salePlace.getId()))) {

         // If the bill information is not belongs to
         // current sale place any more, remove it from table.
         this.removeBillFromTable(b);
         return;

      }else {

         int itemIndex = this.getBillItemIndex(b);

         if (itemIndex >= 0) {
            
            tableModel.setValueAt(b.getBoughtTime(), itemIndex, 0);
            tableModel.setValueAt(b.getOperatorId(), itemIndex, 1);
            tableModel.setValueAt(b.getComment(), itemIndex, 2);
            tableModel.setValueAt(
                    new BillStatusEnum(b.getStatus()), itemIndex, 3);
            tableModel.setValueAt(b.getPrice(), itemIndex, 4);
         }
      }
   }

   public void setCommentToBillFromList(Bill b, String comment) {
      if ((b == null) || (b.getId() == null)) {
         return;
      }

      int itemIndex = this.getBillItemIndex(b);

      b.setComment(comment);

      if (itemIndex >= 0) {
         tableModel.setValueAt(b.getComment(), itemIndex, 2);
      }
   }

   public void setCommentToBill(Bill b, String comment) {
      if ((b == null) || (b.getId() == null)) {
         return;
      }

      b.setComment(comment);

      billManagement.setCommentToBill(b, b.getComment());

      this.setCommentToBill(b, b.getComment());
   }

   public void clearTable() {
      
      billItemList.clear();

      int rowCount = this.getRowCount();
      for (int i=0; i<rowCount; i++) {
         tableModel.removeRow(0);
      }
   }

   public void reloadTable(SalePlace s) {
      this.salePlace = s;
      // Clear table first.
      this.clearTable();
      // Reload content.
      if (this.salePlace != null) {
         List<Bill> billList
                 = billManagement.getCurrentBillFromSalePlace(s);
         for (Bill bill : billList) {
            this.addBillToTable(bill);
         }
      }
   }

   public void reloadTable() {
      this.reloadTable(this.salePlace);
   }

   public Bill getSelectedBill() {
      int selectedRowIndex = this.getSelectedRow();
      if (selectedRowIndex < 0) {
         return null;
      }

      return billItemList.get(selectedRowIndex);
   }

   private int getBillItemIndex(Bill b) {
      if ((billItemList == null) || (b == null) || (b.getId() == null)) {
         return -1;
      }

      int listSize = billItemList.size();
      for (int i=0; i<listSize; i++) {
         Bill bill = billItemList.get(i);
         if ((bill != null) && (bill.getId() != null)
                 && (bill.getId().equals(b.getId()))) {
            return i;
         }
      }
      return -1;
   }

   @Override
   public void addNewActionPerformed(Object object) {
      if ((object != null) && (object instanceof Bill)) {
         this.addBillToTable((Bill)object);
      }
   }

   @Override
   public void updateActionPerformed(Object object) {
      if ((object != null) && (object instanceof Bill)) {
         this.updateBillFromTable((Bill)object);
      }
   }

   private static class BillListFromSalePlaceTableModel
           extends DefaultTableModel {

      public BillListFromSalePlaceTableModel() {
      }

      @Override
      public int getColumnCount() {
         return 5;
      }

      @Override
      public String getColumnName(int column) {
         switch (column) {
            case 0:
               return "开单时间";
            case 1:
               return "操作员ID";
            case 2:
               return "附注";
            case 3:
               return "状态";
            case 4:
               return "价格";
            default:
               return null;
         }
      }

      @Override
      public Class getColumnClass(int column) {
         switch (column) {
            case 3:
               return BigDecimal.class;
            default:
               return String.class;
         }
      }

      @Override
      public boolean isCellEditable(int row, int column) {
         return false;
      }
   }
}
