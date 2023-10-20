import java.io.*;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class App {
    public static void close(Closeable c) {
        try {
            c.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void createIndex(File src, File index) {
        if (src.exists() && src.isFile()) {
            BufferedReader bfr = null;
            BufferedWriter bfw = null;
            try {
                bfr = new BufferedReader(new FileReader(src));
                bfw = new BufferedWriter(new FileWriter(index));

                int characterAsInt = 0;
                int pos = 0;
                bfw.write(pos + ""); //Escribimos la posición de la primera frase
                bfw.newLine();
                while ((characterAsInt = bfr.read()) != -1) {
                    pos++;
                    char characterAsChar = (char) characterAsInt;
                    if (characterAsChar == '.') {
                        bfw.write(pos + "");
                        bfw.newLine();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                close(bfr);
                close(bfw);
            }
        } else {
            System.err.println("El fichero <" + src + "> no existe o no es un fichero");
        }

    }

    //Haciendo uso del RandomAccessFile, dada una frase, leerá el fichero original por la posición que toque y mostrará dicha frase.
    public void readPhrase(File src, File index, int numPhrase) {
        if (src.exists() && src.isFile() && index.exists() && index.isFile()) {
            BufferedReader bfrIndex = null;
            BufferedReader bfrSrc = null;
            try {
                bfrIndex = new BufferedReader(new FileReader(index));
                RandomAccessFile raf = new RandomAccessFile(src, "r");
                String line = "";
                int contador = 0;
                int pos = -1;
                while ((line = bfrIndex.readLine()) != null) {
                    contador++;
                    if (contador == numPhrase) {
                        pos = Integer.parseInt(line);
                        break;
                    }
                }
                if (pos > -1) {
                    raf.seek(pos);
                    bfrSrc = new BufferedReader(new FileReader(raf.getFD()));
                    String frase = "";
                    String line2 = "";
                    while ((line2 = bfrSrc.readLine()) != null) {
                        if (line2.contains(".")) {
                            frase += line2.split("\\.")[0];
                            break;
                        } else {
                            frase += line2;
                        }
                    }
                    System.out.println(frase);
                }


            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                close(bfrIndex);
                close(bfrSrc);
            }
        }
    }

    public static void main(String[] args) {
        //Parámetros:    quijote.txt       index.txt           numFrase
        //Descripción: Fichero con texto  Fichero de índice   nº frase a mostrar
        //String src = args[0];
        //String index = args[1];
        if (args.length == 3) {
            int numFrase = Integer.parseInt(args[2]);
            App app = new App();
            app.createIndex(new File(args[0]), new File(args[1]));
            app.readPhrase(new File(args[0]), new File(args[1]), numFrase);

        } else {
            System.err.println("Es obligatorio usar 3 argumentos.");
            System.out.println("Argumentos: Fichero_con_texto  Fichero_de-índice   nº_frase_a_mostrar");
        }

    }

}