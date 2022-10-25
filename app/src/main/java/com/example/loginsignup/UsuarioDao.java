package com.example.loginsignup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class UsuarioDao {
    Context con;
    Usuario usuario;
    ArrayList<Usuario> lista;
    SQLiteDatabase database;
    String bd = "BDUsuarios";
    String tabla = "CREATE TABLE IF NOT EXISTS Usuario(id integer primary key AUTOINCREMENT, nombre text, usuario text, password text, escolaridad text, estadoCivil tex, habilidades text)";

    public UsuarioDao(Context con){
        this.con = con;
        database=con.openOrCreateDatabase(bd, con.MODE_PRIVATE, null);
        database.execSQL(tabla);
        usuario = new Usuario();
    }

    public boolean insertarUsuario(Usuario usr){
        if(buscarUsuario(usr.getUsuario()) == 0){//Si retorna 0 es porque el usuario no existe
            ContentValues cv = new ContentValues();
            cv.put("nombre",usr.getNombre());
            cv.put("usuario",usr.getUsuario());
            cv.put("password",usr.getPassword());
            cv.put("escolaridad",usr.getEscolaridad());
            cv.put("estadoCivil",usr.getEstadoCivil());
            cv.put("habilidades",usr.getHabilidades());
            return (database.insert("Usuario", null, cv)>0);
        }else{
            return false;
        }
    }

    public int buscarUsuario(String usr){
        int x = 0;

        lista = selectUsuarios();

        for (Usuario us:lista) {
            if(us.getUsuario().equals(usr)){
                x++;
            }
        }
        return x;
    }

    public ArrayList<Usuario> selectUsuarios(){
        ArrayList<Usuario> lista=new ArrayList<Usuario>();
        lista.clear();
        Cursor cr = database.rawQuery("select * from Usuario", null);
        if(cr!=null && cr.moveToFirst()){
            do{
                Usuario u = new Usuario();
                u.setId(cr.getInt(0));
                u.setNombre(cr.getString(1));
                u.setUsuario(cr.getString(2));
                u.setPassword(cr.getString(3));
                u.setEscolaridad(cr.getString(4));
                u.setEstadoCivil(cr.getString(5));
                u.setHabilidades(cr.getString(6));
                lista.add(u);

            }while(cr.moveToNext());
        }
        return lista;
    }

    public int login(String user, String pass){
        int a = 0;
        Cursor cr = database.rawQuery("select * from Usuario", null);
        if(cr!=null && cr.moveToFirst()){
            do{
                if(cr.getString(2).equals(user)&&cr.getString(3).equals(pass)){
                    a++;
                }
            }while(cr.moveToNext());
        }
        return a;
    }

    public Usuario obtenerUsuario(String user, String pass){
        lista = selectUsuarios();
        for(Usuario usuario:lista){
            if(usuario.getUsuario().equals(user)&&usuario.getPassword().equals(pass)){
                return usuario;
            }
        }
        return null;
    }

    public Usuario obtenerUsuarioPorId(int id){
        lista = selectUsuarios();
        for(Usuario usuario:lista){
            if(usuario.getId() == id){
                return usuario;
            }
        }
        return null;
    }

    public boolean updateUsuario(Usuario us){
        ContentValues cv = new ContentValues();
        cv.put("nombre",us.getNombre());
        cv.put("usuario",us.getUsuario());
        cv.put("password",us.getPassword());
        cv.put("escolaridad",us.getEscolaridad());
        cv.put("estadoCivil",us.getEstadoCivil());
        cv.put("habilidades",us.getHabilidades());
        return (database.update("Usuario",cv, "id="+us.getId(),null)>0);
    }

    public boolean deleteUsuario(int id){
        return (database.delete("Usuario", "id="+id,null)>0);
    }
}
