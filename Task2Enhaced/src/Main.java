import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    Connection c;
    DatabaseMetaData meta;

    public void openConnection() {
        String db = "w3schools";
        String host = "localhost";
        String port = "3306";
        String urlConnection = "jdbc:mariadb://" + host + ":" + port + "/" + db;
        String user = "root";
        String password = "5856101097";
        try {
            this.c = DriverManager.getConnection(urlConnection, user, password);
            System.out.println("Connected to: " + db);
            meta = c.getMetaData();
        } catch (SQLException e) {
            //showSQLError(e);
            e.printStackTrace();
        }
    }

    public void retrieveInfo() {
        try {
            ResultSet rs = meta.getTables(null, null, null, null);
            while (rs.next()) {
                System.out.println("Table Catalog: " + rs.getString("TABLE_CAT"));
                System.out.println("Table Schema: " + rs.getString("TABLE_SCHEM"));
                System.out.println("Table Name: " + rs.getString("TABLE_NAME"));
                System.out.println("Table Type: " + rs.getString("TABLE_TYPE"));
                System.out.println("Remarks: " + rs.getString("REMARKS"));
                System.out.println("Type Catalog: " + rs.getString("TYPE_CAT"));
                System.out.println("Type Schema: " + rs.getString("TYPE_SCHEM"));
                System.out.println("Type Name: " + rs.getString("TYPE_NAME"));
                System.out.println("Self Referencing Column Name: " + rs.getString("SELF_REFERENCING_COL_NAME"));
                System.out.println("Ref Generation: " + rs.getString("REF_GENERATION"));

                System.out.println("------------------------------");

            }

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void retrieveInfoFromTable(String tableName) {
        PreparedStatement pst = null;

        try {
            pst = c.prepareStatement("SELECT * FROM " + tableName + ";");
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsmeta = rs.getMetaData();
            int numberColumns = rsmeta.getColumnCount();
            String columnNames = "";
            String data = "";


            for (int i = 1; i <= numberColumns; i++) {
                columnNames += rsmeta.getColumnName(i) + " | ";
            }

            while (rs.next()) {
                for (int i = 1; i <= numberColumns; i++) {
                    data += String.format("%-15s", rs.getString(i));
                }
                data += "\n";

            }

            System.out.println(columnNames);
            System.out.println(data);


            pst.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void retrieveDataWithWhereClause(String tableName, String[] columns, String whereClause) {

        PreparedStatement pst = null;

        try {
            String selectClause = "";
            for (int i = 0; i < columns.length; i++) {
                selectClause += columns[i] + ",";
            }
            selectClause = selectClause.substring(0, selectClause.length() - 1);

            System.out.println("SELECT " + selectClause + " FROM " + tableName + " WHERE " + whereClause);
            pst = c.prepareStatement("SELECT " + selectClause + " FROM " + tableName + " WHERE " + whereClause);
            ResultSet rs = pst.executeQuery();

            ResultSetMetaData rsmeta = rs.getMetaData();
            int numberColumns = rsmeta.getColumnCount();
            String columnNames = "";
            String data = "";


            for (int i = 1; i <= numberColumns; i++) {
                columnNames += rsmeta.getColumnName(i) + " | ";
            }

            while (rs.next()) {
                for (int i = 1; i <= numberColumns; i++) {
                    data += String.format("%-15s", rs.getString(i));
                }
                data += "\n";

            }

            System.out.println(columnNames);
            System.out.println(data);


            pst.close();
            rs.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showDataFromDB() {

        PreparedStatement pst = null;

        try {
            pst = c.prepareStatement("SHOW COLUMNS FROM CATEGORIES");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString(1));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void innerJoin(String table1, String table2) {
        PreparedStatement pst = null;


        try {
            pst = c.prepareStatement("SELECT * FROM " + table1);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsmeta = rs.getMetaData();
            int columnCount = rsmeta.getColumnCount();
            ArrayList<String> fieldsTable1 = new ArrayList<>();
            ArrayList<String> fieldsTable2 = new ArrayList<>();

            for (int i = 1; i <= columnCount; i++) {
                fieldsTable1.add(rsmeta.getColumnName(i));
            }

            pst = c.prepareStatement("SELECT * FROM " + table2);
            rs = pst.executeQuery();
            rsmeta = rs.getMetaData();
            columnCount = rsmeta.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                fieldsTable2.add(rsmeta.getColumnName(i));
            }

            pst = c.prepareStatement("SELECT * FROM " + table2);
            rs = pst.executeQuery();
            rsmeta = rs.getMetaData();
            columnCount = rsmeta.getColumnCount();

            // with this method we are taking common columns and setting to the list. wonder if there is any way to avoid creating second list..
            fieldsTable1.retainAll(fieldsTable2);
            System.out.println(fieldsTable1.size());
            pst = c.prepareStatement("SELECT * FROM " + table1 + " " + "INNER JOIN " + table2 + " on " + table1 + "." + fieldsTable1.get(0) + "=" + table2 + "." + fieldsTable1.get(0) + ";");
            rs = pst.executeQuery();
            rsmeta = rs.getMetaData();
            columnCount = rsmeta.getColumnCount();
            String columnNames = "";
            String data = "";

            for (int i = 1; i <= columnCount; i++) {
                columnNames += rsmeta.getColumnName(i) + " | ";
            }

            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    data += String.format("%-15s", rs.getString(i));
                }
                data += "\n";

            }

            System.out.println(columnNames);
            System.out.println(data);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        Main m = new Main();
        m.openConnection();
        //m.retrieveInfo();
        //m.retrieveInfoFromTable("product");
        //m.retrieveDataWithWhereClause("product", new String[]{"product_name"}, "product_id=1");
        //m.showDataFromDB();
        //m.retrieveDataWithWhereClause("categories",new String[]{"category_name"},"category_id=2");
        m.innerJoin("products", "order_details");
    }
}