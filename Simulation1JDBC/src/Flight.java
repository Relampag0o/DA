public class Flight {
    private int id;
    private int id_flight;
    private int id_student;
    private String drone;


    public Flight(int id, int id_flight, int id_student, String drone) {
        this.id = id;
        this.id_flight = id_flight;
        this.id_student = id_student;
        this.drone = drone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_flight() {
        return id_flight;
    }

    public void setId_flight(int id_flight) {
        this.id_flight = id_flight;
    }

    public int getId_student() {
        return id_student;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }

    public String getDrone() {
        return drone;
    }

    public void setDrone(String drone) {
        this.drone = drone;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", id_flight=" + id_flight +
                ", id_student=" + id_student +
                ", drone='" + drone + '\'' +
                '}';
    }
}
