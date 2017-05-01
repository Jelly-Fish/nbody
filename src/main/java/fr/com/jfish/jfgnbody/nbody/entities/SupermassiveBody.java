package fr.com.jfish.jfgnbody.nbody.entities;

import java.awt.Color;

/**
 *
 * @author thw
 */
public class SupermassiveBody extends MassiveBody {

    /**
     * @param key
     * @param rx the cartesian position x
     * @param ry the cartesian position y
     * @param rz the cartesian position z
     * @param vx velocity component x
     * @param vy velocity component y
     * @param vz velocity component z
     * @param mass mass of the body
     * @param color body's display color for fun.
     */
    public SupermassiveBody(final int key, final double rx, final double ry, final double rz, final double vx, 
        final double vy, final double vz, final double mass, final Color color) {
        super(key, rx, ry, rz, vx, vy, vz, mass, color);
    }
    
}
