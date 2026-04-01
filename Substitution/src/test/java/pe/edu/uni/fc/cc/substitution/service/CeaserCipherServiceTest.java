/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.uni.fc.cc.substitution.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
/**
 *
 * @author JUAN
 */
public class CeaserCipherServiceTest {
    private final CeaserCipherService cipher = new CeaserCipherService(5);
    
    @Test
    public void testEncryptBasic() {
        String input = "abc";
        String expected = "fgh";
        
        assertEquals(expected, cipher.encrypt(input));
    }
    
    @Test
    public void testDecryptBasic() {
        String input = "fgh";
        String expected = "abc";
        
        assertEquals(expected, cipher.decrypt(input));
    }
    
    @Test
    public void testEncryptDecryptIdentity() {
        String input = "Hello Word!";
        
        String encrypted = cipher.encrypt((input));
        String decrypted = cipher.decrypt(encrypted);
        
        assertEquals(input,decrypted);
    }
    
    @Test
    public void testWrapAround(){
        String input = "xyz";
        String expected = "cde";
        
        assertEquals(expected,cipher.encrypt(input));
    }
    
    @Test
    public void testNonLetters(){
        String input = "Hello world !!! 123.";
        String encrypted = cipher.encrypt(input);
        
        assertTrue(encrypted.contains("!"));
        assertTrue(encrypted.contains("."));
        assertTrue(encrypted.contains("123"));
        assertFalse(encrypted.contains("?"));
    }
    
    @Test
    public void TestUpperCase(){
        String input = "ABC";
        String expected = "FGH";
        
        assertEquals(expected, cipher.encrypt(input));
    }
}
