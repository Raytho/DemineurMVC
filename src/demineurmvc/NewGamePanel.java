package demineurmvc;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class NewGamePanel extends JPanel{
    JRadioButton beginnerButton, intermediateButton,
                 expertButton, customButton;
    JPanel choice;
    CustomGamePanel customGamePanel;
    ButtonGroup choiceButtonGroup;
    JButton bouton;
    public NewGamePanel(JFrame frame, DemineurControler c){
        setLayout(new FlowLayout(FlowLayout.LEFT));
        choice = new JPanel();
        choice.setLayout(new BoxLayout(this.choice, BoxLayout.PAGE_AXIS));
        beginnerButton = new JRadioButton("Beginner : 10 mines in a 9x9 field");
        intermediateButton = new JRadioButton("Intermediate : 40 mines in a 16x16 field");
        expertButton = new JRadioButton("Expert : 99 mines in a 16x30 field");
        customButton = new JRadioButton("Custom :");
        choice.add(new JLabel("Select a level :")); 
        choiceButtonGroup = new ButtonGroup();
        choiceButtonGroup.add(beginnerButton);
        choiceButtonGroup.add(intermediateButton);
        choiceButtonGroup.add(expertButton);
        choiceButtonGroup.add(customButton);
        this.choice.add(beginnerButton);
        this.choice.add(intermediateButton);
        this.choice.add(expertButton);
        this.choice.add(customButton);
        this.add(this.choice);
        customGamePanel = new CustomGamePanel();
        this.add(customGamePanel);
        bouton = new JButton("OK");
        this.add(bouton);
        
        //Lance la partie avec les valeurs des JTextFields
        //si elles sont correctes
        ActionListener actionListener = (ActionEvent ae) -> {
            try{
                int rowsValue = Integer.valueOf(customGamePanel.jtfRows.getText());
                int colsValue = Integer.valueOf(customGamePanel.jtfCols.getText());
                int minesValue = Integer.valueOf(customGamePanel.jtfMines.getText());
                if(rowsValue < 9 || rowsValue > 24 || colsValue<9 || colsValue>30 || minesValue<9 ||
                   minesValue > 85*(Integer.valueOf(customGamePanel.jtfRows.getText())*
                   Integer.valueOf(customGamePanel.jtfCols.getText()))/100){
                        JOptionPane.showMessageDialog(new JFrame(), "Incorrect Value");
                }else{
                    frame.dispose();
                    c.newGame(customGamePanel.rows.getValue(),
                          customGamePanel.cols.getValue(),
                          customGamePanel.mines.getValue()*100/
                          (customGamePanel.rows.getValue()*customGamePanel.cols.getValue()));
                    
                }
            }catch(NumberFormatException nfe){
                JOptionPane.showMessageDialog(new JFrame(), "Incorrect Value");
            }
        };
        
        //coche le bouton Custom si on modifie les sliders
        ChangeListener listener2 = (ChangeEvent ce) -> {
            beginnerButton.setSelected(false);
            intermediateButton.setSelected(false);
            expertButton.setSelected(false);
            customButton.setSelected(true);
        };
        customGamePanel.cols.addChangeListener(listener2);
        customGamePanel.rows.addChangeListener(listener2);
        customGamePanel.mines.addChangeListener(listener2);
        customGamePanel.jtfCols.addActionListener(actionListener);
        customGamePanel.jtfRows.addActionListener(actionListener);
        customGamePanel.jtfMines.addActionListener(actionListener);
        
        //Lance la partie selon le bouton sur lequel on est
        bouton.addActionListener((ActionEvent ae) -> {
            customGamePanel.cols.getValue();
            if(beginnerButton.isSelected()){
                frame.dispose();
                c.newGame(9,9,13);
            }else if(intermediateButton.isSelected()){
                frame.dispose();
                c.newGame(16,16,16);
            }else if(expertButton.isSelected()){
                frame.dispose();
                c.newGame(16,30,21);
            }else if(customButton.isSelected()){
                frame.dispose();
                c.newGame(customGamePanel.rows.getValue(),
                          customGamePanel.cols.getValue(),
                          customGamePanel.mines.getValue()*100/
                          (customGamePanel.rows.getValue()*customGamePanel.cols.getValue()));
            }else{
                JOptionPane.showMessageDialog(new JFrame(), "You must choose a level");
            }         
        });  
        this.setPreferredSize(new Dimension(350,330));
    }
}

