package mocks;

import jakarta.validation.constraints.Email;
import model.Usuario;

import java.util.ArrayList;
import java.util.Optional;

public class UsuarioDB {

    public static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

    //metodo para agregar usuario
    public static void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public static Usuario buscarUsuario(String email) {
        Optional<Usuario> usuario = usuarios.stream().filter(usuario1 -> usuario1.getEmail().equals(email)).findFirst();
        return usuario.orElse(null);
    }

    public static void mostrarUsuarios(){
        System.out.println(usuarios);

    }

    public static String ObtenerPwdUsuario(String email) {
        Optional<Usuario> usuario = usuarios.stream().filter(usuario1 -> usuario1.getPassword().equals(email)).findFirst();
        return usuario.map(Usuario::getPassword).orElse(null);
    }


}
