package com.example.appmusiconline.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmusiconline.Adapter.SearchAdapter;
import com.example.appmusiconline.Model.SongAndArtist;
import com.example.appmusiconline.R;
import com.example.appmusiconline.Service.APIService;
import com.example.appmusiconline.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Timkiem extends Fragment {
    View view ;
    RecyclerView recyclerViewSearch ;
    TextView tvKhongcodulieu ;
    Toolbar toolbar ;
    androidx.appcompat.widget.SearchView searchView;
    SearchAdapter searchAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_timkiem , container , false);
        
        mapping();

        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                searchTuKhoaBaiHat(query);
                Log.d("BBB",query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return view ;
    }

    private void mapping() {

        recyclerViewSearch = (RecyclerView) view.findViewById(R.id.recycleViewSearch) ;
        tvKhongcodulieu = (TextView) view.findViewById(R.id.textViewKhongCoDuLieu);
//        toolbar = view.findViewById(R.id.toolbarSearch);
        searchView = view.findViewById(R.id.searchview);

    }
    private void searchTuKhoaBaiHat (String tukhoa) {
        DataService dataService = APIService.getService();
        Call<List<SongAndArtist>> callback = dataService.getSongXX(tukhoa);
        Log.d("BBB",callback.toString());
        callback.enqueue(new Callback<List<SongAndArtist>>() {
            @Override
            public void onResponse(Call<List<SongAndArtist>> call, Response<List<SongAndArtist>> response) {

                Log.d("BBB" , "sucess") ;
                ArrayList<SongAndArtist> song_arr = (ArrayList<SongAndArtist>) response.body();
                if (song_arr.size() > 0 ){
                    searchAdapter = new SearchAdapter(getActivity() , song_arr);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerViewSearch.setLayoutManager(linearLayoutManager);
                    recyclerViewSearch.setAdapter(searchAdapter);
                    tvKhongcodulieu.setVisibility(View.GONE);
                    recyclerViewSearch.setVisibility(View.VISIBLE);
                }
                else {
                    tvKhongcodulieu.setVisibility(View.VISIBLE);
                    recyclerViewSearch.setVisibility(View.GONE);

                }

            }

            @Override
            public void onFailure(Call<List<SongAndArtist>> call, Throwable t) {
                    Log.d("BBB" , "failed") ;
            }
        });
    }
}
