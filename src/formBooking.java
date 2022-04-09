import Classes.Booking;
import Classes.Customer;
import Classes.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class formBooking {
    private JTextField textField1;
    private JLabel lblUser;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JComboBox comboBox1;
    private JButton button1;
    private JLabel lblHead;
    private JPanel mainPanelBooking;
    private JTextField textField7;
    private JTextField textField6;

    private JLabel lblPass;
    private JButton signInButton;

    private static String updateString="";
    ArrayList<String> seaRooms = new ArrayList<String>();
    ArrayList<String> jungleRooms = new ArrayList<String>();
    ArrayList<String> allData = new ArrayList<String>();

    private ArrayList<String> allSeaRooms = new ArrayList<String>();
    private ArrayList<String> allJungleRooms = new ArrayList<String>();
    ArrayList<String> numbers = new ArrayList<String>();

    private static JFrame frame = new JFrame();
    viewForm vf = new viewForm();
    String data2 = "";
    String [] tokens;

    private static boolean forUpdate = false;
    static String data = "";

    public void showForm()
    {
         frame = new JFrame("Book Rooms");
        frame.setContentPane(new formBooking().getBookingPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public void closeForm()
    {
        frame.dispose();
    }
    public static boolean isForUpdate() {
        return forUpdate;
    }

    public static void setForUpdate(boolean forUpdate) {
        forUpdate = forUpdate;
    }

    public static String getUpdateString() {
        return updateString;
    }

    public static void setUpdateString(String updateString) {
        formBooking.updateString = updateString;
    }

    public formBooking()
    {
        forUpdate = actionsForm.isIsUpd();

        for(int i=1 ; i<=10; i++)
        {
            numbers.add(""+i);
        }
        if(forUpdate)
        {
            tokens = updateString.split("[,]",0);
            this.textField1.setText(tokens[3]);
            this.textField2.setText(tokens[4]);
            this.textField3.setText(tokens[5]);
            this.textField5.setText(tokens[6]);
            this.textField4.setText(tokens[9]);
            this.textField4.setText(tokens[8]);
            this.textField7.setText(tokens[0]);
            this.comboBox1.setSelectedItem(tokens[2]);

        }

        this.comboBox1.addItem("Jungle");
        this.comboBox1.addItem("Sea");
        this.comboBox1.setSelectedItem("Sea");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                setRooms();
                if(forUpdate)
                {

                    try {

                        loadData();
                        updateData();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    if(tokens[2].equals("Sea"))
                    {
                        viewForm.setViewString("<html>Room no: "+tokens[0]+"<br/>" +
                                "View: "+tokens[2]+"<br/>" +
                                "Name: "+tokens[3]+"<br/>" +
                                "IC: "+tokens[4]+"<br/>" +
                                "Contact: "+tokens[5]+"<br/>" +
                                "Email: "+tokens[6]+"<br/>" +
                                "Total charges: "+tokens[7]+"<br/>" +
                                "Total days: "+tokens[8]+"<html>" );
                    }
                    else {
                        viewForm.setViewString2("<html>Room no: "+tokens[0]+"<br/>" +
                                "View: "+tokens[2]+"<br/>" +
                                "Name: "+tokens[3]+"<br/>" +
                                "IC: "+tokens[4]+"<br/>" +
                                "Contact: "+tokens[5]+"<br/>" +
                                "Email: "+tokens[6]+"<br/>" +
                                "Total charges: "+tokens[7]+"<br/>" +
                                "Total days: "+tokens[8]+"<html>" );
                    }


                    java.awt.EventQueue.invokeLater(new Runnable()
                    {
                        public void run()
                        {
                            frame.dispose();
                            //view form here
                            vf.showForm();
                        }
                    });
                }
                else
                {


                    loadData();
                    if((seaRooms.contains(textField7.getText())) && comboBox1.getSelectedItem().toString().equals("Sea"))
                    {
                        JOptionPane.showMessageDialog(null,"Room is already occupied");

                    }
                    else if(jungleRooms.contains(textField7.getText()) && comboBox1.getSelectedItem().toString().equals("Jungle"))
                    {
                        JOptionPane.showMessageDialog(null,"Room is already occupied");

                    }
                    else
                    {

                        if(numbers.contains(textField7.getText()))
                        {
                            Room room = new Room(textField7.getText(), true, comboBox1.getSelectedItem().toString());
                            Customer cust = new Customer(textField1.getText(), textField2.getText(), textField3.getText(), textField5.getText());

                            Booking booking = new Booking(room,cust,textField6.getText(),Integer.parseInt(textField4.getText()));
                            writeToFile(booking.toString());
                            tokens = booking.toString().split(",");

                            if(comboBox1.getSelectedItem().toString().equals("Sea"))
                            {
                                viewForm.setViewString("<html>Room no: "+tokens[0]+"<br/>" +
                                        "View: "+tokens[2]+"<br/>" +
                                        "Name: "+tokens[3]+"<br/>" +
                                        "IC: "+tokens[4]+"<br/>" +
                                        "Contact: "+tokens[5]+"<br/>" +
                                        "Email: "+tokens[6]+"<br/>" +
                                        "Total charges: "+tokens[7]+"<br/>" +
                                        "Date: "+tokens[8]+"<br/>" +
                                        "Total days: "+tokens[9]+"<html>" );
                            }
                            else
                            {
                                viewForm.setViewString2("<html>Room no: "+tokens[0]+"<br/>" +
                                        "View: "+tokens[2]+"<br/>" +
                                        "Name: "+tokens[3]+"<br/>" +
                                        "IC: "+tokens[4]+"<br/>" +
                                        "Contact: "+tokens[5]+"<br/>" +
                                        "Email: "+tokens[6]+"<br/>" +
                                        "Total charges: "+tokens[7]+"<br/>" +
                                        "Date: "+tokens[8]+"<br/>" +
                                        "Total days: "+tokens[9]+"<html>" );
                            }


                            java.awt.EventQueue.invokeLater(new Runnable()
                            {
                                public void run()
                                {
                                    frame.dispose();
                                    vf.showForm();
                                }
                            });

                        }
                    }

                }


            }
        });

    }
    public void writeToFile(String b)
    {
        try {

            FileWriter myWriter = new FileWriter("booked.txt",true);
            myWriter.write(b.toString()+ System.getProperty( "line.separator" ));
            myWriter.close();
            JOptionPane.showMessageDialog(null,"Successfully wrote to the file.");


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void setAllData() throws FileNotFoundException {
        allData.clear();
        File myObj = new File("booked.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            data = myReader.nextLine();
            allData.add(data);
        }
        myReader.close();
    }

    public void setRooms()
    {
        try {
            seaRooms.clear();
            jungleRooms.clear();
            allData.clear();
            File myObj = new File("booked.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine())
            {
                String d = myReader.nextLine();


                if((d.length() >0))
                {
                    String[] tok = d.split("[,]",0);


                    if(tok[2].equals("Sea"))
                    {
                        seaRooms.add(tok[0]);

                    }
                    if(tok[2].equals("Jungle"))
                    {
                        jungleRooms.add(tok[0]);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadData()
    {
        try {
            seaRooms.clear();
            jungleRooms.clear();
            allData.clear();

            allSeaRooms.clear();
            allJungleRooms.clear();

            File myObj = new File("booked.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine())
            {
                 data = myReader.nextLine();

                tokens = data.split("[,]",0);
                if(!(data.equals("")) && !(data.equals(" ")))
                {
                    if(tokens[2].equals("Sea"))
                    {
                        seaRooms.add(tokens[0]);
                        allSeaRooms.add(data);
                    }
                    if(tokens[2].equals("Jungle"))
                    {
                        jungleRooms.add(tokens[0]);
                        allJungleRooms.add(data);
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public boolean loadData(String id)
    {
        boolean ret = false;
        try {

            File myObj = new File("booked.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine())
            {
                data = myReader.nextLine();

                tokens = data.split("[,]",0);
                if(tokens[0].equals(id))
                {

                    ret = true;
                    break;
                }
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return ret;
    }

    public void updateData() throws IOException {

            String t = "";
            for(int i = 0; i<allData.size()-1; i++)
            {
                String s = allData.get(i);
                if(s.equals(updateString))
                {

                    allData.remove(updateString);


                }
                t = t + s+System.getProperty( "line.separator");
            }
                Room room = new Room(textField7.getText(), true, comboBox1.getSelectedItem().toString());
                Customer cust = new Customer(textField1.getText(), textField2.getText(), textField3.getText(), textField5.getText());
                Booking booking = new Booking(room,cust,textField6.getText(), Integer.parseInt(textField4.getText()));
                allData.add(booking.toString());
                t = t + booking.toString()+System.getProperty( "line.separator" );
                new FileWriter("booked.txt", false).close();
                writeToFile(t);

    }
    public JPanel getBookingPanel()
    {
        return mainPanelBooking;
    }
}
