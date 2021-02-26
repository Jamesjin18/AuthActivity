package edu.mda.authactivity;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncFlickrJSONDataForList extends AsyncTask<String,Void, JSONObject> {
    private ListActivity.MyAdapter adapter;

    public AsyncFlickrJSONDataForList(ListActivity.MyAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected JSONObject doInBackground(String... params){
        //url with Json format
        String stringUrl = params[0]+"&nojsoncallback=1";
        Log.i("URL",stringUrl);
        JSONObject jsonObject = null;
        //Create a URL object holding our url
        URL myUrl;
        try {
            myUrl = new URL(stringUrl);
            //opens the connection to a specified URL
            HttpURLConnection URLConnection = (HttpURLConnection) myUrl.openConnection();
            try {
                //BufferedInputStream allow us to read a stream of data through a buffer
                InputStream in = new BufferedInputStream(URLConnection.getInputStream());
                //get the JsonObject
                String s = readStream(in);
                jsonObject = new JSONObject(s);

            }finally {
                URLConnection.disconnect();
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
    protected void onPostExecute(JSONObject result) {
        if (result != null){
            try {
                String URLimage = result.getJSONArray("items").getJSONObject(0).getJSONObject("media").getString("m");
                Log.i("item 0:", URLimage);
                JSONArray ja = result.getJSONArray("items");
                //fill the adapter with URLs
                for (int i = 0; i < ja.length(); i++) {
                    adapter.dd(ja.getJSONObject(i).getJSONObject("media").getString("m"));
                }
                //set notifyDataSetChanged to the adapter
                adapter.notifyDataSetChanged();
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
