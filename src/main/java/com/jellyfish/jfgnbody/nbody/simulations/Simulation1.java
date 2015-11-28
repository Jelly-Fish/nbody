package com.jellyfish.jfgnbody.nbody.simulations;

import com.jellyfish.jfgnbody.interfaces.NBodyDrawable;
import com.jellyfish.jfgnbody.nbody.NbodyCollection;
import com.jellyfish.jfgnbody.nbody.constants.NBodyConst;
import com.jellyfish.jfgnbody.nbody.entities.Body;
import com.jellyfish.jfgnbody.nbody.entities.SupermassiveStaticBody;
import com.jellyfish.jfgnbody.utils.BodySimulationGenUtils;

/**
 * @author thw
 */
public class Simulation1 extends AbstractSimulation {
    
    private static final int M_COUNT = 1;
    
    @Override
    public void start(final int N, final NBodyDrawable n, final boolean bhTree) {
        
        double px, py, magv, absangle, thetav, phiv, vx, vy, mass;
        
        for (int i = 0; i < N; i++) {

            px = NBodyConst.NBODY_MASS_CONST * BodySimulationGenUtils.exp(-1.8) * (.5 - Math.random());
            py = NBodyConst.NBODY_MASS_CONST * BodySimulationGenUtils.exp(-1.8) * (.5 - Math.random());
            magv = BodySimulationGenUtils.circleV(px, py);

            absangle = Math.atan(Math.abs(py / px));
            thetav = Math.PI / 2 - absangle;
            phiv = Math.random() * Math.PI;
            vx = -1 * Math.signum(py) * Math.cos(thetav) * magv;
            vy = Math.signum(px) * Math.sin(thetav) * magv;

            mass = Math.random() * NBodyConst.SOLARMASS;
            n.getNB().put(i, new Body(i, px, py, vx, vy, mass, NBodyConst.BODY_COLOR));
        }

        /**
         * Put a supermassive body in the center - SupermassiveBody instances
         * will not be candidates to draw or paint methods.
         */
        final SupermassiveStaticBody smsb = new SupermassiveStaticBody(n.getNB().size(), 
                0, 0, 0, 0, 1e6 * NBodyConst.SOLARMASS, NBodyConst.SM_STATIC_BODY_COLOR);
        n.getNB().put(n.getNB().size(), smsb);
        n.getForceUpdater().getMbs().put(smsb.graphics.key, smsb);
    }

    @Override
    public void start(final NBodyDrawable n, final int N, final NbodyCollection m) {

        double px, py, magv, absangle, thetav, phiv, vx, vy, mass;
        
        for (int i = 0; i < N - Simulation1.M_COUNT; i++) {

            px = NBodyConst.NBODY_MASS_CONST * BodySimulationGenUtils.exp(-1.8) * (.5 - Math.random());
            py = NBodyConst.NBODY_MASS_CONST * BodySimulationGenUtils.exp(-1.8) * (.5 - Math.random());
            magv = BodySimulationGenUtils.circleV(px, py);

            absangle = Math.atan(Math.abs(py / px));
            thetav = Math.PI / 2 - absangle;
            phiv = Math.random() * Math.PI;
            vx = -1 * Math.signum(py) * Math.cos(thetav) * magv;
            vy = Math.signum(px) * Math.sin(thetav) * magv;

            mass = Math.random() * NBodyConst.SOLARMASS;
            m.add(new Body(i, px, py, vx, vy, mass, NBodyConst.BODY_COLOR));
        }

        /**
         * Put a supermassive body in the center - SupermassiveBody instances
         * will not be candidates to draw or paint methods.
         */
        final SupermassiveStaticBody smsb = new SupermassiveStaticBody(N - Simulation1.M_COUNT,
                0, 0, 0, 0, 1e6 * NBodyConst.SOLARMASS, NBodyConst.SM_STATIC_BODY_COLOR);
        m.add(smsb);
        n.getForceUpdater().getMbs().put(smsb.graphics.key, smsb);
    }
    
    @Override
    public String toString() {
        return "Initialize N bodies with random positions and circular velocities";
    }
    
}
