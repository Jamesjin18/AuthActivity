package edu.mda.authactivity;

import android.view.View;


public class GetImageOnClickListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        //parameter which will be passed in the asynctask
        String url = "https://www.flickr.com/services/feeds/photos_public.gne?tags=trees&format=json";
        //start the new asynctask
        new AsyncFlickrJSONData().execute(url);
    }
}
