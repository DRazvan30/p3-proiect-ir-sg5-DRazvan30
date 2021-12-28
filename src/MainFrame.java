import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {
    private JPanel mainFrame;
    private JButton loginButton;
    private JButton exitButton;
    private JButton registerButton;

    MainFrame(){

        setTitle("Biblioteca");
        this.setContentPane(mainFrame);
        this.setSize(600,500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//sa se inchida si procesul odata cu frameul
        this.setResizable(false);//nu se poate modifica dimensiunea
        this.setLocationRelativeTo(null);
        addActionEvent();



    }

    public void addActionEvent(){

        loginButton.addActionListener(this);
        exitButton.addActionListener(this);
        registerButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){

        if(e.getSource()==exitButton)
            System.exit(0);

        if(e.getSource()==loginButton)
        {
            this.setVisible(false);
            new LoginFrame();

        }

        if(e.getSource()==registerButton)
        {
            this.setVisible(false);
            new RegisterFrame();
        }

    }
}
