package luoyong.dinnerpanel.ui.component;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import luoyong.dinnerpanel.dao.model.Food;
import luoyong.dinnerpanel.dao.model.FoodCategory;
import luoyong.dinnerpanel.rwsclient.FoodServiceClient;
import luoyong.dinnerpanel.rwscommon.info.RWSException;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class FoodListFromFoodCategoryTable extends JTable
        implements AddNewActionListener, UpdateActionListener {

   private FoodListTableModel tableModel = null;

   private FoodCategory foodCategory = null;

   private FoodServiceClient foodServiceClient = null;

   private LinkedList<Food> foodItemList = null;

   public FoodListFromFoodCategoryTable() {
      tableModel = new FoodListTableModel();
      this.setModel(tableModel);
      this.setAutoCreateRowSorter(true);
      this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

      foodServiceClient = new FoodServiceClient();

      foodItemList = new LinkedList<Food>();
   }

   public void addFoodToTable(Food f) {
      if ((f == null)
              || (foodCategory == null)
              || (f.getCategory() == null)
              || (f.getId() == null)
              || (foodCategory.getId() == null)
              || (f.getCategory().getId() == null)
              || (!foodCategory.getId().equals(f.getCategory().getId()))) {
         
         return;
      }

      Object rowData[] = {f.getName(), f.getCode(),
         (f.getStatus()==null)?null:f.getStatus().toString(), f.getPrice()};
      tableModel.addRow(rowData);

      foodItemList.add(f);
   }

   public void addFood(Food f) {
      if (f == null) {
         return;
      }

      // Add food information.
      try {
         foodServiceClient.addFoodInformation(f);
      }catch(RWSException ex) {
         RWSExceptionDialog.showRWSExceptionDialog(this, ex);
         return;
      }

      this.addFoodToTable(f);
   }

   public void removeFoodFromList(Food f) {
      if ((f == null) || (f.getId() == null)) {
         return;
      }

      int itemIndex = this.getFoodItemIndex(f);
      if (itemIndex >= 0) {
         // Remove from local list.
         foodItemList.remove(itemIndex);
         // Remove from table.
         tableModel.removeRow(itemIndex);
      }
   }

   public void removeFood(Food f) {
      if ((f == null) || (f.getId() == null)) {
         return;
      }

      this.removeFoodFromList(f);

      try {
         foodServiceClient.removeFoodInformation(f);
      }catch(RWSException ex) {
         RWSExceptionDialog.showRWSExceptionDialog(this, ex);
         return;
      }
   }

   public void updateFoodFromList(Food f) {
      if ((f == null) || (f.getId() == null)) {
         return;
      }

      // TODO Add null value and category check to all update methods in UI comonent.

      if ((f == null)
              || (f.getCategory() == null)
              || (f.getCategory().getId() == null)
              || (this.foodCategory == null)
              || (!f.getCategory().getId().equals(this.foodCategory.getId()))) {

         // If the updated food information is not belongs to
         // current food category any more, remove it from table.
         this.removeFoodFromList(f);
         return;

      }else {
         
         int itemIndex = this.getFoodItemIndex(f);

         if (itemIndex >= 0) {

            tableModel.setValueAt(f.getName(), itemIndex, 0);
            tableModel.setValueAt(f.getCode(), itemIndex, 1);
            tableModel.setValueAt(f.getStatus(), itemIndex, 2);
            tableModel.setValueAt(f.getPrice(), itemIndex, 3);
         }
      }
   }

   public void updateFood(Food f) {
      if ((f == null) || (f.getId() == null)) {
         return;
      }

      try {
         foodServiceClient.updateFoodInformation(f);
      }catch(RWSException ex) {
         RWSExceptionDialog.showRWSExceptionDialog(this, ex);
         return;
      }

      Food foodUpdated = null;

      try {
         // Reload food information.
         foodUpdated = foodServiceClient.getFoodInformation(f);
      }catch(RWSException ex) {
         RWSExceptionDialog.showRWSExceptionDialog(this, ex);
         return;
      }

      // TODO Add reload action to all update methods.
         
      this.updateFoodFromList(foodUpdated);
   }

   public void clearTable() {
      
      foodItemList.clear();

      int rowCount = this.getRowCount();
      for (int i=0; i<rowCount; i++) {
         tableModel.removeRow(0);
      }
   }

   public void reloadTable(FoodCategory c) {
      this.foodCategory = c;
      // Clear table first.
      this.clearTable();
      // Reload content.
      if (this.foodCategory != null) {

         List<Food> foodList = null;

         try {
            foodList = foodServiceClient.getFoodListFromFoodCategory(foodCategory);
         }catch(RWSException ex) {
            RWSExceptionDialog.showRWSExceptionDialog(this, ex);
            return;
         }

         if (foodList == null) {
            return;
         }

         for (Food food : foodList) {
            this.addFoodToTable(food);
         }
      }
   }

   public void reloadTable() {
      this.reloadTable(this.foodCategory);
   }

   public Food getSelectedFoodInformation() {
      int selectedRowIndex = this.getSelectedRow();
      if (selectedRowIndex < 0) {
         return null;
      }

      return foodItemList.get(selectedRowIndex);
   }

   private int getFoodItemIndex(Food f) {
      if ((foodItemList == null) || (f == null) || (f.getId() == null)) {
         return -1;
      }

      int listSize = foodItemList.size();
      for (int i=0; i<listSize; i++) {
         Food food = foodItemList.get(i);
         if ((food != null) && (food.getId() != null)
                 && (food.getId().equals(f.getId()))) {
            return i;
         }
      }
      return -1;
   }

   @Override
   public void addNewActionPerformed(Object object) {
      if ((object != null) && (object instanceof Food)) {

         // Handle food information added outside this table.
         this.addFoodToTable((Food)object);
      }
   }
   
   @Override
   public void updateActionPerformed(Object object) {
      if ((object != null) && (object instanceof Food)) {

         // Handle food information updated outside this table.
         this.updateFoodFromList((Food)object);
      }
   }

   private static class FoodListTableModel extends DefaultTableModel {

      public FoodListTableModel() {
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
               return "餐品代号";
            case 2:
               return "状态";
            case 3:
               return "餐品价格";
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
