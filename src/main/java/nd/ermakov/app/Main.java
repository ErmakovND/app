package nd.ermakov.app;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Main {
    public static void main(String[] args) {
        PasswordEncoder pe = new BCryptPasswordEncoder();
        String enc = pe.encode("pupa");
        System.out.println(enc);
    }
}
