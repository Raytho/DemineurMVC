package demineurmvc;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GraphicalGridView extends JPanel{
    GraphicalCellView[][] c;
    int counter;
    JLabel text;
    JPanel bottomInfos;
    Chronometre chrono;
    JFrame frame;
    Menu menu;
    public GraphicalGridView(Cell[][] grid) {
        setLayout(new GridLayout(grid.length, grid[0].length, 0, 0));
        c = new GraphicalCellView[grid.length][grid[0].length];
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                c[i][j] = new GraphicalCellView(grid[i][j].printedValue, i, j);
                this.add(c[i][j]);
            }
        }
        text = new JLabel();
        text.setText(""); 
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setVerticalAlignment(JLabel.CENTER);
        chrono = new Chronometre();
        bottomInfos = new JPanel(new FlowLayout());
        frame = new JFrame("Minesweeper");
    }
    
    
    public void afficher(int remainingMines, int rows, int cols){
        frame.add(this,  BorderLayout.CENTER);
        bottomInfos.add(text);
        bottomInfos.add(chrono);
        chrono.afficher();
        frame.add(bottomInfos, BorderLayout.SOUTH);
        menu = new Menu();
        frame.setJMenuBar(menu);
        this.text.setText("Remaining mines : " + remainingMines);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        if(rows<15 && cols<15)  frame.setSize(50*rows, 50*cols);
        else if(rows<25 && cols<25) frame.setSize(1000,600);
        else frame.setSize(1250,680);
    }    
    
    public void update(Cell[][] grid, int remainingMines){ 
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                c[i][j].update(grid[i][j].printedValue);
                if(grid[i][j].printedValue == 'x') counter++;
            }
            this.text.setText("Remaining mines : " + remainingMines);
        }
       // System.out.println("Il reste " + remainingMines + " mines !"); //Affichage texte en 1 ligne
    }
    
    public void addListeners(DemineurControler dc){
        for (GraphicalCellView[] c1 : c) {
            for (int j = 0; j<c[0].length; j++) {
                c1[j].addMouseListener(dc);
            }
        }
    }
    
    public void addMenuListeners(MenuListener ml){
        menu.beginnerItem.addActionListener(ml);
        menu.intermediateItem.addActionListener(ml);
        menu.expertItem.addActionListener(ml);
        menu.customItem.addActionListener(ml);
        menu.quitItem.addActionListener(ml);
        menu.scoresItem.addActionListener(ml);
    }
}
