/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.uni.fc.cc.asymmetric.App;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import pe.edu.uni.fc.cc.asymmetric.Service.RSACipherService;
import static pe.edu.uni.fc.cc.common.Constants.RSA_ALGORITHM;
import static pe.edu.uni.fc.cc.common.Constants.RSA_KEY_SIZE_2048;

/**
 *
 * @author JUAN
 */
public class MainRSACipher {
    public static void main(String[] args) {
        System.out.println("MainRSACipher");
        KeyPair kp = null;
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
            kpg.initialize(RSA_KEY_SIZE_2048);
            kp = kpg.genKeyPair();
            System.out.println("Generated RSA key pair!!!");
            
        } catch (NoSuchAlgorithmException ex) {
            System.getLogger(MainRSACipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        RSACipherService cipher = new RSACipherService(kp);
        String message = "This is a message with RSA algorithm";
        String encryptedText = cipher.encrypt(message);
        String decryptedText = cipher.decrypt(encryptedText);
            
        System.out.println("Original Text: " + message);
        System.out.println("Encrypted text: " + encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
        
    }
}
