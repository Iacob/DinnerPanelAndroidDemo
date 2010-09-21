package luoyong.dinnerpanel.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import luoyong.dinnerpanel.ui.component.IconButton;
import luoyong.dinnerpanel.ui.icon.IconConstant;
//import luoyong.dinnerpanel.dao.report.ReportDataSource;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class MainPanel extends JPanel {

   JDesktopPane panelDesktop = null;

   IconButton buttonBillManagement = null;
   IconButton buttonFoodManagement = null;
   IconButton buttonOperatorManagement = null;
   IconButton buttonSalePlaceManagement = null;
   IconButton buttonReport = null;

   JPopupMenu popupMenuReport = null;

   public MainPanel() {

      panelDesktop = new JDesktopPane();

      buttonBillManagement = new IconButton(
              "帐单操作", IconConstant.ICON_BILL_MANAGEMENT);
      buttonBillManagement.setEnabled(false);
      buttonFoodManagement = new IconButton(
              "餐品管理", IconConstant.ICON_FOOD_MANAGEMENT);
      buttonOperatorManagement = new IconButton(
              "操作员管理", IconConstant.ICON_OPERATOR_MANAGEMENT);
      buttonSalePlaceManagement = new IconButton(
              "餐桌管理", IconConstant.ICON_SALE_PLACE_MANAGEMENT);
      buttonReport = new IconButton(
              "制作报表", IconConstant.ICON_REPORT_MANAGEMENT);
      buttonReport.setEnabled(false);

      popupMenuReport = new JPopupMenu();
//      JMenuItem menuItemTest = popupMenuReport.add("餐品报表");

      JToolBar toolBar = new JToolBar();
      toolBar.setFloatable(false);
      toolBar.add(buttonFoodManagement);
      toolBar.add(buttonOperatorManagement);
      toolBar.add(buttonSalePlaceManagement);
      toolBar.add(buttonBillManagement);
      toolBar.add(buttonReport);

      this.setLayout(new BorderLayout());
      this.add(toolBar, BorderLayout.NORTH);
      this.add(panelDesktop, BorderLayout.CENTER);

      buttonFoodManagement.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

            JInternalFrame internalFrame = MainFrame
                    .getFrameFromFrameRecord(FoodManagementPanel.NAME);

            if (internalFrame != null) {
               try {
                  internalFrame.setMaximum(false);
                  internalFrame.setMaximum(true);
               } catch (PropertyVetoException ex) {
                  ex.printStackTrace(System.err);
               }
            }else {
               FoodManagementPanel panel = new FoodManagementPanel();

               JInternalFrame frame = MainFrame.createDefaultInternalFrame();
               frame.setTitle("餐品管理");
               MainFrame.addInternalFrame(FoodManagementPanel.NAME, frame);
               frame.add(panel, BorderLayout.CENTER);
               frame.pack();
               frame.setVisible(true);
            }
         }
      });

      buttonOperatorManagement.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

            JInternalFrame internalFrame = MainFrame
                    .getFrameFromFrameRecord(OperatorManagementPanel.NAME);

            if (internalFrame != null) {
               try {
                  internalFrame.setMaximum(false);
                  internalFrame.setMaximum(true);
               } catch (PropertyVetoException ex) {
                  ex.printStackTrace(System.err);
               }
            } else {

               OperatorManagementPanel panel = new OperatorManagementPanel();

               JInternalFrame frame = MainFrame.createDefaultInternalFrame();
               frame.setTitle("操作员管理");
               MainFrame.addInternalFrame(OperatorManagementPanel.NAME, frame);
               frame.add(panel, BorderLayout.CENTER);
               frame.pack();
               frame.setVisible(true);
            }
         }
      });

      buttonBillManagement.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

//            JInternalFrame internalFrame = MainFrame
//                    .getFrameFromFrameRecord(BillManagementPanel.NAME);
//
//            if (internalFrame != null) {
//               try {
//                  internalFrame.setMaximum(false);
//                  internalFrame.setMaximum(true);
//               } catch (PropertyVetoException ex) {
//                  ex.printStackTrace(System.err);
//               }
//            } else {
//
//               BillManagementPanel panel = new BillManagementPanel();
//
//               JInternalFrame frame = MainFrame.createDefaultInternalFrame();
//               frame.setTitle("帐单操作");
//               MainFrame.addInternalFrame(BillManagementPanel.NAME, frame);
//               frame.add(panel, BorderLayout.CENTER);
//               frame.pack();
//               frame.setVisible(true);
//            }
         }
      });

      buttonSalePlaceManagement.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

            JInternalFrame internalFrame = MainFrame
                    .getFrameFromFrameRecord(SalePlaceManagementPanel.NAME);

            if (internalFrame != null) {
               try {
                  internalFrame.setMaximum(false);
                  internalFrame.setMaximum(true);
               } catch (PropertyVetoException ex) {
                  ex.printStackTrace(System.err);
               }
            } else {

               SalePlaceManagementPanel panel = new SalePlaceManagementPanel();

               JInternalFrame frame = MainFrame.createDefaultInternalFrame();
               frame.setTitle("餐桌管理");
               MainFrame.addInternalFrame(SalePlaceManagementPanel.NAME, frame);
               frame.add(panel, BorderLayout.CENTER);
               frame.pack();
               frame.setVisible(true);
            }
         }
      });

      buttonReport.addMouseListener(new MouseListener() {
         @Override
         public void mouseClicked(MouseEvent e) {
            popupMenuReport.show(buttonReport, e.getX(), e.getY());
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

//      menuItemTest.addActionListener(new ActionListener() {
//         @Override
//         public void actionPerformed(ActionEvent e) {
//
//            JInternalFrame internalFrame = MainFrame
//                    .getFrameFromFrameRecord(ReportPanel.NAME);
//
//            if (internalFrame != null) {
//               try {
//                  internalFrame.setMaximum(false);
//                  internalFrame.setMaximum(true);
//               } catch (PropertyVetoException ex) {
//                  ex.printStackTrace(System.err);
//               }
//            } else {
//
//               ReportPanel panel = new ReportPanel();
//
//               ReportDataSource reportDataSource = new ReportDataSource("select f from Food f");
//               reportDataSource.startQuery();
//
//               JasperReport jasperReport = null;
//               JasperPrint jasperPrint = null;
//               try {
//                  jasperReport = JasperCompileManager.compileReport(Thread.currentThread().getClass().getResourceAsStream("/report/ReportCurrentStatus.jrxml"));
//                  jasperPrint = JasperFillManager.fillReport(jasperReport, new LinkedHashMap(), reportDataSource);
//               } catch (JRException ex) {
//                  ex.printStackTrace(System.err);
//               }
//
//               JInternalFrame frame = MainFrame.createDefaultInternalFrame();
//               frame.setTitle("餐品报表");
//               MainFrame.addInternalFrame(ReportPanel.NAME, frame);
//               frame.add(panel, BorderLayout.CENTER);
//               frame.setVisible(true);
//               frame.setSize(750, 550);
//               try {
//                  frame.setMaximum(false);
//                  frame.setMaximum(true);
//               } catch (PropertyVetoException ex) {
//                  ex.printStackTrace(System.err);
//               }
//               panel.setJasperPrint(jasperPrint);
//               panel.switchToTextReport();
//
//               reportDataSource.close();
//            }
//         }
//      });
   }

   public JDesktopPane getDesktopPanel() {
      return this.panelDesktop;
   }
}
