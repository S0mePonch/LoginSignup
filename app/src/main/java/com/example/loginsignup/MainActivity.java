package com.example.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText user, password;
    Button btnEnter, btnRegistrar;
    UsuarioDao dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        user = (EditText)findViewById(R.id.User);
        password = (EditText)findViewById(R.id.password);
        btnEnter = (Button) findViewById(R.id.buttonLogin);
        btnRegistrar = (Button) findViewById(R.id.buttonSignUp);

        btnEnter.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);

        dao = new UsuarioDao(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.buttonLogin:
                String usr = user.getText().toString();
                String pas = password.getText().toString();
                if (usr.equals("") && pas.equals("")) {
                    Toast.makeText(this,"Error: Campos vacios",Toast.LENGTH_LONG).show();
                }else if(dao.login(usr,pas) == 1){
                    Usuario us = dao.obtenerUsuario(usr,pas);
                    Toast.makeText(this,"Login Exitoso",Toast.LENGTH_LONG).show();
                    Intent y=new Intent(MainActivity.this,Inicio.class);
                    y.putExtra("id",us.getId());
                    startActivity(y);
                    finish();
                }else{
                    Toast.makeText(this,"Usuario y/o contraseña Incorrectos",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.buttonSignUp:
                Intent i=new Intent(MainActivity.this,SignUp.class);
                startActivity(i);
                break;
        }
    }
}