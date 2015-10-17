package com.jellyfish.jfgnbody.nbody;

import com.jellyfish.jfgnbody.utils.MassUtils;
import com.jellyfish.jfgnbody.utils.StopWatch;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author thw
 */
public class BruteForce extends javax.swing.JPanel {

    //<editor-fold defaultstate="collapsed" desc="vars">
    /**
     * Count of Body classes to instanciate.
     */
    public final int N;

    /**
     * Collection of Body instances.
     */
    private final HashMap<Integer, Body> bodyMap = new HashMap<>();

    /**
     * Stop watch util.
     */
    private final StopWatch stopWatch;
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
        this.setSize(800, 600);
        this.setBackground(Color.GRAY);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="methods">
    @Override
    public void paint(Graphics g) {

        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        // Originally the origin is in the top right. Put it in its normal place :
        g.translate(this.getWidth() / 2, this.getHeight() / 2);

        for (Body b : this.bodyMap.values()) {
            
            if (b instanceof SupermassiveBody) continue;
            
            b.graphicRadius = MassUtils.getVirtualIntegerMass(b);
            g.setColor(b.color);
            g.fillOval((int) Math.round(b.rx * 250 / 1e18),
                    (int) Math.round(b.ry * 250 / 1e18), b.graphicRadius, b.graphicRadius);
        }

        if (this.stopWatch.hasReachedMaxElapsedMS()) {
            cleanBodyMap();
            addforces();
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

        double radius = 1e18; // radius of universe
        double solarmass = 1.98892e30; // = Math.pow(1.98892 * 10, 30) 
        double px, py, magv, absangle, thetav, phiv, vx , vy, mass;
        int red, blue, green;
        Color color;

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

            mass = Math.random() * solarmass * 10 + 1e20; //10 + 1e20;
            // Color the masses in green gradients by mass.
            red = (int) Math.floor(mass * 254 / (solarmass * 10 + 1e20));
            blue = (int) Math.floor(mass * 254 / (solarmass * 10 + 1e20));
            green = 255;
            color = new Color(red, green, blue);
            mass = Math.random() * solarmass;
            this.bodyMap.put(i, new Body(i, px, py, vx, vy, mass, color));
        }

        // Put a supermassive body in the center. SupermassiveBody instances
        // will not be candidates to draw or paint methods.
        this.bodyMap.put(this.bodyMap.size(), new SupermassiveBody(this.bodyMap.size(), 
                0, 0, 0, 0, 1e6 * solarmass, Color.RED));

    }

    /**
     * Use the method in Body to reset the forces, then add all the new forces.
     */
    @SuppressWarnings({"BoxedValueEquality", "NumberEquality"})
    public void addforces() {

        for (Map.Entry<Integer, Body> entry : bodyMap.entrySet()) {
            entry.getValue().resetForce();
            // FIXME : Notice-2 loops-->N^2 complexity :S
            for (Map.Entry<Integer, Body> entryBis : bodyMap.entrySet()) {
                if (!entry.getKey().equals(entryBis.getKey())) {
                    entry.getValue().addForce(entryBis.getValue());
                    entry.getValue().checkCollision(entryBis.getValue());
                }
            }
        }
        // Loop again and update the bodies using timestep param.
        for (Body b : this.bodyMap.values()) b.update(1e11);
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
                keys[i] = b.key;
                ++i;
            }
        }
        
        for (int j = 0; j < keys.length; j++) {
            this.bodyMap.remove(keys[j]);
        }
    }
    //</editor-fold>

}
