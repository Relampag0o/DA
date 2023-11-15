import java.sql.*;

public class Main {
    public Connection c;

    public Main() {
        openConnection();
    }

    public void openConnection() {
        String db = "w3schools";
        String host = "localhost";
        String port = "3306";
        String urlConnection = "jdbc:mariadb://" + host + ":" + port + "/" + db;
        String user = "root";
        String password = "5856101097";
        try {
            this.c = DriverManager.getConnection(urlConnection, user, password);
            System.out.println("Connected to " + db);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getMetaData(String sql) {
        try {
            Statement statement = c.createStatement();
            ResultSet results = statement.executeQuery(sql);
            ResultSetMetaData metaData = results.getMetaData();
            int columnCount = metaData.getColumnCount();
            StringBuilder columns = new StringBuilder("Columns: ");
            for (int i = 1; i <= columnCount; i++) {
                columns.append(metaData.getColumnName(i)).append(" ");
            }

            System.out.println(columns);

            int rowCount = 0;
            while (results.next()) {
                StringBuilder rowData = new StringBuilder("Row " + (++rowCount) + ": ");
                rowData.append(results.getString(1)).append(" ");
                for (int i = 2; i <= columnCount; i++) {
                    rowData.append(rowData.append(": ").append(results.getString(i)).append(" "));
                }

                System.out.println(rowData);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        Main m = new Main();
        m.getMetaData("SELECT * FROM products");

        StringBuilder jose = new StringBuilder();
        jose.append("j");
        jose.append("o");
        jose.append("s");
        System.out.println(jose);

    }
}

