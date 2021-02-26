package edu.mda.authactivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncBitmapDownloader extends AsyncTask<String,Void, Bitmap> {

    @Override
    protected Bitmap doInBackground(String... params) {
        String stringUrl = params[0];
        URL myUrl;
        Bitmap bitmap = null;
        try {
            myUrl = new URL(stringUrl);
            //opens the connection to a specified URL
            HttpURLConnection URLConnection;
            URLConnection = (HttpURLConnection) myUrl.openConnection();
            try {
                //BufferedInputStream allow us to read a stream of data through a buffer
                InputStream in = new BufferedInputStream(URLConnection.getInputStream());
                //Decode an input stream into a bitmap
                bitmap = BitmapFactory.decodeStream(in);
            } finally {
                //disconnect the Http connection
                URLConnection.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    protected void onPostExecute(Bitmap result) {
        if (result != null){
            //set the image view
            FlickrApp.getImage().setImageBitmap(result);
        }else{
            Log.e("Error", "Error: Bitmap is null");
        }
    }
}
