package com.example.appmusiconline.Fragment;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.appmusiconline.Activity.LoginActivity;

import com.example.appmusiconline.Adapter.MainAdapter;

import com.example.appmusiconline.R;
import com.example.appmusiconline.Service.APIRetrofitClient;
import com.example.appmusiconline.Service.APIService;
import com.example.appmusiconline.Service.DataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;




public class Fragment_Album extends Fragment {
    View view;
    CircleIndicator circleIndicatorMain ;
    ViewPager viewPagerMain ;
    Fragment ft1 , ft2 ;
    ImageView btnSearch ;
    EditText edtSearch ;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album , container , false );
        mapping();

        ArrayList<String> arr_String = new ArrayList<>();
        String hottrends = getResources().getString(R.string.hottrend) ;
        String personal = getResources().getString(R.string.personal) ;
        String explore = getResources().getString(R.string.explore) ;
//        arr_String.add("Hot trends") ;
//        arr_String.add("Personal");
//        arr_String.add("Exploxer") ;
        arr_String.add(hottrends);
        arr_String.add(personal);
        arr_String.add(explore);
        MainAdapter mainAdapter = new MainAdapter(getActivity() , arr_String);
        viewPagerMain.setAdapter(mainAdapter);
        circleIndicatorMain.setViewPager(viewPagerMain);


        // add fragment into a fragment
            final FragmentManager fragmentManager = getChildFragmentManager() ;
            final Fragment fragmentHottrend = new Fragment_hottrend();
           // final Fragment color = new Fragment_Color();
          final  Fragment fragmentPersonal = new Fragment_personal();
          final Fragment fragmentExplore = new Fragment_Explore() ;

            final Fragment fragmentStart = new Fragment_getstart();
            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            final Fragment fragmentTimKiem = new Fragment_Timkiem();
            transaction.add(R.id.textxyz , fragmentHottrend) ;


            transaction.commit();

            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction transaction1 = fragmentManager.beginTransaction();
                    transaction1.replace(R.id.textxyz, fragmentTimKiem).commit();
                }
            });


        // event _ viewpager
        viewPagerMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0 ){
                    FragmentTransaction transaction1 = fragmentManager.beginTransaction();

                    transaction1.replace(R.id.textxyz, fragmentHottrend).commit();
                    fragmentManager.popBackStack();
                }
                else if (position == 1 ) {
                    FragmentTransaction transaction2 = fragmentManager.beginTransaction();

                    transaction2.replace(R.id.textxyz, fragmentPersonal).commit();
                    fragmentManager.popBackStack();
                }
                else if (position == 2) {
                    FragmentTransaction transaction3 = fragmentManager.beginTransaction();

                    transaction3.replace(R.id.textxyz, fragmentExplore).commit();
                    fragmentManager.popBackStack();

                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }



    private void mapping() {

        viewPagerMain = view.findViewById(R.id.viewPagerMain);
     //   recyclerViewMain = view.findViewById(R.id.recycleViewMain);
        circleIndicatorMain = view.findViewById(R.id.indicatorMain);
      //  ft1 = view.findViewById(R.id.fragmentHottrend);

        btnSearch = view.findViewById(R.id.btnSearch);
        edtSearch = view.findViewById(R.id.edtSearch);



    }



}

