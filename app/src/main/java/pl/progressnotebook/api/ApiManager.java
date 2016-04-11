package pl.progressnotebook.api;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotr on 30.03.16.
 */
public class ApiManager {

    private static final String TAG_COUNTS = "counts";
    private static final String TAG_RESULTS = "results";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";

    public static String GET(String urlString) {
        StringBuffer chaine = new StringBuffer("");

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("User-Agent", "");
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();

            InputStream inputStream = connection.getInputStream();

            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = rd.readLine()) != null) {
                chaine.append(line);
            }

        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();
        }

        return chaine.toString();
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public static boolean isConnected(Context context){
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    public static ArrayList<String> parseJson(String jsonStr)  {
        ArrayList<String> result = new ArrayList<String>();

        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            JSONArray jsonResults = jsonObject.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < jsonResults.length(); i++) {
                String name = jsonResults.getJSONObject(i).getString(TAG_NAME);
                result.add(name);
            }
        } catch (JSONException e) {
            return result;
        }

        return result;
    }
}
