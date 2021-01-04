package com.example.appmusiconline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appmusiconline.Model.PersonalAlbum;
import com.example.appmusiconline.Model.PersonalPlaylist;
import com.example.appmusiconline.Model.PersonalSong;
import com.example.appmusiconline.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PersonalPlaylistAdapterGridView extends BaseAdapter {
    Context context;
    int layout ;
    ArrayList<PersonalPlaylist> arr_personal_playlist ;

    public PersonalPlaylistAdapterGridView(Context context, int layout, ArrayList<PersonalPlaylist> arr_personal_playlist) {
        this.context = context;
        this.layout = layout;
        this.arr_personal_playlist = arr_personal_playlist;
    }

    @Override
    public int getCount() {
        return arr_personal_playlist.size();
    }

    @Override
    public Object getItem(int position) {
        return arr_personal_playlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class ViewHolder {
        ImageView imgSong ;
        TextView txtName , txtArtist ,txtTime  ;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder ;

        holder = new ViewHolder() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        // convertView sẽ chứa layout nào sẽ hiển thị mỗi dòng
        convertView = inflater.inflate(layout,null);

        ImageView imgSong  = (ImageView) convertView.findViewById(R.id.imgPersonalPlaylist) ;
        TextView txtName = (TextView) convertView.findViewById(R.id.txtPersonalPlaylistName) ;
        TextView txtArtist = (TextView) convertView.findViewById(R.id.txtPersonalPlaylistSum) ;
//        TextView txtTime = (TextView) convertView.findViewById(R.id.txtPersonalPlaylistTime);

        txtName.setSelected(true);
        txtArtist.setSelected(true);
        PersonalPlaylist object = arr_personal_playlist.get(position);


        Picasso.with(context).load(object.getImagePlaylist()).into(imgSong);
        txtName.setText(object.getNamePlaylist());
        txtArtist.setText(object.getSumPlayList());
//        txtTime.setText(object.getTimePlaylist());

//
//        Animation animation = AnimationUtils.loadAnimation(context,R.anim.animation_personal_song);
//        convertView.startAnimation(animation);

        return convertView;
    }
}
