/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.uni.fc.cc.substitution.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

/**
 *
 * @author JUAN
 */
public class AffineCipherServiceTest {
    private final AfineCipherService cipher = new AfineCipherService(11,6);
    
    @Test
    public void testEncryptDecryptIdentity() {
        String input = "Hello World";
        String encrypted = cipher.encrypt(input);
        String decrypted = cipher.decrypt(encrypted);
        assertEquals(input, decrypted);
    }
    
    @Test
    public void testInvalidKey(){
        assertThrows(IllegalArgumentException.class, ()-> {
            new AfineCipherService(13,5);
        });
    }
    
    // hacer mas tests incluyendo los de Utils
    
}
