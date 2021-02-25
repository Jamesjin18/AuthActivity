package edu.mda.authactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import java.util.Vector;
import java.util.zip.Inflater;

public class ListActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ListView lv = (ListView) findViewById(R.id.list);
        String url = "https://www.flickr.com/services/feeds/photos_public.gne?tags=trees&format=json";
        MyAdapter adapter = new MyAdapter();
        lv.setAdapter(adapter);

        AsyncFlickrJSONDataForList asyncTaskList = new AsyncFlickrJSONDataForList(adapter);
        asyncTaskList.execute(url);

    }
    public class MyAdapter extends BaseAdapter{
        //a vector that store all url
        Vector<String> vector = new Vector<String>();

        //return the number of url
        @Override
        public int getCount() {
            return vector.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.i("JFL", "TODO");

            /*//modify the textview
            if( convertView == null ){
                //We create a View:
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                convertView = inflater.inflate(R.layout.textviewlay, parent, false);
            }
            TextView tv = (TextView) convertView.findViewById(R.id.textView);
            tv.setText(vector.get(position));*/

            if( convertView == null ){
                //We create a View:
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                convertView = inflater.inflate(R.layout.bitmaplayout, parent, false);
            }

            final ImageView iv = (ImageView) convertView.findViewById(R.id.imageView);
            ImageRequest ir = new ImageRequest(vector.get(position), new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    iv.setImageBitmap(response);

                }
            },0,0, ImageView.ScaleType.CENTER_CROP,null, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ListActivity.this,"Some Thing Goes Wrong", Toast.LENGTH_SHORT).show();
                    error.printStackTrace();

                }
            });
            MySingleton.getInstance(parent.getContext()).addToRequestQueue(ir);

            return convertView;
        }
        public void dd(String url){
            Log.i("JFL", "Adding to adapter url : " + url);
            vector.add(url);
        }
    }
}
