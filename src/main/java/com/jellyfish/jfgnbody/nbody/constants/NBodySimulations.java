/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jellyfish.jfgnbody.nbody.constants;

import com.jellyfish.jfgnbody.nbody.simulations.AbstractSimulation;
import com.jellyfish.jfgnbody.nbody.simulations.*;

/**
 *
 * @author thw
 */
public class NBodySimulations {
    
    public static final AbstractSimulation[] sims = new AbstractSimulation[] 
    {
        new Simulation1(), new Simulation2(), new Simulation3(), new Simulation4(),
        new Simulation5()
    };
    
}
