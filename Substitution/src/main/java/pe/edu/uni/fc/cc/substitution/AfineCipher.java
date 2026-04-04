/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.uni.fc.cc.substitution;

import static pe.edu.uni.fc.cc.common.Constants.ALPHABET_SET_SIZE;
import pe.edu.uni.fc.cc.common.Utils;

/**
 *
 * @author JUAN
 */
public class AfineCipher {
    
    public static String encrypt(String plainText, int a, int b) {
        String result = "";
        for(int i=0;i<plainText.length();i++){
            char c = plainText.charAt(i);
            if(Character.isLetter(c)){
                char offset = Character.isUpperCase(c)? 'A': 'a';
                // x = c-offset
                c = (char) (((a*(c-offset) + b)%ALPHABET_SET_SIZE) + offset);
            }
            result = result + c;
        }
        return result;
    }
    
    public static String decrypt(String encryptTest, int a, int b) {
        String result = "";
        
        int inv_a = Utils.getInverseModule(a, ALPHABET_SET_SIZE);
        if(inv_a == 0) return result;
        
        for(int i=0;i<encryptTest.length();i++){
            char c = encryptTest.charAt(i);
            if(Character.isLetter(c)){
                char offset = Character.isUpperCase(c)? 'A': 'a';
                // x = c-offset
                int tmp = inv_a*(c-offset-b + ALPHABET_SET_SIZE);
                c=(char) (tmp%ALPHABET_SET_SIZE+offset);
            }
            
            result = result + c;
        }
        
        
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println("Affine Cipher");
        
        String original = "Juan Silva!";
        
        //key=(a,b)
        int a=11;
        int b=6;
        
        String encrypted = encrypt(original,a,b);
        String decrypted = decrypt(encrypted,a,b);
        
        System.out.println("Original: " + original);
        System.out.println("Encriptado: " + encrypted);
        System.out.println("Desencriptado: " + decrypted);
    }
    
    
}
