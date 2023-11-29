import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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

    public void show() {

        for (Instructor i : instructors) {
            System.out.println(i);
        }

        for (Student i : students) {
            System.out.println(i);
        }

        for (Drone i : drones) {
            System.out.println(i);
        }
    }

    public void chargeDataFromDB() {
        PreparedStatement pst = null;
        try {

            // INSTRUCTORS
            pst = c.prepareStatement("SELECT * FROM instructor");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                instructors.add(new Instructor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
            }

            // STUDENTS
            pst = c.prepareStatement("SELECT * FROM student");
            rs = pst.executeQuery();
            while (rs.next()) {
                students.add(new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
            }

            // DRONES
            pst = c.prepareStatement("SELECT * FROM drone");
            rs = pst.executeQuery();
            while (rs.next()) {
                drones.add(new Drone(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }

            pst.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void chargeDataToDB() {
        PreparedStatement pst = null;

        try {
            c.setAutoCommit(false);

            for (Instructor i : instructors) {
                pst = c.prepareStatement("INSERT INTO instructor(id,nss,name,phone,address) VALUES(?,?,?,?,?)");
                pst.setInt(1, i.getId());
                pst.setString(2, i.getNss());
                pst.setString(3, i.getName());
                pst.setInt(4, i.getPhone());
                pst.setString(5, i.getAddress());

                pst.executeUpdate();
            }

            for (Student i : students) {
                pst = c.prepareStatement("INSERT INTO student(id,dni,name,phone,level) VALUES(?,?,?,?,?)");
                pst.setInt(1, i.getId());
                pst.setString(2, i.getDni());
                pst.setString(3, i.getName());
                pst.setInt(4, i.getPhone());
                pst.setString(5, i.getLevel());
                pst.executeUpdate();

            }

            for (Drone i : drones) {
                pst = c.prepareStatement("INSERT INTO drone(id,brand,model,size) VALUES(?,?,?,?)");
                pst.setInt(1, i.getId());
                pst.setString(2, i.getBrand());
                pst.setString(3, i.getModel());
                pst.setString(4, i.getSize());
                pst.executeUpdate();

            }
            c.commit();
            pst.close();


        } catch (Exception e) {
            try {
                c.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }


    }

    public void insertDrones(Drone d) {
        PreparedStatement pst = null;

        try {
            c.setAutoCommit(false);
            pst = c.prepareStatement("SELECT * FROM drone WHERE id=?");
            pst.setInt(1, d.getId());
            ResultSet rs = pst.executeQuery();
            Boolean found = false;
            if (rs.next())
                found = true;

            if (found) {
                pst = c.prepareStatement("UPDATE drone SET brand=?,model=?,size=? WHERE id=? ");
                pst.setString(1, d.getBrand());
                pst.setString(2, d.getModel());
                pst.setString(3, d.getSize());
                pst.setInt(4, d.getId());
                pst.executeUpdate();
            } else {
                pst = c.prepareStatement("INSERT INTO drone(id,brand,model,size) values(?,?,?,?) ");
                pst.setInt(1, d.getId());
                pst.setString(2, d.getBrand());
                pst.setString(3, d.getModel());
                pst.setString(4, d.getSize());
                pst.executeUpdate();


            }
            c.commit();
            pst.close();


        } catch (Exception e) {
            try {
                e.printStackTrace();
                c.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }


        }

    }

    public void renameStudents() {
        PreparedStatement pst = null;

        try {
            c.setAutoCommit(false);
            HashMap<String, Integer> map = new HashMap<String, Integer>();

            pst = c.prepareStatement("select name, count(name) as numberOfTimes from student group by name");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                map.put(rs.getString(1), rs.getInt(2));
            }
            int i = 1;


            for (Map.Entry<String, Integer> entryKey : map.entrySet()) {
                i = 1;
                while (i <= entryKey.getValue()) {
                    pst = c.prepareStatement("UPDATE student SET name=? WHERE name=?");
                    pst.setString(1, entryKey.getKey() + "_0" + i);
                    System.out.println(entryKey.getKey() + "_0" + i);
                    pst.setString(2, entryKey.getKey());
                    pst.executeUpdate();
                    i++;
                }

            }
            c.commit();
        } catch (Exception e) {
            try {
                c.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();


        }

    }

    public void sessionsPlayed() {
        PreparedStatement pst = null;
        try {
            pst = c.prepareStatement("SELECT student_id, lesson_id FROM Lesson_Taught order by student_id;");
            ResultSet rs = pst.executeQuery();
            HashMap<Integer, LinkedList<Integer>> students = new HashMap<Integer, LinkedList<Integer>>();

            while (rs.next()) {
                if (students.containsKey(rs.getInt(1))) {
                    students.get(rs.getInt(1)).add(rs.getInt(2));
                } else {
                    LinkedList<Integer> data = new LinkedList<>();
                    data.add(rs.getInt(2));
                    students.put(rs.getInt(1), data);
                }
            }

            for (Map.Entry<Integer, LinkedList<Integer>> entry : students.entrySet()) {
                System.out.println("Student id: " + entry.getKey() + " sessions: " + entry.getValue());

            }

            pst.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void manageIncidents() {
        /*
        Añadir una nueva tabla denominada “Incidencia” que representa un problema en un
        vuelo concreto con un alumno y/o un dron. Añade alguna incidencia. Tener en
        cuenta que las incidencias sólo pueden crearse en vuelos que aún no han finalizado
        (hora de fin es null).

         */

        PreparedStatement pst = null;

        try {
            c.setAutoCommit(false);
            pst = c.prepareStatement("select flight_completed.student_id,flight_completed.drone_id,flight.end_time  from flight \n" +
                    "INNER JOIN flight_completed on flight_completed.flight_id = flight.id\n" +
                    "WHERE flight.end_time is null;");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                pst = c.prepareStatement("INSERT INTO incident(student_id,drone_id)values(?,?)");
                pst.setInt(1, rs.getInt(1));
                pst.setInt(2, rs.getInt(2));
                pst.executeUpdate();

            }
            c.commit();
            pst.close();


        } catch (Exception e) {
            try {
                c.rollback();
                e.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


    }

    public void lessonsGroupedByStudent() {
        PreparedStatement pst = null;
        try {
            pst = c.prepareStatement("select student.name,count(*) as numberLessons,lesson.id,lesson.date,lesson.start_time from lesson \n" +
                    "INNER JOIN lesson_taught on lesson.id = lesson_taught.lesson_id\n" +
                    "INNER JOIN student on lesson_taught.student_id = student.id\n" +
                    "group by student.id;");

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                System.out.println("@Student: " + rs.getString(1));
                System.out.println(rs.getInt(2) + " Lessons: ");
                System.out.println("- Lesson <" + rs.getInt(3) +  ">" + " the day <" + rs.getString(4) + ">" + " at <" + rs.getString(5)+ ">");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.openConnection();
        //m.chargeDataFromDB();
        //m.show();
        /*
        m.instructors.add(new Instructor(100, "1234", "pepe", 5623723, "asfd"));
        m.instructors.add(new Instructor(101, "12345", "juan", 5623723, "asfd"));
        m.instructors.add(new Instructor(102, "12346", "Roberto", 5623723, "asfd"));

        m.students.add(new Student(110, "12348", "jose", 5623723, "asfd"));
        m.students.add(new Student(120, "12347", "manuel", 5623723, "asfd"));

        m.drones.add(new Drone(200, "light", "toyota", "Small"));
        m.drones.add(new Drone(203, "lightasdf", "toyota", "Small"));

         */

        // m.renameStudents();
        //m.sessionsPlayed();
        //m.manageIncidents();
        m.lessonsGroupedByStudent();


    }
}