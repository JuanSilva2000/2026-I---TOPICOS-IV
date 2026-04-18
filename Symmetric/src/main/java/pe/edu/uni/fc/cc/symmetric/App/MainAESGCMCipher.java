/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.uni.fc.cc.symmetric.App;

import java.security.SecureRandom;
import static pe.edu.uni.fc.cc.common.Constants.AES_GCM_IV_LENGTH;
import pe.edu.uni.fc.cc.common.Utils;
import pe.edu.uni.fc.cc.symmetric.service.AESGCMCipherService;

/**
 *
 * @author JUAN
 */
public class MainAESGCMCipher {
    public static void main(String[] args) {
        System.out.println("MainAESGCMCipher!!");
        
        SecureRandom sr = new SecureRandom();
        
        byte[] key = new byte[16];
        sr.nextBytes(key);
        
        AESGCMCipherService aes_gcm = new AESGCMCipherService(key);
        
        byte[] iv = Utils.generateIV(AES_GCM_IV_LENGTH);
        byte[] add = "Header".getBytes();
        
        String msg = "This is a message";
        
        String encrypted = aes_gcm.encrypt(msg, iv,add);
        String decrypted = aes_gcm.decrypt(encrypted, add);
        
        System.out.println("Original: " + msg);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
        
    }
}
