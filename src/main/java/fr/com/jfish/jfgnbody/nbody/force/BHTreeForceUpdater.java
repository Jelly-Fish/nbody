package fr.com.jfish.jfgnbody.nbody.force;

import fr.com.jfish.jfgnbody.interfaces.NBodyForceComputable;
import fr.com.jfish.jfgnbody.nbody.NbodyCollection;
import fr.com.jfish.jfgnbody.nbody.barneshut.BHTCube;
import fr.com.jfish.jfgnbody.nbody.entities.Body;
import fr.com.jfish.jfgnbody.nbody.barneshut.BarnesHutTree;
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
    public void addForces(final int w, final int h, final BHTCube bhtcube, final NbodyCollection m) {
        
        final BarnesHutTree bhT = new BarnesHutTree(bhtcube);
        final int[] keys = new int[m.size() + this.mb.size()];
        int k = 0;
        int i = 0;
        
        while (m.perform(i)) {

             // If body still on screen (main quadrant) & not swallowed, add to tree.
            if (!m.c[i].isSwallowed() && m.c[i].in(bhtcube)) {
                bhT.insert(m.c[i]);
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
            
            if (m.c[j].isOutOfBounds(w, h)) {
                m.c[j].setSwallowed(true);
                continue;
            }
            
            m.c[j].resetForce();
            m.c[j].checkCollision(this.mb.values());
            if (m.c[j].in(bhtcube)) bhT.updateForce(m.c[j]);
            m.c[j].update(1e11); // Calculate new positions on time step dt (1e11 here).
        }
    }
        
    @Override
    public HashMap<Integer, Body> getMbs() {
        return mb;
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
