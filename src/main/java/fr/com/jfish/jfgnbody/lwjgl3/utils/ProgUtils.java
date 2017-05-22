package fr.com.jfish.jfgnbody.lwjgl3.utils;

import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;
import static org.lwjgl.opengl.GL20.glGetProgrami;
import static org.lwjgl.opengl.GL20.glLinkProgram;

/**
 * @author thw
 */
public class ProgUtils {
    
    public static int createProgram(final int vshader, final int fshader) {
        
        int program = glCreateProgram();
        glAttachShader(program, vshader);
        glAttachShader(program, fshader);
        glLinkProgram(program);
        int linked = glGetProgrami(program, GL_LINK_STATUS);
        String programLog = glGetProgramInfoLog(program);
        
        if (programLog.trim().length() > 0) System.err.println(programLog);
        if (linked == 0) throw new AssertionError("Could not link program");
        
        return program;
    }
    
    public static int createProgram(final int fshader) {
        
        int program = glCreateProgram();
        glAttachShader(program, fshader);
        glLinkProgram(program);
        int linked = glGetProgrami(program, GL_LINK_STATUS);
        String programLog = glGetProgramInfoLog(program);
        
        if (programLog.trim().length() > 0) System.err.println(programLog);
        if (linked == 0) throw new AssertionError("Could not link program");
        
        return program;
    }
    
}
