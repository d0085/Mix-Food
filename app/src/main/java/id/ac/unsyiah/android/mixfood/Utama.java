package id.ac.unsyiah.android.mixfood;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

import id.ac.unsyiah.android.mixfood.Fragment.FragmentCart;
import id.ac.unsyiah.android.mixfood.Fragment.FragmentHome;
import id.ac.unsyiah.android.mixfood.Fragment.FragmentNotif;
import id.ac.unsyiah.android.mixfood.Fragment.FragmentProfil;

public class Utama extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);

        sharedPreferences = getSharedPreferences(NextDaftarActivity.MIX_FOOD_PREFERENCES, Context.MODE_PRIVATE);

        tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new FragmentHome(), null);
        adapter.AddFragment(new FragmentNotif(), null);
        adapter.AddFragment(new FragmentCart(), null);
        adapter.AddFragment(new FragmentProfil(), null);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_notifications_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_shopping_cart_black_24dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_person_black_24dp);
    }

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

}
