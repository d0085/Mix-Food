package id.ac.unsyiah.android.mixfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DaftarActivity extends AppCompatActivity {

    private EditText username;
    private EditText email;
    private EditText pass;
    private EditText confPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        username = findViewById(R.id.textFullName);
        email = findViewById(R.id.textdob);
        pass = findViewById(R.id.textNoHp);
        confPass = findViewById(R.id.textAddress);

        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {

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

            intent.putExtra("DATA_DAFTAR1",data);

            startActivity(intent);
        }
    }
}
