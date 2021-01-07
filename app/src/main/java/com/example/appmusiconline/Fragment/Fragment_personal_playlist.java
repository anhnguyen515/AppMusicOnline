package com.example.appmusiconline.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appmusiconline.Adapter.PersonalPlaylistAdapter;
import com.example.appmusiconline.Adapter.PersonalPlaylistAdapterGridView;
import com.example.appmusiconline.DataBase.PlayListOperations;
import com.example.appmusiconline.Model.PersonalSong;
import com.example.appmusiconline.R;

import java.util.ArrayList;

public class Fragment_personal_playlist extends Fragment {

    View view ;
    ListView lvPersonalPlaylist ;
    PersonalPlaylistAdapter adapter ;
    GridView gridView ;
    PersonalPlaylistAdapterGridView adapterGridView ;
    private PlayListOperations playListOperations;
    public ArrayList<PersonalSong> playlistArrayList;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        playListOperations = new PlayListOperations(context);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personal_playlist , container , false );

        mapping();
        getPersonalPlaylist();

        Fragment_personal.imgPersonalRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPersonalPlaylist();
            }
        });
        return view ;
    }

    private void getPersonalPlaylist(){
        playlistArrayList = new ArrayList<>();
        playlistArrayList = (ArrayList<PersonalSong>) playListOperations.getAllPlayList();
        adapterGridView = new PersonalPlaylistAdapterGridView(getActivity() , R.layout.dong_playlist_personal_gridview , playlistArrayList);
        gridView.setAdapter(adapterGridView);
        adapter = new PersonalPlaylistAdapter(getActivity(), R.layout.dong_playlist_personal, playlistArrayList);
        lvPersonalPlaylist.setAdapter(adapter);
    }

    private void mapping() {
        lvPersonalPlaylist = view.findViewById(R.id.lvPersonalPlaylist);
        gridView = view.findViewById(R.id.personalGridView);
    }
}
