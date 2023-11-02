import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public Connection c;

    public void openConnection() {
        String db = "w3schools";
        String host = "localhost";
        String port = "3306";
        String urlConnection = "jdbc:mariadb://" + host + ":" + port + "/" + db;
        String user = "root";
        String password = "5856101097";
        try {
            this.c = DriverManager.getConnection(urlConnection, user, password);
            System.out.println("Connected");
        } catch (SQLException e) {
            //showSQLError(e);
            e.printStackTrace();
        }
        

    }

    public static void main(String[] args) {

        Main m = new Main();
        m.openConnection();
    }
}