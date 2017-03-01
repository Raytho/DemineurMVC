package demineurmvc;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class GraphicalCellView extends JButton{
    private static final String assetsPath = System.getProperty("user.dir");
    private static final ImageIcon flagIcon = new ImageIcon(assetsPath + "/flag.png");
    private static final ImageIcon pointIcon = new ImageIcon(assetsPath + "/point.png");
    private static final ImageIcon unIcon = new ImageIcon(assetsPath + "/un.png");
    private static final ImageIcon deuxIcon = new ImageIcon(assetsPath + "/deux.png");
    private static final ImageIcon troisIcon = new ImageIcon(assetsPath + "/trois.png");
    private static final ImageIcon quatreIcon = new ImageIcon(assetsPath + "/quatre.png");
    private static final ImageIcon cinqIcon = new ImageIcon(assetsPath + "/cinq.png");
    private static final ImageIcon sixIcon = new ImageIcon(assetsPath + "/six.png");
    private static final ImageIcon septIcon = new ImageIcon(assetsPath + "/sept.png");
    private static final ImageIcon huitIcon = new ImageIcon(assetsPath + "/huit.png");
    int i,j;
    char c;
    public GraphicalCellView(char c, int i, int j){
        super();
        if(c=='#') this.setText(" ");
        else this.setText(Character.toString(c));
        this.c = c;
        this.i = i;
        this.j = j;
    }
    
    public void update(char c){
        this.setIcon(null);
        this.setDisabledIcon(null);
        this.setText(Character.toString(c));
        if(c=='.')
            this.setEnabled(false);
        else if(c=='1'){
            this.setEnabled(false);
            this.setIcon(unIcon);
            this.setDisabledIcon(unIcon);
            this.setText("");
        }else if(c=='2'){
            this.setEnabled(false);
            this.setIcon(deuxIcon);
            this.setDisabledIcon(deuxIcon);
            this.setText("");
        }
        else if(c=='3'){
            this.setEnabled(false);
            this.setIcon(troisIcon);
            this.setDisabledIcon(troisIcon);
            this.setText("");
        }else if(c=='4'){
            this.setEnabled(false);
            this.setIcon(quatreIcon);
            this.setDisabledIcon(quatreIcon);
            this.setText("");
        }
        else if(c=='5'){
            this.setEnabled(false);
            this.setIcon(cinqIcon);
            this.setDisabledIcon(cinqIcon);
            this.setText("");
        }else if(c=='6'){
            this.setEnabled(false);
            this.setIcon(sixIcon);
            this.setDisabledIcon(sixIcon);
            this.setText("");
        }else if(c=='7'){
            this.setEnabled(false);
            this.setIcon(septIcon);
            this.setDisabledIcon(septIcon);
            this.setText("");
        }else if(c=='8'){
            this.setEnabled(false);
            this.setIcon(huitIcon);
            this.setDisabledIcon(huitIcon);
            this.setText("");
        }
        else if(c=='#')
            this.setText(" ");
        else if(c=='x'){
            this.setIcon(flagIcon);
            this.setText("");
        }else if(c=='?'){
            this.setIcon(pointIcon);
            this.setText("");            
        }
    }
}
