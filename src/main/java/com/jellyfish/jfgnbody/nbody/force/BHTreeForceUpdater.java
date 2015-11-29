package com.jellyfish.jfgnbody.nbody.force;

import com.jellyfish.jfgnbody.interfaces.NBodyForceComputable;
import com.jellyfish.jfgnbody.nbody.NbodyCollection;
import com.jellyfish.jfgnbody.nbody.entities.Body;
import com.jellyfish.jfgnbody.nbody.barneshut.BarnesHutTree;
import com.jellyfish.jfgnbody.nbody.barneshut.Quadrant;
import com.jellyfish.jfgnbody.nbody.entities.MassiveBody;
import java.util.HashMap;

/**
 *
 * @author thw
 */
public class BHTreeForceUpdater implements NBodyForceComputable {

    /**
     * Massive body list.
     */
    private final HashMap<Integer, Body> mb = new HashMap<>();
    
    @Override
    public void addForces(final int w, final int h, final Quadrant q, final HashMap<Integer, Body> m) {

        final BarnesHutTree bhT = new BarnesHutTree(q);

        // If the body is still on the screen, add it to the tree
        for (Body b : m.values()) {
            if (b.in(q)) bhT.insert(b);
        }
            
        /**
         * Use out methods in BarnesHutTree for updating forces traveling
         * recursively through the tree - Only check collisions with MassiveBody 
         * instances.
         */
        for (Body b : m.values()) {
            b.resetForce();
            if (b.in(q)) {
                b.checkCollision(this.mb.values());
                bhT.updateForce(b);
            }
            b.update(1e11); // Calculate new positions on time step dt (1e11 here).
        }
    }
    
    
    @Override
    public HashMap<Integer, Body> getMbs() {
        return mb;
    }

    @Override
    public void addForces(final int w, final int h, final Quadrant q, final NbodyCollection m) {
        
        final BarnesHutTree bhT = new BarnesHutTree(q);
        final int[] keys = new int[m.size()];
        int k = 0;
        int i = 0;
        
        while (m.perform(i)) {
            if (m.c[i].in(q)) {
                bhT.insert(m.c[i]); // If body still on screen, add to tree.
                keys[k] = i;
                ++k;
            } 
            ++i;
        }
            
        /**
         * Use out methods in BarnesHutTree for updating forces traveling
         * recursively through the tree - Only check collisions with SupermassiveBody 
         * instances - Only work for and if there is only 1 SupermassiveBody instance.
         */
        for (int j : keys) {
            m.c[j].resetForce();
            m.c[j].checkCollision(this.mb.values());
            if (m.c[j].in(q)) {
                bhT.updateForce(m.c[j]);
                m.c[j].update(1e11); // Calculate new positions on time step dt (1e11 here).
            }
        }
    }

    @Override
    public boolean isBHtree() {
        return true;
    }
    
    @Override
    public void cleanBodyCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
