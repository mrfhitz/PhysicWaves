package Engine;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class ScreenManager {
    private JFrame f;
    private GraphicsDevice vc;

    //give vc access to monitor screen
    public ScreenManager() {
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        vc = e.getDefaultScreenDevice();
    }

    //get all compatible DM
    public DisplayMode[] getCompatibleDisplayModes() {
        return vc.getDisplayModes();
    }

    //compare DM passed in to DM and see if they match
    public DisplayMode findFirstCompatibleMode(DisplayMode modes[]) {
        DisplayMode goodModes[] = vc.getDisplayModes();
        for (int i = 0; i < modes.length; i++) {
            for (int j = 0; j < goodModes.length; j++) {
                if (displayModesMatch(modes[i], goodModes[j])) {
                    return modes[i];
                }
            }
        }
        return null;
    }

    //get current DM
    public DisplayMode getCurrentDisplayMode() {
        return vc.getDisplayMode();
    }

    //check if two modes match aech other
    private boolean displayModesMatch(DisplayMode m1, DisplayMode m2) {
        if (m1.getWidth() != m2.getWidth() || m1.getHeight() != m2.getHeight()) {
            return false;
        }

        if (m1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && m2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && m1.getBitDepth() != m2.getBitDepth()) {
            return false;
        }
        if (m1.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && m2.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && m1.getRefreshRate() != m2.getRefreshRate()) {
            return false;
        }
        return true;
    }

    //make frame full screen
    public void setFullScreen(DisplayMode dm) {
        f = new JFrame();
        f.setUndecorated(true);
        f.setIgnoreRepaint(true);
        f.setResizable(false);
        vc.setFullScreenWindow(f);

        if (dm != null && vc.isDisplayChangeSupported()) {
            try {
                vc.setDisplayMode(dm);
            } catch (Exception e) {
                //########ERRROR#######
            }
        }

        f.createBufferStrategy(2);
    }

    //we will set Graphics object = to this
    public Graphics2D getGraphics() {
        Window w = vc.getFullScreenWindow();
        if (w != null) {
            BufferStrategy s = w.getBufferStrategy();
            return (Graphics2D) s.getDrawGraphics();
        } else {
            return null;
        }
    }

    //updates display
    public void update() {
        Window w = vc.getFullScreenWindow();
        if (w != null) {
            BufferStrategy s = w.getBufferStrategy();
            if (!s.contentsLost()) {
                s.show();
            }
        }
    }

    //retuen full screen windows
    public Window getFullScreenWindow() {
        return vc.getFullScreenWindow();
    }

    //get whidth of window
    public int getWidth() {
        Window w = vc.getFullScreenWindow();
        if (w != null) {
            return w.getWidth();
        } else {
            return 0;
        }
    }

    //get height of window
    public int getHeight() {
        Window w = vc.getFullScreenWindow();
        if (w != null) {
            return w.getHeight();
        } else {
            return 0;
        }
    }

    //get out of full screen
    public void restoreScreen() {
        Window w = vc.getFullScreenWindow();
        if (w != null) {
            w.dispose();
        }
        vc.setFullScreenWindow(null);
    }

    //create image compatible with monitor
    public BufferedImage createCompatibleImage(int w, int h, int t) {
        Window win = vc.getFullScreenWindow();
        if (win != null) {
            GraphicsConfiguration gc = win.getGraphicsConfiguration();
            return gc.createCompatibleImage(w, h, t);
        } else {
            return null;
        }
    }

    /**
     * @return the f
     */
    public JFrame getFrame() {
        return f;
    }

    /**
     * @param f the f to set
     */
    public void setFrame(JFrame f) {
        this.f = f;
    }
}//end class
