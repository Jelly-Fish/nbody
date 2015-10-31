package com.jellyfish.jfgnbody.nbody.barneshut;

/**
 *
 * @author thw
 */
public class Quadrant {
    
    /**
     * x midddle point of this quadrant.
     */
    public final double xM;
    
    /**
     * y midddle point of this quadrant.
     */
    public final double yM;
    
    /**
     * Length of this quadrant.
     */
    public final double l;
    
    /**
     * Cardinalities.
     */
    public static enum Cardinality {
       NW, NE, SW, SE;
    };

    /**
     * Constructor.
     * @param xM
     * @param yM
     * @param l 
     */
    public Quadrant(final double xM, final double yM, final double l) {
        this.xM = xM;
        this.yM = yM;
        this.l = l;
    }
    
    /**
     * Does quadrant contains a point ?
     * @param x
     * @param y
     * @return true if so.
     */
    public boolean contains(final double x, final double y) {
        return x <= xM + l / 2.0 && xM >= xM - l / 2.0 && y <= yM + l / 2.0 && y >= yM - l / 2.0;
    }
    
    /**
     * Build a new sub quadrant defined by 4 cardinalities.
     * @param c
     * @return 
     */
    public Quadrant getSubQuadrant(final Cardinality c) {
        
        switch (c) {
            case NW:
                return new Quadrant(xM - l / 4.0, yM + l / 4.0, l / 2.0);
            case NE:
                return new Quadrant(xM + l / 4.0, yM + l / 4.0, l / 2.0);
            case SW:
                return new Quadrant(xM - l / 4.0, yM - l / 4.0, l / 2.0);
            case SE:
                return new Quadrant(xM + l / 4.0, yM - l / 4.0, l / 2.0);
            default:
                return null;
        }
    }
    
}
