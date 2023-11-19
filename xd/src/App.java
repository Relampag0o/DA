import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class App {

    //String apiUrl = "https://neutrinoapi.net/geocode-address";

    // Usar el Root Key como contraseña para la autenticación
    // String rootKey = "FA0Ejml1O4NPBdb3x0ZWJqKPF0wosmxCegd3ca7zUPmgfu6I";


    public static void main(String[] args) {
        String apiUrl = "https://neutrinoapi.net/geocode-address";
        String userId = "Relampag0";
        String apiKey = "FA0Ejml1O4NPBdb3x0ZWJqKPF0wosmxCegd3ca7zUPmgfu6I";
        String addressToGeocode = "Montijo, Cuba 58"; // Modifica con la dirección que quieras geocodificar

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configurar la autenticación con user-id y api-key
            connection.setRequestProperty("user-id", userId);
            connection.setRequestProperty("api-key", apiKey);

            // Configurar otros parámetros de la solicitud (por ejemplo, método, tiempo de espera, etc.)
            connection.setRequestMethod("POST"); // Cambiado a POST
            connection.setDoOutput(true); // Habilitar la escritura

            // Enviar los datos de la dirección a geocodificar
            try (OutputStream os = connection.getOutputStream()) {
                String postData = "address=" + addressToGeocode +
                        "&house-number=" +
                        "&street=" +
                        "&city=" +
                        "&county=" +
                        "&state=" +
                        "&postal-code=" +
                        "&country-code=" +
                        "&language-code=en" +
                        "&fuzzy-search=false";
                os.write(postData.getBytes(StandardCharsets.UTF_8));
            }

            // Obtener la respuesta
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                // Procesar la respuesta JSON
                System.out.println("Respuesta de la API: " + response.toString());
            } else {
                System.out.println("Error al hacer la solicitud. Código de respuesta: " + responseCode);
            }

            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}








