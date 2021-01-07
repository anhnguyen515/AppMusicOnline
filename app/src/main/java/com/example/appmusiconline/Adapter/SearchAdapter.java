package com.example.appmusiconline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusiconline.Activity.MainActivity;
import com.example.appmusiconline.Activity.MusicActivity;
import com.example.appmusiconline.Model.PersonalSong;
import com.example.appmusiconline.Model.SongAndArtist;
import com.example.appmusiconline.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchAdapter extends  RecyclerView.Adapter<SearchAdapter.ViewHolder>{

    Context context ;
    ArrayList<SongAndArtist> song_arr ;
    ArrayList<PersonalSong> list_song;

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
    public void onBindViewHolder(@NonNull final ViewHolder holder, int i) {

        SongAndArtist object = song_arr.get(i);
        holder.txtTenbaihat.setText(object.getSongTitle());
        holder.txtCasi.setText(object.getArtistName());
//                holder.txtTime.setText(object.get);
        Picasso.with(context).load(object.getSongHinh()).into(holder.imgbaihat);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                MainActivity.mediaPlayer.release();
                MainActivity.initMediaPlayer();

                // man hinh play nhac
                Intent intent = new Intent(context, MusicActivity.class) ;
                Bundle bundle = new Bundle();
                //dong goi lan luot tung song
                for(int i=0; i<song_arr.size();i++)
                {
                    PersonalSong song1 = new PersonalSong(song_arr.get(i).getSongTitle(), song_arr.get(i).getArtistName(),
                            song_arr.get(i).getSongHinh(), "¯\\_( ͡° ͜ʖ ͡°)_/¯", song_arr.get(i).getSongLink());
                    SongAndArtist song = song_arr.get(i);
                    bundle.putSerializable("song"+i, song1);
                }
                //dong goi size
                bundle.putInt("darkwa1",song_arr.size());
                //dong goi potition
                bundle.putInt("darkwa2",position);

                //  bundle.put
                intent.putExtra("darkwa", bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return song_arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenbaihat , txtCasi ,txtTime;
        ImageView imgbaihat ,imgluotthich;
        int position;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenbaihat = itemView.findViewById(R.id.textViewSearchTenBaiHat);
            txtCasi = itemView.findViewById(R.id.textViewSearchTenCasi) ;
            imgbaihat = itemView.findViewById(R.id.imageSearchBaiHat);
            txtTime = itemView.findViewById(R.id.textViewSearchTime);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
