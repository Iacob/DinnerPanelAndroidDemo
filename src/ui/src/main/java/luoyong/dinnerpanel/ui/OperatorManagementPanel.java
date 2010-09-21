package luoyong.dinnerpanel.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import luoyong.dinnerpanel.dao.model.Operator;
import luoyong.dinnerpanel.ui.component.IconButton;
import luoyong.dinnerpanel.ui.component.OperatorListTable;
import luoyong.dinnerpanel.ui.icon.IconConstant;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class OperatorManagementPanel extends JPanel {

   public static final String NAME = "OPERATOR_MANAGEMENT";

   OperatorListTable operatorListTable = null;

   IconButton buttonAddOperator = null;
   IconButton buttonModifyOperatorInformation = null;
   IconButton buttonRemoveOperatorInformation = null;

   public OperatorManagementPanel() {

      operatorListTable = new OperatorListTable();
      operatorListTable.reloadTable();

      JScrollPane scrollPanelOperatorList = new JScrollPane();
      scrollPanelOperatorList.setViewportView(operatorListTable);

      buttonAddOperator= new IconButton(
              "添加操作员", IconConstant.ICON_OPERATOR_ADD);
      buttonModifyOperatorInformation = new IconButton(
              "修改操作员信息", IconConstant.ICON_OPERATOR_EDIT);
      buttonRemoveOperatorInformation = new IconButton(
              "删除操作员信息", IconConstant.ICON_OPERATOR_REMOVE);

      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
      buttonPanel.add(buttonAddOperator);
      buttonPanel.add(buttonModifyOperatorInformation);
      buttonPanel.add(buttonRemoveOperatorInformation);

      this.setLayout(new BorderLayout());
      this.add(scrollPanelOperatorList, BorderLayout.CENTER);
      this.add(buttonPanel, BorderLayout.SOUTH);

      buttonAddOperator.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            OperatorInformationUpdateDialog dialog
                    = new OperatorInformationUpdateDialog();
            dialog.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
            dialog.setModal(true);
            dialog.addAddNewActionListener(operatorListTable);
            dialog.addUpdateActionListener(operatorListTable);
            dialog.setVisible(true);
         }
      });

      buttonModifyOperatorInformation.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            Operator operator
                    = operatorListTable.getSelectedOperatorInformation();
            if (operator != null) {
               OperatorInformationUpdateDialog dialog
                    = new OperatorInformationUpdateDialog();
               dialog.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
               dialog.setModal(true);
               dialog.setOperatorInformation(operator);
               dialog.addAddNewActionListener(operatorListTable);
               dialog.addUpdateActionListener(operatorListTable);
               dialog.setVisible(true);
            }
         }
      });

      buttonRemoveOperatorInformation.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            Operator operator
                    = operatorListTable.getSelectedOperatorInformation();
            if (operator != null) {
               operatorListTable.removeOperator(operator);
            }
         }
      });
   }
}
