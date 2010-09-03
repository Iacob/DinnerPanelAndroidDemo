package luoyong.dinnerpanel.ui;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
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

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class FoodManagementPanel extends JPanel {

   public static final String NAME = "FOOD_MANAGEMENT";

   // Stores the invisible information.
   FoodCategoryTree foodCategoryTree = null;
   FoodListFromFoodCategoryTable foodListTable = null;

   JScrollPane foodCategoryScrollPanel = null;
   JScrollPane foodListScrollPanel = null;

   JButton buttonAddFood = null;
   JButton buttonAddCategory = null;
   JButton buttonModifyCategory = null;
   JButton buttonRemoveCategory = null;

   JButton buttonModifyFood = null;
   JButton buttonRemoveFood = null;

   JPanel foodCategoryButtonPanel = null;
   JPanel foodListButtonPanel = null;

   JPanel foodCategoryPanel = null;
   JPanel foodListPanel = null;

   JSplitPane splitPanel = null;

   public FoodManagementPanel() {

      foodCategoryTree = new FoodCategoryTree();
      foodCategoryTree.reloadTree();
      foodListTable = new FoodListFromFoodCategoryTable();
      foodListTable.reloadTable();

      foodCategoryScrollPanel = new JScrollPane(foodCategoryTree);
      foodListScrollPanel = new JScrollPane(foodListTable);

      buttonAddFood = new JButton("增加餐品");
      buttonAddCategory = new JButton("增加分类");
      buttonModifyCategory = new JButton("修改分类");
      buttonRemoveCategory = new JButton("删除分类");

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

      buttonModifyFood = new JButton("修改餐品资料");
      buttonRemoveFood = new JButton("删除餐品资料");

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
