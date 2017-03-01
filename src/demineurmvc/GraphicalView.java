package demineurmvc;


public class GraphicalView{
    public void print(Cell[][] grid){
        int rows = grid.length;
        int cols = grid[0].length;
        for(int i=0 ; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(grid[i][j].isRevealed == 0)
                    System.out.print(grid[i][j].printedValue + " ");
                else 
                    System.out.print(grid[i][j].value + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
