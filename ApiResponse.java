
package ConsumoAPIS.ExchangeAPI;

/**
 *
 * @author Jerlinson G
 */
public class ApiResponse {
       String base_code;
       String target_code;
       double conversion_rate;
       double conversion_result;
       transient double cantConversion; // se coloca transient para que este atributo no sea serializado

         void setCantConversion(double cantConversion) {
            this.cantConversion = cantConversion;
        }

        double getCantConversion() {
            return cantConversion;
        }

        
        @Override
        public String toString() {
            return "\n" +cantConversion + " " + base_code+ " " + "equivalen a "+ conversion_result + " " + target_code;
                         
        }
}
