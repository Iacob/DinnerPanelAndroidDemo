package luoyong.dinnerpanel.ui.component;

import java.util.LinkedList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import luoyong.dinnerpanel.dao.model.Operator;
import luoyong.dinnerpanel.service.OperatorManagement;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class OperatorListTable extends JTable
        implements AddNewActionListener, UpdateActionListener {

   OperatorListTableModel tableModel = null;

   OperatorManagement operatorManagement = null;

   LinkedList<Operator> operatorItemList = null;

   public OperatorListTable() {
      tableModel = new OperatorListTableModel();
      this.setModel(tableModel);
      this.setAutoCreateRowSorter(true);
      this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

      operatorManagement = new OperatorManagement();

      operatorItemList = new LinkedList<Operator>();
   }

   public void addOperatorToTable(Operator o) {
      if ((o == null) || (o.getId() == null)) {
         return;
      }

      Object rowData[] = {o.getId(), o.getName(), o.getStatus()};
      tableModel.addRow(rowData);

      operatorItemList.add(o);
   }

   public void addOperator(Operator o) {
      if (o == null) {
         return;
      }

      // Add operator information.
      operatorManagement.addOperator(o);

      this.addOperatorToTable(o);
   }

   public void removeOperatorFromList(Operator o) {
      if ((o == null) || (o.getId() == null)) {
         return;
      }

      int itemIndex = this.getOperatorItemIndex(o);
      if (itemIndex >= 0) {
         // Remove from local list.
         operatorItemList.remove(itemIndex);
         // Remove from table.
         tableModel.removeRow(itemIndex);
      }
   }

   public void removeOperator(Operator o) {
      if ((o == null) || (o.getId() == null)) {
         return;
      }

      this.removeOperatorFromList(o);

      operatorManagement.removeOperatorInformation(o);
   }

   public void updateOperatorFromList(Operator o) {
      if ((o == null) || (o.getId() == null)) {
         return;
      }

      int itemIndex = this.getOperatorItemIndex(o);

      if (itemIndex >= 0) {

         tableModel.setValueAt(o.getId(), itemIndex, 0);
         tableModel.setValueAt(o.getName(), itemIndex, 1);
         tableModel.setValueAt(o.getStatus(), itemIndex, 2);
      }
   }

   public void updateOperator(Operator o) {
      if ((o == null) || (o.getId() == null)) {
         return;
      }

      operatorManagement.updateOperatorInformation(o);

      this.updateOperatorFromList(o);
   }

   public void clearTable() {
      
      operatorItemList.clear();

      int rowCount = this.getRowCount();
      for (int i=0; i<rowCount; i++) {
         tableModel.removeRow(0);
      }
   }

   public void reloadTable() {
      // Clear table first.
      this.clearTable();
      // Reload content.
      List<Operator> operatorList
              = operatorManagement.getAllOperatorInformation();
      for (Operator operator : operatorList) {
         this.addOperatorToTable(operator);
      }
   }

   public Operator getSelectedOperatorInformation() {
      int selectedRowIndex = this.getSelectedRow();
      if (selectedRowIndex < 0) {
         return null;
      }

      return operatorItemList.get(selectedRowIndex);
   }

   private int getOperatorItemIndex(Operator o) {
      if ((operatorItemList == null) || (o == null) || (o.getId() == null)) {
         return -1;
      }

      int listSize = operatorItemList.size();
      for (int i=0; i<listSize; i++) {
         Operator operator = operatorItemList.get(i);
         if ((operator != null) && (operator.getId() != null)
                 && (operator.getId().equals(o.getId()))) {
            return i;
         }
      }
      return -1;
   }

   @Override
   public void addNewActionPerformed(Object object) {
      if ((object != null) && (object instanceof Operator)) {
         // Handle operator information added outside this table.
         this.addOperatorToTable((Operator)object);
      }
   }

   @Override
   public void updateActionPerformed(Object object) {
      if ((object != null) && (object instanceof Operator)) {
         // Handle operator information updated outside this table.
         this.updateOperatorFromList((Operator)object);
      }
   }

   private static class OperatorListTableModel extends DefaultTableModel {

      public OperatorListTableModel() {
      }

      @Override
      public int getColumnCount() {
         return 3;
      }

      @Override
      public String getColumnName(int column) {
         switch (column) {
            case 0:
               return "操作员ID";
            case 1:
               return "姓名";
            case 2:
               return "状态";
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
