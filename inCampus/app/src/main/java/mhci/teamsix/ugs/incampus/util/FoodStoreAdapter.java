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

import mhci.teamsix.ugs.incampus.R;
import mhci.teamsix.ugs.incampus.ui.StoreDetails;


/**
 * Created by Vincey on 6/3/2017.
 */

public class FoodStoreAdapter extends RecyclerView.Adapter<ViewCardHolder>{
    private Context appContext;
    private List<FoodStore> storeList;

    public FoodStoreAdapter (Context appContext, List<FoodStore> storeList){
        this.appContext = appContext;
        this.storeList = storeList;
    }

    @Override
    public ViewCardHolder onCreateViewHolder(ViewGroup parent, int viewtype){

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_card, parent, false);
        return new ViewCardHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewCardHolder holder, int position) {
        final FoodStore foodStore = storeList.get(position);
        holder.storeName.setText(foodStore.getName());
        String location;
        if (foodStore.getLocation().equals("n")){
            location = "North Canteen";
        } else if (foodStore.getLocation().equals("s")){
            location = "South Canteen";
        } else if (foodStore.getLocation().equals("l")){
            location = "Lawn";
        } else {
            location = "unknown";
        }
        holder.byUser.setText(location);

        Glide.with(appContext).load(foodStore.getImg()[0]).into(holder.thumbnail);


        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next = new Intent(view.getContext(), StoreDetails.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("foodStore_details", foodStore);
                next.putExtras(bundle);
                view.getContext().startActivity(next);
            }
        });

    }
    /**
     * Showing popup menu when tapping on 3 dots
     */


    @Override
    public int getItemCount() {
        return storeList.size();
    }
}
