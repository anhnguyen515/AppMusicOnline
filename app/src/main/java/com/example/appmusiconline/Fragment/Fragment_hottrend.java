package com.example.appmusiconline.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.appmusiconline.Activity.LoginActivity;
import com.example.appmusiconline.Adapter.AlbumAdapter;
import com.example.appmusiconline.Adapter.AlbumAdapterGridView;
import com.example.appmusiconline.Adapter.MainAdapter;
import com.example.appmusiconline.Adapter.SongAdapter;
import com.example.appmusiconline.Adapter.SongAdapterGridView;
import com.example.appmusiconline.Model.AlbumAndArtist;
import com.example.appmusiconline.Model.SongAndArtist;
import com.example.appmusiconline.R;
import com.example.appmusiconline.Service.APIService;
import com.example.appmusiconline.Service.DataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_hottrend extends Fragment {
    View view;
    static ViewPager viewPager ;
    AlbumAdapter albumAdapter ;
    Runnable runable;
    Handler handler ;
    int currentItem ;
    static RecyclerView recyclerViewSong ;
    SongAdapter songAdapter ;
    ImageView imgSeeTrendingAlbums , imgSeeTrendingSongs ;
    GridView gridViewAlbums , gridViewSongs ;
    AlbumAdapterGridView albumAdapterGridView ;
    SongAdapterGridView songAdapterGridView ;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hottrend, container , false );
        mapping();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if(position == 3) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
               // Toast.makeText(getContext(), "You chose " +state, Toast.LENGTH_SHORT).show();
            }
        });
        getData();
        getSong();

        imgSeeTrendingSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gridViewSongs.getVisibility() == View.GONE) {
                    gridViewSongs.setVisibility(View.VISIBLE);
                    recyclerViewSong.setVisibility(View.GONE);

                }
                else {
                    gridViewSongs.setVisibility(View.GONE);
                    recyclerViewSong.setVisibility(View.VISIBLE);
                }
            }
        });
        return view;
    }

    private void getSong() {
        DataService dataService = APIService.getService();
        Call<List<SongAndArtist>> callback = dataService.getSong() ;

        callback.enqueue(new Callback<List<SongAndArtist>>() {
            @Override
            public void onResponse(Call<List<SongAndArtist>> call, Response<List<SongAndArtist>> response) {

                ArrayList<SongAndArtist> songArrayList = (ArrayList<SongAndArtist>) response.body();
                Collections.shuffle(songArrayList);
                songAdapter = new SongAdapter(getActivity() ,songArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewSong.setLayoutManager(linearLayoutManager);
                recyclerViewSong.setAdapter(songAdapter);
                songAdapterGridView = new SongAdapterGridView(getActivity(),R.layout.dong_song_gridview , songArrayList) ;
                gridViewSongs.setAdapter(songAdapterGridView);
            }

            @Override
            public void onFailure(Call<List<SongAndArtist>> call, Throwable t) {
                Log.d("bbb","Loi !!!");

            }


        });
    }

    private void mapping() {
        viewPager = view.findViewById(R.id.viewPagerHotTrend);
        // circleIndicator = view.findViewById(R.id.indicatorHotTrend);
        recyclerViewSong = view.findViewById(R.id.recycleViewSong) ;
        imgSeeTrendingAlbums = view.findViewById(R.id.imgSeeTrendingAlbums) ;
        imgSeeTrendingSongs = view.findViewById(R.id.imgSeeTrendingSongs) ;
        gridViewAlbums = view.findViewById(R.id.gridViewTrendingAlbums) ;
        gridViewSongs = view.findViewById(R.id.gridViewTrendingSongs) ;


    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<List<AlbumAndArtist>> callback = dataService.getDataAlbum() ;

        callback.enqueue(new Callback<List<AlbumAndArtist>>() {
            @Override
            public void onResponse(Call<List<AlbumAndArtist>> call, Response<List<AlbumAndArtist>> response) {

                ArrayList<AlbumAndArtist> albums_arr = (ArrayList<AlbumAndArtist>) response.body();
                Log.d("aaa" , albums_arr.get(0).getAlbumHinh().toString());

                albumAdapter = new AlbumAdapter(getActivity() , albums_arr);
                viewPager.setAdapter(albumAdapter);
                //  circleIndicator.setViewPager(viewPager);
                albumAdapterGridView = new AlbumAdapterGridView(getActivity() , R.layout.dong_album_personal_gridview , albums_arr) ;
                gridViewAlbums.setAdapter(albumAdapterGridView) ;

                handler = new Handler();
                runable = new Runnable() {
                    @Override
                    public void run() {
                        currentItem = viewPager.getCurrentItem();
                        currentItem ++ ;
                        // viewPager.getAdapter().getCount()
                        if ( currentItem >= 2){
                            currentItem = 0 ;
                        }
                        viewPager.setCurrentItem(currentItem , true );
                        handler.postDelayed(runable , 4500);
                    }
                };
                handler.postDelayed(runable,4500);

            }

            @Override
            public void onFailure(Call<List<AlbumAndArtist>> call, Throwable t) {
                Log.d("aaa" , "Loi !!!");

            }
        });
    }
}
