package fr.com.jfish.jfgnbody.nbody.constants;

import fr.com.jfish.jfgnbody.nbody.simulations.AbstractSimulation;
import fr.com.jfish.jfgnbody.nbody.simulations.*;

/**
 *
 * @author thw
 */
public class NBodySimulations {
    
    public static final AbstractSimulation[] sims = new AbstractSimulation[] 
    {
        new Simulation1(), new Simulation2(), new Simulation3(), new Simulation4(),
        new Simulation5(), new Simulation6()
    };
    
}
