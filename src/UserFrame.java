import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UserFrame extends JFrame implements ActionListener {
    private JButton imprumutaButton;
    private JButton logoutButton;
    private JPanel UserFrame;

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





    }

    public void addActionEvent(){

        imprumutaButton.addActionListener(this);
        logoutButton.addActionListener(this);
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
