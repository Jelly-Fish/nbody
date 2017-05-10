package fr.com.jfish.jfgnbody.nbody.barneshut;

/**
 * @author thw
 */
public class BHTCube {
    
    /**
     * x midddle point of this cube.
     */
    public final double xM;
    
    /**
     * y midddle point of this cube.
     */
    public final double yM;
    
    /**
     * y midddle point of this cube.
     */
    public final double zM;
    
    /**
     * Length of this cube.
     */
    public final double l;
    
    /**
     * Cardinalities.
     */
    public static enum Cardinality {
       NW_LOW_Y, NE_LOW_Y, SW_LOW_Y, SE_LOW_Y, 
       NW_HIGH_Y, NE_HIGH_Y, SW_HIGH_Y, SE_HIGH_Y;
    };

    /**
     * Constructor.
     * @param xM
     * @param yM
     * @param zM
     * @param l 
     */
    public BHTCube(final double xM, final double yM, final double zM, final double l) {        
        this.xM = xM;
        this.yM = yM;
        this.zM = zM;
        this.l = l;
    }
    
    /**
     * Does cube contains a point ?
     * @param x
     * @param y
     * @param z
     * @return true if so.
     */
    public boolean contains(final double x, final double y, final double z) {
        return x <= xM + l / 2.0 && x >= xM - l / 2.0 && y <= yM + l / 2.0 && y >= yM - l / 2.0 &&
            z <= zM + l / 2.0 && z >= zM - l / 2.0;
    }
    
    /**
     * Build a new sub cube defined by 8 cardinalities.
     * @param c
     * @return 
     */
    public BHTCube getSubBHTCube(final Cardinality c) {
        
        switch (c) {
            case NW_LOW_Y:
                return new BHTCube(xM - l / 4.0, yM + l / 4.0, zM - l / 4.0, l / 2.0);
            case NE_LOW_Y:
                return new BHTCube(xM + l / 4.0, yM + l / 4.0, zM - l / 4.0, l / 2.0);
            case SW_LOW_Y:
                return new BHTCube(xM - l / 4.0, yM - l / 4.0, zM - l / 4.0, l / 2.0);
            case SE_LOW_Y:
                return new BHTCube(xM + l / 4.0, yM - l / 4.0, zM - l / 4.0, l / 2.0);
            case NW_HIGH_Y:
                return new BHTCube(xM - l / 4.0, yM + l / 4.0, zM + l / 4.0, l / 2.0);
            case NE_HIGH_Y:
                return new BHTCube(xM + l / 4.0, yM + l / 4.0, zM + l / 4.0, l / 2.0);
            case SW_HIGH_Y:
                return new BHTCube(xM - l / 4.0, yM - l / 4.0, zM + l / 4.0, l / 2.0);
            case SE_HIGH_Y:
                return new BHTCube(xM + l / 4.0, yM - l / 4.0, zM + l / 4.0, l / 2.0);
            default:
                return null;
        }
    }
    
}
