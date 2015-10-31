package com.jellyfish.jfgnbody.nbody.force;

import com.jellyfish.jfgnbody.interfaces.NBodyForceComputable;
import com.jellyfish.jfgnbody.nbody.Body;
import com.jellyfish.jfgnbody.nbody.barneshut.Quadrant;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author thw
 */
public class ForceUpdater implements NBodyForceComputable {

    @Override
    public void addForces(final int w, final int h, final Quadrant q, final HashMap<Integer, Body> m) {
        
        for (Map.Entry<Integer, Body> eA : m.entrySet()) {
            eA.getValue().resetForce();
            // N^2 complexity...
            for (Map.Entry<Integer, Body> eB : m.entrySet()) {
                if (!eA.getKey().equals(eB.getKey())) {
                    eA.getValue().addForce(eB.getValue());
                    eA.getValue().checkCollision(eB.getValue());   
                }
            }
        }
        
        // Loop again and update the bodies using timestep param if tehy are still
        // in the bounds of 0,0,w,h quadrant.
        for (Body b : m.values()) {
            if (!b.isOutOfBounds(w, h)) {
                b.update(1e11);
            } else {
                b.swallowed = true;
            }
        }
    }
    
}
