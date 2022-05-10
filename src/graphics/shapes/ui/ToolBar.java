package graphics.shapes.ui;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class ToolBar {
    Editor editor;
    public static ColorAttributes LAST_COLOR = ShapeDraftman.DEFAULTCOLORATTRIBUES;

    public ToolBar(Editor editor) {
        this.editor = editor;
    }

    public JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();

        JButton buttonRectangle = new JButton(new ImageIcon("icons/rectangle.png"));
        buttonRectangle.setToolTipText("Forme: Rectangle");
        buttonRectangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Editor.state = ButtonState.RECTANGLE;
            }
        });
        toolBar.add(buttonRectangle);

        JButton buttonCircle = new JButton(new ImageIcon("icons/circle.png"));
        buttonCircle.setToolTipText("Forme: Cercle");
        buttonCircle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Editor.state = ButtonState.CIRCLE;
            }
        });
        toolBar.add(buttonCircle);

        toolBar.addSeparator();

        JButton buttonText = new JButton(new ImageIcon("icons/text.png"));
        buttonCircle.setToolTipText("Ecrire le texte");
        buttonCircle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Editor.state = ButtonState.TEXT;
            }
        });
        toolBar.add(buttonText);

        JButton buttonErase = new JButton(new ImageIcon("icons/gomme.png"));
        buttonErase.setToolTipText("Gomme");
        toolBar.add(buttonErase);
        buttonErase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SCollection coll = editor.sview.getModel();
                erase(coll);
                editor.getContentPane().repaint();
            }
        });

        toolBar.addSeparator();

        JButton buttonColor = new JButton(new ImageIcon("icons/color.png")); //Afficher une page avec un choix de couleur
        buttonColor.setToolTipText("Choissir la couleur");
        buttonColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseColor();
            }
        });
        toolBar.add(buttonColor);

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
    public static void chooseColor(){
        JColorChooser colorChooser= new JColorChooser();
        Color color=JColorChooser.showDialog(null,"Couleur à selectionner", Color.black);
        JOptionPane.showMessageDialog(null, "La couleur a bien été selectionné");

        LAST_COLOR = new ColorAttributes(false, true, color, color);
    }
}
