package fr.com.jfish.jfgnbody.nbody.force;

import fr.com.jfish.jfgnbody.interfaces.NBodyForceComputable;
import fr.com.jfish.jfgnbody.nbody.NbodyCollection;
import fr.com.jfish.jfgnbody.nbody.entities.Body;
import fr.com.jfish.jfgnbody.nbody.barneshut.Quadrant;
import java.util.HashMap;

/**
 * @author thw
 */
public class ForceUpdater implements NBodyForceComputable {

    /**
     * Massive body list.
     */
    private final HashMap<Integer, Body> mb = new HashMap<>();

    @Override
    public void addForces(final int w, final int h, final Quadrant q, final NbodyCollection m) {
        
        int i = 0;
        while (m.perform(i)) {
                        
            m.c[i].resetForce();
            for (Body mB : this.mb.values()) {
                
                if (m.c[i].isMassive()) { 
                    // Optional : if resetForce unused, MB will speed up under SMSB's pull.
                    mB.resetForce();
                    mB.addForce(m.c[i]);
                    //mB.checkCollision(m.c[i]);
                    mB.update(1e11);
                } else {
                    m.c[i].addForce(mB);
                    //m.c[i].checkCollision(mB); 
                }
                
                if (!m.c[i].isOutOfBounds(w, h)) {
                    mB.setSwallowed(true);
                }
            }
            
            if (!m.c[i].isOutOfBounds(w, h)) {
                m.c[i].update(1e11);
            } else {
                m.c[i].setSwallowed(true);
            }
        }
    }
    
    @Override
    public HashMap<Integer, Body> getMbs() {
        return this.mb;
    }

    @Override
    public void cleanBodyCollection() {
        throw new UnsupportedOperationException();
        /**
         * FIXME : called on first loops, not with collision between smsb & mb.
         */
        /*final int[] keys = new int[mb.size()];
        int i = 0;
        for (Body b : mb.values()) {
            if (b.isSwallowed()) {
                keys[i] = b.graphics.key;
                ++i;
            }
        }

        for (int j = 0; j < i; j++) mb.remove(keys[j]);*/      
    }

    @Override
    public boolean isBHtree() {
        return false;
    }
    
}
