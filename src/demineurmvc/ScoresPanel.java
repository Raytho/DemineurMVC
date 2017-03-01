package demineurmvc;

import java.awt.GridLayout;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoresPanel extends JPanel{
    List<Score> scoresB, scoresI, scoresE;

    public ScoresPanel() {
        this.setLayout(new GridLayout(6, 3, 10, 10));
    }

    public void setScores(List<Score> scoresB, List<Score> scoresI, List<Score> scoresE) {
        this.scoresB = scoresB;
        this.scoresI = scoresI;
        this.scoresE = scoresE;
        this.add(new JLabel("Beginner (9x9) : "));
        this.add(new JLabel("Intermediate (16x16) : "));
        this.add(new JLabel("Expert (16x30) : "));        
        for(int i=0; i<5; i++){
            if(scoresB.size() > i) this.add(new JLabel(scoresB.get(i).timer + "s"));
            else this.add(new JLabel("/"));
            if(scoresI.size() > i) this.add(new JLabel(scoresI.get(i).timer + "s"));
            else this.add(new JLabel("/"));
            if(scoresE.size() > i) this.add(new JLabel(scoresE.get(i).timer + "s"));
            else this.add(new JLabel("/"));
        }
    }
    

}
