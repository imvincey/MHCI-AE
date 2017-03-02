package mhci.teamsix.ugs.incampus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInstaller;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

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
                }, 3000);
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
