package demineurmvc;


public class Cell {
    char value;   //La vraie valeur de la case : rien(.), un nombre de mines adjacentes(n) ou une mine(x)
    char printedValue;  //La valeur affichée
    int isRevealed;  //0 ou 1 selon que la case est dévoilée ou pas

    public Cell() {
        value = '.';
        printedValue = '#';
        isRevealed = 0;
    }

}

