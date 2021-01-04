package com.example.appmusiconline;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.example.appmusiconline.Fragment.Fragment_getstart;
import com.example.appmusiconline.Fragment.Fragment_getstart_1;
import com.example.appmusiconline.Fragment.Fragment_getstart_2;
import com.example.appmusiconline.Fragment.Fragment_personal_song;

import java.util.List;

public class SildePagerAdapter extends FragmentPagerAdapter {


    public SildePagerAdapter(@NonNull  FragmentManager fm){
        super(fm);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :

                return new Fragment_getstart();
            case 1 :
                return new Fragment_getstart_1();
            case 2 :
                return new Fragment_getstart_2();
        }
        return null ;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
