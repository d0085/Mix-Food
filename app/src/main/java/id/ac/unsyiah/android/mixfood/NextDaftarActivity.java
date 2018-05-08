package id.ac.unsyiah.android.mixfood;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import model.DBStatus;
import parsers.StatusJsonParser;

public class NextDaftarActivity extends AppCompatActivity {

    public static final String MIX_FOOD_PREFERENCES = "Mix_Food_Preferences";
    SharedPreferences sharedPreferences;

    private String data[] = new String[7];
    private EditText fullName;
    private EditText dob;
    private EditText phoneNum;
    private EditText address;
    private Button daftarBtn;
    private String a;

    ProgressBar pb;
    DBStatus status;

    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.arraycopy(getIntent().getStringArrayExtra("DATA_DAFTAR"), 0, data,0,3);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_daftar);

        sharedPreferences = getSharedPreferences(MIX_FOOD_PREFERENCES, Context.MODE_PRIVATE);

        pb = findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);

        fullName = findViewById(R.id.textFullName);
        dob = findViewById(R.id.textdob);
        phoneNum = findViewById(R.id.textNoHp);
        address = findViewById(R.id.textAddress);
        daftarBtn = findViewById(R.id.daftarBtn);
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dob.setText(sdf.format(myCalendar.getTime()));
    }

    private boolean editTextIsEmpty(EditText editText) {
        return editText.getText().toString().equals("");
    }

    public void intentBuatAkun(View view) throws Exception {

        if (editTextIsEmpty(fullName)) {
            fullName.setError(getString(R.string.fullname_error));
        }

        if (editTextIsEmpty(dob)) {
            dob.setError(getString(R.string.dob_error));
        }

        if (editTextIsEmpty(phoneNum)) {
            phoneNum.setError(getString(R.string.phone_error));
        }

        if (!editTextIsEmpty(fullName) && !editTextIsEmpty(dob) && !editTextIsEmpty(phoneNum)) {
            data[3] = fullName.getText().toString();
            data[4] = dob.getText().toString();
            data[5] = phoneNum.getText().toString();
            data[6] = address.getText().toString();

            String param = "username=" + data[0] + "&email=" + data[1] + "&pass=" + Encryptor.encrypt(data[2],data[0]) + "&nama=" + data[3] + "&tgl_lahir=" + data[4] + "&no_hp=" + data[5] + "&alamat=" + data[6];
            String uri = "http://cs.unsyiah.ac.id/~fdhari/tambah_pelanggan.php";

            if (InternetConnection.isOnline(getSystemService(Context.CONNECTIVITY_SERVICE))) {
                MyTask task = new MyTask();
                try {
                    task.execute(uri, param);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(this, R.string.no_internet, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void showDate(View view) {
        new DatePickerDialog(NextDaftarActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private class MyTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            daftarBtn.setEnabled(false);
            fullName.setEnabled(false);
            dob.setEnabled(false);
            phoneNum.setEnabled(false);
            address.setEnabled(false);
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            return HttpManager.getData(params[0], params[1]);
        }

        @Override
        protected void onPostExecute(String result) {

            status = StatusJsonParser.parseFeed(result);

            if (status.isStatusTrue()) {
                Toast.makeText(NextDaftarActivity.this, status.getDescription(), Toast.LENGTH_LONG).show();

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("username",data[0]);
                editor.putString("nama", data[3]);

                editor.apply();

                Intent intent = new Intent(NextDaftarActivity.this, Utama.class);
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(NextDaftarActivity.this, status.getDescription(), Toast.LENGTH_SHORT).show();
            }

            daftarBtn.setEnabled(true);
            fullName.setEnabled(true);
            dob.setEnabled(true);
            phoneNum.setEnabled(true);
            address.setEnabled(true);
            pb.setVisibility(View.INVISIBLE);

        }
    }
}
