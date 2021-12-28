import com.sun.jdi.connect.spi.Connection;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginFrame extends JFrame implements ActionListener {
    private JPanel loginFrame;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton cancelButton;
    private JButton confirmButton;
    private final String url="jdbc:postgresql://localhost:5432/P3";
    private final String user="postgres";
    private final String password="1524";

    PreparedStatement pst = null;
    ResultSet rs = null;


    LoginFrame(){

        setTitle("Biblioteca");
        this.setContentPane(loginFrame);
        this.setSize(600,500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//sa se inchida si procesul odata cu frameul
        this.setResizable(false);//nu se poate modifica dimensiunea
        this.setLocationRelativeTo(null);
        addActionEvent();

    }

    public void addActionEvent(){
        cancelButton.addActionListener(this);
        confirmButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){

        boolean v = false;

        if(e.getSource()==cancelButton){
            this.setVisible(false);
            new MainFrame();
        }

        if(e.getSource()==confirmButton){
            String username = textField1.getText();
            String pass = passwordField1.getText();


            if(username.equals("admin") && pass.equals("123")){

                JOptionPane.showMessageDialog(null,"Admin cu succes");
                this.setVisible(false);
                new AdminForm();
            }
  /*
            if(username.equals("razvan") && pass.equals("123")){
                this.setVisible(false);
                v = true;
                //new UserFrame();
            }

            if(v==false)
                JOptionPane.showMessageDialog(null,"Nu exista");*/

            else {

                try {
                    String query = "Select * FROM utilizatori WHERE username=? and parola=? ";

                    java.sql.Connection conn= DriverManager.getConnection(url, user, password);
                    pst = conn.prepareStatement(query);
                    PreparedStatement pst1 = conn.prepareStatement("update utilizatori set logged_in='true' where username=? and parola=?");
                    pst.setString(1,username);
                    pst.setString(2, pass);
                    pst1.setString(1,username);
                    pst1.setString(2, pass);

                    ResultSet rs = pst.executeQuery();
                    pst1.executeUpdate();

                    if(rs.next()){
                        JOptionPane.showMessageDialog(null,"Login succsessss");
                        this.setVisible(false);
                        new UserFrame();
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"nu EXISTA");
                        this.setVisible(false);
                        new MainFrame();
                    }
                }catch (Exception ee){
                    ee.printStackTrace();
                }


            }
    }
}}
