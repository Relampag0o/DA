import java.util.Comparator;

public class BrandComparator implements Comparator<Car> {

    @Override
    public int compare(Car c1, Car c2) {
        if (c1.getBrand().equalsIgnoreCase(c2.getBrand()))
            return c2.getCv()-c1.getCv();
        else
            return c1.getBrand().compareTo(c2.getBrand());

    }
}
