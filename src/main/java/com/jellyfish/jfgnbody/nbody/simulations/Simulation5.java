package com.jellyfish.jfgnbody.nbody.simulations;

import com.jellyfish.jfgnbody.nbody.NBody;
import com.jellyfish.jfgnbody.nbody.NbodyCollection;
import com.jellyfish.jfgnbody.nbody.constants.NBodyConst;
import com.jellyfish.jfgnbody.nbody.entities.SupermassiveBody;

/**
 *
 * @author thw
 */
public class Simulation5 extends AbstractSimulation {

    private double velocityX = 0.0;
    private double velocityY = 0.0;
    
    @Override
    public void start(final int N, final NBody nBody) {
        new Simulation1().start(N, nBody);
        this.velocityX = -28617.0 / 50.0;
        this.velocityY = -1787.0 / 50.0;
        final int k = nBody.bodyMap.size();
        nBody.bodyMap.put(k, new SupermassiveBody(k, NBodyConst.NBODY_MASS_CONST * (1.4), 1, 
                this.velocityX, this.velocityY, 
                1e6 * (NBodyConst.SOLARMASS / 1.2),
                NBodyConst.M_BODY_COLOR)); 
    }

    @Override
    public void start(final NBody n, final int N, final NbodyCollection m) {
        new Simulation1().start(n, N - 1, m);
        m.add(new SupermassiveBody(m.c.length - 1, NBodyConst.NBODY_MASS_CONST * (1.4), 1, 
                this.velocityX, this.velocityY, 
                1e6 * (NBodyConst.SOLARMASS / 1.2),
                NBodyConst.M_BODY_COLOR));
    }
    
    @Override
    public String toString() {
        return "N + 1 supermassive body (solarmass/1.2) on slow collision course";
    }
    
}