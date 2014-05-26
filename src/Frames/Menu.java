package Frames;

import Engine.Core;
import Engine.Sound;
import MainSources.Main;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.ImageIcon;

public class Menu {
    public static final int ID = MainFrame.MENU;
    
    private String title = "Menu";
    private short menuIndex = 0;
    private short maxIndex = 4;
    private Sound[] audio = new Sound[3]; // 0- Backgound sound, 1 - menu jump, 2 - menu select
    private Image[] menuImages;
    private Image[] menuSelectedImages;
    private Image bg;

    public Menu() {
        loadImages();
                // -- Sounds --
        audio[0] = new Sound(new File("audio/menu_bg.wav"),3);
        audio[1] = new Sound(new File("audio/menu_jump.wav"));
        audio[2] = new Sound(new File("audio/menu_select.wav"));
    }

    private void loadImages() {
        menuImages = new Image[maxIndex + 1];
        menuSelectedImages = new Image[maxIndex + 1];

        bg = new ImageIcon("img/bg.png").getImage();

        for (int i = 0; i < maxIndex; i++) {
            menuImages[i] = new ImageIcon("img/menu/menu_" + (i+1) + ".png").getImage();
            menuSelectedImages[i] = new ImageIcon("img/menu/menu_s_" + (i+1) + ".png").getImage();
        }
        
         menuImages[maxIndex] = new ImageIcon("img/menu/exit.png").getImage();
            menuSelectedImages[maxIndex] = new ImageIcon("img/menu/exit_s.png").getImage();
        
        

    }

    public void nextMenu() {
        if (menuIndex < maxIndex) {
            menuIndex++;
        } else {
            menuIndex = 0;
        }
    }

    public void previousMenu() {
        if (menuIndex > 0) {
            menuIndex--;
        }else {
            menuIndex = maxIndex;
        }
    }

    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        if (keycode == KeyEvent.VK_UP) {
            previousMenu();
            audio[1].playSound();
        }else if (keycode == KeyEvent.VK_DOWN) {
            nextMenu();
            audio[1].playSound();
        }else if (keycode == KeyEvent.VK_ESCAPE) {
            Main.getMain().stop();
        }else if (keycode == KeyEvent.VK_ENTER) {
            audio[2].playSound();
            MainFrame.actualVew = (menuIndex + 1);
            audio[0].stopSound();
        }
        
        if(keycode == KeyEvent.VK_1){
            MainFrame.actualVew = MainFrame.FOTOELECTRICO;
        }else if(keycode == KeyEvent.VK_2){
            MainFrame.actualVew = MainFrame.COMPTON;
        }else if(keycode == KeyEvent.VK_3){
            MainFrame.actualVew = MainFrame.RAYLEIGH;
        }else if(keycode == KeyEvent.VK_4){
            MainFrame.actualVew = MainFrame.RAIOX;
        }
    }

    public void draw(Graphics2D g, Window w) {
        int actual = 130;
        
        g.drawImage(bg, 0, 0, null);
        g.setColor(Color.BLACK);
        g.drawString(title, (w.getWidth() / 2) - (title.length()*6) , 87);

        for (int i = 0; i <= maxIndex; i++) {
            if (i == menuIndex) {
                g.drawImage(menuSelectedImages[i], (w.getWidth() / 2) - (menuSelectedImages[i].getWidth(null) / 2), actual, null);
            } else {
                g.drawImage(menuImages[i], (w.getWidth() / 2) - (menuImages[i].getWidth(null) / 2), actual, null);
            }
            actual += 80;
        }
    
    // -- sound --
        if(!audio[0].isRunning())
            audio[0].playSound();
    }
}
