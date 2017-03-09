package mhci.teamsix.ugs.incampus.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import mhci.teamsix.ugs.incampus.ui.EventDetails;
import mhci.teamsix.ugs.incampus.R;

/**
 * Created by Vincey on 6/3/2017.
 */

public class EventAdapter extends RecyclerView.Adapter<EventCardHolder> {
    private Context appContext;
    private List<Event> eventList;

    public EventAdapter (Context appContext, List<Event> eventList){
        this.appContext = appContext;
        this.eventList = eventList;
    }

    @Override
    public EventCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_card, parent, false);
        return new EventCardHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final EventCardHolder holder, final int position) {
        final Event event = eventList.get(position);
        holder.eventName.setText(event.getEventName());
        holder.eventLocation.setText(event.getLocation());

        Glide.with(appContext).load(event.getImgURL()[0]).into(holder.eventThumbnail);

        holder.eventThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next = new Intent(view.getContext(), EventDetails.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("event_details", event);
                next.putExtras(bundle);
                view.getContext().startActivity(next);

            }
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
