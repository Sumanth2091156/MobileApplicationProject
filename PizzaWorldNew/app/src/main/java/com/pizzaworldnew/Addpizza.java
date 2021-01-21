package com.pizzaworldnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.converter.gson.GsonConverterFactory;

public class Addpizza extends AppCompatActivity {
    EditText name,price, desc, image;
    Button addpizza, logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpizza);

        name = findViewById(R.id.spizza);
        price = findViewById(R.id.sprice);
        desc = findViewById(R.id.sdesc);
        image = findViewById(R.id.simage);
        addpizza = findViewById(R.id.Pizza);
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Addpizza.this,LoginActivity.class);
                startActivity(i);
            }
        });

        addpizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit.Builder retrofitBuilder =
                        new Retrofit.Builder()
                                .baseUrl("http://192.168.2.15:3000/")
                                .addConverterFactory(GsonConverterFactory.create());
                Retrofit retrofit = retrofitBuilder.build();
                api ap=retrofit.create(api.class);
                Call<ResponseBody> reg=ap.addpizza(name.getText().toString(),price.getText().toString(),image.getText().toString(),desc.getText().toString());
                reg.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            Log.d("test",response.body().string());
                            Intent i=new Intent(Addpizza.this,Addpizza.class);
                            startActivity(i);
                            Toast.makeText(getApplicationContext(),"Added Pizza",Toast.LENGTH_LONG);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_LONG);
                    }
                });

            }
        });

    }
}