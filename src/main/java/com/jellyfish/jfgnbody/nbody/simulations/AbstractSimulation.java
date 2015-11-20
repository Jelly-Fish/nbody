package com.jellyfish.jfgnbody.nbody.simulations;

import com.jellyfish.jfgnbody.nbody.NBody;
import com.jellyfish.jfgnbody.nbody.NbodyCollection;

/**
 *
 * @author thw
 */
public abstract class AbstractSimulation {
   
    /**
     * NBody instance that runs this simulation.
     */
    protected NBody nBody = null;

    /**
     * @param N body count.
     * @param nBody main handler instance.
     */
    public abstract void start(final int N, final NBody nBody);
    
    /**
     * @param n
     * @param N 
     * @param nBody 
     */
    public abstract void start(final NBody n, final int N, final NbodyCollection nBody);
    
}
