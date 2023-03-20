package com.example.firebasechat.Entidades;

public class Usuario {
    private String nombre;
    private String correo;

    public Usuario() {
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
