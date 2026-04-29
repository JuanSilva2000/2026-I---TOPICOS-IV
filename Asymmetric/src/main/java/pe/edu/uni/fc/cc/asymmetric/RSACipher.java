/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pe.edu.uni.fc.cc.asymmetric;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import static pe.edu.uni.fc.cc.common.Constants.RSA_ALGORITHM;
import static pe.edu.uni.fc.cc.common.Constants.RSA_KEY_SIZE_2048;

/**
 *
 * @author JUAN
 */
public class RSACipher {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
            kpg.initialize(RSA_KEY_SIZE_2048);
            KeyPair kp = kpg.genKeyPair();
            PublicKey pubKey = kp.getPublic();
            PrivateKey priKey = kp.getPrivate();
            System.out.println("Key pair generated!");
            
            String message = "This is a message with RSA algorithm";
            String encryptedText = encrypt(pubKey,message);
            String decryptedText = decrypt(priKey, encryptedText);
            
            System.out.println("Original Text: " + message);
            System.out.println("Encrypted text: " + encryptedText);
            System.out.println("Decrypted Text: " + decryptedText);
        } catch (NoSuchAlgorithmException ex) {
            System.getLogger(RSACipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    private static String encrypt(PublicKey pubKey, String plainText){
        String result = "";
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            // el par de llaves son numeros ergo cómo puedo decir yo que esas llaves les pertenece a alguien
            // Rspta: con el certificado 
            // Es el elmento que se crea que tinee un conjunto de datos que sirve para atestiguar de quién es la llave públlica
            cipher.init(Cipher.ENCRYPT_MODE, pubKey); 
            byte[] encryptedText = cipher.doFinal(plainText.getBytes());
            result = Base64.getEncoder().encodeToString(encryptedText);
            
        } catch (NoSuchAlgorithmException ex) {
            System.getLogger(RSACipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (NoSuchPaddingException ex) {
            System.getLogger(RSACipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (InvalidKeyException ex) {
            System.getLogger(RSACipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IllegalBlockSizeException ex) {
            System.getLogger(RSACipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (BadPaddingException ex) {
            System.getLogger(RSACipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return result;
    }

    private static String decrypt(PrivateKey priKey, String exryptedText) {
        String result = "";
        
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            byte[] decodeEncryptedText = Base64.getDecoder().decode(exryptedText);
            byte[] decyptedText = cipher.doFinal(decodeEncryptedText);
            result = new String(decyptedText);
          
        } catch (NoSuchAlgorithmException ex) {
            System.getLogger(RSACipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (NoSuchPaddingException ex) {
            System.getLogger(RSACipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (InvalidKeyException ex) {
            System.getLogger(RSACipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IllegalBlockSizeException ex) {
            System.getLogger(RSACipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (BadPaddingException ex) {
            System.getLogger(RSACipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return result;
    }
}

