package com.example.firebasechat.Entidades.Logica;

import com.example.firebasechat.Entidades.Firebase.Usuario;
import com.google.firebase.database.Exclude;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Lusuario {
    
    private String key;
    private Usuario usuario;

    public Lusuario(String key, Usuario usuario) {
        this.key = key;
        this.usuario = usuario;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public long getCreatedTimestapLong(){
        return (long) usuario.getCreatedTimestap();
    }

    public String obtenerFechaDeCreacion(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date = new Date(getCreatedTimestapLong());
        return simpleDateFormat.format(date);
    }

}
