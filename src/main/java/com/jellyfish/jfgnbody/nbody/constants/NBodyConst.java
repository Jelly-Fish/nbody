package com.jellyfish.jfgnbody.nbody.constants;

import java.awt.Color;

/**
 *
 * @author thw
 */
public class NBodyConst {
    
    /**
     * Solar mass constant.
     */
    public static final double SOLARMASS = 1.98892e30; // = Math.pow(1.98892 * 10, 30) 
    
    /**
     * Body mass constant.
     */
    public static final double NBODY_MASS_CONST = 1e18;
    
    /**
     * Massive body count.
     * @see MassiveBody
     */
    public static final int N_MASSIVE_BODY_COUNT = 1;
    
        
    /**
     * Simulation background color.
     */
    public static final Color BG_COLOR = new Color(8,19,35);
    
    /**
     * Body color.
     */
    public static final Color BODY_COLOR = new Color(255,255,225);
    
    /**
     * Masisive body color.
     */
    public static final Color M_BODY_COLOR = Color.CYAN;
    
    /**
     * Super massive Body color.
     */
    public static final Color SM_STATIC_BODY_COLOR = Color.RED;
    
}
