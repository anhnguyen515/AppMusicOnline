package com.example.appmusiconline.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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

    TextView txtSignUp;
    EditText edtUsername, edtPass;
    ImageButton imageViewSubmit, imgSkip;
    public static boolean checkUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mapping();
        imgSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUser = false;
                Intent intent = new Intent(LoginActivity.this, TrangchuActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_enter_right, R.anim.anim_exit_left);
            }
        });
        imageViewSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUser(edtUsername.getText().toString(), edtPass.getText().toString());

            }
        });
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);

            }
        });
    }

    private void checkUser(String username, String password) {
        DataService dataService = APIService.getService();
        Call<String> callback = dataService.checkuser(username, password);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String a = response.body().trim();
                Log.d("USER123", a + "hello");
                if (edtUsername.getText().toString().trim().length() == 0 || edtPass.getText().toString().trim().length() == 0) {
                    Toast.makeText(LoginActivity.this, "Incorrect username or password.\nPlease try again or Skip.", Toast.LENGTH_SHORT).show();
                } else {
                    if (a.contains("THAT")) {

                        checkUser = false;
                        Toast.makeText(LoginActivity.this, "Incorrect username or password.\nPlease try again or Skip.", Toast.LENGTH_SHORT).show();
                    } else {
                        checkUser = true;
                        Intent intent = new Intent(LoginActivity.this, TrangchuActivity.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("USER123", "That bai");
            }
        });


    }

    private void mapping() {
        imgSkip = findViewById(R.id.imgSkip);
        edtUsername = findViewById(R.id.edtUsername);
        edtPass = findViewById(R.id.edtPass);
        imageViewSubmit = findViewById(R.id.imageviewSubmit);
        txtSignUp = findViewById(R.id.textViewSignUp);
    }
}
