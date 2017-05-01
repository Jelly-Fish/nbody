package fr.com.jfish.jfgnbody.nbody.entities;

import fr.com.jfish.jfgnbody.nbody.barneshut.Quadrant;
import fr.com.jfish.jfgnbody.nbody.constants.NBodyConst;
import fr.com.jfish.jfgnbody.utils.CollisionUtils;
import fr.com.jfish.jfgnbody.utils.MassUtils;
import java.awt.Color;
import java.util.Collection;

/**
 *
 * @author thw
 */
public class Body extends AbstractBody {

    //<editor-fold defaultstate="collapsed" desc="vars">
    /**
     * gravitational constant. (6.673 * 10)^-11
     */
    protected final double G = 6.673e-11;
    
    /**
     * Solor mass. (1.98892 * 10)^30
     */
    private static final double solarmass = 1.98892e30;

    /**
     * Equals -1 if velocity and cartesian values should be negated - if
     * negativ, then spin will be counter clockwise.
     */
    public double negate = 1;
    
    /**
     * holds the cartesian position X.
     */
    public double rx; 
           
    /**
     * holds the cartesian position Y.
     */
    public double ry;
    
    /**
     * holds the cartesian position Z.
     */
    public double rz;
    
    /**
     * velocity component X.
     */
    public double vx;
    
    /**
     * velocity component Y. 
     */
    public double vy;
    
    /**
     * velocity component Y. 
     */
    public double vz;
    
    /**
     * Force component X.
     */
    public double fx;
    
    /**
     * Force component Y.
     */
    public double fy;
    
    /**
     * Force component Y.
     */
    public double fz;
    
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
    boolean swallowed = false;

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
     * @param rz the cartesian position z
     * @param vx velocity component x
     * @param vy velocity component y
     * @param vz velocity component y
     * @param mass mass of the body
     * @param color body's display color for fun.
     */
    public Body(final int key, final double rx, final double ry, final double rz, 
        final double vx, final double vy, final double vz, final double mass, final Color color) {
        
        this.rx = rx;
        this.ry = ry;
        this.rz = rz;
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
        this.mass = mass;
        this.key = key;
        this.graphics = new BodyGraphics(MassUtils.getVirtualIntegerMass(mass), 
                this.getGraphicX(), this.getGraphicY(), color, key);
    }
    
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
     * @param negate if velocity & cartesian valie should be negated.
     */
    public Body(final int key, final double rx, final double ry, final double rz, final double vx, 
            final double vy, final double vz, final double mass, final Color color, final boolean negate) {
        this.rx = rx;
        this.ry = ry;
        this.vx = vx;
        this.vy = vy;
        this.rz = rz;
        this.vz = vz;
        this.mass = mass;
        this.key = key;
        this.graphics = new BodyGraphics(MassUtils.getVirtualIntegerMass(mass), 
                this.getGraphicX(), this.getGraphicY(), color, key);
        this.negate = negate ? -1 : 1;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="overriden methods">
    @Override
    public void update(double dt) {
        
        if (this.negate < 0) {
            vx += -(dt * fx / mass);
            vy += -(dt * fy / mass);
            vz += -(dt * fz / mass);
            rx += -(dt * vx);
            ry += -(dt * vy);
            rz += -(dt * vz);
        } else {            
            vx += dt * fx / mass;
            vy += dt * fy / mass;
            vz += (dt * fz / mass);
            rx += dt * vx;
            ry += dt * vy;
            rz += -(dt * vz);
        }
        
        this.graphics.graphicX = this.getGraphicX();
        this.graphics.graphicY = this.getGraphicY();
    }

    @Override
    public double distanceTo(Body b) {
        final double dx = rx - b.rx;
        final double dy = ry - b.ry;
        final double dz = rz - b.rz;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    @Override
    public void resetForce() {
        fx = 0.0d;
        fy = 0.0d;
        fz = 0.0d;
    }

    @Override
    public void addForce(final Body b) {      
        final double EPS = 3E4; // softening parameter (just to avoid infinities).
        final double dx = b.rx - this.rx;
        final double dy = b.ry - this.ry;
        final double dz = b.rz - this.rz;
        final double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);
        final double F = (G * this.mass * b.mass) / (dist * dist + EPS * EPS);
        this.fx += F * dx / dist;
        this.fy += F * dy / dist;
        this.fz += F * dz / dist;
    }

    @Override
    public boolean in(final Quadrant q) {
        return q.contains(this.rx, this.ry);
    }
    
    @Override
    public Body add(final Body a, final Body b) {
        final double nRx = (a.rx * a.mass + b.rx * b.mass) / (a.mass + b.mass);
        final double nRy = (a.ry * a.mass + b.ry * b.mass) / (a.mass + b.mass);
        final double nRz = (a.rz * a.mass + b.rz * b.mass) / (a.mass + b.mass);
        final double nMass = a.mass + b.mass;
        return new Body(a.key, nRx, nRy, nRz, 0d, 0d, 0d, nMass, Color.DARK_GRAY);
    }

    @Override
    public void checkCollision(final Body b) {

        if (CollisionUtils.collidesWith(b, this)) {
            b.swallow(this);
        }
    }
    
    @Override
    public void checkCollision(final Collection<Body> bList) {
        
        for (Body b : bList) {
            this.checkCollision(b);
        }
    }
    
    @Override
    public void checkCollision(final Body[] bList) {
        
        for (Body b : bList) {
            this.checkCollision(b);
        }
    }

    @Override
    public boolean isOutOfBounds(final int width, final int height) {
        
        final int bx = this.getGraphicX();
        final int by = this.getGraphicY();
        return bx + (width / 2) < 0 || bx + (width / 2) > width || 
                by + (height / 2) < 0 || by + (height / 2) > height;
    }
    
    @Override
    public final int getGraphicX() {
        return (int) Math.round(this.rx * 250 / NBodyConst.NBODY_MASS_CONST);
    }

    @Override
    public final int getGraphicY() {
        return (int) Math.round(this.ry * 250 / NBodyConst.NBODY_MASS_CONST);
    }

    @Override
    public void swallow(final Body toSwallow) { 
        this.mass += toSwallow.mass;
        toSwallow.setSwallowed(true);
    }
    
    @Override
    public boolean isMassive() {
        return false;
    }
    
    @Override
    public boolean isSuperMassiveStatic() {
        return false;
    }
            
    @Override
    public String toString() {
        return String.format(">> [key°%d] x:%f y:%f z:%f mass:%f", this.key, this.vx, this.vy, this.vz, this.mass);
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
