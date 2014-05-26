package Frames;

import Engine.Sound;
import Objects.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import org.omg.CORBA.SetOverrideType;

public class Raiosx {
    public static final int ID = MainFrame.RAIOX;
    
    private String title = "Produção de Raios x";
    private Image bg, atomImage, emissor, reflector, point, select;
    private Sound explosion, drop;
    private Ponto centro;
    private Electron e1, e2, incidente;
    private Electron[] e = new Electron[5];
    private Atom a;
    private Wave raiox;
    private Wave wave1;
    private int counter;
    private Ponto[] orbit1;
    private Ponto[] orbit2;
    private boolean orbit_flag;
    private boolean start_shot;
    private boolean zoomin;
    private int tempo;

    public Raiosx(Window w) {
        centro = new Ponto(w.getWidth() / 2, w.getHeight() / 2);
        init();
    }
    
    private void init(){
        tempo = 200;
        counter = 0;
        orbit1 = new Ponto[180];
        orbit2 = new Ponto[180];
        orbit_flag = false;
        start_shot = false;
        zoomin = false;
        
        Ponto p_tmp = new Ponto(0,0);
        for (int i = 0; i < orbit1.length; i++) {
            orbit1[i] = p_tmp;
            orbit2[i] = p_tmp;
        }
        
        a = new Atom(centro.x + 200, centro.y, 30);
        e1 = new Electron(a.getX(), a.getY(), 70);
        e2 = new Electron(a.getX(), a.getY(), 110);
        incidente = new Electron(480, 250, 6);
        incidente.setVelocidade(4);
        incidente.setVisible(false);
                
        for (int i = 0; i < e.length; i++) {
            int ex = random(140,290);
            int ey = random(290,340);
            e[i] = new Electron(ex, ey, 4);
            e[i].setVelocidade(1);
            e[i].setVisible(false);
            
        }
        
        raiox = new Wave(centro.x - 150, centro.x - 100, centro.y + 70);
        raiox.setVisible(true);
        raiox.setAltura(20);
        raiox.setFrec(0.4);
        raiox.setDiagonal(-1);
        
        wave1 = new Wave(620, 680, 370);
        wave1.setVisible(true);
        wave1.setAltura(20);
        wave1.setFrec(0.4);
        wave1.setDiagonal(1);
        
        
        
        // -- Sound --
        explosion = new Sound(new File("audio/electron_pops_out.wav"));
        drop = new Sound(new File("audio/atom_drop.wav"));
        
        // -- Image --
        bg = new ImageIcon("img/frame.png").getImage();
        atomImage = new ImageIcon("img/atom/mini_atom.png").getImage();
        emissor = new ImageIcon("img/atom/issuing.png").getImage();
        reflector = new ImageIcon("img/atom/ref.png").getImage();
        point = new ImageIcon("img/atom/point.png").getImage();
        select = new ImageIcon("img/atom/select.png").getImage();
    }
    
    private int random(int a, int b) {
        int c = Math.abs((b - a));
        return a + (int) (Math.random() * (c + 1));
    }

    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        if (keycode == KeyEvent.VK_ESCAPE) {
            MainFrame.actualVew = -1;
        }else if (keycode == KeyEvent.VK_W) {
            zoomin = !zoomin;
        }else if (keycode == KeyEvent.VK_S) {
            orbit_flag = !orbit_flag;
        }else if (keycode == KeyEvent.VK_A) {
            init();
        } else if (keycode == KeyEvent.VK_SPACE) {
            e1.sair();
            e2.dentro();
            start_shot = true;
            tempo = 0;
        }else if(keycode == KeyEvent.VK_H){
            MainFrame.web.setPage(ID);
            MainFrame.actualVew = MainFrame.WEBINTEGRATION;
        }
    }

    public void draw(Graphics2D g, Window w) {
        
        if(counter < orbit1.length){
            orbit1[counter] = e2.getPosicao();
            orbit2[counter] = e1.getPosicao();
            counter++;
        }else{
            orbit1[0] = e2.getPosicao();
            orbit2[0] = e1.getPosicao();
            counter = 1;
        }

        
        g.drawImage(bg, 0, 0, null);
        g.setColor(Color.BLACK);
        g.drawString(title, (w.getWidth() / 2) - (title.length() * 5), 87);

        if(orbit_flag){
        for (int i = 0; i < orbit1.length;i++) {
            if(e2.isVisible())
                g.drawImage(point, orbit1[i].x, orbit1[i].y, null);
            if(e1.isVisible())
                g.drawImage(point, orbit2[i].x, orbit2[i].y, null);
            }}
        
        g.setColor(Color.WHITE);  
            
        g.drawImage(emissor, centro.x - 300, centro.y - 50, null);
        g.drawImage(reflector, centro.x - 150, centro.y - 90, null);        
        a.draw(g, atomImage);
        e1.draw(g);
        e2.draw(g, e1.getRaio());
        
        KeyBoard.draw(g, KeyBoard.KEY_SPACE, "Exemplo");
        KeyBoard.draw(g, KeyBoard.KEY_S);
        KeyBoard.draw(g, KeyBoard.KEY_W, "Orbitas / Zoom");
        KeyBoard.draw(g, KeyBoard.KEY_H, "Ajuda");
        
        
        g.setColor(Color.yellow);
        raiox.draw(g);
        
        if(tempo < 200)
        wave1.draw(g);
        
        
        if((incidente.getCentro().x < 700) && start_shot){
            g.fillOval((incidente.getCentro().x - incidente.getRaio()), (incidente.getCentro().y - incidente.getRaio()), incidente.getRaio() * 2, incidente.getRaio() * 2);
            incidente.moveDireita();
        }
        
        for (int i = 0; i < e.length; i++) {
            g.fillOval((e[i].getCentro().x - e[i].getRaio()), (e[i].getCentro().y - e[i].getRaio()), e[i].getRaio() * 2, e[i].getRaio() * 2);
            e[i].moveDireita();
            if(e[i].getCentro().x > 290)
                e[i].setCentro(140, e[i].getCentro().y);
        }
        if(zoomin)
        g.drawImage(select, 0, 0, null);
    tempo++;
    }
}
