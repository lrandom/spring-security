package com.example.demospringsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

public class PasswordEncoderTest {

    @Test
    public void bcrypt() {
        System.out.println(new BCryptPasswordEncoder().encode("123456789"));
    }

    @Test
    public void noop() {
       System.out.println(NoOpPasswordEncoder.getInstance().encode("12345678"));
    }

    @Test
    public void sh256() {
        System.out.println(new StandardPasswordEncoder().encode("123434374"));
    }

    @Test
    public void scrypt() {
       // System.out.println( new SCryptPasswordEncoder.encode("12345678"));
    }
}
