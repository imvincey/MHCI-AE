package mhci.teamsix.ugs.incampus.util;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import mhci.teamsix.ugs.incampus.R;


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
        FoodStore album = storeList.get(position);
        holder.storeName.setText(album.getName());
        holder.byUser.setText("Vince The Great");

        // loading album cover using Glide library
        Glide.with(appContext).load(album.getImage()).into(holder.thumbnail);

        holder.settingImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.settingImg);
            }
        });
    }
    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(appContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.card_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener(appContext));
        popup.show();
    }

    @Override
    public int getItemCount() {
        return storeList.size();
    }
}
