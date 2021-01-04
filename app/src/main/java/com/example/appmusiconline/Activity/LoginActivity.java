package com.example.appmusiconline.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.appmusiconline.R;

public class LoginActivity extends AppCompatActivity {

    TextView txtSkip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mapping ();
        txtSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this , TrangchuActivity.class);
                startActivity(intent);
            }
        });
    }

    private void mapping() {
        txtSkip = (TextView) findViewById(R.id.txtSkip);
    }
}
