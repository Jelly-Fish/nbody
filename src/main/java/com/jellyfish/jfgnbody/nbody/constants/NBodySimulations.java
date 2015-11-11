/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jellyfish.jfgnbody.nbody.constants;

import com.jellyfish.jfgnbody.nbody.simulations.AbstractSimulation;
import com.jellyfish.jfgnbody.nbody.simulations.Simulation1;
import com.jellyfish.jfgnbody.nbody.simulations.Simulation2;
import com.jellyfish.jfgnbody.nbody.simulations.Simulation3;
import com.jellyfish.jfgnbody.nbody.simulations.Simulation4;

/**
 *
 * @author thw
 */
public class NBodySimulations {
    
    public static final AbstractSimulation[] sims = new AbstractSimulation[] 
    {
        new Simulation1(), new Simulation2(), new Simulation3(), new Simulation4()
    };
    
}
