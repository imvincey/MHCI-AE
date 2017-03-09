package mhci.teamsix.ugs.incampus.ui;

import android.content.res.Resources;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mhci.teamsix.ugs.incampus.R;
import mhci.teamsix.ugs.incampus.asynctask.EventListingAsyncTask;
import mhci.teamsix.ugs.incampus.asynctask.EventListingNearestAsync;
import mhci.teamsix.ugs.incampus.estimote.TeamSixBeaconsID;
import mhci.teamsix.ugs.incampus.util.CardSpaceItemDeco;
import mhci.teamsix.ugs.incampus.util.Event;
import mhci.teamsix.ugs.incampus.util.EventAdapter;
import mhci.teamsix.ugs.incampus.util.UserSessionManager;

public class EventNearest extends AppCompatActivity {

    ActionBar actionBar;
    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;
    private List<Event> eventList;

    private SwipeRefreshLayout swipey;
    UserSessionManager usm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearest_event);

        recyclerView = (RecyclerView) findViewById(R.id.nearest_event_recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new CardSpaceItemDeco(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        swipey = (SwipeRefreshLayout) findViewById(R.id.swipeEventRefresh);
        swipey.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                // Refresh items
                refreshItems();
            }
        });
        usm = new UserSessionManager(getApplicationContext());
        callAsync();
    }

    public void callAsync(){
        String test = usm.getBeaconId();
        if (test.equals("-1")){
            test = "99";
        }
        EventListingNearestAsync async = new EventListingNearestAsync(this);
        async.execute(test);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void refreshItems() {
        callAsync();
    }

    public void onItemsLoadComplete(ArrayList<Event> eventList) {
        eventAdapter = new EventAdapter(this, eventList);
        recyclerView.setAdapter(eventAdapter);
        swipey.setRefreshing(false);
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
