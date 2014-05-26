package Objects;

import java.awt.Graphics2D;

public class Wave {
    private int xi, xf;
    private int y0;
    private double frec = 0.05;
    private int altura = 50;
    private float tmp;
    private float diagonal = 0;
    private boolean visible;
    private int frame = 0;

    public Wave(int xi, int xf, int y0) {
        this.xi = xi;
        this.xf = xf;
        this.y0 = y0;
        visible = true;
    }

    public Wave() {
        this(100, 300, 100);
    }

    public void draw(Graphics2D g) {
        int y1, y2;

        tmp = 0;
        if (isVisible()) {
            int h = getAltura();
            for (int x = getXi(); x <= getXf(); x++) {
                y1 = (int) (((Math.sin((x - getFrame()) * (getFrec()))) * h));
                y2 = (int) (((Math.sin(((x + 1) - getFrame()) * (getFrec()))) * h));
                g.drawLine(x, (y1 + getY0()) + ((int)tmp), x, (y2 + getY0()) + ((int)tmp));
                tmp = tmp + diagonal;
            }
        }
        frame++;
        
    }

    public void incFrequency() {
        setFrec(getFrec() + 0.01);
    }

    public void decFrequency() {
        if(getFrec() != 0)
        setFrec(getFrec() - 0.01);
    }

    public void incHeight() {
        setAltura(getAltura() + 1);
    }

    public void decHeight() {
        if(getAltura() != 0)
        setAltura(getAltura() - 1);
    }
    
    public void incDiagonal() {
        diagonal += .05;
    }

    public void decDiagonal() {
        if(diagonal != 0)
        diagonal -= .05;
    }
    
    public void movRight(){
        xi++;
        xf++;
    }
    
    public void movRightAndDown(){
        xi++;
        xf++;
        y0++;
    }

    /**
     * @return the xi
     */
    public int getXi() {
        return xi;
    }

    /**
     * @param xi the xi to set
     */
    public void setXi(int xi) {
        this.xi = xi;
    }

    /**
     * @return the xf
     */
    public int getXf() {
        return xf;
    }

    /**
     * @param xf the xf to set
     */
    public void setXf(int xf) {
        this.xf = xf;
    }

    /**
     * @return the y0
     */
    public int getY0() {
        return y0;
    }

    /**
     * @param y0 the y0 to set
     */
    public void setY0(int y0) {
        this.y0 = y0;
    }

    /**
     * @return the frec
     */
    public double getFrec() {
        return frec;
    }

    /**
     * @param frec the frec to set
     */
    public void setFrec(double frec) {
        this.frec = frec;
    }

    /**
     * @return the altura
     */
    public int getAltura() {
        return altura;
    }

    /**
     * @param altura the altura to set
     */
    public void setAltura(int altura) {
        this.altura = altura;
    }

    /**
     * @return the frame
     */
    public int getFrame() {
        return frame;
    }

    /**
     * @param frame the frame to set
     */
    public void setFrame(int frame) {
        this.frame = frame;
    }

    /**
     * @return the visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * @param visible the visible to set
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * @param diagonal the diagonal to set
     */
    public void setDiagonal(float diagonal) {
        this.diagonal = (diagonal);
    }
}
