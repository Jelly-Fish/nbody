/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jellyfish.jfgnbody.nbody.simulations;

import com.jellyfish.jfgnbody.nbody.NBody;
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
        nBody.bodyMap.put(nBody.bodyMap.size() + 1, 
            new MassiveBody(nBody.bodyMap.size() + 1,
                NBodyConst.NBODY_MASS_CONST * (1.4), 1, 
                -28617.639985581613, 
                -1787.297295869821, 
                1e6 * (NBodyConst.SOLARMASS / 2), 
                NBodyConst.M_BODY_COLOR)); 
        nBody.bodyMap.put(nBody.bodyMap.size() + 1, 
            new MassiveBody(nBody.bodyMap.size() + 1,
                NBodyConst.NBODY_MASS_CONST * (-1.4), -1, 
                28617.639985581613 * 1.2, 
                1787.297295869821, 
                1e6 * (NBodyConst.SOLARMASS / 4), 
                NBodyConst.M_BODY_COLOR)); 
    }
    
    @Override
    public String toString() {
        return "N + 2 massive bodies (Solarmass / 2) & (Solarmass / 4).";
    }
    
}
