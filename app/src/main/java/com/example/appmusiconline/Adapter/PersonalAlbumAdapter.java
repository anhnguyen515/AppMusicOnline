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
import com.example.appmusiconline.Model.PersonalSong;
import com.example.appmusiconline.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PersonalAlbumAdapter extends BaseAdapter {
    Context context;
    int layout ;
    ArrayList<PersonalAlbum> arr_personal_album ;

    public PersonalAlbumAdapter(Context context, int layout, ArrayList<PersonalAlbum> arr_personal_album) {
        this.context = context;
        this.layout = layout;
        this.arr_personal_album = arr_personal_album;
    }

    @Override
    public int getCount() {
        return arr_personal_album.size();
    }

    @Override
    public Object getItem(int position) {
        return arr_personal_album.get(position);
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

        ImageView imgSong  = (ImageView) convertView.findViewById(R.id.imgPersonalAlbum) ;
        TextView txtName = (TextView) convertView.findViewById(R.id.txtPersonalAlbumName) ;
        TextView txtArtist = (TextView) convertView.findViewById(R.id.txtPersonalAlbumArtist) ;
        TextView txtTime = (TextView) convertView.findViewById(R.id.txtPersonalAlbumTime);

        PersonalAlbum object = arr_personal_album.get(position);


        Picasso.with(context).load(object.getImageAlbum()).into(imgSong);
        txtName.setText(object.getNameAlbum());
        txtArtist.setText(object.getArtistAlbum());
        txtTime.setText(object.getTimeAlbum());


        Animation animation = AnimationUtils.loadAnimation(context,R.anim.animation_personal_song);
        convertView.startAnimation(animation);

        return convertView;
    }
}
