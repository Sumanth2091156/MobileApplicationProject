package com.pizzaworldnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    EditText email,password;
    Button submit;
    TextView register,admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        submit=findViewById(R.id.btn_login);
        register=findViewById(R.id.btn_signup);
        admin=findViewById(R.id.btn_admin);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginActivity.this,Admin.class);
                startActivity(i);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(i);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit.Builder retrofitBuilder =
                        new Retrofit.Builder()
                                .baseUrl("http://192.168.2.15:3000/")
                                .addConverterFactory(GsonConverterFactory.create());
                Retrofit retrofit = retrofitBuilder.build();
                api ap=retrofit.create(api.class);
                Call<ResponseBody> reg=ap.login(email.getText().toString(),password.getText().toString());
                reg.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {

                            Log.d("success",response.body().string());
                            Toast.makeText(getApplicationContext(),"Login Successfully",Toast.LENGTH_LONG);
                            Intent i=new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(i);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_LONG);
                    }
                });

            }
        });
    }
}