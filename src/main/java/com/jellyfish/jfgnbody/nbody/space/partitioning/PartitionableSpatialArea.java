/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jellyfish.jfgnbody.nbody.space.partitioning;

/**
 *
 * @author thw
 */
public abstract class PartitionableSpatialArea {
    
    /**
     * With of spatial zone.
     */
    public int width;
    
    /**
     * Height of spatial zone.
     */
    public int height;
    
    /**
     * X & Y coordinates.
     */
    public int x, y;
    
    /**
     * Rectabgle that represents this area.
     */
    public java.awt.Rectangle area;

    /**
     * Constructor.
     * @param width
     * @param height
     * @param x
     * @param y 
     */
    public PartitionableSpatialArea(final int x, final int y, final int width, final int height) {
        this.width = width;
        this.height = height;
        this.area = new java.awt.Rectangle(x, y, width, height);
    }
    
    /**
     * Does this area contain Point p.
     * @param p
     * @return true if so.
     */
    public abstract boolean contains(final java.awt.Point p);
    
    /**
     * Update area dimensions.
     * @param w
     * @param h 
     */
    public abstract void updateSize(final int w, final int h);
    
    /**
     * Partition space
     * @param pSize 
     * @param motherX 
     * @param motherY 
     */
    public abstract void partition(final int pSize, final int motherX, final int motherY);
    
}
