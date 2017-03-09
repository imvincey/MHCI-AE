package mhci.teamsix.ugs.incampus.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mhci.teamsix.ugs.incampus.R;
import mhci.teamsix.ugs.incampus.util.UserSessionManager;

public class SplashScreen extends AppCompatActivity {

    //session
    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        redirect();
                    }
                }, 2000);
    }

    public void redirect(){
        session = new UserSessionManager(getApplicationContext());
        if (session.checkLogin()){
            Intent next = new Intent(this, LoginActivity.class);
            startActivity(next);
            finish();
        } else {
            Intent next = new Intent(this, MainActivity.class);
            startActivity(next);
            finish();
        }
    }
}
