/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.uni.fc.cc.substitution;

import static pe.edu.uni.fc.cc.common.Constants.ALPHABET_SET_SIZE;

/**
 *
 * @author JUAN
 */
public class CeaserCipher {
    
    public static void main(String[] args) {
        
        String original_message = "Juan Silva";
        
        int key = 5;
        
        String encrypt_message = encrypt(original_message, key);
        String decrypt_message = decrypt(encrypt_message, key);
        
        System.out.println("Mensaje Original: " + original_message);
        System.out.println("Mensaje cifrado: " + encrypt_message);
        System.out.println("Mensaje descifrado: " + decrypt_message);
    }
    
    private static String encrypt(String plainText, int key) {
        String result = "";
        
        for(int i=0; i< plainText.length(); i++){
            char c = plainText.charAt(i);
            
            if(Character.isLetter(c)){
                c = (char) (c + key);
                
                if(Character.isLowerCase(plainText.charAt(i)) && c > 'z' || 
                   Character.isUpperCase(plainText.charAt(i)) && c > 'Z'
                ) {
                    c = (char) (c - ALPHABET_SET_SIZE);
                }
            }
            
            result = result + c;
        }
        
        
        return result;
    }
    
    private static String decrypt(String encriptedText, int key){
        String result = "";
        
        for(int i=0; i< encriptedText.length(); i++){
            char c = encriptedText.charAt(i);
            
            if(Character.isLetter(c)){
                c = (char) (c - key);
                
                if(Character.isLowerCase(encriptedText.charAt(i)) && c < 'a' || 
                   Character.isUpperCase(encriptedText.charAt(i)) && c < 'A'
                ) {
                    c = (char) (c + ALPHABET_SET_SIZE);
                }
            }
            
            result = result + c;
        }
        
        return result;
    }
}
