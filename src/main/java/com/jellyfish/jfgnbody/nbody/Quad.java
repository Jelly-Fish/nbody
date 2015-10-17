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

/**
 *
 * @author thw
 */
public class Quad {

    private double xmid, ymid, length;

    //Create a square quadrant with a given length and midpoints (xmid,ymid)

    public Quad(double xmid, double ymid, double length) {
        this.xmid = xmid;
        this.ymid = ymid;
        this.length = length;
    }

    //How long is this quadrant?
    public double length() {
        return length;
    }

    //Check if the current quadrant contains a point
    public boolean contains(double xmid, double ymid) {
        if (xmid <= this.xmid + this.length / 2.0 && xmid >= this.xmid - this.length / 2.0 && ymid <= this.ymid + this.length / 2.0 && ymid >= this.ymid - this.length / 2.0) {
            return true;
        } else {
            return false;
        }
    }
  //Create subdivisions of the current quadrant

    // Subdivision: Northwest quadrant
    public Quad NW() {
        Quad newquad = new Quad(this.xmid - this.length / 4.0, this.ymid + this.length / 4.0, this.length / 2.0);
        return newquad;
    }

    // Subdivision: Northeast quadrant
    public Quad NE() {
        Quad newquad = new Quad(this.xmid + this.length / 4.0, this.ymid + this.length / 4.0, this.length / 2.0);
        return newquad;
    }

    // Subdivision: Southwest quadrant
    public Quad SW() {
        Quad newquad = new Quad(this.xmid - this.length / 4.0, this.ymid - this.length / 4.0, this.length / 2.0);
        return newquad;
    }

    // Subdivision: Southeast quadrant
    public Quad SE() {
        Quad newquad = new Quad(this.xmid + this.length / 4.0, this.ymid - this.length / 4.0, this.length / 2.0);
        return newquad;
    }
}
