package com.jellyfish.jfgnbody.nbody.simulations;

import com.jellyfish.jfgnbody.nbody.NBody;
import com.jellyfish.jfgnbody.nbody.NbodyCollection;
import com.jellyfish.jfgnbody.nbody.constants.NBodyConst;
import com.jellyfish.jfgnbody.nbody.entities.Body;
import com.jellyfish.jfgnbody.nbody.entities.SupermassiveStaticBody;
import com.jellyfish.jfgnbody.utils.BodySimulationGenUtils;

/**
 *
 * @author thw
 */
public class Simulation1 extends AbstractSimulation {

    /**
     * Count of supper massive or massive entites to add to NBody collection instance.
     */
    private final int M_COUNT = 1;
    
    @Override
    public void start(final int N, final NBody nBody) {
        
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
            nBody.bodyMap.put(i, new Body(i, px, py, vx, vy, mass, NBodyConst.BODY_COLOR));
        }

        /**
         * Put a supermassive body in the center - SupermassiveBody instances
         * will not be candidates to draw or paint methods.
         */
        nBody.bodyMap.put(nBody.bodyMap.size(), new SupermassiveStaticBody(nBody.bodyMap.size(),
                0, 0, 0, 0, 1e6 * NBodyConst.SOLARMASS, NBodyConst.SM_STATIC_BODY_COLOR));
    }

    @Override
    public void start(final int N, final NbodyCollection m) {
        
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
            m.add(new Body(i, px, py, vx, vy, mass, NBodyConst.BODY_COLOR));
        }

        /**
         * Put a supermassive body in the center - SupermassiveBody instances
         * will not be candidates to draw or paint methods.
         */
        m.add(new SupermassiveStaticBody(N + M_COUNT,
                0, 0, 0, 0, 1e6 * NBodyConst.SOLARMASS, NBodyConst.SM_STATIC_BODY_COLOR));
    }
    
    @Override
    public String toString() {
        return "Initialize N bodies with random positions and circular velocities";
    }
    
}
