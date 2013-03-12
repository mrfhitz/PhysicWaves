package Frames;

import Objects.Atom;
import Objects.KeyBoard;
import Objects.Wave;
import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Rayleigh {
    public static final int ID = MainFrame.RAYLEIGH;

    private String title = "Disperção de Rayleigh";
    private Image bg;
    Window w;
    private Wave[] waveVec = new Wave[3];
    private Wave wave1 = new Wave();
    private Wave wave2 = new Wave();
    private Atom bola;
    private int mx, my;

    public Rayleigh(Window w) {
        this.w = w;
        mx = w.getWidth() / 2;
        my = w.getHeight() / 2;
        init();
    }

    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        if (keycode == KeyEvent.VK_ESCAPE) {
            MainFrame.actualVew = -1;
        } else if (keycode == KeyEvent.VK_UP) {
            if (wave1.getAltura() < 80) {
                wave1.incHeight();
                wave2.incHeight();
            }
            visibleCondition();
        } else if (keycode == KeyEvent.VK_DOWN) {
            if (wave1.getAltura() > 1) {
                wave1.decHeight();
                wave2.decHeight();
            }
            visibleCondition();
        } else if (keycode == KeyEvent.VK_RIGHT) {
            if (wave1.getFrec() < 0.40) {
                wave1.incFrequency();
                wave2.incFrequency();
            }
        } else if (keycode == KeyEvent.VK_LEFT) {
            if (wave1.getFrec() > 0.02) {
                wave1.decFrequency();
                wave2.decFrequency();
            }
        } else if (keycode == KeyEvent.VK_W) {
            if (bola.getRadius() <= 100) {
                bola.incRadius();
                wave1.setXf(wave1.getXf() + 1);
                wave1.setXi(wave1.getXi() + 1);
                for (int i = 0; i < waveVec.length; i++) {
                    waveVec[i].setXi(waveVec[i].getXi() + 1);
                    waveVec[i].setXf(waveVec[i].getXf() + 1);
                }
            }
            visibleCondition();
        } else if (keycode == KeyEvent.VK_S) {
            if (bola.getRadius() >= 50) {
                bola.decRadius();
                wave1.setXf(wave1.getXf() - 1);
                wave1.setXi(wave1.getXi() - 1);
                for (int i = 0; i < waveVec.length; i++) {
                    waveVec[i].setXi(waveVec[i].getXi() - 1);
                    waveVec[i].setXf(waveVec[i].getXf() - 1);
                }
            }
            visibleCondition();
        }else if(keycode == KeyEvent.VK_H){
            MainFrame.web.setPage(ID);
            MainFrame.actualVew = MainFrame.WEBINTEGRATION;
        }
    }
    

    private void visibleCondition() {
        if (wave1.getAltura() >= bola.getRadius()) {
            wave2.setVisible(true);
            for (int i = 0; i < 3; i++) {
                waveVec[i].setVisible(false);
            }
        } else {
            wave2.setVisible(false);
            for (int i = 0; i < 3; i++) {
                waveVec[i].setVisible(true);
            }
        }
    }

    public void draw(Graphics2D g) {
        int actual = 130;

        bg = new ImageIcon("img/frame.png").getImage();
        g.drawImage(bg, 0, 0, null);
        g.setColor(Color.BLACK);
        g.drawString(title, (w.getWidth() / 2) - (title.length() * 5), 87);

        wave1.setY0(my);
        wave1.setXf(mx);
        wave1.setXi(mx - 200);
        wave1.draw(g);

        g.drawOval(bola.getX(), bola.getY(), bola.getDiameter(), bola.getDiameter());

        wave2.setY0(my);
        wave2.setXi(mx);
        wave2.setXf(mx + 200);
        wave2.draw(g);

        // Carregas  as Ondas com comprimentos de onda <
        for (int i = 0; i < 3; i++) {
            if (wave1.getAltura() <= 1) {
                waveVec[i].setVisible(false);
                wave1.setVisible(false);
            } else {
                wave1.setVisible(true);
            }

            waveVec[i].setFrec(wave1.getFrec() * 7);
            waveVec[i].setAltura(wave1.getAltura() / 4);
            waveVec[i].draw(g);
        }
        
        KeyBoard.draw(g, KeyBoard.ARROWS, "Frequencia / Amplitude");
        KeyBoard.draw(g, KeyBoard.KEY_S);
        KeyBoard.draw(g, KeyBoard.KEY_W, "Raio Atmo");
        KeyBoard.draw(g, KeyBoard.KEY_H, "Ajuda");
    }

    public void init() {

        bola = new Atom(mx - 50, my - 50, 50);

        for (int i = 0; i < waveVec.length; i++) {
            waveVec[i] = new Wave(mx + 50, mx + 110, my);
            waveVec[i].setVisible(false);
        }
        waveVec[0].setY0(my + 20);
        waveVec[0].setDiagonal(1);
        waveVec[0].setXf(mx + 80);
        waveVec[0].setFrec(wave1.getFrec() * 7);
        waveVec[2].setY0(my - 20);
        waveVec[2].setDiagonal(-1);
        waveVec[2].setXf(mx + 80);
        waveVec[2].setFrec(wave1.getFrec() * 7);
    }
}
