package com.jellyfish.jfgnbody.nbody.entities;

import com.jellyfish.jfgnbody.nbody.constants.NBodyConst;
import com.jellyfish.jfgnbody.utils.BodySimulationGenUtils;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author thw
 */
public class RandPXPY {
    
    public final double px;
    public final double py;

    public RandPXPY(final double px, final double py) {
        this.px = px;
        this.py = py;
    }  
    
    public static RandPXPY[] build(final int n) {
        
        final RandPXPY[] c = new RandPXPY[n];
        final double[] fX = new double[n];
        final double[] fY = new double[n];
        final double[] expX = new double[n];
        final double[] expY = new double[n];
        final int fFactM = 1000, fFactm = 37, expFactM = 11, expFactm = 1;
        
        for (int i = 0; i < n; i++) {
            fX[i] = (RandPXPY.randD(fFactm, fFactM) / fFactM) - Math.random();
            fY[i] = (RandPXPY.randD(fFactm, fFactM) / fFactM) - Math.random();
            expX[i] = RandPXPY.randE(expFactm, expFactM) / 10;
            expY[i] = RandPXPY.randE(expFactm, expFactM) / 10;
        }
        
        RandPXPY.shuffleArray(fX);
        RandPXPY.shuffleArray(fY);
        RandPXPY.shuffleArray(expX);
        RandPXPY.shuffleArray(expY);

        for (int i = 0; i < n; i++) {
            c[i] = new RandPXPY(
                NBodyConst.NBODY_MASS_CONST * BodySimulationGenUtils.exp(-expX[i]) * fX[i], 
                NBodyConst.NBODY_MASS_CONST * BodySimulationGenUtils.exp(-expY[i]) * fY[i]);
        }
        
        return c;
    }
    
    private static double randD(final int m, final int M) {
        return m + (Math.random() * ((M - m) + 1));
    }
    
    private static double randE(final int m, final int M) {
        return m + (int) (Math.random() * ((M - m) + 1));
    }
    
    public static void shuffleArray(double[] doubleArray) {

        final Random rand = ThreadLocalRandom.current();
        for (int i = doubleArray.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            // Simple swap
            double a = doubleArray[index];
            doubleArray[index] = doubleArray[i];
            doubleArray[i] = a;
        }
    }
    
    public static void shuffleArray(int[] integerArray) {

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
