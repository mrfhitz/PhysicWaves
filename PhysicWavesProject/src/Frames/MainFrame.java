package Frames;

import Engine.Core;
import Engine.Sound;
import Objects.KeyBoard;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import javax.swing.JFrame;

public class MainFrame extends Core implements KeyListener {

    private Window w;
    public static int actualVew = -1;
    private static Menu menu;
    private static Fotoelectrico fotoelectrico;
    private static Compton compton;
    private static Rayleigh rayleigh;
    private static Raiosx raiosx;
    public static WebIntegration web;
    private boolean debug = true;
    public static final int MENU = -1;
    public static final int WEBINTEGRATION = 0;
    public static final int FOTOELECTRICO = 1;
    public static final int COMPTON = 2;
    public static final int RAYLEIGH = 3;
    public static final int RAIOX = 4;
    public static final int EXIT = 4;

    public void init() {
        super.init();
        w = s.getFullScreenWindow();
        menu = new Menu();
        fotoelectrico = new Fotoelectrico();
        compton = new Compton(w);
        rayleigh = new Rayleigh(w);
        raiosx = new Raiosx(w);
        web = new WebIntegration(s);
        w.addKeyListener(this);
    }

    @Override
    public void draw(Graphics2D g) {
        switch (actualVew) {
            case WEBINTEGRATION:
                setStat(true);
                web.draw();
                break;
            case FOTOELECTRICO:
                fotoelectrico.draw(g, w);
                break;
            case COMPTON:
                compton.draw(g);
                break;
            case RAYLEIGH:
                rayleigh.draw(g);
                break;
            case RAIOX:
                raiosx.draw(g, w);
                break;
            case 5:
                stop();
                break;
            default:
                menu.draw(g, w);
                break;

        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        ke.consume();
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        
        if(ke.getKeyCode() == KeyEvent.VK_Q)
            debug = !debug;
        
        switch (actualVew) {
            case FOTOELECTRICO:
                fotoelectrico.keyPressed(ke);
                break;
            case COMPTON:
                compton.keyPressed(ke);
                break;
            case RAYLEIGH:
                rayleigh.keyPressed(ke);
                break;
            case RAIOX:
                raiosx.keyPressed(ke);
                break;
            case WEBINTEGRATION:
                web.keyPressed(ke);
                break;
            default:
                menu.keyPressed(ke);
                break;
        }

        ke.consume();
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        ke.consume();
    }
    
    public void setStat(boolean bl) {
        stat = bl;
    }
}
