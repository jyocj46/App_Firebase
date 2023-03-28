package com.example.firebasechat.Entidades.Logica;

import com.example.firebasechat.Entidades.Firebase.Mensaje;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LMensaje {

    private String key;
    private Mensaje mensaje;
    private Lusuario lusuario;

    public LMensaje(String key, Mensaje mensaje) {
        this.key = key;
        this.mensaje = mensaje;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Mensaje getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensaje mensaje) {
        this.mensaje = mensaje;
    }

    public long getCreatedTimestampLong(){
        return (long) mensaje.getCreatedTimestamp();

    }

    public Lusuario getLusuario() {
        return lusuario;
    }

    public void setLusuario(Lusuario lusuario) {
        this.lusuario = lusuario;
    }

    public String fechaDeCreacionDelMensaje(){

        Date date =  new Date(getCreatedTimestampLong());
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a", Locale.getDefault()); //a pm o am
        return sdf.format(date);
    }

}
