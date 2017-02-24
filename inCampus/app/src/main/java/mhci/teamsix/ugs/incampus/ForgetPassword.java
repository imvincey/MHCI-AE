package mhci.teamsix.ugs.incampus;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Bind;

public class ForgetPassword extends AppCompatActivity {
    private static final String TAG = "ForgetPassword";

    @Bind(R.id.matric_no) EditText _matricNo;
    @Bind(R.id.btn_request) Button _requestBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);

        _requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPass();
            }
        });
    }

    public void requestPass() {
        Log.d(TAG, "Requesting Password");

        if (!validate()) {
            onRequestFailed();
            return;
        }

        _requestBtn.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(ForgetPassword.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Requesting for password...");
        progressDialog.show();

        String matric = _matricNo.getText().toString();

        // TODO: Check if matric exist or not.
        if (matric.equals("2228131l")) {
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            // On complete call either onSignupSuccess or onSignupFailed
                            // depending on success
                            onRequestSuccess();
                            // onRequestFailed();
                            progressDialog.dismiss();
                        }
                    }, 3000);
        } else {
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            // On complete call either onSignupSuccess or onSignupFailed
                            // depending on success
                            // onRequestSuccess();
                            onRequestFailed();
                            progressDialog.dismiss();
                        }
                    }, 3000);
        }
    }

    @Override
    public void onBackPressed(){
        finish();
    }

    public void onRequestSuccess() {
        _requestBtn.setEnabled(true);
        finish();
        Toast.makeText(getBaseContext(), "Please check your email for new password", Toast.LENGTH_LONG).show();
    }

    public void onRequestFailed() {
        Toast.makeText(getBaseContext(), "Requesting of password failed", Toast.LENGTH_LONG).show();

        _requestBtn.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String matric = _matricNo.getText().toString();


        if (matric.isEmpty()) {
            _matricNo.setError("enter a valid matriculation number");
            valid = false;
        } else {
            _matricNo.setError(null);
        }

        return valid;
    }
}