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
    
    public RSACipherService(PublicKey pubKey){
        this.publicKey = pubKey;
        this.privatKey = null;
    }
    
     public RSACipherService(PrivateKey privatKey){
        this.privatKey = privatKey;
        this.publicKey = null;
    }
    
    public byte[] encrypt(byte[] plainText){
        if(publicKey == null) return null;
        
        
        byte[] result = null;
        
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, this.publicKey);
            //byte[] encryptedText = cipher.doFinal(plainText.getBytes());
            byte[] encryptedText = cipher.doFinal(plainText);
            //result = Base64.getEncoder().encodeToString(encryptedText);
            result = encryptedText;
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
    
    public String encrypt(String plainText){
        byte[] encryptedText = encrypt(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedText);
    }
    
    public byte[] decrypt(byte[] exryptedText) {
        if(privatKey == null){
            return null;
        }
        
        byte[] result = null;
        
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, this.privatKey);
            //byte[] decodeEncryptedText = Base64.getDecoder().decode(exryptedText);
            byte[] decodeEncryptedText = exryptedText;
            byte[] decyptedText = cipher.doFinal(decodeEncryptedText);
            //result = new String(decyptedText);
            result =decyptedText;
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
    
    public String decrypt(String exryptedText){
        byte[] decodeEncryptedText = Base64.getDecoder().decode(exryptedText);
        byte[] decryptedText = decrypt(decodeEncryptedText);
        return new String(decryptedText);
    }
            
}
