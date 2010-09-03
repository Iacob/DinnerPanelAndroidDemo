package luoyong.dinnerpanel.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import luoyong.dinnerpanel.dao.model.ExistKey;
import luoyong.dinnerpanel.dao.model.Operator;
import luoyong.dinnerpanel.dao.model.OperatorStatus;
import luoyong.dinnerpanel.service.OperatorManagement;
import luoyong.dinnerpanel.ui.component.AddNewActionListener;
import luoyong.dinnerpanel.ui.component.UpdateActionListener;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class OperatorInformationUpdateDialog extends JDialog {

   // Stores the invisible parameter.
   private boolean update = false;

   private AddNewActionListener addNewActionListener = null;
   private UpdateActionListener updateActionListener = null;

   private OperatorManagement operatorManagement = null;

   private JLabel labelId;
   private JLabel labelName;
   private JLabel labelPassword;
   private JLabel labelPasswordConfirm;
   private JLabel labelStatus;

   private JTextField textFieldId;
   private JTextField textFieldName;
   private JPasswordField passwordFieldPassword;
   private JPasswordField passwordFieldPasswordConfirm;
   private JComboBox comboBoxStatus;

   private JTextArea textAreaDesc;

   private JButton buttonConfirm;
   private JButton buttonCancel;

   public OperatorInformationUpdateDialog() {

      this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

      operatorManagement = new OperatorManagement();

      labelId = new JLabel("ID:");
      labelName = new JLabel("姓名:");
      labelPassword = new JLabel("密码:");
      labelPasswordConfirm = new JLabel("确认密码");
      labelStatus = new JLabel("状态:");

      textFieldId = new JTextField(10);
      textFieldName = new JTextField(10);
      passwordFieldPassword = new JPasswordField(10);
      passwordFieldPasswordConfirm = new JPasswordField(10);

      // Initial the password field.
      passwordFieldPassword.setText("");
      passwordFieldPasswordConfirm.setText("");

      comboBoxStatus = new JComboBox();
      OperatorStatus operatorStatusArray[] = OperatorStatus.values();
      for (OperatorStatus operatorStatus : operatorStatusArray) {
         comboBoxStatus.addItem(operatorStatus);
      }
      comboBoxStatus.setSelectedItem(OperatorStatus.P);

      textAreaDesc = new JTextArea();

      buttonConfirm = new JButton("确定");
      buttonCancel = new JButton("取消");

      JPanel parameterPanel = new JPanel();

      GroupLayout layout = new GroupLayout(parameterPanel);
      parameterPanel.setLayout(layout);
      layout.setAutoCreateContainerGaps(true);
      layout.setAutoCreateGaps(true);

      layout.setHorizontalGroup(layout.createSequentialGroup()
              .addGroup(layout.createParallelGroup()
                  .addComponent(labelId)
                  .addComponent(labelName)
                  .addComponent(labelStatus)
                  .addComponent(labelPassword)
                  .addComponent(labelPasswordConfirm))
              .addGroup(layout.createParallelGroup()
                  .addComponent(textFieldId)
                  .addComponent(textFieldName)
                  .addComponent(comboBoxStatus)
                  .addComponent(passwordFieldPassword)
                  .addComponent(passwordFieldPasswordConfirm)));

      layout.setVerticalGroup(layout.createSequentialGroup()
              .addGroup(layout.createParallelGroup()
                  .addComponent(labelId)
                  .addComponent(textFieldId))
              .addGroup(layout.createParallelGroup()
                  .addComponent(labelName)
                  .addComponent(textFieldName))
              .addGroup(layout.createParallelGroup()
                  .addComponent(labelStatus)
                  .addComponent(comboBoxStatus))
              .addGroup(layout.createParallelGroup()
                  .addComponent(labelPassword)
                  .addComponent(passwordFieldPassword))
              .addGroup(layout.createParallelGroup()
                  .addComponent(labelPasswordConfirm)
                  .addComponent(passwordFieldPasswordConfirm)));

      JScrollPane scrollPaneDesc = new JScrollPane();
      scrollPaneDesc.setViewportView(textAreaDesc);

      JPanel panelDesc = new JPanel();
      panelDesc.setLayout(new BorderLayout());
      panelDesc.setBorder(BorderFactory.createTitledBorder("描述"));
      panelDesc.add(scrollPaneDesc, BorderLayout.CENTER);

      JPanel inputPanel = new JPanel();
      inputPanel.setLayout(new GridLayout(2, 1));

      inputPanel.add(parameterPanel);
      inputPanel.add(panelDesc);

      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
      buttonPanel.add(buttonConfirm);
      buttonPanel.add(buttonCancel);

      this.getContentPane().setLayout(new BorderLayout());
      this.getContentPane().add(inputPanel, BorderLayout.CENTER);
      this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

      buttonConfirm.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

            if ((textFieldId.getText() == null)
                    || (textFieldId.getText().trim().length() < 1)) {

               showErrorMessage("用户名不可为空", "输入错误");
               return;
            }

            if ((textFieldName.getText() == null)
                    || (textFieldName.getText().trim().length() < 1)) {

               showErrorMessage("用户姓名不可为空", "输入错误");
               return;
            }

            if (passwordFieldPassword.getPassword() == null) {
               showErrorMessage("密码不可为空", "输入错误");
               return;
            }

            if (passwordFieldPasswordConfirm.getPassword() == null) {
               showErrorMessage("密码不一致", "输入错误");
               return;
            }
            
            String operatorPassword =
                    new String(passwordFieldPassword.getPassword());

            if ((operatorPassword == null)
                    || (operatorPassword.trim().length() < 1)) {
               
               showErrorMessage("密码不可为空", "输入错误");
               return;
            }

            if (!operatorPassword.equals(
                    new String(passwordFieldPasswordConfirm.getPassword()))) {
               showErrorMessage("密码不一致", "输入错误");
               return;
            }

            Operator operator = new Operator();
            operator.setEk(ExistKey.E);
            operator.setId(textFieldId.getText());
            operator.setName(textFieldName.getText());
            operator.setPassword(operatorPassword);
            // Set operator status from the combo box.
            if ((comboBoxStatus.getSelectedItem() != null)
                    && (comboBoxStatus.getSelectedItem()
                        instanceof OperatorStatus)) {

               operator.setStatus(
                       (OperatorStatus)comboBoxStatus.getSelectedItem());
            }
            operator.setDescription(textAreaDesc.getText());

            // Get the correspond operator information from system.
            Operator operatorInSystem = operatorManagement
                    .getOperatorInformation(operator);

            if (update) {
               if (operatorInSystem == null) {
                  showErrorMessage("所要更新的用户不存在，请取消", "错误");
                  return;
               }else {
                  operatorManagement.updateOperatorInformation(operator);
                  // Tell the updated operator to listener.
                  if (updateActionListener != null) {
                     updateActionListener.updateActionPerformed(operator);
                  }
                  showMessage("修改操作员信息成功", "成功");
                  // Close this dialog.
                  dispose();
               }
            }else {
               if (operatorInSystem != null) {
                  showErrorMessage("所要添加的用户已存在，请取消", "错误");
                  return;
               }else {
                  operatorManagement.addOperator(operator);
                  if (addNewActionListener != null) {
                     addNewActionListener.addNewActionPerformed(operator);
                  }
                  showMessage("添加操作员成功", "成功");
                  // Close this dialog.
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
      this.pack();
   }

   public void setOperatorInformation(Operator o) {
      if ((o != null) && (ExistKey.E.equals(o.getEk()))) {
         
         this.setOperatorId(o.getId()); // Set the operator ID text field.
         textFieldName.setText(o.getName());
         passwordFieldPassword.setText(o.getPassword());
         passwordFieldPasswordConfirm.setText(o.getPassword());
         comboBoxStatus.setSelectedItem(o.getStatus());
         textAreaDesc.setText(o.getDescription());
      }
   }

   private void setOperatorId(String id) {
      if ((id != null) && (id.trim().length() > 0)) {
         textFieldId.setText(id);
         textFieldId.setEditable(false);
         this.update = true;
      }else {
         textFieldId.setText("");
         this.update = false;
      }
   }

   private void showErrorMessage(String message, String title) {
      JOptionPane.showMessageDialog(
              this, message, title, JOptionPane.ERROR_MESSAGE);
   }

   private void showMessage(String message, String title) {
      JOptionPane.showMessageDialog(
              this, message, title, JOptionPane.INFORMATION_MESSAGE);
   }

   public void addAddNewActionListener(AddNewActionListener listener) {
      this.addNewActionListener = listener;
   }

   public void addUpdateActionListener(UpdateActionListener listener) {
      this.updateActionListener = listener;
   }
}
