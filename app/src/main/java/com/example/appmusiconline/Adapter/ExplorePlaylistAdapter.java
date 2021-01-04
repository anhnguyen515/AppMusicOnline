package com.example.appmusiconline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.appmusiconline.Model.Album;
import com.example.appmusiconline.Model.AlbumAndArtist;
import com.example.appmusiconline.Model.PersonalPlaylist;
import com.example.appmusiconline.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExplorePlaylistAdapter extends PagerAdapter {
    Context context ;
    ArrayList<PersonalPlaylist> arr_personal_playlist ;

    public ExplorePlaylistAdapter(Context context, ArrayList<PersonalPlaylist> arr_personal_playlist) {
        this.context = context;
        this.arr_personal_playlist = arr_personal_playlist;
    }

    @Override
    public int getCount() {
        return arr_personal_playlist.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_playlist_explore, null);
        ImageView imgExplorePlaylist = (ImageView) view.findViewById(R.id.imgExploerPlaylist);


        // Picasso.with(context).load(arr_personal_playlist).get(position).getAlbumHinh()).into(imgAlbum);
        Picasso.with(context).load(arr_personal_playlist.get(position).getImagePlaylist() ).into(imgExplorePlaylist);


        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
