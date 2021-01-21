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
import retrofit2.converter.gson.GsonConverterFactory;

public class Feedback extends AppCompatActivity {
    EditText title, desc;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        title = findViewById(R.id.titlef);
        desc = findViewById(R.id.desc);
        submit = findViewById(R.id.Pizza);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit.Builder retrofitBuilder =
                        new Retrofit.Builder()
                                .baseUrl("http://192.168.2.15:3000/")
                                .addConverterFactory(GsonConverterFactory.create());
                Retrofit retrofit = retrofitBuilder.build();
                api ap=retrofit.create(api.class);
                Call<ResponseBody> reg=ap.feedback(title.getText().toString(),desc.getText().toString());
                reg.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            Log.d("test",response.body().string());
                            Intent i=new Intent(Feedback.this,DetailsActivity.class);
                            startActivity(i);
                            Toast.makeText(getApplicationContext(),"Feedback Added",Toast.LENGTH_LONG);
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