package luoyong.dinnerpanel.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.MathContext;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import luoyong.dinnerpanel.dao.model.ExistKey;
import luoyong.dinnerpanel.dao.model.Food;
import luoyong.dinnerpanel.dao.model.FoodCategory;
import luoyong.dinnerpanel.dao.model.FoodStatus;
import luoyong.dinnerpanel.rwsclient.FoodServiceClient;
import luoyong.dinnerpanel.rwscommon.info.RWSException;
import luoyong.dinnerpanel.ui.component.AddNewActionListener;
import luoyong.dinnerpanel.ui.component.IconButton;
import luoyong.dinnerpanel.ui.component.RWSExceptionDialog;
import luoyong.dinnerpanel.ui.component.SelectItemActionListener;
import luoyong.dinnerpanel.ui.component.UpdateActionListener;
import luoyong.dinnerpanel.ui.icon.IconConstant;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class FoodInformationUpdateDialog extends JDialog {

   // Stores the invisible paramter.
   private Long foodId = null;
   private FoodCategory foodCategory = null;

   private AddNewActionListener addNewActionListener = null;
   private UpdateActionListener updateActionListener = null;

   private FoodServiceClient foodManagement = null;

   private JLabel labelName = null;
   private JLabel labelCode = null;
   private JLabel labelTag = null;
   private JLabel labelCategory = null;
   private JLabel labelPrice = null;
   private JLabel labelStatus = null;

   private JTextField textFieldName = null;
   private JTextField textFieldCode = null;
   private JTextField textFieldTag = null;
   private JTextField textFieldCategory = null;
   private JTextField textFieldPrice = null;

   private JComboBox comboBoxStatus = null;

   private JTextArea textAreaDesc = null;

   private JScrollPane scrollPanelDesc = null;

   private IconButton buttonChooseFoodCategory = null;
   private IconButton buttonConfirm = null;
   private IconButton buttonCancel = null;

   public FoodInformationUpdateDialog() {

      this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

      foodManagement = new FoodServiceClient();

      labelName = new JLabel("餐品名称:");
      labelCode = new JLabel("餐品代号:");
      labelTag = new JLabel("餐品标签");
      labelCategory = new JLabel("所属分类:");
      labelPrice = new JLabel("价格:");
      labelStatus = new JLabel("状态:");

      textFieldName = new JTextField(10);
      textFieldCode = new JTextField(10);
      textFieldTag = new JTextField(10);
      textFieldCategory = new JTextField(5);
      textFieldCategory.setEditable(false);
      textFieldPrice = new JTextField(10);

      comboBoxStatus = new JComboBox();
      FoodStatus foodStatusArray[] = FoodStatus.values();
      for (FoodStatus s : foodStatusArray) {
         comboBoxStatus.addItem(s);
      }

      textAreaDesc = new JTextArea();

      scrollPanelDesc = new JScrollPane();
      scrollPanelDesc.setViewportView(textAreaDesc);

      buttonChooseFoodCategory
              = new IconButton("选择分类", IconConstant.ICON_FOOD_CATEGORY_EDIT);
      buttonConfirm = new IconButton("确认", IconConstant.ICON_FOOD_EDIT);
      buttonCancel = new IconButton("取消", IconConstant.ICON_CANCEL);

      JPanel inputPanel = new JPanel();
      JPanel parameterPanel = new JPanel();
      JPanel descPanel = new JPanel();

      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new FlowLayout());
      buttonPanel.add(buttonConfirm);
      buttonPanel.add(buttonCancel);

      GroupLayout layout = new GroupLayout(parameterPanel);
      parameterPanel.setLayout(layout);

      descPanel.setBorder(BorderFactory.createTitledBorder("餐品描述"));
      descPanel.setLayout(new BorderLayout());
      descPanel.add(scrollPanelDesc, BorderLayout.CENTER);

      inputPanel.setLayout(new GridLayout(2, 1));
      inputPanel.add(parameterPanel);
      inputPanel.add(descPanel);

      this.getContentPane().setLayout(new BorderLayout());
      this.getContentPane().add(inputPanel, BorderLayout.CENTER);
      this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

      layout.setAutoCreateGaps(true);
      layout.setAutoCreateContainerGaps(true);

      layout.setHorizontalGroup(layout.createSequentialGroup()
              .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                  .addComponent(labelName)
                  .addComponent(labelCode)
                  .addComponent(labelTag)
                  .addComponent(labelCategory)
                  .addComponent(labelPrice)
                  .addComponent(labelStatus))
              .addGroup(layout.createParallelGroup()
                  .addComponent(textFieldName)
                  .addComponent(textFieldCode)
                  .addComponent(textFieldTag)
                  .addGroup(layout.createSequentialGroup()
                        .addComponent(textFieldCategory)
                        .addComponent(buttonChooseFoodCategory))
                  .addComponent(textFieldPrice)
                  .addComponent(comboBoxStatus))
              .addContainerGap());
      layout.setVerticalGroup(layout.createSequentialGroup()
              .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                  .addComponent(labelName)
                  .addComponent(textFieldName))
              .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                  .addComponent(labelCode)
                  .addComponent(textFieldCode))
              .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                  .addComponent(labelTag)
                  .addComponent(textFieldTag))
              .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                  .addComponent(labelCategory)
                  .addComponent(textFieldCategory)
                  .addComponent(buttonChooseFoodCategory))
              .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                  .addComponent(labelPrice)
                  .addComponent(textFieldPrice))
              .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                  .addComponent(labelStatus)
                  .addComponent(comboBoxStatus))
              );
      
      buttonCancel.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
            dispose();
         }
      });

      buttonChooseFoodCategory.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            showChooseFoodCategoryDialog();
         }
      });

      buttonConfirm.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

            if ((textFieldName.getText() == null)
                    || (textFieldName.getText().trim().length() == 0)) {
               showErrorMessage("餐品名称不能为空", "输入错误");
               return;
            }

            if ((textFieldCode.getText() == null)
                    || (textFieldCode.getText().trim().length() == 0)) {
               showErrorMessage("餐品代号不能为空", "输入错误");
               return;
            }

            if (foodCategory == null) {
               showErrorMessage("请选择餐品分类", "输入错误");
               return;
            }

            try {
               foodCategory = foodManagement.getFoodCategory(foodCategory);
            } catch (RWSException ex) {
               RWSExceptionDialog.showRWSExceptionDialog(rootPane, ex);
               return;
            }
            
            if (foodCategory == null) {
               showErrorMessage("所选的餐品分类不存在", "输入错误");
               return;
            }

            if (!ifPriceStringValid(textFieldPrice.getText())) {
               showErrorMessage("输入的价格无效", "输入错误");
               return;
            }

            BigDecimal price = null;
            try {
               price = new BigDecimal(
                       textFieldPrice.getText(), MathContext.DECIMAL32);
               price.setScale(2);
            }catch(Throwable t) {}
            if (price == null) {
               showErrorMessage("输入的价格无效", "输入错误");
               return;
            }

            Food f = new Food();
            f.setEk(ExistKey.E);
            f.setId(foodId);
            f.setName(textFieldName.getText());
            f.setCode(textFieldCode.getText());
            f.setCategory(foodCategory);
            f.setPrice(price);
            Object comboBoxStatusSelectedItem
                    = comboBoxStatus.getSelectedItem();
            if ((comboBoxStatusSelectedItem != null)
                    && (comboBoxStatusSelectedItem instanceof FoodStatus)) {
               f.setStatus((FoodStatus)comboBoxStatusSelectedItem);
            }
            f.setDescription(textAreaDesc.getText());

            if (f.getId() ==null) {
               // Add food information to system.
               try {
                  foodManagement.addFoodInformation(f);
               } catch (RWSException ex) {
                  RWSExceptionDialog.showRWSExceptionDialog(rootPane, ex);
                  return;
               }
               // Tell the added food information to listener.
               if (addNewActionListener != null) {
                  addNewActionListener.addNewActionPerformed(f);
               }
               // Show success message.
               showMessage("餐品成功添加", "操作成功");
               // Close this dialog.
               dispose();
            }else {
               // Check if the food information needed to be updated
               // is still exists in system. If not, show error message.
               Food foodInSystem = null;
               try {
                  foodInSystem = foodManagement.getFoodInformation(f);
               } catch (RWSException ex) {
                  RWSExceptionDialog.showRWSExceptionDialog(rootPane, ex);
                  return;
               }
               if (foodInSystem != null) {
                  // Update food information from system.
                  try {
                     foodManagement.updateFoodInformation(f);
                  } catch (RWSException ex) {
                     RWSExceptionDialog.showRWSExceptionDialog(rootPane, ex);
                     return;
                  }
                  // TODO Do not use update methods in UI components.
                  // tell the updated food information to listener.
                  if (updateActionListener != null) {
                     updateActionListener.updateActionPerformed(f);
                  }
                  // Show success message
                  showMessage("餐品成功更新", "操作成功");
                  // Close this dialog.
                  dispose();
               }else {
                  showErrorMessage("所要更新的餐品信息并不存在，请点取消按钮退出编辑",
                          "输入错误");
               }
            }
         }
      });

      // Set the default size.
      this.setSize(400, 350);
      this.pack();
   }

   public void setFoodInformation(Food f) {

      if ((f != null) && (ExistKey.E.equals(f.getEk()))) {

         this.foodId = f.getId();
         this.foodCategory = f.getCategory();

         textFieldName.setText(f.getName());
         textFieldCode.setText(f.getCode());
         textFieldCategory.setText(
                 (f.getCategory()==null)?"":f.getCategory().getName());
         textFieldPrice.setText(
                 (f.getPrice()==null)?"":f.getPrice().toPlainString());
         comboBoxStatus.setSelectedItem(f.getStatus());
         textAreaDesc.setText(f.getDescription());
      }
   }

   public void setFoodCategory(FoodCategory c) {

      this.foodCategory = c;
      
      if ((this.foodCategory != null)
              && (ExistKey.E.equals(this.foodCategory.getEk()))) {
         
         textFieldCategory.setText(this.foodCategory.getName());
      }
   }

   public void addAddNewActinoListener(
           AddNewActionListener addNewActionListener) {
      
      this.addNewActionListener = addNewActionListener;
   }

   public void addUpdateActionListener(
           UpdateActionListener updateActionListener) {

      this.updateActionListener = updateActionListener;
   }

   private void showErrorMessage(String message, String title) {
      JOptionPane.showMessageDialog(this, message, title,
              JOptionPane.ERROR_MESSAGE);
   }

   private void showMessage(String message, String title) {
      JOptionPane.showMessageDialog(this, message, title,
              JOptionPane.INFORMATION_MESSAGE);
   }

   private void showChooseFoodCategoryDialog() {
      SelectFoodCategoryDialog dialog = new SelectFoodCategoryDialog();
      dialog.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
      dialog.setModal(true);
      dialog.addSelectItemActionListener(new SelectItemActionListenerAdapter());
      dialog.setVisible(true);
   }

   private boolean ifPriceStringValid(String priceString) {
      if (priceString == null) {
         return false;
      }

      if (priceString.trim().length() == 0) {
         return false;
      }

      return (priceString.trim().matches("[0-9]+")
              || priceString.trim().matches("[0-9]+\\.[0-9]{1,2}"));
   }

   private class SelectItemActionListenerAdapter
           implements SelectItemActionListener {
      
      @Override
      public void selectActionPerformed(Object object) {
         if ((object != null) && (object instanceof FoodCategory)) {
            setFoodCategory((FoodCategory) object);
         }
      }
   }
}
