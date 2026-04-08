/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.uni.fc.cc.substitution;

/**
 *
 * @author JUAN
 */
public class SustitutionCipher {

    // Mapa de caracteres
    static char[] charsMap = {
        'q', 
        'w', 
        'e', 
        'r', 
        't', 
        'y', 
        'u', 
        'i', 
        'o', 
        'p', 
        'a', 
        's', 
        'd', 
        'f', 
        'g', 
        'h', 
        'j', 
        'k', 
        'l', 
        'z', 
        'x', 
        'c', 
        'v', 
        'b', 
        'n',
        'm'
    };
    
    private static int indexOfCharsMap(char c){
        int index = -1;
        
        for(int i=0; i<charsMap.length; i++){
            if (c==charsMap[i]){
                index = i;
                break;
            }
        }
        
        return index;
    }

    public static void main(String[] args) {
        System.out.println("Subsistution cipher!!!");
        System.out.println("Length of Map: "+ charsMap.length);
        
        char c = 'g';
        System.out.println("index (" + c + ") = " + indexOfCharsMap(c));
        
        String original_message = "this is a plain text";
        String encrypted_message = encrypt(original_message);
        String decrypted_message = decrypt(encrypted_message);
        
        System.out.println("original: " + original_message);
        System.out.println("encrypted: " + encrypted_message);
        System.out.println("decrypted: " + decrypted_message);
                
        
    }
    
    private static String encrypt(String plainText){
        String result = "";
        int delta = 'Z' - 'z';
//        System.out.println("delta: " + delta);

        for(int i=0; i<plainText.length();i++){
            char c = plainText.charAt(i);
            
            if(Character.isLetter(c)) {
                if(Character.isUpperCase(c)) {
                    c = (char) (c - delta);
                    
                    c = charsMap[c - 'a'];
                    c = (char) (c + delta);
                    
                } else {
                    c = charsMap[c - 'a'];
                }
            }
            result = result + c;
        }
        
        return result;
    }
    
    private static String decrypt(String encryptedText) {
        String result = "";
        
        int delta = 'Z' - 'z';
        for(int i=0; i< encryptedText.length(); i++){
            char c = encryptedText.charAt(i);
            
            if(Character.isLetter(c)) {
                if(Character.isUpperCase(c)) {
                    c = (char) (c - delta);
                    
                    c = (char) ('a' + indexOfCharsMap(c));
                    c = (char) (c  + delta);
                    
                } else {
                    c = (char) ('a' + indexOfCharsMap(c));
                }
            }
            result = result + c;
        }
        
        return result;
    }
    
    
}
