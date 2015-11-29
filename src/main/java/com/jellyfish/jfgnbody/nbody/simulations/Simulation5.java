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
    public void start(final int N, final NBodyDrawable n, final boolean bhTree) {
        new Simulation1().start(N, n, bhTree);
        this.velocityX = -28617.0 / 50.0;
        this.velocityY = -1787.0 / 50.0;
        final int k = n.getNB().size();
        final SupermassiveBody smb = new SupermassiveBody(k, NBodyConst.NBODY_MASS_CONST * (1.4), 1, 
                this.velocityX, this.velocityY, 
                1e6 * (NBodyConst.SOLARMASS / 1.2),
                NBodyConst.M_BODY_COLOR);
        n.getForceUpdater().getMbs().put(smb.graphics.key, smb);
        if (bhTree) n.getNB().put(smb.graphics.key, smb);
    }

    @Override
    public void start(final NBodyDrawable n, final int N, final NbodyCollection m) {
        new Simulation1().start(n, N - 1, m);
        final SupermassiveBody smb = new SupermassiveBody(m.c.length - 1, NBodyConst.NBODY_MASS_CONST * (1.4), 1, 
                this.velocityX, this.velocityY, 
                1e6 * (NBodyConst.SOLARMASS / 1.2),
                NBodyConst.M_BODY_COLOR);
        n.getForceUpdater().getMbs().put(smb.graphics.key, smb);
    }
    
    @Override
    public String toString() {
        return "N + 1 supermassive body [m=1e6(solarmass/1.2)]";
    }
    
}
