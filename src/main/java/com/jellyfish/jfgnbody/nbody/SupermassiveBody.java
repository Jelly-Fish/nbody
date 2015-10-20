package com.jellyfish.jfgnbody.nbody;

import java.awt.Color;

/**
 *
 * @author thw
 */
public class SupermassiveBody extends Body {

    /**
     * @param key
     * @param rx the cartesian position x
     * @param ry the cartesian position y
     * @param vx velocity component x
     * @param vy velocity component y
     * @param mass mass of the body
     * @param color body's display color for fun.
     */
    public SupermassiveBody(int key, double rx, double ry, double vx, double vy, double mass, Color color) {
        super(key, rx, ry, vx, vy, mass, color);
    }
    
    @Override
    public void update(double dt) { }
    
}
