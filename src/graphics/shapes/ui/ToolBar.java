package graphics.shapes.ui;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import graphics.shapes.attributes.SelectionAttributes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class ToolBar {
    Editor editor;

    public ToolBar(Editor editor) {
        this.editor = editor;
    }

    public JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();
        JButton buttonNew = new JButton(new ImageIcon("icons/new.png"));
        toolBar.add(buttonNew);

        JButton buttonSquare = new JButton(new ImageIcon("icons/square.png"));
        buttonSquare.setToolTipText("Forme: Carré");
        toolBar.add(buttonSquare);

        JButton buttonRectangle = new JButton(new ImageIcon("icons/rectangle.png"));
        buttonRectangle.setToolTipText("Forme: Rectangle");
        toolBar.add(buttonRectangle);

        JButton buttonCircle = new JButton(new ImageIcon("icons/circle.png"));
        buttonCircle.setToolTipText("Forme: Cercle");
        toolBar.add(buttonCircle);

        JButton buttonTriangle = new JButton(new ImageIcon("icons/triangle.png"));
        buttonTriangle.setToolTipText("Forme: Triangle");
        toolBar.add(buttonTriangle);

        JButton buttonFree = new JButton(new ImageIcon("icons/pentagon.png"));
        buttonFree.setToolTipText("Forme: Polygone Libre");
        toolBar.add(buttonFree);

        toolBar.addSeparator();

        JButton buttonText = new JButton(new ImageIcon("icons/text.png"));
        buttonCircle.setToolTipText("Ecrire le texte");
        toolBar.add(buttonText);

        JButton buttonDraw = new JButton(new ImageIcon("icons/draw.png"));
        buttonCircle.setToolTipText("Dessin libre");
        toolBar.add(buttonDraw);

        JButton buttonErase = new JButton(new ImageIcon("icons/gomme.png"));
        buttonErase.setToolTipText("Gomme");
        toolBar.add(buttonErase);
        buttonErase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SCollection coll = editor.sview.getModel();
                erase(coll);
            }
        });

        toolBar.addSeparator();

        JButton buttonPipette = new JButton(new ImageIcon("icons/pipette.png"));
        buttonPipette.setToolTipText("Pipette");
        toolBar.add(buttonPipette);

        JButton buttonColor = new JButton(new ImageIcon("icons/color.png")); //Afficher une page avec un choix de couleur
        buttonColor.setToolTipText("Choissir la couleur");
        buttonColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color=chooseColor();
            }
        });
        toolBar.add(buttonColor);

        toolBar.addSeparator();
        toolBar.add(new JTextField("Hello bg"));
        toolBar.add(new JCheckBox("Si t'es bg, check!"));
        return toolBar;

    }


    public static Shape erase(SCollection coll) {
        for (Iterator<Shape> it = coll.iterator(); it.hasNext(); ) {
            Shape s = it.next();
            SelectionAttributes sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);

            if (sa.isSelected()) {
                coll.remove(s);
                return s;
            }

        }
        return null;
    }
    public static Color chooseColor(){
        JColorChooser colorChooser= new JColorChooser();
        Color color=JColorChooser.showDialog(null,"Couleur à selectionner", Color.black);
        JOptionPane.showMessageDialog(null, "La couleur a bien été selectionné");
        return color;
    }
}
