package com.jovanovic.stefan.Semana_13_Grupo6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registrarse extends AppCompatActivity {
EditText txtNombre,txtContrasena,txtreContrasena;
Button btnRegistrar,btnIngresar;
DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        txtNombre = findViewById(R.id.txtnombre);
        txtContrasena= findViewById(R.id.txtContrasena);
        txtreContrasena = findViewById(R.id.txtreContrasena);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnIngresar = findViewById(R.id.btnIngresar);
        DB = new DBHelper(this);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String usu =txtNombre.getText().toString();
               String con = txtContrasena.getText().toString();
               String recon = txtreContrasena.getText().toString();
               if(TextUtils.isEmpty(usu)||TextUtils.isEmpty(con) ||TextUtils.isEmpty(recon) ){
                   Toast.makeText(Registrarse.this, " Todos los campos son obligatorios ", Toast.LENGTH_SHORT).show();
               }else{
                   if(con.equals(recon)){
                       boolean checkusers = DB.checkusuario(usu);
                       if (checkusers==false){
                           boolean insert = DB.insertarDatos(usu,con);
                           if (insert==true){
                               Toast.makeText(Registrarse.this, "Resgistrado con Exito", Toast.LENGTH_SHORT).show();
                               Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                               startActivity(intent);
                           }else {
                               Toast.makeText(Registrarse.this,"No se pudo Registrar",Toast.LENGTH_SHORT).show();
                           }

                       }else {
                           Toast.makeText(Registrarse.this,"Usuario ya existe",Toast.LENGTH_SHORT).show();
                       }

                   }else {
                       Toast.makeText(Registrarse.this,"Contraseña no Coinciden",Toast.LENGTH_SHORT).show();
                   }
               }
            }
        });
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
    }
}