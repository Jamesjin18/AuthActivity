package edu.mda.authactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.Vector;

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
        Vector<String> vector;

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

            return null;
        }
        public void dd(String url){
            Log.i("JFL", "Adding to adapter url : " + url);
            vector.add(url);
        }
    }
}
