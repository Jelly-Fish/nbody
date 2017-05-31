package fr.com.jfish.jfgnbody.nbody.entities;

import fr.com.jfish.jfgnbody.nbody.barneshut.BHTCube;
import fr.com.jfish.jfgnbody.nbody.barneshut.Quadrant;
import java.util.Collection;

/**
 *
 * @author thw
 */
abstract class AbstractBody {
    
    //<editor-fold defaultstate="collapsed" desc="methods">
    /**
     * update the velocity and position using a timestep dt.
     * @param dt
     */
    public abstract void update(double dt);

    /**
     * returns the distance between two bodies.
     * @param b
     * @return 
     */
    public abstract double distanceTo(Body b);

    /**
     * set the force to 0 for the next iteration.
     */
    public abstract void resetForce();

    /**
     * compute the net force acting between the body a and b, and 
     * add to the net force acting on a.
     * @param b
     */
    public abstract void addForce(final Body b);

    /**
     * @param q
     * @return does Quadrant contain this ?
     */
    public abstract boolean in(final Quadrant q);
    
    /**
     * @param bhtcube
     * @return does BHTCube contain this ?
     */
    public abstract boolean in(final BHTCube bhtcube);
      
    /**
     * @param a
     * @param b
     * @return new Body instance with key = a.key.
     */
    public abstract Body add(final Body a, final Body b);
    
    /**
     * Check for a collision between a > in mass body with this.
     * @param b the body candidate to swallow this instance.
     */
    public abstract void checkCollision(final Body b);
    
    /**
     * Check for a collision between a super massive body with this.
     * @param bList
     */
    public abstract void checkCollision(final Collection<Body> bList);
    
    /**
     * Check collision with all bodies.
     * @param bArray 
     */
    public abstract void checkCollision(final Body[] bArray);
    
    /**
     * Is this, as a graphical object suject to being draw, in GUI's bounds or not ?
     * @param width
     * @param height
     * @return true if out of bounds else false.
     */
    public abstract boolean isOutOfBounds(final int width, final int height);

    /**
     * @param toSwallow the Body to swallow.
     */
    public abstract void swallow(final Body toSwallow);
    
    /**
     * @return true if mass is considered massive or super massive.
     */
    public abstract boolean isMassive();
    
    /**
     * @return true if mass is considered super massive static (not in motion).
     */
    public abstract boolean isSuperMassiveStatic();
    
    /**
     * @return Body's graphical X position for drawing purposes.
     */
    public abstract int getGraphicX();
    
    /**
     * @return Body's graphical Y position for drawing purposes.
     */
    public abstract int getGraphicY();
    
    /**
     * @return Body's graphical Z position for drawing purposes.
     */
    public abstract int getGraphicZ();
    
    /**
     * @return Open GL x.
     */
    public abstract int getOpenGLX(final int windowWidth);

    /**
     * @return Open GL y.
     */
    public abstract int getOpenGLY(final int windowHeight);

    /**
     * @return Open GL z.
     */
    public abstract int getOpenGLZ(final int windowDepth);
    
}
