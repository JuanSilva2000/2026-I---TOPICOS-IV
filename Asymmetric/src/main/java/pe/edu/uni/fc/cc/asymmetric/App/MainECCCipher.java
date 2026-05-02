/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.uni.fc.cc.asymmetric.App;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.ECGenParameterSpec;
import pe.edu.uni.fc.cc.asymmetric.Service.ECCCipherService;
import static pe.edu.uni.fc.cc.common.Constants.ECC_ALGORITHM;
import static pe.edu.uni.fc.cc.common.Constants.ECC_KEY_PARAM;

/**
 *
 * @author JUAN
 */
public class MainECCCipher {

    public static void main(String[] args) {
        System.out.println("Main ECC CIPHER");
        KeyPair kp = null;

        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(ECC_ALGORITHM);
            kpg.initialize(new ECGenParameterSpec(ECC_KEY_PARAM));
            kp = kpg.generateKeyPair();

        } catch (NoSuchAlgorithmException ex) {
            System.getLogger(MainECCCipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            System.getLogger(MainECCCipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        ECCCipherService cipher = new ECCCipherService(kp);
        String message = "This is a meesage signed with ECC service";
        String signedText = cipher.sign(message);
        boolean isVerified = cipher.verify(signedText, message);

        System.out.println("Mensaje Oirignal: " + message);
        System.out.println("Mensaje firmado: " + signedText);
        System.out.println("Esta verificado: " + isVerified);

    }
}
