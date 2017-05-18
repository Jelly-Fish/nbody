package fr.com.jfish.jfgnbody.nbody.entities;

/**
 * @author thw
 * Random 2D/3D coordinate.
 */
public class Rand3DC {
    
    public final double px;
    public final double py;
    public final double pz;

    public Rand3DC(final double px, final double py, final double pz) {
        this.px = px;
        this.py = py;
        this.pz = pz;
    }  
    
    @Override
    public String toString() {
        return String.format("x:%f y:%f, z:%f", px, py, pz);
    }
       
}
