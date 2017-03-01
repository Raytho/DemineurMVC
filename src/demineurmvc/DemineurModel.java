package demineurmvc;

public class DemineurModel{
    Cell[][] grid;
    private int remainingMines;
    public DemineurModel() {
    }
    public int getRemainingMines() {
        return remainingMines;
    }
    public void setRemainingMines(int remainingMines) {
        this.remainingMines = remainingMines;
    }
    
    public void refactor(int[] values, int x, int y){
        System.out.println("REFACTOR");
        int rows = values[0];
        int cols = values[1];
        int randomCol, randomRow, xAround;
        String temp;
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                grid[i][j].value = '.';
            }
        }
        int bombNumber = (rows*cols)*values[2]/100;
        for(int i=0; i<bombNumber; i++){
            randomRow = (int)(Math.random() * rows);
            randomCol = (int)(Math.random() * cols);
            while(grid[randomRow][randomCol].value == 'x' || (randomRow==x && randomCol==y)){
                randomRow = (int)(Math.random() * rows);
                randomCol = (int)(Math.random() * cols);
            }
            grid[randomRow][randomCol].value = 'x';
        }
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(grid[i][j].value == '.'){
                    xAround = countNeighbors(i,j, rows, cols);
                    if(xAround!=0){
                        temp = Integer.toString(xAround);
                        grid[i][j].value = temp.toCharArray()[0];
                    }
                }
            }
        }
    }
    
    public void createGrid(int[] values){
        int rows = values[0];
        int cols = values[1];
        String temp;
        grid = new Cell[rows][cols];
        int bombNumber = (rows*cols)*values[2]/100;
        int randomRow, randomCol, xAround;
        
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                grid[i][j] = new Cell();
            }
        }
        //On crée les mines dans certaines cases
        for(int i=0; i<bombNumber; i++){
            randomRow = (int)(Math.random() * rows);
            randomCol = (int)(Math.random() * cols);
            while(grid[randomRow][randomCol].value == 'x'){
                randomRow = (int)(Math.random() * rows);
                randomCol = (int)(Math.random() * cols);
            }
            grid[randomRow][randomCol].value = 'x';
        }
        
        //On remplit le reste des cases
        for(int i=0; i<values[0] ; i++){
            for(int j=0; j<values[1]; j++){
                if(values[3] == 0) grid[i][j].printedValue = '#';
                else if(grid[i][j].value == 'x'){
                    grid[i][j].printedValue = 'x';
                    grid[i][j].isRevealed = 1;
                }
                else grid[i][j].printedValue = '#';
                if(grid[i][j].value != 'x') grid[i][j].value = '.';
            }
        }
        
        //On calcule les mines voisines pour les cases vides
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(grid[i][j].value == '.'){
                    xAround = countNeighbors(i,j, rows, cols);
                    if(xAround!=0){
                        temp = Integer.toString(xAround);
                        grid[i][j].value = temp.toCharArray()[0];
                    }
                }
            }
        }
    }
    
    public int countNeighbors(int i, int j, int rows, int cols){
        int n=0;
        if(i!=0 && j!=0){
            if(grid[i-1][j-1].value == 'x') n++;
        }
        if(i!=0){
            if(grid[i-1][j].value == 'x') n++;
        }
        if(i!=0 && j!=cols-1){
            if(grid[i-1][j+1].value == 'x') n++;
        }
        if(j!=0){
            if(grid[i][j-1].value == 'x') n++;
        }
        if(j!=cols-1){
            if(grid[i][j+1].value == 'x') n++;
        }
        if(i!=rows-1 && j!=0){
            if(grid[i+1][j-1].value == 'x') n++;
        }
        if(i!=rows-1){
            if(grid[i+1][j].value == 'x') n++;
        }
        if(i!=rows-1 && j!=cols-1){
            if(grid[i+1][j+1].value == 'x') n++;
        }
        return n;
    }
    
    public void markCell(int i, int j, int c){
        if(grid[i][j].isRevealed == 1){
            System.out.println("Case dévoilée ne peut être marquée.");
        }else{
            switch(c){
                case 0:
                    if(grid[i][j].printedValue == 'x'){
                        System.out.println("Cette case est déjà marquée comme étant une mine");
                    }else{
                        grid[i][j].printedValue = 'x';
                        remainingMines--;
                    }
                    break;
                case 1:
                    if(grid[i][j].printedValue=='x')
                        remainingMines++;
                    if(grid[i][j].printedValue == '?')
                        System.out.println("Cette case est déjà marquée comme étant inconnue");
                    else
                        grid[i][j].printedValue = '?';
                    break;
                case 2:
                    if(grid[i][j].printedValue=='x')
                        remainingMines++;
                    if(grid[i][j].printedValue == '#')
                        System.out.println("Cette case est déjà marquée comme étant vide");
                    else grid[i][j].printedValue = '#';
                    break;
            }  
        }
    }
    
    public int discoverCell(int i, int j){
        if(grid[i][j].isRevealed == 1){
            System.out.println("Case déjà dévoilée.");
            return 0;
        }
        if(grid[i][j].value == 'x') {
            grid[i][j].printedValue = grid[i][j].value;
            return 1;
        }
        else{
            int rows = grid.length;
            int cols = grid[0].length;
            grid[i][j].printedValue = grid[i][j].value;
            grid[i][j].isRevealed = 1;
            if(grid[i][j].value == '.'){
                if(i!=0 && j!=0 && grid[i-1][j-1].isRevealed!=1){
                    discoverCell(i-1,j-1);
                }
                if(i!=0 && grid[i-1][j].isRevealed!=1){
                    discoverCell(i-1,j);
                }
                if(i!=0 && j!=cols-1 && grid[i-1][j+1].isRevealed!=1){
                    discoverCell(i-1,j+1);
                }
                if(j!=0 && grid[i][j-1].isRevealed!=1){
                    discoverCell(i,j-1);
                }
                if(j!=cols-1 && grid[i][j+1].isRevealed!=1){
                    discoverCell(i,j+1);
                }
                if(i!=rows-1 && j!=0 && grid[i+1][j-1].isRevealed!=1){
                    discoverCell(i+1,j-1);
                }
                if(i!=rows-1 && grid[i+1][j].isRevealed!=1){
                    discoverCell(i+1,j);
                }
                if(i!=rows-1 && j!=cols-1 && grid[i+1][j+1].isRevealed!=1){
                    discoverCell(i+1,j+1);
                }
            }
        }
        return 0;
    }
    
    public int checkIfWin(int[] values){
        int rows = values[0];
        int cols = values[1];
        int bomb = (rows*cols)*values[2]/100;
        int sum = 0;
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                sum += grid[i][j].isRevealed;
                //on compte le nombre de 1, donc de cases découvertes
            }
        }
        //on inverse ce nombre par rapport au nombre total de cases
        sum = (rows*cols)-sum;
        if(sum == bomb) return 2;
        return 0;
    }
}
