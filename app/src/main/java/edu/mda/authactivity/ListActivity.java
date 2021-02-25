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
        MyAdapter adapter = new MyAdapter();
        lv.setAdapter(adapter);

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
            vector.add(url);
        }
    }
}
