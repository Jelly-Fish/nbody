package com.jellyfish.jfgnbody.nbody.simulations;

import com.jellyfish.jfgnbody.nbody.NBody;
import com.jellyfish.jfgnbody.nbody.NbodyCollection;

/**
 *
 * @author thw
 */
public abstract class AbstractSimulation {
    
    /**
     * Nbody collection of bodies instance.
     */
    public NbodyCollection nBodies = null;
    
    /**
     * @param N body count.
     * @param nBody main handler instance.
     */
    public abstract void start(final int N, final NBody nBody);
    
}
