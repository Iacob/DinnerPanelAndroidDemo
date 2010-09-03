package luoyong.dinnerpanel.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import luoyong.dinnerpanel.dao.model.Bill;
import luoyong.dinnerpanel.dao.model.BillItem;
import luoyong.dinnerpanel.dao.model.BillItemStatus;
import luoyong.dinnerpanel.dao.model.ExistKey;
import luoyong.dinnerpanel.dao.model.Food;
import luoyong.dinnerpanel.service.BillManagement;
import luoyong.dinnerpanel.ui.component.AddNewActionListener;
import luoyong.dinnerpanel.ui.component.FoodSearchTable;
import luoyong.dinnerpanel.ui.component.UpdateActionListener;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class FoodSearchDialog extends JDialog {

   public static int MODE_CODE = 0;
   public static int MODE_KEYWORD = 1;

   private BillManagement billManagement = null;

   private Bill bill = null;

   private AddNewActionListener addNewActionListener = null;
   private UpdateActionListener updateActionListener = null;

   private int mode = MODE_KEYWORD;

   private FoodSearchTable foodSearchTable = null;

   private JTextField textFieldKeyword = null;

   private JButton buttonSearch = null;

   private JButton buttonConfirm = null;
   private JButton buttonCancel = null;

   public FoodSearchDialog(int searchMode) {

      this.mode = searchMode;

      this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

      billManagement = new BillManagement();

      foodSearchTable = new FoodSearchTable();

      textFieldKeyword = new JTextField(10);
      textFieldKeyword.addKeyListener(new KeyListener() {
         @Override
         public void keyTyped(KeyEvent e) {
            refreshSearchResult();
         }

         @Override
         public void keyPressed(KeyEvent e) {
         }

         @Override
         public void keyReleased(KeyEvent e) {
         }
      });

      buttonSearch = new JButton("重新搜索");

      buttonConfirm = new JButton("确定");
      buttonCancel = new JButton("关闭");

      JPanel panelKeyword = new JPanel();
      panelKeyword.setLayout(new FlowLayout(FlowLayout.LEADING));
      panelKeyword.add(new JLabel("搜索"));
      panelKeyword.add(textFieldKeyword);
      panelKeyword.add(buttonSearch);

      JScrollPane scrollPanelFoodSearchTable = new JScrollPane(foodSearchTable);

      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
      buttonPanel.add(buttonConfirm);
      buttonPanel.add(buttonCancel);

      this.getContentPane().setLayout(new BorderLayout());
      this.getContentPane().add(panelKeyword, BorderLayout.NORTH);
      this.getContentPane().add(
              scrollPanelFoodSearchTable, BorderLayout.CENTER);
      this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

      buttonSearch.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            String keyword = textFieldKeyword.getText();
            searchFood(foodSearchTable, keyword, mode);
         }
      });

      buttonConfirm.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

            if (bill != null) {

               Food food = foodSearchTable.getSelectedFoodInformation();
               if (food != null) {

                  FoodItemInfoDialog dialog = new FoodItemInfoDialog();
                  dialog.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
                  dialog.setModal(true);
                  dialog.setFood(food);
                  dialog.setVisible(true);

//                  BillItem billItem = new BillItem();
//                  billItem.setAddedTime(new Date());
//                  billItem.setBill(bill);
//                  billItem.setComment();
//                  billItem.setFoodCount();
//                  billItem.setPrice(food.getPrice());
//                  billItem.setStatus();
//
//                  billManagement
//
//                  // Tell selected food information to listener.
//                  if (addNewActionListener != null) {
//                     addNewActionListener.addNewActionPerformed(food);
//                  }
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
      this.pack();
   }

   public Bill getBill() {
      return bill;
   }

   public void setBill(Bill bill) {
      this.bill = bill;
   }

   public void addAddNewActionListener(AddNewActionListener listener) {
      this.addNewActionListener = listener;
   }

   public void addUpdateActionListener(UpdateActionListener listener) {
      this.updateActionListener = listener;
   }

   private synchronized void refreshSearchResult() {
      
      Thread refreshResultThread = new Thread(new Runnable() {

         @Override
         public void run() {
            // Sleep a while to wait key press from user complete.
            try {
               Thread.sleep(800);
            } catch (InterruptedException ex) {
               ex.printStackTrace(System.err);
            }
            String keyword = textFieldKeyword.getText();
            searchFood(foodSearchTable, keyword, mode);
         }
      });
      refreshResultThread.start();
   }

   private synchronized static void searchFood(
           FoodSearchTable foodSearchTable, String keyword, int mode) {

      if (keyword == null) {
         keyword = "";
      }

      if (mode == FoodSearchDialog.MODE_CODE) {
         foodSearchTable.searchFoodByCode(keyword);
      } else {
         foodSearchTable.searchFoodByKeyword(keyword);
      }
   }

   private class FoodItemInfoDialog extends JDialog {

      // Invisible field.
      private Food food = null;

      private JLabel labelCount = null;

      private JTextField textFieldCount = null;

      private JTextArea textAreaComment = null;

      private JButton buttonConfirm = null;
      private JButton buttonCancel = null;

      public FoodItemInfoDialog() {
         labelCount = new JLabel("所订份数");
         textFieldCount = new JTextField(10);
         textFieldCount.setText("1");
         textAreaComment = new JTextArea();

         buttonConfirm = new JButton("确定");
         buttonCancel = new JButton("取消");

         JPanel panelParameter = new JPanel();
         panelParameter.setLayout(new FlowLayout(FlowLayout.LEADING));
         panelParameter.add(labelCount);
         panelParameter.add(textFieldCount);

         JScrollPane scrollPanelComment = new JScrollPane();
         scrollPanelComment.setBorder(BorderFactory.createTitledBorder("餐品附注"));
         scrollPanelComment.setViewportView(textAreaComment);

         JPanel panelButton = new JPanel();
         panelButton.setLayout(new FlowLayout(FlowLayout.CENTER));
         panelButton.add(buttonConfirm);
         panelButton.add(buttonCancel);

         this.getContentPane().setLayout(new BorderLayout());
         this.getContentPane().add(panelParameter, BorderLayout.NORTH);
         this.getContentPane().add(scrollPanelComment, BorderLayout.CENTER);
         this.getContentPane().add(panelButton, BorderLayout.SOUTH);

         buttonConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               if ((bill == null)
                       || (bill.getId() == null)
                       || (!ExistKey.E.equals(bill.getEk()))) {
                  showErrorMessage("相关帐单有误，无法添加", "错误");
                  return;
               }

               if ((food == null)
                       || (food.getId() == null)
                       || (!ExistKey.E.equals(food.getEk()))) {
                  
                  showErrorMessage("所选的餐品信息有误，无法添加", "错误");
                  return;
               }

               String countString = textFieldCount.getText();
               if ((countString == null) || (countString.trim().length() < 1)) {
                  showErrorMessage("请输入份数", "输入错误");
                  return;
               }

               if (!countString.matches("[0-9]+")) {
                  showErrorMessage("输入的份数必须为整数", "输入错误");
                  return;
               }

               int count = 0;
               try {
                  count = Integer.parseInt(countString);
               }catch(Throwable t) {}

               if (count < 1) {
                  showErrorMessage("输入的份数必须大於1", "输入错误");
                  return;
               }

               BillItem billItem = new BillItem();
               billItem.setFoodId(food.getId());
               billItem.setFoodName(food.getName());
               billItem.setAddedTime(new Date());
               billItem.setBill(bill);
               billItem.setComment(textAreaComment.getText());
               billItem.setFoodCount(count);
               billItem.setPrice(food.getPrice());
               billItem.setStatus(BillItemStatus.W);

               billManagement.addItemToBill(billItem);

               // Tell the added bill item to listener.
               if (addNewActionListener != null) {
                  addNewActionListener.addNewActionPerformed(billItem);
               }

               // Show success message.
               showMessage("餐品添加成功", "成功");

               // Close this dialog.
               dispose();
            }
         });

         buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();
            }
         });

         // Set the default size.
         this.setSize(350, 270);
      }

      private void showErrorMessage(String message, String title) {
         JOptionPane.showMessageDialog(
                 this, message, title, JOptionPane.ERROR_MESSAGE);
      }

      private void showMessage(String message, String title) {
         JOptionPane.showMessageDialog(
                 this, message, title, JOptionPane.INFORMATION_MESSAGE);
      }

      public void setFood(Food food) {
         this.food = food;
      }
   }
}
