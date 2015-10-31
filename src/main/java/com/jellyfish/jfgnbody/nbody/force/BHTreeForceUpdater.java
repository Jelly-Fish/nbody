package com.jellyfish.jfgnbody.nbody.force;

import com.jellyfish.jfgnbody.interfaces.NBodyForceComputable;
import com.jellyfish.jfgnbody.nbody.Body;
import com.jellyfish.jfgnbody.nbody.barneshut.BarnesHutTree;
import com.jellyfish.jfgnbody.nbody.barneshut.Quadrant;
import java.util.HashMap;

/**
 *
 * @author thw
 */
public class BHTreeForceUpdater implements NBodyForceComputable {

    @Override
    public void addForces(final int w, final int h, final Quadrant q, final HashMap<Integer, Body> m) {
        final BarnesHutTree bhT = new BarnesHutTree(q);
        // If the body is still on the screen, add it to the tree
        for (Body b : m.values()) {
            if (b.in(q)) bhT.insert(b);
        }
            
        /**
         * Use out methods in BarnesHutTree for updating forces traveling
         * recursively through the tree.
         */
        for (Body b : m.values()) {
            //b.resetForce();
            if (b.in(q)) {
                bhT.updateForce(b);
                // Calculate the new positions on a time step dt (1e11 here) :
                b.update(1e11);
            }
        }
    }
    
}
