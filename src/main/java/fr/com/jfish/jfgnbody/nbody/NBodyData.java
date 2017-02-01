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
     * update data.
     * @param iterationCount
     * @param bodyCount
     * @param superMassiveBodyMass 
     * @param update 
     */
    public static void update(final int iterationCount, final long bodyCount, final double superMassiveBodyMass, 
            final boolean update) {
        if (!update) return;
        NBodyData.iterationCount = iterationCount;
        NBodyData.bodyCount = bodyCount;
        NBodyData.superMassiveBodyMass = superMassiveBodyMass;
    }
    
    @SuppressWarnings("MalformedFormatString")
    public static String getFormattedData() {
        return String.format("interation n° %d - body count = %d - super massive body mass = %f", 
            NBodyData.iterationCount, NBodyData.bodyCount, NBodyData.superMassiveBodyMass);
    }
    
}
