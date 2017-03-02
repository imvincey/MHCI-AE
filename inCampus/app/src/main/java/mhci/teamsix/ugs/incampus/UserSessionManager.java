package mhci.teamsix.ugs.incampus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by Vincey on 28/2/2017.
 */

public class UserSessionManager {
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;
    private static final String PREFER_NAME = "inCAMPUS_Session";
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";

    public UserSessionManager(Context context){
        this.context = context;
        sharedPref = context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = sharedPref.edit();
    }

    public void createUserLoginSession(String matric_no){
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.putString("matric_no", matric_no);
        editor.apply();
    }

    public boolean checkLogin(){
        return !this.isUserLoggedIn();
    }

    public boolean isUserLoggedIn(){
        return sharedPref.getBoolean(IS_USER_LOGIN, false);
    }

    public String getUserDetails(){
        return sharedPref.getString("matric_no", "");
    }

    public void logoutUser(){
        editor.clear();
        editor.apply();
        Intent i = new Intent(context, LoginActivity.class);
        context.startActivity(i);

    }

}
