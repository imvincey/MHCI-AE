package mhci.teamsix.ugs.incampus.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mhci.teamsix.ugs.incampus.R;
import mhci.teamsix.ugs.incampus.asynctask.EventListingAsyncTask;
import mhci.teamsix.ugs.incampus.asynctask.ForStaticDataToTest;
import mhci.teamsix.ugs.incampus.util.CardSpaceItemDeco;
import mhci.teamsix.ugs.incampus.util.Event;
import mhci.teamsix.ugs.incampus.util.EventAdapter;
import mhci.teamsix.ugs.incampus.util.FoodStore;
import mhci.teamsix.ugs.incampus.util.FoodStoreAdapter;

public class EventAvailableActivity extends AppCompatActivity {
    ActionBar actionBar;
    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;
    private List<Event> eventList;

    private SwipeRefreshLayout swipey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_available);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.event_recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new CardSpaceItemDeco(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        swipey = (SwipeRefreshLayout) findViewById(R.id.swipeEventRefresh);
        swipey.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                // Refresh items
                refreshItems();
            }
        });

        callAsync();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_event_nearest, menu);
        return true;
    }

    public void callAsync(){
        EventListingAsyncTask async = new EventListingAsyncTask(this);
        async.execute();
    }

    public Event getEventObject (){
        ForStaticDataToTest forStaticDataToTest = new ForStaticDataToTest();
        return forStaticDataToTest.getStaticEventData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.go_to_event_nearest:
                Intent next = new Intent(this, EventNearest.class);
                startActivity(next);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void refreshItems() {
        callAsync();
    }

    public void onItemsLoadComplete(ArrayList<Event> eventList) {
        this.eventList = eventList;
        eventList.add(getEventObject());
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
