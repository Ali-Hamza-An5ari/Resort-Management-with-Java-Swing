import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class staffLoginForm extends  JDialog{
    static JFrame frame = new JFrame();
    private JPanel mainPanel;
    private JTextField textField1;
    private JLabel lblUser;
    private JTextField textField2;
    private JLabel lblPass;
    private JButton signInButton;
    private JLabel lblHead;
    private String[] validCredents = new String[2];
    actionsForm af = new actionsForm();
    public staffLoginForm() {

        signInButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {

                if((textField1.getText().length() != 0 && textField2.getText().length() != 0))
                {


                    validCredents = staffSignup.getCredentials();
                    if(textField1.getText().equals(validCredents[0]) && textField2.getText().equals(validCredents[1]))
                    {
                            frame.dispose();
                            af.showForm();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Enter valid credentials");

                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"One field is empty");
                }

                dispose();
            }
        });


    }
    public JPanel getter(){
        return this.mainPanel;
    }
    public void showForm()
    {
        frame = new JFrame("Staff Login");
        frame.setContentPane(new staffLoginForm().getter());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
