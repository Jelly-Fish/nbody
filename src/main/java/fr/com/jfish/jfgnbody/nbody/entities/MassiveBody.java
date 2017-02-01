package fr.com.jfish.jfgnbody.nbody.entities;

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
    public void swallow(final Body toSwallow) {
        //this.mass += toSwallow.mass;
        //this.graphics.graphicSize = MassUtils.getVirtualIntegerMass(this.mass);
        toSwallow.swallowed = true;
    }

    @Override
    public void addForce(final Body b) {
        if (this.mass < b.mass) super.addForce(b);
    }
    
    @Override
    public void setSwallowed(final boolean swallowed) {
        this.swallowed = swallowed;
    }
    
    @Override
    public boolean isMassive() {
        return true;
    }

}
