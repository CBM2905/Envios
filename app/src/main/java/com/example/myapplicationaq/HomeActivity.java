package com.example.myapplicationaq;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeActivity extends AppCompatActivity implements PopupMenu

        .OnMenuItemClickListener {
    EditText idPaqueteE;
    Button buscar;

    String id;
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
        Intent It = getIntent();
        id = It.getStringExtra("id");
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        buscar = findViewById(R.id.buscar);
        idPaqueteE = findViewById(R.id.idPaquete);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idPaquete = idPaqueteE.getText().toString();
                db.collection("PAQUETE").document(idPaquete).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot dc = task.getResult();
                            String estado = (String)dc.get("ESTADO");
                            Intent I = new Intent(HomeActivity.this,EstadoActivity.class);
                            I.putExtra("st",estado);
                            I.putExtra("id",id);
                            startActivity(I);
                        }
                    }
                });
            }
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
            envia.putExtra("id",id);
            startActivity(envia);
            return true;
        }
        else if (itemId == R.id.HistorialItem){
            Intent historial = new Intent(HomeActivity.this, HistorialActivity.class);
            historial.putExtra("id",id);
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