package com.example.firebasechat.Persistencia;

import com.google.firebase.auth.FirebaseAuth;

public class UsuarioDAO {

    public static String getKeyUsuario(){
        return FirebaseAuth.getInstance().getUid();
    }

}
