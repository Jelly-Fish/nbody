package fr.com.jfish.jfgnbody.nbody.simulations;

import fr.com.jfish.jfgnbody.interfaces.NBodyDrawable;
import fr.com.jfish.jfgnbody.nbody.NbodyCollection;
import fr.com.jfish.jfgnbody.nbody.constants.NBodyConst;
import fr.com.jfish.jfgnbody.nbody.entities.MassiveBody;
import fr.com.jfish.jfgnbody.utils.Rand2DCUtils;

/**
 *
 * @author thw
 */
public class Simulation2 extends AbstractSimulation {

    private static final int M_COUNT = 2;
    
    @Override
    public void start(final int N, final NBodyDrawable n, final boolean bhTree) {
        
        new Simulation1().start(N, n, bhTree);
        final int k = n.getNB().size();
        final MassiveBody mb = new MassiveBody(k, NBodyConst.NBODY_MASS_CONST * 1.4d, 1d, 1d,
                -28617.639985581613d, 
                -1787.297295869821d, 
                -1787.297295869821d, 
                1e6 * (NBodyConst.SOLARMASS / 4), 
                NBodyConst.M_BODY_COLOR);
        n.getForceUpdater().getMbs().put(mb.graphics.key, mb);
        if (bhTree) n.getNB().put(mb.graphics.key, mb);
    }

    @Override
    public void start(final NBodyDrawable n, final int N, final NbodyCollection m, final Rand2DCUtils.Layout l) {
        
        new Simulation1().start(n, N - 1, m, l);
        final MassiveBody mb = new MassiveBody(m.c.length - 1, NBodyConst.NBODY_MASS_CONST * (1.4d), 1d, 1d,
                -28617.639985581613d, 
                -1787.297295869821d, 
                -1787.297295869821d, 
                1e6 * (NBodyConst.SOLARMASS / 4), 
                NBodyConst.M_BODY_COLOR);
        m.add(mb);
        n.getForceUpdater().getMbs().put(mb.graphics.key, mb);
    }
        
    @Override
    public String toString() {
        return "N bodies + 1 colliding massive body [m=1e6(solormass/4)]";
    }
    
}
