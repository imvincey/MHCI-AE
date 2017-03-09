package mhci.teamsix.ugs.incampus.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;

import mhci.teamsix.ugs.incampus.R;
import mhci.teamsix.ugs.incampus.asynctask.CarouselImageRender;
import mhci.teamsix.ugs.incampus.util.Event;


public class EventDetails extends AppCompatActivity {

    ActionBar actionBar;

    TextView _event_name, _event_date, _event_location, _event_description;
    Button buttonRegister;
    FrameLayout frame;
    boolean check = true;

    public EventDetails(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        setStatusBarTranslucent(true);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //ArrayList<Event> eventAL = getIntent().getParcelableArrayListExtra("EventList");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Event eventObj = (Event) bundle.getSerializable("event_details");

        _event_name = (TextView) findViewById(R.id.event_name);
        _event_date = (TextView) findViewById(R.id.event_date);
        _event_location = (TextView) findViewById(R.id.event_location);
        _event_description = (TextView) findViewById(R.id.event_details);
        buttonRegister = (Button) findViewById(R.id.register_event);
        frame = (FrameLayout) findViewById(R.id.eventframeLayout);

        _event_name.setText(eventObj.getEventName());
        _event_date.setText("Event Date: " + eventObj.getDate());
        _event_location.setText("Event Location: " + eventObj.getLocation());
        _event_description.setText("Event Description: " + eventObj.getDescription());

        String[] imgUrl = eventObj.getImgURL();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check) {
                    Toast.makeText(getApplicationContext(), "You've successfully registered for the event", Toast.LENGTH_SHORT).show();
                    buttonRegister.setText("Registered");
                    frame.setBackgroundColor(Color.RED);
                    check = false;
                } else {
                    Toast.makeText(getApplicationContext(), "You've successfully cancelled registering for the event", Toast.LENGTH_SHORT).show();
                    buttonRegister.setText("REGISTER NOW");
                    frame.setBackgroundColor(getResources().getColor(R.color.cyan));
                    check = true;
                }
            }
        });

        CarouselImageRender cir = new CarouselImageRender(EventDetails.this);
        cir.execute(imgUrl);
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
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
