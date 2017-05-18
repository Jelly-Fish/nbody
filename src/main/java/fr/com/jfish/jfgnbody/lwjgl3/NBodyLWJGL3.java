package fr.com.jfish.jfgnbody.lwjgl3;

import fr.com.jfish.jfgnbody.lwjgl3.maths.Vector3f;
import fr.com.jfish.jfgnbody.nbody.NbodyCollection;
import java.io.IOException;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.BufferUtils;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_HAND_CURSOR;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_SAMPLES;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateStandardCursor;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetCursor;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.glfw.GLFW.nglfwGetFramebufferSize;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL11.GL_ALWAYS;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_EQUAL;
import static org.lwjgl.opengl.GL11.GL_KEEP;
import static org.lwjgl.opengl.GL11.GL_ONE;
import static org.lwjgl.opengl.GL11.GL_REPLACE;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_STENCIL_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColorMask;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glStencilFunc;
import static org.lwjgl.opengl.GL11.glStencilOp;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniform2f;
import static org.lwjgl.opengl.GL20.glUniform3f;
import static org.lwjgl.opengl.GL20.glUseProgram;
import org.lwjgl.opengl.GLCapabilities;
import org.lwjgl.opengl.GLUtil;
import org.lwjgl.system.Callback;
import static org.lwjgl.system.MemoryUtil.NULL;
import static org.lwjgl.system.MemoryUtil.memAddress;

/**
 *
 * @author thw
 */
public class NBodyLWJGL3 {
    
    public final int width = 800;
    public final int height = 600;
    private int prog;
    private long window;
    private GLCapabilities glCapabilities;
    private Callback debugProc;
    private final ArrayList<Light> lights;
    
    private float dt;
    private float preTime;

    public NBodyLWJGL3() {
        this.lights = new ArrayList<>();
        this.run();
    }
    
    public NBodyLWJGL3(final NbodyCollection n) {
        this.lights = new ArrayList<>();
        this.initLights(n);
        this.run();
    }
    
    private void render() {
        
        glClear(GL_COLOR_BUFFER_BIT);
        for (Light l : this.lights) {
            glColorMask(false, false, false, false);
            glStencilFunc(GL_ALWAYS, 1, 1);
            glStencilOp(GL_KEEP, GL_KEEP, GL_REPLACE);
            glStencilOp(GL_KEEP, GL_KEEP, GL_KEEP);
            glStencilFunc(GL_EQUAL, 0, 1);
            glColorMask(true, true, true, true);
            glUseProgram(prog);
            glUniform2f(glGetUniformLocation(prog, "lightLocation"), l.location.x, height - l.location.y);
            glUniform3f(glGetUniformLocation(prog, "lightColor"), l.red, l.green, l.blue);
            glEnable(GL_BLEND);
            glBlendFunc(GL_ONE, GL_ONE);
            glDisable(GL_BLEND);
            glUseProgram(0);
            glClear(GL_STENCIL_BUFFER_BIT);
        }
    }
    
    private void run() {

        try {

            this.init();
            this.loop();
            if (debugProc != null) debugProc.free();          
            glfwDestroyWindow(window);

        } catch (final Exception eX) {
            Logger.getLogger(NBodyLWJGL3.class.getName()).log(Level.SEVERE, null, eX);
        } finally {
            glfwTerminate();
        }
    }
    
    private void loop() {

        long thisTime = 0L;
        
        while (!glfwWindowShouldClose(window)) {

            glfwPollEvents();
            glViewport(-FrameVars.V_WIDTH, -FrameVars.V_HEIGHT, 
                FrameVars.ADD_VIEWPORT_WIDTH, FrameVars.ADD_VIEWPORT_HEIGHT);
            //glViewport(0, 0, FrameVars.V_WIDTH, FrameVars.V_HEIGHT);

            thisTime = System.nanoTime();
            this.dt = (thisTime - this.preTime) / 1E9f;
            this.preTime = thisTime;
            render();
            glfwSwapBuffers(window);
        }
    }
    
    private void init() throws IOException {

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        glfwWindowHint(GLFW_SAMPLES, 4);

        window = glfwCreateWindow(FrameVars.V_WIDTH, FrameVars.V_HEIGHT, "nbody", 0L, NULL);
        if (window == NULL) {
            throw new AssertionError("Failed to create the GLFW window");
        }

        // Get the resolution of the primary monitor
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // Center our window
        glfwSetWindowPos(
            window,
            (vidmode.width() - FrameVars.V_WIDTH) / 2,
            (vidmode.height() - FrameVars.V_HEIGHT) / 2
        );
        
        glfwSetCursor(window, glfwCreateStandardCursor(GLFW_HAND_CURSOR));

        glfwMakeContextCurrent(window);
        glfwSwapInterval(0);
        glfwShowWindow(window);

        IntBuffer framebufferSize = BufferUtils.createIntBuffer(2);
        nglfwGetFramebufferSize(window, memAddress(framebufferSize), memAddress(framebufferSize) + 4);

        glCapabilities = GL.createCapabilities();
        if (!glCapabilities.OpenGL20) {
            throw new AssertionError("This demo requires OpenGL 2.0.");
        }

        this.debugProc = GLUtil.setupDebugMessageCallback();
        this.prog = this.createProg();
        
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE);
    }
    
    private int createProg() throws IOException {
        
        final int fragshd = ShaderUtils.createShader("shader.frag", GL_FRAGMENT_SHADER);
        final int prg = ProgUtils.createProgram(fragshd);
        glUseProgram(prg);
        /*default_viewUniform = glGetUniformLocation(prog, "view");
        default_projUniform = glGetUniformLocation(prog, "proj");
        default_modelUniform = glGetUniformLocation(prog, "model");*/
        glUseProgram(0); 
        
        return prog;
    }
    
    private void initLights(final NbodyCollection n) {
        
        int i = 0;
        while(n.perform(i)) {
            
            Vector3f pos = new Vector3f(
                n.c[i].graphics.graphicX, 
                n.c[i].graphics.graphicY,
                n.c[i].graphics.graphicZ
            );
                        
            lights.add(new Light(pos, 
                (float) Math.random() * 10, (float) Math.random() * 10, (float) Math.random() * 10));
            
            ++i;
        }
    }    
    
}
