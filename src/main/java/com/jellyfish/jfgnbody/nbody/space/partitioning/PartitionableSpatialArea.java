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
     * Does the neighboor areas of pA (pA included) contain Point pB ?
     * @param pA
     * @param pB
     * @return true if so.
     * @throws com.jellyfish.jfgnbody.nbody.space.partitioning.SpatialSuperPartitionException
     */
    public abstract boolean superContains(final java.awt.Point pA, final java.awt.Point pB) throws SpatialSuperPartitionException;
    
    /**
     * Does this area contain point p ?
     * @param p
     * @return 
     */
    public abstract boolean contains(final java.awt.Point p);
    
    /**
     * Update area dimensions.
     * @param x
     * @param y
     * @param w
     * @param h 
     */
    public abstract void updateSize(final int x, final int y, final int w, final int h);
    
    /**
     * Partition space 
     * @param partitionSize
     * @param motherX 
     * @param motherY 
     */
    public abstract void partition(final int partitionSize, final int motherX, final int motherY);
    
}
