package id.ac.unsyiah.android.mixfood;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 2000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        final SharedPreferences sharedPreferences = getSharedPreferences(NextDaftarActivity.MIX_FOOD_PREFERENCES, Context.MODE_PRIVATE);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent;
                if (sharedPreferences.getString("nama", "").equals("")) {
                    mainIntent = new Intent(MainActivity.this, LoginActivity.class);
                } else {
                    mainIntent = new Intent(MainActivity.this, Utama.class);
                }

                /* Create an Intent that will start the Menu-Activity. */
                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
