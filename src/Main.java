import java.sql.*;

public class Main {

    public static void main(String args[]){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/AE?serverTimezone=UTC","svenja","root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from user");
            while(rs.next())
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
            con.close();
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
