package Objects;

import java.awt.*;

public class Electron {

    private Ponto posicao;
    private Ponto centro;
    private int raio;
    private Ponto[] pos;
    private int estado;
    private int movimento = 0;
    private int contador = 0;
    private boolean visible;
    private float velocidade = 1;

    public Electron(int centroX, int centroY, int raio) {
        centro = new Ponto(centroX, centroY);
        this.visible = true;
        this.raio = raio;
        genPos();
        estado = (int) (1 + (Math.random() * (pos.length - 1)));
        posicao = pos[estado];
    }
    
    public void moveDireita(){
        centro.x = (int)(centro.x + velocidade);
    }

    public void incPos() {
        if (estado >= pos.length) {
            estado = 0;
        }

        posicao = pos[estado];
        estado++;
    }

    private void genPos() {
        pos = new Ponto[180];

        // Calculos de 5graus em 5graus 
        for (int i = 0; i < pos.length; i++) {
            int x, y;

            double t = 2 * Math.PI * i / pos.length;
            x = (int) Math.round(raio * Math.cos(t));
            y = (int) Math.round(raio * Math.sin(t));

            pos[i] = new Ponto(centro.x + x, centro.y + y);
        }
    }

    public void sair() {
        movimento = 1;
    }

    public void dentro() {
        movimento = -1;
    }

    public void draw(Graphics2D g, int raio) {
        if(visible){
        g.setColor(Color.GRAY);
        g.fillOval(posicao.x, posicao.y, 15, 15);

        if (movimento == 0) {
            incPos();
        } else if (movimento == 1) {
            posicao.x++;
            posicao.y--;
            contador++;
            if(contador == 110){
                visible = false;
            }
        } else {
            if (this.raio != raio && this.raio > 0) {
                this.raio--;
            }
            genPos();
            incPos();
        }
        }
    }

    public void draw(Graphics2D g) {
        draw(g, raio);
    }

    public Ponto[] getAray() {
        return pos;
    }

    /**
     * @return the posicao
     */
    public Ponto getPosicao() {
        return posicao;
    }

    /**
     * @return the centro
     */
    public Ponto getCentro() {
        return centro;
    }

    /**
     * @param centro the centro to set
     */
    public void setCentro(int x, int y) {
        centro.x = x;
        centro.y = y;
        genPos();
    }

    /**
     * @return the raio
     */
    public int getRaio() {
        return raio;
    }

    /**
     * @param raio the raio to set
     */
    public void setRaio(int raio) {
        this.raio = raio;
        genPos();
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
     * @return the velocidade
     */
    public float getVelocidade() {
        return velocidade;
    }

    /**
     * @param velocidade the velocidade to set
     */
    public void setVelocidade(float velocidade) {
        this.velocidade = velocidade;
    }
}//end class
