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

import pl.progressnotebook.models.Category;

/**
 * Created by piotr on 30.03.16.
 */
public class ApiManager {

    private static final String TAG_COUNTS = "counts";
    private static final String TAG_RESULTS = "results";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";

    public static String GET(String urlString) {
        String resultStr = "";

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("User-Agent", "");
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            resultStr = convertInputStreamToString(inputStream);

        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();
        }

        return resultStr;
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

    public static Category[] parseCategoryFromJson(String jsonStr)  {
        Category[] result = null;

        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            JSONArray jsonResults = jsonObject.getJSONArray(TAG_RESULTS);
            result = new Category[jsonResults.length()];

            for (int i = 0; i < jsonResults.length(); i++) {
                String name = jsonResults.getJSONObject(i).getString(TAG_NAME);
                int id = jsonResults.getJSONObject(i).getInt(TAG_ID);
                result[i] = new Category(id, name);
            }
        } catch (JSONException e) {
            return result;
        }

        return result;
    }
}
