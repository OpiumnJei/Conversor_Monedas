package ConsumoAPIS.ExchangeAPI;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jerlinson G
 */
public class principal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //ArrayList para almacerar el codigo de cada moneda 
        List<String> CurrencyCode = new ArrayList<>();
        CurrencyCode.add("USD");
        CurrencyCode.add("DOP");
        CurrencyCode.add("EUR");
        CurrencyCode.add("COP");

        double cantidad = 0;
        String busqueda = null;
        String apiKey = "0aa8f5831a963de747393fe6"; //api key usuario
        HttpClient client = HttpClient.newHttpClient();

        while (true) {
            try {
                System.out.println(
                        """         
                        
                                      CONVERSIONES
                        
                        1. Peso dominicano [DOP] a Dolar [USD]
                        2. Dolar [USD] A Peso dominicano [DOP]
                        3. Euro [EUR] a Peso dominicano [DOP]
                        4. Peso dominicano [DOP] a Euro [EUR]
                        5. Peso colombiano [COP] a Dolar [USD]
                        6. Dolar [USD] a Peso colombiano [COP]
                        7. Salir
                        
                        Ingrese su opción: 
                        
                          """);
                int option = sc.nextInt();
                switch (option) {
                    case 1 -> {
                        System.out.println("Indica la cantidad a convertir: ");
                        cantidad = sc.nextDouble();
                        busqueda = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + CurrencyCode.get(1) + "/" + CurrencyCode.get(0) + "/" + cantidad;
                    }
                    case 2 -> {
                        System.out.println("Indica la cantidad a convertir: ");
                        cantidad = sc.nextDouble();
                        busqueda = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + CurrencyCode.get(0) + "/" + CurrencyCode.get(1) + "/" + cantidad;
                    }
                    case 3 -> {
                        System.out.println("Indica la cantidad a convertir: ");
                        cantidad = sc.nextDouble();
                        busqueda = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + CurrencyCode.get(2) + "/" + CurrencyCode.get(1) + "/" + cantidad;
                    }
                    case 4 -> {
                        System.out.println("Indica la cantidad a convertir: ");
                        cantidad = sc.nextDouble();
                        busqueda = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + CurrencyCode.get(1) + "/" + CurrencyCode.get(2) + "/" + cantidad;
                    }
                    case 5 -> {
                        System.out.println("Indica la cantidad a convertir: ");
                        cantidad = sc.nextDouble();
                        busqueda = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + CurrencyCode.get(3) + "/" + CurrencyCode.get(0) + "/" + cantidad;
                    }
                    case 6 -> {
                        System.out.println("Indica la cantidad a convertir: ");
                        cantidad = sc.nextDouble();
                        busqueda = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + CurrencyCode.get(0) + "/" + CurrencyCode.get(3) + "/" + cantidad;
                    }
                    case 7 -> {
                        System.out.println("Programa concluido");
                        System.exit(0);
                    }
                    default -> {
                        System.out.println("Opción no válida, intenta de nuevo.");
                    }
                }

                // Crea un objeto HttpRequest con la URL de búsqueda especificada.
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(busqueda))
                        .build();

                // Envía la solicitud HTTP al servidor y recibe la respuesta.
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                String responseBody = response.body();

                // Crea una instancia de Gson
                Gson gson = new Gson();

               
                ApiResponse apiResponse = gson.fromJson(responseBody, ApiResponse.class);
                
                apiResponse.setCantConversion(cantidad);
                // Imprime los detalles de la conversión
                System.out.println(apiResponse.toString());

            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(ApiResponse.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
