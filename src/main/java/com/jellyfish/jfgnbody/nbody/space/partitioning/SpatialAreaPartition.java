package com.jellyfish.jfgnbody.nbody.space.partitioning;

/**
 *
 * @author thw
 */
public class SpatialAreaPartition extends PartitionableSpatialArea {

    /**
     * Constructor.
     * @param w
     * @param h
     * @param x
     * @param y 
     */
    public SpatialAreaPartition(final int x, final int y, final int w, final int h) {
        super(x, y, w, h);
    }

    @Override
    public boolean contains(final java.awt.Point p) {
        return this.area.contains(p);
    }

    @Override
    public void updateSize(final int w, final int h) {}

    @Override
    public void partition(final int pSize, final int motherX, final int motherY) {}

    @Override
    public boolean superContains(final java.awt.Point pA, final java.awt.Point pB) throws SpatialSuperPartitionException { 
        throw new SpatialSuperPartitionException();
    }
    
}
