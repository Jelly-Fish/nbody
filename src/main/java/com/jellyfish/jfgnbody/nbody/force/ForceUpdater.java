package com.jellyfish.jfgnbody.nbody.force;

import com.jellyfish.jfgnbody.interfaces.NBodyForceComputable;
import com.jellyfish.jfgnbody.nbody.NbodyCollection;
import com.jellyfish.jfgnbody.nbody.entities.Body;
import com.jellyfish.jfgnbody.nbody.barneshut.Quadrant;
import java.util.HashMap;

/**
 *
 * @author thw
 */
public class ForceUpdater implements NBodyForceComputable {

    /**
     * Massive body list.
     */
    private final HashMap<Integer, Body> mb = new HashMap<>();

    @Override
    public void addForces(final int w, final int h, final Quadrant q, final NbodyCollection m) {
        
        for (Body b : m.c) {
            
            if (b == null) continue;
            
            b.resetForce();
            for (Body mB : this.mb.values()) {
                b.addForce(mB);
                b.checkCollision(mB); 
                if (b.isMassive()) { 
                    // Optional : if resetForce unused, MB will speed up under SMSB's pull.
                    mB.resetForce();
                    mB.addForce(b);
                    mB.checkCollision(b);
                    mB.update(1e11);
                }
                if (!b.isOutOfBounds(w, h)) {
                    mB.setSwallowed(true);
                }
            }
            
            if (!b.isOutOfBounds(w, h)) {
                b.update(1e11);
            } else {
                b.setSwallowed(true);
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
