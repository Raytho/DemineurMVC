package demineurmvc;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class Menu extends JMenuBar{
    JMenu menu, subMenu;
    JMenuItem beginnerItem, intermediateItem, expertItem, customItem, quitItem, scoresItem;
    public Menu(){
        menu = new JMenu("Game");
        subMenu = new JMenu("New");
        beginnerItem = new JMenuItem("Beginner");
        intermediateItem = new JMenuItem("Intermediate");
        expertItem = new JMenuItem("Expert");
        customItem = new JMenuItem("Custom");
        quitItem = new JMenuItem("Quit");
        scoresItem = new JMenuItem("Scores");
        beginnerItem.setMnemonic('b');
        intermediateItem.setMnemonic('i');
        expertItem.setMnemonic('e');
        customItem.setMnemonic('c');
        quitItem.setMnemonic('q');
        scoresItem.setMnemonic('s');
        beginnerItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.Event.ALT_MASK));
        intermediateItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.Event.ALT_MASK));
        expertItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.Event.ALT_MASK));
        customItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.Event.ALT_MASK));
        quitItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.Event.ALT_MASK));
        scoresItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.Event.ALT_MASK));
        subMenu.add(beginnerItem);
        subMenu.add(intermediateItem);
        subMenu.add(expertItem);
        subMenu.add(customItem);
        menu.add(subMenu); 
        menu.add(quitItem);
        menu.add(scoresItem);
        this.add(menu);
    }
}

