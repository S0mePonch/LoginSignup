package com.example.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Mostrar extends AppCompatActivity {
    ListView lista;
    UsuarioDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        lista = (ListView) findViewById(R.id.lista);
        dao = new UsuarioDao(this);
        ArrayList<Usuario> arrL = dao.selectUsuarios();
        ArrayList<String> list = new ArrayList<String>();

        for(Usuario u: arrL){
            list.add(u.getNombre()+" "+u.getUsuario());
        }
        ArrayAdapter<String> array = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,list);
        lista.setAdapter(array);
    }
}