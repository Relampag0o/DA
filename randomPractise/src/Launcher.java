import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.RandomAccess;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Launcher {

    public ArrayList<String> names;


    public void ls(String path) {
        File f = new File(path);
        String[] dirs = {};
        if (f.isDirectory()) {
            dirs = f.list();
        } else
            System.out.println("File: " + f.getName() + " path: " + f.getPath());

        if (dirs != null)
            show(dirs);

    }

    public void cp() throws IOException {
        File f = new File("C:\\Users\\josem\\Desktop\\destino\\gonzalogafasXDXD.txt");
        File f2 = new File("C:\\Users\\josem\\Desktop\\destino\\kljasdsadf\\" + f.getName());


        if (f2.createNewFile())
            System.out.println("sucess");

    }

    public void show(String[] dirs) {
        for (String d : dirs) {
            System.out.println("Filename:" + d);
        }

    }

    public void countWords() {
        HashMap<String, Integer> words = new HashMap<String, Integer>();
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("C:\\Users\\josem\\Desktop\\destino\\yourmom.txt"));
            String line = "";

            while ((line = bfr.readLine()) != null) {
                String[] phrase = line.split(" ");

                for (String w : phrase) {
                    if (!words.containsKey(w))
                        words.put(w, 1);
                    else
                        words.put(w, words.get(w) + 1);
                }
            }

            for (Map.Entry<String, Integer> entry : words.entrySet()) {
                System.out.println(entry);
            }
            System.out.println("---");
            System.out.println(words.get("la"));

            bfr.close();
        } catch (Exception e) {

        }


    }

    public void createIndex() {
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("C:\\Users\\josem\\Downloads\\db.csv"));
            BufferedWriter bfw = new BufferedWriter(new FileWriter("index.csv"));

            String line = bfr.readLine();
            int counter = line.getBytes().length + 2;

            while ((line = bfr.readLine()) != null) {
                bfw.write(counter + ";" + line.split(";")[0] + '\n');
                counter += line.getBytes().length + 2;

            }

            bfr.close();
            bfw.close();
        } catch (Exception e) {


        }

    }

    public void raf(String dni) {
        try {

            BufferedReader bfr = new BufferedReader(new FileReader("index.csv"));
            String line = "";
            int index = -1;

            while ((line = bfr.readLine()) != null) {
                if (line.split(";")[1].equalsIgnoreCase(dni))
                    index = Integer.parseInt(line.split(";")[0]);
            }

            RandomAccessFile raf = new RandomAccessFile("C:\\Users\\josem\\Downloads\\db.csv", "r");
            raf.seek(index);
            System.out.println(raf.readLine());
            System.out.println(raf.readLine());


            bfr.close();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public static void main(String[] args) throws IOException {
        Launcher l = new Launcher();
        //l.countWords();
        //l.createIndex();
        //l.raf("74639192F");

        double n = 5;
        int n2 = 6;

        int n3 = (int) n;

    }
}