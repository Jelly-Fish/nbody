package fr.com.jfish.jfgnbody.interfaces;

import fr.com.jfish.jfgnbody.nbody.NbodyCollection;
import fr.com.jfish.jfgnbody.nbody.barneshut.BHTCube;
import fr.com.jfish.jfgnbody.nbody.entities.Body;
import java.util.HashMap;

/**
 *
 * @author thw
 */
public interface NBodyForceComputable {

    void addForces(final int w, final int h, final BHTCube bhtcube, final NbodyCollection m);
    
    HashMap<Integer, Body> getMbs();
    
    void cleanBodyCollection();
    
    boolean isBHtree();
    
}
