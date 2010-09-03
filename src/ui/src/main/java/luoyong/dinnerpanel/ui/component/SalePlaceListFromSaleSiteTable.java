package luoyong.dinnerpanel.ui.component;

import java.util.LinkedList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import luoyong.dinnerpanel.dao.model.SalePlace;
import luoyong.dinnerpanel.service.SalePlaceManagement;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class SalePlaceListFromSaleSiteTable extends JTable
        implements AddNewActionListener, UpdateActionListener {

   SalePlaceListTableModel tableModel = null;

   SalePlaceManagement salePlaceManagement = null;

   LinkedList<SalePlace> salePlaceItemList = null;

   public SalePlaceListFromSaleSiteTable() {
      tableModel = new SalePlaceListTableModel();
      this.setModel(tableModel);
      this.setAutoCreateRowSorter(true);
      this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

      salePlaceManagement = new SalePlaceManagement();

      salePlaceItemList = new LinkedList<SalePlace>();
   }

   public void addSalePlaceToTable(SalePlace s) {
      if ((s == null) || (s.getId() == null)) {
         return;
      }

      Object rowData[] = {s.getName(), s.getServiceStatus(), s.getStatus()};
      tableModel.addRow(rowData);

      // Add to local list.
      salePlaceItemList.add(s);
   }

   public void addSalePlace(SalePlace s) {
      if (s == null) {
         return;
      }

      // Add sale place information.
      salePlaceManagement.addSalePlace(s);

      this.addSalePlaceToTable(s);
   }

   public void removeSalePlaceFromList(SalePlace s) {
      if ((s == null) || (s.getId() == null)) {
         return;
      }

      int itemIndex = this.getSalePlaceItemIndex(s);
      if (itemIndex >= 0) {
         // Remove from local list.
         salePlaceItemList.remove(itemIndex);
         // Remove from table.
         tableModel.removeRow(itemIndex);
      }
   }

   public void removeSalePlace(SalePlace s) {
      if ((s == null) || (s.getId() == null)) {
         return;
      }

      this.removeSalePlaceFromList(s);

      salePlaceManagement.removeSalePlace(s);
   }

   public void updateSalePlaceFromList(SalePlace s) {
      if ((s == null) || (s.getId() == null)) {
         return;
      }

      int itemIndex = this.getSalePlaceItemIndex(s);

      if (itemIndex >= 0) {

         tableModel.setValueAt(s.getName(), itemIndex, 0);
         tableModel.setValueAt(s.getServiceStatus(), itemIndex, 1);
         tableModel.setValueAt(s.getStatus(), itemIndex, 2);
      }
   }

   public void updateSalePlace(SalePlace s) {
      if ((s == null) || (s.getId() == null)) {
         return;
      }

      salePlaceManagement.updateSalePlace(s);

      this.updateSalePlaceFromList(s);
   }

   public void clearTable() {

      salePlaceItemList.clear();

      int rowCount = this.getRowCount();
      for (int i=0; i<rowCount; i++) {
         tableModel.removeRow(0);
      }
   }

   public void reloadTable() {
      // Clear table first.
      this.clearTable();
      // Reload content.
      List<SalePlace> salePlaceList
              = salePlaceManagement.getAllSalePlaces();
      for (SalePlace salePlace : salePlaceList) {
         this.addSalePlaceToTable(salePlace);
      }
   }

   public SalePlace getSelectedSalePlaceInformation() {
      int selectedRowIndex = this.getSelectedRow();
      if (selectedRowIndex < 0) {
         return null;
      }

      return salePlaceItemList.get(selectedRowIndex);
   }

   public int getSelectedSalePlaceIndex() {
      int selectedRowIndex = this.getSelectedRow();
      if (selectedRowIndex < 0) {
         return -1;
      }

      if (selectedRowIndex < salePlaceItemList.size()) {
         return selectedRowIndex;
      }else {
         return -1;
      }
   }

   private int getSalePlaceItemIndex(SalePlace s) {
      if ((salePlaceItemList == null) || (s == null) || (s.getId() == null)) {
         return -1;
      }

      int listSize = salePlaceItemList.size();
      for (int i=0; i<listSize; i++) {
         SalePlace salePlace = salePlaceItemList.get(i);
         if ((salePlace != null) && (salePlace.getId() != null)
                 && (salePlace.getId().equals(s.getId()))) {
            return i;
         }
      }
      return -1;
   }

   @Override
   public void addNewActionPerformed(Object object) {
      if ((object != null) && (object instanceof SalePlace)) {
         // Handle sale place added outside this table.
         this.addSalePlaceToTable((SalePlace)object);
      }
   }

   @Override
   public void updateActionPerformed(Object object) {
      if ((object != null) && (object instanceof SalePlace)) {
         // Handle sale place updated outside this table.
         int selectedIndex = getSelectedSalePlaceIndex();
         if (selectedIndex >= 0) {
            // Update the local list.
            // TODO Add update local list action to all updateActionPerformed methods.
            this.salePlaceItemList.set(selectedIndex, (SalePlace)object);
         }
         this.updateSalePlaceFromList((SalePlace)object);
      }
   }

   private static class SalePlaceListTableModel extends DefaultTableModel {

      public SalePlaceListTableModel() {
      }

      @Override
      public int getColumnCount() {
         return 3;
      }

      @Override
      public String getColumnName(int column) {
         switch (column) {
            case 0:
               return "名称";
            case 1:
               return "服务状态";
            case 2:
               return "可用状态";
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
