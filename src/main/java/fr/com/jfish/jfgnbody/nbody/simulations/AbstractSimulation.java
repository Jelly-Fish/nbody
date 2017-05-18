package fr.com.jfish.jfgnbody.nbody.simulations;

import fr.com.jfish.jfgnbody.interfaces.NBodyDrawable;
import fr.com.jfish.jfgnbody.nbody.NbodyCollection;
import fr.com.jfish.jfgnbody.utils.RandUtils;

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
        final RandUtils.Layout l);
    
}
