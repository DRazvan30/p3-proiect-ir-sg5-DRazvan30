import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RentForm extends JFrame implements ActionListener {
    private JPanel RentForm;
    private JComboBox cartiBox;
    private JButton imprumutaButton;
    private JButton cancelButton;
    private JButton returneazaButton;

    private final String url="jdbc:postgresql://localhost:5432/P3";
    private final String user="postgres";
    private final String password="1524";

    PreparedStatement pst = null;
    ResultSet rs = null;


    RentForm(){
        setTitle("Biblioteca");
        this.setContentPane(RentForm);
        this.setSize(600,500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        addActionEvent();
        insertInDropBox1();
    }

    public void addActionEvent(){
        imprumutaButton.addActionListener(this);
        cancelButton.addActionListener(this);
        returneazaButton.addActionListener(this);
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
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==cancelButton){
            this.setVisible(false);
            new UserFrame();

        }


        if(e.getSource()==imprumutaButton){

            try {
                String carte_id = (String) cartiBox.getSelectedItem();
                String query = "update utilizatori set id=(select id from carti where titlu=?) where logged_in='1';";
                java.sql.Connection conn = DriverManager.getConnection(url, user, password);
                pst = conn.prepareStatement(query);
                pst.setString(1,carte_id);
                pst.executeUpdate();




            }catch (Exception ee){
                ee.printStackTrace();
            }


        }


        /*if(e.getSource()==returneazaButton){

            try {

                String query = "update utilizatori set id=(select id from carti where titlu=?) where logged_in='1';";
                java.sql.Connection conn = DriverManager.getConnection(url, user, password);
                pst = conn.prepareStatement(query);
                //pst.setString(1,carte_id);
                pst.executeUpdate();




            }catch (Exception eee){
                eee.printStackTrace();
            }
        }*/
    }
}
