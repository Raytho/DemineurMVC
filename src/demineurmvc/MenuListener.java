package demineurmvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

public class MenuListener implements ActionListener{
    DemineurControler c;
    public MenuListener(DemineurControler c) {
        this.c = c;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        JMenuItem source = (JMenuItem)ae.getSource();
        switch (source.getText()) {
            case "Beginner":
                c.newGame(9, 9, 13);
                break;
            case "Intermediate":
                c.newGame(16, 16, 16);
                break;
            case "Expert":
                c.newGame(16, 30, 21);
                break;
            case "Quit":
                c.ggv.frame.dispose();
                System.exit(0);
                break;
            case "Custom":
                c.getValues();
                break;
            case "Scores":
                JFrame frame = new JFrame("Best scores");
                Serialization s = new Serialization();
                try{
                    s.loadScore();
                }catch(IOException | ClassNotFoundException e){
                }
                s.updateScore();
                ScoresPanel sp = new ScoresPanel();
                sp.setScores(s.scoresBeginner, s.scoresIntermediate, s.scoresExpert);
                frame.add(sp);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                break;
        }
    }
}
