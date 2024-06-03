package com.example.myapplicationaq;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    EditText email;
    EditText password;
    EditText name;
    EditText lastName;
    EditText address;
    EditText user;
    EditText passwordVerify;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent It = getIntent();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main3), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        name = findViewById(R.id.editTextPersonName);
        lastName = findViewById(R.id.editTextPersonLastName);
        address = findViewById(R.id.editTextAddress);
        user = findViewById(R.id.editTextUser);
        passwordVerify = findViewById(R.id.editTextPasswordVerify);

        db = FirebaseFirestore.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();


        findViewById(R.id.buttonCreate).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!password.getText().toString().equals(passwordVerify.getText().toString())) {
                    passwordVerify.setError("Las contraseñas deben ser iguales");
                    return;
                }

                Map<String, Object> mp = new HashMap<>();
                mp.put("Nombre", name.getText().toString());
                mp.put("Apellido", lastName.getText().toString());
                mp.put("Direccion", address.getText().toString());
                mp.put("Usuario", user.getText().toString());
                mp.put("Contraseña", password.getText().toString());
                mp.put("Email", email.getText().toString());

                db.collection("USUARIO").add(mp).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {

                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        auth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),"Usuario creado",Toast.LENGTH_LONG).show();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"usuario no creado",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                });
            }
        });

        findViewById(R.id.menuButton3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(SignUpActivity.this,v);
                popupMenu.setOnMenuItemClickListener(SignUpActivity.this::onMenuItemClick);
                popupMenu.inflate(R.menu.menu);
                popupMenu.show();

            }
        });
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.InicioSesion){
            Intent InSesion = new Intent(SignUpActivity.this,LogInActivity.class);
            startActivity(InSesion);
        }
        return false;
    }
}
