package graphics.shapes.ui;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MenuBar extends JFrame {

    public MenuBar(){
        super("Paint de bg");
        this.setSize(600,600);
        this.setLocationRelativeTo(null); //Centre par rapport au conteneur en parametre
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //Detruire l'objet

        /* Barre de menu*/
        this.setJMenuBar(createMenuBar());
    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar=new JMenuBar();
        JMenu menuFile= new JMenu ("Fichier");
        menuBar.add(menuFile);
        menuFile.setMnemonic('F');

        JMenuItem itemNew= new JMenuItem("Nouveau Fichier");
        itemNew.addActionListener(this::menuListener);
        menuFile.add(itemNew);
        itemNew.setMnemonic('N');
        itemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_DOWN_MASK));

        JMenuItem itemOpen= new JMenuItem("Ouvrir...");
        menuFile.add(itemOpen);
        itemOpen.setMnemonic('O');
        itemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,KeyEvent.CTRL_DOWN_MASK));

        JMenuItem itemSave= new JMenuItem("Sauvegarder");
        menuFile.add(itemSave);
        itemSave.setMnemonic('S');
        itemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK));

        return menuBar;
    }

    public void menuListener(ActionEvent event){
        JOptionPane.showMessageDialog(this, "Entrer un message");
    }


}
