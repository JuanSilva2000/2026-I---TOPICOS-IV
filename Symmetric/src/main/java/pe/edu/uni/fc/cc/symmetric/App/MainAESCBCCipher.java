/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.uni.fc.cc.symmetric.App;

import java.security.SecureRandom;
import static pe.edu.uni.fc.cc.common.Constants.AES_CBC_IV_LENGTH;
import pe.edu.uni.fc.cc.common.Utils;
import static pe.edu.uni.fc.cc.symmetric.AESCBCCipher.decrypt;
import static pe.edu.uni.fc.cc.symmetric.AESCBCCipher.encrypt;
import pe.edu.uni.fc.cc.symmetric.service.AESCBCCipherService;

/**
 *
 * @author JUAN
 */
public class MainAESCBCCipher {
    public static void main(String[] args) {
        System.out.println("Main Symetric AES CBC");

        // fuente de aleatoriedad
        SecureRandom sr = new SecureRandom();

        // llave AES
        byte[] key = new byte[16]; // 128 bits
        sr.nextBytes(key);
        
        // IV
        /*
        byte[] initVector = new byte[16];
        sr.nextBytes(initVector);
        */
        
        byte[] initVector = Utils.generateIV(AES_CBC_IV_LENGTH);
        
        // Visualizacion
        System.out.println("Key: " + Utils.byteToHex(key));
        System.out.println("IV: " + Utils.byteToHex(initVector));
        
        AESCBCCipherService cipher = new AESCBCCipherService(key);
        
        
        String payload = "This is a plaintext sent from Alice to Bob.";
        String encrypted = cipher.encrypt(initVector, payload);
        String decrypted = cipher.decrypt(encrypted);

        System.out.println("Texto original: " + payload);
        System.out.println("Texto cifrado: " + encrypted);
        System.out.println("Texto decifrado: " + decrypted);

        String result = decrypted.equals(payload) ? "OK!" : "KO!";
        System.out.println("Iguales? " + result);

    }
}
