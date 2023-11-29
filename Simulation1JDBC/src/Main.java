import java.sql.*;
import java.util.LinkedList;

public class Main {

    public Connection c;
    DatabaseMetaData databaseMetaData;


    public LinkedList<Instructor> instructors;
    public LinkedList<Student> students;
    public LinkedList<Drone> drones;
    public LinkedList<Lesson> lessons;
    public LinkedList<Flight> flights;


    public Main() {
        this.instructors = new LinkedList<Instructor>();
        this.students = new LinkedList<Student>();
        this.drones = new LinkedList<Drone>();
        this.lessons = new LinkedList<Lesson>();
        this.flights = new LinkedList<>();

    }

    public void openConnection() {
        String db = "academy";
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

    public void chargeDataIntoLists() {
        PreparedStatement pst = null;
        try {

            pst = c.prepareStatement("SELECT * FROM instructor");
            ResultSet rs = pst.executeQuery();


            pst.close();
            while (rs.next()) {
                instructors.add(new Instructor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
            }

        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    public void chargeDataIntoDB() {
        PreparedStatement pst = null;
        try {
            pst = c.prepareStatement("INSERT INTO instructor(id,nss,name,phone,address) VALUES(?,?,?,?,?)");
            for (Instructor i : instructors) {

                pst.setInt(1, i.getId());
                pst.setString(2, i.getNss());
                pst.setString(3, i.getName());
                pst.setInt(4, i.getPhone());
                pst.setString(5, i.getAddress());
                pst.executeUpdate();
            }


        } catch (Exception e) {

        }

    }

    public void show() {
        instructors.add(new Instructor(17, "1234567", "pepe", 12356677, "asdf"));

        for (Instructor i : instructors) {
            System.out.println(i);
        }
    }

    public void insertInto(Drone d) {
        PreparedStatement pst = null;
        try {
            pst = c.prepareStatement("UPDATE drone SET id=" + d.getId() + ", " + "brand='" + d.getBrand() + "'" + ", " + " model='" + d.getModel() + "'" + ", " + " size='" + d.getSize() + "'" + " where id= " + d.getId() + ";");
            if (pst.executeUpdate() > 0) {
                System.out.println("There was a drone with the id " + d.getId() + " and it was updated to the new data.");
            } else {
                //pst = c.prepareStatement()
                pst.executeUpdate("INSERT INTO drone(id,brand,model,size) VALUES(?,?,?,?)" + ";");
                pst.setInt(1, d.getId());
                pst.setString(2, d.getBrand());
                pst.setString(3, d.getModel());
                pst.setInt(4, d.getSize());
                pst.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    public void renameStudents() {


    }

    public void amountOfSessions() {
        PreparedStatement st = null;
        try {
            st = c.prepareStatement("SELECT * FROM ");

        } catch (Exception e) {
            e.printStackTrace();

        } finally {

        }

    }

    public static void main(String[] args) {
        Main m = new Main();
        m.openConnection();
        //m.chargeDataIntoLists();
        m.show();
        //m.chargeDataIntoDB();
        Drone d = new Drone(200, "Toyota", "SS-45", 40);
        m.insertInto(d);


    }
}