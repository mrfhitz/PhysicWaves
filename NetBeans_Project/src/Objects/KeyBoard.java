package Objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class KeyBoard {

    public static final int ARROWS = 1;
    public static final int LR_ARROWS = 2;
    public static final int UD_ARROWS = 3;
    public static final int KEY_S = 4;
    public static final int KEY_W = 5;
    public static final int KEY_H = 6;
    public static final int KEY_Q = 7;
    public static final int KEY_SPACE = 8;

    public static Image getImage(int key) {
        if (key == ARROWS) {
            return new ImageIcon("img/keyboard/arrows.png").getImage();
        } else if (key == LR_ARROWS) {
            return new ImageIcon("img/keyboard/LR_arrows.png").getImage();
        } else if (key == UD_ARROWS) {
            return new ImageIcon("img/keyboard/UD_arrows.png").getImage();
        } else if (key == KEY_S) {
            return new ImageIcon("img/keyboard/s.png").getImage();
        } else if (key == KEY_W) {
            return new ImageIcon("img/keyboard/w.png").getImage();
        } else if (key == KEY_H) {
            return new ImageIcon("img/keyboard/h.png").getImage();
        } else if (key == LR_ARROWS) {
            return new ImageIcon("img/keyboard/h.png").getImage();
        } else if (key == KEY_Q) {
            return new ImageIcon("img/keyboard/q.png").getImage();
        }else if (key == KEY_SPACE) {
            return new ImageIcon("img/keyboard/space.png").getImage();
        }

        return null;
    }

    public static void draw(Graphics2D g, int key, String msg) {
        int x = 100, y = 500;
        int sx = 255, sy = 530;
        Color tmp = g.getColor();

        if (key == ARROWS) {
            x = 100;
            sx = x+155;
        } else if (key == LR_ARROWS) {
            x = 100;
            sx = x+155;
        } else if (key == UD_ARROWS) {
            x = 100;
            sx = x+140;
        } else if (key == KEY_S) {
            x = 400;
            sx = x+60;
        } else if (key == KEY_W) {
            x = 450;
            sx = x+60;
        } else if (key == KEY_H) {
            x = 660;
            sx = x+60;
        } else if (key == KEY_Q) {
            x = 10;
            y = 65;
            sx = x+60;
        } else if (key == KEY_SPACE) {
            x = 10;
            sx = 260;
        }
        
        sy = y+30;

        g.setColor(Color.WHITE);
        Image img = getImage(key);
        if (img != null) {
            g.drawImage(img, x, y, null);
        }
        if (msg != null) {
            drawString(g,msg,sx,sy);
        }
        g.setColor(tmp);

    }

    public static void draw(Graphics2D g, int key) {
        draw(g, key, null);
    }

    private static void drawString(Graphics2D g, String msg, int sx, int sy) {
        String[] tmp;
        String a="",b="";
        int meio;
        
        if(msg.length() > 12){
        tmp = msg.split(" ");
        meio = tmp.length / 2;
        
        
            for (int i = 0; i < tmp.length; i++) {
                if(i < meio)
                    a+= tmp[i]+" ";
                else
                    b+= tmp[i]+" ";
            }
            
            g.drawString(a, sx, sy-10);
            g.drawString(b, sx, sy+15);
            
        }else{
         g.drawString(msg, sx, sy);   
        }
        
        
        
        
        
    }

}
