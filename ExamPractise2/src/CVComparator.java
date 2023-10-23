import java.util.Comparator;

public class CVComparator implements Comparator<Car> {
    @Override
    public int compare(Car c1, Car c2) {
        return c2.getCv()-c1.getCv();
    }
}
