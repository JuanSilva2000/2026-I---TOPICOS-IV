    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package pe.edu.uni.fc.cc.asymmetric;

    import java.security.InvalidAlgorithmParameterException;
    import java.security.InvalidKeyException;
    import java.security.KeyPair;
    import java.security.KeyPairGenerator;
    import java.security.NoSuchAlgorithmException;
    import java.security.PrivateKey;
    import java.security.PublicKey;
    import java.security.Signature;
import java.security.SignatureException;
    import java.security.spec.ECGenParameterSpec;
import java.util.Base64;
    import static pe.edu.uni.fc.cc.common.Constants.ECC_ALGORITHM;
    import static pe.edu.uni.fc.cc.common.Constants.ECC_KEY_PARAM;
    import static pe.edu.uni.fc.cc.common.Constants.ECC_SIGN_ALGORITHM;

    /**
     *
     * @author JUAN
     */
    public class ECCCipher {
        public static void main(String[] args) {
            System.out.println("ECCCIPHER!");
            try {    
                KeyPairGenerator kpg = KeyPairGenerator.getInstance(ECC_ALGORITHM);
                kpg.initialize(new ECGenParameterSpec(ECC_KEY_PARAM));
                KeyPair kp = kpg.generateKeyPair();
                PublicKey publicKey = kp.getPublic();
                PrivateKey privateKey = kp.getPrivate();
                
                String message = "This is a meesage signed with ECC algoritm";
                String signedText = sign(privateKey,message);
                boolean isVerified = verify(publicKey,signedText,message);
                
                System.out.println("Mensaje Oirignal: " +message);
                System.out.println("Mensaje firmado: " + signedText );
                System.out.println("Esta verificado: " + isVerified);

            } catch (NoSuchAlgorithmException ex) {
                System.getLogger(ECCCipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            } catch (InvalidAlgorithmParameterException ex) {
                System.getLogger(ECCCipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            } catch (SignatureException ex) {
                System.getLogger(ECCCipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }

        private static String sign(PrivateKey priKey, String plainText){
            String result = "";
            
            try {
                Signature signature = Signature.getInstance(ECC_SIGN_ALGORITHM);
                signature.initSign(priKey);
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

        private static boolean verify(PublicKey pubKey, String signedText, String plainText) throws SignatureException{
           boolean result = false;
            try {
                Signature signature = Signature.getInstance(ECC_SIGN_ALGORITHM);
                signature.initVerify(pubKey);
                signature.update(plainText.getBytes());
                
                result = signature.verify(Base64.getDecoder().decode(signedText));
                
            } catch (NoSuchAlgorithmException ex) {
                System.getLogger(ECCCipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            } catch (InvalidKeyException ex) {
                System.getLogger(ECCCipher.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            return result;
        }
    }
