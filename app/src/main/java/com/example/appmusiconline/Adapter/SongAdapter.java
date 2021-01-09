package com.example.appmusiconline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
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

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder>{

    Context context ;
    ArrayList<SongAndArtist> arr_SongAndArtist ;


    public SongAdapter(Context context, ArrayList<SongAndArtist> arr_SongAndArtist) {
        this.context = context;
        this.arr_SongAndArtist = arr_SongAndArtist;
    }

    @NonNull
    @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_song , parent , false );
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        SongAndArtist songAndArtist = arr_SongAndArtist.get(position);
        holder.txtArtistSong.setText(songAndArtist.getArtistName());
        holder.txtNameSong.setText(songAndArtist.getSongTitle());
        Picasso.with(context).load(songAndArtist.getSongHinh()).into(holder.imgSong);
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
                for(int i=0; i<arr_SongAndArtist.size();i++)
                {
                    PersonalSong song1 = new PersonalSong(arr_SongAndArtist.get(i).getSongTitle(), arr_SongAndArtist.get(i).getArtistName(),
                            arr_SongAndArtist.get(i).getSongHinh(), "¯\\_( ͡° ͜ʖ ͡°)_/¯", arr_SongAndArtist.get(i).getSongLink());
                    SongAndArtist song = arr_SongAndArtist.get(i);
                    bundle.putSerializable("song"+i, song1);
                }
                //dong goi size
                bundle.putInt("darkwa1",arr_SongAndArtist.size());
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
        return arr_SongAndArtist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgSong ;
        TextView txtNameSong ;
        TextView txtArtistSong ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSong = itemView.findViewById(R.id.imgSong);
            txtNameSong = itemView.findViewById(R.id.txtSongName) ;
            txtArtistSong = itemView.findViewById(R.id.txtSongArtist);

        }
    }
}
