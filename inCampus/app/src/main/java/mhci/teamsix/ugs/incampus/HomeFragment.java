package mhci.teamsix.ugs.incampus;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mhci.teamsix.ugs.incampus.util.CardSpaceItemDeco;
import mhci.teamsix.ugs.incampus.util.FoodStore;
import mhci.teamsix.ugs.incampus.util.FoodStoreAdapter;


public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private FoodStoreAdapter foodStoreAdapter;
    private List<FoodStore> foodStoreList;

    public HomeFragment (){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        foodStoreList = new ArrayList<>();
        foodStoreAdapter = new FoodStoreAdapter(getContext(), foodStoreList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new CardSpaceItemDeco(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(foodStoreAdapter);

        prepareCards();

        return view;
    }

    private void prepareCards() {
        int[] covers = new int[]{
                R.drawable.dp};

        FoodStore a = new FoodStore("Jap Food", "HELLOPANDA", covers[0]);
        foodStoreList.add(a);

        a = new FoodStore("Jap Food", "HELLOPANDA", covers[0]);
        foodStoreList.add(a);

        a = new FoodStore("Jap Food", "HELLOPANDA", covers[0]);
        foodStoreList.add(a);

        a = new FoodStore("Jap Food", "HELLOPANDA", covers[0]);
        foodStoreList.add(a);

        a = new FoodStore("Jap Food", "HELLOPANDA", covers[0]);
        foodStoreList.add(a);

        foodStoreAdapter.notifyDataSetChanged();
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
