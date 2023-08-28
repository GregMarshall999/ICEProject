package com.greglabs.iceproject.lwjgl;

import com.greglabs.iceproject.lwjgl.application.Application;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.glfw.GLFWErrorCallback.createPrint;
import static org.lwjgl.opengl.GL.createCapabilities;
import static org.lwjgl.opengl.GL46.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL46.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL46.glClear;
import static org.lwjgl.opengl.GL46.glClearColor;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.*;

public class Testing extends Application {
    //window handle
    private long window;

    @Override
    public void run() {
        System.out.println("Testing LWJGL " + Version.getVersion());

        init();
        loop();

        //free window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        //terminate glfw and free error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    @Override
    protected void init() {
        //setup error callback in System.err
        createPrint(System.err).set();

        //initialize GLFW.
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        //configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        //create window
        window = glfwCreateWindow(300, 300, "LWJGL Testing", NULL, NULL);
        if (window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        //setup key callback called every time a key is pressed repeated or released
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true);
        });

        //get thread stack and push new frame
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            //pass window size to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            //get resolution of primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            //center window
            glfwSetWindowPos(window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        }


        glfwMakeContextCurrent(window); //make OpenGL context current
        glfwSwapInterval(1);            //enable v-sync
        glfwShowWindow(window);         //make window visible
    }

    @Override
    protected void loop() {
        //enable openGL bindings
        createCapabilities();

        //set clear color
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        //loop rendering until user closes window
        while (!glfwWindowShouldClose(window)) {
            //clear frame buffer
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            //swap color buffers
            glfwSwapBuffers(window);

            //poll all window events
            glfwPollEvents();
        }
    }
}
