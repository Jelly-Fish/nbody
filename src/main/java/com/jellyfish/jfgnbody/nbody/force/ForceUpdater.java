package com.jellyfish.jfgnbody.nbody.force;

import com.jellyfish.jfgnbody.interfaces.NBodyForceComputable;
import com.jellyfish.jfgnbody.nbody.NbodyCollection;
import com.jellyfish.jfgnbody.nbody.entities.Body;
import com.jellyfish.jfgnbody.nbody.barneshut.Quadrant;
import com.jellyfish.jfgnbody.nbody.entities.SupermassiveStaticBody;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author thw
 */
public class ForceUpdater implements NBodyForceComputable {

    /**
     * Massive body list.
     */
    private final ArrayList<Body> mb = new ArrayList<>();
    
    @Override
    public void addForces(final int w, final int h, final Quadrant q, final HashMap<Integer, Body> m) {
        
        for (Body b : m.values()) {
            
            b.resetForce();
            for (Body mB : this.mb) {
                b.addForce(mB);
                b.checkCollision(mB); 
                if (b instanceof SupermassiveStaticBody) {
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
    public void addForces(final int w, final int h, final Quadrant q, final NbodyCollection m) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public ArrayList<Body> getMbs() {
        return this.mb;
    }

    @Override
    public void cleanBodyCollection() {
        /**
         * FIXME : Exception in thread "AWT-EventQueue-0" java.util.ConcurrentModificationException
	 * at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:901)
	 * at java.util.ArrayList$Itr.next(ArrayList.java:851)
         * ...
        for (Body b : this.mb) {
            if (b.isSwallowed()) this.mb.remove(b);
        }
        */
    }
    
}
