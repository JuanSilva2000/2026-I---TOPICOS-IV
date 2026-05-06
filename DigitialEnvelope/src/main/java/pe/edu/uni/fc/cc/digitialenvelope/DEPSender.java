/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.uni.fc.cc.digitialenvelope;

import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import pe.edu.uni.fc.cc.asymmetric.RSACipher;
import pe.edu.uni.fc.cc.asymmetric.Service.RSACipherService;
import static pe.edu.uni.fc.cc.common.Constants.AES_ALGORITHM;
import static pe.edu.uni.fc.cc.common.Constants.AES_GCM_ADD;
import static pe.edu.uni.fc.cc.common.Constants.AES_GCM_IV_LENGTH;
import pe.edu.uni.fc.cc.common.Utils;
import pe.edu.uni.fc.cc.symmetric.service.AESGCMCipherService;

/**
 *
 * @author JUAN
 */
public class DEPSender {
//    private byte[] iv;
    private byte[] cipheredMessage;
    private byte[] digitalEnvelope;

//    public byte[] getIv() {
//        return iv;
//    }

    public byte[] getCipheredMessage() {
        return cipheredMessage;
    }

    public byte[] getDigitalEnvelope() {
        return digitalEnvelope;
    }
    
    public void prepareForShipping(String message, PublicKey pubKey){
        try {
            //Generar llave simetrica
            KeyGenerator kg = KeyGenerator.getInstance(AES_ALGORITHM);
            kg.init(256);
            SecretKey aesKey = kg.generateKey();
            
            // cifrar el mensaje con la llave AES GCM
            AESGCMCipherService aes_gcm_cipher = new AESGCMCipherService(aesKey.getEncoded());
            
            //parametros
            byte[] iv = Utils.generateIV(AES_GCM_IV_LENGTH);
            cipheredMessage = aes_gcm_cipher.encrypt(message.getBytes(), iv, AES_GCM_ADD);
            
            //crear el sobre digital
            RSACipherService rsa_cipher = new RSACipherService(pubKey);
            digitalEnvelope = rsa_cipher.encrypt(aesKey.getEncoded());
            
            System.out.println("Sender: Mensaje cifrado y sobre digitial sellado");
            
        } catch (NoSuchAlgorithmException ex) {
            System.getLogger(DEPSender.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
}
