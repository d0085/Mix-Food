package id.ac.unsyiah.android.mixfood.Fragment;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.ac.unsyiah.android.mixfood.R;

/**
 * Created by FMA on 27/04/2018.
 */

public class FragmentCart extends Fragment {
    View view;
    public FragmentCart(){


    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.cart_fragment,container,false);
        return view;
    }
}
