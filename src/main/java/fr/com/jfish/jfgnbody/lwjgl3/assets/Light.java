package fr.com.jfish.jfgnbody.lwjgl3.assets;

import fr.com.jfish.jfgnbody.lwjgl3.maths.Vector3f;

public class Light {

    public Vector3f location;
    public float red;
    public float green;
    public float blue;

    public Light(Vector3f location, float red, float green, float blue) {
        this.location = location;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
}
