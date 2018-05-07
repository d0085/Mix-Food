package id.ac.unsyiah.android.mixfood;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {


    private EditText username;
    private EditText password;

    ProgressBar pb;
    Button signInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.textUsername);
        password = (EditText) findViewById(R.id.textPassword);

        pb = findViewById(R.id.progressBarSignIn);
        signInBtn = findViewById(R.id.signInBtn);

        pb.setVisibility(View.INVISIBLE);
    }

    public void intentDaftar(View view) {
        Intent intent = new Intent(LoginActivity.this, DaftarActivity.class);
        startActivity(intent);
    }

    public void intentLogin(View view){
        if (editTextIsEmpty(username)){
            username.setError(getString(R.string.username_error));
        }
        if (editTextIsEmpty(password)) {
            password.setError(getString(R.string.password_error));
        }

        if (!editTextIsEmpty(username) && !editTextIsEmpty(password)) {

            String pass="";

            try {
                pass = Encryptor.encrypt(password.getText().toString(), username.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }


            if (!pass.equals("")) {
                if (InternetConnection.isOnline(getSystemService(Context.CONNECTIVITY_SERVICE))) {
                    MyTask task = new MyTask();
                    task.execute("http://cs.unsyiah.ac.id/~fdhari/data_pelanggan.php", "username=" + username.getText().toString() + "&pass=" + pass);
                } else {
                    Toast.makeText(this, R.string.no_internet, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, R.string.went_wrong, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean editTextIsEmpty(EditText editText) {
        return editText.getText().toString().equals("");
    }

    private class MyTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            signInBtn.setEnabled(false);
            username.setEnabled(false);
            password.setEnabled(false);

            pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            return HttpManager.getData(params[0], params[1]);
        }

        @Override
        protected void onPostExecute(String result) {

            try {
                JSONObject jsonObject = new JSONObject(result);

                String name = jsonObject.getString("nama");
                String user = jsonObject.getString("username");

                if (name.equals("null")) {
                    username.setError(getString(R.string.user_pass_mismatch));
                    password.setError(getString(R.string.user_pass_mismatch));
                } else {

                    SharedPreferences sharedPreferences = getSharedPreferences(NextDaftarActivity.MIX_FOOD_PREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("nama",name);
                    editor.putString("username",user);
                    editor.apply();

                    Intent intent = new Intent(LoginActivity.this, Utama.class);
                    startActivity(intent);
                    finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            pb.setVisibility(View.INVISIBLE);
            signInBtn.setEnabled(true);
            username.setEnabled(true);
            password.setEnabled(true);

        }
    }
}
