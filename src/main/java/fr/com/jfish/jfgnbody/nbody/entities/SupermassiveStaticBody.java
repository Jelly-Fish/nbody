package fr.com.jfish.jfgnbody.nbody.entities;

import java.awt.Color;

/**
 *
 * @author thw
 */
public class SupermassiveStaticBody extends SupermassiveBody {

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
    public SupermassiveStaticBody(final int key, final double rx, final double ry, final double rz, 
            final double vx, final double vy, final double vz, final double mass, final Color color) {
        super(key, rx, ry, rz, vx, vy, vz, mass, color);
    }

    @Override
    public void update(double dt) {
    }

    @Override
    public boolean isSwallowed() {
        return false;
    }
    
    @Override
    public boolean isSuperMassiveStatic() {
        return true;
    }
    
    @Override
    public boolean isMassive() {
        return false;
    }

}
