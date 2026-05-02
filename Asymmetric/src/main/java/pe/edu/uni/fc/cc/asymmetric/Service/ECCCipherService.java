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
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;
import pe.edu.uni.fc.cc.asymmetric.ECCCipher;
import static pe.edu.uni.fc.cc.common.Constants.ECC_SIGN_ALGORITHM;

/**
 *
 * @author JUAN
 */
public class ECCCipherService {

    PublicKey publicKey;
    PrivateKey privateKey;

    public ECCCipherService(KeyPair keyPair) {
        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();
    }

    public String sign(String plainText) {
        String result = "";

        try {
            Signature signature = Signature.getInstance(ECC_SIGN_ALGORITHM);
            signature.initSign(privateKey);
            signature.update(plainText.getBytes());

            byte[] signed = signature.sign();

            result = Base64.getEncoder().encodeToString(signed);
        } catch (NoSuchAlgorithmException ex) {
            System.getLogger(ECCCipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (InvalidKeyException ex) {
            System.getLogger(ECCCipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (SignatureException ex) {
            System.getLogger(ECCCipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        return result;
    }

    public boolean verify(String signedText, String plainText) {
        boolean result = false;
        
         try {
                Signature signature = Signature.getInstance(ECC_SIGN_ALGORITHM);
                signature.initVerify(publicKey);
                signature.update(plainText.getBytes());
                
                result = signature.verify(Base64.getDecoder().decode(signedText));
            } catch (NoSuchAlgorithmException ex) {
                System.getLogger(ECCCipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            } catch (InvalidKeyException ex) {
                System.getLogger(ECCCipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            } catch (SignatureException ex) {
            System.getLogger(ECCCipherService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return result;
    }

}
