package com.example.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Edit extends AppCompatActivity implements View.OnClickListener {
    EditText eUser, eNombre, ePassword, eEstadoCivil, eHabilidad, eEscolar;
    RadioButton Casado, Soltero;
    CheckBox eJava, eCMas, eCSh;
    Spinner eEscolaridad;
    Button btnActualizar, btnCancelar;
    int id = 0;
    Usuario u;
    UsuarioDao dao;
    Intent x;

    String [] eEscolaridades = {"Licenciatura", "Maestria", "Doctorado"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        eUser = (EditText) findViewById(R.id.EditUsuario);
        ePassword = (EditText) findViewById(R.id.EditContraseña);
        eNombre = (EditText) findViewById(R.id.EditNombre);
        Casado = (RadioButton) findViewById(R.id.radioEditCasado);
        Soltero = (RadioButton) findViewById(R.id.radioEditSoltero);
        eJava = (CheckBox) findViewById(R.id.checkBoxEditJava);
        eCMas = (CheckBox) findViewById(R.id.checkBoxEditCMas);
        eCSh = (CheckBox) findViewById(R.id.checkBoxEditC);
        btnActualizar = (Button) findViewById(R.id.buttonActualizar);
        btnCancelar = (Button) findViewById(R.id.buttonCancelar);
        eEscolaridad = (Spinner) findViewById(R.id.spinnerEditEscolaridades);
        btnActualizar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Edit.this, android.R.layout.simple_spinner_dropdown_item, eEscolaridades);
        eEscolaridad.setAdapter(adapter);

        Bundle b = getIntent().getExtras();
        id = b.getInt("id");
        dao = new UsuarioDao(this);
        u = dao.obtenerUsuarioPorId(id);
        eUser.setText(u.getUsuario());
        eNombre.setText(u.getNombre());
        ePassword.setText(u.getPassword());
        //eEstadoCivil.setText(u.getEstadoCivil());
        //eHabilidad.setText(u.getHabilidades());
        //eEscolar.setText(u.getEscolaridad());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonActualizar:
                u.setNombre(eNombre.getText().toString());
                u.setUsuario(eUser.getText().toString());
                u.setPassword(ePassword.getText().toString());
                u.setEscolaridad(eEscolaridad.getSelectedItem().toString());

                if(!Casado.isChecked()&&!Soltero.isChecked()){
                    Toast.makeText(this,"Error: Estado Civil Vacio", Toast.LENGTH_LONG).show();
                }else{
                    if(Casado.isChecked()){
                        u.setEstadoCivil(Casado.getText().toString());
                    }else if(Soltero.isChecked()){
                        u.setEstadoCivil(Soltero.getText().toString());
                    }
                }

                if(!eJava.isChecked() == false &&!eCSh.isChecked() == false &&!eCMas.isChecked() == false){
                    Toast.makeText(this,"Error: Habilidades Vacio", Toast.LENGTH_LONG).show();
                }else{
                    if(eJava.isChecked()){
                        u.setHabilidades("Java");
                        if(eCSh.isChecked()){
                            u.setHabilidades(u.getHabilidades() + " C# ");
                        }
                        if(eCMas.isChecked()){
                            u.setHabilidades(u.getHabilidades() + " C++ ");
                        }
                    }
                    if(eCSh.isChecked() && !eJava.isChecked()){
                        u.setHabilidades("C#");
                        if(eCMas.isChecked()){
                            u.setHabilidades(u.getHabilidades() + " C++ ");
                        }
                    }
                    if(eCMas.isChecked() && !eJava.isChecked() && !eCSh.isChecked()){
                        u.setHabilidades("C++");
                    }
                }

                Toast.makeText(this, u.toString(), Toast.LENGTH_LONG).show();

                if(!u.isNull()){
                    Toast.makeText(this,"Error: Campos Vacios", Toast.LENGTH_LONG).show();
                    Toast.makeText(this, u.toString(), Toast.LENGTH_LONG).show();
                }else if(dao.updateUsuario(u)){
                    Toast.makeText(this, u.toString(), Toast.LENGTH_LONG).show();
                    Toast.makeText(this,"Actualizacion Exitosoa", Toast.LENGTH_LONG).show();
                    Intent i2 = new Intent(Edit.this,Inicio.class);
                    i2.putExtra("id", u.getId());
                    startActivity(i2);
                    finish();
                }else{
                    Toast.makeText(this, "No se puede actualizar el usuario", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.buttonCancelar:
                Intent y=new Intent(Edit.this,Edit.class);
                startActivity(y);
                finish();
                break;
        }
    }
}