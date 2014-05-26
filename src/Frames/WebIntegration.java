package Frames;

import Engine.ScreenManager;
import MainSources.Main;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class WebIntegration {
    
    public static final int ID = MainFrame.WEBINTEGRATION;
    private JFrame f;
    private JEditorPane jep;
    private JScrollPane scrollPane;
    private JButton bt;
    boolean visible;
    
    public WebIntegration(ScreenManager s) {
        visible = false;
        f = s.getFrame();
        bt = new JButton(" ");
        bt.setVisible(visible);
        f.add(bt, BorderLayout.CENTER);
        init();
    }
    
    private void init() {
        jep = new JEditorPane();
        jep.setEditable(false);
        
        scrollPane = new JScrollPane(jep);
        setPage(-1);
        jep.setVisible(visible);
        f.add(scrollPane);
    }
    
    public void setPage(int frame) {
            String url = "http://www.tntcraft.net/isep/";
        try {
            if (frame == MainFrame.FOTOELECTRICO) {
                url += "efeitofoto";
            } else if (frame == MainFrame.COMPTON) {
                url += "efeitocompton";
            } else if (frame == MainFrame.RAYLEIGH) {
                url += "dispersaorayleigh";
            } else if (frame == MainFrame.RAIOX) {
                url += "producaoraiosx";
            }
            
            url += ".html";
            
            jep.setPage(url);
            
        } catch (IOException ex) {
            jep.setContentType("text/html");
            jep.setText("<html>Could not load WEBPAGE </html>\n");
            jep.setText("<html>"+ex.getMessage()+"</html>\n");
        }
    }
    
    public void draw() {
        if (visible == false) {
            visible = true;
            bt.setVisible(visible);
            jep.setVisible(visible);
        }
    }
    
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        if (keycode == KeyEvent.VK_ESCAPE) {
            Main.getMain().setStat(false);
            MainFrame.actualVew = -1;
        }else if(keycode == KeyEvent.VK_DOWN){
            
        }
    }
}//enc class

