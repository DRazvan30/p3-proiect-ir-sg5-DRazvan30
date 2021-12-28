import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminForm extends JFrame implements ActionListener {
    private JButton adaugareButton;
    private JButton stergereButton;
    private JPanel adminForm;
    private JComboBox cartiBox;
    private JButton logoutButton;
    private JComboBox userBox;

    private final String url="jdbc:postgresql://localhost:5432/P3";
    private final String user="postgres";
    private final String password="1524";

    PreparedStatement pst = null;
    ResultSet rs = null;




    AdminForm(){

        setTitle("Biblioteca");
        this.setContentPane(adminForm);
        this.setSize(600,500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        addActionEvent();
        insertInDropBox1();
        insertInDropBox2();


    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==adaugareButton){

            this.setVisible(false);
            new AdaugareForm();


        }

        if(e.getSource()==stergereButton){
            this.setVisible(false);
            new StergeForm();

        }

        if(e.getSource()==logoutButton){
            this.setVisible(false);
            new LoginFrame();
        }

    }

    public void addActionEvent(){

        adaugareButton.addActionListener(this);
        stergereButton.addActionListener(this);
        logoutButton.addActionListener(this);
    }


    public void insertInDropBox1(){

        try{

            String query = "select * from carti";

            java.sql.Connection conn= DriverManager.getConnection(url, user, password);
            pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                String carte = rs.getString("titlu");
                cartiBox.addItem(carte);

            }
        }catch (Exception e){
            e.printStackTrace();

        }

    }


    public void insertInDropBox2() {

        try {

            String query1 = "select * from utilizatori";

            java.sql.Connection conn = DriverManager.getConnection(url, user, password);
            pst = conn.prepareStatement(query1);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String user = rs.getString("username");
                userBox.addItem(user);

            }
        } catch (Exception e) {
            e.printStackTrace();

        }}



    }
