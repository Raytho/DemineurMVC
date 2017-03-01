package demineurmvc;

import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Chronometre extends JPanel{
    static long chrono = 0, chrono2 = 0; 
    JLabel affichage;
    public Chronometre(){
        Timer SimpleTimer = new Timer(1000, (ActionEvent e) -> {
            chrono2 = java.lang.System.currentTimeMillis() ;
            affichage.setText("|     Time spent trying to win : " + (int)((chrono2-chrono)/1000) + "s");
        });
        
        SimpleTimer.start();
    }
    
    public void afficher(){
        Chronometre.startChrono();
        affichage = new JLabel("|     Time spent trying to win : 0s");
        add(affichage);
    }
    
    static void startChrono() { 
        chrono = java.lang.System.currentTimeMillis() ; 
    } 

    static long stopChrono() { 
        chrono2 = java.lang.System.currentTimeMillis() ; 
        long temps = chrono2 - chrono ; 
        System.out.println("Time elapsed = " + temps + " ms") ; 
        return temps;
    } 
}
