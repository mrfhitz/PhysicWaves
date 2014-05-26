package Frames;

import Engine.Sound;
import Objects.Atom;
import Objects.KeyBoard;
import Objects.Line;
import Objects.Wave;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.ImageIcon;

public class Compton {
     public static final int ID = MainFrame.COMPTON;

    private String title = "Efeito de Compton";
    private Sound click;
    private int ang = 10;
    private Image bg;
    Window w;
    private Line linha1;
    private Line linha2;
    private Wave wave1 = new Wave();
    private Atom bola;
    private int mx, my;

    public Compton(Window w) {
        this.w = w;
        this.w = w;
        mx = w.getWidth() / 2;
        my = w.getHeight() / 2;
        init();
    }

    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        if (keycode == KeyEvent.VK_ESCAPE) {
            MainFrame.actualVew = -1;
        } else if (keycode == KeyEvent.VK_RIGHT || keycode == KeyEvent.VK_UP) {
            if (wave1.getFrec() < 0.40) {
                click.playSound();
                wave1.incFrequency();
                linha1.sobe();
                linha2.desce();
                ang++;
            }
        } else if (keycode == KeyEvent.VK_LEFT || keycode == KeyEvent.VK_DOWN) {
            if (wave1.getFrec() > 0.02) {
                click.playSound();
                wave1.decFrequency();
                linha1.desce();
                linha2.sobe();
                ang--;
            }
        }else if(keycode == KeyEvent.VK_H){
            MainFrame.web.setPage(ID);
            MainFrame.actualVew = MainFrame.WEBINTEGRATION;
        }
    }

    public void draw(Graphics2D g) {
        int actual = 130;

        bg = new ImageIcon("img/frame.png").getImage();
        g.drawImage(bg, 0, 0, null);
        g.setColor(Color.BLACK);
        g.drawString(title, (w.getWidth() / 2) - (title.length() * 5), 87);

        g.drawString(ang + "º", mx + 60, my);
        wave1.setY0(my);
        wave1.setXf(mx);
        wave1.setXi(mx - 200);
        wave1.draw(g);

        g.drawOval(bola.getX(), bola.getY(), bola.getDiameter(), bola.getDiameter());

        // Carregas  as Ondas com comprimentos de onda <
        linha1.draw(g);
        linha2.draw(g);
        
        KeyBoard.draw(g, KeyBoard.LR_ARROWS, "Frequencia");
        KeyBoard.draw(g, KeyBoard.KEY_H, "Ajuda");
    }

    public void init() {

        bola = new Atom(mx - 50, my - 50, 50);
        linha1 = new Line(mx + 50, my, mx + 110, my-10);
        linha2 = new Line(mx + 50, my, mx + 110, my+10);
        click = new Sound(new File("audio/click.wav"));
    }
}// end class