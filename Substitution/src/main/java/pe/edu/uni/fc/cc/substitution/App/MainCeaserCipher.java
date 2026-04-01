/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.uni.fc.cc.substitution.App;

import pe.edu.uni.fc.cc.substitution.service.CeaserCipherService;

/**
 *
 * @author JUAN
 */
public class MainCeaserCipher {
    public static void main(String[] args) {
        int key = 5;
        
        CeaserCipherService cipher = new CeaserCipherService(key);
        String original = "xyz";
        
        String encrypted = cipher.encrypt(original);
        String decrypted = cipher.decrypt(encrypted);
        
        System.out.println("Original text: " + original);
        System.out.println("Encrypted text: " + encrypted);
        System.out.println("Decrypted text: " + decrypted);
    }
}
