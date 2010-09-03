package luoyong.dinnerpanel.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import luoyong.dinnerpanel.dao.model.FoodCategory;
import luoyong.dinnerpanel.ui.component.FoodCategoryTree;
import luoyong.dinnerpanel.ui.component.SelectItemActionListener;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class SelectFoodCategoryDialog extends JDialog {

   private FoodCategoryTree foodCategoryTree = null;
   private JScrollPane foodCategoryScrollPanel = null;
   private JButton buttonConfirm = null;
   private JButton buttonCancel = null;

   SelectItemActionListener selectItemActionListener = null;

   public SelectFoodCategoryDialog() {

      foodCategoryTree = new FoodCategoryTree();
      foodCategoryTree.reloadTree();

      foodCategoryScrollPanel = new JScrollPane();
      foodCategoryScrollPanel.setViewportView(foodCategoryTree);

      buttonConfirm = new JButton("确定");
      buttonCancel = new JButton("取消");

      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
      buttonPanel.add(buttonConfirm);
      buttonPanel.add(buttonCancel);

      this.getContentPane().setLayout(new BorderLayout());
      this.getContentPane().add(foodCategoryScrollPanel, BorderLayout.CENTER);
      this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

      buttonConfirm.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            if (selectItemActionListener != null) {
               // Get selected food category.
               FoodCategory selectedFoodCategory
                       = foodCategoryTree.getSelectedFoodCategory();
               if (selectedFoodCategory != null) {
                  // Tell the selected food category to listener
                  // and close this dialog.
                  selectItemActionListener
                          .selectActionPerformed(selectedFoodCategory);
                  dispose();
               }
            }
         }
      });

      buttonCancel.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            dispose();
         }
      });

      // Set the default size.
      this.setSize(350, 300);
   }

   public void addSelectItemActionListener(
           SelectItemActionListener selectItemActionListener) {
      
      this.selectItemActionListener = selectItemActionListener;
   }
}
