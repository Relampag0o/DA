import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main2 {
    public static void main(String[] args) {


        String apiUrl = "http://ip-api.com/json/24.48.0.1"; // URL de la API con la IP específica
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Configurar otros parámetros de la solicitud (por ejemplo, método, tiempo de espera, etc.)
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

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

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
