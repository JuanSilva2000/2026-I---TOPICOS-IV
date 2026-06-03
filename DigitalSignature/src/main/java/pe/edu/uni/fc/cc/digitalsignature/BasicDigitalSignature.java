/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pe.edu.uni.fc.cc.digitalsignature;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import static pe.edu.uni.fc.cc.common.Constants.RSA_ALGORITHM;
import static pe.edu.uni.fc.cc.common.Constants.RSA_KEY_SIZE_2048;

/**
 *
 * @author JUAN
 */
public class BasicDigitalSignature {
    public static void main(String[] args) {
        System.out.println("Basic Digital Signature");
        
        //mensaje a firmar y enviar
        String originalMessage = "Hola soy Juan";
        
        //llaves RSA
        KeyPair kp = null;
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
            kpg.initialize(RSA_KEY_SIZE_2048);
            kp = kpg.generateKeyPair();
        } catch (NoSuchAlgorithmException ex) {
            System.getLogger(BasicDigitalSignature.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        //par de llaves
        PrivateKey privateKey = kp.getPrivate();
        PublicKey publicKey = kp.getPublic();
        
        // visualizar los componentes
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey; //(d,n)
        RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey; // (e,n)
        
        // componentes o parametros de las llaves de la llave privada
        BigInteger d = rsaPrivateKey.getPrivateExponent();
        BigInteger n = rsaPrivateKey.getModulus();
        
        // mensaje como expresión numérica
        BigInteger originaBigIntegerMessage = new BigInteger(1, originalMessage.getBytes());
        
        // FIRMAR MENSAJE
        BigInteger signature = originaBigIntegerMessage.modPow(d, n); // s = x^d mod n
        
        // resultado
        System.out.println("Mensaje original: " + originalMessage);
        System.out.println("Mensaje numetico: " + originaBigIntegerMessage);
        System.out.println("Firma digitial: " + signature);
        System.out.println("Modulo (n): " + n);
        //System.out.println("Modulo (d): " + d);
        
        BigInteger e = rsaPublicKey.getPublicExponent();
        
        // VERIFICACIÓN
        // descifrar con la llave publica
        BigInteger recoveredMessage = signature.modPow(e, n); // x = s^e mod n
        
        // comparacion
        boolean verified = recoveredMessage.equals(originaBigIntegerMessage);
        
        //imprimir
        System.out.println("Mensaje recuperado: " + recoveredMessage);
        System.out.println("Firma valida: " + verified);
        
        
        
    }
}
