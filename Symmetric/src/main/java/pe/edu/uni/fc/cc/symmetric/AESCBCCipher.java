/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.uni.fc.cc.symmetric;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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
import pe.edu.uni.fc.cc.common.Utils;

/**
 *
 * @author JUAN
 */
public class AESCBCCipher {

    public static String encrypt(byte[] key, byte[] initVector, String plainText) {
        String result = "";
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector);
            SecretKeySpec sKeySpec = new SecretKeySpec(key, AES_ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION_AES_CBC);
            cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, iv);
            byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            result = Base64.getEncoder().encodeToString(encrypted);
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

    public static String decrypt(byte[] key, byte[] initVector, String cipheredText) {
        String result = "";
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector);
            SecretKeySpec sKeySpec = new SecretKeySpec(key, AES_ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION_AES_CBC);
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, iv);
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(cipheredText));
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

    public static void main(String[] args) {
        System.out.println("Symetric AES CBC");

        // fuente de aleatoriedad
        SecureRandom sr = new SecureRandom();

        // llave AES
        byte[] key = new byte[16];
        sr.nextBytes(key);
        
        // IV
        /*
        byte[] initVector = new byte[16];
        sr.nextBytes(initVector);
        */
        
        byte[] initVector = Utils.generateIV(AES_CBC_IV_LENGTH);
        
        // Visualizacion
        System.out.println("Key: " + Utils.byteToHex(key));
        System.out.println("IV: " + Utils.byteToHex(initVector));

        String payload = "This is a plaintext sent from Alice to Bob.";
        String encrypted = encrypt(key, initVector, payload);
        String decrypted = decrypt(key, initVector, encrypted);

        System.out.println("Texto original: " + payload);
        System.out.println("Texto cifrado: " + encrypted);
        System.out.println("Texto decifrado: " + decrypted);

        String result = decrypted.equals(payload) ? "OK!" : "KO!";
        System.out.println("Iguales? " + result);

    }
}
