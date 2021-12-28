import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdaugareForm extends JFrame implements ActionListener {
    private JPanel addForm;
    private JTextField titluField;
    private JTextField autorField;
    private JButton cancelButton;
    private JButton adaugaButton;

    private final String url="jdbc:postgresql://localhost:5432/P3";
    private final String user="postgres";
    private final String password="1524";

    PreparedStatement pst = null;
    ResultSet rs = null;

    AdaugareForm(){

        setTitle("Biblioteca");
        this.setContentPane(addForm);
        this.setSize(600,500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        addActionEvent();
    }
    public void addActionEvent(){

        cancelButton.addActionListener(this);
        adaugaButton.addActionListener(this);

    }

    public void clearLabels(){

        titluField.setText("");
        autorField.setText("");
    }


    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==cancelButton){
            this.setVisible(false);
            new AdminForm();
        }

        if(e.getSource()==adaugaButton){

            String titlu = titluField.getText();
            String autor = autorField.getText();

            //delete from carti where titlu=?
                try {
                    String query = "INSERT INTO carti(titlu,autor) VALUES(?, ?) ";
                    java.sql.Connection conn= DriverManager.getConnection(url, user, password);
                    pst = conn.prepareStatement(query);

                    pst.setString(1,titlu);
                    pst.setString(2, autor);
                    //ResultSet rs = pst.executeQuery();

                   pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Carte adaugata");
                    clearLabels();


                }catch (Exception ee){
                    JOptionPane.showMessageDialog(null,"Cartea exista deja");
                    clearLabels();
                }


            }

        }
    }

