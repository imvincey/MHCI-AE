package mhci.teamsix.ugs.incampus.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;
import mhci.teamsix.ugs.incampus.R;
import mhci.teamsix.ugs.incampus.estimote.BeaconID;
import mhci.teamsix.ugs.incampus.estimote.EstimoteCloudBeaconDetailsFactory;
import mhci.teamsix.ugs.incampus.estimote.ProximityContentManager;
import mhci.teamsix.ugs.incampus.util.UserSessionManager;
import mhci.teamsix.ugs.incampus.util.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    UserSessionManager session;
    boolean exitByBackPressed = false;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    //EXTENSION//
    private ProximityContentManager proximityContentManager;
    private static final Map<String, List<String>> PLACES_BY_BEACONS;

    static {
        Map<String, List<String>> placesByBeacons = new HashMap<>();
        placesByBeacons.put("16182:42505", new ArrayList<String>() {{ // lemon
            add("1");
        }});
        placesByBeacons.put("58809:14820", new ArrayList<String>() {{ // beetroot
            add("2");
        }});
        placesByBeacons.put("38534:15206", new ArrayList<String>() {{ // candy
            add("3");
        }});
        PLACES_BY_BEACONS = Collections.unmodifiableMap(placesByBeacons);
    }

    private List<String> placesNearBeacon(Beacon beacon) {
        String beaconKey = String.format("%d:%d", beacon.getMajor(), beacon.getMinor());
        if (PLACES_BY_BEACONS.containsKey(beaconKey)) {
            return PLACES_BY_BEACONS.get(beaconKey);
        }
        return Collections.emptyList();
    }

    private BeaconManager beaconManager;
    private Region region;

    // <MYCODE>
    private String mybeacon = "";
    // </MYCODE>

    //EXTENSION//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View nav_view = navigationView.getHeaderView(0);
        TextView _matricText = (TextView) nav_view.findViewById(R.id.user_matric);
        TextView _emailAddress = (TextView) nav_view.findViewById(R.id.user_email);
        CircleImageView civ = (CircleImageView) nav_view.findViewById(R.id.profile_image);

        session = new UserSessionManager(getApplicationContext());
        String userid = session.getUserDetails();
        _matricText.setText(userid);
        String email = userid + "@student.gla.ac.uk";
        _emailAddress.setText(email);
        String imgurl = "https://scontent-sin6-1.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/12135257_899001306842832_2146857047_n.jpg";
        Glide.with(this).load(imgurl).into(civ);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        //EXTENSION//
        beaconManager = new BeaconManager(this);
        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
                if (!list.isEmpty()) {
                    Beacon nearestBeacon = list.get(0);
                    List<String> places = placesNearBeacon(nearestBeacon);
                    // TODO: update the UI here
                    String text = places.toString().replaceAll("\\[", "").replaceAll("\\]","");

                    if (!text.equals(mybeacon)){
                        mybeacon = text;
                        if (mybeacon.equals("")){
                            showNotification("Out of region", "You're not in beacon's range, please exit the application.");
                            session.setBeaconId("-1");
                        } else {
                            session.setBeaconId(mybeacon);
                            showNotification(mybeacon, "is in ranged");
                        }
                    }
                } else {
                    showNotification("Out of region", "You're not in beacon's range, please exit the application.");
                    session.setBeaconId("-1");
                }
            }
        });
        region = new Region("ranged region", UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null);

        // <MYCODE>
        proximityContentManager = new ProximityContentManager(this,
                Arrays.asList(
                        new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 16182, 42505),  // lemon
                        new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 58809, 14820),  // beetroot
                        new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 38534, 15206)), // candy
                new EstimoteCloudBeaconDetailsFactory());
        // </MYCODE>
        //EXTENSION//
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), "All");
        adapter.addFragment(new NorthFragment(), "North");
        adapter.addFragment(new SouthFragment(), "South");
        adapter.addFragment(new LawnFragment(), "Lawn");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            // Disable going back to the MainActivity
            if (exitByBackPressed) {
                super.onBackPressed();
                finish();
            }

            this.exitByBackPressed = true;
            Toast.makeText(this, "Press back again to exit the application", Toast.LENGTH_LONG).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exitByBackPressed = false;
                }
            }, 10000);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_event) {
            Intent next = new Intent(this, EventAvailableActivity.class);
            startActivity(next);
            return true;
        } else if (id == R.id.nav_coupons) {
            /*Intent next = new Intent(this, CouponActivity.class);
            startActivity(next);*/
            return true;
        } else if (id == R.id.nav_settings) {
            Intent next = new Intent(this, SettingActivity.class);
            startActivity(next);
            return true;
        } else if (id == R.id.nav_logout) {
            logout();
            return true;
        }

        // Highlight the selected item has been done by NavigationView
        item.setChecked(true);
        // Set action bar title
        setTitle(item.getTitle());
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void logout (){
        View view = findViewById(R.id.drawer_layout);
        session = new UserSessionManager(view.getContext());
        session.logoutUser();
        finish();
    }

    //ESXTENSIONNN!N
    @Override
    protected void onResume() {
        super.onResume();

        // <MYCODE>
        SystemRequirementsChecker.checkWithDefaultDialogs(this);
        // </MYCODE>
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region);
            }
        });
    }

    @Override
    protected void onPause() {
        //beaconManager.stopRanging(region);
        super.onPause();
        //proximityContentManager.stopContentUpdates();
    }

    // <MYCODE>
    @Override
    protected void onDestroy() {
        super.onDestroy();
        proximityContentManager.destroy();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void showNotification(String title, String message) {
        Intent notifyIntent = new Intent(this, MainActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0, new Intent[]{notifyIntent}, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }
}
