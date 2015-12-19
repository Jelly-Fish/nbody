package com.jellyfish.jfgnbody.nbody.simulations;

import com.jellyfish.jfgnbody.interfaces.NBodyDrawable;
import com.jellyfish.jfgnbody.nbody.NbodyCollection;
import com.jellyfish.jfgnbody.nbody.constants.NBodyConst;
import com.jellyfish.jfgnbody.nbody.entities.MassiveBody;
import java.awt.Color;

/**
 *
 * @author thw
 */
public class Simulation6  extends AbstractSimulation {

    @Override
    public void start(final int N, final NBodyDrawable n, final boolean bhTree) {
        new Simulation1().start(N, n, bhTree);
        int k = n.getNB().size();
        final MassiveBody mb1 = new MassiveBody(k, NBodyConst.NBODY_MASS_CONST * 1.4, 1, 
                -28617.639985581613, 
                -1787.297295869821, 
                1e6 * (NBodyConst.SOLARMASS / 2), 
                NBodyConst.M_BODY_COLOR);
        n.getForceUpdater().getMbs().put(mb1.graphics.key, mb1);
        if (bhTree) n.getNB().put(mb1.graphics.key, mb1);
        ++k;
        final MassiveBody mb2 = new MassiveBody(k, NBodyConst.NBODY_MASS_CONST * -0.4, 2.2, 
                15000.0, 
                -12000.0, 
                1e6 * (NBodyConst.SOLARMASS / 8), 
                NBodyConst.M_BODY_COLOR);
        mb2.graphics.color = Color.GREEN;
        mb2.graphics.graphicSize = 8;
        n.getForceUpdater().getMbs().put(mb2.graphics.key, mb2);
        if (bhTree) n.getNB().put(mb2.graphics.key, mb2);
    }

    @Override
    public void start(final NBodyDrawable n, final int N, final NbodyCollection m) {
        
        new Simulation1().start(n, N - 2, m);
        int k = n.getForceUpdater().getMbs().size();
        final MassiveBody mb1 = new MassiveBody(k, NBodyConst.NBODY_MASS_CONST * 1.4, 1, 
                -28617.639985581613, 
                -1787.297295869821, 
                1e6 * (NBodyConst.SOLARMASS / 2), 
                NBodyConst.M_BODY_COLOR);
        n.getForceUpdater().getMbs().put(mb1.graphics.key, mb1);
        m.add(mb1);
        
        ++k;
        final MassiveBody mb2 = new MassiveBody(k, NBodyConst.NBODY_MASS_CONST * -0.4, 2.2, 
                15000.0, 
                -12000.0, 
                1e6 * (NBodyConst.SOLARMASS / 8), 
                NBodyConst.M_BODY_COLOR);
        mb2.graphics.color = Color.GREEN;
        mb2.graphics.graphicSize = 8;
        n.getForceUpdater().getMbs().put(mb2.graphics.key, mb2);
        m.add(mb2);
    }
    
    @Override
    public String toString() {
        return "+ 2 massive body [m=1e6(sm/8)]+[m=1e6(sm/2)]";
    }
    
}
