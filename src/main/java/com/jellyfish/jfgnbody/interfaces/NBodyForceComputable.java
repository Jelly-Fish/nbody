/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jellyfish.jfgnbody.interfaces;

import com.jellyfish.jfgnbody.nbody.Body;
import com.jellyfish.jfgnbody.nbody.barneshut.Quadrant;
import java.util.HashMap;

/**
 *
 * @author thw
 */
public interface NBodyForceComputable {
    
    void addForces(final int w, final int h, final Quadrant q, final HashMap<Integer, Body> m);
    
}
