package fr.com.jfish.jfgnbody.utils;

import fr.com.jfish.jfgnbody.nbody.entities.Body;
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
         
        final Rectangle br = new Rectangle(b.graphics.graphicX, 
            b.graphics.graphicY, b.graphics.graphicSize, b.graphics.graphicSize);
        final Point cp = new Point(
            c.graphics.graphicX + (c.graphics.graphicSize / 2),
            c.graphics.graphicY + (c.graphics.graphicSize / 2));
        
        return br.contains(cp);
    }
    
}
