package com.example.appproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText user, password;
    private TextView myTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = (EditText) findViewById(R.id.nombre);
        password=(EditText) findViewById(R.id.contraseña);

        myTextView = (TextView) findViewById(R.id.notmember);
        myTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrarse = new Intent(MainActivity.this,RegistrarseActivity.class);
                startActivity(registrarse);
            }
        });


    }
    public void registrarse(View view){
        Intent registrarse = new Intent(this,RegistrarseActivity.class);
        startActivity(registrarse);
    }
    public void login(View view){

        Intent principal = new Intent(this,MenuPrincipalActivity.class);
        String name = user.getText().toString();
        String contra = password.getText().toString();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();


        if(!name.isEmpty() && !contra.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery("select nombre,password from usuarios where nombre=?",new String[]{name},null);
            if(fila.moveToFirst()){
                if(name.equals(fila.getString(0))&&contra.equals(fila.getString(1))){
                    startActivity(principal);
                    BaseDeDatos.close();

                }else if(name.equals(fila.getString(0))){
                    Toast.makeText(this,"Contraseña Incorrecta",Toast.LENGTH_SHORT).show();
                    BaseDeDatos.close();
                }
            }else{
                Toast.makeText(this,"Usuario no existe",Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }

        }else if(name.isEmpty() && !contra.isEmpty()){
            Toast.makeText(this,"Falta Nombre",Toast.LENGTH_SHORT).show();
        }else if(!name.isEmpty() && contra.isEmpty()){
            Toast.makeText(this,"Falta Contraseña",Toast.LENGTH_SHORT).show();
        }else if(name.isEmpty() && contra.isEmpty()){
            Toast.makeText(this,"Faltan Nombre y Contraseña",Toast.LENGTH_SHORT).show();
        }


    }
}