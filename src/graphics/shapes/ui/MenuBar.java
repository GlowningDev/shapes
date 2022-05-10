package graphics.shapes.ui;


import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import graphics.shapes.attributes.SelectionAttributes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Iterator;

import static graphics.shapes.ui.ToolBar.chooseColor;
import static graphics.shapes.ui.ToolBar.erase;

public class MenuBar extends JFrame {

    Editor editor;
    Shape copiedShape;

    public MenuBar(Editor editor) {
        this.editor = editor;
    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar=new JMenuBar();

        JMenu menuFile= new JMenu ("Fichier");
        menuBar.add(menuFile);
        menuFile.setMnemonic('F');

        JMenuItem itemNew= new JMenuItem("Nouveau Fichier");
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


        JMenuItem itemCopy= new JMenuItem("Copier");
        menuEdit.add(itemCopy);
        itemCopy.setMnemonic('C');
        itemCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_DOWN_MASK));
        itemCopy.setIcon(new ImageIcon("icons/copy.png"));
        itemCopy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SCollection coll = editor.sview.getModel();
                copy(coll);
            }
        });


        JMenuItem itemCut= new JMenuItem("Couper");
        menuEdit.add(itemCut);
        itemCut.setMnemonic('C');
        itemCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,KeyEvent.CTRL_DOWN_MASK));
        itemCut.setIcon(new ImageIcon("icons/cut.png"));
        itemCut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SCollection coll = editor.sview.getModel();
                cut(coll);
            }
        });


        JMenuItem itemPaste= new JMenuItem("Coller");
        menuEdit.add(itemPaste);
        itemPaste.setMnemonic('P');
        itemPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,KeyEvent.CTRL_DOWN_MASK));
        itemPaste.setIcon(new ImageIcon("icons/paste.png"));
        itemPaste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SCollection coll = editor.sview.getModel();
                Shape s= paste(coll);
                coll.add(s);
            }
        });

        JMenu menuShape= new JMenu ("Forme");
        menuBar.add(menuShape);
        menuShape.setMnemonic('F');

        JMenuItem itemSquare= new JMenuItem("Carré");
        menuShape.add(itemSquare);
        itemSquare.setMnemonic('C');
        itemSquare.setIcon(new ImageIcon("icons/square.png"));

        JMenuItem itemRectangle= new JMenuItem("Rectangle");
        menuShape.add(itemRectangle);
        itemRectangle.setMnemonic('R');
        itemRectangle.setIcon(new ImageIcon("icons/rectangle.png"));

        JMenuItem itemCircle= new JMenuItem("Cercle");
        menuShape.add(itemCircle);
        itemCircle.setMnemonic('C');
        itemCircle.setIcon(new ImageIcon("icons/circle.png"));

        JMenuItem itemTriangle= new JMenuItem("Triangle");
        menuShape.add(itemTriangle);
        itemTriangle.setMnemonic('T');
        itemTriangle.setIcon(new ImageIcon("icons/triangle.png"));

        JMenuItem itemPolygon= new JMenuItem("Polygone");
        menuShape.add(itemPolygon);
        itemPolygon.setMnemonic('P');
        itemPolygon.setIcon(new ImageIcon("icons/pentagon.png"));


        JMenu menuExtension= new JMenu ("Extension");
        menuBar.add(menuExtension);
        menuExtension.setMnemonic('E');

        JMenuItem itemText= new JMenuItem("Texte");
        menuExtension.add(itemText);
        itemText.setMnemonic('T');
        itemText.setIcon(new ImageIcon("icons/text.png"));

        JMenuItem itemDraw= new JMenuItem("Dessin");
        menuExtension.add(itemDraw);
        itemDraw.setMnemonic('D');
        itemDraw.setIcon(new ImageIcon("icons/draw.png"));

        JMenuItem itemGomme= new JMenuItem("Gomme");
        menuExtension.add(itemGomme);
        itemGomme.setMnemonic('G');
        itemGomme.setIcon(new ImageIcon("icons/gomme.png"));
        itemGomme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SCollection coll = editor.sview.getModel();
                erase(coll);
            }
        });

        JMenuItem itemPipette= new JMenuItem("Pipette");
        menuExtension.add(itemPipette);
        itemPipette.setMnemonic('P');
        itemPipette.setIcon(new ImageIcon("icons/pipette.png"));


        JMenuItem itemColor= new JMenuItem("Couleur");
        menuExtension.add(itemColor);
        itemColor.setMnemonic('C');
        itemColor.setIcon(new ImageIcon("icons/color.png"));
        itemColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = chooseColor();
            }
        });

        return menuBar;
    }

    public Shape copy(SCollection coll) {
        for (Iterator<Shape> it = coll.iterator(); it.hasNext();) {
            Shape s = it.next();
            SelectionAttributes sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);

            if (sa.isSelected()) {
                copiedShape = s;
                return copiedShape;
            }
        }

        return null;
    }

    public Shape cut(SCollection coll){
        Shape copyS=copy(coll);
        coll.remove(copyS);
        return copyS;
    }

    public Shape paste(SCollection coll){
        Shape pasteShape= (Shape) copiedShape.clone();
        return pasteShape;
    }


}
