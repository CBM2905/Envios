package com.example.myapplicationaq;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity implements PopupMenu

        .OnMenuItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main5), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ImageButton Ibtn = findViewById(R.id.menuButton);
        Ibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(HomeActivity.this,v)  ;
                popupMenu.setOnMenuItemClickListener(HomeActivity.this::onMenuItemClick);
                popupMenu.inflate(R.menu.menu_sesion);
                popupMenu.show();
            }
        });
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.EnviaItem){
            Intent envia = new Intent(HomeActivity.this, EnviaActivity.class);
            startActivity(envia);
            return true;
        }
        else if (itemId == R.id.HistorialItem){
            Intent historial = new Intent(HomeActivity.this, HistorialActivity.class);
            startActivity(historial);
            return true;
        }
        else if (itemId == R.id.LogOut){
            Intent main = new Intent(HomeActivity.this,MainActivity.class);
            startActivity(main);
            return true;
        }
        return false;
    }
}