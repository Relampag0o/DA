import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.security.KeyStore;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class CarManager {
    public LinkedList<Car> cars = new LinkedList<>();

    public CarManager() {
        this.cars = new LinkedList<Car>();
    }


    public void mockData(File f) {
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(f));
            String line = bfr.readLine();

            while ((line = bfr.readLine()) != null) {
                String[] details = line.split("#");
                cars.add(new Car(details[0], details[1], details[2], Double.parseDouble(details[3]), Integer.parseInt(details[4]), details[5]));

            }


            if (cars.size() > 0) {
                System.out.println("Data successfully loaded.");
            }

            bfr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showCars() {
        for (Car c : cars) {
            System.out.println(c);
        }
    }

    public void searchOcurrences(String o) {
        for (Car c : cars) {
            if (c.getBrand().equalsIgnoreCase(o) | c.getBrand().equalsIgnoreCase(o)) {
                System.out.println(c);
            }
        }

    }

    public void showCarsByColor() {
        LinkedHashMap<String, Integer> mostFrecuent = new LinkedHashMap<String, Integer>();
        int max = 0;
        String color = "";

        for (Car c : cars) {
            if (!mostFrecuent.containsKey(c.getColor()))
                mostFrecuent.put(c.getColor(), 1);
            else
                mostFrecuent.put(c.getColor(), mostFrecuent.get(c.getColor()) + 1);

        }

        for (Map.Entry<String, Integer> entry : mostFrecuent.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                color = entry.getKey();
            }
        }
        for (Car c : cars) {
            if (c.getColor().equalsIgnoreCase(color))
                System.out.println(c);
        }
    }

    public void test() {

    }


    public void showCarsByCv(int cv) {
        for (Car c : cars) {
            if (c.getCv() >= cv)
                System.out.println(c);
        }
    }

    public void writeData(String model) {
        try {
            BufferedWriter bfw = new BufferedWriter(new FileWriter("vehículos_" + model + ".txt"));

            for (Car c : cars) {
                if (c.getModel().equalsIgnoreCase(model)) {
                    bfw.write(c.getId() + ";" + c.getBrand() + ";" + c.getModel() + ";" + c.getCapacity() + c.getCv() + ";" + c.getColor() + '\n');
                }
            }

            bfw.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error..");
        }

    }

    public void groupByBrand(String brand) {

        try {
            BufferedWriter bfw = new BufferedWriter(new FileWriter(brand));

            cars.sort(new CVComparator());

            for (Car c : cars) {
                bfw.write(c.getId() + ";" + c.getBrand() + ";" + c.getModel() + ";" + c.getCapacity() +";" + c.getCv() + ";" + c.getColor() + '\n');
            }

            bfw.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error..");
        }


    }

    public void randomTest(){

    }

    public void raf(int index) {

        try {
            RandomAccessFile raf = new RandomAccessFile("todos.txt", "r");
            raf.seek(index);
            FileReader fr = new FileReader(raf.getFD());
            BufferedReader bfr = new BufferedReader(fr);
            String line = "";
            int counter = 1;

            /*
            RandomAccessFile raf = new RandomAccessFile(dbSrc, "r");
                    System.out.println(index);
                    raf.seek(index);
                    FileReader fr = new FileReader(raf.getFD());
                    BufferedReader bfr2 = new BufferedReader(fr);
                    System.out.println(bfr2.readLine());
             */
            while ((line = bfr.readLine()) != null) {
                if (index == counter) {

                    break;
                } else
                    counter++;
            }


            raf.close();
            bfr.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error..");
        }

    }

    public static void main(String[] args) {
        File f = new File(args[0]);
        CarManager cm = new CarManager();
        if (!f.isDirectory() && f.canRead()) {
            cm.mockData(f);
            //cm.showCars();
            //cm.searchOcurrences("Mazda");
            //cm.showCarsByColor();
            //cm.showCarsByCv(100);
            //cm.writeData("a4");
            //cm.groupByBrand("todos.txt");
            cm.groupByBrand("Toyota");
        } else
            System.out.println("The file does not exist or doesnt have read permission.");


    }

}