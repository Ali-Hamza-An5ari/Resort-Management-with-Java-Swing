import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class staffSignup {
    static JFrame frame  = new JFrame("staffSignup");
    private JPanel mainPanel;
    private JTextField textField1;
    private JLabel lblHead;
    private JLabel lblUser;
    private JTextField textField2;
    private JLabel lblPass;
    private JButton signUpButton;
    private JPanel signupP;
    private static String[] credentials = new String[2];
    staffLoginForm slf = new staffLoginForm();
    public static String[] getCredentials() {
        return credentials;
    }

    public static void setCredentials(String[] credentials) {
        staffSignup.credentials = credentials;
    }
    public void showForm()
    {
        frame.setContentPane(new staffSignup().getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public staffSignup()
    {

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                credentials[0] = textField1.getText();
                credentials[1] = textField2.getText();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {

                    frame.dispose();
                    slf.showForm();


                    }
                });
            }
        });
    }
    public void closeForm()
    {
        frame.dispose();
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
