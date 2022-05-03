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
        itemNew.setIcon(new ImageIcon("icons/new.png"));


        JMenuItem itemOpen= new JMenuItem("Ouvrir...");
        menuFile.add(itemOpen);
        itemOpen.setMnemonic('O');
        itemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,KeyEvent.CTRL_DOWN_MASK));
        itemOpen.setIcon(new ImageIcon("icons/open.png"));


        JMenuItem itemSave= new JMenuItem("Sauvegarder");
        menuFile.add(itemSave);
        itemSave.setMnemonic('S');
        itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK));
        itemSave.setIcon(new ImageIcon("icons/save.png"));

        JMenu menuEdit= new JMenu ("Edit");
        menuBar.add(menuEdit);
        menuEdit.setMnemonic('E');


        JMenuItem itemUndo= new JMenuItem("Undo");
        menuEdit.add(itemUndo);
        itemUndo.setMnemonic('U');
        itemUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,KeyEvent.CTRL_DOWN_MASK));
        itemUndo.setIcon(new ImageIcon("icons/undo.png"));


        JMenuItem itemRedo=new JMenuItem("Redo");
        menuEdit.add(itemRedo);
        itemRedo.setMnemonic('R');
        itemRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,KeyEvent.CTRL_DOWN_MASK));
        itemRedo.setIcon(new ImageIcon("icons/redo.png"));


        JMenuItem itemCopy= new JMenuItem("Copy");
        menuEdit.add(itemCopy);
        itemCopy.setMnemonic('C');
        itemCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_DOWN_MASK));
        itemCopy.setIcon(new ImageIcon("icons/copy.png"));


        JMenuItem itemCut= new JMenuItem("Cut");
        menuEdit.add(itemCut);
        itemCut.setMnemonic('C');
        itemCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,KeyEvent.CTRL_DOWN_MASK));
        itemCut.setIcon(new ImageIcon("icons/cut.png"));


        JMenuItem itemPaste= new JMenuItem("Paste");
        menuEdit.add(itemPaste);
        itemPaste.setMnemonic('P');
        itemPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,KeyEvent.CTRL_DOWN_MASK));
        itemPaste.setIcon(new ImageIcon("icons/paste.png"));



        return menuBar;
    }
//barre d'outil //JFileChooser (sauvegarder)
    public void menuListener(ActionEvent event){
        JOptionPane.showMessageDialog(this, "Entrer un message");
    }


}
