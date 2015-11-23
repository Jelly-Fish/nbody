package com.jellyfish.jfgnbody.interfaces;

import com.jellyfish.jfgnbody.nbody.NbodyCollection;
import com.jellyfish.jfgnbody.nbody.entities.Body;
import com.jellyfish.jfgnbody.nbody.simulations.AbstractSimulation;
import com.jellyfish.jfgnbody.nbody.space.SpatialArea;
import java.util.HashMap;

/**
 *
 * @author thw
 */
public interface NBodyDrawable {
    
    void cleanBodyMap();

    boolean performPaint();

    void restart(int n, int iSpeed, final AbstractSimulation sim);
    
    HashMap<Integer, Body> getNB();
    
    void setWriter(final Writable w);
    
    Writable getWriter();
    
    AbstractSimulation getSim();
    
    javax.swing.JPanel getPanel();
    
    SpatialArea getSpatialArea();
    
    void setSpatialArea(final SpatialArea s);
    
    void swapForceUpdater(final NBodyForceComputable fu);
    
    void clear();
    
    NbodyCollection getNCollection();
    
    void setNCollection(final NbodyCollection n);
    
}
