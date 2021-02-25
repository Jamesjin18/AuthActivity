package edu.mda.authactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class FlickrApp extends AppCompatActivity {
    private static ImageView image ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = (Button) findViewById(R.id.getImage);
        Button button2 = (Button) findViewById(R.id.get_list_image);
        image = (ImageView) findViewById(R.id.image);

        button1.setOnClickListener(new GetImageOnClickListener() {

        });
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //go to another activity (list activity)
                Intent myIntent = new Intent(FlickrApp.this, ListActivity.class);
                FlickrApp.this.startActivity(myIntent);
            }
        });
    }

    public static ImageView getImage() {
        return image;
    }

    public static void setImage(ImageView image) {
        FlickrApp.image = image;
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
