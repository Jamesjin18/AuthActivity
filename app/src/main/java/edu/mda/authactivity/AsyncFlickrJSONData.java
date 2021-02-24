package edu.mda.authactivity;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncFlickrJSONData extends AsyncTask<String,Void, JSONObject> {
    
    @Override
    protected JSONObject doInBackground(String... params){
        String stringUrl = params[0]+"&nojsoncallback=1";
        Log.i("URL",stringUrl);
        JSONObject jsonObject = null;
        //Create a URL object holding our url
        URL myUrl = null;
        try {
            myUrl = new URL(stringUrl);
            HttpURLConnection URLConnection = (HttpURLConnection) myUrl.openConnection();
            try {
                InputStream in = new BufferedInputStream(URLConnection.getInputStream());
                String s = readStream(in);
                jsonObject = new JSONObject(s);

            }finally {
                URLConnection.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
    protected void onPostExecute(JSONObject result) {
        if (result != null){
            Log.i("jsonObject", result.toString());
            try {
                String URLimage = result.getJSONArray("items").getJSONObject(0).getJSONObject("media").getString("m");
                Log.i("item 0:", URLimage);
                new AsyncBitmapDownloader().execute(URLimage);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            Log.e("Error", "Error: jsonObject is null");
        }

    }
    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }
}
