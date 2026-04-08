/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.uni.fc.cc.substitution.App;

import pe.edu.uni.fc.cc.substitution.service.SustitutionCipherService;

/**
 *
 * @author JUAN
 */
public class MainSustitutionCipher {

    static char[] key = {
        'q',
        'w',
        'e',
        'r',
        't',
        'y',
        'u',
        'i',
        'o',
        'p',
        'a',
        's',
        'd',
        'f',
        'g',
        'h',
        'j',
        'k',
        'l',
        'z',
        'x',
        'c',
        'v',
        'b',
        'n',
        'm'
    };

    public static void main(String[] args) {
        SustitutionCipherService cipher = new SustitutionCipherService(key);

        String original_message = "this is a plain text";
        String encrypted_message = cipher.encrypt(original_message);
        String decrypted_message = cipher.decrypt(encrypted_message);

        System.out.println("original: " + original_message);
        System.out.println("encrypted: " + encrypted_message);
        System.out.println("decrypted: " + decrypted_message);
    }

}
