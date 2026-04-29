/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.uni.fc.cc.asymmetric.Service;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import static pe.edu.uni.fc.cc.common.Constants.RSA_ALGORITHM;

/**
 *
 * @author JUAN
 */
public class RSACipherService {
    
    private final PublicKey publicKey;
    private final PrivateKey privatKey;
    
    public RSACipherService(KeyPair kp){
        this.publicKey = kp.getPublic();
        this.privatKey = kp.getPrivate();
    }
    
    public String encrypt(String plainText){
        String result = "";
        
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, this.publicKey);
            byte[] encryptedText = cipher.doFinal(plainText.getBytes());
            result = Base64.getEncoder().encodeToString(encryptedText);
        } catch (NoSuchAlgorithmException ex) {
            System.getLogger(RSACipherService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (NoSuchPaddingException ex) {
            System.getLogger(RSACipherService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (InvalidKeyException ex) {
            System.getLogger(RSACipherService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IllegalBlockSizeException ex) {
            System.getLogger(RSACipherService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (BadPaddingException ex) {
            System.getLogger(RSACipherService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return result;
    }
    
    public String decrypt(String exryptedText) {
        String result = "";
        
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, this.privatKey);
            byte[] decodeEncryptedText = Base64.getDecoder().decode(exryptedText);
            byte[] decyptedText = cipher.doFinal(decodeEncryptedText);
            result = new String(decyptedText);
        } catch (NoSuchAlgorithmException ex) {
            System.getLogger(RSACipherService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (NoSuchPaddingException ex) {
            System.getLogger(RSACipherService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (InvalidKeyException ex) {
            System.getLogger(RSACipherService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IllegalBlockSizeException ex) {
            System.getLogger(RSACipherService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (BadPaddingException ex) {
            System.getLogger(RSACipherService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return result;
    }
}
