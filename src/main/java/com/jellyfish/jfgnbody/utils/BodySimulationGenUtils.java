package com.jellyfish.jfgnbody.utils;

/**
 *
 * @author thw
 */
public class BodySimulationGenUtils {

    /**
     * @param lambda
     * @return
     */
    public static double exp(final double lambda) {
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
    public static double circleV(final double rx, final double ry) {

        final double solarmass = 1.98892e30;
        double r2 = Math.sqrt(rx * rx + ry * ry);
        double numerator = (6.67e-11) * 1e6 * solarmass;
        return Math.sqrt(numerator / r2);
    }
    
}
