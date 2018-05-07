package id.ac.unsyiah.android.mixfood.Fragment;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.ac.unsyiah.android.mixfood.R;
import id.ac.unsyiah.android.mixfood.Utama;

/**
 * Created by FMA on 27/04/2018.
 */

public class FragmentHome extends Fragment {
    View view;
    SharedPreferences sharedPreferences = Utama.getSharedPreferences();

    public FragmentHome() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment,container,false);
        TextView tv = (TextView) view.findViewById(R.id.textView1);
        String nama = sharedPreferences.getString("nama","");
        tv.setText("Nama : "+nama);
        return view;
    }
}
