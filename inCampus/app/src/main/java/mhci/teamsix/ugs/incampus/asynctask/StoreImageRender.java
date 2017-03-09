package mhci.teamsix.ugs.incampus.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import mhci.teamsix.ugs.incampus.R;
import mhci.teamsix.ugs.incampus.ui.StoreDetails;

/**
 * Created by Vincey on 7/3/2017.
 */

public class StoreImageRender extends AsyncTask<String, Void, List<Bitmap>> {
    StoreDetails storeDetails;
    CarouselView carouselView;
    ProgressBar progBar;
    List<Bitmap> imageList = new ArrayList<Bitmap>();
    public StoreImageRender (StoreDetails storeDetails){
        this.storeDetails = storeDetails;
        progBar = (ProgressBar) storeDetails.findViewById(R.id.prog_bar_store_details);
    }

    public void onPreExecute(){
        progBar.setVisibility(View.VISIBLE);
        carouselView = (CarouselView) storeDetails.findViewById(R.id.carousel_view_store_details);
        carouselView.setImageListener(imageListener);
    }
    @Override
    protected List<Bitmap> doInBackground(String... params) {
        for (String url : params) {
            Bitmap bmImg = null;
            try {
                InputStream in = new java.net.URL(url).openStream();
                bmImg = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
            }
            imageList.add(bmImg);
        }
        return imageList;
    }

    protected void onPostExecute(List<Bitmap> imageList){
        carouselView.setPageCount(imageList.size());
        new CommentsAsyncTask(storeDetails).execute();
        progBar.setVisibility(View.GONE);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageBitmap(imageList.get(position));
        }
    };
}
