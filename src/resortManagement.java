import javax.swing.*;

public class resortManagement
{
    static staffSignup sf;
    public static void main(String arg[])
    {


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                sf = new staffSignup();
                sf.showForm();

            }
        });

    }

}
