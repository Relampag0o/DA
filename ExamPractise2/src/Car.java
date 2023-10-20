public class Car {
    private String id;
    private String brand;
    private String model;
    private double capacity;
    private int cv;
    private String color;

    public Car(String id, String brand, String model, double capacity, int cv, String color) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.capacity = capacity;
        this.cv = cv;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public int getCv() {
        return cv;
    }

    public void setCv(int cv) {
        this.cv = cv;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", capacity=" + capacity +
                ", cv=" + cv +
                ", color='" + color + '\'' +
                '}';
    }
}
