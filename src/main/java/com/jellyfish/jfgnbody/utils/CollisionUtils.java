package com.jellyfish.jfgnbody.utils;

import com.jellyfish.jfgnbody.nbody.Body;

/**
 *
 * @author thw
 */
public class CollisionUtils {
    
    /**
     * @param b superior in mass body.
     * @param collider body that is potentially collinding with b
     * @return true if b contains collider.
     */
    public static final boolean collidesWith(final Body b, final Body collider) {
        
        // FIXME : lossy calculations.
        /*
        if (collider.mass > b.mass) return false;     
        return (collider.rx > b.rx && 
                collider.rx + collider.mass < b.rx + b.mass &&
                collider.ry > b.ry && 
                collider.ry + collider.mass < b.ry + b.mass);
        */
        
        return false;
    }
    
}
