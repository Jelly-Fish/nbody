package fr.com.jfish.jfgnbody.nbody;

/**
 *
 * @author thw
 */
public class NBodyData {
    
    /**
     * Amount of iterations performed during simulation.
     */
    public static int iterationCount = 0;
    
    /**
     * Iteration i amount of bodies iterated on.
     */
    public static long bodyCount = 0;
    
    /**
     * Super massive center body's updated mass.
     */
    public static double superMassiveBodyMass = 0.0;

    /**
     * @return formatted data.
     */
    public static String getFormattedData() {
        return String.format("interation n° %d - body count = %d", 
            NBodyData.iterationCount, NBodyData.bodyCount);
    }

}
