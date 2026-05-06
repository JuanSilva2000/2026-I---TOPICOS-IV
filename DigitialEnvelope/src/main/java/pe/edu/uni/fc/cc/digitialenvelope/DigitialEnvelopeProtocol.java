/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pe.edu.uni.fc.cc.digitialenvelope;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import static pe.edu.uni.fc.cc.common.Constants.RSA_ALGORITHM;
import static pe.edu.uni.fc.cc.common.Constants.RSA_KEY_SIZE_2048;
import pe.edu.uni.fc.cc.common.Utils;

/**
 *
 * @author JUAN
 */
public class DigitialEnvelopeProtocol {

    public static void main(String[] args) {
        System.out.println("DigitialEnvelopeProtocol");
        try {
            // Las llaves RSA del receptor
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
            kpg.initialize(RSA_KEY_SIZE_2048);
            KeyPair kp = kpg.genKeyPair();
            
            // el mensaje a ser enviado
            String secret_message = "Este es un mensaje confidencial  desde el emisor";
            
            // El emisor preparar el paquete
            DEPSender sender = new DEPSender();
            sender.prepareForShipping(secret_message, kp.getPublic());
            
            // Mostrar lo que viaja en la red
            System.out.println("Digital envelope  : " + Utils.byteToHex(sender.getDigitalEnvelope()));
            System.out.println("Mensaje cifrado   : " + Utils.byteToHex(sender.getCipheredMessage()));
            
            // El receiver procesa los datos recibidos
            DEPReceiver receiver = new DEPReceiver();
            String recovered_message = receiver.processShooedPayload(sender.getCipheredMessage(), sender.getDigitalEnvelope(), kp.getPrivate());
            
            // mostramos el mensaje original y el recuperado
            System.out.println("Mensaje secreto     : " + secret_message);
            System.out.println("Mensaje recuperado  : " + recovered_message);
            
        } catch (NoSuchAlgorithmException ex) {
            System.getLogger(DigitialEnvelopeProtocol.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
}
