/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.uni.fc.cc.common;

import java.security.SecureRandom;
import java.util.HexFormat;

/**
 *
 * @author JUAN
 */
public class Utils {
    public static int getInverseModule(int a, int module) {
        int inv = 0;
        int temp;
        
        for(int i=0;i<module;i++){
            temp = (a*i)%module;
            if(temp == 1) return i;
        }
        
        return inv;
    }
    
    // MCD
    public static int gcd(int x, int y) {
        while(y != 0) {
            int temp = y;
            y = x % y;
            x = temp;
        }
        return x;
    }
 
    public static String byteToHex(byte[] bytes){
        return HexFormat.of().withUpperCase().withDelimiter(" ").formatHex(bytes);
    }
    
    public static byte[] generateIV(int length) {
        byte[] initVector = new byte[length];
        SecureRandom sr = new SecureRandom();
        sr.nextBytes(initVector);
        
        return initVector;
    }

}
