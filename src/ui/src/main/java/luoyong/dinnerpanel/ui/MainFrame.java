package luoyong.dinnerpanel.ui;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class MainFrame {

   private static MainPanel mainPanel = null;

   private static LinkedHashMap<String, JInternalFrame> frameRecord
           = new LinkedHashMap<String, JInternalFrame>();

   public static void showMainFrame() {

      GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
      GraphicsDevice gDevice = gEnv.getDefaultScreenDevice();

      JFrame f = new JFrame();
      JButton b = new JButton("Click");

      if (mainPanel == null) {
         mainPanel = new MainPanel();
      }

      JScrollPane panel = new JScrollPane(mainPanel);
      f.getContentPane().setLayout(new BorderLayout());
      f.getContentPane().add(panel, BorderLayout.CENTER);
      f.getContentPane().add(b, BorderLayout.SOUTH);

      b.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            System.exit(0);
         }
      });

      gDevice.setFullScreenWindow(f);

//      f.setSize(700,500);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setVisible(true);
   }

   public static void addInternalFrame(
           String name, JInternalFrame internalFrame) {

      if (mainPanel != null) {
         mainPanel.getDesktopPanel().add(internalFrame);
         frameRecord.put(name, internalFrame);
      }else {
         JOptionPane.showMessageDialog(
                 null, "主窗口未初始化", "出错", JOptionPane.ERROR_MESSAGE);
      }
   }

   public static JInternalFrame createDefaultInternalFrame() {
      final JInternalFrame frame = new JInternalFrame(
              "操作窗口", true, true, true, true);
      frame.setLayout(new BorderLayout());
      frame.pack();
      frame.addInternalFrameListener(new InternalFrameListener() {
         @Override
         public void internalFrameOpened(InternalFrameEvent e) {
         }
         @Override
         public void internalFrameClosing(InternalFrameEvent e) {
            MainFrame.removeFrameFromFrameRecord(frame);
            frame.dispose();
         }
         @Override
         public void internalFrameClosed(InternalFrameEvent e) {
         }
         @Override
         public void internalFrameIconified(InternalFrameEvent e) {
         }
         @Override
         public void internalFrameDeiconified(InternalFrameEvent e) {
         }
         @Override
         public void internalFrameActivated(InternalFrameEvent e) {
         }
         @Override
         public void internalFrameDeactivated(InternalFrameEvent e) {
         }
      });
      return frame;
   }

   public static void removeFrameFromFrameRecord(JInternalFrame frame) {
      if (frameRecord != null) {
         Set keySet = frameRecord.keySet();
         if (keySet != null) {
            Iterator<String> keyIterator = keySet.iterator();
            if (keyIterator != null) {
               if (keyIterator.hasNext()) {
                  String key = keyIterator.next();
                  if (frameRecord.get(key) == frame) {
                     frameRecord.remove(key);
                  }
               }
            }
         }
      }
   }

   public static JInternalFrame getFrameFromFrameRecord(String name) {
      if (frameRecord != null) {
         return frameRecord.get(name);
      }else {
         return null;
      }
   }
}
