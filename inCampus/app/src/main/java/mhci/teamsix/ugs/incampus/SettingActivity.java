package mhci.teamsix.ugs.incampus;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {
    ActionBar actionBar;
    SeekBar distanceSeekBar;
    Switch _notiSwitch;
    int sessionDistance;
    boolean checker;

    UserSessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        final TextView distanceCounter = (TextView) findViewById(R.id.distVal_text);

        distanceSeekBar = (SeekBar) findViewById(R.id.distance_seek);
        _notiSwitch = (Switch) findViewById(R.id.noti_switch);
        session = new UserSessionManager(getApplicationContext());
        sessionDistance = 0;
        if (session.getDistance() != 0){
            sessionDistance = session.getDistance();
        }
        checker = session.getNoti();
        _notiSwitch.setChecked(checker);
        distanceSeekBar.setProgress(sessionDistance);
        distanceCounter.setText(sessionDistance + " m");
        distanceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                String toText = Integer.toString(progressChangedValue) + " m";
                distanceCounter.setText(toText);
                sessionDistance = progressChangedValue;
            }
        });
        _notiSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    checker = true;
                } else {
                    checker = false;
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                session.userSettings(sessionDistance, checker);
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
