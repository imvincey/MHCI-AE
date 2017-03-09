package mhci.teamsix.ugs.incampus.ui;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import mhci.teamsix.ugs.incampus.R;
import mhci.teamsix.ugs.incampus.asynctask.NorthShopAsyncTask;
import mhci.teamsix.ugs.incampus.util.CardSpaceItemDeco;
import mhci.teamsix.ugs.incampus.util.FoodStore;
import mhci.teamsix.ugs.incampus.util.FoodStoreAdapter;

public class NorthFragment extends Fragment {

    private RecyclerView recyclerView;
    private FoodStoreAdapter foodStoreAdapter;

    ProgressBar progBar;

    private SwipeRefreshLayout swipey;

    public NorthFragment (){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_north, container, false);

        progBar = (ProgressBar) view.findViewById(R.id.progbar_north);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_north);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new CardSpaceItemDeco(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        swipey = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_fragment_north);
        swipey.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                // Refresh items
                refreshItems();
            }
        });

        callAsync();

        return view;
    }

    public void callAsync(){
        NorthShopAsyncTask northShopAsyncTask = new NorthShopAsyncTask(this);
        northShopAsyncTask.execute();
    }

    void refreshItems() {
        // Load items
        // ...
        callAsync();
    }

    public void onItemsLoadComplete(ArrayList<FoodStore> foodStoreList) {
        foodStoreAdapter = new FoodStoreAdapter(getContext(), foodStoreList);
        recyclerView.setAdapter(foodStoreAdapter);
        swipey.setRefreshing(false);
        progBar.setVisibility(View.GONE);
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
