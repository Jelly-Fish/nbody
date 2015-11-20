package com.jellyfish.jfgnbody.nbody;

import com.jellyfish.jfgnbody.nbody.entities.Body;

/**
 *
 * @author thw
 */
public class NbodyCollection {
    
    public Body[] c = null;
    
    private int iC;

    public NbodyCollection(final int n) {
        this.c = new Body[n];
        this.iC = 0;
    }

    public void add(final Body body) {
        c[iC] = body;
        ++iC;
    }

    public void discard(final int i) {
        c[i] = null;
    }

    public int size() {
        return c.length;
    }
      
}
