package com.example.appmusiconline.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.example.appmusiconline.Adapter.PersonalSongAdapter;
import com.example.appmusiconline.Adapter.PersonalSongAdapterGridView;
import com.example.appmusiconline.Adapter.PersonalViewPagerAdapter;
import com.example.appmusiconline.Adapter.SongAdapter;
import com.example.appmusiconline.Model.PersonalSong;
import com.example.appmusiconline.Model.SongAndArtist;
import com.example.appmusiconline.R;
import com.example.appmusiconline.Service.APIService;
import com.example.appmusiconline.Service.DataService;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_personal extends Fragment {
    View view;
    ViewPager viewPager ;
    TabLayout tabLayout ;
    static public ImageView imgPersonalSort ;






    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personal, container, false);
        mapping();
        init();
       // getPersonalSong() ;





        return view;
    }



    private void init() {
        final PersonalViewPagerAdapter adapter = new PersonalViewPagerAdapter(getFragmentManager()) ;
        String songs = getResources().getString(R.string.songs) ;
        String playlists = getResources().getString(R.string.playlists) ;
        String albums = getResources().getString(R.string.albums) ;

//        adapter.addFragment(new Fragment_personal_song() , "Songs");
//        adapter.addFragment(new Fragment_personal_playlist() , "Playlists ");
//        adapter.addFragment(new Fragment_personal_album() , "Albums");
        adapter.addFragment(new Fragment_personal_song() , songs);
        adapter.addFragment(new Fragment_personal_playlist() , playlists);
        adapter.addFragment(new Fragment_personal_album() , albums);

        adapter.notifyDataSetChanged();
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

       // tabLayout.setTabTextColors(getResources().getColor(R.color.black),getResources().getColor(R.color.white));


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               // Toast.makeText(getActivity(), tab.getPosition() + " ", Toast.LENGTH_SHORT).show();
            //    tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.black));

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void mapping() {
        tabLayout = view.findViewById(R.id.myTabLayout) ;
        viewPager = view.findViewById(R.id.myViewPager);
        imgPersonalSort = view.findViewById(R.id.imgPersonalSort);
     //   lvPersonalSong = (ListView) view.findViewById(R.id.lvPersonalSong) ;
    }

}
