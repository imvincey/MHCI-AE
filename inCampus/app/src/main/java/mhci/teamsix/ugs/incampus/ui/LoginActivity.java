package mhci.teamsix.ugs.incampus.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Bind;
import mhci.teamsix.ugs.incampus.R;
import mhci.teamsix.ugs.incampus.util.UserSessionManager;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_FORGOT = 0;

    @Bind(R.id.input_matric) EditText _matricText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_login) Button _loginButton;
    @Bind(R.id.link_forgotPass) TextView _forgotPass;

    UserSessionManager session;
    boolean exitByBackPressed = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _forgotPass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), ForgetPassword.class);
                startActivityForResult(intent, REQUEST_FORGOT);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String matric = _matricText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.
        if (matric.equals("2228131l") && password.equals("1")) {
            session = new UserSessionManager(getApplicationContext());
            session.createUserLoginSession(matric);
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            // On complete call either onLoginSuccess or onLoginFailed
                            onLoginSuccess();
                            // onLoginFailed();
                            progressDialog.dismiss();
                        }
                    }, 3000);
        } else {
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            // On complete call either onLoginSuccess or onLoginFailed
                            // onLoginSuccess();
                            onLoginFailed();
                            progressDialog.dismiss();
                        }
                    }, 3000);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_FORGOT) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful requestPass logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
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

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        Intent next = new Intent(this, MainActivity.class);
        startActivity(next);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Wrong matriculation number / password. Please try again.", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String matric = _matricText.getText().toString();
        String password = _passwordText.getText().toString();

        if (matric.isEmpty()) {
            _matricText.setError("enter a valid matriculation number");
            valid = false;
        } else {
            _matricText.setError(null);
        }
        if (password.equals("1")){
            _passwordText.setError(null);
        }
        else if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
