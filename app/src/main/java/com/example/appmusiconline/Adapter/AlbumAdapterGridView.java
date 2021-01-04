package com.example.appmusiconline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.appmusiconline.Model.AlbumAndArtist;
import com.example.appmusiconline.Model.PersonalAlbum;
import com.example.appmusiconline.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapterGridView extends BaseAdapter {
    Context context ;
    int layout ;
    ArrayList<AlbumAndArtist> arr_album_adapter ;

    public AlbumAdapterGridView(Context context, int layout, ArrayList<AlbumAndArtist> arr_album_adapter) {
        this.context = context;
        this.layout = layout;
        this.arr_album_adapter = arr_album_adapter;
    }

    @Override
    public int getCount() {
        return arr_album_adapter.size();
    }

    @Override
    public Object getItem(int position) {
        return arr_album_adapter.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layout, null);
        ImageView imgAlbum = (ImageView) view.findViewById(R.id.imgAlbum);
        TextView txtAlbumTitle = (TextView) view.findViewById(R.id.txtAlbumTitle);
        TextView txtAlbumArtist = (TextView) view.findViewById(R.id.txtAlbumArtist);
        txtAlbumTitle.setSelected(true);
        txtAlbumArtist.setSelected(true);

        Picasso.with(context).load(arr_album_adapter.get(position).getAlbumHinh()).into(imgAlbum);
        txtAlbumArtist.setText(arr_album_adapter.get(position).getArtistName());
        txtAlbumTitle.setText(arr_album_adapter.get(position).getAlbumTitle());
        return convertView ;
    }


//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return view == object;
//    }

//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.dong_album, null);
//        ImageView imgAlbum = (ImageView) view.findViewById(R.id.imgAlbum);
//        TextView txtAlbumTitle = (TextView) view.findViewById(R.id.txtAlbumTitle);
//        TextView txtAlbumArtist = (TextView) view.findViewById(R.id.txtAlbumArtist);
//
//        Picasso.with(context).load(arr_album_adapter.get(position).getAlbumHinh()).into(imgAlbum);
//        txtAlbumArtist.setText(arr_album_adapter.get(position).getArtistName());
//        txtAlbumTitle.setText(arr_album_adapter.get(position).getAlbumTitle());
//
//        container.addView(view);
//        return view;
//    }
//
//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        container.removeView((View) object);
//    }
}