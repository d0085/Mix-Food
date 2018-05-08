package id.ac.unsyiah.android.mixfood;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class DaftarActivity extends AppCompatActivity {

    private EditText username;
    private EditText email;
    private EditText pass;
    private EditText confPass;
    private ProgressBar progressBarUsername;
    private ProgressBar progressBarEmail;
    private Button daftarBtn;
    private ImageView emailCheck;
    private ImageView usernameCheck;
    private boolean emailExists = true;
    private boolean usernameExists = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        // get all view component
        username = findViewById(R.id.textFullName);
        email = findViewById(R.id.textdob);
        pass = findViewById(R.id.textNoHp);
        confPass = findViewById(R.id.textAddress);
        progressBarUsername = findViewById(R.id.progressBarUsername);
        progressBarEmail = findViewById(R.id.progressBarEmail);
        daftarBtn = findViewById(R.id.daftarBtn);
        emailCheck = findViewById(R.id.emailCheck);
        usernameCheck = findViewById(R.id.usernameCheck);

        // set the visibility and enable to false and visible
        progressBarUsername.setVisibility(View.INVISIBLE);
        progressBarEmail.setVisibility(View.INVISIBLE);
        emailCheck.setVisibility(View.INVISIBLE);
        usernameCheck.setVisibility(View.INVISIBLE);
        daftarBtn.setEnabled(false);

        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {

                    if (editTextIsEmpty(username)) {
                        username.setError(getString(R.string.username_error_daftar));
                    } else {
                        UsernameCheckTask usernameCheckTask = new UsernameCheckTask();
                        usernameCheckTask.execute("http://cs.unsyiah.ac.id/~fdhari/ada_pelanggan.php", "username=" + username.getText().toString());

                        setDaftarBtnEnabled(usernameExists, emailExists);
                    }

                }
            }
        });

        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {

                    if (editTextIsEmpty(email)){
                        email.setError(getString(R.string.email_error));
                    } else {
                        EmailCheckTask task2 = new EmailCheckTask();
                        task2.execute("http://cs.unsyiah.ac.id/~fdhari/ada_pelanggan.php", "email=" + email.getText().toString());

                        setDaftarBtnEnabled(usernameExists, emailExists);
                    }

                }
            }
        });

    }

    public void intentLogin(View view) {
        Intent intent = new Intent(DaftarActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean editTextIsEmpty(EditText editText) {
        return editText.getText().toString().equals("");
    }

    private boolean checkEqual(EditText et1, EditText et2) {
        return et1.getText().toString().equals(et2.getText().toString());
    }

    private void setDaftarBtnEnabled(boolean usernameExists, boolean emailExists) {
        if (!usernameExists && !emailExists) {
            daftarBtn.setEnabled(true);
        }
    }

    public void next(View view) {

        if (editTextIsEmpty(username)) {
            username.setError(getString(R.string.username_error_daftar));
        }

        if (editTextIsEmpty(email)) {
            email.setError(getString(R.string.email_error));
        }

        if (editTextIsEmpty(pass)) {
            pass.setError(getString(R.string.pass_error));
        }

        if (!checkEqual(pass, confPass)) {
            pass.setError(getString(R.string.pass_not_match));
            confPass.setError(getString(R.string.pass_not_match));
        }

        if(!editTextIsEmpty(username) && !editTextIsEmpty(email) && !editTextIsEmpty(pass) && checkEqual(pass, confPass)){

            Intent intent = new Intent(DaftarActivity.this, NextDaftarActivity.class);

            String data[] = {username.getText().toString(), email.getText().toString(), pass.getText().toString()};

            intent.putExtra("DATA_DAFTAR",data);

            startActivity(intent);
        }
    }

    private class EmailCheckTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            emailCheck.setVisibility(View.INVISIBLE);
            progressBarEmail.setVisibility(View.VISIBLE);
            email.setEnabled(false);
        }

        @Override
        protected String doInBackground(String... params) {
            return HttpManager.getData(params[0], params[1]);
        }

        @Override
        protected void onPostExecute(String result) {

            JSONObject jsonObject = null;

            try {
                jsonObject = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                assert jsonObject != null;
                emailExists = jsonObject.getBoolean("exists");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            progressBarEmail.setVisibility(View.INVISIBLE);
            email.setEnabled(true);

            if(!emailExists) emailCheck.setVisibility(View.VISIBLE);
            else email.setError(getString(R.string.email_taken));

        }
    }

    private class UsernameCheckTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            usernameCheck.setVisibility(View.INVISIBLE);
            progressBarUsername.setVisibility(View.VISIBLE);
            username.setEnabled(false);
        }

        @Override
        protected String doInBackground(String... params) {
            return HttpManager.getData(params[0], params[1]);
        }

        @Override
        protected void onPostExecute(String result) {

            JSONObject jsonObject = null;

            try {
                jsonObject = new JSONObject(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                assert jsonObject != null;
                usernameExists = jsonObject.getBoolean("exists");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            progressBarUsername.setVisibility(View.INVISIBLE);
            username.setEnabled(true);

            if (!usernameExists) usernameCheck.setVisibility(View.VISIBLE);
            else username.setError(getString(R.string.username_taken));

        }
    }
}
