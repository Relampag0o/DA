import java.io.*;
import java.sql.*;
import java.util.LinkedList;

public class Main {
    public Connection c;
    DatabaseMetaData databaseMetaData;
    PreparedStatement ResultSetMetaData;


    public Main() {
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
                e.printStackTrace();
            }
        }
    }

    public void closeStatement(Statement s) {
        if (s != null) {
            try {
                s.close();
            } catch (SQLException sqlException) {
                showSQLError(sqlException);
            }
        }
    }

    public void openConnection() {
        //String db = "dbproducts";
        String host = "localhost";
        String port = "3306";
        String urlConnection = "jdbc:mariadb://" + host + ":" + port;
        String user = "root";
        String password = "5856101097";
        try {
            this.c = DriverManager.getConnection(urlConnection, user, password);
            System.out.println("Connected");
            databaseMetaData = c.getMetaData();
        } catch (SQLException e) {
            //showSQLError(e);
            e.printStackTrace();
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
        PreparedStatement st = null;
        try {
            st = c.prepareStatement("DELETE FROM " + table + " WHERE " + whereClause + ";");
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

    public void getInfo(String table_name) {
        Statement st = null;
        try {
            st = c.createStatement();
            ResultSet columnData = databaseMetaData.getColumns(null, null, table_name, null);
            while (columnData.next()) {
                System.out.println("\u001B[32mColumn Name: " + columnData.getString("COLUMN_NAME") +
                        "\u001B[0m, \u001B[32mData Type: " + columnData.getString("TYPE_NAME") +
                        "\u001B[0m, \u001B[32mColumn Size: " + columnData.getInt("COLUMN_SIZE") +
                        "\u001B[0m, \u001B[32mIs Nullable: " + columnData.getString("IS_NULLABLE") +
                        "\u001B[0m, \u001B[32mOrdinal Position: " + columnData.getInt("ORDINAL_POSITION") +
                        "\u001B[0m");
            }


        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            closeStatement(st);
        }

    }

    public void getAllData(String tableName) {
        Statement st = null;
        ResultSet resultSet = null;

        try {
            st = c.createStatement();
            resultSet = st.executeQuery("SELECT * FROM " + tableName);

            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = rsmd.getColumnName(i);
                    Object value = resultSet.getObject(i);
                    System.out.println(columnName + ": " + value);
                }
                System.out.println("------");
            }
        } catch (SQLException e) {
            showSQLError(e);
        } finally {
            closeStatement(st);
        }
    }


    public void selectFrom(String tablename, LinkedList<String> columns, String whereClause) {
        Statement st = null;
        String cols = "";

        for (String c : columns) {
            cols += c + ",";
        }
        cols = cols.substring(0, cols.length() - 1);
        try {
            st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT " + cols + " FROM" + " " + tablename + " WHERE " + whereClause + ";");

            while (rs.next()) {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    System.out.print(rs.getObject(i) + "\t");
                }
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {

        }

    }

    public void displayCommonFields(String table1, String table2) {
        try {
            ResultSet columnsTable1 = databaseMetaData.getColumns(null, null, table1, null);
            ResultSet columnsTable2 = databaseMetaData.getColumns(null, null, table2, null);

            LinkedList<String> columnsTable1List = new LinkedList<>();
            LinkedList<String> columnsTable2List = new LinkedList<>();

            while (columnsTable1.next()) {
                columnsTable1List.add(columnsTable1.getString("COLUMN_NAME"));
            }
            while (columnsTable2.next()) {
                columnsTable2List.add(columnsTable2.getString("COLUMN_NAME"));
            }

            // this function seems to be checking those atributes that are common..
            LinkedList<String> commonFields = new LinkedList<>(columnsTable1List);
            commonFields.retainAll(columnsTable2List);

            if (!commonFields.isEmpty()) {
                String commonFieldsStr = String.join(", ", commonFields);
                String query = "SELECT " + "t1." + commonFieldsStr + " FROM " + table1 + " AS t1 INNER JOIN " + table2 +
                        " AS t2 ON t1." + commonFields.get(0) + " = t2." + commonFields.get(0);
                System.out.println(query);
                Statement statement = c.createStatement();
                ResultSet result = statement.executeQuery(query);
                ResultSetMetaData rsmd = result.getMetaData();
                int columnCount = rsmd.getColumnCount();

                while (result.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(rsmd.getColumnName(i) + ": " + result.getObject(i) + "\t");
                    }
                    System.out.println();
                }
            } else {
                System.out.println("There aren't common fields..");
            }
        } catch (SQLException e) {
            showSQLError(e);
        }
    }

    // todo: finish this exercise. not working atm
    public void hackDB(String pattern) {
        Statement st = null;

        try {
            st = c.createStatement();
            ResultSet catalogs = databaseMetaData.getCatalogs();
            while (catalogs.next()) {
                st.execute("USE " + catalogs.getString(1));
                String dbName = catalogs.getString(1);
                System.out.println("Database Name: " + dbName);
                System.out.println("----------------");
                ResultSet tableNames = databaseMetaData.getTables(dbName, null, null, null);
                while (tableNames.next()) {
                    System.out.println("Table name: " + tableNames.getString("TABLE_NAME"));
                    ResultSet columns = databaseMetaData.getColumns(dbName, null, tableNames.getString("TABLE_NAME"), null);
                    System.out.println("---COLUMNS---");
                    while (columns.next()) {
                        System.out.print(columns.getString("COLUMN_NAME") + " ");
                        String query = "SELECT * FROM " + tableNames.getString("TABLE_NAME") + " WHERE `" + columns.getString("COLUMN_NAME")
                                + "` LIKE '%" + pattern + "%'" + ";";
                        ResultSet results = st.executeQuery(query);
                        ResultSetMetaData rsm = results.getMetaData();
                        while (results.next()) {
                            ResultSetMetaData metadata = results.getMetaData();
                            int columnCount = metadata.getColumnCount();
                            for (int i = 1; i <= columnCount; i++) {
                                System.out.print(metadata.getColumnName(i) + ": " + results.getString(i) + " ");
                            }
                            System.out.println();
                        }


                    }
                    System.out.println(" ");
                }

                System.out.println("----------------");

            }


        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            closeStatement(st);
        }

    }


    public void readCsv(String f) {
        Statement st = null;
        try {
            st = c.createStatement();
            BufferedReader bfr = new BufferedReader(new FileReader(f));
            String line = bfr.readLine();
            String s = "";

            while ((line = bfr.readLine()) != null) {
                System.out.println("INSERT INTO product (product_id, product_name, other_data) VALUES (" +
                        "'" + (line.split(",")[0] + "'," +
                        "'" + line.split(",")[1] + "'," +
                        "'" + line.split(",")[2] + "')"));
                st.executeUpdate("INSERT INTO product (product_id, product_name, other_data) VALUES (" +
                        "'" + line.split(",")[0] + "'," +
                        "'" + line.split(",")[1] + "'," +
                        "'" + line.split(",")[2] + "')");
            }


        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            closeStatement(st);
        }


    }

    public void randomTest() {
        Statement st = null;
        try {
            st = c.createStatement();
            String[] r = {"SYSTEM TABLE"};
            try (ResultSet resultSet = databaseMetaData.getTables(null, null, null, r)) {
                System.out.println(r.length);
                while (resultSet.next()) {
                    String systemTableName = resultSet.getString("TABLE_NAME");

                }
            }
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) throws SQLException {
        Main m = new Main();
        LinkedList<String> columns = new LinkedList<String>();
        /*
        columns.add("color_id");
        columns.add("color_code");
        m.selectFrom("color", columns, "color_id='10'");
        */
        //m.displayCommonFields("product_categories", "categories");

        m.randomTest();


    }
}