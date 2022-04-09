import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class viewForm {
    private JLabel lblHead;
    private JPanel mainPanelView;
    private JLabel lblView;
    private JLabel lblView2;
    private JLabel lblHead1;
    private JLabel lblHead2;
    private JButton backToHomeButton;
    private static String viewString = "";
    private static JFrame frame = new JFrame();

    public void showForm()
    {
        JFrame frame = new JFrame("View");
        frame.setContentPane(new viewForm().getMainPanelView());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public void closeForm()
    {
        frame.dispose();
    }
    public static String getViewString2() {
        return viewString2;
    }

    public static void setViewString2(String viewString2) {
        viewForm.viewString2 = viewString2;
    }

    private static String viewString2 = "";
    private static String query = "";

    public static String getQuery() {
        return query;
    }

    public JLabel getLblView() {
        return lblView;
    }

    public void setLblViewText(String text) {
        this.lblView.setText(text);
    }

    public static void setQuery(String query) {
        viewForm.query = query;
    }

    public static void setViewString(String viewString) {
        viewForm.viewString = viewString;
    }

    public JPanel getMainPanelView() {
        return mainPanelView;
    }

    public viewForm()
    {

        this.lblView.setText(viewString);
        this.lblView2.setText(viewString2);
        getData();
        backToHomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                actionsForm af = new actionsForm();
                af.showForm();
            }
        });
    }
    public void getData()
    {

                if (query.equals("all"))
                {

                }
                else if(query.equals("update"))
                {

                }
                else
                {


                }



    }
}
