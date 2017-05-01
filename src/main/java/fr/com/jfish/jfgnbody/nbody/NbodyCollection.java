package fr.com.jfish.jfgnbody.nbody;

import fr.com.jfish.jfgnbody.nbody.entities.Body;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
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

    public boolean perform(final int i) {
        if (i < c.length && c[i] != null) System.out.println(c[i].toString());
        return i < c.length && c[i] != null;
    }
    
    private void shuffle(final int n) {
        
        int count = 0;
        do {
            final Random rand = ThreadLocalRandom.current();
            for (int i = c.length - 1; i > 0; i--) {
                int index = rand.nextInt(i + 1);
                // Simple swap :
                Body a = c[index];
                c[index] = c[i];
                c[i] = a;
            }
            ++count;
        } while (count < n);
    }
    
    private void shuffle() {
        this.shuffle(0);
    }
      
}
