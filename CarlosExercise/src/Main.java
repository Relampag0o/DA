import java.io.Closeable;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

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


    public void chargeDataToDb() {
        PreparedStatement pst = null;

        try {
            c.setAutoCommit(false);

            for (Student s : students) {
                pst = c.prepareStatement("INSERT INTO students(id, name, age) VALUES (?, ?, ?)");
                pst.setInt(1, s.getId());
                pst.setString(2, s.getName());
                pst.setInt(3, s.getAge());
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

    public void select() {
        PreparedStatement pst = null;

        try {
            pst = c.prepareStatement("SELECT * FROM students");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3));
            }

            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter id: ");
        int id = sc.nextInt();
        PreparedStatement pst = null;

        try {
            c.setAutoCommit(false);
            pst = c.prepareStatement("SELECT * FROM students WHERE id = ?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                pst = c.prepareStatement("UPDATE students SET  age = ? WHERE id = ?");
                pst.setInt(1, 18);
                pst.setInt(2, id);
                pst.executeUpdate();

            } else System.out.println("Student not found");
            c.commit();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStatement(pst);
        }

    }

    public void deleteStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter id: ");
        int id = sc.nextInt();
        PreparedStatement pst = null;

        try {
            c.setAutoCommit(false);
            pst = c.prepareStatement("SELECT * FROM students WHERE id = ?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                pst = c.prepareStatement("DELETE FROM students WHERE id = ?");
                pst.setInt(1, id);
                pst.executeUpdate();

            } else System.out.println("Student not found");

            c.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStatement(pst);
        }

    }

    public void createMap() {
        PreparedStatement pst = null;
        HashMap<Integer, LinkedList<String>> map = new HashMap<Integer, LinkedList<String>>();

        try {
            pst = c.prepareStatement("SELECT * FROM students");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (!map.containsKey(rs.getInt("age"))) {
                    LinkedList<String> names = new LinkedList<String>();
                    names.add(rs.getString("name"));
                    map.put(rs.getInt("age"), names);
                } else map.get(rs.getInt("age")).add(rs.getString("name"));
            }

            for (Map.Entry<Integer, LinkedList<String>> entry : map.entrySet()) {
                System.out.println("Age: " + entry.getKey() + " Names: " + entry.getValue());
            }

            pst.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addBooks() {
        PreparedStatement pst = null;
        try {
            c.setAutoCommit(false);
            pst = c.prepareStatement("INSERT INTO books(isbn, title, author) VALUES (?, ?, ?)");
            pst.setInt(1, 1);
            pst.setString(2, "Cien anios de soledad");
            pst.setString(3, "Gabriel Garcia Marquez");
            pst.executeUpdate();

            c.commit();

        } catch (Exception e) {
            try {
                c.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void findBooks(String pattern) {
        PreparedStatement pst = null;
        try {
            pst = c.prepareStatement("SELECT * FROM books WHERE title LIKE ? OR title like ?");
            pst.setString(1, "%" + pattern + "%");
            pst.setString(2, "%" + pattern + "%");


            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }


            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void borrowBook(int idStudent, int isbn) {

        PreparedStatement pst = null;
        try {
            c.setAutoCommit(false);
            pst = c.prepareStatement("INSERT INTO loans(loan_id,student_id,isbn_book,start_date,end_date) VALUES (?,?,?,?,?)");
            pst.setInt(1, 1);
            pst.setInt(2, idStudent);
            pst.setInt(3, isbn);
            pst.setDate(4, new Date(System.currentTimeMillis()));
            pst.setDate(5, null);
            pst.executeUpdate();

            pst = c.prepareStatement("UPDATE books SET available_copies = available_copies - 1 WHERE isbn = ?");
            pst.setInt(1, isbn);
            pst.executeUpdate();
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

    public void returnBook(int idStudent) {
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;

        try {
            c.setAutoCommit(false);
            pst = c.prepareStatement("UPDATE loans SET end_date = ? WHERE student_id = ?");
            pst.setDate(1, new Date(System.currentTimeMillis()));
            pst.setInt(2, idStudent);
            pst.executeUpdate();


            pst = c.prepareStatement("UPDATE books SET available_copies = available_copies + 1 WHERE isbn = ?");

            pst2 = c.prepareStatement("SELECT * FROM loans WHERE student_id = ?");
            pst2.setInt(1, idStudent);
            ResultSet rs = pst2.executeQuery();
            rs.next();
            int isbn = rs.getInt(3);

            pst.setInt(1, isbn);
            pst.executeUpdate();
            c.commit();
            pst.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createReportWithouthMap() {
        PreparedStatement pst = null;
        try {
            pst = c.prepareStatement("select books.title,GROUP_CONCAT(students.name) as \"Students\" from loans\n" +
                    "INNER JOIN students on loans.student_id = students.id\n" +
                    "INNER JOIN books on books.isbn = loans.isbn_book\n" +
                    "group by books.title;");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("Book name: " + rs.getString(1) + " Students: " + rs.getString(2));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void createReportWithMap() {
        HashMap<String, LinkedList<String>> map = new HashMap<String, LinkedList<String>>();

        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        PreparedStatement pst3 = null;

        try {
            pst = c.prepareStatement("select * from books");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                pst2 = c.prepareStatement("select * from loans\n" +
                        "INNER JOIN books on loans.isbn_book = books.isbn \n" +
                        "INNER JOIN students on loans.student_id = students.id\n" +
                        "where books.isbn=?");
                pst2.setInt(1, rs.getInt("isbn"));
                ResultSet rs2 = pst2.executeQuery();
                while (rs2.next()) {
                    if (!map.containsKey(rs.getString("title"))) {
                        LinkedList<String> names = new LinkedList<String>();
                        names.add(rs2.getString("students.name"));
                        map.put(rs.getString("books.title"), names);
                    } else map.get(rs.getString("books.title")).add(rs2.getString("students.name"));

                }
            }
            for (Map.Entry<String, LinkedList<String>> entry : map.entrySet()) {
                System.out.println("Book name: " + entry.getKey() + " Students: " + entry.getValue());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void retrieveStudentWithMoreBorrowedBooks() {
        PreparedStatement pst = null;
        try {
            pst = c.prepareStatement("select  students.name,count(*) as numberOfBooks from loans\n" +
                    "INNER JOIN students on students.id = loans.student_id\n" +
                    "group by students.id LIMIT 1");
            ResultSet rs = pst.executeQuery();
            rs.next();

            System.out.println("Student: " + rs.getString(1) + " borrowed " + rs.getInt(2) + " books");

            pst.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void retrieveMostBorrowedBook() {
        PreparedStatement pst = null;
        try {
            pst = c.prepareStatement("select books.title,count(*) as numberOfBooks  from loans\n" +
                    "INNER JOIN books on loans.isbn_book = books.isbn\n" +
                    "group by loans.isbn_book\n" +
                    "LIMIT 1;");
            ResultSet rs = pst.executeQuery();
            rs.first();
            System.out.println("Most borrowed book: " + rs.getString(1) + " borrowed " + rs.getInt(2) + " times");



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.openConnection();
/*
        m.students.add(new Student(1, "Carlos", 29));
        m.students.add(new Student(2, "Marina", 33));
        m.students.add(new Student(3, "Daniel", 29));
        m.students.add(new Student(4, "Verónica", 33));
        m.students.add(new Student(5, "Justo", 26));


        m.students.add(new Student(17, "Jose", 18));
        m.students.add(new Student(18, "Alfonso", 33));
        m.students.add(new Student(19, "Roberto", 33));
        m.students.add(new Student(20, "Lydia", 26));
        m.chargeDataToDb();
      */
        //m.select();
        //m.updateStudent();
        //m.deleteStudent();
        //m.createMap();
        //m.addBooks();
        //m.findBooks("tupimama");
        //m.returnBook(1);
        //m.createReportWithouthMap();
        //m.createReportWithMap();
        m.retrieveStudentWithMoreBorrowedBooks();
        m.retrieveMostBorrowedBook();

    }
}