/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jellyfish.jfgnbody.nbody;

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
     * Graphic X location for drawing.
     */
    public int graphicY;

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
     * @param color
     * @param key 
     */
    public BodyGraphics(final int graphicSize, final int graphicX, 
            final int graphicY, final Color color, final int key) {
        this.graphicSize = graphicSize;
        this.graphicX = graphicX;
        this.graphicY = graphicY;
        this.color = color;
        this.key = key;
    }
    
}
