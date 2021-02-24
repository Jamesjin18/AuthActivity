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
        URL myUrl = null;
        Bitmap bitmap = null;
        try {
            myUrl = new URL(stringUrl);
            HttpURLConnection URLConnection = null;
            URLConnection = (HttpURLConnection) myUrl.openConnection();
            try {
                InputStream in = new BufferedInputStream(URLConnection.getInputStream());
                bitmap = BitmapFactory.decodeStream(in);
            } finally {
                URLConnection.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    protected void onPostExecute(Bitmap result) {
        if (result != null){
            FlickrApp.getImage().setImageBitmap(result);
        }else{
            Log.e("Error", "Error: Bitmap is null");
        }
    }
}
