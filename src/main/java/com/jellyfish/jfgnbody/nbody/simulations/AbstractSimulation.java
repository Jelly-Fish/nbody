package com.jellyfish.jfgnbody.nbody.simulations;

import com.jellyfish.jfgnbody.nbody.NBody;
import com.jellyfish.jfgnbody.nbody.NbodyCollection;

/**
 *
 * @author thw
 */
public abstract class AbstractSimulation {
      
    /**
     * @param N body count.
     * @param nBody main handler instance.
     */
    public abstract void start(final int N, final NBody nBody);
    
    /**
     * @param N 
     * @param m 
     */
    public abstract void start(final int N, final NbodyCollection m);
    
}
