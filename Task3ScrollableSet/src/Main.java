import java.io.Closeable;
import java.sql.*;
import java.util.LinkedList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public Connection c;
    DatabaseMetaData databaseMetaData;


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

    public void closeResultSet(ResultSet s) {
        if (s != null) {
            try {
                s.close();
            } catch (SQLException sqlException) {
                showSQLError(sqlException);
            }
        }
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

    // TODO: ask how to iterate a result set without knowing the column names or the column amount.
    // TODO : ask if i need to close metadata resultsets.

    public void reverseOrder(LinkedList<String> queries) {
        Statement st = null;
        ResultSet rs = null;
        ResultSetMetaData meta = null;
        try {
            st = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            for (String q : queries) {
                rs = st.executeQuery(q);
                meta = rs.getMetaData();
                int columnCount = meta.getColumnCount();
                rs.afterLast();
                while (rs.previous()) {
                    StringBuilder columns = new StringBuilder();
                    StringBuilder data = new StringBuilder();
                    for (int i = 1; i <= columnCount; i++) {
                        columns.append(meta.getColumnName(i)).append(" ");
                        data.append(rs.getObject(i)).append(" ");
                    }
                    System.out.println(columns);
                    System.out.println(data);
                    System.out.println("--------------");
                }


            }


        } catch (SQLException s) {
            showSQLError(s);
        } finally {
            closeStatement(st);
            closeResultSet(rs);

        }


    }


    public void showThreeRows(LinkedList<String> queries) {
        Statement st = null;
        ResultSet rs = null;
        ResultSetMetaData meta = null;
        try {
            st = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            for (String q : queries) {
                rs = st.executeQuery(q);
                meta = rs.getMetaData();
                int columnCount = meta.getColumnCount();
                rs.last();
                int rowCount = rs.getRow();
                int middle = rs.getRow() / 2;
                rs.beforeFirst();
                while (rs.next()) {
                    System.out.println("ROW: " + rs.getRow());
                    String columns = " ";
                    String data = " ";
                    if (rs.getRow() == 1 || rs.getRow() == middle || rs.getRow() == rowCount) {
                        for (int i = 1; i <= columnCount; i++) {
                            columns += meta.getColumnName(i) + " ";
                            data += rs.getString(i) + " ";
                        }
                        System.out.println(columns);
                        System.out.println(data);
                        System.out.println("--------------");


                    }


                }


            }
        } catch (SQLException s) {
            showSQLError(s);

        } finally {
            closeStatement(st);
            closeResultSet(rs);
        }

    }

    public void displayMiddleData(LinkedList<String> queries, int columnIndex) {

        Statement st = null;
        ResultSet rs = null;
        ResultSetMetaData meta = null;
        try {
            st = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            for (String q : queries) {
                rs = st.executeQuery(q);
                meta = rs.getMetaData();
                int columnCount = meta.getColumnCount();
                rs.last();
                int rowCount = rs.getRow();
                if (rowCount >= 1) {
                    if (rowCount > 1)
                        rs.absolute(rs.getRow() / 2);

                    String columns = " ";
                    String data = " ";
                    for (int i = 1; i <= columnCount; i++) {
                        columns += meta.getColumnName(i) + " ";
                        data += rs.getString(i) + " ";
                    }
                    System.out.println(columns);
                    System.out.println(data);
                    System.out.println("--------------");

                    if (rowCount > 1)
                        rs.absolute((rs.getRow() / 2) + 1);

                    columns = " ";
                    data = " ";
                    for (int i = 1; i <= columnCount; i++) {
                        columns += meta.getColumnName(i) + " ";
                        data += rs.getString(i) + " ";
                    }
                    System.out.println(columns);
                    System.out.println(data);
                    System.out.println("--------------");

                    if (rowCount > 1)
                        rs.absolute((rs.getRow() / 2) + 2);

                    columns = " ";
                    data = " ";
                    for (int i = 1; i <= columnCount; i++) {
                        columns += meta.getColumnName(i) + " ";
                        data += rs.getString(i) + " ";
                    }
                    System.out.println(columns);
                    System.out.println(data);
                    System.out.println("--------------");

                    if (columnIndex <= rowCount)
                        rs.absolute((columnIndex));

                    columns = " ";
                    data = " ";
                    for (int i = 1; i <= columnCount; i++) {
                        columns += meta.getColumnName(i) + " ";
                        data += rs.getString(i) + " ";
                    }
                    System.out.println(columns);
                    System.out.println(data);
                    System.out.println("--------------");


                } else
                    System.out.println("The query doesnt give any result.");
            }
        } catch (SQLException s) {
            showSQLError(s);

        } finally {
            closeStatement(st);
            closeResultSet(rs);
        }


    }


    public static void main(String[] args) {
        Main m = new Main();
        LinkedList<String> queries = new LinkedList<String>();
        /*
        queries.add("select ProductName,price from products where ProductName like 'T%' and price <10;");
        queries.add("select * from suppliers where country = 'USA' or country = 'Italy';");
        queries.add("select * from customers where city like '%n' and city like '%a%';");

        queries.add("select categoryid, avg(price) as median from products group by categoryid;");
        queries.add("select categoryid from products  where productID is not NULL;");
        queries.add("select count(orderid) as amount_orders from orders\n" +
                "         where year(OrderDate)=1996 and month(OrderDate)=7;");
        m.showThreeRows(queries);
        */
        queries.add("select count(orderid) as amount_orders from orders where year(OrderDate)=1996 and month(OrderDate)=7;");
        queries.add(" SELECT\n" +
                "        products.ProductID,\n" +
                "        products.ProductName,\n" +
                "        SUM(order_details.Quantity) AS TotalQuantitySold\n" +
                "        FROM products\n" +
                "        INNER JOIN order_details ON products.ProductID = order_details.ProductID\n" +
                "        INNER JOIN orders ON order_details.OrderID = orders.OrderID\n" +
                "        WHERE YEAR(orders.OrderDate) = 1996\n" +
                "        GROUP BY products.ProductID, products.ProductName\n" +
                "        ORDER BY TotalQuantitySold DESC\n" +
                "        LIMIT 10;");
        queries.add("select * from orders\n" +
                "        INNER JOIN order_details on order_details.OrderID = orders.OrderID\n" +
                "        INNER JOIN products on order_details.ProductID = products.ProductID\n" +
                "        where products.price > 300;");
        m.displayMiddleData(queries, 3);


        /*
        1
        select ProductName,price from products where ProductName like 'T%' and price <10;
        2
        # select * from suppliers where country = 'USA' or country = 'Italy';
        3
        # select * from customers where city like '%n' and city like '%a%';
        4
        select categoryid, avg(price) as median from products
        group by categoryid;
        5
        select categoryid from products
        where productID is not null;
        6
        # select count(orderid) as amount_orders from orders
        # where year(OrderDate)=1996 and month(OrderDate)=7;
        7
        select suppliers.Country,avg(price) as Average from products
        INNER JOIN suppliers on products.SupplierID = suppliers.SupplierID
        group by suppliers.Country

        8
        SELECT
        products.ProductID,
        products.ProductName,
        SUM(order_details.Quantity) AS TotalQuantitySold
        FROM products
        INNER JOIN order_details ON products.ProductID = order_details.ProductID
        INNER JOIN orders ON order_details.OrderID = orders.OrderID
        WHERE YEAR(orders.OrderDate) = 1996
        GROUP BY products.ProductID, products.ProductName
        ORDER BY TotalQuantitySold DESC
        LIMIT 10;
        9
        select * from orders
        INNER JOIN order_details on order_details.OrderID = orders.OrderID
        INNER JOIN products on order_details.ProductID = products.ProductID
        where products.price > 300;


         */

    }
}
