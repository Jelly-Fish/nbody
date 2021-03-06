package com.jellyfish.jfgnbody.nbody.space;

import com.jellyfish.jfgnbody.nbody.space.partitioning.PartitionableSpatialArea;
import com.jellyfish.jfgnbody.nbody.space.partitioning.SpatialAreaPartition;
import com.jellyfish.jfgnbody.nbody.space.partitioning.SpatialSuperPartitionException;

/**
 *
 * @author thw
 */
public class SpatialArea extends PartitionableSpatialArea {

    /**
     * Square size of each partioned sub element.
     */
    private final static int partitionSize = 50;
    
    /**
     * Collection of coordinates of each partitioned space area.
     */
    private final java.util.HashMap<int[], SpatialAreaPartition> grid = new java.util.HashMap<>();

    /**
     * Constructor.
     * @param x
     * @param y 
     * @param w 
     * @param h 
     */
    public SpatialArea(final int x, final int y, final int w, final int h) {
        super(x, y, w, h);
        this.partition(SpatialArea.partitionSize, width, height);
    }
    
    @Override
    public boolean superContains(final java.awt.Point pA, final java.awt.Point pB) throws SpatialSuperPartitionException {
        for (java.util.Map.Entry<int[], SpatialAreaPartition> entry : grid.entrySet()) {
            if (entry.getValue().contains(pA)) {
                return this.contains(entry.getKey(), pB);
            }
        }
        System.out.println("pA is not found.");
        return false;
    }
    
    /**
     * Does any sub partition contain the Point p.
     * @param xy
     * @param p
     * @return 
     */
    private boolean contains(final int[] xy, final java.awt.Point p) {
        
        for (int[] c : this.getNeighboorsAreas(xy)) {
            if (this.grid.get(c) == null) {
                System.out.println("neighboor is null.");
            }
            if (this.grid.containsKey(xy) && this.grid.get(c) != null && this.grid.get(c).contains(p)) {
                System.out.println("pA area contains p");
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateSize(final int x, final int y, final int w, final int h) {
        
        this.area.setSize(w, h);
        this.grid.clear();
        this.partition(SpatialArea.partitionSize, w, h);
    }

    @Override
    public final void partition(final int pSize, final int motherX, final int motherY) {
        
        for (int i = 0; i < this.width; i += pSize) {
            for (int j = 0; j < this.height; j += pSize) {
                int[] xy = new int[] { i, j + i };
                this.grid.put(xy, new SpatialAreaPartition(pSize, pSize, xy[0], xy[1]));
            }
        }
    }
    
    /**
     * Get all neighboors of x & y coordinates.
     * @param xy int array length 2. i0 = x, i1 = Y;
     * @return all neighbours.
     */
    private java.util.ArrayList<int[]> getNeighboorsAreas(final int[] xy) {
        final java.util.ArrayList<int[]> keys = new java.util.ArrayList<>();
        keys.add(xy);
        keys.add(new int[] {xy[0] - 1, xy[1] - 1});
        keys.add(new int[] {xy[0], xy[1] - 1});
        keys.add(new int[] {xy[0], xy[1] + 1});
        keys.add(new int[] {xy[0] - 1, xy[1]});
        keys.add(new int[] {xy[0] + 1, xy[1]});
        keys.add(new int[] {xy[0] - 1, xy[1] + 1});
        keys.add(new int[] {xy[0], xy[1] + 1});
        keys.add(new int[] {xy[0] + 1, xy[1] + 1});
        return keys;
    } 

    @Override
    public boolean contains(final java.awt.Point p) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
        
}
