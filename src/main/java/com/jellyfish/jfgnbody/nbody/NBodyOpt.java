package com.jellyfish.jfgnbody.nbody;

import com.jellyfish.jfgnbody.gui.GUIDTO;
import com.jellyfish.jfgnbody.interfaces.NBodyDrawable;
import com.jellyfish.jfgnbody.interfaces.NBodyForceComputable;
import com.jellyfish.jfgnbody.interfaces.Writable;
import com.jellyfish.jfgnbody.nbody.barneshut.Quadrant;
import com.jellyfish.jfgnbody.nbody.constants.NBodyConst;
import com.jellyfish.jfgnbody.nbody.entities.Body;
import com.jellyfish.jfgnbody.nbody.entities.SupermassiveBody;
import com.jellyfish.jfgnbody.nbody.force.BHTreeForceUpdater;
import com.jellyfish.jfgnbody.nbody.simulations.AbstractSimulation;
import com.jellyfish.jfgnbody.nbody.space.SpatialArea;
import com.jellyfish.jfgnbody.utils.StopWatch;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.LinkedHashMap;
import javax.swing.JPanel;

/**
 *
 * @author thw
 */
public class NBodyOpt extends javax.swing.JPanel implements ComponentListener, NBodyDrawable {
    
    //<editor-fold defaultstate="collapsed" desc="vars">
    /**
     * Nbody collection of bodies instance.
     */
    public NbodyCollection nBodies;
    
    /**
     * Count of Body classes to instanciate.
     */
    public int N;
    
    /**
     * Stop watch util.
     */
    public StopWatch stopWatch;

    /**
     * Spatial partitioning area.
     */
    public SpatialArea spatialArea = null;

    /**
     * The draw counter constant - reduce to perform drawing at lower interval -
     * If = 64, then drawinf or painting will be performed every 64 iterations.
     */
    protected static final int DRAW_COUNTER = 64;

    /**
     * Drow count - do not perform draw on every iteration.
     */
    protected int drawCount = 0;

    /**
     * Global space quandrant.
     */
    protected final Quadrant q = new Quadrant(0, 0, 8 * NBodyConst.NBODY_MASS_CONST); // Previously new Quadrant(0, 0, 2 * 1e18)

    /**
     * Interface for updating forces.
     */
    protected NBodyForceComputable fu = new BHTreeForceUpdater();

    /**
     * Data output writer.
     */
    private Writable writer = null;

    /**
     * Simulation instance.
     */
    protected AbstractSimulation sim;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="constructors">
    /**
     * @param n
     * @param iterationSpeed
     * @param sim 
     */
    public NBodyOpt(final int n, final double iterationSpeed, final AbstractSimulation sim) {
        this.N = n;
        this.sim = sim;
        //this.stopWatch = new StopWatch(iterationSpeed);
        this.addComponentListener(this);
        this.setBackground(NBodyConst.BG_COLOR);
        this.nBodies = new NbodyCollection(n);
    }
    
    /**
     * @param n
     * @param iterationSpeed
     * @param writer
     * @param sim
     */
    public NBodyOpt(final int n, final double iterationSpeed, final Writable writer, final AbstractSimulation sim) {
        this(n, iterationSpeed, sim);
        this.writer = writer;
        this.writer.setParent(this);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="methods">
    @Override
    public void paint(Graphics g) {

        NBodyData.bodyCount = 0;
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        // Originally the origin is in the top right. Put it in its normal place :
        g.translate(this.getWidth() / 2, this.getHeight() / 2);

        int i = 0;
        while (this.nBodies.perform(i)) {

            NBodyData.bodyCount++;
            g.setColor(nBodies.c[i].graphics.color);
            if (nBodies.c[i] instanceof SupermassiveBody) {
                g.drawOval(nBodies.c[i].graphics.graphicX, 
                        nBodies.c[i].graphics.graphicY, 
                        nBodies.c[i].graphics.graphicSize,
                        nBodies.c[i].graphics.graphicSize);
            } else {
                g.fillOval(nBodies.c[i].graphics.graphicX, 
                        nBodies.c[i].graphics.graphicY, 
                        nBodies.c[i].graphics.graphicSize,
                        nBodies.c[i].graphics.graphicSize);
            }
            ++i;
        }

        if (!GUIDTO.pause) {
            NBodyData.iterationCount++;
            //this.cleanBodyMap();
            fu.addForces(getWidth(), getHeight(), q, nBodies);
            if (this.stopWatch != null) {
                this.stopWatch.start();
            }
        }
        
        // Always repaint.
        super.repaint();
    }
    
    @Override
    public void cleanBodyMap() {

        final int[] keys = new int[this.nBodies.size()];
        int k = 1;
        int i = 0;
        while (this.nBodies.perform(i)) {
            if (this.nBodies.c[i].isSwallowed()) {
                //keys[i] = this.nBodies.c[i].graphics.key;
                //++k;
                this.nBodies.discard(i); //keys[j]);
            }
            ++i;
        }
        /*
        for (int j = 0; j < k; j++) {
            this.nBodies.discard(keys[j]);
        }*/
    }

    @Override
    public boolean performPaint() {

        if (NBody.DRAW_COUNTER > this.drawCount) {
            this.drawCount++;
            return false;
        } else {
            this.drawCount = 0;
            return true;
        }
    }

    @Override
    public void restart(int n, int iSpeed, final AbstractSimulation sim) {
        NBodyData.bodyCount = 0;
        NBodyData.iterationCount = 0;
        NBodyData.superMassiveBodyMass = 0.0;
        this.N = n;
        this.sim = sim;
        sim.start(this, N, nBodies);
        //this.stopWatch = new StopWatch(iSpeed);
        this.spatialArea.updateSize(this.getWidth(), this.getHeight());
    }
    
    @Override
    public LinkedHashMap<Integer, Body> getNB() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public AbstractSimulation getSim() {
        return sim;
    }
    
    @Override
    public NBodyForceComputable getForceUpdater() {
        return this.fu;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="GUI called methods">
    /**
     * Switch or swap interface.
     *
     * @param fu
     */
    @Override
    public void swapForceUpdater(final NBodyForceComputable fu) {
        this.fu = fu;
    }

    @Override
    public void setWriter(final Writable w) {
        this.writer = w;
    }

    @Override
    public Writable getWriter() {
        return this.writer;
    }
    
    @Override
    public JPanel getPanel() {
        return this;
    }

    @Override
    public SpatialArea getSpatialArea() {
        return this.spatialArea;
    }

    @Override
    public void setSpatialArea(final SpatialArea s) {
        this.spatialArea = s;
    }

    @Override
    public void clear() {
        this.nBodies.c = new Body[0];
        this.N = 0;
        this.stopWatch.stop();
        this.getParent().repaint();
    }
    
    @Override
    public NbodyCollection<Body> getNCollection() {
        return this.nBodies;
    }
    
    @Override
    public void setNCollection(final NbodyCollection n) {
        this.nBodies = n;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="javax.swing.JPanel overrides">
    @Override
    public void componentResized(ComponentEvent evt) {
        if (this.spatialArea != null) {
            this.spatialArea.updateSize(this.getWidth(), this.getHeight());
        }
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
    //</editor-fold>
    
}
