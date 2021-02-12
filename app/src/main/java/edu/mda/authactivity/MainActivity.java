package edu.mda.authactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private boolean authenticated = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.authenticate);
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
        final TextView result = (TextView) findViewById(R.id.result);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Thread(new Runnable(){
                    public void run(){
                        URL url = null;

                        try {

                            url = new URL("https://httpbin.org/basic-auth/bob/sympa");
                            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                            try {

                                String basicAuth = "Basic " + Base64.encodeToString((username.getText()+":"+password.getText()).getBytes(),Base64.NO_WRAP);
                                urlConnection.setRequestProperty ("Authorization", basicAuth);
                                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                                String s = readStream(in);
                                Log.i("JFL", s);
                                final JSONObject res = new JSONObject(s);
                                authenticated = res.getBoolean("authenticated");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(MainActivity.this.authenticated){
                                            result.setText("Welcome");
                                        }else{
                                            result.setText("credential error. Please retry.");
                                        }
                                    }
                                });
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } finally {
                                urlConnection.disconnect();
                            }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });
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
