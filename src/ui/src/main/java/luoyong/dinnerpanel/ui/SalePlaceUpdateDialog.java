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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import luoyong.dinnerpanel.dao.model.ExistKey;
import luoyong.dinnerpanel.dao.model.SalePlace;
import luoyong.dinnerpanel.dao.model.SalePlaceServiceStatus;
import luoyong.dinnerpanel.dao.model.SalePlaceStatus;
import luoyong.dinnerpanel.service.SalePlaceManagement;
import luoyong.dinnerpanel.ui.component.AddNewActionListener;
import luoyong.dinnerpanel.ui.component.UpdateActionListener;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class SalePlaceUpdateDialog extends JDialog {

   // Invisible field.
   private Long salePlaceId = null;

   SalePlaceManagement salePlaceManagement = null;

   private AddNewActionListener addNewActionListener = null;
   private UpdateActionListener updateActionListener = null;

   private JLabel labelName = null;
   private JLabel labelServiceStatus = null;
   private JLabel labelStatus = null;

   private JTextField textFieldName = null;
   private JComboBox comboBoxServiceStatus = null;
   private JComboBox comboBoxStatus = null;

   private JTextArea textAreaDesc = null;

   private JButton buttonConfirm;
   private JButton buttonCancel;

   public SalePlaceUpdateDialog() {

      this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

      salePlaceManagement = new SalePlaceManagement();

      labelName = new JLabel("餐桌名称");
      labelServiceStatus = new JLabel("工作状态");
      labelStatus = new JLabel("可用状态");

      textFieldName = new JTextField(10);
      comboBoxServiceStatus = new JComboBox();
      for (SalePlaceServiceStatus serviceStatus
              : SalePlaceServiceStatus.values()) {
         comboBoxServiceStatus.addItem(serviceStatus);
      }
      comboBoxStatus = new JComboBox();
      for (SalePlaceStatus status : SalePlaceStatus.values()) {
         comboBoxStatus.addItem(status);
      }

      JPanel parameterPanel = new JPanel();
      
      GroupLayout layout = new GroupLayout(parameterPanel);
      parameterPanel.setLayout(layout);

      layout.setAutoCreateContainerGaps(true);
      layout.setAutoCreateGaps(true);
      
      layout.setHorizontalGroup(layout.createSequentialGroup()
              .addGroup(layout.createParallelGroup()
                  .addComponent(labelName)
                  .addComponent(labelServiceStatus)
                  .addComponent(labelStatus))
              .addGroup(layout.createParallelGroup()
                  .addComponent(textFieldName)
                  .addComponent(comboBoxServiceStatus)
                  .addComponent(comboBoxStatus)));
      
      layout.setVerticalGroup(layout.createSequentialGroup()
              .addGroup(
                  layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                     .addComponent(labelName)
                     .addComponent(textFieldName))
              .addGroup(
                  layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                     .addComponent(labelServiceStatus)
                     .addComponent(comboBoxServiceStatus))
              .addGroup(
                  layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                     .addComponent(labelStatus)
                     .addComponent(comboBoxStatus)));

      textAreaDesc = new JTextArea();

      JScrollPane scrollPanelDesc = new JScrollPane();
      scrollPanelDesc.setViewportView(textAreaDesc);
      scrollPanelDesc.setBorder(BorderFactory.createTitledBorder("介绍"));

      JPanel inputPanel = new JPanel();
      inputPanel.setLayout(new GridLayout(2, 1));
      inputPanel.add(parameterPanel);
      inputPanel.add(scrollPanelDesc);

      buttonConfirm = new JButton("确认");
      buttonCancel = new JButton("取消");

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
            if ((textFieldName.getText() == null)
                    || (textFieldName.getText().trim().length() < 1)) {

               showErrorMessage("餐桌名不能为空", "输入错误");
               return;
            }

            SalePlace salePlace = new SalePlace();
            salePlace.setEk(ExistKey.E);
            salePlace.setId(salePlaceId);
            salePlace.setName(textFieldName.getText());
            salePlace.setType("D");
            salePlace.setDescription(textAreaDesc.getText());
            if ((comboBoxServiceStatus.getSelectedItem() != null)
                    && (comboBoxServiceStatus.getSelectedItem()
                        instanceof SalePlaceServiceStatus)) {
               
               salePlace.setServiceStatus((SalePlaceServiceStatus)
                       comboBoxServiceStatus.getSelectedItem());
            }
            if ((comboBoxStatus.getSelectedItem() != null)
                    && (comboBoxStatus.getSelectedItem()
                        instanceof SalePlaceStatus)) {

               salePlace.setStatus((SalePlaceStatus)
                       comboBoxStatus.getSelectedItem());
            }

            SalePlace salePlaceInSystem
                    = salePlaceManagement.getSalePlace(salePlace);

            if (salePlace.getId() != null) {
               if (salePlaceInSystem == null) {
                  showErrorMessage("所要更新的餐桌信息不存在，请取消", "错误");
                  return;
               }else {
                  salePlaceManagement.updateSalePlace(salePlace);
                  showMessage("餐桌信息成功更新", "成功");
                  if (updateActionListener != null) {
                     updateActionListener.updateActionPerformed(salePlace);
                  }
                  // Close this dialog.
                  dispose();
               }
            }else {
               if (salePlaceInSystem != null) {
                  showErrorMessage("所要添加的餐桌已存在，请取消", "错误");
                  return;
               }else {
                  salePlaceManagement.addSalePlace(salePlace);
                  showMessage("餐桌信息成功添加", "成功");
                  if (addNewActionListener != null) {
                     addNewActionListener.addNewActionPerformed(salePlace);
                  }
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

      // Set default size.
      this.pack();
   }

   public void setSalePlace(SalePlace s) {
      if ((s != null) && (ExistKey.E.equals(s.getEk()))) {
         this.setSalePlaceId(s.getId());
         textFieldName.setText(s.getName());
         comboBoxServiceStatus.setSelectedItem(s.getServiceStatus());
         comboBoxStatus.setSelectedItem(s.getStatus());
         textAreaDesc.setText(s.getDescription());
      }
   }

   public void addAddNewActionListener(AddNewActionListener listener) {
      this.addNewActionListener = listener;
   }

   public void addUpdateActionListener(UpdateActionListener listener) {
      this.updateActionListener = listener;
   }

   public void showErrorMessage(String message, String title) {
      JOptionPane.showMessageDialog(
              this, message, title, JOptionPane.ERROR_MESSAGE);
   }

   public void showMessage(String message, String title) {
      JOptionPane.showMessageDialog(
              this, message, title, JOptionPane.INFORMATION_MESSAGE);
   }

   private void setSalePlaceId(Long id) {
      if (id != null) {
         this.salePlaceId = id;
         this.setTitle("更新餐桌信息");
      }else {
         this.setTitle("添加餐桌");
      }
   }
}
