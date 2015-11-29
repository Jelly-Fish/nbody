package com.jellyfish.jfgnbody.nbody.entities;

import java.awt.Color;
import java.util.Collection;

/**
 * @author thw
 */
public class MassiveBody extends Body {

    public MassiveBody(final int key, final double rx, final double ry, final double vx,
            final double vy, final double mass, final Color color) {
        super(key, rx, ry, vx, vy, mass, color);
    }

    @Override
    public void checkCollision(final Collection<Body> bList) {
    }

    @Override
    public void addForce(final Body b) {
        /**
         * FIXME : with > 40000 body count MB bugs :S
         */
        if (this.mass < b.mass) super.addForce(b);
    }

}
