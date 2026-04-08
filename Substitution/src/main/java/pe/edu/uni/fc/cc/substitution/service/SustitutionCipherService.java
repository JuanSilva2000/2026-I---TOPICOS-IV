/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.uni.fc.cc.substitution.service;

/**
 *
 * @author JUAN
 */
public class SustitutionCipherService {

    private final char[] key;

    public SustitutionCipherService(char[] key) {
        this.key = key;
    }

    private int indexOfCharsMap(char c) {
        int index = -1;

        for (int i = 0; i < key.length; i++) {
            if (c == key[i]) {
                index = i;
                break;
            }
        }

        return index;
    }

    public String encrypt(String plainText) {
        String result = "";
        int delta = 'Z' - 'z';
//        System.out.println("delta: " + delta);

        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);

            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    c = (char) (c - delta);

                    c = key[c - 'a'];
                    c = (char) (c + delta);

                } else {
                    c = key[c - 'a'];
                }
            }
            result = result + c;
        }

        return result;
    }

    public String decrypt(String encryptedText) {
        String result = "";

        int delta = 'Z' - 'z';
        for (int i = 0; i < encryptedText.length(); i++) {
            char c = encryptedText.charAt(i);

            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    c = (char) (c - delta);

                    c = (char) ('a' + indexOfCharsMap(c));
                    c = (char) (c + delta);

                } else {
                    c = (char) ('a' + indexOfCharsMap(c));
                }
            }
            result = result + c;
        }

        return result;
    }
}
