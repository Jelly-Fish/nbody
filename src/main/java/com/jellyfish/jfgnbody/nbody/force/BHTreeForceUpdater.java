package com.jellyfish.jfgnbody.nbody.force;

import com.jellyfish.jfgnbody.interfaces.NBodyForceComputable;
import com.jellyfish.jfgnbody.nbody.NbodyCollection;
import com.jellyfish.jfgnbody.nbody.entities.Body;
import com.jellyfish.jfgnbody.nbody.barneshut.BarnesHutTree;
import com.jellyfish.jfgnbody.nbody.barneshut.Quadrant;
import com.jellyfish.jfgnbody.nbody.entities.MassiveBody;
import java.util.ArrayList;
import java.util.HashMap;

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
            if (!b.isOutOfBounds(w, h)) {
                b.resetForce();
                if (b.in(q)) {
                    b.checkCollision(this.mb);
                    bhT.updateForce(b);
                    b.update(1e11); // Calculate new positions on time step dt (1e11 here).
                }
            } else {
               b.setSwallowed(true);
            }
        }
    }
    
    
    @Override
    public ArrayList<Body> getMbs() {
        return mb;
    }

    @Override
    public void addForces(final int w, final int h, final Quadrant q, final NbodyCollection m) {
        
        this.mb.clear();
        final BarnesHutTree bhT = new BarnesHutTree(q);
        final int[] keys = new int[m.size()];
        int k = 0; // keys max index.
        
        int i = 0;
        while (m.perform(i)) {
            
            if (m.c[i] instanceof MassiveBody) this.mb.add(m.c[i]);
            
            if (m.c[i].in(q)) {
                bhT.insert(m.c[i]); // If body still on screen, add to tree.
                keys[i] = m.c[i].graphics.key;
                ++k;
            } 
            ++i;
        }
            
        /**
         * Use out methods in BarnesHutTree for updating forces traveling
         * recursively through the tree - Only check collisions with SupermassiveBody 
         * instances - Only work for and if there is only 1 SupermassiveBody instance.
         */
        for (int j = 0; j < k && m.perform(j); ++j) {
            m.c[keys[j]].resetForce();
            //m.c[keys[j]].checkCollision(this.mb);
            if (m.c[keys[j]].in(q)) {
                bhT.updateForce(m.c[keys[j]]);
                m.c[keys[j]].update(1e11); // Calculate new positions on time step dt (1e11 here).
            }
        }
    }

    @Override
    public void cleanBodyCollection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
