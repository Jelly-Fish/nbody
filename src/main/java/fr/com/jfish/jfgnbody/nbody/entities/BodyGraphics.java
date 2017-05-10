package fr.com.jfish.jfgnbody.nbody.entities;

import java.awt.Color;

/**
 *
 * @author thw
 */
public class BodyGraphics {
    
    /**
     *  Radius for calculation of body's size to draw.
     */
    public int graphicSize;
    
    /**
     * Graphic X location for drawing.
     */
    public int graphicX;
    
    /**
     * Graphic Y location for drawing.
     */
    public int graphicY;

    /**
     * Graphic Z location for drawing.
     */
    public int graphicZ;
    
    /**
     * Color for drawing or painting purposes.
     */
    public Color color;
    
    /**
     * Key used for reteiving object in collections.
     */
    public final int key;

    /**
     * Constructor.
     * @param graphicSize
     * @param graphicX
     * @param graphicY
     * @param graphicZ
     * @param color
     * @param key 
     */
    public BodyGraphics(final int graphicSize, final int graphicX, 
            final int graphicY, final int graphicZ, final Color color, final int key) {
        this.graphicSize = graphicSize;
        this.graphicX = graphicX;
        this.graphicY = graphicY;
        this.graphicZ = graphicZ;
        this.color = color;
        this.key = key;
    }
    
}
