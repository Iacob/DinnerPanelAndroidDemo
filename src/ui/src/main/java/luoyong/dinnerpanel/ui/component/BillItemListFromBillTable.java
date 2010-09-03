package luoyong.dinnerpanel.ui.component;

import java.util.LinkedList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import luoyong.dinnerpanel.dao.model.Bill;
import luoyong.dinnerpanel.dao.model.BillItem;
import luoyong.dinnerpanel.dao.model.BillItemStatus;
import luoyong.dinnerpanel.dao.model.enumwrapper.BillItemStatusEnum;
import luoyong.dinnerpanel.service.BillManagement;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class BillItemListFromBillTable extends JTable
        implements AddNewActionListener, UpdateActionListener {

   BillItemFromBillTableModel tableModel = null;

   // The bill which the bill items in this list belongs to.
   Bill bill = null;

   BillManagement billManagement = null;

   LinkedList<BillItem> billItemItemList = null;

   public BillItemListFromBillTable() {
      tableModel = new BillItemFromBillTableModel();
      this.setModel(tableModel);
      this.setAutoCreateRowSorter(true);
      this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

      billManagement = new BillManagement();

      billItemItemList = new LinkedList<BillItem>();
   }

   public void addBillItemToTable(BillItem i) {
      if ((i == null)
              || (bill == null)
              || (i.getBill() == null)
              || (i.getId() == null)
              || (bill.getId() == null)
              || (i.getBill().getId() == null)
              || (!bill.getId().equals(i.getBill().getId()))) {

         return;
      }

      Object rowData[] = {i.getFoodName(), i.getFoodCount(),
         new BillItemStatusEnum(i.getStatus()), i.getComment()};
      tableModel.addRow(rowData);

      billItemItemList.add(i);
   }

   public void addBillItem(BillItem i) {
      if (i == null) {
         return;
      }

      // Add food information.
      billManagement.addItemToBill(i);

      this.addBillItemToTable(i);
   }

   public void removeBillItemFromTable(BillItem i) {
      if ((i == null) || (i.getId() == null)) {
         return;
      }

      int itemIndex = this.getBillItemItemIndex(i);
      if (itemIndex >= 0) {
         // Remove from local list.
         billItemItemList.remove(itemIndex);
         // Remove from table.
         tableModel.removeRow(itemIndex);
      }
   }

   public void removeBillItem(BillItem i) {
      if ((i == null) || (i.getId() == null)) {
         return;
      }

      this.removeBillItemFromTable(i);

      billManagement.removeItemFromBill(i);
   }

   public void cancelBillItemFromTable(BillItem i) {
      if ((i == null) || (i.getId() == null)) {
         return;
      }
      
      i.setStatus(BillItemStatus.C);

      // Change the status of the bill item to "cancelled".
      this.updateBillItemFromTable(i);
   }

   public void cancelBillItem(BillItem i) {
      if ((i == null) || (i.getId() == null)) {
         return;
      }

      this.cancelBillItemFromTable(i);

      billManagement.cancelItemFromBill(i);
   }

   public void updateBillItemFromTable(BillItem i) {

      if ((i == null) || (i.getId() == null)) {
         return;
      }

      if ((i == null)
              || (i.getBill() == null)
              || (i.getBill().getId() == null)
              || (this.bill == null)
              || (this.bill.getId() == null)
              || (!i.getBill().getId().equals(this.bill.getId()))) {

         // If the bill item is not belongs to
         // current bill any more, remove it from table.
         this.removeBillItemFromTable(i);
         return;

      }else {

         int itemIndex = this.getBillItemItemIndex(i);

         if (itemIndex >= 0) {

            tableModel.setValueAt(i.getFoodName(), itemIndex, 0);
            tableModel.setValueAt(i.getFoodCount(), itemIndex, 1);
            tableModel.setValueAt(new BillItemStatusEnum(i.getStatus()),
                    itemIndex, 2);
            tableModel.setValueAt(i.getComment(), itemIndex, 3);
         }
      }
   }

   public void setCommentToBillItemFromTable(BillItem i, String comment) {
      if ((i == null) || (i.getId() == null)) {
         return;
      }

      int itemIndex = this.getBillItemItemIndex(i);

      if (itemIndex >= 0) {
         
         tableModel.setValueAt(comment, itemIndex, 3);
      }
   }

   public void setCommentToBillItem(BillItem i, String comment) {
      if ((i == null) || (i.getId() == null)) {
         return;
      }

      // Set comment to bill item.
      billManagement.setCommentToBillItem(i, comment);

      // Update display.
      this.setCommentToBillItemFromTable(i, comment);
   }

   public void clearTable() {

      billItemItemList.clear();

      int rowCount = this.getRowCount();
      for (int i=0; i<rowCount; i++) {
         tableModel.removeRow(0);
      }
   }

   public void reloadTable(Bill b) {
      this.bill = b;
      // Clear table first.
      this.clearTable();
      // Reload content.
      if (this.bill != null) {
         List<BillItem> billItemList
                 = billManagement.getAllBillItemsFromBill(b);
         for (BillItem billItem : billItemList) {
            this.addBillItemToTable(billItem);
         }
      }
   }

   public void reloadTable() {
      this.reloadTable(this.bill);
   }

   public BillItem getSelectedBillItem() {
      int selectedRowIndex = this.getSelectedRow();
      if (selectedRowIndex < 0) {
         return null;
      }

      return billItemItemList.get(selectedRowIndex);
   }

   private int getBillItemItemIndex(BillItem i) {
      if ((billItemItemList == null) || (i == null) || (i.getId() == null)) {
         return -1;
      }

      int listSize = billItemItemList.size();
      for (int idx=0; idx<listSize; idx++) {
         BillItem billItem = billItemItemList.get(idx);
         if ((billItem != null) && (billItem.getId() != null)
                 && (billItem.getId().equals(i.getId()))) {
            return idx;
         }
      }
      return -1;
   }

   @Override
   public void addNewActionPerformed(Object object) {
      if ((object != null) && (object instanceof BillItem)) {
         this.addBillItemToTable((BillItem)object);
      }
   }

   @Override
   public void updateActionPerformed(Object object) {
      if ((object != null) && (object instanceof BillItem)) {
         this.updateBillItemFromTable((BillItem)object);
      }
   }

   private static class BillItemFromBillTableModel extends DefaultTableModel {

      public BillItemFromBillTableModel() {
      }

      @Override
      public int getColumnCount() {
         return 4;
      }

      @Override
      public String getColumnName(int column) {
         switch (column) {
            case 0:
               return "餐品名称";
            case 1:
               return "份数";
            case 2:
               return "状态";
            case 3:
               return "附注";
            default:
               return null;
         }
      }

      @Override
      public Class getColumnClass(int column) {
         return String.class;
      }

      @Override
      public boolean isCellEditable(int row, int column) {
         return false;
      }
   }
}
