/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.uni.fc.cc.common;

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
    
}
