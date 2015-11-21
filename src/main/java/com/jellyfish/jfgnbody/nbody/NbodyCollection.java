package com.jellyfish.jfgnbody.nbody;

import com.jellyfish.jfgnbody.nbody.entities.Body;

/**
 *
 * @author thw
 * @param <E>
 */
public class NbodyCollection<E extends Body> {

    public Body[] c = null;
    
    private int lDI;
    
    private int iC;

    public NbodyCollection(final int n) {
        this.c = new Body[n];
        this.iC = 0;
        this.lDI = this.c.length - 1;
    }

    public void add(final E e) {
        c[iC] = e;
        ++iC;
    }

    public void discard(final int i) {
        c[i] = c[lDI];
        c[lDI] = null;
        --lDI;
    }

    public int size() {
        return c.length;
    }
      
}
