package fr.com.jfish.jfgnbody.nbody.barneshut;

import javafx.geometry.Point3D;

/**
 * @author thw
 */
public class Cube {
    
    private final int x, y, z, l;
    
    public Cube(final int x, final int y, final int z, final int l) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.l = l;
    }
    
    public boolean contains(final Point3D p) {        
        return !(p.getX() > x && p.getX() + l < x + l &&
            p.getY() > y && p.getY() + l < y + l &&
            p.getZ() > z && p.getZ() + l < z + l);
    }
    
}
