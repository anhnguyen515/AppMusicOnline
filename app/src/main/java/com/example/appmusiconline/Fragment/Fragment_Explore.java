package com.example.appmusiconline.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.appmusiconline.Adapter.ExplorePlaylistAdapter;
import com.example.appmusiconline.Adapter.ExploreSongAdapter;
import com.example.appmusiconline.Adapter.PersonalPlaylistAdapter;
import com.example.appmusiconline.Adapter.PersonalPlaylistAdapterGridView;
import com.example.appmusiconline.Adapter.PersonalSongAdapter;
import com.example.appmusiconline.Adapter.PersonalSongAdapterGridView;
import com.example.appmusiconline.Adapter.SongAdapterGridView;
import com.example.appmusiconline.Model.PersonalPlaylist;
import com.example.appmusiconline.Model.PersonalSong;
import com.example.appmusiconline.R;
import com.example.appmusiconline.Service.APIService;
import com.example.appmusiconline.Service.DataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Explore extends Fragment {
    View view ;
    ListView lvExploreSong ;
    ViewPager viewPagerExplore ;
    ImageView imgNewestSong , imgSeeall ;
    ExploreSongAdapter adapter  ;
    ArrayList<PersonalSong> arr_per_song ;
    PersonalSongAdapterGridView gridViewAdapter ;
    GridView gridView ;


    Runnable runable;
    Handler handler ;
    int currentItem ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_explore , container , false );
        mapping();
        getExplorerPlaylist();
        getExplorerSong();

        imgSeeall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lvExploreSong.getVisibility() == View.VISIBLE) {
                    lvExploreSong.setVisibility(View.GONE);
                    gridView.setVisibility(View.VISIBLE);
                }
                else {
                    lvExploreSong.setVisibility(View.VISIBLE);
                    gridView.setVisibility(View.GONE);
                }
            }
        });
        return view ;
    }
    private void  getExplorerPlaylist() {
        DataService dataService = APIService.getService();
        Call<List<PersonalPlaylist>> callback = dataService. getExplorerPlaylist();

        callback.enqueue(new Callback<List<PersonalPlaylist>>() {
            @Override
            public void onResponse(Call<List<PersonalPlaylist>> call, Response<List<PersonalPlaylist>> response) {

                ArrayList<PersonalPlaylist> playlistArrayList = (ArrayList<PersonalPlaylist>) response.body();
                Collections.shuffle(playlistArrayList);

                ExplorePlaylistAdapter adapter = new  ExplorePlaylistAdapter(getActivity(), playlistArrayList);
                viewPagerExplore.setAdapter(adapter);
                handler = new Handler();
                runable = new Runnable() {
                    @Override
                    public void run() {
                        currentItem = viewPagerExplore.getCurrentItem();
                        currentItem ++ ;
                        // viewPager.getAdapter().getCount()
                        if ( currentItem >= viewPagerExplore.getAdapter().getCount()){
                            currentItem = 0 ;
                        }
                        viewPagerExplore.setCurrentItem(currentItem , true );
                        handler.postDelayed(runable , 4500);
                    }
                };
                handler.postDelayed(runable,4500);
                Log.d("bbb", "Sucesss!");

            }





            @Override
            public void onFailure(Call<List<PersonalPlaylist>> call, Throwable t) {
                Log.d("bbb", "Loi !!!");

            }


        });
    }

        private void getExplorerSong() {
            DataService dataService = APIService.getService();
            Call<List<PersonalSong>> callback = dataService.getExplorerSong();

            callback.enqueue(new Callback<List<PersonalSong>>() {
                @Override
                public void onResponse(Call<List<PersonalSong>> call, Response<List<PersonalSong>> response) {

                    ArrayList<PersonalSong> songArrayList = (ArrayList<PersonalSong>) response.body();
                    Collections.shuffle(songArrayList);
                    gridViewAdapter = new PersonalSongAdapterGridView(getActivity() , R.layout.dong_song_personal_gridview , songArrayList);
                    gridView.setAdapter(gridViewAdapter);
                    adapter = new ExploreSongAdapter(getActivity(), R.layout.dong_song_explore, songArrayList);
                    lvExploreSong.setAdapter(adapter);
                    Log.d("bbb", "Sucesss!");


                }

                @Override
                public void onFailure(Call<List<PersonalSong>> call, Throwable t) {
                    Log.d("bbb", "Loi !!!");

                }


            });
        }




    private void mapping() {
        lvExploreSong = (ListView) view.findViewById(R.id.lvExploreSong) ;
        viewPagerExplore = view.findViewById(R.id.viewPagerExplore) ;
        imgNewestSong = view.findViewById(R.id.imgExploreNewestSong) ;
        imgSeeall = view.findViewById(R.id.imgExploreSeeall) ;
        gridView = view.findViewById(R.id.exploreGridView);
    }
}
