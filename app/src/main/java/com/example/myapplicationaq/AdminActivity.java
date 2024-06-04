package com.example.myapplicationaq;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class AdminActivity extends AppCompatActivity {
    EditText id;
    RadioButton recogido;
    RadioButton enBodega;
    RadioButton transportandose;
    RadioButton entregado;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        db = FirebaseFirestore.getInstance();
        id = findViewById(R.id.IdPaquete);
        recogido = findViewById(R.id.Rb1);
        enBodega = findViewById(R.id.Rb2);
        transportandose = findViewById(R.id.Rb3);
        entregado = findViewById(R.id.Rb4);
        findViewById(R.id.AdminButton1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idPaquete = id.getText().toString();
                if(idPaquete.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Inserte algun id",Toast.LENGTH_LONG).show();
                }
                else {
                    db.collection("PAQUETE").document(idPaquete).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot dc = task.getResult();
                                String estadoP = (String) dc.get("ESTADO");
                                try {
                                    if (estadoP.equals("null")) {
                                        Toast.makeText(getApplicationContext(), "bad", Toast.LENGTH_LONG).show();
                                    } else {
                                        if (estadoP.equals(recogido.getText().toString())) {
                                            recogido.setChecked(true);
                                        } else if (estadoP.equals(enBodega.getText().toString())) {
                                            enBodega.setChecked(true);
                                        } else if (estadoP.equals(transportandose.getText().toString())) {
                                            transportandose.setChecked(true);
                                        } else if (estadoP.equals(entregado.getText().toString())) {
                                            entregado.setChecked(true);
                                        }
                                        Log.d("her", "dat" + dc.get("ESTADO") + "  " + recogido.getText().toString());
                                    }
                                }
                                catch(Exception e){
                                    Toast.makeText(getApplicationContext(),"bad",Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(),"invalid da",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        findViewById(R.id.AdminButton2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String idDoc = id.getText().toString();
                if(idDoc.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Inserte Algun id",Toast.LENGTH_LONG).show();
                }
                else {
                DocumentReference upd = db.collection("PAQUETE").document(idDoc);
                String estado = "";
                if(recogido.isChecked()){
                     estado = recogido.getText().toString();
                }
                else if (enBodega.isChecked()){
                    estado = enBodega.getText().toString();
                }
                else if (transportandose.isChecked()){
                    estado = transportandose.getText().toString();
                }
                else if (entregado.isChecked()){
                    estado = entregado.getText().toString();
                }
                upd.update("ESTADO",estado).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(),"Actualizado",Toast.LENGTH_LONG).show();
                    }
                });}
            }
        });
    }
}