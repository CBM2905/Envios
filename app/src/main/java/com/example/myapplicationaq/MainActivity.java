package com.example.myapplicationaq;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    EditText id;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        db = FirebaseFirestore.getInstance();
        id = findViewById(R.id.PaqueteId);
        Button b = findViewById(R.id.buttonBuscar);
        ImageButton b2 = findViewById(R.id.menuButton);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this,v);
                popupMenu.setOnMenuItemClickListener(MainActivity.this::onMenuItemClick);
                popupMenu.inflate(R.menu.menu);
                popupMenu.show();
            }
        });
        Intent i = new Intent(this, LogInActivity.class);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idPaquete = id.getText().toString();
                if(idPaquete.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Ingrese algun dato",Toast.LENGTH_SHORT).show();
                }
                else{
                db.collection("PAQUETE").document(idPaquete).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            DocumentSnapshot dc = task.getResult();
                            String estado = (String) dc.get("ESTADO");
                            try {


                                if (estado.equals("null")) {
                                    Toast.makeText(getApplicationContext(), "no existe", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "El estado es: " + estado, Toast.LENGTH_LONG).show();
                                }
                            }
                            catch (Exception e){
                                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
                            }
                            }
                        else{
                            Toast.makeText(getApplicationContext(),"no exite o invalido",Toast.LENGTH_SHORT).show();
                        }
                    }
                });}
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        if(item.getItemId() == R.id.InicioSesion){
            Intent i = new Intent(MainActivity.this, LogInActivity.class);
            startActivity(i);
            return true;
        }
        else{
            Intent I = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(I);
            return true;
        }

        }
    }
