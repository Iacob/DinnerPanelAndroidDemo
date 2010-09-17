package luoyong.dinnerpanel.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import luoyong.dinnerpanel.rwsclient.RWSURLHolder;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class LoginFrame extends JFrame {

   private JTextField textFieldEndPoint = null;
   private JTextField textFieldUsername = null;
   private JPasswordField passwordFieldPassword = null;

   private JButton buttonLogin = null;

   public LoginFrame() {
      
      super();

      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

      this.setTitle("登陆");

      textFieldEndPoint = new JTextField(10);
      textFieldUsername = new JTextField(10);
      passwordFieldPassword = new JPasswordField(10);

      buttonLogin = new JButton("登陆");

      JPanel panelUserInput = new JPanel();

      GroupLayout layout = new GroupLayout(panelUserInput);
      panelUserInput.setLayout(layout);
      this.getContentPane().setLayout(new BorderLayout());
      this.getContentPane().add(panelUserInput, BorderLayout.CENTER);
      
      this.getContentPane().add(buttonLogin, BorderLayout.SOUTH);

      layout.setAutoCreateGaps(true);
      layout.setAutoCreateContainerGaps(true);

      JLabel labelEndPoint = new JLabel("服务器URL");
      JLabel labelUsername = new JLabel("用户名");
      JLabel labelPassword = new JLabel("密码");

      layout.setHorizontalGroup(layout.createSequentialGroup()
              .addGroup(layout.createParallelGroup()
                  .addComponent(labelEndPoint)
                  .addComponent(labelUsername)
                  .addComponent(labelPassword))
              .addGroup(layout.createParallelGroup()
                  .addComponent(textFieldEndPoint)
                  .addComponent(textFieldUsername)
                  .addComponent(passwordFieldPassword)));

      layout.setVerticalGroup(layout.createSequentialGroup()
              .addGroup(layout.createParallelGroup()
                  .addComponent(labelEndPoint)
                  .addComponent(textFieldEndPoint))
              .addGroup(layout.createParallelGroup()
                  .addComponent(labelUsername)
                  .addComponent(textFieldUsername))
              .addGroup(layout.createParallelGroup()
                  .addComponent(labelPassword)
                  .addComponent(passwordFieldPassword)));

      buttonLogin.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

            RWSURLHolder.setBaseURL(textFieldEndPoint.getText());

            Authenticator.setDefault(new Authenticator() {
               @Override
               protected PasswordAuthentication getPasswordAuthentication() {
                  return new PasswordAuthentication(
                          textFieldUsername.getText(),
                          passwordFieldPassword.getPassword());
               }
            });

            MainFrame.showMainFrame();

            // Close the login frame.
            dispose();
         }
      });

      this.pack();
   }
}
