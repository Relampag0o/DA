import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Launcher {
    public Connection c;


    public Launcher() {
        openConnection();
    }

    public static void showSQLError(SQLException e) {
        System.err.println("SQL error message: " + e.getMessage());
        System.err.println("SQL state: " + e.getSQLState());
        System.err.println("SQL error code: " + e.getErrorCode());
    }

    public static void close(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void closeStatement(Statement s) {
        if (s != null) {
            try {
                s.close();
            } catch (SQLException sqlException) {
                // TODO Auto-generated catch block
                showSQLError(sqlException);
            }
        }
    }

    public void createTable(String sql) {
        Statement st = null;
        try {
            st = c.createStatement();
            st.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStatement(st);
        }


    }

    public void addData(String table, String fields, String values) {
        Statement st = null;
        try {
            st = c.createStatement();
            st.executeUpdate("INSERT INTO " + table + " (" + fields + ") VALUES (" + values + ")");
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            closeStatement(st);
        }
        
    }

    public void delete(String table, String whereClause) {
        Statement st = null;
        try {
            st = c.createStatement();
            st.executeUpdate("DELETE FROM " + table + " WHERE " + whereClause + ";");
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            closeStatement(st);
        }

    }

    public void updateData(String table, String newValues, String whereClause) {
        Statement st = null;
        try {
            st = c.createStatement();
            st.executeUpdate("UPDATE " + table + " SET  " + newValues + " WHERE " + whereClause + " ;");

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            closeStatement(st);
        }

    }

    public void openConnection() {
        String db = "dbproducts";
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
        Launcher m = new Launcher();
        /*
         create tables:

        m.createTable("CREATE TABLE size " +
                "(size_id INTEGER not NULL, " +
                " size_code INTEGER, " +
                " clasification VARCHAR(255))");

        m.createTable("CREATE TABLE product " +
                "(product_id INTEGER not NULL," +
                "product_name varchar(255)," +
                "other_data varchar(255))");
        m.createTable("CREATE TABLE product_categories " +
                "(category_id INTEGER not NULL)");
        m.createTable("CREATE TABLE product_colors " +
                "(color_id INTEGER not NULL)");
        m.createTable("CREATE TABLE color " +
                "(color_id INTEGER not NULL, " +
                " color_code INTEGER, " +
                " color_name VARCHAR(255))");
        m.createTable("CREATE TABLE categories " +
                "(category_id INTEGER not NULL, " +
                " main_category varchar(255), " +
                " category_name VARCHAR(255))");
*/

        /*
        insert data:
        m.addData("size", "size_id, size_code, clasification", "1, 10, 'Small'");
        m.addData("product", "product_id, product_name, other_data", "1, 'Product A', 'Data A'");
        m.addData("product_categories", "category_id", "1");
        m.addData("color", "color_id, color_code, color_name", "1, 20, 'Red'");
        m.addData("categories", "category_id, main_category, category_name", "1, 'Main', 'Category 1'");

         modify data:

        m.updateData("product", "product_name = 'New Product Name'", "product_id = 1");
        // delete data:


        m.delete("product", "product_name LIKE '%a%'");
        */


    }
}