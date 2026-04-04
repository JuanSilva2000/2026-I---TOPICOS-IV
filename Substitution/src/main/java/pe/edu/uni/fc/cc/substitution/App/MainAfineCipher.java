/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.uni.fc.cc.substitution.App;

import pe.edu.uni.fc.cc.substitution.service.AfineCipherService;

/**
 *
 * @author JUAN
 */
public class MainAfineCipher {
    public static void main(String[] args) throws IllegalAccessException {
        int a = 11;
        int b= 6;
        
        AfineCipherService affine = new AfineCipherService(a,b);
        String original = "Juan Silva";
        
        String encrypted = affine.encrypt(original);
        String decrypted = affine.decrypt(encrypted);
        
        System.out.println("Original text: " + original);
        System.out.println("Encrypted text: " + encrypted);
        System.out.println("Decrypted text: " + decrypted);
    }
}
