package com.example.myapplicationaq;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    EditText email;
    EditText Password;
    FirebaseAuth auth;

    ProgressBar sppin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.ativity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        sppin = findViewById(R.id.progressBar);
        sppin.setVisibility(View.GONE);
        auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.editTextTextEmailAddress);
        Password = findViewById(R.id.editTextTextPassword);
        findViewById(R.id.buttonLogIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String EmailT = email.getText().toString();
                String PasswordT = Password.getText().toString();
                if(EmailT.equals("Admin") && PasswordT.equals("012345")){
                    Intent Admin = new Intent(LogInActivity.this, AdminActivity.class);
                    startActivity(Admin);
                }
                else{
                    sppin.setVisibility(View.VISIBLE);
                    auth.signInWithEmailAndPassword(EmailT,PasswordT).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                FirebaseUser user = auth.getCurrentUser();
                                String id = user.getUid();

                                Intent I = new Intent(LogInActivity.this, HomeActivity.class);
                                I.putExtra("id",id);
                                I.putExtra("ac","none");
                                sppin.setVisibility(View.GONE);
                                startActivity(I);
                            }
                        }
                    });
                }
            }
        });
        findViewById(R.id.menuButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(LogInActivity.this,v);
                popupMenu.setOnMenuItemClickListener(LogInActivity.this::onMenuItemClick);
                popupMenu.inflate(R.menu.menu);
                popupMenu.show();

            }
        });
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.Registro){
            Intent registro = new Intent(LogInActivity.this,SignUpActivity.class);
            startActivity(registro);
        }
        return false;
    }
}