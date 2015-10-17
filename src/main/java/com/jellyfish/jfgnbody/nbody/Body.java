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

/**
 *
 * @author thw
 */
public class Body {

    private static final double G = 6.673e-11;   // gravitational constant
    private static final double solarmass = 1.98892e30;

    public double rx, ry;       // holds the cartesian positions
    public double vx, vy;       // velocity components 
    public double fx, fy;       // force components
    public double mass;         // mass
    public Color color;         // color (for fun)

    /**
     * 
     * @param rx the cartesian position x
     * @param ry the cartesian position y
     * @param vx velocity component x
     * @param vy velocity component y
     * @param mass mass of the body
     * @param color body's display color for fun.
     */
    public Body(double rx, double ry, double vx, double vy, double mass, Color color) {
        this.rx = rx;
        this.ry = ry;
        this.vx = vx;
        this.vy = vy;
        this.mass = mass;
        this.color = color;
    }

    // update the velocity and position using a timestep dt
    public void update(double dt) {
        vx += dt * fx / mass;
        vy += dt * fy / mass;
        rx += dt * vx;
        ry += dt * vy;
    }

    // returns the distance between two bodies
    public double distanceTo(Body b) {
        double dx = rx - b.rx;
        double dy = ry - b.ry;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // set the force to 0 for the next iteration
    public void resetForce() {
        fx = 0.0;
        fy = 0.0;
    }

    // compute the net force acting between the body a and b, and
    // add to the net force acting on a
    public void addForce(Body b) {
        Body a = this;
        double EPS = 3E4;      // softening parameter (just to avoid infinities)
        double dx = b.rx - a.rx;
        double dy = b.ry - a.ry;
        double dist = Math.sqrt(dx * dx + dy * dy);
        double F = (G * a.mass * b.mass) / (dist * dist + EPS * EPS);
        a.fx += F * dx / dist;
        a.fy += F * dy / dist;
    }

    public boolean in(Quad q) {
        return q.contains(this.rx, this.ry);
    }

    // convert to string representation formatted nicely
    @Override
    public String toString() {
        return "" + rx + ", " + ry + ", " + vx + ", " + vy + ", " + mass;
    }

    Body add(Body body, Body b) {
        return null;
    }
}
