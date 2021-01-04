package com.example.appmusiconline.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusiconline.Model.SongAndArtist;
import com.example.appmusiconline.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchAdapter extends  RecyclerView.Adapter<SearchAdapter.ViewHolder>{

    Context context ;
    ArrayList<SongAndArtist> song_arr ;

    public SearchAdapter(Context context, ArrayList<SongAndArtist> song_arr) {
        this.context = context;
        this.song_arr = song_arr;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dong_search, parent ,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                SongAndArtist object = song_arr.get(position);
                holder.txtTenbaihat.setText(object.getSongTitle());
                holder.txtCasi.setText(object.getArtistName());
//                holder.txtTime.setText(object.get);
        Picasso.with(context).load(object.getSongHinh()).into(holder.imgbaihat);
    }

    @Override
    public int getItemCount() {
        return song_arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenbaihat , txtCasi ,txtTime;
        ImageView imgbaihat ,imgluotthich;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenbaihat = itemView.findViewById(R.id.textViewSearchTenBaiHat);
            txtCasi = itemView.findViewById(R.id.textViewSearchTenCasi) ;
            imgbaihat = itemView.findViewById(R.id.imageSearchBaiHat);
//            imgluotthich = itemView.findViewById(R.id.imageSearchluotthich);
//
//            imgluotthich.setTag(R.drawable.heart_empty);
            txtTime = itemView.findViewById(R.id.textViewSearchTime);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            // CLICK vao hinh trai tim roi` chuyen mau
//            imgluotthich.setOnClickListener(new View.OnClickListener() {
//                Drawable myDrawable1 = context.getDrawable(R.drawable.heart_empty);
//                Drawable myDrawable2 = context.getDrawable(R.drawable.heart_fill);
//                final Bitmap myLogo1 = ((BitmapDrawable) myDrawable1).getBitmap();
//                final Bitmap myLogo2 = ((BitmapDrawable) myDrawable2).getBitmap();
//                @Override
//                public void onClick(View v) {
//                    final Bitmap bmap = ((BitmapDrawable)imgluotthich.getDrawable()).getBitmap();
//
//
//                    if(bmap.sameAs(myLogo1))
//                    {
//                        imgluotthich.setImageResource(R.drawable.heart_fill);
//                    }
//                    else if (bmap.sameAs(myLogo2))
//                    {
//                        imgluotthich.setImageResource(R.drawable.heart_empty);
//                    }
//                }
//            });
        }
    }
}
