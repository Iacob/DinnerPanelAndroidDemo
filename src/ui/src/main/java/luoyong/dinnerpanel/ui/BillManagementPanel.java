package luoyong.dinnerpanel.ui;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeSelectionModel;
import luoyong.dinnerpanel.dao.model.Bill;
import luoyong.dinnerpanel.dao.model.SalePlace;
import luoyong.dinnerpanel.ui.component.BillListFromSalePlaceTable;
import luoyong.dinnerpanel.ui.component.SalePlaceFromSaleSiteTree;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class BillManagementPanel extends JPanel {

   public static final String NAME = "BILL_MANAGEMENT";

   SalePlaceFromSaleSiteTree salePlaceFromSaleSiteTree = null;

   BillListFromSalePlaceTable billListFromSalePlaceTable = null;

   JButton buttonMakeBill = null;
   JButton buttonCheckout = null;
   JButton buttonCancel = null;
   JButton buttonEdit = null;

   public BillManagementPanel() {

      salePlaceFromSaleSiteTree = new SalePlaceFromSaleSiteTree();
      salePlaceFromSaleSiteTree.reloadTree();

      billListFromSalePlaceTable = new BillListFromSalePlaceTable();

      buttonMakeBill = new JButton("开新帐单");
      buttonCheckout = new JButton("结帐...");
      buttonCancel = new JButton("取消帐单...");
      buttonEdit = new JButton("编辑帐单...");
      
      JScrollPane scrollPanelSalePlace = new JScrollPane();
      scrollPanelSalePlace.setViewportView(salePlaceFromSaleSiteTree);

      JScrollPane scrollPanelBillList = new JScrollPane();
      scrollPanelBillList.setViewportView(billListFromSalePlaceTable);

      JPanel buttonPanelBillList = new JPanel();
      buttonPanelBillList.setLayout(new FlowLayout(FlowLayout.CENTER));
      buttonPanelBillList.add(buttonMakeBill);
      buttonPanelBillList.add(buttonCheckout);
      buttonPanelBillList.add(buttonCancel);
      buttonPanelBillList.add(buttonEdit);

      JPanel panelBillList = new JPanel();
      panelBillList.setLayout(new BorderLayout());
      panelBillList.add(scrollPanelBillList, BorderLayout.CENTER);
      panelBillList.add(buttonPanelBillList, BorderLayout.SOUTH);

      JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
      splitPanel.setLeftComponent(scrollPanelSalePlace);
      splitPanel.setRightComponent(panelBillList);

      this.setLayout(new BorderLayout());
      this.add(splitPanel, BorderLayout.CENTER);

      TreeSelectionModel salePlaceTreeSelectionModel
              = salePlaceFromSaleSiteTree.getSelectionModel();
      salePlaceTreeSelectionModel.addTreeSelectionListener(
              new TreeSelectionListener() {

         @Override
         public void valueChanged(TreeSelectionEvent e) {
            SalePlace salePlace
                    = salePlaceFromSaleSiteTree.getSelectedSalePlace();
            if (salePlace != null) {
               billListFromSalePlaceTable.reloadTable(salePlace);
            }
         }
      });

      buttonMakeBill.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            
            SalePlace salePlace
                    = salePlaceFromSaleSiteTree.getSelectedSalePlace();

            if (salePlace != null) {
               BillItemManagementDialog dialog = new BillItemManagementDialog();
               dialog.setModalityType(ModalityType.APPLICATION_MODAL);
               dialog.setModal(true);
               dialog.addAddNewActionListener(billListFromSalePlaceTable);
               dialog.addUpdateActionListener(billListFromSalePlaceTable);
               dialog.makeBill(salePlace);

               showMessage("新帐单已创建", "成功");

               dialog.setVisible(true);
            }
         }
      });

      buttonCancel.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

            Bill bill = billListFromSalePlaceTable.getSelectedBill();

            if (bill != null) {
               BillItemManagementDialog dialog = new BillItemManagementDialog();
               dialog.setModalityType(ModalityType.APPLICATION_MODAL);
               dialog.setModal(true);
               dialog.addAddNewActionListener(billListFromSalePlaceTable);
               dialog.addUpdateActionListener(billListFromSalePlaceTable);
               dialog.setBill(bill);
               dialog.setVisible(true);
               // Cancel selected bill.
               dialog.cancelBill();
            }
         }
      });

      buttonEdit.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

            Bill bill = billListFromSalePlaceTable.getSelectedBill();

            if (bill != null) {
               BillItemManagementDialog dialog = new BillItemManagementDialog();
               dialog.setModalityType(ModalityType.APPLICATION_MODAL);
               dialog.setModal(true);
               dialog.addAddNewActionListener(billListFromSalePlaceTable);
               dialog.addUpdateActionListener(billListFromSalePlaceTable);
               dialog.setBill(bill);
               dialog.setVisible(true);
            }
         }
      });
   }

   public void showMessage(String message, String title) {
      JOptionPane.showMessageDialog(
              this, message, title, JOptionPane.INFORMATION_MESSAGE);
   }
}
