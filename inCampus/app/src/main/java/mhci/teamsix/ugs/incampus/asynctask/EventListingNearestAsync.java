package mhci.teamsix.ugs.incampus.asynctask;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import mhci.teamsix.ugs.incampus.R;
import mhci.teamsix.ugs.incampus.ui.EventNearest;
import mhci.teamsix.ugs.incampus.util.Event;

/**
 * Created by Vincey on 8/3/2017.
 */

public class EventListingNearestAsync extends AsyncTask <String, Void, String> {
    private EventNearest eventActivity;
    final ProgressBar progBar;
    public EventListingNearestAsync(EventNearest parent){
        eventActivity = parent;
        progBar =  (ProgressBar) eventActivity.findViewById(R.id.progbar_event_nearest);
    }

    @Override
    protected String doInBackground(String... params) {
        String url = "http://ugsteamsix.esy.es/query.php?bid=" + params[0];
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        HttpGet httpGet = new HttpGet(url);
        String text = null;

        try{
            HttpResponse response = httpClient.execute(httpGet, localContext);
            HttpEntity entity = response.getEntity();
            text = getASCIIContentFromEntity(entity);

        } catch (Exception e) {
            return null;
        }
        return text;
    }

    protected void onPreExecute(){
        progBar.setVisibility(View.VISIBLE);
    }

    protected void onPostExecute(String results){
        ArrayList<Event> eventList = new ArrayList<Event>();
        try {
            JSONObject object = new JSONObject(results);
            JSONArray jsonObj = object.getJSONArray("result");
            for (int i = 0; i < jsonObj.length(); i ++){
                String id, name, date, location, img, desc;
                if (!jsonObj.getJSONObject(i).isNull("eid")){
                    id = jsonObj.getJSONObject(i).getString("eid");
                    name = jsonObj.getJSONObject(i).getString("name");
                    date = jsonObj.getJSONObject(i).getString("date");
                    location = jsonObj.getJSONObject(i).getString("location");
                    img = jsonObj.getJSONObject(i).getString("img");
                    desc = jsonObj.getJSONObject(i).getString("desc");
                    String[] imgArray = {img};
                    Event event = new Event(id, name, date, location, imgArray, desc);
                    eventList.add(event);
                }
            }
            eventActivity.onItemsLoadComplete(eventList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        progBar.setVisibility(View.GONE);
    }



    public String getASCIIContentFromEntity(HttpEntity entity)
            throws IllegalStateException, IOException {
        InputStream in = entity.getContent();
        StringBuffer out = new StringBuffer();
        int n = 1;
        while (n > 0) {
            byte[] b = new byte[4096];
            n = in.read(b);
            if (n > 0)
                out.append(new String(b, 0, n));
        }
        return out.toString();
    }
}
