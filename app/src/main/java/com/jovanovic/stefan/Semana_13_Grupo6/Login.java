package com.jovanovic.stefan.Semana_13_Grupo6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText username,password;
    Button signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username1);
        password= findViewById(R.id.password1);
        signin = findViewById(R.id.signin);
        DB = new DBHelper(this);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if (TextUtils.isEmpty(user)||TextUtils.isEmpty(pass)){
                    Toast.makeText(Login.this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                }else {
                    boolean checkuserpass = DB.checkusernamepassword(user,pass);
                    if (checkuserpass == true){
                        Toast.makeText(Login.this, "Inicio de Sesion Exitoso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(Login.this, "Inicio de Sesion Fallido", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}