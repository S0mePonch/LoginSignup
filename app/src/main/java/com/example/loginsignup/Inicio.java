package com.example.loginsignup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Inicio extends AppCompatActivity implements View.OnClickListener {
    Button btnEdit, btnEliminar, btnMostrar, btnSalir;
    TextView nombre;
    int id = 0;
    Usuario u;
    UsuarioDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        nombre = (TextView) findViewById(R.id.nombreUsuario);
        btnEdit = (Button) findViewById(R.id.buttonEditar);
        btnEliminar = (Button) findViewById(R.id.buttonEliminar);
        btnMostrar = (Button) findViewById(R.id.buttonMostrar);
        btnSalir = (Button) findViewById(R.id.buttonCancelar);

        btnEdit.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
        btnMostrar.setOnClickListener(this);
        btnSalir.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        id = b.getInt("id");
        dao = new UsuarioDao(this);
        u = dao.obtenerUsuarioPorId(id);
        nombre.setText(u.getNombre()+" "+u.getUsuario());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonEditar:
                Intent a=new Intent(Inicio.this,Edit.class);
                a.putExtra("id", id);
                startActivity(a);
                break;
            case R.id.buttonEliminar:
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setMessage("Estas Seguro de Eliminar tu centa??");
                b.setCancelable(false);
                b.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(dao.deleteUsuario(id)){
                            Toast.makeText(Inicio.this, "Se elimino exitsamente!", Toast.LENGTH_LONG).show();
                            Intent a=new Intent(Inicio.this,MainActivity.class);
                            startActivity(a);
                            finish();
                        }else{
                            Toast.makeText(Inicio.this, "Error", Toast.LENGTH_LONG).show();

                        }
                    }
                });
                b.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                b.show();
                break;
            case R.id.buttonMostrar:
                Intent c=new Intent(Inicio.this,Mostrar.class);
                startActivity(c);
                break;
            case R.id.buttonCancelar:
                Intent y=new Intent(Inicio.this,MainActivity.class);
                startActivity(y);
                finish();
                break;
        }
    }
}