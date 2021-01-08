package com.example.appmusiconline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appmusiconline.Activity.MainActivity;
import com.example.appmusiconline.Activity.MusicActivity;
import com.example.appmusiconline.Model.PersonalSong;
import com.example.appmusiconline.Model.SongAndArtist;
import com.example.appmusiconline.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

public class SongAdapterGridView extends BaseAdapter {
    Context context;
    int layout ;
    ArrayList<SongAndArtist> arr_personal_song ;

    public SongAdapterGridView(Context context, int layout, ArrayList<SongAndArtist> arr_personal_song) {
        this.context = context;
        this.layout = layout;
        this.arr_personal_song = arr_personal_song;
    }

    @Override
    public int getCount() {
        return arr_personal_song.size();
    }

    @Override
    public Object getItem(int position) {
        return arr_personal_song.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class ViewHolder {
        ImageView imgSong ;
        TextView txtName , txtArtist  ;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder ;

        holder = new ViewHolder() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        // convertView sẽ chứa layout nào sẽ hiển thị mỗi dòng
        convertView = inflater.inflate(layout,null);

        ImageView imgSong  = (ImageView) convertView.findViewById(R.id.imgPersonalSong) ;
        TextView txtName = (TextView) convertView.findViewById(R.id.txtPersonalSongName) ;
        TextView txtArtist = (TextView) convertView.findViewById(R.id.txtPersonalSongArtist) ;

        txtName.setSelected(true);
        txtArtist.setSelected(true);
        SongAndArtist object = arr_personal_song.get(position);


        Picasso.with(context).load(object.getSongHinh()).into(imgSong);
        txtName.setText(object.getSongTitle());
        txtArtist.setText(object.getArtistName());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.mediaPlayer.release();
                MainActivity.initMediaPlayer();

                // man hinh play nhac
                Intent intent = new Intent(context, MusicActivity.class) ;
                Bundle bundle = new Bundle();
                //dong goi lan luot tung song
                for(int i=0; i<arr_personal_song.size();i++)
                {
                    PersonalSong song1 = new PersonalSong(arr_personal_song.get(i).getSongTitle(), arr_personal_song.get(i).getArtistName(),
                            arr_personal_song.get(i).getSongHinh(), "¯\\_( ͡° ͜ʖ ͡°)_/¯", arr_personal_song.get(i).getSongLink());
                    SongAndArtist song = arr_personal_song.get(i);
                    bundle.putSerializable("song"+i, song1);
                }
                //dong goi size
                bundle.putInt("darkwa1",arr_personal_song.size());
                //dong goi potition
                bundle.putInt("darkwa2",position);

                //  bundle.put
                intent.putExtra("darkwa", bundle);
                context.startActivity(intent);
            }
        });
//
//        Animation animation = AnimationUtils.loadAnimation(context,R.anim.animation_personal_song);
//        convertView.startAnimation(animation);

        return convertView;
    }
}
