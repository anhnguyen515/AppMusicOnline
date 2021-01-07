package com.example.appmusiconline.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appmusiconline.Activity.LoginActivity;
import com.example.appmusiconline.R;

public class Fragment_getstart_2 extends Fragment {
    ImageView imgLetgo ;
    ViewGroup view ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = (ViewGroup) inflater.inflate(R.layout.fragment_getstart_2 , container , false);

        imgLetgo = view.findViewById(R.id.imgLetgo);
        imgLetgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , LoginActivity.class);
                startActivity(intent);

            }
        });
        return view;
    }
}
