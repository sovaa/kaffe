package org.eldslott.hb;

import org.eldslott.hb.entity.Entity;
import org.eldslott.hb.entity.Player;
import org.eldslott.hb.entity.Ship;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

/**
 * @author <a href="mailto:oscar.eriks@gmail.com">Oscar Eriksson</a>
 * @date 12/11/13
 */
public class Game implements Runnable {
    private static final String WINDOW_TITLE = "kaffe";
    private int width = 1280;
    private int height = 720;

    private static boolean running = true;
    private List<Entity> entities = new ArrayList<>();
    private boolean waitingForKeyPress = false;

    private Entity player;
    private Entity ship;

    private static long	 timerTicksPerSecond = Sys.getTimerResolution();
    private long lastLoopTime = getTime();
    private long lastFpsTime;
    private int fps;

    public Game() {
        initialize();
        initializeEntities();
    }

    @Override
    public void run() {
        while (!Display.isCloseRequested() && Game.isRunning()) {
            // clear screen
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glMatrixMode(GL_MODELVIEW);
            glLoadIdentity();

            // let subsystem paint
            frameRendering();

            // render OpenGL here
            Display.update();
        }

        Display.destroy();
    }

    private void frameRendering() {
        Display.sync(60);

        long delta = getTime() - lastLoopTime;
        lastLoopTime = getTime();
        lastFpsTime += delta;
        fps++;

        // update our FPS counter if a second has passed
        if (lastFpsTime >= 1000) {
            Display.setTitle(WINDOW_TITLE + " (FPS: " + fps + ")");
            lastFpsTime = 0;
            fps = 0;
        }

        // cycle round asking each entity to move itself
        if (!waitingForKeyPress) {// && !soundManager.isPlayingSound()) {
            for (Entity entity : entities) {
                entity.move(delta);
            }
        }

        // cycle round drawing all the entities we have in the game
        for (Entity entity : entities) {
            entity.draw();
        }

        pollInput();
    }

    private void pollInput() {
        if (Mouse.isButtonDown(0)) {
            int x = Mouse.getX();
            int y = Mouse.getY();

            System.out.println("MOUSE DOWN @ X: " + x + " Y: " + y);
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            System.out.println("SPACE KEY IS DOWN");
        }

        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                //agent.keyDown(Keyboard.getEventKey());
            } else {
                //agent.keyUp(Keyboard.getEventKey());
            }
        }
    }

    /**
     * Intialise the common elements for the game
     */
    private void initialize() {
        // initialize the window beforehand
        try {
            //setDisplayMode();
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.setTitle(WINDOW_TITLE);
            //Display.setFullscreen(true);
            Display.create();

            // enable textures since we're going to use these for our sprites
            glEnable(GL_TEXTURE_2D);

            // disable the OpenGL depth test since we're rendering 2D graphics
            glDisable(GL_DEPTH_TEST);

            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();

            glOrtho(0, width, height, 0, -1, 1);
            glMatrixMode(GL_MODELVIEW);
            glLoadIdentity();
            glViewport(0, 0, width, height);
        }
        catch (LWJGLException le) {
            System.out.println("Game exiting - exception in initialization:");
            le.printStackTrace();
            setRunning(false);
            return;
        }
    }

    private void initializeEntities() {
        player = new Player();
        ship = new Ship();

        entities.add(ship);
        entities.add(player);
    }

    /**
     * Sets the display mode for fullscreen mode
     */
    private boolean setDisplayMode() {
        try {
            // get modes
            DisplayMode[] dm = org.lwjgl.util.Display.getAvailableDisplayModes(width, height, -1, -1, -1, -1, 60, 60);

            org.lwjgl.util.Display.setDisplayMode(dm, new String[] {
                    "width=" + width,
                    "height=" + height,
                    "freq=" + 60,
                    "bpp=" + org.lwjgl.opengl.Display.getDisplayMode().getBitsPerPixel()
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to enter fullscreen, continuing in windowed mode");
        }

        return false;
    }

    public static long getTime() {
        return (Sys.getTime() * 1000) / timerTicksPerSecond;
    }

    public static void setRunning(boolean running) {
        Game.running = running;
    }

    public static boolean isRunning() {
        return Game.running;
    }
}
