import java.util.Comparator;

public class BrandComparator implements Comparator<Car> {

    @Override
    public int compare(Car c1, Car c2) {
        return c1.getBrand().compareTo(c2.getBrand());
    }
}
