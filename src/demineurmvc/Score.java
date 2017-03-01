package demineurmvc;

import java.io.Serializable;

//Les scores sont de la forme rows x cols : temps
public class Score implements Serializable, Comparable<Score> {
    int r,c,timer;
    public Score(int r, int c, int timer){
        this.r = r;
        this.c = c;
        this.timer = timer;
    }
    
    @Override
    public String toString(){
        return r + "x" + c + ":" + timer;
    }

    @Override
    public int compareTo(Score s) {
        if(this.timer < s.timer)
            return -1;
        else if(this.timer == s.timer)
            return 0;
        else return 1;
    }
}