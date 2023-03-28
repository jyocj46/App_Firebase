package com.example.firebasechat.Entidades.Firebase;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.ServerValue;

public class Usuario {
    private String fotoPerfilURL;
    private String nombre;
    private String correo;

    public Usuario() {

    }
    public String getFotoPerfilURL() {
        return fotoPerfilURL;
    }

    public void setFotoPerfilURL(String fotoPerfilURL) {
        this.fotoPerfilURL = fotoPerfilURL;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nomnbre) {
        this.nombre = nomnbre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }



}
