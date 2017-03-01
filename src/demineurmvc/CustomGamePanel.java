package demineurmvc;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class CustomGamePanel extends JPanel{
    JSlider rows, cols, mines;
    JPanel labels, sliders, textfields;
    JTextField jtfRows, jtfCols, jtfMines;
    
    public CustomGamePanel(){     
        this.labels = new JPanel();      
        this.labels.setLayout(new BoxLayout(this.labels, BoxLayout.PAGE_AXIS));
        this.sliders = new JPanel();      
        this.sliders.setLayout(new BoxLayout(this.sliders, BoxLayout.PAGE_AXIS));
        this.textfields = new JPanel();      
        this.textfields.setLayout(new BoxLayout(this.textfields, BoxLayout.PAGE_AXIS));
        rows = new JSlider(JSlider.HORIZONTAL,9, 24, 9);
        cols = new JSlider(JSlider.HORIZONTAL,9, 30, 9);
        mines = new JSlider(JSlider.HORIZONTAL,9, 139, 9); 
        
        
        //Listener qui change le texte des JTextFields quand on change la valeur du slider
        ChangeListener listener = (ChangeEvent ce) -> {
            JSlider slider = (JSlider) ce.getSource();
            if(slider==rows || slider==cols){
                if(slider==rows) jtfRows.setText(Integer.toString(slider.getValue()));
                if(slider==cols) jtfCols.setText(Integer.toString(slider.getValue()));
                mines.setMaximum(rows.getValue() * cols.getValue() * 85 / 100);
                if(rows.getValue()*cols.getValue()*85/100 > 200){
                    mines.setMinorTickSpacing(10);
                    mines.setMajorTickSpacing(40);
                    mines.setLabelTable(mines.createStandardLabels(82));
                }else{
                    mines.setMinorTickSpacing(5);
                    mines.setMajorTickSpacing(20);
                    mines.setLabelTable(mines.createStandardLabels(26));
                }
            }else if(slider== mines){
                jtfMines.setText(Integer.toString(slider.getValue()));
            }
        };
        
        
        //keyListener qui contrôle ce qu'on tape dans les JTextField
        //Une valeur incorrecte entraîne l'annulation de ce qu'on vient de taper
        //Le slider change aussi de position selon ce qu'on tape
        KeyListener keyListener = new KeyListener(){
            @Override
            public void keyTyped(KeyEvent ke) {
            }
            @Override
            public void keyPressed(KeyEvent ke) {
            }
            @Override
            public void keyReleased(KeyEvent ke) {
                JTextField jtf = (JTextField)ke.getSource();
                if(!jtf.getText().isEmpty()){
                    try{
                        int value = Integer.valueOf(jtf.getText());
                        if(jtf == jtfRows){
                            if(value < 9){   //do nothing 
                            }else if(value < 25){
                                rows.setValue(value);
                            }else{
                                jtf.setText(Integer.toString(rows.getValue()));
                            }
                        }else if(jtf == jtfCols){
                            if(value<9){
                            }else if(value<31){
                                cols.setValue(value);
                            }else{
                                jtf.setText(Integer.toString(cols.getValue()));
                            }
                        }else if(jtf == jtfMines){
                            if(value<9){
                            }else if(value < (Integer.valueOf(jtfRows.getText())*Integer.valueOf(jtfCols.getText())*85)/100){
                                mines.setValue(value);
                            }else{
                                jtf.setText(Integer.toString(mines.getValue()));
                            }
                        }
                    }catch(Exception e){
                        if(jtf == jtfRows) jtf.setText(Integer.toString(rows.getValue()));
                        if(jtf == jtfCols) jtf.setText(Integer.toString(cols.getValue()));
                        if(jtf == jtfMines) jtf.setText(Integer.toString(mines.getValue()));
                        
                    } 
                }
            }         
        };
        
        rows.setMinorTickSpacing(1);
        rows.setMajorTickSpacing(10);
        rows.setPaintTicks(true);
        rows.setPaintLabels(true);
        rows.setLabelTable(rows.createStandardLabels(2));
        rows.addChangeListener(listener);
        cols.setMinorTickSpacing(1);
        cols.setMajorTickSpacing(10);
        cols.setPaintTicks(true);
        cols.setPaintLabels(true);
        cols.setLabelTable(cols.createStandardLabels(4));
        cols.addChangeListener(listener);
        mines.setMinorTickSpacing(5);
        mines.setMajorTickSpacing(20);
        mines.setPaintTicks(true);
        mines.setPaintLabels(true);
        mines.setLabelTable(mines.createStandardLabels(26));
        mines.addChangeListener(listener);
        this.sliders.add(new JLabel(" "));
        this.sliders.add(rows);
        this.sliders.add(cols);
        this.sliders.add(mines);
        this.labels.add(new JLabel("Rows"));
        this.labels.add(new JLabel(" ")); this.labels.add(new JLabel(" "));
        this.labels.add(new JLabel("Columns"));
        this.labels.add(new JLabel(" ")); this.labels.add(new JLabel(" "));
        this.labels.add(new JLabel("Mines"));
        jtfRows =new JTextField("9", 4);
        jtfRows.addKeyListener(keyListener);
        this.textfields.add(jtfRows);
        this.textfields.add(new JLabel(" ")); this.textfields.add(new JLabel(" "));
        jtfCols = new JTextField("19", 4);
        jtfCols.addKeyListener(keyListener);
        this.textfields.add(jtfCols);
        this.textfields.add(new JLabel(" ")); this.textfields.add(new JLabel(" "));
        jtfMines = new JTextField("76", 4);
        jtfMines.addKeyListener(keyListener);
        this.textfields.add(jtfMines);
        this.add(this.labels, BorderLayout.EAST);
        this.add(this.sliders, BorderLayout.NORTH);
        this.add(this.textfields, BorderLayout.WEST);  
        rows.setValue(9);
        cols.setValue(19);
        mines.setValue(76);
    }
    
}

