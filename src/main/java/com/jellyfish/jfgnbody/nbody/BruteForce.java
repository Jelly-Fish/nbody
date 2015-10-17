/*******************************************************************************
 * Copyright (c) 2015, Thomas.H Warner.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, 
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this 
 * list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, 
 * this list of conditions and the following disclaimer in the documentation and/or 
 * other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors 
 * may be used to endorse or promote products derived from this software without 
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY 
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; 
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON 
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT 
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS 
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
 *******************************************************************************/

package com.jellyfish.fr.jfgnbody.nbody;

import com.jellyfish.fr.jfgnbody.utils.StopWatch;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author thw
 */
public class BruteForce extends javax.swing.JPanel {

    public final int N;
    public Body bodies[] = new Body[10000];
    private final StopWatch stopWatch;

    public BruteForce(final int n, final double iterationSpeed) {
        this.N = n;
        this.startBodies(N);
        this.stopWatch = new StopWatch(iterationSpeed);
        this.setSize(800, 600);
        this.setBackground(Color.GRAY);
    }

    @Override
    public void paint(Graphics g) {
        
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        // Originally the origin is in the top right. Put it in its normal place :
        g.translate(this.getWidth() / 2, this.getHeight() / 2);

        for (int i = 0; i < N; i++) {
            g.setColor(bodies[i].color);
            g.fillOval((int) Math.round(bodies[i].rx * 250 / 1e18), 
                    (int) Math.round(bodies[i].ry * 250 / 1e18), 8, 8);
        }
        
        if (this.stopWatch.hasReachedMaxElapsedMS()) { 
            addforces(N);
            if (this.stopWatch != null)
                this.stopWatch.start();
        }
        
        super.repaint();
    }
    
    /**
     * the bodies are initialized in circular orbits around the central mass,
     * some physics to do so.
     * @param rx
     * @param ry
     * @return 
     */
    public static double circleV(final double rx, final double ry) {
        
        double solarmass = 1.98892e30;
        double r2 = Math.sqrt(rx * rx + ry * ry);
        double numerator = (6.67e-11) * 1e6 * solarmass;
        return Math.sqrt(numerator / r2);
    }

    /**
     * Initialize N bodies with random positions and circular velocities.
     * @param N 
     */
    public final void startBodies(final int N) {
        
        double radius = 1e18; // radius of universe
        double solarmass = 1.98892e30; // = Math.pow(1.98892 * 10, ) 
        
        for (int i = 0; i < N; i++) {
            
            double px = 1e18 * exp(-1.8) * (.5 - Math.random());
            double py = 1e18 * exp(-1.8) * (.5 - Math.random());
            double magv = circleV(px, py);

            double absangle = Math.atan(Math.abs(py / px));
            double thetav = Math.PI / 2 - absangle;
            double phiv = Math.random() * Math.PI;
            double vx = -1 * Math.signum(py) * Math.cos(thetav) * magv;
            double vy = Math.signum(px) * Math.sin(thetav) * magv;
                
            // Orient a random 2D circular orbit.
            if (Math.random() <= .5) {
                vx = -vx;
                vy = -vy;
            }

            double mass = Math.random() * solarmass * 10 + 1e20;
            // Color the masses in green gradients by mass.
            int red = (int) Math.floor(mass * 254 / (solarmass * 10 + 1e20));
            int blue = (int) Math.floor(mass * 254 / (solarmass * 10 + 1e20));
            int green = 255;
            Color color = new Color(red, green, blue);
            bodies[i] = new Body(px, py, vx, vy, mass, color);
        }
        
        // Put the central mass in. Put a heavy body in the center :
        bodies[0] = new Body(0, 0, 0, 0, 1e6 * solarmass, Color.red);

    }

    /**
     * Use the method in Body to reset the forces, then add all the new forces.
     * @param N 
     */
    public void addforces(int N) {
        
        for (int i = 0; i < N; i++) {
            
            bodies[i].resetForce();
            
            //Notice-2 loops-->N^2 complexity
            for (int j = 0; j < N; j++) {
                if (i != j) {
                    bodies[i].addForce(bodies[j]);
                }
            }
        }
        
        //Then, loop again and update the bodies using timestep dt
        for (int i = 0; i < N; i++) {
            bodies[i].update(1e11);
        }
    }

    /**
     * @param lambda
     * @return 
     */
    public static double exp(double lambda) {
        return -Math.log(1 - Math.random()) / lambda;
    }
    
}
