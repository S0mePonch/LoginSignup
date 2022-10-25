package com.example.loginsignup;

import android.widget.Toast;

public class Usuario {
    int id;
    String nombre, usuario, password, escolaridad, estadoCivil, habilidades;

    public Usuario() {
    }

    public Usuario(String nombre, String usuario, String password, String escolaridad, String estadoCivil, String habilidades) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
        this.escolaridad = escolaridad;
        this.estadoCivil = estadoCivil;
        this.habilidades = habilidades;
    }

    public Usuario(int id, String nombre, String usuario, String password, String escolaridad, String estadoCivil, String habilidades) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
        this.escolaridad = escolaridad;
        this.estadoCivil = estadoCivil;
        this.habilidades = habilidades;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(String escolaridad) {
        this.escolaridad = escolaridad;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    public boolean isNull(){
        if(nombre.isEmpty()&&usuario.isEmpty()&&password.isEmpty()&&escolaridad.isEmpty()&&estadoCivil.isEmpty()&&habilidades.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", usuario='" + usuario + '\'' +
                ", password='" + password + '\'' +
                ", escolaridad='" + escolaridad + '\'' +
                ", estadoCivil='" + estadoCivil + '\'' +
                ", habilidades='" + habilidades + '\'' +
                '}';
    }
}
