package fr.com.jfish.jfgnbody.exceptions;

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
