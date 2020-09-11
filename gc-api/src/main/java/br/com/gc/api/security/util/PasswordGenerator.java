package br.com.gc.api.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

    public static void main(String[] args) {
        String p1 = encryptPassword("localmachine");
        System.out.println(p1);
        if(comparePasswords("localmachine", p1)){
            System.out.println("As senhas coincidem!");
        }

    }

    public static String encryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static boolean comparePasswords(String firstPassword, String secondPassword){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(firstPassword, secondPassword);
    }
}
