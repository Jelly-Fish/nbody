/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jellyfish.jfgnbody.nbody;

import com.jellyfish.jfgnbody.nbody.entities.Body;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Collection;

/**
 *
 * @author thw
 */
public class NBodyDrawingHelper {
    
    private static final RenderingHints REND_1 = new RenderingHints(
             RenderingHints.KEY_TEXT_ANTIALIASING,
             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

    static void draw(Graphics2D g, final Collection<Body> bc, final Collection<Body> mb) {
        
        NBodyData.bodyCount = 0;
        
        for (Body b : bc) {
            NBodyData.bodyCount++;
            NBodyDrawingHelper.draw(g, b);
        }
        
        for (Body b : mb) {
            NBodyData.bodyCount++;
            NBodyDrawingHelper.draw(g, b);
        }
    }
    
    static void draw(Graphics2D g, final NbodyCollection nc, final Collection<Body> mb) {

        NBodyData.bodyCount = 0;
        int i = 0;
        while (nc.perform(i)) {
            NBodyData.bodyCount++;
            NBodyDrawingHelper.draw(g, nc.c[i]);
            ++i;
        }
        
        for (Body b : mb) {
            NBodyData.bodyCount++;
            NBodyDrawingHelper.draw(g, b);
        }
    }
    
    private static void draw(Graphics2D g, final Body b) {
        
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);

        g.setColor(b.graphics.color);
        if (b.isSuperMassiveStatic()) {
            g.setStroke(new BasicStroke(1.5f));
            g.drawOval(b.graphics.graphicX, b.graphics.graphicY, b.graphics.graphicSize,
                    b.graphics.graphicSize);
        } else {
            g.fillOval(b.graphics.graphicX, b.graphics.graphicY, b.graphics.graphicSize,
                    b.graphics.graphicSize);
        }
    }

}
