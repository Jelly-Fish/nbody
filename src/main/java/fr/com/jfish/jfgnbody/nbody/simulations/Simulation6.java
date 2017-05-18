package fr.com.jfish.jfgnbody.nbody.simulations;

import fr.com.jfish.jfgnbody.interfaces.NBodyDrawable;
import fr.com.jfish.jfgnbody.nbody.NbodyCollection;
import fr.com.jfish.jfgnbody.nbody.constants.NBodyConst;
import fr.com.jfish.jfgnbody.nbody.entities.MassiveBody;
import fr.com.jfish.jfgnbody.utils.RandUtils;
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
        final MassiveBody mb1 = new MassiveBody(k, NBodyConst.NBODY_MASS_CONST * 1.4d, 1d, 1d, 
                -28617.639985581613d, 
                -1787.297295869821d, 
                -1787.297295869821d, 
                1e6 * (NBodyConst.SOLARMASS / 2), 
                NBodyConst.M_BODY_COLOR);
        n.getForceUpdater().getMbs().put(mb1.graphics.key, mb1);
        if (bhTree) n.getNB().put(mb1.graphics.key, mb1);
        ++k;
        final MassiveBody mb2 = new MassiveBody(k, NBodyConst.NBODY_MASS_CONST * -0.4d, 2.2d, 1d,
                15000.0d, 
                -12000.0d, 
                -12000.0d, 
                1e6 * (NBodyConst.SOLARMASS / 8), 
                NBodyConst.M_BODY_COLOR);
        mb2.graphics.color = Color.GREEN;
        mb2.graphics.graphicSize = 8;
        n.getForceUpdater().getMbs().put(mb2.graphics.key, mb2);
        if (bhTree) n.getNB().put(mb2.graphics.key, mb2);
    }

    @Override
    public void start(final NBodyDrawable n, final int N, final NbodyCollection m, final RandUtils.Layout l) {
        
        new Simulation1().start(n, N - 2, m, l);
        int k = n.getForceUpdater().getMbs().size();
        final MassiveBody mb1 = new MassiveBody(k, NBodyConst.NBODY_MASS_CONST * 1.4d, 1d, 1d, 
                -28617.639985581613d, 
                -1787.297295869821d, 
                -1787.297295869821d, 
                1e6 * (NBodyConst.SOLARMASS / 2), 
                NBodyConst.M_BODY_COLOR);
        n.getForceUpdater().getMbs().put(mb1.graphics.key, mb1);
        m.add(mb1);
        
        ++k;
        final MassiveBody mb2 = new MassiveBody(k, NBodyConst.NBODY_MASS_CONST * -0.4d, 2.2d, 1d,
                15000.0d, 
                -12000.0d, 
                -12000.0d, 
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
