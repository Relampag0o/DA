import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.FileWriter;
import java.sql.*;
import java.util.LinkedList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private LinkedList<Supplier> suppliers = new LinkedList<Supplier>();
    private LinkedList<Product> products = new LinkedList<Product>();
    private Connection c;


    public Main() {
        this.suppliers = new LinkedList<Supplier>();
        this.products = new LinkedList<Product>();

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
        String db = "examen";
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

    public void writeXMLwithClasses() {
        PreparedStatement pst = null;
        try {
            BufferedWriter bfw = new BufferedWriter(new FileWriter("exam.xml"));
            pst = c.prepareStatement("SELECT * FROM proveedores");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                suppliers.add(new Supplier(rs.getInt("id"), rs.getString("nombre"), rs.getString("pais")));
            }

            pst = c.prepareStatement("SELECT * FROM productos");
            rs = pst.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"), rs.getString("moneda")));
            }


            bfw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
            bfw.newLine();
            bfw.write("<exam>");
            bfw.write("<suppliers>");
            bfw.newLine();
            for (Supplier s : suppliers) {
                bfw.write(s.toString());
                bfw.newLine();
            }
            bfw.write("</suppliers>");
            bfw.newLine();
            bfw.write("<products>");
            bfw.newLine();
            for (Product p : products) {
                bfw.write(p.toString());
                bfw.newLine();
            }
            bfw.write("</products>");
            bfw.newLine();
            bfw.write("</exam>");


            bfw.close();
            pst.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void writeXMLwithouthClasses() {
        try {
            BufferedWriter bfw = new BufferedWriter(new FileWriter("exam.xml"));
            PreparedStatement pst = c.prepareStatement("SELECT * FROM proveedores");
            ResultSet rs = pst.executeQuery();

            bfw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
            bfw.newLine();
            bfw.write("<exam>");
            bfw.newLine();
            bfw.write("<suppliers>");
            bfw.newLine();

            while (rs.next()) {
                int supplierId = rs.getInt("id");
                String supplierName = rs.getString("nombre");
                String supplierCountry = rs.getString("pais");

                bfw.write("<supplier id=\"" + supplierId + "\">");
                bfw.newLine();
                bfw.write("<name>" + supplierName + "</name>");
                bfw.newLine();
                bfw.write("<country>" + supplierCountry + "</country>");
                bfw.newLine();

                PreparedStatement pstProducts = c.prepareStatement("SELECT * FROM productos WHERE proveedor= ?");
                pstProducts.setInt(1, supplierId);
                ResultSet rsProducts = pstProducts.executeQuery();

                bfw.write("<products>");
                bfw.newLine();

                while (rsProducts.next()) {
                    int productId = rsProducts.getInt("id");
                    String productName = rsProducts.getString("nombre");
                    double productPrice = rsProducts.getDouble("precio");
                    String productCurrency = rsProducts.getString("moneda");

                    bfw.write("<product id=\"" + productId + "\">");
                    bfw.newLine();
                    bfw.write("<name>" + productName + "</name>");
                    bfw.newLine();
                    bfw.write("<price>" + productPrice + "</price>");
                    bfw.newLine();
                    bfw.write("<currency>" + productCurrency + "</currency>");
                    bfw.newLine();
                    bfw.write("</product>");
                    bfw.newLine();
                }

                bfw.write("</products>");
                bfw.newLine();
                bfw.write("</supplier>");
                bfw.newLine();

                rsProducts.close();
                pstProducts.close();
            }

            bfw.write("</suppliers>");
            bfw.newLine();
            bfw.write("</exam>");

            bfw.close();
            rs.close();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.openConnection();
        m.writeXMLwithouthClasses();

    }
}