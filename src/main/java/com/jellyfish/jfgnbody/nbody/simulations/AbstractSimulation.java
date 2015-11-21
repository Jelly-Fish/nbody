package com.jellyfish.jfgnbody.nbody.simulations;

import com.jellyfish.jfgnbody.interfaces.NBodyDrawable;
import com.jellyfish.jfgnbody.nbody.NbodyCollection;

/**
 *
 * @author thw
 */
public abstract class AbstractSimulation {

    /**
     * @param N body count.
     * @param n main handler instance.
     */
    public abstract void start(final int N, final NBodyDrawable n);
    
    /**
     * @param n
     * @param N 
     * @param nBody 
     */
    public abstract void start(final NBodyDrawable n, final int N, final NbodyCollection nBody);
    
}
