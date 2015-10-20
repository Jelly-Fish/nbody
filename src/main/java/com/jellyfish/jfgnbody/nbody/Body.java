package com.jellyfish.jfgnbody.nbody;

import com.jellyfish.jfgnbody.nbody.barneshut.Quad;
import com.jellyfish.jfgnbody.utils.CollisionUtils;
import com.jellyfish.jfgnbody.utils.MassUtils;
import java.awt.Color;

/**
 *
 * @author thw
 */
public class Body {

    //<editor-fold defaultstate="collapsed" desc="vars">
    /**
     * gravitational constant. (6.673 * 10)^-11
     */
    private static final double G = 6.673e-11;
    
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
     * Has this been swallowed by another body.
     */
    public boolean swallowed = false;
    
    /**
     * This body's graphical data.
     */
    public final BodyGraphics graphics;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="constructor">
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
        this.graphics = new BodyGraphics(MassUtils.getVirtualIntegerMass(mass), 
                (int) Math.round(this.rx * 250 / 1e18),
                (int) Math.round(this.ry * 250 / 1e18), color, key);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="methods">
    /**
     * update the velocity and position using a timestep dt.
     * @param dt
     */
    public void update(double dt) {
        vx += dt * fx / mass;
        vy += dt * fy / mass;
        rx += dt * vx;
        ry += dt * vy;
        this.graphics.graphicX = (int) Math.round(this.rx * 250 / 1e18);
        this.graphics.graphicY = (int) Math.round(this.ry * 250 / 1e18);
    }

    /**
     * returns the distance between two bodies.
     * @param b
     * @return 
     */
    public double distanceTo(Body b) {
        double dx = rx - b.rx;
        double dy = ry - b.ry;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * set the force to 0 for the next iteration.
     */
    public void resetForce() {
        fx = 0.0;
        fy = 0.0;
    }

    /**
     * compute the net force acting between the body a and b, and 
     * add to the net force acting on a.
     * @param b
     */
    public void addForce(final Body b) {
        Body a = this;
        double EPS = 3E4; // softening parameter (just to avoid infinities)
        double dx = b.rx - a.rx;
        double dy = b.ry - a.ry;
        double dist = Math.sqrt(dx * dx + dy * dy);
        double F = (G * a.mass * b.mass) / (dist * dist + EPS * EPS);
        a.fx += F * dx / dist;
        a.fy += F * dy / dist;
    }

    /**
     * @param q
     * @return does quad contain Quad ?
     */
    public boolean in(Quad q) {
        return q.contains(this.rx, this.ry);
    }
      
    /**
     * Check for a collision between a > in mass body with this.
     * @param b the body candidate to swallow this instance.
     */
    void checkCollision(final Body b) {
        
        if (this.mass > b.mass) return;
        
        if (CollisionUtils.collidesWith(b, this)) {
            b.swallow(this);
        }
    }
    
    /**
     * Is this, as a graphical object suject to being draw, in GUI's bounds or not ?
     * @param width
     * @param height
     * @return true if out of bounds else false.
     */
    boolean isOutOfBounds(final int width, final int height) {
        
        final int bx = (int) Math.round(this.rx * 250 / 1e18);
        final int by = (int) Math.round(this.ry * 250 / 1e18);
        return bx + (width / 2) < 0 || bx > width * 2 || by + (height / 2) < 0 || by > height * 2;
    }

    /**
     * @param toSwallow the Body to swallow.
     */
    private void swallow(final Body toSwallow) {
        
        if (toSwallow instanceof SupermassiveBody) return;
        
        this.mass += toSwallow.mass;
        this.graphics.graphicSize = MassUtils.getVirtualIntegerMass(this.mass);
        toSwallow.swallowed = true;
    }

    public Body add(Body body, Body b) {
        throw new UnsupportedOperationException("Method add(b1, b2) not supported in class " +
                Body.class.getSimpleName());
    }
    //</editor-fold>
    
}
