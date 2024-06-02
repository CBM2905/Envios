package com.example.myapplicationaq;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EstadoActivity extends AppCompatActivity {
    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_estado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //


        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        Intent i = getIntent();
        String estado = i.getStringExtra("st");
        if(estado.equals("RECOGIDO")){
            img1.setBackgroundColor(Color.BLACK);

        }
        else if(estado.equals("EN BODEGA")){
            img2.setBackgroundColor(Color.BLACK);
        }
        else if(estado.equals("TRANSPORTANDOSE")){
            img3.setBackgroundColor(Color.BLACK);
        }
        else if(estado.equals("ENTREGADO")){
            img4.setBackgroundColor(Color.BLACK);
        }
        else if(false){

        }
    }
}