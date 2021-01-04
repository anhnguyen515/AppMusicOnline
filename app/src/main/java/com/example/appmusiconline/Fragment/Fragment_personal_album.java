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
import com.example.appmusiconline.Adapter.PersonalAlbumAdapterGridView;
import com.example.appmusiconline.Adapter.PersonalSongAdapter;
import com.example.appmusiconline.Model.PersonalAlbum;
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

public class Fragment_personal_album extends Fragment {

    View view ;
    ListView lvPersonalAlbum ;
    GridView gridView;
    PersonalAlbumAdapter adapter;
    PersonalAlbumAdapterGridView gridviewAdapter ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personal_album , container , false );
        mapping();
        getPersonalAlbum();
        Fragment_personal.imgPersonalSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lvPersonalAlbum.getVisibility() == View.VISIBLE) {
                    lvPersonalAlbum.setVisibility(View.GONE);
                    gridView.setVisibility(View.VISIBLE);
                }
                else{
                    lvPersonalAlbum.setVisibility(View.VISIBLE);
                    gridView.setVisibility(View.GONE);
                }
                Toast.makeText(getActivity(), "ALBUM", Toast.LENGTH_SHORT).show();
            }
        });
        return view ;
    }

    private void mapping() {

        lvPersonalAlbum = view.findViewById(R.id.lvPersonalAlbum);
        gridView = view.findViewById(R.id.personalGridView);
    }

    private void getPersonalAlbum() {
        DataService dataService = APIService.getService();
        Call<List<PersonalAlbum>> callback = dataService.getPersonalAlbum();

        callback.enqueue(new Callback<List<PersonalAlbum>>() {
            @Override
            public void onResponse(Call<List<PersonalAlbum>> call, Response<List<PersonalAlbum>> response) {

                ArrayList<PersonalAlbum> albumArrayList = (ArrayList<PersonalAlbum>) response.body();
                Collections.shuffle(albumArrayList);
                gridviewAdapter = new PersonalAlbumAdapterGridView(getActivity() , R.layout.dong_album_personal_gridview , albumArrayList);
                gridView.setAdapter(gridviewAdapter);
                adapter = new PersonalAlbumAdapter(getActivity(), R.layout.dong_album_personal, albumArrayList);
                lvPersonalAlbum.setAdapter(adapter);
                Log.d("bbb", "Sucesss!");


            }

            @Override
            public void onFailure(Call<List<PersonalAlbum>> call, Throwable t) {
                Log.d("bbb", "Loi !!!");

            }


        });
    }
}
