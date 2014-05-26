package MainSources;

import Frames.MainFrame;
        
public class Main {
    public static MainFrame main;
    public static void main(String[] args) {
        main = new MainFrame();
        main.run();
    }
    
    public static MainFrame getMain(){
    return main;
    }
}
