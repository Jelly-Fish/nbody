package com.jellyfish.jfgnbody.nbody.force;

import com.jellyfish.jfgnbody.interfaces.NBodyForceComputable;
import com.jellyfish.jfgnbody.nbody.entities.Body;
import com.jellyfish.jfgnbody.nbody.barneshut.BarnesHutTree;
import com.jellyfish.jfgnbody.nbody.barneshut.Quadrant;
import com.jellyfish.jfgnbody.nbody.entities.MassiveBody;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 *
 * @author thw
 */
public class BHTreeForceUpdater implements NBodyForceComputable {

    /**
     * Massive body list.
     */
    private final ArrayList<Body> mb = new ArrayList<>();
    
    @Override
    public void addForces(final int w, final int h, final Quadrant q, final LinkedHashMap<Integer, Body> m) {
        
        this.mb.clear();
        final BarnesHutTree bhT = new BarnesHutTree(q);
        
        // If the body is still on the screen, add it to the tree
        for (Body b : m.values()) {
            if (b.in(q)) bhT.insert(b);
            if (b instanceof MassiveBody) this.mb.add(b);
        }
            
        /**
         * Use out methods in BarnesHutTree for updating forces traveling
         * recursively through the tree - Only check collisions with SupermassiveBody 
         * instances - Only work for and if there is only 1 SupermassiveBody instance.
         */
        for (Body b : m.values()) {
            if (!b.isOutOfBounds(w, h)) {
                b.resetForce();
                b.checkCollision(this.mb);
                if (b.in(q)) {
                    bhT.updateForce(b);
                    // Calculate the new positions on a time step dt (1e11 here) :
                    b.update(1e11);
                }
            } else {
                b.setSwallowed(true);
            }
        }
    }
    
}
