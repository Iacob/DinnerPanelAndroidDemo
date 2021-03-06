package luoyong.dinnerpanel.ui;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeSelectionModel;
import luoyong.dinnerpanel.dao.model.Food;
import luoyong.dinnerpanel.dao.model.FoodCategory;
import luoyong.dinnerpanel.ui.component.FoodCategoryTree;
import luoyong.dinnerpanel.ui.component.FoodListFromFoodCategoryTable;
import luoyong.dinnerpanel.ui.component.IconButton;
import luoyong.dinnerpanel.ui.icon.IconConstant;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class FoodManagementPanel extends JPanel {

   public static final String NAME = "FOOD_MANAGEMENT";

   // Stores the invisible information.
   private FoodCategoryTree foodCategoryTree = null;
   private FoodListFromFoodCategoryTable foodListTable = null;

   private JScrollPane foodCategoryScrollPanel = null;
   private JScrollPane foodListScrollPanel = null;

   private IconButton buttonAddFood = null;
   private IconButton buttonAddCategory = null;
   private IconButton buttonModifyCategory = null;
   private IconButton buttonRemoveCategory = null;

   private IconButton buttonModifyFood = null;
   private IconButton buttonRemoveFood = null;

   private JPanel foodCategoryButtonPanel = null;
   private JPanel foodListButtonPanel = null;

   private JPanel foodCategoryPanel = null;
   private JPanel foodListPanel = null;

   private JSplitPane splitPanel = null;

   public FoodManagementPanel() {

      foodCategoryTree = new FoodCategoryTree();
      foodCategoryTree.reloadTree();
      foodListTable = new FoodListFromFoodCategoryTable();
      foodListTable.reloadTable();

      foodCategoryScrollPanel = new JScrollPane(foodCategoryTree);
      foodListScrollPanel = new JScrollPane(foodListTable);

      buttonAddFood = new IconButton("增加餐品", IconConstant.ICON_FOOD_ADD);
      buttonAddCategory = new IconButton(
              "增加分类", IconConstant.ICON_FOOD_CATEGORY_ADD);
      buttonModifyCategory = new IconButton(
              "修改分类", IconConstant.ICON_FOOD_CATEGORY_EDIT);
      buttonRemoveCategory = new IconButton(
              "删除分类", IconConstant.ICON_FOOD_CATEGORY_REMOVE);

      foodCategoryButtonPanel = new JPanel();
      foodCategoryButtonPanel.setLayout(new BorderLayout());
      JPanel panel1 = new JPanel();
      panel1.add(buttonAddFood);
      panel1.add(buttonAddCategory);
      JPanel panel2 = new JPanel();
      panel2.add(buttonModifyCategory);
      panel2.add(buttonRemoveCategory);
      foodCategoryButtonPanel.add(panel1, BorderLayout.NORTH);
      foodCategoryButtonPanel.add(panel2, BorderLayout.SOUTH);

      buttonModifyFood = new IconButton("修改餐品资料", IconConstant.ICON_FOOD_EDIT);
      buttonRemoveFood = new IconButton("删除餐品资料", IconConstant.ICON_FOOD_REMOVE);

      foodListButtonPanel = new JPanel();
      foodListButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
      foodListButtonPanel.add(buttonModifyFood);
      foodListButtonPanel.add(buttonRemoveFood);

      foodCategoryPanel = new JPanel();
      foodCategoryPanel.setLayout(new BorderLayout());
      foodCategoryPanel.add(foodCategoryScrollPanel, BorderLayout.CENTER);
      foodCategoryPanel.add(foodCategoryButtonPanel, BorderLayout.SOUTH);
      foodListPanel = new JPanel();
      foodListPanel.setLayout(new BorderLayout());
      foodListPanel.add(foodListScrollPanel, BorderLayout.CENTER);
      foodListPanel.add(foodListButtonPanel, BorderLayout.SOUTH);

      splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

      splitPanel.setLeftComponent(foodCategoryPanel);
      splitPanel.setRightComponent(foodListPanel);

      this.setLayout(new BorderLayout());
      this.add(splitPanel);

      TreeSelectionModel foodCategoryTreeSelectionModel
              = foodCategoryTree.getSelectionModel();
      foodCategoryTreeSelectionModel.addTreeSelectionListener(
              new TreeSelectionListener() {
         
         @Override
         public void valueChanged(TreeSelectionEvent e) {
            FoodCategory c = foodCategoryTree.getSelectedFoodCategory();
            if (c != null) {
               foodListTable.reloadTable(c);
            }
         }
      });

      buttonAddFood.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

            FoodInformationUpdateDialog dialog
                    = new FoodInformationUpdateDialog();
            dialog.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
            dialog.setModal(true);
            // Set selected food category to dialog.
            dialog.setFoodCategory(foodCategoryTree.getSelectedFoodCategory());
            dialog.addAddNewActinoListener(foodListTable);
            dialog.addUpdateActionListener(foodListTable);
            // Show add food panel.
            dialog.setVisible(true);
         }
      });

      buttonAddCategory.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            String categoryName = JOptionPane.showInputDialog(null, "请输入分类名称",
                    "请输入", JOptionPane.PLAIN_MESSAGE);
            if ((categoryName != null) && (categoryName.trim().length() > 0)) {
               FoodCategory c = new FoodCategory();
               c.setName(categoryName);
               foodCategoryTree.addTopLevelCategory(c);
            }
         }
      });

      buttonModifyCategory.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            FoodCategory c = foodCategoryTree.getSelectedFoodCategory();
            if (c != null) {
               String categoryName = JOptionPane.showInputDialog(
                       "请输入新的分类名称", c.getName());
               
               if ((categoryName != null)
                       && (categoryName.trim().length() > 0)) {
                  
                  c.setName(categoryName);
                  foodCategoryTree.updateCategory(c);
               }
            }
         }
      });

      buttonRemoveCategory.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // Get selected food category.
            FoodCategory c = foodCategoryTree.getSelectedFoodCategory();
            if (c != null) {
               // Remove selected food category.
               foodCategoryTree.removeTopLevelCategory(c);
            }
         }
      });

      buttonModifyFood.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            Food food = foodListTable.getSelectedFoodInformation();
            if (food != null) {
               FoodInformationUpdateDialog dialog
                       = new FoodInformationUpdateDialog();
               dialog.setModalityType(ModalityType.APPLICATION_MODAL);
               dialog.setModal(true);
               // Set selected food category to dialog.
               dialog.setFoodInformation(food);
               dialog.addAddNewActinoListener(foodListTable);
               dialog.addUpdateActionListener(foodListTable);
               // Show add food panel.
               dialog.setVisible(true);
            }
         }
      });

      buttonRemoveFood.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            Food f = foodListTable.getSelectedFoodInformation();
            foodListTable.removeFood(f);
         }
      });
   }

   public void showMessage(String message, String title) {
      JOptionPane.showMessageDialog(
              this, message, title, JOptionPane.INFORMATION_MESSAGE);
   }
}
