package fr.com.jfish.jfgnbody.lwjgl3;

import fr.com.jfish.jfgnbody.lwjgl3.maths.Vector3f;
import fr.com.jfish.jfgnbody.nbody.NbodyCollection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
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
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_ALWAYS;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_EQUAL;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_KEEP;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_REPLACE;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_STENCIL_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_STENCIL_TEST;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glColorMask;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glStencilFunc;
import static org.lwjgl.opengl.GL11.glStencilOp;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL20.glUniform2f;
import static org.lwjgl.opengl.GL20.glUniform3f;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL20.glValidateProgram;
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
    private int fragShader;
    private long window;
    private GLCapabilities glCapabilities;
    private Callback debugProc;
    private final ArrayList<Light> lights;
    public NbodyCollection nBodies;
    private float dt;
    private float preTime;

    public NBodyLWJGL3() {
        this.lights = new ArrayList<>();
        this.initLightsMock(10);
        this.run();
    }
    
    public NBodyLWJGL3(final int n) {
        this.lights = new ArrayList<>();
        this.nBodies = new NbodyCollection(n);
        this.initLights();
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
            //glUniform3f(glGetUniformLocation(prog, "lightLocation"), l.location.x, 
            //    l.location.y, l.location.z);
            
            glUniform3f(glGetUniformLocation(prog, "lightColor"), l.red, l.green, l.blue);
            glEnable(GL_BLEND);
            glBlendFunc(GL_ONE, GL_ONE);
            
            glBegin(GL_QUADS);
            {
                glVertex2f(0, 0);
                glVertex2f(0, height);
                glVertex2f(width, height);
                glVertex2f(width, 0);
            }
            glEnd();
            
            glDisable(GL_BLEND);
            glUseProgram(0);
            glClear(GL_STENCIL_BUFFER_BIT);
        }
    }
    
    private void run() {

        try {

            init();
            loop();
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
            //glViewport(0, 0, width, height);

            thisTime = System.nanoTime();
            dt = (thisTime - this.preTime) / 1E9f;
            preTime = thisTime;
            render();
            glfwSwapBuffers(window);
        }
    }
    
    private void init() throws IOException {

        if (!glfwInit()) throw new IllegalStateException("Unable to initialize GLFW");
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        glfwWindowHint(GLFW_SAMPLES, 4);

        //window = glfwCreateWindow(FrameVars.V_WIDTH, FrameVars.V_HEIGHT, "nbody", 0L, NULL);
        window = glfwCreateWindow(width, height, "nbody", 0L, NULL);
        if (window == NULL) {
            throw new AssertionError("Failed to create the GLFW window");
        }

        // Primary monitor resolution :
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // Setup window :
        glfwSetWindowPos(
            window,
            (vidmode.width() - FrameVars.V_WIDTH) / 2,
            (vidmode.height() - FrameVars.V_HEIGHT) / 2
            //(vidmode.width() - width) / 2,
            //(vidmode.height() - height) / 2
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
        //this.prog = this.createProg();
        this.createProgs();
        
        //glEnableClientState(GL_VERTEX_ARRAY);
        //glEnable(GL_DEPTH_TEST);
        //glEnable(GL_CULL_FACE);
        //glBlendFunc(GL_SRC_ALPHA, GL_ONE);
    }
    
    private void createProgs() {
        
        this.prog = glCreateProgram();
        this.fragShader = glCreateShader(GL_FRAGMENT_SHADER);
        final URL url = Thread.currentThread().getContextClassLoader().getResource("shader.frag");
        final File file = new File(url.getFile());
        final StringBuilder fragmentShaderSource = new StringBuilder();
        
        try {
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) fragmentShaderSource.append(line).append("\n");
        } catch (FileNotFoundException e) {
            Logger.getLogger(NBodyLWJGL3.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            Logger.getLogger(NBodyLWJGL3.class.getName()).log(Level.SEVERE, null, e);
        }
        
        glShaderSource(this.fragShader, fragmentShaderSource);
        glCompileShader(this.fragShader);
        if (glGetShaderi(this.fragShader, GL_COMPILE_STATUS) == GL_FALSE) System.err.println(
            "Fragment shader not compiled!");
        glAttachShader(this.prog, this.fragShader);
        glLinkProgram(this.prog);
        glValidateProgram(this.prog);
        
        /*glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        //glOrtho(0, width, height, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_STENCIL_TEST);*/
        glOrtho(0, width, height, 0, 1, -1);
        glClearColor(0, 0, 0, 0);
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
    
    private void initLights() {
        
        int i = 0;
        Vector3f pos;
        while(this.nBodies.perform(i)) {
            
            pos = new Vector3f(
                this.nBodies.c[i].graphics.graphicX, 
                this.nBodies.c[i].graphics.graphicY,
                this.nBodies.c[i].graphics.graphicZ
            );
                        
            lights.add(new Light(pos, 
                (float) Math.random() * 10, (float) Math.random() * 10, (float) Math.random() * 10));
            
            ++i;
        }
    }    
    
    private void initLightsMock(final int n) {
        
        Vector3f pos;
        for (int i = 0; i < n; i++) {
            pos = new Vector3f(
                (float) Math.random() * width, (float) Math.random() * height,
                    (float) Math.random() * height
                // (float) Math.random(), (float) Math.random(), (float) Math.random()
            );
            
            lights.add(new Light(pos, 
                (float) Math.random() * 10, (float) Math.random() * 10, (float) Math.random() * 10));
        }        
    }
    
}
