package Frames;

import MainSources.Main;
import Objects.Electron;
import Objects.KeyBoard;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.util.LinkedList;

public class Fotoelectrico {

    public static final int ID = MainFrame.FOTOELECTRICO;
    private String title = "Efeito Fotoelectrico";
    private Image bg;
    private Image[] system = new Image[2];
    private Image[] intencidade = new Image[11];
    private Image[] frequencia = new Image[11];
    private int estado_intencidade = 0;
    private int estado_frequencia = 0;
    private int velocidade = 1;
    private Electron[] elects = new Electron[25];
    private int estado_elects = 0;

    public Fotoelectrico() {
        init();
    }

    private void init() {
        bg = new ImageIcon("img/frame.png").getImage();
        system[0] = new ImageIcon("img/light_system/system_off.png").getImage();
        system[1] = new ImageIcon("img/light_system/system_on.png").getImage();

        for (int i = 0; i < intencidade.length; i++) {
            intencidade[i] = new ImageIcon("img/light_system/light_" + i + ".png").getImage();
            frequencia[i] = new ImageIcon("img/light_system/color_" + i + ".png").getImage();
        }
        
        for (int i = 0; i < elects.length; i++) {
            int y = random(230, 370);
            int x = random(170, 465);
            elects[i] = new Electron(x, y, 4);
            elects[i].setVelocidade(velocidade);
            elects[i].setVisible(false);
        }
    }

    private void addElect(int qtd) {
        int contador = 0;
        for (int i = 0; i < elects.length; i++) {
            if (!elects[i].isVisible() && contador != qtd) {
                elects[i].setVisible(true);
                contador++;
            }
        }
    }

    private void remElect(int qtd) {
        int contador = 0;
        for (int i = 0; i < elects.length; i++) {
            if(elects[i].isVisible() && contador != qtd){
            elects[i].setVisible(false);
            contador++;
            }
        }
    }

    private void incVelElect() {
        velocidade++;
        for (Electron e : elects) {
            e.setVelocidade(velocidade);
        }
    }

    private void decVelElect() {
        velocidade--;
        for (Electron e : elects) {
            e.setVelocidade(velocidade);
        }
    }

    private int random(int a, int b) {
        int c = Math.abs((b - a));
        return a + (int) (Math.random() * (c + 1));
    }

    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        if (keycode == KeyEvent.VK_ESCAPE) {
            MainFrame.actualVew = -1;
        } else if (keycode == KeyEvent.VK_UP) {
            if (estado_intencidade != 10) {
                estado_intencidade++;
                addElect(2);
            }
        } else if (keycode == KeyEvent.VK_DOWN) {
            if (estado_intencidade != 0) {
                estado_intencidade--;
                remElect(2);
            }
        } else if (keycode == KeyEvent.VK_W) {
            if (estado_frequencia != 10) {
                estado_frequencia++;
                incVelElect();
                
            }
        } else if (keycode == KeyEvent.VK_S) {
            if (estado_frequencia != 0) {
                estado_frequencia--;
                decVelElect();
            }
        } else if (keycode == KeyEvent.VK_H) {
            MainFrame.web.setPage(ID);
            MainFrame.actualVew = MainFrame.WEBINTEGRATION;
        }
    }

    public void draw(Graphics2D g, Window w) {

        g.drawImage(bg, 0, 0, null);
        g.setColor(Color.BLACK);
        g.drawString(title, (w.getWidth() / 2) - (title.length() * 4), 87);

        g.setColor(Color.WHITE);


        if (estado_intencidade == 0) {
            g.drawImage(system[0], 100, 120, null);
        } else {
            g.drawImage(system[1], 100, 120, null);
        }

        g.drawImage(intencidade[estado_intencidade], 650, 200, null);
        g.drawImage(frequencia[estado_frequencia], 550, 200, null);

        for (Electron e : elects) {
            if(e.isVisible()){
            if (e.getCentro().x + e.getRaio() < 465) {
                g.fillOval((e.getCentro().x - e.getRaio()), (e.getCentro().y - e.getRaio()), e.getRaio() * 2, e.getRaio() * 2);
            } else {
                e.getCentro().x = (170 + e.getRaio());
            }
            e.moveDireita();
            }
        }



        KeyBoard.draw(g, KeyBoard.UD_ARROWS, "Intensidade");
        KeyBoard.draw(g, KeyBoard.KEY_S);
        KeyBoard.draw(g, KeyBoard.KEY_W, "Frequência");
        KeyBoard.draw(g, KeyBoard.KEY_H, "Ajuda");
    }
}
