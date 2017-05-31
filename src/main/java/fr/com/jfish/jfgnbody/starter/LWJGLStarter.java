package fr.com.jfish.jfgnbody.starter;

import fr.com.jfish.jfgnbody.lwjgl3.NBodyLWJGL3;
import fr.com.jfish.jfgnbody.nbody.simulations.Simulation1;

/**
 *
 * @author thw
 */
public class LWJGLStarter {
    
    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main(String[] args) {
        
        final int n = 60;
        // FIXME : iteration speed is not used in OpenGL cfg.
        new NBodyLWJGL3(n, -1, new Simulation1());
    }
    
}
