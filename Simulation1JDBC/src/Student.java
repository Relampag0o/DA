public class Student {
    private int id;
    private String dni;
    private String name;
    private int phone;
    private String level;


    public Student(int id, String dni, String name, int phone, String level) {
        this.id = id;
        this.dni = dni;
        this.name = name;
        this.phone = phone;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", level='" + level + '\'' +
                '}';
    }
}