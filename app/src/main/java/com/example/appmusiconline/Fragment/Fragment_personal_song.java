package com.example.appmusiconline.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.appmusiconline.Adapter.PersonalSongAdapter;
import com.example.appmusiconline.Adapter.PersonalSongAdapterGridView;
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

public class Fragment_personal_song extends Fragment {
    View view ;
    ListView lvPersonalSong ;
    GridView gridView ;
    PersonalSongAdapter adapter ;
    PersonalSongAdapterGridView adapterGridView ;
    public  static  int i = 0 ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personal_song , container , false ) ;
        mapping () ;
        getPersonalSong ();
        Fragment_personal.imgPersonalSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (lvPersonalSong.getVisibility() == View.VISIBLE) {
                    lvPersonalSong.setVisibility(View.GONE);
                    gridView.setVisibility(View.VISIBLE);
                    Fragment_personal.imgPersonalSort.setImageResource(R.drawable.grid_white);
                }
                else {
                    lvPersonalSong.setVisibility(View.VISIBLE);
                    gridView.setVisibility(View.GONE);
                    Fragment_personal.imgPersonalSort.setImageResource(R.drawable.grid);
                }
            }
        });
        return view ;
    }
    private void getPersonalSong() {
        DataService dataService = APIService.getService();
        Call<List<PersonalSong>> callback = dataService.getPersonalSong();

        callback.enqueue(new Callback<List<PersonalSong>>() {
            @Override
            public void onResponse(Call<List<PersonalSong>> call, Response<List<PersonalSong>> response) {

                ArrayList<PersonalSong> songArrayList = (ArrayList<PersonalSong>) response.body();
                Collections.shuffle(songArrayList);
                adapterGridView = new PersonalSongAdapterGridView(getActivity() , R.layout.dong_song_personal_gridview , songArrayList);
                gridView.setAdapter(adapterGridView);
                adapter = new PersonalSongAdapter(getActivity(), R.layout.dong_song_personal, songArrayList);
                lvPersonalSong.setAdapter(adapter);
                Log.d("bbb", "Sucesss!");


            }

            @Override
            public void onFailure(Call<List<PersonalSong>> call, Throwable t) {
                Log.d("bbb", "Loi !!!");

            }


        });
    }

    private void mapping() {
       lvPersonalSong = (ListView) view.findViewById(R.id.lvPersonalSong) ;
       gridView = (GridView) view.findViewById(R.id.personalGridView);
    }
}
