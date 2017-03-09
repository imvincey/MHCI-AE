package mhci.teamsix.ugs.incampus.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import mhci.teamsix.ugs.incampus.R;

/**
 * Created by Vincey on 6/3/2017.
 */

public class EventCardHolder extends RecyclerView.ViewHolder{
    public TextView eventName, eventLocation;
    public ImageView eventThumbnail;
    public EventCardHolder (View view){
        super(view);
        eventThumbnail = (ImageView) view.findViewById(R.id.thumbnail_event);
        eventName = (TextView) view.findViewById (R.id.event_name);
        eventLocation = (TextView) view.findViewById(R.id.event_location);
    }
}
