package luoyong.dinnerpanel.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.export.JRGraphics2DExporterParameter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class ReportPanel extends JPanel {

   public static final String NAME = "REPORT";

   public static final int DEFAULT_TEXT_REPORT_WIDTH = 80;
   public static final int DEFAULT_TEXT_REPORT_HEIGHT = 80;

   public static final int DEFAULT_GRAPHICS_REPORT_WIDTH = 750;
   public static final int DEFAULT_GRAPHICS_REPORT_HEIGHT = 550;

   private JTextArea textAreaReport = null;
   private JPanel panelGraphicsReport = null;

   private CardLayout layout = null;

   private JasperPrint jasperPrint = null;

   private StringBuffer reportText = null;
   private BufferedImage reportImage = null;

   JPanel panelReport = null;

   private JButton buttonSwitchToTextReport = null;
   private JButton buttonSwitchToGraphicsReport = null;
   private JButton buttoSaveReport = null;

   public ReportPanel() {
      super();

      // Initial place for text report.
      textAreaReport = new JTextArea();
      textAreaReport.setEditable(false);

      // Initial place for graphic report.
      panelGraphicsReport = new JPanel() {
         @Override
         protected void paintComponent(Graphics g) {
            if (reportImage != null) {
               g.drawImage(reportImage, 0, 0, this);
            }
         }
      };
      panelGraphicsReport.setPreferredSize(
              new Dimension(DEFAULT_GRAPHICS_REPORT_WIDTH,
               DEFAULT_GRAPHICS_REPORT_HEIGHT));
      panelGraphicsReport.setMinimumSize(
              new Dimension(DEFAULT_GRAPHICS_REPORT_WIDTH,
               DEFAULT_GRAPHICS_REPORT_HEIGHT));

      buttonSwitchToTextReport = new JButton("切换到文字报表");
      buttonSwitchToGraphicsReport = new JButton("切换到图形报表");
      buttoSaveReport = new JButton("保存报表");

      layout = new CardLayout();

      JScrollPane scrollPaneTextReport = new JScrollPane(textAreaReport);
      JScrollPane scrollPaneGraphicsReport
              = new JScrollPane(panelGraphicsReport);

      panelReport = new JPanel();
      panelReport.setLayout(layout);
      panelReport.add(scrollPaneTextReport);
      panelReport.add(scrollPaneGraphicsReport);

      JPanel panelButton = new JPanel();
      panelButton.setLayout(new FlowLayout(FlowLayout.CENTER));
      panelButton.add(buttonSwitchToTextReport);
      panelButton.add(buttonSwitchToGraphicsReport);
      panelButton.add(buttoSaveReport);

      buttonSwitchToTextReport.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            switchToTextReport();
         }
      });

      buttonSwitchToGraphicsReport.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            switchToGraphicsReport();
         }
      });

      this.setLayout(new BorderLayout());
      this.add(panelReport, BorderLayout.CENTER);
      this.add(panelButton, BorderLayout.SOUTH);
   }

   public JasperPrint getJasperPrint() {
      return jasperPrint;
   }

   public void setJasperPrint(JasperPrint jasperPrint) {
      this.jasperPrint = jasperPrint;
   }

   public void switchToTextReport() {
      if (this.reportText == null) {
         reportText = new StringBuffer();
         JRTextExporter exporter = new JRTextExporter();
         exporter.setParameter(
                 JRExporterParameter.JASPER_PRINT, this.jasperPrint);
         exporter.setParameter(JRTextExporterParameter.PAGE_WIDTH,
                 DEFAULT_TEXT_REPORT_WIDTH);
         exporter.setParameter(JRTextExporterParameter.PAGE_HEIGHT,
                 DEFAULT_TEXT_REPORT_HEIGHT);
         exporter.setParameter(
                 JRExporterParameter.OUTPUT_STRING_BUFFER, this.reportText);
         // Start export.
         try {
            exporter.exportReport();
         } catch (JRException ex) {
            ex.printStackTrace(System.err);
         }
      }

      this.textAreaReport.setText(this.reportText.toString());
      // Set current position to the first character of the report.
      this.textAreaReport.setCaretPosition(0);

      // Switch to text report panel.
      layout.first(panelReport);
   }

   public void switchToGraphicsReport() {
      if ((this.reportImage == null) && (this.jasperPrint != null)) {
         
         this.reportImage = new BufferedImage(
                 this.jasperPrint.getPageWidth(),
                 this.jasperPrint.getPageHeight(),
                 BufferedImage.TYPE_INT_RGB);

         // Reset the graphics report panel size for the specified report.
         this.panelGraphicsReport.setPreferredSize(
                 new Dimension(this.jasperPrint.getPageWidth(),
                  this.jasperPrint.getPageHeight()));
         this.panelGraphicsReport.setMinimumSize(
                 new Dimension(this.jasperPrint.getPageWidth(),
                  this.jasperPrint.getPageHeight()));
         
         Graphics2D reportGraphics = this.reportImage.createGraphics();
         reportGraphics.setBackground(Color.WHITE);
         try {
            JRGraphics2DExporter exporter = new JRGraphics2DExporter();
            exporter.setParameter(
                    JRExporterParameter.JASPER_PRINT, this.jasperPrint);
            exporter.setParameter(
                    JRGraphics2DExporterParameter.GRAPHICS_2D, reportGraphics);
            exporter.exportReport();
         } catch (JRException ex) {
            ex.printStackTrace(System.err);
         }
      }

      // Repaint the graphics report panel.
      this.panelGraphicsReport.repaint();
      // Switch to graphics report panel.
      layout.last(panelReport);
   }
}
