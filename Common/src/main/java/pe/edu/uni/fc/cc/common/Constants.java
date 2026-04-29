/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.uni.fc.cc.common;

/**
 *
 * @author JUAN
 */
public class Constants {
     public static final int ALPHABET_SET_SIZE = 26;
     public static final String AES_ALGORITHM = "AES";
     public static final String RSA_ALGORITHM = "RSA";
     public static final String TRANSFORMATION_AES_CBC = "AES/CBC/PKCS5Padding";
     public static final String TRANSFORMATION_AES_GCM = "AES/GCM/NoPadding";
     public static final String SHA_256_ALGORITHM = "SHA-256";
     public static final String PBKDF2_WITH_HMAC_SHA_256_ALGORITHM = "PBKDF2WithHmacSHA256";
     
     public static final int AES_CBC_IV_LENGTH = 16;
     public static final int AES_GCM_IV_LENGTH = 12;
          public static final int RSA_KEY_SIZE_2048 = 2048;
     
     public static final int TAG_LENGTH = 128;
}
