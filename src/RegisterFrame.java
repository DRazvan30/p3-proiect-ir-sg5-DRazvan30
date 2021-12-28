import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterFrame extends JFrame implements ActionListener {

    private JTextField numeField;
    private JTextField telField;
    private JTextField prenumeField;
    private JPasswordField passwordField;
    private JPanel registerframe;
    private JButton cancelButton;
    private JButton registerButton;
    private JTextField usernameField;
    private final String url="jdbc:postgresql://localhost:5432/P3";
    private final String user="postgres";
    private final String password="1524";

    PreparedStatement pst = null;
    ResultSet rs = null;

    PreparedStatement pst1 = null;
    ResultSet rs1 = null;

    RegisterFrame() {

        setTitle("Biblioteca");
        this.setContentPane(registerframe);
        this.setSize(600, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);//
        this.setLocationRelativeTo(null);
        addActionEvent();
    }


    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==cancelButton){
            this.setVisible(false);
            new MainFrame();
        }

        if(e.getSource()==registerButton){

            String nume = numeField.getText();
            String prenume =  prenumeField.getText();
            String username = usernameField.getText();
            String telefon = telField.getText();
            String pass = passwordField.getText();

                try {
                    String query = "Select * FROM utilizatori WHERE nume=? and prenume=? and username=? ";
                    java.sql.Connection conn= DriverManager.getConnection(url, user, password);
                    pst = conn.prepareStatement(query);
                    //pst.setString(1, userText.getText());
                    pst.setString(1,nume);
                    pst.setString(2, prenume);
                    pst.setString(3, username);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()){
                        JOptionPane.showMessageDialog(null,"Already logged in");
                        this.setVisible(false);
                        new LoginFrame();
                    }


                    else {

                        try{
                            String querry2 = "INSERT INTO utilizatori(nume,prenume,nr_tel,parola,username) VALUES(?, ?, ?, ?, ?)";
                            java.sql.Connection conn1= DriverManager.getConnection(url, user, password);
                            pst1 = conn1.prepareStatement(querry2);
                            pst1.setString(1,nume);
                            pst1.setString(2,prenume);
                            pst1.setString(3,telefon);
                            pst1.setString(4,pass);
                            pst1.setString(5,username);

                            pst1.executeUpdate();
                            JOptionPane.showMessageDialog(null,"Succsesfully registered");
                        }catch(SQLException y){

                        }
                        this.setVisible(false);
                        //new MainFrame();
                    }
                }catch (Exception ee){
                    ee.printStackTrace();
                }


            }


    }

    public void addActionEvent(){

        registerButton.addActionListener(this);
        cancelButton.addActionListener(this);


    }
}
