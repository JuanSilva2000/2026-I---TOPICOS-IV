/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.uni.fc.cc.substitution.service;

import pe.edu.uni.fc.cc.common.Constants;
import static pe.edu.uni.fc.cc.common.Constants.ALPHABET_SET_SIZE;
import pe.edu.uni.fc.cc.common.Utils;

/**
 *
 * @author JUAN
 */
public class AfineCipherService {

    private final int a;
    private final int b;
    private final int inv_a;

    public AfineCipherService(int a, int b){
        if (Utils.gcd(a, ALPHABET_SET_SIZE) != 1 || a < 0 || b < 0) {
            //error
            throw new IllegalArgumentException("a debe ser coprimo con " + Constants.ALPHABET_SET_SIZE);
        }

        this.a = a % ALPHABET_SET_SIZE;
        this.b = b % ALPHABET_SET_SIZE;
        this.inv_a = Utils.getInverseModule(a, ALPHABET_SET_SIZE);
    }

    public String encrypt(String plainText) {
        String result = "";
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            if (Character.isLetter(c)) {
                char offset = Character.isUpperCase(c) ? 'A' : 'a';
                // x = c-offset
                c = (char) (((a * (c - offset) + b) % ALPHABET_SET_SIZE) + offset);
            }
            result = result + c;
        }
        return result;
    }

    public String decrypt(String encryptTest) {
        String result = "";

//        int inv_a = Utils.getInverseModule(a, ALPHABET_SET_SIZE);
//        if (inv_a == 0) {
//            return result;
//        }

        for (int i = 0; i < encryptTest.length(); i++) {
            char c = encryptTest.charAt(i);
            if (Character.isLetter(c)) {
                char offset = Character.isUpperCase(c) ? 'A' : 'a';
                // x = c-offset
                int tmp = inv_a * (c - offset - b + ALPHABET_SET_SIZE);
                c = (char) (tmp % ALPHABET_SET_SIZE + offset);
            }

            result = result + c;
        }

        return result;
    }
}
