package com.example.myapplicationaq;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HistorialActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    ListView historial;
    List<String> paquetes;
    String id;
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
        Intent It = getIntent();
        id = It.getStringExtra("id");
        paquetes = new ArrayList<String>();
        historial = findViewById(R.id.historial);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("PAQUETE")
                .whereEqualTo("Usuario", id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot qds : task.getResult()){
                                Log.d("heree","paquete id " + qds.getId());
                                // Obtener el ID, el estado y la ciudad del paquete
                                String paqueteId = qds.getId();
                                String ESTADO = qds.getString("ESTADO");
                                String Ciudad = qds.getString("Ciudad"); // Asumiendo que tienes un campo "ciudad" en tus documentos
                                paquetes.add("Guia: " + paqueteId + " || Estado: " + ESTADO + " || Ciudad: " + Ciudad);
                            }
                            String[] arr = new String[paquetes.size()];
                            paquetes.toArray(arr);
                            ArrayAdapter<String> apt = new ArrayAdapter<String>(HistorialActivity.this,R.layout.activity_listview,arr);
                            historial.setAdapter(apt);
                }
            }
        });
        findViewById(R.id.menuButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(HistorialActivity.this,v);
                popupMenu.setOnMenuItemClickListener(HistorialActivity.this::onMenuItemClick);
                popupMenu.inflate(R.menu.menu_sesion);
                popupMenu.show();

            }
        });
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int itemId = item.getItemId();
        if(R.id.EnviaItem == itemId){
            Intent it = new Intent(HistorialActivity.this,EnviaActivity.class);
            it.putExtra("id",id);
            startActivity(it);
            return true;
        }
        else if (itemId == R.id.LogOut){
            Intent it = new Intent(HistorialActivity.this,MainActivity.class);
            startActivity(it);
            return true;
        }
        else if (itemId == R.id.Home){
            Intent it = new Intent(HistorialActivity.this,HomeActivity.class);
            it.putExtra("id",id);
            startActivity(it);
            return true;
        }
        return false;
    }
}