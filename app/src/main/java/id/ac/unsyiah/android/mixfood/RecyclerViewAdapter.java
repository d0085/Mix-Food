package id.ac.unsyiah.android.mixfood;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by FMA on 13/05/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Tempat> mData;

    public RecyclerViewAdapter(Context mContext, List<Tempat> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_restauran,parent,false);
        MyViewHolder vHolder =  new MyViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tv_nama.setText(mData.get(position).getNama());
        holder.tv_alamat.setText(mData.get(position).getAlamat());
        holder.tv_rating.setText(mData.get(position).getRating());
        holder.tv_status.setText(mData.get(position).getStatus());
        holder.img_foto.setImageResource(mData.get(position).getFoto());
        holder.img_favorit.setImageResource(mData.get(position).getFavorit());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_nama;
        private TextView tv_alamat;
        private TextView tv_rating;
        private TextView tv_status;
        private ImageView img_foto;
        private ImageView img_favorit;

        public MyViewHolder (View itemView){
            super(itemView);

            tv_nama = (TextView) itemView.findViewById(R.id.textRestauran);
            tv_alamat = (TextView) itemView.findViewById(R.id.textAlamat);
            tv_rating = (TextView) itemView.findViewById(R.id.textRating);
            tv_status = (TextView) itemView.findViewById(R.id.textStatus);
            img_foto = (ImageView) itemView.findViewById(R.id.imageRestauran);
            img_favorit = (ImageView) itemView.findViewById(R.id.imageBookmark);
        }
    }
}
