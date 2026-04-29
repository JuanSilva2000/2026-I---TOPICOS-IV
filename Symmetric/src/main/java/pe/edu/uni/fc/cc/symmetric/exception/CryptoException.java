/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.uni.fc.cc.symmetric.exception;

/**
 *
 * @author JUAN
 */
public class CryptoException extends RuntimeException {
    public CryptoException(String message, Throwable cause){
        super(message, cause);
    }
}
