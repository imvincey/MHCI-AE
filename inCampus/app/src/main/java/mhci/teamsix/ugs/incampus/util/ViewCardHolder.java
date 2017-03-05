package mhci.teamsix.ugs.incampus.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import mhci.teamsix.ugs.incampus.R;

/**
 * Created by Vincey on 6/3/2017.
 */

public class ViewCardHolder extends RecyclerView.ViewHolder{
    public TextView storeName, byUser;
    public ImageView thumbnail, settingImg;
    public ViewCardHolder (View view){
        super(view);
        thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        storeName = (TextView) view.findViewById (R.id.shop_name);
        byUser = (TextView) view.findViewById(R.id.author);
        settingImg = (ImageView) view.findViewById(R.id.settingImg);
    }
}
