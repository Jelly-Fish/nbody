package com.jellyfish.jfgnbody.utils;

import com.jellyfish.jfgnbody.nbody.constants.NBodyConst;
import com.jellyfish.jfgnbody.nbody.entities.Rand2DC;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author thw
 */
public class Rand2DCUtils {
    
    /**
     * Star shape layout of random positions.
     * @param n
     * @return Rand2DC[] instance containing px & py values.
     */
    public static Rand2DC[] build(final int n) {
        
        final Rand2DC[] c = new Rand2DC[n];
        double[] fX = new double[n];
        double[] fY = new double[n];
        double[] expX = new double[n];
        double[] expY = new double[n];
        final int fFactM = 1000, fFactm = 37, expFactM = 11, expFactm = 1;
        
        for (int i = 0; i < n; i++) {
        
            fX[i] = (Rand2DCUtils.randD(fFactm, fFactM) / fFactM) - (Rand2DCUtils.randD(1, 10000) / 10000);
            fY[i] = (Rand2DCUtils.randD(fFactm, fFactM) / fFactM) - (Rand2DCUtils.randD(1, 10000) / 10000);
            expX[i] = Rand2DCUtils.randD(expFactm, expFactM) / 10;
            expY[i] = Rand2DCUtils.randD(expFactm, expFactM) / 10;
        }

        Rand2DCUtils.shuffleArray(fX);
        Rand2DCUtils.shuffleArray(fY);
        Rand2DCUtils.shuffleArray(expX);
        Rand2DCUtils.shuffleArray(expY);

        for (int i = 0; i < n; i++) {
            c[i] = new Rand2DC(
                NBodyConst.NBODY_MASS_CONST * SimulationGenerationUtils.exp(-expX[i]) * fX[i], 
                NBodyConst.NBODY_MASS_CONST * SimulationGenerationUtils.exp(-expY[i]) * fY[i]);
            fX[i] = c[i].px;
            fY[i] = c[i].py;
        }
        
        return c;
    }
    
    /**
     * Random layout.
     * @param n body count.
     * @param M maximum value / must be smaller than 3.0d / will define minimum value = -M.
     * @param factor random accruracy factor, must be > 100.
     * @return Rand2DC[] instance containing px & py values.
     */
    public static Rand2DC[] build(final int n, final double M, final int factor) {
        
        double[] xV = new double[n];
        double[] yV = new double[n];
        final Rand2DC[] c = new Rand2DC[n];
        final int max = (int) M * factor;
        
        for (int i = 0; i < n / 2; i++) {
            xV[i] = (Rand2DCUtils.randD(1, max) / factor) * NBodyConst.NBODY_MASS_CONST;
            yV[i] = (Rand2DCUtils.randD(1, max) / factor) * NBodyConst.NBODY_MASS_CONST;
        }
        
        for (int i = n / 2; i < n; i++) {
            xV[i] = -(Rand2DCUtils.randD(1, max) / factor) * NBodyConst.NBODY_MASS_CONST;
            yV[i] = -(Rand2DCUtils.randD(1, max) / factor) * NBodyConst.NBODY_MASS_CONST;
        }
        
        shuffleArray(xV);
        shuffleArray(yV);

        for (int i = 0; i < n; i++) {
            c[i] = new Rand2DC(xV[i], yV[i]);
        }
        
        return c;
    }
    
    private static double randD(final int m, final int M) {
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
