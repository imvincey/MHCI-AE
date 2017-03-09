package mhci.teamsix.ugs.incampus.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import mhci.teamsix.ugs.incampus.R;
import mhci.teamsix.ugs.incampus.asynctask.StoreImageRender;
import mhci.teamsix.ugs.incampus.util.Comment;
import mhci.teamsix.ugs.incampus.util.CommentAdapter;
import mhci.teamsix.ugs.incampus.util.FoodStore;
import mhci.teamsix.ugs.incampus.util.UserSessionManager;

public class StoreDetails extends AppCompatActivity {
    ActionBar actionBar;
    private RecyclerView recyclerView;
    private CommentAdapter commentAdapter;
    private TextView store_name, store_price, store_location, store_description;
    Button redeemed;
    UserSessionManager usm;

    public StoreDetails(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details);
        setStatusBarTranslucent(true);

        redeemed = (Button) findViewById(R.id.btn_redeem_coupon);
        redeemed.setClickable(false);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.store_recycler_view);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        FoodStore foodObj = (FoodStore) bundle.getSerializable("foodStore_details");

        store_name = (TextView) findViewById(R.id.store_name);
        store_price = (TextView) findViewById(R.id.store_price);
        store_location = (TextView) findViewById(R.id.store_location);
        store_description = (TextView) findViewById(R.id.store_description);

        usm = new UserSessionManager(getApplicationContext());
        String beaconId = usm.getBeaconId();
        if (beaconId == null){
            beaconId = "4";
        }

        store_name.setText(foodObj.getName());
        store_price.setText("Price Range:  $ " + foodObj.getPrice_range());
        if (foodObj.getLocation().equals("n")){
            store_location.setText("Location: North Canteen");
            if (beaconId.equals("1")){
                redeemed.setClickable(true);
            } else {
                redeemed.setClickable(false);
                redeemed.setText("Beacon not in range");
                redeemed.setBackgroundColor(getResources().getColor(R.color.iron));
            }
        } else if (foodObj.getLocation().equals("s")){
            store_location.setText("Location: South Canteen");
            if (beaconId.equals("2")){
                redeemed.setClickable(true);
            } else {
                redeemed.setClickable(false);
                redeemed.setText("Beacon not in range");
                redeemed.setBackgroundColor(getResources().getColor(R.color.iron));
            }
        } else if (foodObj.getLocation().equals("l")){
            store_location.setText("Location: Lawn");
            if (beaconId.equals("3")){
                redeemed.setClickable(true);
            } else {
                redeemed.setClickable(false);
                redeemed.setText("Beacon not in range");
                redeemed.setBackgroundColor(getResources().getColor(R.color.iron));
            }
        } else {
            store_location.setText("Location: Unknown");
            redeemed.setClickable(false);
            redeemed.setText("Beacon not in range");
            redeemed.setBackgroundColor(getResources().getColor(R.color.iron));
        }
        store_description.setText("Description: " + foodObj.getDesc());

        String[] imgUrl = foodObj.getImg();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setNestedScrollingEnabled(false);



        StoreImageRender storeImageRender = new StoreImageRender(StoreDetails.this);
        storeImageRender.execute(imgUrl);

    }

    protected void setStatusBarTranslucent(boolean makeTranslucent) {
        if (makeTranslucent) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
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

    public void loadPage (List<Comment> commentList){
        commentAdapter = new CommentAdapter(this, commentList);
        recyclerView.setAdapter(commentAdapter);
    }
}
