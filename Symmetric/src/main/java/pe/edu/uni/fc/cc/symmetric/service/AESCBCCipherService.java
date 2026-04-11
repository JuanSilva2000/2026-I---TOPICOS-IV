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
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import static pe.edu.uni.fc.cc.common.Constants.AES_ALGORITHM;
import static pe.edu.uni.fc.cc.common.Constants.AES_CBC_IV_LENGTH;
import static pe.edu.uni.fc.cc.common.Constants.TRANSFORMATION_AES_CBC;
import pe.edu.uni.fc.cc.symmetric.AESCBCCipher;

/**
 *
 * @author JUAN
 */
public class AESCBCCipherService {

    private final byte[] key;

    public AESCBCCipherService(byte[] key) {
        this.key = key;
    }

    public String encrypt(byte[] initVector, String plainText) {
        String result = "";
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector);
            SecretKeySpec sKeySpec = new SecretKeySpec(key, AES_ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION_AES_CBC);
            cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, iv);
            byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            // [IV, "ciphered Text"]
            byte[] combined = new byte[initVector.length + encrypted.length];
            System.arraycopy(initVector, 0, combined, 0, initVector.length);
            System.arraycopy(encrypted, 0, combined, initVector.length, encrypted.length);

            result = Base64.getEncoder().encodeToString(combined);
        } catch (NoSuchAlgorithmException ex) {
            System.getLogger(AESCBCCipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (NoSuchPaddingException ex) {
            System.getLogger(AESCBCCipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (InvalidKeyException ex) {
            System.getLogger(AESCBCCipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            System.getLogger(AESCBCCipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IllegalBlockSizeException ex) {
            System.getLogger(AESCBCCipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (BadPaddingException ex) {
            System.getLogger(AESCBCCipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return result;
    }

    public String decrypt(String encryptedText) {
        String result = "";
        try {
            byte[] combined = Base64.getDecoder().decode(encryptedText);
            byte[] initVector = new byte[AES_CBC_IV_LENGTH];
            byte[] cipherText = new byte[combined.length - initVector.length];
            
            System.arraycopy(combined, 0, initVector, 0, initVector.length);
            System.arraycopy(combined, initVector.length, cipherText, 0,  cipherText.length);
            
            IvParameterSpec iv = new IvParameterSpec(initVector);
            SecretKeySpec sKeySpec = new SecretKeySpec(key, AES_ALGORITHM);
            
            Cipher cipher = Cipher.getInstance(TRANSFORMATION_AES_CBC);
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, iv);
            
            byte[] decrypted = cipher.doFinal(cipherText);
            
            result = new String(decrypted, StandardCharsets.UTF_8);

        } catch (NoSuchAlgorithmException ex) {
            System.getLogger(AESCBCCipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (NoSuchPaddingException ex) {
            System.getLogger(AESCBCCipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (InvalidKeyException ex) {
            System.getLogger(AESCBCCipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            System.getLogger(AESCBCCipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IllegalBlockSizeException ex) {
            System.getLogger(AESCBCCipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (BadPaddingException ex) {
            System.getLogger(AESCBCCipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        return result;
    }
}
