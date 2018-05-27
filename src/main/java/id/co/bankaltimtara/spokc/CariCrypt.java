package id.co.bankaltimtara.spokc;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CariCrypt {

    public static void main(String[] args) {
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        System.out.println(encode.encode("admin1234"));
    }

}
