package demineurmvc;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DemineurControler implements MouseListener{
    DemineurModel m;
    GraphicalView gv;
    GraphicalGridView ggv;
    int[] globalValues;
    int gameEnded = 0;
    int firstMove;
    MenuListener ml;
    public DemineurControler() {
        this.m = new DemineurModel();
        gv = new GraphicalView();
        ml = new MenuListener(this);
    }
    
    public void main(){
        getValues();
    }
    
    public void newGame(int rows, int cols, int minesRatio){
        globalValues = new int[4];
        globalValues[0] = rows;
        globalValues[1] = cols;
        globalValues[2] = minesRatio;
        globalValues[3] = 0; //fixé à 0 car mode débug devenu obsolète      
        if(ggv != null) ggv.frame.dispose();
        m.createGrid(globalValues);
        m.setRemainingMines(globalValues[0]*globalValues[1]*globalValues[2]/100);
        gv.print(m.grid);
        ggv = new GraphicalGridView(m.grid);
        ggv.afficher(m.getRemainingMines(), globalValues[0], globalValues[1]);
        ggv.addListeners(this);
        ggv.addMenuListeners(ml);
        int[] action;
        firstMove = 0;
       /* while(gameEnded == 0){
           action = getAction(m.grid.length, m.grid[0].length);
            if(action[0] == 2){
                gameEnded = 3;
                System.exit(0);
            } //si l'utilisateur a mit 'q'
            else if(action[0] == 1) {
                    if(m.getRemainingMines()==0 && action[3]==0)
                        System.out.println("Il n'est plus possible de marquer des mines.");
                    else 
                        m.markCell(action[1], action[2], action[3]);
                    
                    gv.print(m.grid);
                    ggv.update(m.grid, m.getRemainingMines());
            }else if(action[0] == 0) {
                if(firstMove == 0){
                    firstMove = 1;
                    if(m.grid[action[1]][action[2]].value == 'x'){
                        int x = m.countNeighbors(action[1], action[2], globalValues[0], globalValues[1]);
                        if(x==0) m.grid[action[1]][action[2]].value = '.';
                        else m.grid[action[1]][action[2]].value = Integer.toString(x).toCharArray()[0];
                        int randomRow = (int)(Math.random() * globalValues[0]);
                        int randomCol = (int)(Math.random() * globalValues[1]);
                        while(m.grid[randomRow][randomCol].value == 'x' || (randomRow==action[1] && randomCol==action[2])){
                            randomRow = (int)(Math.random() * globalValues[0]);
                            randomCol = (int)(Math.random() * globalValues[1]);
                        }
                        m.grid[randomRow][randomCol].value = 'x';
                    }                    
                }                
                gameEnded = m.discoverCell(action[1],action[2]);
                gv.print(m.grid);
                ggv.update(m.grid, m.getRemainingMines());
                if(gameEnded == 0)
                    gameEnded = m.checkIfWin(globalValues);
            }
            else if(action[0] == 3){
                System.out.println("Faute de frappe/Mauvaise norme");
            }
                   
        }*/
    }
    
    //récupère les valeurs de début de partie
    public void getValues(){
        JFrame panel = new JFrame();
        NewGamePanel gamePanel = new NewGamePanel(panel, this);
        panel.add(gamePanel);
        panel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);       
        panel.pack();
        panel.setLocationRelativeTo(null);
        panel.setVisible(true);  
    }
    
    //quand le contrôle texte était activé, cette fonction permettait de 
    //récupérer une action de l'utilisateur et de la traiter
    public int[] getAction(int rows,int cols){
        int[] tab = new int[4];          
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String s[] = str.split(" ");
        if(s[0].toCharArray()[0] == 'q'){
            tab[0] = 2;
            tab[1] = tab[2] = tab[3] = 0;
        }else if(s[0].toCharArray()[0] == 'd'){
            tab[0] = 0;
            try{
                tab[1] = Integer.parseInt(s[1]);
                tab[2] = Integer.parseInt(s[2]);
            }catch(Exception e){
                tab[0] = 3;
            }
            tab[3] = 0;
        }else if(s[0].toCharArray()[0] == 'm'){
            tab[0] = 1;
            try{
                tab[1] = Integer.parseInt(s[1]);
                tab[2] = Integer.parseInt(s[2]);
            }catch(NumberFormatException fne){
                tab[0] = 3;
            }
            if(s.length < 4) tab[0] = 3;
            else if(s[3].toCharArray()[0] == 'x') tab[3] = 0;
            else if(s[3].toCharArray()[0] == '?') tab[3] = 1;
            else if(s[3].toCharArray()[0] == '#') tab[3] = 2;
            else{
                tab[0] = 3;
            }
        }else{
            tab[0] = 3;
        }
        if((tab[1] > rows-1) || tab[2] > cols-1) tab[0] = 3;
        return tab;
    }

    //Le controleur est directement un listener de chaque bouton
    @Override
    public void mousePressed(MouseEvent me) {
        final GraphicalCellView sourceCell = (GraphicalCellView)me.getComponent();
        int i = sourceCell.i, j = sourceCell.j;
        if(me.getButton() == MouseEvent.BUTTON1){
            if(firstMove == 0){
                firstMove = 1;
                if(m.grid[i][j].value == 'x'){
                    m.refactor(globalValues, i, j);
                }                    
            }                
            gameEnded = m.discoverCell(i,j);
            gv.print(m.grid);
            ggv.update(m.grid, m.getRemainingMines());
            if(gameEnded == 0)
                gameEnded = m.checkIfWin(globalValues);
            if(gameEnded == 2) {
                long temps = Chronometre.stopChrono();
                JOptionPane.showMessageDialog(new JFrame(), "Won in " + (int)temps/1000 + " seconds !");
                if((globalValues[0]==9 && globalValues[1]==9 && globalValues[2]==13) ||
                (globalValues[0]==16 && globalValues[1]==16 && globalValues[2]==16) ||
                (globalValues[0]==16 && globalValues[1]==30 && globalValues[2]==21)){
                    serialize(globalValues[0], globalValues[1], (int)temps/1000);
                }
                ggv.frame.dispose();
                System.exit(0);
            }
            if(gameEnded == 1) {
                JOptionPane.showMessageDialog(new JFrame(), "Game Over ! ");
                long temps = Chronometre.stopChrono();
                ggv.frame.dispose();
                System.exit(0);
            }
        }else if(me.getButton() == MouseEvent.BUTTON3){
            if(m.getRemainingMines()==0 && m.grid[i][j].printedValue == '#')
                System.out.println("You can't mark mines anymore.");
            else{ 
                if(m.grid[i][j].printedValue == '#'){
                    m.markCell(i, j, 0);
                }else if(m.grid[i][j].printedValue == 'x'){
                    m.markCell(i, j, 1);
                }else if(m.grid[i][j].printedValue == '?'){
                    m.markCell(i, j, 2);
                }
            }
            gv.print(m.grid);
            ggv.update(m.grid, m.getRemainingMines());
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }
    @Override
    public void mouseEntered(MouseEvent me) {
    }
    @Override
    public void mouseExited(MouseEvent me) {
    }
    @Override
    public void mouseClicked(MouseEvent me) {  
    }
    
    public void serialize(int rows, int cols, int time){
        Serialization s = new Serialization();
        Score sc = new Score(rows, cols, time);
        try{
            s.loadScore();
        }catch(IOException | ClassNotFoundException e){
            
        }
        s.scores.add(sc);
        s.updateScore();
    }
}
