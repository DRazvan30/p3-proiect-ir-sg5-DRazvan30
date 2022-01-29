import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserFrame extends JFrame implements ActionListener {



    private JButton imprumutaButton;
    private JButton logoutButton;
    private JPanel UserFrame;
    private JButton ReturButton;

    private final String url="jdbc:postgresql://localhost:5432/P3";
    private final String user="postgres";
    private final String password="1524";

    PreparedStatement pst = null;
    PreparedStatement pst1 = null;
    ResultSet rs = null;

    UserFrame(){

        setTitle("Biblioteca");
        this.setContentPane(UserFrame);
        this.setSize(600,500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        addActionEvent();


    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==logoutButton){

            this.setVisible(false);
            set_false_log_in();
            new LoginFrame();

        }

        if(e.getSource()==imprumutaButton){
            this.setVisible(false);
            new RentForm();
        }

        if(e.getSource()==ReturButton) {

            try {

                //String query = "update utilizatori set id=0 where logged_in='1';";
                String query = "select id from utilizatori where logged_in='1';";
                java.sql.Connection conn = DriverManager.getConnection(url, user, password);
                pst = conn.prepareStatement(query);
                rs = pst.executeQuery();

                if(rs.next()){
                         int id = rs.getInt("id");

                         if(id!=0){
                             String query1 = "update utilizatori set id=0 where logged_in='1';";
                             java.sql.Connection conn1 = DriverManager.getConnection(url, user, password);
                             pst = conn1.prepareStatement(query1);
                             pst.executeUpdate();
                             JOptionPane.showMessageDialog(null,"Cartea a fost returnata");


                         }
                         else{
                             JOptionPane.showMessageDialog(null,"Nu a fost imprumutata nicio carte");
                         }



                }








            }catch (Exception eee){
                eee.printStackTrace();
            }


        }


    }

    public void addActionEvent(){

        imprumutaButton.addActionListener(this);
        logoutButton.addActionListener(this);
        ReturButton.addActionListener(this);
    }

    public static void set_false_log_in(){

        String url="jdbc:postgresql://localhost:5432/P3";
        String user="postgres";
        String password="1524";

        PreparedStatement pst = null;

        try {

            String query = "update utilizatori set logged_in='false' where logged_in='true'";
            java.sql.Connection conn = DriverManager.getConnection(url, user, password);
            pst = conn.prepareStatement(query);
            pst.executeUpdate();




        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
