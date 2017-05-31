/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.com.jfish.jfgnbody.lwjgl3;

import fr.com.jfish.jfgnbody.interfaces.NBodyDrawable;
import fr.com.jfish.jfgnbody.interfaces.NBodyForceComputable;
import fr.com.jfish.jfgnbody.interfaces.Writable;
import fr.com.jfish.jfgnbody.lwjgl3.constants.FrameVars;
import fr.com.jfish.jfgnbody.nbody.NBodyData;
import fr.com.jfish.jfgnbody.nbody.NbodyCollection;
import fr.com.jfish.jfgnbody.nbody.barneshut.BHTCube;
import fr.com.jfish.jfgnbody.nbody.barneshut.Quadrant;
import fr.com.jfish.jfgnbody.nbody.constants.NBodyConst;
import fr.com.jfish.jfgnbody.nbody.entities.Body;
import fr.com.jfish.jfgnbody.nbody.force.BruteForceUpdater;
import fr.com.jfish.jfgnbody.nbody.simulations.AbstractSimulation;
import fr.com.jfish.jfgnbody.nbody.space.SpatialArea;
import fr.com.jfish.jfgnbody.utils.RandUtils;
import java.util.LinkedHashMap;
import javax.swing.JPanel;

/**
 *
 * @author thw
 */
public class NBodyLWJGLHelper implements NBodyDrawable {
    
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
     * // Previously new Quadrant(0, 0, 2 * 1e18)
     */
    protected final Quadrant q = new Quadrant(.0d, .0d, 8 * NBodyConst.NBODY_MASS_CONST);

    /**
     * Global space BHTCube.
     * // Previously new Quadrant(0, 0, 2 * 1e18)
     */
    protected final BHTCube bhtc = new BHTCube(.0d, .0d, .0d, 8 * NBodyConst.NBODY_MASS_CONST);
    
    /**
     * Interface for updating forces.
     */
    //protected NBodyForceComputable fu = new BHTreeForceUpdater();
    protected NBodyForceComputable fu = new BruteForceUpdater();

    /**
     * Data output writer.
     */
    private Writable writer = null;

    /**
     * Simulation instance.
     */
    protected AbstractSimulation sim;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="construct">
    /**
     * @param n
     * @param iterationSpeed
     * @param sim 
     */
    public NBodyLWJGLHelper(final int n, final double iterationSpeed, final AbstractSimulation sim) {
        this.N = n;
        this.sim = sim;
        this.nBodies = new NbodyCollection(n);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="methods">    
    @Override
    public void cleanBodyCollection() {

        int i = 0;
        
        while (nBodies.perform(i)) {
            if (nBodies.c[i].isSwallowed()) {
                if (nBodies.c[i].isMassive()) fu.getMbs().remove(nBodies.c[i].graphics.key);
                nBodies.discard(i);
            }
            ++i;
        }
    }

    @Override
    public boolean performPaint() {
        NBodyData.bodyCount = 0;      
        NBodyData.iterationCount++;
        fu.addForces(FrameVars.V_WIDTH, FrameVars.V_HEIGHT, bhtc, nBodies);
        cleanBodyCollection();               
       return true;
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

    /**
     * Switch or swap interface.
     *
     * @param fu
     */
    @Override
    public void swapForceUpdater(final NBodyForceComputable fu) {        
        fu.getMbs().clear();
        fu.getMbs().putAll(this.fu.getMbs());
        this.fu.getMbs().clear();        
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
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void restart(int n, int iSpeed, AbstractSimulation sim, RandUtils.Layout l) {
        throw new UnsupportedOperationException();
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
        this.fu.getMbs().clear();
        this.N = 0;
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
    
}
