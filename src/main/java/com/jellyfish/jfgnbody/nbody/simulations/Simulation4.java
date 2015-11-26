package com.jellyfish.jfgnbody.nbody.simulations;

import com.jellyfish.jfgnbody.interfaces.NBodyDrawable;
import com.jellyfish.jfgnbody.nbody.NbodyCollection;
import com.jellyfish.jfgnbody.nbody.constants.NBodyConst;
import com.jellyfish.jfgnbody.nbody.entities.MassiveBody;

/**
 *
 * @author thw
 */
public class Simulation4 extends AbstractSimulation {
    
    @Override
    public void start(final int N, final NBodyDrawable n) {
        
        new Simulation1().start(N, n);
        final int k = n.getNB().size();
        final MassiveBody mb = new MassiveBody(k, NBodyConst.NBODY_MASS_CONST * 3.0, 1, 
                -28617.639985581613 * 4, 
                -1787.297295869821, 
                1e6 * (NBodyConst.SOLARMASS / 1.2),
                NBodyConst.M_BODY_COLOR);
        n.getForceUpdater().getMbs().add(mb);
    }

    @Override
    public void start(final NBodyDrawable n, final int N, final NbodyCollection m) {
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
