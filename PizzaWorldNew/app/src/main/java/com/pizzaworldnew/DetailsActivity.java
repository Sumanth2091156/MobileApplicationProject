package com.pizzaworldnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {

    TextView name,price,description;
    Button feedback;
    pizzaModel ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        name=findViewById(R.id.p_name);
        price=findViewById(R.id.p_price);
        description=findViewById(R.id.p_des);
        feedback=findViewById(R.id.feedback);

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DetailsActivity.this,Feedback.class);
                startActivity(i);
            }
        });

        ad=getIntent().getParcelableExtra("data");
        name.setText("Name :"+ad.name);
        price.setText("Price :"+ad.price+" CAD");
        description.setText("Description :"+ad.description);


        findViewById(R.id.book).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailsActivity.this,"Add To Cart successfully",Toast.LENGTH_LONG).show();
                Intent i=new Intent(DetailsActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}