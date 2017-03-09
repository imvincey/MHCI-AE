package mhci.teamsix.ugs.incampus.asynctask;

import android.os.AsyncTask;

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
import java.util.List;

import mhci.teamsix.ugs.incampus.ui.StoreDetails;
import mhci.teamsix.ugs.incampus.util.Comment;

/**
 * Created by Vincey on 8/3/2017.
 */

public class CommentsAsyncTask extends AsyncTask<Void, Void, String> {
    private StoreDetails storeDetails;

    public CommentsAsyncTask(StoreDetails parent){
        storeDetails = parent;
    }
    @Override
    protected String doInBackground(Void... params) {
        String url = "http://ugsteamsix.esy.es/query.php?cid=0";
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

    protected void onPostExecute(String results){
        List<Comment> commentList = new ArrayList<Comment>();
        try {
            JSONObject object = new JSONObject(results);
            JSONArray jsonObj = object.getJSONArray("result");
            for (int i = 0; i < jsonObj.length(); i ++){
                String cid, name, time, rating, desc;
                if (!jsonObj.getJSONObject(i).isNull("cid")){
                    cid = jsonObj.getJSONObject(i).getString("cid");
                    name = jsonObj.getJSONObject(i).getString("name");
                    desc = jsonObj.getJSONObject(i).getString("desc");
                    rating = jsonObj.getJSONObject(i).getString("rating");
                    time = jsonObj.getJSONObject(i).getString("time");
                    Comment comment = new Comment(cid, name, desc, Float.parseFloat(rating), time);
                    commentList.add(comment);
                }
            }
            storeDetails.loadPage(commentList);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
