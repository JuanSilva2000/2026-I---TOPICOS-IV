/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.uni.fc.cc.substitution.service;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author JUAN
 */
public class SustitutionCipherServiceTest {
     static char[] key = {
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
     
    SustitutionCipherService cipher = new SustitutionCipherService(key);
    
    @Test
    public void testEncryptDecyptIndentity() {
       String input = "Hello World"; 
       String encrypted = cipher.encrypt(input);
       String decrypted = cipher.decrypt(encrypted);
       
       assertEquals(input, decrypted);
       
    }    
}
