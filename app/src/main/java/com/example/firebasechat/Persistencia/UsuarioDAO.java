package com.example.firebasechat.Persistencia;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.firebasechat.Entidades.Firebase.Usuario;
import com.example.firebasechat.Entidades.Logica.Lusuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Utilidades.Constantes;

public class UsuarioDAO {

    public interface IDevolverUrlFoto{
        public void devolverUrlString(String url);
    }


    private static UsuarioDAO usuarioDAO;
    private FirebaseDatabase database;
    private DatabaseReference referenceUsuarios;
    private FirebaseStorage storage;
    private DatabaseReference getReferenceUsuarios;
    private StorageReference referenceFotoDePerfil;

    public static UsuarioDAO getInstance(){
        if (usuarioDAO==null) usuarioDAO = new UsuarioDAO();
        return usuarioDAO;
    }

    private UsuarioDAO(){
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        referenceUsuarios = database.getReference(Constantes.NODO_USUARIOS);
        referenceFotoDePerfil = storage.getReference("Fotos/FotoPerfil/"+getKeyUsuario());
    }

    public String getKeyUsuario(){
        return FirebaseAuth.getInstance().getUid();
    }

    public long fechaDeCreacionLong(){
        return FirebaseAuth.getInstance().getCurrentUser().getMetadata().getCreationTimestamp();
    }

    public long fechaDeUltimaVezQueSeLogeoLong(){
        return FirebaseAuth.getInstance().getCurrentUser().getMetadata().getLastSignInTimestamp();
    }
//llamar al metodo con mucho cuidado ya que el metodo tiene demasiada informacion.
    public void anadirFotoDePerfilALosUsuariosQueNoTienenFoto(){
        referenceUsuarios.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Lusuario> lusuariosLista = new ArrayList<>();
                for (DataSnapshot childDataSnapShot : dataSnapshot.getChildren()){
                    Usuario usuario = childDataSnapShot.getValue(Usuario.class);
                    Lusuario lusuario = new Lusuario(childDataSnapShot.getKey(),usuario);
                    lusuariosLista.add(lusuario);
                }

                for (Lusuario lusuario : lusuariosLista){
                    if (lusuario.getUsuario().getFotoPerfilURL()==null){
                        referenceUsuarios.child(lusuario.getKey()).child("fotoPerfilURL").setValue(Constantes.URL_FOTO_POR_DEFECTO_USUARIOS);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void subirFotoUri(Uri uri, IDevolverUrlFoto iDevolverUrlFoto){
        String nombreFoto = "";
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("SSS.ss-mm-hh-dd--MM-yyyy", Locale.getDefault());
        nombreFoto = simpleDateFormat.format(date);
        final StorageReference fotoReferencia = referenceFotoDePerfil.child(nombreFoto);
        fotoReferencia.putFile(uri).continueWithTask((task) -> {
            if (!task.isSuccessful()){
                throw task.getException();
            }
            return fotoReferencia.getDownloadUrl();
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri uri = task.getResult();
                    iDevolverUrlFoto.devolverUrlString(uri.toString());
                }
            }
        });
    }

}
