package fr.com.jfish.jfgnbody.lwjgl3.factory;

import fr.com.jfish.jfgnbody.lwjgl3.constants.FrameVars;
import fr.com.jfish.jfgnbody.lwjgl3.assets.Light;
import fr.com.jfish.jfgnbody.lwjgl3.maths.Vector3f;
import fr.com.jfish.jfgnbody.nbody.NbodyCollection;
import fr.com.jfish.jfgnbody.nbody.entities.Body;
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
        for (int i = 0; i < n; i++) l.add(this.getMockedLight());
        
        return l;
    }
    
    public NbodyCollection mockLightToBody(final int n, final NbodyCollection nBodies) {
        
        Light light = null;
        for (Body b : nBodies.c) {
            light = getMockedLight();
            light.location.x = (float) b.rx;
            light.location.y = (float) b.ry;
            light.location.z = (float) b.rz;
            b.setLight(light);
        }
        
        return nBodies;
    }
    
    private Light getMockedLight() {
        
        Vector3f pos;
        pos = new Vector3f(
            (float) Math.random() * FrameVars.V_WIDTH, 
            (float) Math.random() * FrameVars.V_HEIGHT,
            (float) Math.random() * FrameVars.V_HEIGHT
        );
        
        return new Light(pos, 
            (float) Math.random() * 10, 
            (float) Math.random() * 10, 
            (float) Math.random() * 10);
    }
    
    private Light getMockedLightSMB() {
        
        Vector3f pos;
        pos = new Vector3f(
            (float) Math.random() * FrameVars.V_WIDTH, 
            (float) Math.random() * FrameVars.V_HEIGHT,
            (float) Math.random() * FrameVars.V_HEIGHT
        );
        
        return new Light(pos, 
            (float) Math.random() * 10, 
            (float) Math.random() * 10, 
            (float) Math.random() * 10);
    }
    
}
