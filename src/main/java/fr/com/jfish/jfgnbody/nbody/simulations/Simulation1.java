package fr.com.jfish.jfgnbody.nbody.simulations;

import fr.com.jfish.jfgnbody.interfaces.NBodyDrawable;
import fr.com.jfish.jfgnbody.nbody.NbodyCollection;
import fr.com.jfish.jfgnbody.nbody.constants.NBodyConst;
import fr.com.jfish.jfgnbody.nbody.entities.Body;
import fr.com.jfish.jfgnbody.nbody.entities.Rand2DC;
import fr.com.jfish.jfgnbody.nbody.entities.SupermassiveStaticBody;
import fr.com.jfish.jfgnbody.utils.Rand2DCUtils;
import fr.com.jfish.jfgnbody.utils.SimulationGenerationUtils;

/**
 * @author thw
 */
public class Simulation1 extends AbstractSimulation {
    
    private static final int M_COUNT = 1;
  
    @Override
    public void start(final NBodyDrawable n, final int N, final NbodyCollection m, 
            final Rand2DCUtils.Layout l) {

        double magv, absangle, thetav, phiv, vx, vy, vz, mass;
        final Rand2DC[] pos = Rand2DCUtils.build(N, false, l);

        /**
         * Put a supermassive body in the center - SupermassiveBody instances
         * will not be candidates to draw or paint methods.
         */
        final SupermassiveStaticBody smsb = new SupermassiveStaticBody(N - Simulation1.M_COUNT,
                0d, 0d, 0d, 0d, 0d, 0d, 1.0e6 * NBodyConst.SOLARMASS, NBodyConst.SM_STATIC_BODY_COLOR);
        m.add(smsb);
        n.getForceUpdater().getMbs().put(smsb.graphics.key, smsb);
        
        for (int i = 0; i < N - Simulation1.M_COUNT; i++) {
            
            magv = SimulationGenerationUtils.circleV(pos[i].px, pos[i].py);

            absangle = Math.atan(Math.abs(pos[i].py / pos[i].px));
            thetav = Math.PI / 2 - absangle;
            phiv = Math.random() * Math.PI;
            vx = -1 * Math.signum(pos[i].py) * Math.cos(thetav) * magv;
            vy = Math.signum(pos[i].px) * Math.sin(thetav) * magv;
            vz = Math.signum(pos[i].pz) * Math.sin(thetav) * magv;
            
            mass = Math.random() * NBodyConst.SOLARMASS;
            
            /**
             * To obtain clockwise and counter-clockwise velocities,
             * use Rand2DCUtils.randBool(). Here, all bodies will spin clockwise.
             */
            m.add(new Body(i, pos[i].px, pos[i].py, pos[i].pz, vx, vy, vz, mass, 
                NBodyConst.BODY_COLOR, false));
        }
    }
    
    @Override
    public String toString() {
        return "N bodies with random positions & circular velocities";
    }
    
    @Override
    @Deprecated
    public void start(final int N, final NBodyDrawable n, final boolean bhTree) {
        
        double px, py, pz, magv, absangle, thetav, phiv, vx, vy, vz, mass;
        
        for (int i = 0; i < N; i++) {

            px = NBodyConst.NBODY_MASS_CONST * SimulationGenerationUtils.exp(-1.8) * (.5 - Math.random());
            py = NBodyConst.NBODY_MASS_CONST * SimulationGenerationUtils.exp(-1.8) * (.5 - Math.random());
            pz = NBodyConst.NBODY_MASS_CONST * SimulationGenerationUtils.exp(-1.8) * (.5 - Math.random());
            magv = SimulationGenerationUtils.circleV(px, py);

            absangle = Math.atan(Math.abs(py / px));
            thetav = Math.PI / 2 - absangle;
            phiv = Math.random() * Math.PI;
            vx = -1 * Math.signum(py) * Math.cos(thetav) * magv;
            vy = Math.signum(px) * Math.sin(thetav) * magv;
            vz = Math.signum(pz) * Math.sin(thetav) * magv;
            
            mass = Math.random() * NBodyConst.SOLARMASS;
            n.getNB().put(i, new Body(i, px, py, pz, vx, vy, vz, mass, NBodyConst.BODY_COLOR));
        }

        /**
         * Put a supermassive body in the center - SupermassiveBody instances
         * will not be candidates to draw or paint methods.
         */
        final SupermassiveStaticBody smsb = new SupermassiveStaticBody(n.getNB().size(), 
                0d, 0d, 0d, 0d, 0d, 0d, 1.0e6 * NBodyConst.SOLARMASS, NBodyConst.SM_STATIC_BODY_COLOR);
        n.getNB().put(n.getNB().size(), smsb);
        n.getForceUpdater().getMbs().put(smsb.graphics.key, smsb);
        
        throw new UnsupportedOperationException();
    }
    
}
