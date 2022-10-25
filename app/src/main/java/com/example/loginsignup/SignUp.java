package com.example.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class SignUp extends AppCompatActivity implements View.OnClickListener{
    EditText us, pass, nom;
    RadioButton casado, soltero;
    CheckBox Java, CSh, CMas;
    Spinner escolaridad;
    Button reg, cancel;
    UsuarioDao dao;

    String [] escolaridades = {"Licenciatura", "Maestria", "Doctorado"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        us = (EditText)findViewById(R.id.usuario);
        pass = (EditText)findViewById(R.id.contraseña);
        nom = (EditText)findViewById(R.id.nombre);
        casado = (RadioButton)findViewById(R.id.radioCasado);
        soltero = (RadioButton)findViewById(R.id.radioSoltero);
        Java = (CheckBox)findViewById(R.id.checkBoxJava);
        CSh = (CheckBox)findViewById(R.id.checkBoxC);
        CMas = (CheckBox)findViewById(R.id.checkBoxCMas);
        escolaridad = (Spinner)findViewById(R.id.spinnerEscolaridades);

        cancel = (Button) findViewById(R.id.buttonIniciarSesion);
        reg = (Button) findViewById(R.id.buttonregSignUp);

        cancel.setOnClickListener(this);
        reg.setOnClickListener(this);

        dao = new UsuarioDao(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SignUp.this, android.R.layout.simple_spinner_dropdown_item, escolaridades);
        escolaridad.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.buttonregSignUp:
                Usuario u = new Usuario();
                u.setNombre(nom.getText().toString());
                u.setUsuario(us.getText().toString());
                u.setPassword(pass.getText().toString());
                u.setEscolaridad(escolaridad.getSelectedItem().toString());

                if(!casado.isChecked()&&!soltero.isChecked()){
                    Toast.makeText(this,"Error: Estado Civil Vacio", Toast.LENGTH_LONG).show();
                }else{
                    if(casado.isChecked()){
                        u.setEstadoCivil(casado.getText().toString());
                    }else if(soltero.isChecked()){
                        u.setEstadoCivil(soltero.getText().toString());
                    }
                }

                if(!Java.isChecked() == false &&CSh.isChecked() == false &&CMas.isChecked() == false){
                    Toast.makeText(this,"Error: Habilidades Vacio", Toast.LENGTH_LONG).show();
                }else{
                    if(Java.isChecked()){
                        u.setHabilidades("Java");
                        if(CSh.isChecked()){
                            u.setHabilidades(u.getHabilidades() + " C# ");
                        }
                        if(CMas.isChecked()){
                            u.setHabilidades(u.getHabilidades() + " C++ ");
                        }
                    }
                    if(CSh.isChecked() && !Java.isChecked()){
                        u.setHabilidades("C#");
                        if(CMas.isChecked()){
                            u.setHabilidades(u.getHabilidades() + " C++ ");
                        }
                    }
                    if(CMas.isChecked() && !Java.isChecked() && !CSh.isChecked()){
                        u.setHabilidades("C++");
                    }
                }

            if(!u.isNull()){
                    Toast.makeText(this,"Error: Campos Vacios", Toast.LENGTH_LONG).show();
                    Toast.makeText(this, u.toString(), Toast.LENGTH_LONG).show();
            }else if(dao.insertarUsuario(u)){
                Toast.makeText(this, u.toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(this,"Registro Exitoso", Toast.LENGTH_LONG).show();
                    Intent i2 = new Intent(SignUp.this,MainActivity.class);
                    startActivity(i2);
                    finish();
                }else{
                    Toast.makeText(this, "El usuario ya esta registrado", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.buttonIniciarSesion:
                Intent i=new Intent(SignUp.this,MainActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }
}