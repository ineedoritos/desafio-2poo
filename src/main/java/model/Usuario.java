package model;

import at.favre.lib.crypto.bcrypt.BCrypt;
//se usan 2 constructores o incluso mas, dependiendo de como lo hayamos querido hacer en otras partes
// el constructor vacio es para poder instanciar un objeto vacio y luego poder setearle algun atributo
//igualmente el IDE se toma ese trabajo por si no lo dejamos vacio
public class Usuario {
    private static int idCounter = 0;
    private int id;
    private String email;
    private String password;

    public Usuario() {
    }

    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
        this.id = idCounter++;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }


    public static String hashear(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public static boolean verificar(String intento, String hashed) {
        return BCrypt.verifyer().verify(intento.toCharArray(), hashed).verified;
    }


}

