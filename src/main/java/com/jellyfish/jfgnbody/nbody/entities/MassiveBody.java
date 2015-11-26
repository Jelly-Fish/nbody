package com.jellyfish.jfgnbody.nbody.entities;

import java.awt.Color;

/**
 *
 * @author thw
 */
public class MassiveBody extends Body {

    public MassiveBody(final int key, final double rx, final double ry, final double vx,
        final double vy, final double mass, final Color color) {
        super(key, rx, ry, vx, vy, mass, color);
    }
    
}
