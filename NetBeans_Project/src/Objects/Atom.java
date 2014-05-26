package Objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

public class Atom {
    private int x, y;
    private int radius;

    public Atom(int x, int y, int r) {
        this.x = x;
        this.y = y;
        radius = r;
    }

    public Atom(int x, int y) {
        this(x,y,25);
    }
    
    public Atom() {
        this(0,0,25);
    }
    
    public void incRadius(){
        radius++;
        x -= 1;
        y -= 1;
    }
    
    public void decRadius(){
        radius--;
        x += 1;
        y += 1;
    }
    
    public void draw(Graphics2D g){
        g.setColor(Color.BLACK);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }
    
    public void draw(Graphics2D g, Image img){
        g.drawImage(img, x - (radius) - 10, y - (radius) - 10, null);
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int getRadius(){
        return radius;
    }
    
    public int getDiameter(){
        return radius * 2;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
    
    
}
