/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.uni.fc.cc.substitution.service;

//import static pe.edu.uni.fc.cc.substitution.Constants.ALPHABET_SET_SIZE;

import static pe.edu.uni.fc.cc.common.Constants.ALPHABET_SET_SIZE;


/**
 *
 * @author JUAN
 */
public class CeaserCipherService {
    private final int key;
    
    public CeaserCipherService(int key){
        // this.key= key; qué pasa si key > 26?, mejor:
        this.key = key%ALPHABET_SET_SIZE;
    }
    
    public String encrypt(String plainText) {
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
    
    public String decrypt(String encriptedText){
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
