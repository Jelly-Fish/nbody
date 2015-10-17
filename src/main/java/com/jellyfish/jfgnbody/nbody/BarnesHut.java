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

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author thw
 */
public class BarnesHut extends javax.swing.JPanel {

    public int N = 100;
    public Body bodies[] = new Body[10000];
    public boolean shouldrun = true;
    Quad q = new Quad(0, 0, 2 * 1e18);

    // The first time we call the applet, this function will start
    public void init() {
        startthebodies(N);
    }

    // This method gets called when the applet is terminated. It stops the code
    public void stop() {
        shouldrun = false;
    }

    // Called by the applet initally. It can be executed again by calling repaint();
    @Override
    public void paint(Graphics g) {
        g.translate(250, 250); //Originally the origin is in the top right. Put it in its normal place
        //check if we stopped the applet, and if not, draw the particles where they are
        if (shouldrun) {
            for (int i = 0; i < N; i++) {
                g.setColor(bodies[i].color);
                g.fillOval((int) Math.round(bodies[i].rx * 250 / 1e18), (int) Math.round(bodies[i].ry * 250 / 1e18), 8, 8);
            }
            //go through the Barnes-Hut algorithm (see the function below)
            addforces(N);
            //repeat
            repaint();
        }
    }

    //the bodies are initialized in circular orbits around the central mass.
    //This is just some physics to do that
    public static double circlev(double rx, double ry) {
        double solarmass = 1.98892e30;
        double r2 = Math.sqrt(rx * rx + ry * ry);
        double numerator = (6.67e-11) * 1e6 * solarmass;
        return Math.sqrt(numerator / r2);
    }

    //Initialize N bodies
    public void startthebodies(int N) {
        
        double radius = 1e18;        // radius of universe
        double solarmass = 1.98892e30;
        for (int i = 0; i < N; i++) {
            double px = 1e18 * exp(-1.8) * (.5 - Math.random());
            double py = 1e18 * exp(-1.8) * (.5 - Math.random());
            double magv = circlev(px, py);

            double absangle = Math.atan(Math.abs(py / px));
            double thetav = Math.PI / 2 - absangle;
            double phiv = Math.random() * Math.PI;
            double vx = -1 * Math.signum(py) * Math.cos(thetav) * magv;
            double vy = Math.signum(px) * Math.sin(thetav) * magv;
            
            //Orient a random 2D circular orbit
            if (Math.random() <= .5) {
                vx = -vx;
                vy = -vy;
            }
            
            double mass = Math.random() * solarmass * 10 + 1e20;
            //Color a shade of blue based on mass
            int red = (int) Math.floor(mass * 254 / (solarmass * 10 + 1e20));
            int blue = 255;
            int green = (int) Math.floor(mass * 254 / (solarmass * 10 + 1e20));
            Color color = new Color(red, green, blue);
            bodies[i] = new Body(px, py, vx, vy, mass, color);
        }
        bodies[0] = new Body(0, 0, 0, 0, 1e6 * solarmass, Color.red);
        //put a heavy body in the center

    }

    //The BH algorithm: calculate the forces
    public void addforces(int N) {
        BHTree thetree = new BHTree(q);
        // If the body is still on the screen, add it to the tree
        for (int i = 0; i < N; i++) {
            if (bodies[i].in(q)) {
                thetree.insert(bodies[i]);
            }
        }
            
        //Now, use out methods in BHTree to update the forces,
        //traveling recursively through the tree
        for (int i = 0; i < N; i++) {
            bodies[i].resetForce();
            if (bodies[i].in(q)) {
                thetree.updateForce(bodies[i]);
                //Calculate the new positions on a time step dt (1e11 here)
                bodies[i].update(1e11);
            }
        }
    }

    //A function to return an exponential distribution for position

    public static double exp(double lambda) {
        return -Math.log(1 - Math.random()) / lambda;
    }
    
}
