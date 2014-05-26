package Objects;

import java.awt.Graphics2D;

public class Line {
    private Ponto i, f;
    private boolean visible;

    public Line(int xi, int yi, int xf, int yf) {
        i = new Ponto(xi,yi);
        f = new Ponto(xf, yf);
        visible = true;
    }
    
    public void draw(Graphics2D g){
        if(visible){
            g.drawLine(i.x, i.y, f.x, f.y);
        }
    }
    
    public void sobe(){
        f.y--;
    }
    
    public void desce(){
        f.y++;
    }
    
}//end class
