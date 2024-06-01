package com.example.myapplicationaq;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HistorialActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_historial);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findViewById(R.id.menuButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(HistorialActivity.this,v);
                popupMenu.setOnMenuItemClickListener(HistorialActivity.this::onMenuItemClick);
                popupMenu.inflate(R.menu.menu);
                popupMenu.show();

            }
        });
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int itemId = item.getItemId();
        if(R.id.EnviaItem == itemId){
            Intent it = new Intent(HistorialActivity.this,EnviaActivity.class);
            startActivity(it);
            return true;
        }
        else if (itemId == R.id.LogOut){
            Intent it = new Intent(HistorialActivity.this,HomeActivity.class);
            startActivity(it);
            return true;
        }
        return false;
    }
}