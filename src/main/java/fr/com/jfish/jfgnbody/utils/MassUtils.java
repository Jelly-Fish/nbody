package fr.com.jfish.jfgnbody.utils;

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
     * @param m Body's instance physical mass.
     * @return Body mass / ratio for performing draw or paint GUI actions.
     */
    public static final int getVirtualIntegerMass(final double m) {
        final Double vMass = m / MassUtils.MASS_DRAW_DIMENSION_RATIO;
        return vMass < MassUtils.MIN_VMASS ? MassUtils.MIN_VMASS : vMass.intValue();
    }
    
    /**
     * @param virtualMass used for graphical display &/OR painting objects.
     * @return true mass.
     */
    public static final double getMassByVirtualIntegerMass(final int virtualMass) {
        return virtualMass * MassUtils.MASS_DRAW_DIMENSION_RATIO;
    }
    
}
