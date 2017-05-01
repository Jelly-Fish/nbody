package fr.com.jfish.jfgnbody.interfaces;

import fr.com.jfish.jfgnbody.nbody.NbodyCollection;
import fr.com.jfish.jfgnbody.nbody.entities.Body;
import fr.com.jfish.jfgnbody.nbody.simulations.AbstractSimulation;
import fr.com.jfish.jfgnbody.nbody.space.SpatialArea;
import fr.com.jfish.jfgnbody.utils.Rand2DCUtils;
import java.util.HashMap;

/**
 *
 * @author thw
 */
public interface NBodyDrawable {
    
    void cleanBodyCollection();

    boolean performPaint();

    void restart(int n, int iSpeed, final AbstractSimulation sim, final Rand2DCUtils.Layout l);
    
    HashMap<Integer, Body> getNB();
    
    void setWriter(final Writable w);
    
    Writable getWriter();
    
    AbstractSimulation getSim();
    
    NBodyForceComputable getForceUpdater();
    
    javax.swing.JPanel getPanel();
    
    SpatialArea getSpatialArea();
    
    void setSpatialArea(final SpatialArea s);
    
    void swapForceUpdater(final NBodyForceComputable fu);
    
    void clear();
    
    NbodyCollection getNCollection();
    
    void setNCollection(final NbodyCollection n);
    
}
