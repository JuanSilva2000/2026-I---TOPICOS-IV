/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.uni.fc.cc.hashing;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import static pe.edu.uni.fc.cc.common.Constants.PBKDF2_WITH_HMAC_SHA_256_ALGORITHM;
import pe.edu.uni.fc.cc.common.Utils;

/**
 *
 * @author JUAN
 */
public class HashingWithSalt {
    public static void main(String[] args) {
        System.out.println("HashingWithSalt!!!");
        final String password = "12345"; // baja entropía
        final String salt = "user@example.com";
        final int interactions = 32;
        final int keySize = 512;
        
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray(),salt.getBytes(), interactions, keySize);
        
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_WITH_HMAC_SHA_256_ALGORITHM);
            
            byte[] hash = skf.generateSecret(keySpec).getEncoded();
            System.out.println("El valor SHA-256 con salt y con PBKDF es: " + Utils.byteToHex(hash));
        } catch (NoSuchAlgorithmException ex) {
            System.getLogger(HashingWithSalt.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (InvalidKeySpecException ex) {
            System.getLogger(HashingWithSalt.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
