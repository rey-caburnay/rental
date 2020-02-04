package com.shinn.configuration;

import org.springframework.security.crypto.bcrypt.BCrypt;


public class Encrypt {

       public static String encrypt(String plain) {
           return BCrypt.hashpw(plain,BCrypt.gensalt());
       }

       public static boolean checkPassword(String plain, String hash) {
           return BCrypt.checkpw(plain,hash);
       }
}
