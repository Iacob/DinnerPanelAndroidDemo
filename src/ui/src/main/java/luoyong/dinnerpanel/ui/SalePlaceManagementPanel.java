package luoyong.dinnerpanel.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import luoyong.dinnerpanel.dao.model.SalePlace;
import luoyong.dinnerpanel.ui.component.SalePlaceListFromSaleSiteTable;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class SalePlaceManagementPanel extends JPanel {

   public static final String NAME = "SALE_PLACE_MANAGEMENT";

//   private SalePlaceManagement salePlaceManagement = null;

   private SalePlaceListFromSaleSiteTable salePlaceListFromSaleSiteTable = null;

   private JButton buttonAddSalePlace = null;
   private JButton buttonModifySalePlace = null;
   private JButton buttonRemoveSalePlace = null;

   public SalePlaceManagementPanel() {

//      salePlaceManagement = new SalePlaceManagement();

      salePlaceListFromSaleSiteTable = new SalePlaceListFromSaleSiteTable();
      salePlaceListFromSaleSiteTable.reloadTable();

      JScrollPane scrollPanelSalePlace = new JScrollPane();
      scrollPanelSalePlace.setViewportView(salePlaceListFromSaleSiteTable);

      buttonAddSalePlace = new JButton("添加餐桌");
      buttonModifySalePlace = new JButton("修改餐桌信息");
      buttonRemoveSalePlace = new JButton("删除餐桌");

      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
      buttonPanel.add(buttonAddSalePlace);
      buttonPanel.add(buttonModifySalePlace);
      buttonPanel.add(buttonRemoveSalePlace);

      this.setLayout(new BorderLayout());
      this.add(scrollPanelSalePlace, BorderLayout.CENTER);
      this.add(buttonPanel, BorderLayout.SOUTH);

      buttonAddSalePlace.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            SalePlaceUpdateDialog dialog = new SalePlaceUpdateDialog();
            dialog.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
            dialog.setModal(true);
            dialog.addAddNewActionListener(salePlaceListFromSaleSiteTable);
            dialog.addUpdateActionListener(salePlaceListFromSaleSiteTable);
            dialog.setVisible(true);
         }
      });

      buttonModifySalePlace.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            SalePlace salePlace = salePlaceListFromSaleSiteTable
                    .getSelectedSalePlaceInformation();
            if (salePlace != null) {
               SalePlaceUpdateDialog dialog = new SalePlaceUpdateDialog();
               dialog.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
               dialog.setModal(true);
               dialog.setSalePlace(salePlace);
               dialog.addAddNewActionListener(salePlaceListFromSaleSiteTable);
               dialog.addUpdateActionListener(salePlaceListFromSaleSiteTable);
               dialog.setVisible(true);
            }
         }
      });

      buttonRemoveSalePlace.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            SalePlace salePlace = salePlaceListFromSaleSiteTable
                    .getSelectedSalePlaceInformation();

            if (salePlace != null) {
               salePlaceListFromSaleSiteTable.removeSalePlace(salePlace);
            }
         }
      });
   }
}
