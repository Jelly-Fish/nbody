package com.jellyfish.jfgnbody.nbody.simulations;

import com.jellyfish.jfgnbody.interfaces.NBodyDrawable;
import com.jellyfish.jfgnbody.nbody.NbodyCollection;
import com.jellyfish.jfgnbody.utils.Rand2DCUtils;

/**
 *
 * @author thw
 */
public abstract class AbstractSimulation {

    /**
     * @param N body count.
     * @param n main handler instance.
     * @param bhTree BHTree algorithm used ?
     */
    public abstract void start(final int N, final NBodyDrawable n, final boolean bhTree);
    
    /**
     * @param n
     * @param N 
     * @param nBody 
     * @param l random layout style.
     */
    public abstract void start(final NBodyDrawable n, final int N, final NbodyCollection nBody,
        final Rand2DCUtils.Layout l);
    
}
