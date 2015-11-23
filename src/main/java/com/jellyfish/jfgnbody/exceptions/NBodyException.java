/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jellyfish.jfgnbody.exceptions;

/**
 *
 * @author thw
 */
public class NBodyException extends Exception {

    public static final String NULL_E = "Iteration attempt on Null element in class %s";
    
    public NBodyException() {
    }

    public NBodyException(final String message) {
        super(message);
    }
    
}
