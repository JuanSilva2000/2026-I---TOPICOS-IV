/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.uni.fc.cc.symmetric.exception;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author JUAN
 */
public class CryptoExcepctionHandler {
    public static RuntimeException handle(Exception e) {
        if(e instanceof NoSuchAlgorithmException || e instanceof NoSuchPaddingException) {
            return new CryptoConfigurationException("Error de configuración criptografica", e);
        }
        
        if (e instanceof InvalidKeyException || e instanceof InvalidAlgorithmParameterException) {
            return new CryptoKeyException("Error de configuración criptografica", e);
        }
        
                
        
        return new CryptoException("Error criptografico", e);
    }
}
