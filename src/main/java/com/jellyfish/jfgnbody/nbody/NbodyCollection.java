package com.jellyfish.jfgnbody.nbody;

import com.jellyfish.jfgnbody.nbody.entities.Body;

/**
 *
 * @author thw
 */
public class NbodyCollection {
    
    public Body[] collection = null;
    
    private int iC = 0;

    public NbodyCollection(final int n) {
        this.collection = new Body[n];
    }

    public void add(final Body body) {
        collection[iC] = body;
        ++iC;
    }
    
    public void discard(final int i) {
        collection[i] = null;
    }

    public int size() {
        return collection.length;
    }
      
}
