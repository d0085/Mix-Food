package id.ac.unsyiah.android.mixfood.Fragment;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.ac.unsyiah.android.mixfood.R;
import id.ac.unsyiah.android.mixfood.RecyclerViewAdapter;
import id.ac.unsyiah.android.mixfood.Tempat;
import id.ac.unsyiah.android.mixfood.Utama;

/**
 * Created by FMA on 27/04/2018.
 */

public class FragmentHome extends Fragment {
    View view;
    private RecyclerView myrecylerview;
    private List<Tempat> listTempat;

    public FragmentHome() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment,container,false);
        myrecylerview = (RecyclerView) view.findViewById(R.id.tempatRecylerview);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), listTempat);
        myrecylerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecylerview.setAdapter(recyclerViewAdapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listTempat = new ArrayList<>();
        listTempat.add(new Tempat("Solong Coffe", "Lampeuneurut", "Rating: 5.00", "Open", R.drawable.ic_home_black_24dp, R.drawable.baseline_bookmark_border_black_48dp));
        listTempat.add(new Tempat("Smea Coffe", "Lampeuneurut", "Rating: 5.00", "Open", R.drawable.ic_home_black_24dp, R.drawable.baseline_bookmark_black_48dp));
        listTempat.add(new Tempat("Solong Coffe 1", "Lampeuneurut", "Rating: 5.00", "Open", R.drawable.ic_home_black_24dp, R.drawable.baseline_bookmark_black_48dp));
        listTempat.add(new Tempat("Smea Coffe 2", "Lampeuneurut", "Rating: 5.00", "Open", R.drawable.ic_home_black_24dp, R.drawable.baseline_bookmark_border_black_48dp));
        listTempat.add(new Tempat("Solong Coffe", "Lampeuneurut", "Rating: 5.00", "Open", R.drawable.ic_home_black_24dp, R.drawable.baseline_bookmark_border_black_48dp));
        listTempat.add(new Tempat("Smea Coffe", "Lampeuneurut", "Rating: 5.00", "Open", R.drawable.ic_home_black_24dp, R.drawable.baseline_bookmark_black_48dp));
        listTempat.add(new Tempat("Solong Coffe 1", "Lampeuneurut", "Rating: 5.00", "Open", R.drawable.ic_home_black_24dp, R.drawable.baseline_bookmark_black_48dp));
        listTempat.add(new Tempat("Smea Coffe 2", "Lampeuneurut", "Rating: 5.00", "Open", R.drawable.ic_home_black_24dp, R.drawable.baseline_bookmark_border_black_48dp));

    }
}
