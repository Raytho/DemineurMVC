package demineurmvc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Serialization {
    List<Score> scores = new ArrayList<>();
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;
    List<Score> scoresBeginner, scoresIntermediate, scoresExpert;
    
    //On récupère les scores déjà serialisés
    public void loadScore() throws IOException, ClassNotFoundException {
        try {
            inputStream = new ObjectInputStream(new FileInputStream("scores.txt"));
            this.scores = (ArrayList<Score>) inputStream.readObject();
        } catch (FileNotFoundException fnfe) {
            
        } catch (IOException  ex) {
           
        } finally {
            if (outputStream != null) {
                outputStream.flush();
                outputStream.close();
            }
        }
    }
    
    
    //On trie, et on ne garde que les 5 meilleurs scores de chaque difficulté
    public void updateScore() {
        Collections.sort(scores);
        scoresBeginner = new ArrayList<>();
        scoresIntermediate = new ArrayList<>();
        scoresExpert = new ArrayList<>();
        for(int i=0; i<scores.size(); i++){
            if(scoresBeginner.size() < 5 && scores.get(i).c==9 && scores.get(i).r == 9)
                scoresBeginner.add(scores.get(i));
            else if((scoresIntermediate.size() < 5) && scores.get(i).c==16 && scores.get(i).r == 16)
                scoresIntermediate.add(scores.get(i));
            else if((scoresExpert.size() < 5) && scores.get(i).c == 30 && scores.get(i).r == 16)
                scoresExpert.add(scores.get(i));
            else scores.remove(scores.get(i));
        }
        try {
            this.outputStream = new ObjectOutputStream(new FileOutputStream("scores.txt"));
            this.outputStream.writeObject(this.scores);
        } catch (FileNotFoundException fnfe) {
            System.out.println("Update: File not found exception: " + fnfe.getMessage());
        } catch (IOException ioe) {
            System.out.println("Update: Input/Output exception: " + ioe.getMessage());
        } finally {
            try {
                if (this.outputStream != null) {
                    this.outputStream.flush();
                    this.outputStream.close();
                }
            } catch (IOException ioe) {
                System.out.println("Update: Input/Output exception: " + ioe.getMessage());
            }
        }
    }
    
    public void printScores(){
        Collections.sort(scores);
        scoresBeginner = new ArrayList<>();
        scoresIntermediate = new ArrayList<>();
        scoresExpert = new ArrayList<>();
        for(int i=0; i<scores.size(); i++){
            if((scoresBeginner.size() < 5) && scores.get(i).c==9)
                scoresBeginner.add(scores.get(i));
            else if((scoresIntermediate.size() < 5) && scores.get(i).c==16)
                scoresIntermediate.add(scores.get(i));
            else if((scoresExpert.size() < 5) && scores.get(i).c == 30)
                scoresExpert.add(scores.get(i));
            scores.remove(scores.get(i));
            i--;
        }
        scoresBeginner.stream().forEach((scoresBeginner1) -> {
            System.out.println(scoresBeginner1.r + "x" + scoresBeginner1.c + ":" + scoresBeginner1.timer);
        });
        System.out.println("");
        scoresIntermediate.stream().forEach((scoresIntermediate1) -> {
            System.out.println(scoresIntermediate1.r + "x" + scoresIntermediate1.c + ":" + scoresIntermediate1.timer);
        });
        System.out.println("");
        scoresExpert.stream().forEach((scoresExpert1) -> {
            System.out.println(scoresExpert1.r + "x" + scoresExpert1.c + ":" + scoresExpert1.timer);
        });
        
    }
}
