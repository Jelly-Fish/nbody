package com.jellyfish.jfgnbody.interfaces;

/**
 *
 * @author thw
 */
public interface Writable {
    
    void writeln(final String l);
    
    void write(final String l);
    
    void appendData(final String data);

    public void setParent(final NBodyDrawable nb);
    
}
