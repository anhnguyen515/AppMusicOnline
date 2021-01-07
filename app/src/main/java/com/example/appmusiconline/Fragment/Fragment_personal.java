package com.example.appmusiconline.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.appmusiconline.Adapter.PersonalViewPagerAdapter;
import com.example.appmusiconline.R;
import com.google.android.material.tabs.TabLayout;

public class Fragment_personal extends Fragment {
    View view;
    ViewPager viewPager ;
    TabLayout tabLayout ;
    public static ImageView imgPersonalSort, imgPersonalRefresh;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personal, container, false);
        mapping();
        init();
        return view;
    }

    private void init() {
        assert getFragmentManager() != null;
        final PersonalViewPagerAdapter adapter = new PersonalViewPagerAdapter(getFragmentManager()) ;
        String songs = getResources().getString(R.string.songs) ;
        String playlists = getResources().getString(R.string.playlists) ;
        String albums = getResources().getString(R.string.albums) ;

        adapter.addFragment(new Fragment_personal_song() , songs);
        adapter.addFragment(new Fragment_personal_playlist() , playlists);
        adapter.addFragment(new Fragment_personal_album() , albums);

        adapter.notifyDataSetChanged();
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

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
        imgPersonalRefresh = view.findViewById(R.id.imgPersonalRefresh);
    }

}
