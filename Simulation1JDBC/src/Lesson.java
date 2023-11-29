public class Lesson {

    private int id;
    private int id_lesson;
    private int id_student;


    public Lesson(int id, int id_lesson, int id_student) {
        this.id = id;
        this.id_lesson = id_lesson;
        this.id_student = id_student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_lesson() {
        return id_lesson;
    }

    public void setId_lesson(int id_lesson) {
        this.id_lesson = id_lesson;
    }

    public int getId_student() {
        return id_student;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", id_lesson=" + id_lesson +
                ", id_student=" + id_student +
                '}';
    }
}
