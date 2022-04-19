package com.example.appproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText user, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = (EditText) findViewById(R.id.nombre);
        password=(EditText) findViewById(R.id.contraseña);
    }
    public void registrarse(View view){
        Intent registrarse = new Intent(this,RegistrarseActivity.class);
        startActivity(registrarse);
    }
    public void login(View view){
        Intent login = new Intent(this,RegistrarseActivity.class);
        String name = user.getText().toString();
        String contra = password.getText().toString();
        if(!name.isEmpty() && !contra.isEmpty()){
            startActivity(login);
        }else if(name.isEmpty() && !contra.isEmpty()){
            Toast.makeText(this,"Falta Nombre",Toast.LENGTH_SHORT).show();
        }else if(!name.isEmpty() && contra.isEmpty()){
            Toast.makeText(this,"Falta Contraseña",Toast.LENGTH_SHORT).show();
        }else if(name.isEmpty() && contra.isEmpty()){
            Toast.makeText(this,"Faltan Nombre y Contraseña",Toast.LENGTH_SHORT).show();
        }


    }
}