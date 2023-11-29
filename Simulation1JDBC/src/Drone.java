public class Drone {

    private int id;
    private String brand;
    private String model;

    private int size;

    public Drone(int id, String brand, String model, int size) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Drone{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model=" + model +
                ", size=" + size +
                '}';
    }
}
