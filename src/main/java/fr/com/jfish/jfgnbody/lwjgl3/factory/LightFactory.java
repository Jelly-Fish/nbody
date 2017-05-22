package fr.com.jfish.jfgnbody.lwjgl3.factory;

import fr.com.jfish.jfgnbody.lwjgl3.constants.FrameVars;
import fr.com.jfish.jfgnbody.lwjgl3.assets.Light;
import fr.com.jfish.jfgnbody.lwjgl3.maths.Vector3f;
import java.util.ArrayList;

/**
 * @author thw
 */
public class LightFactory {
    
    public ArrayList<Light> create(final int n) {        
        return null;
    }
    
    public ArrayList<Light> mock(final int n) {
        
        final ArrayList<Light> l = new ArrayList<>();
        
        Vector3f pos;
        for (int i = 0; i < n; i++) {
            pos = new Vector3f(
                (float) Math.random() * FrameVars.V_WIDTH, 
                (float) Math.random() * FrameVars.V_HEIGHT,
                (float) Math.random() * FrameVars.V_HEIGHT
            );
            
            l.add(new Light(pos, 
                (float) Math.random() * 10, 
                (float) Math.random() * 10, 
                (float) Math.random() * 10)
            );
        } 
        
        return l;
    }
    
}
