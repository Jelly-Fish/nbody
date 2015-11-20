/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jellyfish.jfgnbody.nbody.simulations;

import com.jellyfish.jfgnbody.nbody.NBody;
import com.jellyfish.jfgnbody.nbody.NbodyCollection;
import com.jellyfish.jfgnbody.nbody.constants.NBodyConst;
import com.jellyfish.jfgnbody.nbody.entities.MassiveBody;

/**
 *
 * @author thw
 */
public class Simulation4 extends AbstractSimulation {
    
    @Override
    public void start(final int N, final NBody nBody) {
        new Simulation1().start(N, nBody);
        final int k = nBody.bodyMap.size();
        nBody.bodyMap.put(k, new MassiveBody(k, NBodyConst.NBODY_MASS_CONST * (1.4), 1, 
                -28617.639985581613, 
                -1787.297295869821, 
                1e6 * (NBodyConst.SOLARMASS / 1.2),
                NBodyConst.M_BODY_COLOR)); 
    }

    @Override
    public void start(final NBody n, final int N, final NbodyCollection m) {
        new Simulation1().start(n, N - 1, m);
        m.add(new MassiveBody(m.c.length - 1, NBodyConst.NBODY_MASS_CONST * (1.4), 1, 
                -28617.639985581613, 
                -1787.297295869821, 
                1e6 * (NBodyConst.SOLARMASS / 1.2),
                NBodyConst.M_BODY_COLOR)); 
    }
        
    @Override
    public String toString() {
        return "N + 1 massive body mass = (solarmass/1.2)";
    }
    
}
