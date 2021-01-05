package com.example.appmusiconline.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmusiconline.R;
import com.example.appmusiconline.Service.APIService;
import com.example.appmusiconline.Service.DataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextView txtSkip , txtSignUp;
    EditText edtUsername , edtPass ;
    ImageView imageViewSubmit ;
    public static boolean checkUser ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mapping ();
        txtSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUser = false ;
                Intent intent = new Intent(LoginActivity.this , TrangchuActivity.class);
                startActivity(intent);
            }
        });
        imageViewSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkuser(edtUsername.getText().toString() , edtPass.getText().toString());

            }
        });
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this , SignUpActivity.class);
                startActivity(intent);

            }
        });
    }
    private void checkuser (String username , String password) {
        DataService dataService = APIService.getService();
        Call<String> callback = dataService.checkuser(username , password);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String a = response.body().trim();
                Log.d("USER123" ,  a +"hello") ;
                if (a.contains("THAT")) {

                    checkUser = false;
                    Toast.makeText(LoginActivity.this, "Vui Long Dang Nhap Lai hoac skip", Toast.LENGTH_SHORT).show();
                }
                else {
                    checkUser = true ;
                    Intent intent = new Intent(LoginActivity.this , TrangchuActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("USER123" , "That bai") ;
            }
        });


    }

    private void mapping() {
        txtSkip = (TextView) findViewById(R.id.txtSkip);
        edtUsername = (EditText) findViewById(R.id.edtUsername) ;
        edtPass = (EditText) findViewById(R.id.edtPass) ;
        imageViewSubmit = (ImageView) findViewById(R.id.imageviewSubmit);
        txtSignUp = (TextView) findViewById(R.id.textViewSignUp) ;
    }
}
