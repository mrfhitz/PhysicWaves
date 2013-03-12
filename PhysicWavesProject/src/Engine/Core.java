package Engine;

import java.awt.*;
import javax.swing.*;

public abstract class Core {

    private static DisplayMode modes[] = {
        new DisplayMode(800, 600, 32, 0),
        new DisplayMode(800, 600, 24, 0),
        new DisplayMode(800, 600, 16, 0),
        new DisplayMode(640, 480, 32, 0),
        new DisplayMode(640, 480, 24, 0),
        new DisplayMode(640, 480, 16, 0)};
    private boolean running;
    protected boolean stat = false;
    protected ScreenManager s;

    //stop method
    public void stop() {
        running = false;
    }

    //call init and loop
    public void run() {
        try {
            init();
            loop();
        } finally {
            s.restoreScreen();
        }
    }

    //set to full screen
    public void init() {
        s = new ScreenManager();
        DisplayMode dm = s.findFirstCompatibleMode(modes);
        s.setFullScreen(dm);

        Window w = s.getFullScreenWindow();
        w.setFont(new Font("Arial", Font.PLAIN, 20));
        w.setBackground(Color.BLACK);
        w.setForeground(Color.WHITE);
        running = true;
    }

    //main loop
    public void loop() {
        long startTime = System.currentTimeMillis();
        long cumTime = startTime;


        while (running) {

            if (!stat) {
                long timePassed = System.currentTimeMillis() - cumTime;
                cumTime += timePassed;
                update(timePassed);

                //draw and update screen
                Graphics2D g = s.getGraphics();
                draw(g);
                g.dispose();
                s.update();

                try {
                    Thread.sleep(20);
                } catch (Exception e) {
                    //###########ERROR#########
                }

            } else {
                update(1);

                //draw and update screen
                s.update();

                try {
                    Thread.sleep(20);
                } catch (Exception e) {
                    //###########ERROR#########
                }
            }

        }

    }//end class

    //update animation
    public void update(long timePassed) {
    }

    //draw to screen
    public abstract void draw(Graphics2D g);
}