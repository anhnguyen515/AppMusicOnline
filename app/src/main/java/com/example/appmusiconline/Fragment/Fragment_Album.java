package com.example.appmusiconline.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.appmusiconline.Activity.LoginActivity;
import com.example.appmusiconline.Adapter.MainAdapter;
import com.example.appmusiconline.R;
import com.ivorcho.snowfallview.SnowfallView;

import java.util.ArrayList;
import java.util.Collections;

import me.relex.circleindicator.CircleIndicator;
import pl.droidsonroids.gif.GifImageView;


public class Fragment_Album extends Fragment {
    View view;
    CircleIndicator circleIndicatorMain ;
    ViewPager viewPagerMain ;
    Fragment ft1 , ft2 ;
    ImageView btnSearch , imgViewAvatar;
    EditText edtSearch ;
    SnowfallView snow ;
    ScrollView scrollView ;
    ImageView imgViewBaolixi ;






    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album , container , false );
        final ArrayList<Integer> gifImages = new ArrayList<>();
        gifImages.add(R.drawable.tet) ;
        gifImages.add(R.drawable.tet1) ;
        gifImages.add(R.drawable.tet2);
        gifImages.add(R.drawable.tet3) ;
        gifImages.add(R.drawable.tet4) ;
        mapping();
        Log.d("CHECKUSER" , LoginActivity.checkUser + "");
        if (LoginActivity.checkUser)  imgViewAvatar.setImageResource(R.drawable.avatar);
        else imgViewAvatar.setImageResource(R.drawable.user);
//        final Animation animationAlpha = AnimationUtils.loadAnimation(getActivity(),R.anim.animation_rotate);
//        final Animation animationScale = AnimationUtils.loadAnimation(getActivity(),R.anim.animation_scale);
//        imgViewBaolixi.startAnimation(animationAlpha);
        imgViewBaolixi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                v.startAnimation(animationScale);
                final Dialog settingsDialog = new Dialog(getActivity());
                settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);


                settingsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                settingsDialog.setContentView(getLayoutInflater().inflate(R.layout.image_layout , null));
                GifImageView gifImageView = settingsDialog.findViewById(R.id.gifImageViewTet) ;
                Collections.shuffle(gifImages);
             //   gifImageView.setBackgroundResource(gifImages.get(0));
                gifImageView.setImageResource(gifImages.get(0));
                settingsDialog.show();
                Button btn = settingsDialog.findViewById(R.id.btnDialog);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        settingsDialog.dismiss();
                    }
                });

            }
        });
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            private GestureDetector gestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    Log.d("TEST", "onDoubleTap");
                    if(snow.getVisibility() == View.VISIBLE)
                        snow.setVisibility(View.GONE);
                    else snow.setVisibility(View.VISIBLE);
                    return super.onDoubleTap(e);
                }
                @Override
                public boolean onSingleTapConfirmed(MotionEvent event) {
                    Log.d("TEST", "onSingleTap");
                    return false;
                }
            });

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                gestureDetector.onTouchEvent(event);
                return true;
            }


        });

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

    private void dismissListener() {
        Toast.makeText(getActivity(), "NNNNNNNNN", Toast.LENGTH_SHORT).show();

    }

    private void mapping() {

        viewPagerMain = view.findViewById(R.id.viewPagerMain);
     //   recyclerViewMain = view.findViewById(R.id.recycleViewMain);
        circleIndicatorMain = view.findViewById(R.id.indicatorMain);
      //  ft1 = view.findViewById(R.id.fragmentHottrend);

        btnSearch = view.findViewById(R.id.btnSearch);
        edtSearch = view.findViewById(R.id.edtSearch);
        snow = view.findViewById(R.id.snowfall);
        scrollView = view.findViewById(R.id.scrollViewAlumHottrend);
        imgViewBaolixi = view.findViewById(R.id.imageViewBaolixi);
        imgViewAvatar = view.findViewById(R.id.imageViewAvatar) ;



    }



}

