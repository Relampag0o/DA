import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) throws SQLException{
        String db = "w3schools";
        String host = "localhost";
        String port = "3306";
        String urlConnection = "jdbc:mariadb://"+host+":"+port+"/"+db;
        String user = "root";
        String pwd = "5856101097";
        Connection c = DriverManager.getConnection(urlConnection, user, pwd);
        Statement s = c.createStatement();
        Scanner sc = new Scanner(System.in);
        System.out.println("user: ");
        String user2 = sc.nextLine();
        ResultSet rs = s.executeQuery("SELECT * FROM customers WHERE CustomerID='"+user2+"'");
        while(rs.next())
        {
            for(int i=1; i<=rs.getMetaData().getColumnCount();i++)
            {
                System.out.print(rs.getString(i) + " | ");
            }
            System.out.println();
        }
    }
    String nombre = "pepe'; Create user *.* ";

    String dni = ";";
    String sql = "insert into users (nombre, dni) VALUES('"+nombre+"','"+dni+"')";
}