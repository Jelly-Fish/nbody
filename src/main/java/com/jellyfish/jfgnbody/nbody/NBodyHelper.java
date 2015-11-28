/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jellyfish.jfgnbody.nbody;

import com.jellyfish.jfgnbody.nbody.entities.Body;
import com.jellyfish.jfgnbody.nbody.entities.SupermassiveBody;
import java.awt.Graphics;
import java.util.Collection;

/**
 *
 * @author thw
 */
public class NBodyHelper {

    static void draw(Graphics g, final Collection<Body> bc, final Collection<Body> mb) {
        
        NBodyData.bodyCount = 0;
        
        for (Body b : bc) {
            NBodyData.bodyCount++;
            NBodyHelper.draw(g, b);
        }
        
        for (Body b : mb) {
            NBodyData.bodyCount++;
            NBodyHelper.draw(g, b);
        }
    }
    
    static void draw(Graphics g, final NbodyCollection nc, final Collection<Body> mb) {
        
        NBodyData.bodyCount = 0;
        int i = 0;
        while (nc.perform(i)) {
            NBodyData.bodyCount++;
            NBodyHelper.draw(g, nc.c[i]);
            ++i;
        }
        
        for (Body b : mb) {
            NBodyData.bodyCount++;
            NBodyHelper.draw(g, b);
        }
    }
    
    private static void draw(Graphics g, final Body b) {
        
        g.setColor(b.graphics.color);
        if (b instanceof SupermassiveBody) {
            g.drawOval(b.graphics.graphicX, b.graphics.graphicY, b.graphics.graphicSize,
                    b.graphics.graphicSize);
        } else {
            g.fillOval(b.graphics.graphicX, b.graphics.graphicY, b.graphics.graphicSize,
                    b.graphics.graphicSize);
        }
    }

}
