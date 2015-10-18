package com.jellyfish.jfgnbody.utils;

import com.jellyfish.jfgnbody.nbody.Body;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author thw
 */
public class CollisionUtils {
    
    /**
     * @param b superior in mass body.
     * @param c body that is potentially collinding with b
     * @return true if b contains collider.
     */
    public static final boolean collidesWith(final Body b, final Body c) {
         
        final Rectangle br = new Rectangle((int) Math.round(b.rx * 250 / 1e18), 
            (int) Math.round(b.ry * 250 / 1e18), b.graphicSize, b.graphicSize);
        final Point cp = new Point(
            (int) Math.round(c.rx * 250 / 1e18) + (c.graphicSize / 2),
            (int) Math.round(c.ry * 250 / 1e18) + (c.graphicSize / 2));
        
        return br.contains(cp);
    }
    
}
