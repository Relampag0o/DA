import java.io.Closeable;
import java.sql.*;
import java.util.LinkedList;

public class Main {
    public Connection c;
    public LinkedList<Student> students;

    public Main() {
        this.students = new LinkedList<Student>();
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
        String db = "highschool";
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

    public void chargeData() {
        for (Student s : students) {
            addData("Students", "id, name, age", s.getId() + ", '" + s.getName() + "', " + s.getAge());
        }
    }

    public void select() {
        Statement st = null;
        try {
            st = c.createStatement(); // Crear el statement
            ResultSet rs = st.executeQuery("SELECT * FROM Students");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String output = "ID: " + id + ", Name: " + name + ", Age: " + age;
                System.out.println(output);
            }
        } catch (SQLException e) {
            showSQLError(e);
        } finally {
            closeStatement(st);
        }
    }


    public static void main(String[] args) {
        Main m = new Main();
        m.openConnection();
        /*
        m.createTable("CREATE TABLE Students " +
                "(id INTEGER not NULL, " +
                " name varchar(255), " +
                " age INTEGER )");
        */
        m.students.add(new Student(1, "Carlos", 29));
        m.students.add(new Student(2, "Marina", 33));
        m.students.add(new Student(3, "Daniel", 29));
        m.students.add(new Student(4, "Verónica", 33));
        m.students.add(new Student(5, "Justo", 26));


        //m.chargeData();
        m.select();
        //m.updateData("Students", "age='30'", "id='1'");
        m.delete("Students","id='2'");



    }
}