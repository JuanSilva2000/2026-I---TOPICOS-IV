/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.uni.fc.cc.symmetric.service;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import static pe.edu.uni.fc.cc.common.Constants.AES_ALGORITHM;
import static pe.edu.uni.fc.cc.common.Constants.AES_GCM_IV_LENGTH;
import static pe.edu.uni.fc.cc.common.Constants.TAG_LENGTH;
import static pe.edu.uni.fc.cc.common.Constants.TRANSFORMATION_AES_GCM;

/**
 *
 * @author JUAN
 */
public class AESGCMCipherService {
    private final byte[] key;
    
    public AESGCMCipherService(byte[] key) {
        this.key = key;
    }
    
    public byte[] encrypt(byte[] plainText, byte[] IV, byte[] aad) {
        byte[] result = null;
        
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION_AES_GCM);
            SecretKeySpec keySpec = new SecretKeySpec(key, AES_ALGORITHM);
            GCMParameterSpec paramSpec = new GCMParameterSpec(TAG_LENGTH, IV);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, paramSpec);
            
            if(aad != null){
                cipher.updateAAD(aad);
            }
            
            //byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            byte[] encrypted = cipher.doFinal(plainText);
            
            byte[] ciphered = new byte[IV.length + encrypted.length];
            System.arraycopy(IV, 0, ciphered, 0, IV.length);
            System.arraycopy(encrypted, 0, ciphered, IV.length, encrypted.length);
            
            //result = Base64.getEncoder().encodeToString(ciphered);
            result = ciphered;  
            
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException ex) {
            System.getLogger(AESGCMCipherService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return result;
    }
    
    public String encrypt(String plainText, byte[] iv, byte[] aad) {
        byte[] ciphered= encrypt(plainText.getBytes(StandardCharsets.UTF_8), iv, aad);
        return Base64.getEncoder().encodeToString(ciphered);
    }
    
    public byte[] decrypt(byte[] encryptedText, byte[] add){
        byte[] result = null;
        
        //byte[] input = Base64.getDecoder().decode(encryptedText);
        byte[] input = encryptedText;
        byte[] IV = new byte[AES_GCM_IV_LENGTH];
        byte[] cipherText = new byte[input.length - AES_GCM_IV_LENGTH];
        
        System.arraycopy(input, 0, IV, 0, IV.length);
        System.arraycopy(input, IV.length, cipherText, 0,  cipherText.length);
        
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION_AES_GCM);
            SecretKeySpec sKeySpec = new SecretKeySpec(key, AES_ALGORITHM);
            GCMParameterSpec paramSpec = new GCMParameterSpec(TAG_LENGTH, IV);
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, paramSpec);
            
            if(add !=null){
                cipher.updateAAD(add);
            }
            
            byte[] decrypted = cipher.doFinal(cipherText);
            //result = new String(decrypted, StandardCharsets.UTF_8);
            result = decrypted;
            
            
        } catch (NoSuchAlgorithmException ex) {
            System.getLogger(AESGCMCipherService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (NoSuchPaddingException ex) {
            System.getLogger(AESGCMCipherService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (InvalidKeyException ex) {
            System.getLogger(AESGCMCipherService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            System.getLogger(AESGCMCipherService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IllegalBlockSizeException ex) {
            System.getLogger(AESGCMCipherService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (BadPaddingException ex) {
            System.getLogger(AESGCMCipherService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return result;
    }

    public String decrypt(String encryptedText, byte[] add){
        String result = "";
        byte[] input = Base64.getDecoder().decode(encryptedText);
        byte[] decrypted = decrypt(input, add);
        result = new String(decrypted, StandardCharsets.UTF_8);
        return result;
    }
}
