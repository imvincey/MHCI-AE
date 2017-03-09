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

import mhci.teamsix.ugs.incampus.ui.LawnFragment;
import mhci.teamsix.ugs.incampus.util.FoodStore;

/**
 * Created by Vincey on 9/3/2017.
 */

public class LawnShopAsyncTask extends AsyncTask<Void, Void, String> {
    private LawnFragment lawnFragment;

    public LawnShopAsyncTask(LawnFragment lawnFragment){
        this.lawnFragment = lawnFragment;
    }


    @Override
    protected String doInBackground(Void... params) {
        String url = "http://ugsteamsix.esy.es/query.php?sbid=0";
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
        ArrayList<FoodStore> foodStoreList = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(results);
            JSONArray jsonObj = object.getJSONArray("result");
            for (int i = 0; i < jsonObj.length(); i ++){
                String sid, name, price, desc, img, location, coupon;
                if (!jsonObj.getJSONObject(i).isNull("sid")){
                    sid = jsonObj.getJSONObject(i).getString("sid");
                    name = jsonObj.getJSONObject(i).getString("name");
                    price = jsonObj.getJSONObject(i).getString("price");
                    desc = jsonObj.getJSONObject(i).getString("desc");
                    img = jsonObj.getJSONObject(i).getString("img");
                    location = jsonObj.getJSONObject(i).getString("location");
                    coupon = jsonObj.getJSONObject(i).getString("coupon");
                    String[] imgArray = {img};
                    if (location.equals("l")) {
                        FoodStore store_details = new FoodStore(sid, name, price, desc, imgArray, location, coupon);
                        foodStoreList.add(store_details);
                    }
                }
            }
            lawnFragment.onItemsLoadComplete(foodStoreList);
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
