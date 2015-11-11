package com.jellyfish.jfgnbody.nbody.simulations;

import com.jellyfish.jfgnbody.nbody.NBody;

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
    
}
