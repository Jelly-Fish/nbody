package com.jellyfish.jfgnbody.nbody.entities;

import com.jellyfish.jfgnbody.nbody.barneshut.Quadrant;
import com.jellyfish.jfgnbody.nbody.constants.NBodyConst;
import com.jellyfish.jfgnbody.utils.CollisionUtils;
import com.jellyfish.jfgnbody.utils.MassUtils;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author thw
 */
public class Body extends AbstractBody {

    //<editor-fold defaultstate="collapsed" desc="vars">
    /**
     * gravitational constant. (6.673 * 10)^-11
     */
    private final double G = 6.673e-11;
    
    /**
     * Solor mass. (1.98892 * 10)^30
     */
    private static final double solarmass = 1.98892e30;

    /**
     * holds the cartesian position X.
     */
    public double rx; 
           
    /**
     * holds the cartesian position Y.
     */
    public double ry;
    
    /**
     * velocity component X.
     */
    public double vx;
    
    /**
     * velocity component Y. 
     */
    public double vy;
    
    /**
     * Force component X.
     */
    public double fx;
    
    /**
     * Force component Y.
     */
    public double fy;
    
    /**
     * Mass.
     */
    public Double mass;
    
    /**
     * Key in hashmap.
     */
    public final int key;
    
    /**
     * Has this been swallowed by another body.
     */
    private boolean swallowed = false;

    /**
     * This body's graphical data.
     */
    public final BodyGraphics graphics;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="constructors">
    /**
     * @param key
     * @param rx the cartesian position x
     * @param ry the cartesian position y
     * @param vx velocity component x
     * @param vy velocity component y
     * @param mass mass of the body
     * @param color body's display color for fun.
     */
    public Body(final int key, final double rx, final double ry, final double vx, 
            final double vy, final double mass, final Color color) {
        this.rx = rx;
        this.ry = ry;
        this.vx = vx;
        this.vy = vy;
        this.mass = mass;
        this.key = key;
        this.graphics = new BodyGraphics(MassUtils.getVirtualIntegerMass(mass), 
                (int) Math.round(this.rx * 250 / NBodyConst.NBODY_MASS_CONST),
                (int) Math.round(this.ry * 250 / NBodyConst.NBODY_MASS_CONST), color, key);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="overriden methods">
    @Override
    public void update(double dt) {
        vx += dt * fx / mass;
        vy += dt * fy / mass;
        rx += dt * vx;
        ry += dt * vy;
        this.graphics.graphicX = (int) Math.round(this.rx * 250 / NBodyConst.NBODY_MASS_CONST);
        this.graphics.graphicY = (int) Math.round(this.ry * 250 / NBodyConst.NBODY_MASS_CONST);
    }

    @Override
    public double distanceTo(Body b) {
        double dx = rx - b.rx;
        double dy = ry - b.ry;
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public void resetForce() {
        fx = 0.0;
        fy = 0.0;
    }

    @Override
    public void addForce(final Body b) {      
        double EPS = 3E4; // softening parameter (just to avoid infinities).
        double dx = b.rx - this.rx;
        double dy = b.ry - this.ry;
        double dist = Math.sqrt(dx * dx + dy * dy);
        double F = (G * this.mass * b.mass) / (dist * dist + EPS * EPS);
        this.fx += F * dx / dist;
        this.fy += F * dy / dist;
    }

    @Override
    public boolean in(final Quadrant q) {
        return q.contains(this.rx, this.ry);
    }
    
    @Override
    public Body add(final Body a, final Body b) {
        final double nRx = (a.rx * a.mass + b.rx * b.mass) / (a.mass + b.mass);
        final double nRy = (a.ry * a.mass + b.ry * b.mass) / (a.mass + b.mass);
        final double nMass = a.mass + b.mass;
        return new Body(a.key, nRx, nRy, 0, 0, nMass, Color.DARK_GRAY);
    }

    @Override
    public void checkCollision(final Body b) {
        
        if (this.mass > b.mass) return;
        
        if (CollisionUtils.collidesWith(b, this)) {
            b.swallow(this);
        }
    }
    
    @Override
    public void checkCollision(final ArrayList<Body> bList) {
        
        for (Body b : bList) {
            this.checkCollision(b);
        }
    }

    @Override
    public boolean isOutOfBounds(final int width, final int height) {
        
        final int bx = (int) Math.round(this.rx * 250 / NBodyConst.NBODY_MASS_CONST);
        final int by = (int) Math.round(this.ry * 250 / NBodyConst.NBODY_MASS_CONST);
        return bx + (width / 2) < 0 || bx > width * 2 || by + (height / 2) < 0 || by > height * 2;
    }

    @Override
    public void swallow(final Body toSwallow) {
        
        if (toSwallow instanceof MassiveBody) return;
        
        this.mass += toSwallow.mass;
        this.graphics.graphicSize = MassUtils.getVirtualIntegerMass(this.mass);
        toSwallow.swallowed = true;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="getters & setters">
    public boolean isSwallowed() {
        return this.swallowed;
    }

    public void setSwallowed(final boolean swallowed) {
        this.swallowed = swallowed;
    }
    //</editor-fold>

}
