package fr.com.jfish.jfgnbody.utils;

import fr.com.jfish.jfgnbody.nbody.constants.NBodyConst;
import fr.com.jfish.jfgnbody.nbody.entities.Rand3DC;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author thw
 */
public class RandUtils {
    
    public static enum Layout {
        
        FLAT("Random, flat layout."), 
        STAR("Grouped galxy style layout");
        
        private final String desc;
        
        Layout(final String desc) {
            this.desc = desc;
        }
        
        @Override
        public String toString() {
            return this.desc;
        }
    }
    
    public static double randDouble(final double factor) {
        return Math.random() * factor;
    }
    
    public static Rand3DC[] build(final int n, final boolean neg, RandUtils.Layout layout) {
     
        switch(layout) {
            case FLAT:
                return neg ? RandUtils.buildNegPos(n, 3.0, 10000) : 
                    RandUtils.build(n, 3.0, 10000);
            case STAR:
                return RandUtils.build(n);
        }
        
        throw new UnsupportedOperationException();
    }
    
    public static boolean randBool() {
        return Math.random() > 0.5d;
    }
    
    /**
     * Star shape layout of random positions.
     * @param n
     * @return Rand2DC[] instance containing px & py values.
     */
    private static Rand3DC[] build(final int n) {
        
        final Rand3DC[] c = new Rand3DC[n];
        double[] fX = new double[n];
        double[] fY = new double[n];
        double[] fZ = new double[n];
        double[] expX = new double[n];
        double[] expY = new double[n];
        double[] expZ = new double[n];
        final int fFactM = 1000, fFactm = 37, expFactM = 11, expFactm = 1;
        
        for (int i = 0; i < n; i++) {
        
            fX[i] = (RandUtils.randDouble(fFactm, fFactM) / fFactM) - (RandUtils.randDouble(1, 10000) / 10000);
            fY[i] = (RandUtils.randDouble(fFactm, fFactM) / fFactM) - (RandUtils.randDouble(1, 10000) / 10000);
            fZ[i] = (RandUtils.randDouble(fFactm, fFactM) / fFactM) - (RandUtils.randDouble(1, 10000) / 10000);
            expX[i] = RandUtils.randDouble(expFactm, expFactM) / 10;
            expY[i] = RandUtils.randDouble(expFactm, expFactM) / 10;
            expZ[i] = RandUtils.randDouble(expFactm, expFactM) / 10;
        }

        RandUtils.shuffleArray(fX);
        RandUtils.shuffleArray(fY);
        RandUtils.shuffleArray(fZ);
        RandUtils.shuffleArray(expX);
        RandUtils.shuffleArray(expY);
        RandUtils.shuffleArray(expZ);

        for (int i = 0; i < n; i++) {
            c[i] = new Rand3DC(
                NBodyConst.NBODY_MASS_CONST * SimulationGenerationUtils.exp(-expX[i]) * fX[i], 
                NBodyConst.NBODY_MASS_CONST * SimulationGenerationUtils.exp(-expY[i]) * fY[i],
                NBodyConst.NBODY_MASS_CONST * SimulationGenerationUtils.exp(-expZ[i]) * fZ[i]);
            fX[i] = c[i].px;
            fY[i] = c[i].py;
            fZ[i] = c[i].pz;
        }
        
        return c;
    }
    
    /**
     * Random, flat layout. Builsd half N for positivie values, then half N for 
     * negative values. Finnaly, shuffles all values.
     * @param n body count.
     * @param M maximum value / must be smaller than 3.0d / will define minimum value = -M.
     * @param factor random accruracy factor, must be > 100.
     * @return Rand2DC[] instance containing px, py & pz values.
     */
    private static Rand3DC[] buildNegPos(final int n, final double M, final int factor) {
        
        double[] xV = new double[n];
        double[] yV = new double[n];
        double[] zV = new double[n];
        final Rand3DC[] c = new Rand3DC[n];
        final int max = (int) M * factor;
        
        for (int i = 0; i < n / 2; i++) {
            xV[i] = (RandUtils.randDouble(1, max) / factor) * NBodyConst.NBODY_MASS_CONST;
            yV[i] = (RandUtils.randDouble(1, max) / factor) * NBodyConst.NBODY_MASS_CONST;
            zV[i] = (RandUtils.randDouble(1, max) / factor) * NBodyConst.NBODY_MASS_CONST;
        }
        
        for (int i = n / 2; i < n; i++) {
            xV[i] = -(RandUtils.randDouble(1, max) / factor) * NBodyConst.NBODY_MASS_CONST;
            yV[i] = -(RandUtils.randDouble(1, max) / factor) * NBodyConst.NBODY_MASS_CONST;
            zV[i] = -(RandUtils.randDouble(1, max) / factor) * NBodyConst.NBODY_MASS_CONST;
        }
        
        shuffleArray(xV);
        shuffleArray(yV);
        shuffleArray(zV);

        /*printArray("x", xV);
        printArray("y", yV);
        printArray("z", zV);*/
        
        for (int i = 0; i < n; i++) {
            c[i] = new Rand3DC(xV[i], yV[i], zV[i]);
        }
        
        return c;
    }
    
    /**
     * Random, flat layout. Builsd all N with positivie values. 
     * Finnaly, shuffles all values.
     * @param n body count.
     * @param M maximum value / must be smaller than 3.0d / will define minimum value = -M.
     * @param factor random accruracy factor, must be > 100.
     * @return Rand2DC[] instance containing px, py & pz values.
     */
    private static Rand3DC[] build(final int n, final double M, final int factor) {
        
        double[] xP = new double[n];
        double[] yP = new double[n];
        double[] zP = new double[n];
        final Rand3DC[] c = new Rand3DC[n];
        final int max = (int) M * factor;
        
        for (int i = 0; i < n; i++) {
            xP[i] = NBodyConst.NBODY_MASS_CONST * SimulationGenerationUtils.exp(-1.8) * (.5 - Math.random());
            yP[i] = NBodyConst.NBODY_MASS_CONST * SimulationGenerationUtils.exp(-1.8) * (.5 - Math.random());
            zP[i] = NBodyConst.NBODY_MASS_CONST * SimulationGenerationUtils.exp(-1.8) * (.5 - Math.random());
        }
        
        shuffleArray(xP);
        shuffleArray(yP);
        shuffleArray(zP);
        
        for (int i = 0; i < n; i++) {
            c[i] = new Rand3DC(xP[i], yP[i], zP[i]);
        }
        
        return c;
    }
    
    private static void printArray(final String c, double[] a) {
        for (double d : a) System.out.println(String.format("%s: %f", c, d));
    }
    
    private static double randDouble(final int m, final int M) {
        return m + (Math.random() * ((M - m) + 1));
    }
    
    private static void shuffleArray(double[] doubleArray) {

        final Random rand = ThreadLocalRandom.current();
        for (int i = doubleArray.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            // Simple swap
            double a = doubleArray[index];
            doubleArray[index] = doubleArray[i];
            doubleArray[i] = a;
        }
    }
    
    private static void shuffleArray(int[] integerArray) throws InterruptedException {

        final Random rand = ThreadLocalRandom.current();
        for (int i = integerArray.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            // Simple swap
            int a = integerArray[index];
            integerArray[index] = integerArray[i];
            integerArray[i] = a;
        }
    }
    
}