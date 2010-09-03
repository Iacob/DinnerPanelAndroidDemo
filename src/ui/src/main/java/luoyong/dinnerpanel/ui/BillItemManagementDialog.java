package luoyong.dinnerpanel.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import luoyong.dinnerpanel.dao.model.Bill;
import luoyong.dinnerpanel.dao.model.BillItem;
import luoyong.dinnerpanel.dao.model.BillStatus;
import luoyong.dinnerpanel.dao.model.ExistKey;
import luoyong.dinnerpanel.dao.model.SalePlace;
import luoyong.dinnerpanel.service.BillManagement;
import luoyong.dinnerpanel.ui.component.AddNewActionListener;
import luoyong.dinnerpanel.ui.component.BillItemListFromBillTable;
import luoyong.dinnerpanel.ui.component.UpdateActionListener;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class BillItemManagementDialog extends JDialog {

   // Invisible field.
   private Bill bill = null;

   private AddNewActionListener addNewActionListener = null;
   private UpdateActionListener updateActionListener = null;

   private BillManagement billManagement = null;

   private BillItemListFromBillTable billItemListFromBillTable = null;

   private JLabel labelManageBillItem = null;
   private JLabel labelPrice = null;

   private JTextField textFieldPrice = null;

   private JTextArea textAreaComment = null;

   private JButton buttonAddBillItem = null;
   private JButton buttonRemoveBillItem = null;
   private JButton buttonSetBillItemComment = null;

   private JPopupMenu popupMenu = null;

   private JButton buttonSendBill = null;
   private JButton buttonCancelBill = null;
   private JButton buttonCheckoutBill = null;

   public BillItemManagementDialog() {

      this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

      billManagement = new BillManagement();

      billItemListFromBillTable = new BillItemListFromBillTable();
      billItemListFromBillTable.reloadTable();

      labelManageBillItem = new JLabel("管理餐品");
      labelPrice = new JLabel("价格:");

      textFieldPrice = new JTextField(10);
      textFieldPrice.setEditable(false);

      textAreaComment = new JTextArea();

      buttonAddBillItem = new JButton("添加餐品...");
      buttonRemoveBillItem = new JButton("取消餐品");
      buttonSetBillItemComment = new JButton("编辑餐品");

      popupMenu = new JPopupMenu();

      buttonSendBill = new JButton("送出/保存");
      buttonCancelBill = new JButton("取消帐单");
      buttonCheckoutBill = new JButton("结帐...");

      JPanel panelAddBillItem = new JPanel();
      panelAddBillItem.setLayout(new FlowLayout(FlowLayout.LEADING));
      panelAddBillItem.add(labelManageBillItem);
      panelAddBillItem.add(buttonAddBillItem);
      panelAddBillItem.add(buttonRemoveBillItem);
      panelAddBillItem.add(buttonSetBillItemComment);

      JScrollPane scrollPanelBillItem = new JScrollPane();
      scrollPanelBillItem.setViewportView(billItemListFromBillTable);

      JPanel panelBillItem = new JPanel();
      panelBillItem.setLayout(new BorderLayout());
      panelBillItem.add(panelAddBillItem, BorderLayout.NORTH);
      panelBillItem.add(scrollPanelBillItem, BorderLayout.CENTER);

      JScrollPane scrollPanelComment = new JScrollPane();
      scrollPanelComment.setBorder(BorderFactory.createTitledBorder("帐单附注"));
      scrollPanelComment.setViewportView(textAreaComment);

      JPanel panelPrice = new JPanel();
      panelPrice.setLayout(new FlowLayout(FlowLayout.LEADING));
      panelPrice.add(labelPrice);
      panelPrice.add(textFieldPrice);

      JPanel panelBillInfo = new JPanel();
      panelBillInfo.setLayout(new BorderLayout());
      panelBillInfo.add(panelPrice, BorderLayout.NORTH);
      panelBillInfo.add(scrollPanelComment, BorderLayout.CENTER);

      JPanel inputPanel = new JPanel();
      inputPanel.setLayout(new GridLayout(2, 1));
      inputPanel.add(panelBillItem);
      inputPanel.add(panelBillInfo);

      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
      buttonPanel.add(buttonSendBill);
      buttonPanel.add(buttonCancelBill);
      buttonPanel.add(buttonCheckoutBill);

      this.getContentPane().setLayout(new BorderLayout());
      this.getContentPane().add(inputPanel, BorderLayout.CENTER);
      this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

      JMenuItem menuItemAddBillItemByCode = popupMenu.add("按代号");
      menuItemAddBillItemByCode.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // Add bill item by code.
            FoodSearchDialog dialog = new FoodSearchDialog(
                    FoodSearchDialog.MODE_CODE);
            dialog.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
            dialog.setModal(true);
            dialog.setBill(bill);
            dialog.addAddNewActionListener(billItemListFromBillTable);
            dialog.addUpdateActionListener(billItemListFromBillTable);
            dialog.setVisible(true);
         }
      });
      
      JMenuItem menuItemAddBillItemByName = popupMenu.add("按关键字");
      menuItemAddBillItemByName.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // Add bill item by name.
            FoodSearchDialog dialog = new FoodSearchDialog(
                    FoodSearchDialog.MODE_KEYWORD);
            dialog.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
            dialog.setModal(true);
            dialog.setBill(bill);
            dialog.addAddNewActionListener(billItemListFromBillTable);
            dialog.addUpdateActionListener(billItemListFromBillTable);
            dialog.setVisible(true);
         }
      });
      
      JMenuItem menuItemAddBillItemByCategory = popupMenu.add("按分类");
      menuItemAddBillItemByCategory.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Add bill item by category.
         }
      });

      buttonAddBillItem.addMouseListener(new MouseListener() {
         @Override
         public void mouseClicked(MouseEvent e) {
            popupMenu.show(rootPane, e.getX(), e.getY());
         }

         @Override
         public void mousePressed(MouseEvent e) {
         }

         @Override
         public void mouseReleased(MouseEvent e) {
         }

         @Override
         public void mouseEntered(MouseEvent e) {
         }

         @Override
         public void mouseExited(MouseEvent e) {
         }
      });

      buttonRemoveBillItem.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            BillItem billItem = billItemListFromBillTable.getSelectedBillItem();
            if (billItem != null) {
               // Cancel the selected bill item.
               billItemListFromBillTable.cancelBillItem(billItem);
            }
         }
      });

      buttonSendBill.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            bill = billManagement.getBillInformation(bill);
            if ((bill != null)
                    && (bill.getId() != null)
                    && (!BillStatus.C.equals(bill.getStatus()))) {
               
               billManagement.setCommentToBill(
                       bill, textAreaComment.getText());
               billManagement.markBillAsSent(bill);

               // Reload bill information.
               bill = billManagement.getBillInformation(bill);

               if (updateActionListener != null) {
                  updateActionListener.updateActionPerformed(bill);
               }

               showMessage("帐单已被送出", "成功");
               // Close this dialog.
               dispose();
            }else {
               showErrorMessage("找不到帐单，对应的帐单可能已被取消", "出错");
            }
         }
      });

      buttonCancelBill.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            cancelBill();
         }
      });

      buttonCheckoutBill.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Add checkout action.
            if (bill == null) {
               showErrorMessage("无法找到对应的帐单", "出错");
               return;
            }

            String sellingPriceString = null;
            BigDecimal sellingPrice = null;

            for (;;) {

               sellingPriceString = JOptionPane.showInputDialog(
                       null,
                       "总价为" + bill.getPrice() + " ，请输入实付金额",
                       "请输入",
                       JOptionPane.INFORMATION_MESSAGE);

               // If user press cancel button, cancel the checkout action.
               if (sellingPriceString == null) {
                  return;
               }

               if (!ifPriceStringValid(sellingPriceString)) {
                  showErrorMessage("输入的金额格式错误", "输入错误");
                  continue;
               }
               
               try {
                  sellingPrice = new BigDecimal(
                          sellingPriceString, MathContext.DECIMAL32);
                  sellingPrice.setScale(2);
               } catch (Throwable t) {
                  t.printStackTrace(System.err);
               }
               if (sellingPrice == null) {
                  showErrorMessage("输入的价格无效", "输入错误");
                  continue;
               }

               // Write selling price to bill.
               billManagement.setSellingPriceToBill(bill, sellingPrice);
               // Checkout bill.
               billManagement.checkoutBill(bill);
            }
         }
      });

      // Set the default size.
      this.setSize(500, 400);
   }

   public void setBill(Bill b) {
      this.bill = b;
      this.bill = billManagement.getBillInformation(this.bill);
      if (bill != null) {
         billItemListFromBillTable.reloadTable(this.bill);
         String textPrice
                 = ((b.getPrice()==null)?"":b.getPrice().toPlainString());
         textFieldPrice.setText(textPrice);
         textAreaComment.setText(this.bill.getComment());
      }
   }

   /**
    * Make new bill for specified sale place.
    * @param s
    */
   public void makeBill(SalePlace s) {
      // Make new bill for specified sale place.
      if ((s != null) && (s.getId() != null)
              && (ExistKey.E.equals(s.getEk()))) {
         
         Bill newBill = new Bill();
         newBill.setBoughtTime(new Date());
         newBill.setSalePlaceId(s.getId());
         newBill.setSalePlaceName(s.getName());
         newBill.setStatus(BillStatus.N); // Save status to "Not Sent".
         billManagement.makeBill(newBill);

         // Tell added bill to listener.
         if (addNewActionListener != null) {
            addNewActionListener.addNewActionPerformed(newBill);
         }
      }
   }

   public void cancelBill() {

      if (JOptionPane.showConfirmDialog(this, "是否取消帐单？", "请确认",
              JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE)
                  == JOptionPane.OK_OPTION) {

         if ((this.bill != null)
                 && (this.bill.getId() != null)
                 && (ExistKey.E.equals(this.bill.getEk()))) {

            billManagement.cancelBill(this.bill);

            bill = billManagement.getBillInformation(bill);

            // Tell the canceled bill to listener.
            if (updateActionListener != null) {
               updateActionListener.updateActionPerformed(bill);
            }

            // Close this dialog.
            this.dispose();
         } else {
            showErrorMessage("找不到对应的帐单，请关闭", "出错");
         }
      }
   }

   public void addAddNewActionListener(AddNewActionListener listener) {
      this.addNewActionListener = listener;
   }

   public void addUpdateActionListener(UpdateActionListener listener) {
      this.updateActionListener = listener;
   }

   private void showErrorMessage(String message, String title) {
      JOptionPane.showMessageDialog(
              this, message, title, JOptionPane.ERROR_MESSAGE);
   }

   private void showMessage(String message, String title) {
      JOptionPane.showMessageDialog(
              this, message, title, JOptionPane.INFORMATION_MESSAGE);
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

   private class BillItemEditDialog extends JDialog {

      public BillItemEditDialog() {

         JPanel panelStatus = new JPanel();
         // TODO Complete bill item status panel.
      }
   }
}
