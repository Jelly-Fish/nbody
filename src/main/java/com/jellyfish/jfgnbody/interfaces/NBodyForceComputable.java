package com.jellyfish.jfgnbody.interfaces;

import com.jellyfish.jfgnbody.nbody.NbodyCollection;
import com.jellyfish.jfgnbody.nbody.barneshut.Quadrant;
import com.jellyfish.jfgnbody.nbody.entities.Body;
import java.util.LinkedHashMap;

/**
 *
 * @author thw
 */
public interface NBodyForceComputable {
    
    void addForces(final int w, final int h, final Quadrant q, final LinkedHashMap<Integer, Body> m);
    
    void addForces(final int w, final int h, final Quadrant q, final NbodyCollection m);
}
