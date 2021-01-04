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

import com.example.appmusiconline.Adapter.PersonalAlbumAdapter;
import com.example.appmusiconline.Adapter.PersonalPlaylistAdapter;
import com.example.appmusiconline.Adapter.PersonalPlaylistAdapterGridView;
import com.example.appmusiconline.Model.PersonalAlbum;
import com.example.appmusiconline.Model.PersonalPlaylist;
import com.example.appmusiconline.R;
import com.example.appmusiconline.Service.APIService;
import com.example.appmusiconline.Service.DataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_personal_playlist extends Fragment {

    View view ;
    ListView lvPersonalPlaylist ;
    PersonalPlaylistAdapter adapter ;
    GridView gridView ;
    PersonalPlaylistAdapterGridView adapterGridView ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personal_playlist , container , false );
        mapping();
        getPersonalPlaylist();
        Fragment_personal.imgPersonalSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (lvPersonalPlaylist.getVisibility() == View.VISIBLE) {
                    lvPersonalPlaylist.setVisibility(View.GONE);
                    gridView.setVisibility(View.VISIBLE);
                }
                else {
                    lvPersonalPlaylist.setVisibility(View.VISIBLE);
                    gridView.setVisibility(View.GONE);
                }
                Toast.makeText(getActivity(), "PLAYLIST", Toast.LENGTH_SHORT).show();
            }
        });
        return view ;
    }

    private void getPersonalPlaylist() {
        DataService dataService = APIService.getService();
        Call<List<PersonalPlaylist>> callback = dataService.getPersonalPlaylist();

        callback.enqueue(new Callback<List<PersonalPlaylist>>() {
            @Override
            public void onResponse(Call<List<PersonalPlaylist>> call, Response<List<PersonalPlaylist>> response) {

                ArrayList<PersonalPlaylist> playlistArrayList = (ArrayList<PersonalPlaylist>) response.body();
                Collections.shuffle(playlistArrayList);
                adapterGridView = new PersonalPlaylistAdapterGridView(getActivity() , R.layout.dong_playlist_personal_gridview , playlistArrayList);
                gridView.setAdapter(adapterGridView);
                Toast.makeText(getActivity(), "PLAYLIST SUCCESS", Toast.LENGTH_SHORT).show();
                adapter = new PersonalPlaylistAdapter(getActivity(), R.layout.dong_playlist_personal, playlistArrayList);
                lvPersonalPlaylist.setAdapter(adapter);
                Log.d("bbb", "Sucesss!");


            }

            @Override
            public void onFailure(Call<List<PersonalPlaylist>> call, Throwable t) {
                Log.d("bbb", "Loi !!!");

            }


        });
    }

    private void mapping() {
        lvPersonalPlaylist = view.findViewById(R.id.lvPersonalPlaylist);
        gridView = view.findViewById(R.id.personalGridView);
    }
}
