package com.jellyfish.jfgnbody.nbody.simulations;

import com.jellyfish.jfgnbody.interfaces.NBodyDrawable;
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
    public void start(final int N, final NBodyDrawable n) {
        new Simulation1().start(N, n);
        this.velocityX = -28617.0 / 50.0;
        this.velocityY = -1787.0 / 50.0;
        final int k = n.getNB().size();
        final SupermassiveBody smb = new SupermassiveBody(k, NBodyConst.NBODY_MASS_CONST * (1.4), 1, 
                this.velocityX, this.velocityY, 
                1e6 * (NBodyConst.SOLARMASS / 1.2),
                NBodyConst.M_BODY_COLOR);
        n.getNB().put(k, smb); 
        n.getForceUpdater().getMbs().add(smb);
    }

    @Override
    public void start(final NBodyDrawable n, final int N, final NbodyCollection m) {
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
