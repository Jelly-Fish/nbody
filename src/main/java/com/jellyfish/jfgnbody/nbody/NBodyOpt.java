package com.jellyfish.jfgnbody.nbody;

import com.jellyfish.jfgnbody.gui.GUIDTO;
import com.jellyfish.jfgnbody.nbody.entities.Body;
import com.jellyfish.jfgnbody.nbody.entities.SupermassiveBody;
import com.jellyfish.jfgnbody.nbody.simulations.AbstractSimulation;
import com.jellyfish.jfgnbody.utils.StopWatch;
import java.awt.Graphics;

/**
 *
 * @author thw
 */
public class NBodyOpt extends NBody {
    
    public NBodyOpt(final int n, final double iterationSpeed, final AbstractSimulation sim) {
        super(n, iterationSpeed, sim);
        this.nBodies = new NbodyCollection(n);
    }
    
    @Override
    public void paint(Graphics g) {

        if (nBodies != null) {

            NBodyData.bodyCount = 0;
            g.clearRect(0, 0, this.getWidth(), this.getHeight());
            // Originally the origin is in the top right. Put it in its normal place :
            g.translate(this.getWidth() / 2, this.getHeight() / 2);

            for (int i = 0; i < nBodies.size(); i++) {
                
                if (this.nBodies.c[i] == null) continue;
                
                NBodyData.bodyCount++;
                g.setColor(nBodies.c[i].graphics.color);
                if (nBodies.c[i] instanceof SupermassiveBody) {
                    g.drawOval(nBodies.c[i].graphics.graphicX, 
                            nBodies.c[i].graphics.graphicY, 
                            nBodies.c[i].graphics.graphicSize,
                            nBodies.c[i].graphics.graphicSize);
                } else {
                    g.fillOval(nBodies.c[i].graphics.graphicX, 
                            nBodies.c[i].graphics.graphicY, 
                            nBodies.c[i].graphics.graphicSize,
                            nBodies.c[i].graphics.graphicSize);
                }
            }

            if (!GUIDTO.pause) {
                NBodyData.iterationCount++;
                this.cleanBodyMap();
                fu.addForces(getWidth(), getHeight(), q, nBodies);
                if (this.stopWatch != null) {
                    this.stopWatch.start();
                }
            }
        }

        // Always repaint.
        super.repaint();
    }
    
    @Override
    void cleanBodyMap() {

        final int[] keys = new int[this.nBodies.size()];
        int k = 1;
        for (int i = 0; i < this.nBodies.size(); i++) {
            if (this.nBodies.c[i] == null) continue;
            if (this.nBodies.c[i].isSwallowed()) {
                keys[i] = this.nBodies.c[i].graphics.key;
                ++k;
            }
        }

        for (int j = 0; j < k; j++) {
            this.nBodies.discard(keys[j]);
        }
    }
    
    @Override
    boolean performPaint() {

        if (NBody.DRAW_COUNTER > this.drawCount) {
            this.drawCount++;
            return false;
        } else {
            this.drawCount = 0;
            return true;
        }
    }

    @Override
    public void restart(int n, int iSpeed, final AbstractSimulation sim) {
        NBodyData.bodyCount = 0;
        NBodyData.iterationCount = 0;
        NBodyData.superMassiveBodyMass = 0.0;
        this.N = n;
        this.sim = sim;
        sim.start(this, N, nBodies);
        this.stopWatch = new StopWatch(iSpeed);
        this.spatialArea.updateSize(this.getWidth(), this.getHeight());
    }
    
}
