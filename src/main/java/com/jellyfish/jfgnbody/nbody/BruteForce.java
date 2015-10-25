package com.jellyfish.jfgnbody.nbody;

import com.jellyfish.jfgnbody.nbody.space.SpatialArea;
import com.jellyfish.jfgnbody.nbody.space.partitioning.SpatialSuperPartitionException;
import com.jellyfish.jfgnbody.utils.StopWatch;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thw
 */
public class BruteForce extends javax.swing.JPanel implements ComponentListener {

    //<editor-fold defaultstate="collapsed" desc="vars">
    /**
     * Count of Body classes to instanciate.
     */
    public int N;

    /**
     * Collection of Body instances.
     */
    private final HashMap<Integer, Body> bodyMap = new HashMap<>();

    /**
     * Stop watch util.
     */
    private StopWatch stopWatch;
    
    /**
     * Spatial partitioning area.
     */
    public SpatialArea spatialArea = null;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="constructor">
    /**
     * @param n number of bodies.
     * @param iterationSpeed iteration speed for StopWatch.
     */
    public BruteForce(final int n, final double iterationSpeed) {
        this.N = n;
        this.startBodies(N);
        this.stopWatch = new StopWatch(iterationSpeed);
        this.addComponentListener(this);
        this.setBackground(new Color(10,10,10));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="methods">
    @Override
    public void paint(Graphics g) {

        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        // Originally the origin is in the top right. Put it in its normal place :
        g.translate(this.getWidth() / 2, this.getHeight() / 2);

        if (!(this.bodyMap.size() > 0)) {
            return;
        }

        for (Body b : this.bodyMap.values()) {
            boolean superMass = b instanceof SupermassiveBody;
            g.setColor(b.graphics.color);
            g.fillOval(b.graphics.graphicX, b.graphics.graphicY, b.graphics.graphicSize,
                    b.graphics.graphicSize);
        }

        if (this.stopWatch.hasReachedMaxElapsedMS()) {
            cleanBodyMap();
            try {
                addforces();
            } catch (final SpatialSuperPartitionException ex) {
                Logger.getLogger(BruteForce.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (this.stopWatch != null) {
                this.stopWatch.start();
            }
        }

        super.repaint();
    }

    /**
     * the bodies are initialized in circular orbits around the central mass,
     * some physics to do so.
     *
     * @param rx
     * @param ry
     * @return
     */
    public static double circleV(final double rx, final double ry) {

        double solarmass = 1.98892e30;
        double r2 = Math.sqrt(rx * rx + ry * ry);
        double numerator = (6.67e-11) * 1e6 * solarmass;
        return Math.sqrt(numerator / r2);
    }

    /**
     * Initialize N bodies with random positions and circular velocities.
     *
     * @param N
     */
    public final void startBodies(final int N) {

        //double radius = 1e18; // radius of universe
        double solarmass = 1.98892e30; // = Math.pow(1.98892 * 10, 30) 
        double px, py, magv, absangle, thetav, phiv, vx, vy, mass;
        //int red, blue, green;
        //Color color;
        //final Random rand = new Random();

        for (int i = 0; i < N; i++) {

            px = 1e18 * exp(-1.8) * (.5 - Math.random());
            py = 1e18 * exp(-1.8) * (.5 - Math.random());
            magv = circleV(px, py);

            absangle = Math.atan(Math.abs(py / px));
            thetav = Math.PI / 2 - absangle;
            phiv = Math.random() * Math.PI;
            vx = -1 * Math.signum(py) * Math.cos(thetav) * magv;
            vy = Math.signum(px) * Math.sin(thetav) * magv;

            // Orient a random 2D circular orbit. Use when orbir directions can
            // be oposite. Clockwise & counter-clockwise.
            //if (Math.random() <= .5) { vx = -vx; vy = -vy; }

            /* Use only for color effects depending on mass :
             mass = Math.random() * solarmass * 10 + 1e20;
             // Color the masses in green gradients by mass.
             red = (int) Math.floor(mass * 254 / (solarmass * 10 + 1e20));
             blue = (int) Math.floor(mass * 254 / (solarmass * 10 + 1e20));
             green = 255;
             color = new Color(red, green, blue);*/
            mass = Math.random() * solarmass; //* rand.nextInt((100000 - 10000) + 1) + 10000;
            this.bodyMap.put(i, new Body(i, px, py, vx, vy, mass, Color.WHITE));
        }

        // Put a supermassive body in the center. SupermassiveBody instances
        // will not be candidates to draw or paint methods.
        this.bodyMap.put(this.bodyMap.size(), new SupermassiveBody(this.bodyMap.size(),
                0, 0, 0, 0, 1e6 * solarmass, Color.BLACK));

    }

    /**
     * Use the method in Body to reset the forces, then add all the new forces.
     * @throws com.jellyfish.jfgnbody.nbody.space.partitioning.SpatialSuperPartitionException
     */
    @SuppressWarnings({"BoxedValueEquality", "NumberEquality"})
    public void addforces() throws SpatialSuperPartitionException {

        for (Map.Entry<Integer, Body> eA : bodyMap.entrySet()) {
            eA.getValue().resetForce();
            // FIXME : 2 loops = N^2 complexity...
            for (Map.Entry<Integer, Body> eB : bodyMap.entrySet()) {
                if (!eA.getKey().equals(eB.getKey())) {
                    eA.getValue().addForce(eB.getValue());
                    eA.getValue().checkCollision(eB.getValue());   
                }
            }
        }
        
        // Loop again and update the bodies using timestep param if tehy are still
        // in the bounds of the GUI display.
        for (Body b : this.bodyMap.values()) {
            if (!b.isOutOfBounds(this.getWidth(), this.getHeight())) {
                b.update(1e11);
            } else {
                b.swallowed = true;
            }
        }
    }

    /**
     * @param lambda
     * @return
     */
    public static double exp(final double lambda) {
        return -Math.log(1 - Math.random()) / lambda;
    }

    /**
     * Remove all bodies that have collided with more massiv bodies.
     */
    private void cleanBodyMap() {

        final int[] keys = new int[this.bodyMap.size()];
        int i = 0;
        for (Body b : this.bodyMap.values()) {
            if (b.swallowed) {
                keys[i] = b.graphics.key;
                ++i;
            }
        }

        for (int j = 0; j < i; j++) {
            this.bodyMap.remove(keys[j]);
        }
    }

    public void restart(int n, int iSpeed) {
        this.N = n;
        this.bodyMap.clear();
        this.startBodies(N);
        this.stopWatch = new StopWatch(iSpeed);
        this.spatialArea.updateSize(this.getWidth(), this.getHeight());
    }
    
    @Override
    public void componentResized(ComponentEvent evt) {
        if (this.spatialArea != null) this.spatialArea.updateSize(this.getWidth(), this.getHeight());
    }
    
    @Override
    public void componentMoved(ComponentEvent e) { }

    @Override
    public void componentShown(ComponentEvent e) { }

    @Override
    public void componentHidden(ComponentEvent e) { }
    //</editor-fold>

}
