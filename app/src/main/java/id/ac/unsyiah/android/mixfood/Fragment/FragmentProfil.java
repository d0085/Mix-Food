package id.ac.unsyiah.android.mixfood.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import id.ac.unsyiah.android.mixfood.LoginActivity;
import id.ac.unsyiah.android.mixfood.R;
import id.ac.unsyiah.android.mixfood.Utama;

/**
 * Created by FMA on 27/04/2018.
 */

public class FragmentProfil extends Fragment{
    View view;
    Button btn;
    ProgressBar pb;

    public FragmentProfil(){}

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profil_fragment,container,false);
        btn = (Button) view.findViewById(R.id.signOutBtn);
        pb = view.findViewById(R.id.progressBarProfil);
        pb.setVisibility(View.INVISIBLE);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pb.setVisibility(View.VISIBLE);

                btn.setEnabled(false);

                SharedPreferences sharedPreferences = Utama.getSharedPreferences();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                Intent intent = new Intent(getContext(), LoginActivity.class);
                getContext().startActivity(intent);
                ((Activity)getContext()).finish();

                btn.setEnabled(true);
                pb.setVisibility(View.INVISIBLE);
            }
        });
        return view;
    }
}
