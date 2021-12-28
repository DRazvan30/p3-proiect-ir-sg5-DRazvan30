import com.sun.jdi.connect.spi.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {

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
    public static void main(String[] args){

        set_false_log_in();
        MainFrame frame1= new MainFrame();

        //AdminForm frame1 = new AdminForm();


}

}
