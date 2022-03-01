package houk.postal.wikipedia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

public class App
{
    public static void main( String[] args ) {
        try {
            //Open a connection to wikipedia
            URL url = new URL("https://en.wikipedia.org/w/api.php?action=parse&section=0&prop=text&format=json&page=Cincinnati");
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

            //Set method type
            con.setRequestMethod("GET");
            System.out.println("Fetched article from Wikipedia. Response code: " + con.getResponseCode());

            //Read the response and disconnect
            JSONObject response = readJsonResponse(con);
            con.disconnect();

            //Get the nested text field within the JSON object
            String text = response.getJSONObject("parse").getJSONObject("text").toString();

            //Print the number of occurrences of Cincinnati
            System.out.println(String.format("Number of occurrences of Cincinnati in the article: %s",
                countOccurrences(text, "Cincinnati")));
        } catch (Exception e) {
            System.out.println(String.format("This URL was hardcoded, you're probably doing something wrong! \nMore details: %s", e.getMessage()));
        }
    }

    /**
     *
     * @param connection an active HTTP connection to a url
     * @return a JSONObject of the parsed response
     * @throws IOException
     */
    private static JSONObject readJsonResponse(HttpURLConnection connection) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return new JSONObject(response.toString());
    }

    /**
     *
     * @param source the string we are searching for occurrences in
     * @param search the string whose occurrences are being counted
     * @return int: the number of times the search appears in the source string (case-sensitive)
     */
    protected static int countOccurrences(String source, String search) {
        int index = source.indexOf(search);
        int count = 0;

        while (index > -1) {
            count++;
            index = source.indexOf(search, index + 1);
        }

        return count;
    }
}
