import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class IDRAC6KVM {

    private static final String IDRAC_URL = "http://" + System.getenv("IDRAC_IP") + "/cgi-bin/kvm?port=5900";

    public static void main(String[] args) {

        try {
            URL url = new URL(IDRAC_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // Request setup
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/x-www-form-urlencoded");

            // Send request
            con.setDoOutput(true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Display response
            System.out.println(response.toString());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
