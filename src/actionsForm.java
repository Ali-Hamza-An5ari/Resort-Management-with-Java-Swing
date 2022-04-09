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
import java.util.ArrayList;
import java.util.Scanner;

public class actionsForm {
    private JLabel lblHead;
    private JButton viewButton;
    private JButton searchButton;
    private JButton deleteButton;
    private JButton modifyButton;
    private JButton addButton;
    private JPanel mainPanelView;
    ArrayList<String> fileData = new ArrayList<String>();
    private ArrayList<String> allSeaRooms = new ArrayList<String>();
    private ArrayList<String> allJungleRooms = new ArrayList<String>();

    ArrayList<String> seaRooms = new ArrayList<String>();
    ArrayList<String> jungleRooms = new ArrayList<String>();
    private static boolean isUpd = false;
    private static JFrame frame;
    formBooking fb = new formBooking();
    viewForm vf = new viewForm();
    private String data="";
    String tokens[];


    public static boolean isIsUpd() {
        return isUpd;
    }


    public static void setIsUpd(boolean isUpd) {
        actionsForm.isUpd = isUpd;
    }
    public void showForm()
    {
        frame = new JFrame("Actions");
        frame.setContentPane(new actionsForm().getter());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public void closeForm()
    {
        frame.dispose();
    }
    public actionsForm()
    {

        loadData();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //book form
                frame.dispose();
                fb.showForm();

            }
        });
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String s = JOptionPane.showInputDialog("Room Id: ");

                boolean isLoaded = loadData(s);

                if(isLoaded)
                {
                    formBooking.setUpdateString(data);
                    isUpd = true;
                    java.awt.EventQueue.invokeLater(new Runnable()
                    {
                        public void run()
                        {
                            //book form
                            frame.dispose();
                            fb.showForm();
                        }

                    });
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Record not found");
                }

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String s = JOptionPane.showInputDialog("Room Id: ");
                try {
                    deleteData(s);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
        searchButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {

                String s = JOptionPane.showInputDialog("Room Id: ");
                boolean found = loadData(s);
                if(found)
                {
                    tokens = data.split("[,]",0);
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
                    else
                    {

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
                            vf.showForm();
                        }

                    });
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Record Not found");
                };

            }
        });
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                loadData();


                String text = "";
                String text2 = "";

                for(int i=0; i<fileData.size() ; i++)
                {
                    String s = fileData.get(i);
                    if(!(s.equals("") && s.equals(" ")))
                    {
                        tokens = s.split("[,]",0);
                        if(tokens[2].equals("Sea"))
                        {
                            text = text + "<html>Room no: <b>"+tokens[0]+"</b><br/>" +
                                    "View: "+tokens[2]+"<br/>" +
                                    "Name: "+tokens[3]+"<br/>" +
                                    "IC: "+tokens[4]+"<br/>" +
                                    "Contact: "+tokens[5]+"<br/>" +
                                    "Email: "+tokens[6]+"<br/>" +
                                    "Total charges: "+tokens[7]+"<br/>" +
                                    "Date: "+tokens[8]+"<br/>" +
                                    "Total days: "+tokens[9]+"<hr><html>" ;
                        }
                        else
                        {
                            text2 = text2 + "<html>Room no: <b>"+tokens[0]+"</b><br/>" +
                                    "View: "+tokens[2]+"<br/>" +
                                    "Name: "+tokens[3]+"<br/>" +
                                    "IC: "+tokens[4]+"<br/>" +
                                    "Contact: "+tokens[5]+"<br/>" +
                                    "Email: "+tokens[6]+"<br/>" +
                                    "Total charges: "+tokens[7]+"<br/>" +
                                    "Date: "+tokens[8]+"<br/>" +
                                    "Total days: "+tokens[9]+"<hr><html>";
                        }
                    }
                }
                viewForm.setViewString(text);

                viewForm.setViewString2(text2);

                java.awt.EventQueue.invokeLater(new Runnable()
                {
                    public void run()
                    {

                        viewForm.setQuery("all");
                        frame.dispose();
                        vf.showForm();
                    }

                });


            }
        });
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
                    if(tokens[2].equals("Sea"))
                    {
                        viewForm.setViewString(data);
                    }
                    else
                    {
                        viewForm.setViewString2(data);
                    }

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


    public void loadData()
    {
        try {
            seaRooms.clear();
            jungleRooms.clear();
            fileData.clear();
            allSeaRooms.clear();
            allJungleRooms.clear();

            File myObj = new File("booked.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine())
            {
                data = myReader.nextLine();

                tokens = data.split("[,]");
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
                    fileData.add(data);
                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public void setAllData() throws FileNotFoundException {
        fileData.clear();
        File myObj = new File("booked.txt");
        Scanner myReader = new Scanner(myObj);

        while (myReader.hasNextLine())
        {
            data = myReader.nextLine();

            fileData.add(data);
            tokens = data.split("[,]",0);
            if(!(data.equals("")) || !(data.equals(" ")))
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
    }

    public void deleteData(String id) throws IOException {

        String t = "";
        for(int i = 0; i<fileData.size()-1; i++)
        {
            String s = fileData.get(i);
            if(s.substring(0,s.indexOf(",")).equals(id))
            {

                fileData.remove(s);
                JOptionPane.showMessageDialog(null,"Record deleted");
                break;

            }
            t = t + s+System.getProperty( "line.separator" );
        }
        new FileWriter("booked.txt", false).close();
        try {

            FileWriter myWriter = new FileWriter("booked.txt",true);
            myWriter.write(t+ System.getProperty( "line.separator" ));
            myWriter.close();


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public JPanel getter()
    {
        return mainPanelView;
    }

}
