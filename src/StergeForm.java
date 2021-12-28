import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StergeForm extends JFrame implements ActionListener {
    private JPanel stergerePanel;
    private JTextField titluField;
    private JButton stergeButton;
    private JButton cancelButton;

    private final String url="jdbc:postgresql://localhost:5432/P3";
    private final String user="postgres";
    private final String password="1524";

    PreparedStatement pst = null;
    ResultSet rs = null;

    StergeForm(){

        setTitle("Biblioteca");
        this.setContentPane(stergerePanel);
        this.setSize(600,500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        addActionEvent();
    }


    public void addActionEvent(){

        stergeButton.addActionListener(this);
        cancelButton.addActionListener(this);



    }

    public void clearLabels(){

        titluField.setText("");

    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==cancelButton){

            this.setVisible(false);
            new AdminForm();

        }

        if(e.getSource()==stergeButton){

            String titlu = titluField.getText();


            //delete from carti where titlu=?
            try {
                String query = "delete from carti where titlu=?";
                java.sql.Connection conn= DriverManager.getConnection(url, user, password);
                pst = conn.prepareStatement(query);
                pst.setString(1,titlu);

                //ResultSet rs = pst.executeQuery();

                pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Carte stearsa");
                    clearLabels();

            }catch (Exception ee){

            }


        }
        }

    }




