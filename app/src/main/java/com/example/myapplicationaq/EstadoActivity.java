package com.example.myapplicationaq;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EstadoActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;
    String id;
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
        Intent It = getIntent();
        id = It.getStringExtra("id");
        //
        findViewById(R.id.menuButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(EstadoActivity.this,v);
                popupMenu.setOnMenuItemClickListener(EstadoActivity.this::onMenuItemClick);
                popupMenu.inflate(R.menu.menu);
                popupMenu.show();

            }
        });

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
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int itemId = item.getItemId();
        if(R.id.EnviaItem == itemId){
            Intent it = new Intent(EstadoActivity.this,EnviaActivity.class);
            it.putExtra("id",id);
            startActivity(it);
            return true;
        }
        else if (itemId == R.id.LogOut){
            Intent it = new Intent(EstadoActivity.this,MainActivity.class);
            startActivity(it);
            return true;
        }
        else if (itemId == R.id.Home){
            Intent it = new Intent(EstadoActivity.this,HomeActivity.class);
            it.putExtra("id",id);
            startActivity(it);
            return true;
        }
        return false;
    }
}