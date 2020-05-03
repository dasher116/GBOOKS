package com.creativex.gbooks;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class FetchData extends AppCompatActivity {
    public  static  String log_tag="details";
    public static ArrayList<BookContents> extract(String api) {
        URL url;
        url = createUrl(api);
        Log.i(log_tag, "url created");
        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.i(log_tag,"no json response");
        }
        FetchData f=new FetchData();
        ArrayList<BookContents> books = new ArrayList<>();
        books=f.parsejson(jsonResponse);
        return books;
    }
    private   ArrayList<BookContents>parsejson(String url){
        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<BookContents> booksparse = new ArrayList<>();
        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and build up a list of Earthquake objects with the corresponding data.
            Log.i(log_tag,"JSON OBJECT ERRIR00");
            JSONObject ins = new JSONObject(url);
            Log.e(log_tag,ins.toString());
            JSONArray  items = ins.getJSONArray("items");
            for (int i = 0; i < ins.length(); i++) {
                JSONObject ob = items.getJSONObject(i);
                JSONObject volinfo = ob.getJSONObject("volumeInfo");
                String title = volinfo.getString("title");
                JSONArray authorname = volinfo.getJSONArray("authors");
                //if(authorname.isNull(0)){
                    //Log.i(log_tag,"NULLL");
                //}
                String author;
                author=authorname.getString(0);
                String description = volinfo.getString("publishedDate");
                String links = volinfo.getString("previewLink");
                BookContents ob1 = new BookContents(author, title, description, links);
                booksparse.add(ob1);
            }   }         // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
        catch (JSONException e) {
            ListView list=findViewById(R.id.listView);
            TextView nullview=findViewById(R.id.Null);
            nullview.setVisibility(View.VISIBLE);
            list.setEmptyView(nullview);
            try{

                Thread.sleep(3000);
            }catch (Exception f){
                Log.i(log_tag,"thread exception");
            }
            //Toast.makeText(getApplicationContext(),"NO BOOKS FOUND WITH YOUR SEARCH",Toast.LENGTH_LONG).show();
        }
        return booksparse;
    }
        // Return the list of earthquakes
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.i(log_tag,"error in network");

            }
        } catch (IOException e) {
            Log.e(log_tag, "Problem retrieving the results.", e);}
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static URL createUrl(String sampleJsonResponse) {
        URL url = null;
        try {
            url = new URL(sampleJsonResponse);
        } catch (MalformedURLException i) {
            Log.i(log_tag, "url not formed");
        }
        return url;
    }
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}

