package com.jellyfish.jfgnbody.utils;

import com.jellyfish.jfgnbody.nbody.entities.Body;
import com.jellyfish.jfgnbody.nbody.NBody;
import com.jellyfish.jfgnbody.nbody.entities.SupermassiveBody;
import com.jellyfish.jfgnbody.nbody.constants.NBodyConst;
import com.jellyfish.jfgnbody.nbody.entities.MassiveBody;

/**
 *
 * @author thw
 */
public class BodyGenerationUtils {
    
    /**
     * Initialize N bodies with random positions and circular velocities.
     *
     * @param N
     * @param nBody
     */
    public static final void startBodies(final int N, final NBody nBody) {

        double px, py, magv, absangle, thetav, phiv, vx, vy, mass;
        
        for (int i = 0; i < N; i++) {

            px = NBodyConst.NBODY_MASS_CONST * BodyGenerationUtils.exp(-1.8) * (.5 - Math.random());
            py = NBodyConst.NBODY_MASS_CONST * BodyGenerationUtils.exp(-1.8) * (.5 - Math.random());
            magv = BodyGenerationUtils.circleV(px, py);

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
        nBody.bodyMap.put(nBody.bodyMap.size(), new SupermassiveBody(nBody.bodyMap.size(),
                0, 0, 0, 0, 1e6 * NBodyConst.SOLARMASS, NBodyConst.SM_BODY_COLOR));
    }
    
    /**
     * Initialize N bodies with random positions and circular velocities,
     * also add N sub massive bodies.
     *
     * @param N
     * @param nBody
     * @param smbN SupermassiveBody N
     */
    public static final void startBodies(final int N, final NBody nBody, final int smbN) {
        
        BodyGenerationUtils.startBodies(N, nBody);
        
        double px, py, magv, absangle, thetav, phiv, vx, vy;
        
        for (int i = 0; i < smbN; i++) {
            
            px = NBodyConst.NBODY_MASS_CONST * BodyGenerationUtils.exp(-1.8) * (.5 - Math.random());
            py = NBodyConst.NBODY_MASS_CONST * BodyGenerationUtils.exp(-1.8) * (.5 - Math.random());
            magv = BodyGenerationUtils.circleV(px, py);
            
            absangle = Math.atan(Math.abs(py / px));
            thetav = Math.PI / 2 - absangle;
            phiv = Math.random() * Math.PI;
            vx = -1 * Math.signum(py) * Math.cos(thetav) * magv;
            vy = Math.signum(px) * Math.sin(thetav) * magv;
            
            nBody.bodyMap.put(nBody.bodyMap.size() + i, new MassiveBody(nBody.bodyMap.size() + i,
                NBodyConst.NBODY_MASS_CONST * (1.4), 1, vx, vy, 1e6 * (NBodyConst.SOLARMASS / 3), 
                NBodyConst.M_BODY_COLOR));            
        }
    }

    /**
     * @param lambda
     * @return
     */
    private static double exp(final double lambda) {
        return -Math.log(1 - Math.random()) / lambda;
    }
    
    /**
     * the bodies are initialized in circular orbits around the central mass,
     * some physics to do so.
     *
     * @param rx
     * @param ry
     * @return
     */
    private static double circleV(final double rx, final double ry) {

        final double solarmass = 1.98892e30;
        double r2 = Math.sqrt(rx * rx + ry * ry);
        double numerator = (6.67e-11) * 1e6 * solarmass;
        return Math.sqrt(numerator / r2);
    }
    
}
