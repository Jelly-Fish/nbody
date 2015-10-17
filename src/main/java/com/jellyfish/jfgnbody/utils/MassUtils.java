package com.jellyfish.jfgnbody.utils;

import com.jellyfish.jfgnbody.nbody.Body;

/**
 *
 * @author thw
 */
public class MassUtils {
    
    /**
     * Standard solor mass.
     */
    public static final Double STANDARD_SOLOR_MASS = 1.98892e30;
    
    /**
     * Standard supermassive solar mass.
     */
    public static final Double STANDARD_SUPERMASSIVE_SOLAR_MASS = 1e6 * MassUtils.STANDARD_SOLOR_MASS;
    
    /**
     * Ratio used for performing drawing.
     */
    public static final double MASS_DRAW_DIMENSION_RATIO = MassUtils.STANDARD_SUPERMASSIVE_SOLAR_MASS / 20;
    
    /**
     * Minimum virtual mass for performing drawing or paint methods.
     */
    public static final int MIN_VMASS = 2;
    
    /**
     * @param b Body instance.
     * @return Body mass / ratio for performing draw or paint GUI actions.
     */
    public static final int getVirtualIntegerMass(final Body b) {
        final Double vMass = b.mass / MassUtils.MASS_DRAW_DIMENSION_RATIO;
        return vMass < MassUtils.MIN_VMASS ? MassUtils.MIN_VMASS : vMass.intValue();
    }
    
}
