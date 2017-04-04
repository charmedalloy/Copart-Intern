import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ZipToLocation {
    static String API_KEY = //"Awz3TOoQfIYDhQI8rkPpEtHtbnlthRCSocWh0l8f46TlXCuoWkNBNv5ZSqtai53G";
            "ZuPi20JbImiSmPQwRg81JciUcxBz6K85fYnhY7tHtLzQBqmsGuuqHNkUlWtrGBwW";

    public static String getPlaceFromZipCode(String zipCode) {
        String clientKey = API_KEY;

        String url = "https://www.zipcodeapi.com/rest/" + clientKey + "/info.json/" + zipCode + "/radians";

        try {
            URL ur = new URL(url);
            HttpURLConnection con = (HttpURLConnection) ur.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            inputLine = reader.readLine();

            JSONObject json = new JSONObject(inputLine);
            String city = json.getString("city");
            String state = json.getString("state");
            return state;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public static double getDistance(String zip1, String zip2) {
        String API_Key = API_KEY;
        String url = "https://www.zipcodeapi.com/rest/" + API_Key + "/distance.json/" + zip1 + "/" + zip2 + "/km";
        String inputLine;
        try {
            URL ur = new URL(url);
            HttpURLConnection con = (HttpURLConnection) ur.openConnection();
            con.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            inputLine = reader.readLine();
            JSONObject json = new JSONObject(inputLine);
            String distance = json.getString("distance");
            return Double.parseDouble(distance);
        } catch (Exception e) {
            return Integer.MAX_VALUE;
        }
    }
}
