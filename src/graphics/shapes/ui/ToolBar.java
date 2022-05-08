package graphics.shapes.ui;

import graphics.shapes.SRectangle;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;

import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ToolBar extends JFrame {


    public ToolBar(){
        this.setSize(600,300);
        this.setLocationRelativeTo(null); //Centre par rapport au conteneur en parametre
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //Detruire l'objet

        JPanel contentPane =(JPanel) this.getContentPane();//Construction et injection de la barre
        //contentPane.setLayout(new BorderLayout()); val par default
        contentPane.add(createToolBar(), BorderLayout.NORTH);

    }

    private JToolBar createToolBar(){
        JToolBar toolBar= new JToolBar();
        JButton buttonNew=new JButton(new ImageIcon("icons/new.png"));
        toolBar.add(buttonNew);

        JButton buttonCircle=new JButton(new ImageIcon("icons/circle.png"));
        buttonCircle.setToolTipText("Forme: Cercle");
        toolBar.add(buttonCircle);

        JButton buttonSquare=new JButton(new ImageIcon("icons/square.png"));
        buttonSquare.setToolTipText("Forme: Square");
        toolBar.add(buttonCircle);

        JButton buttonRectangle=new JButton(new ImageIcon("icons/rectangle.png"));
        buttonRectangle.setToolTipText("Forme: Rectangle");

        toolBar.add(buttonRectangle);

        JButton buttonTriangle=new JButton(new ImageIcon("icons/triangle.png"));
        buttonTriangle.setToolTipText("Forme: Triangle");
        toolBar.add(buttonTriangle);

        JButton buttonFree=new JButton(new ImageIcon("icons/pentagon.png"));
        buttonFree.setToolTipText("Forme: Polygone Libre");
        toolBar.add(buttonFree);

        toolBar.addSeparator();

        JButton buttonErase=new JButton(new ImageIcon("icons/gomme.png"));
        buttonErase.setToolTipText("Gomme");
        toolBar.add(buttonErase);

        toolBar.addSeparator();

        JButton buttonPipette=new JButton(new ImageIcon("icons/pipette.png"));
        buttonPipette.setToolTipText("Pipette");
        toolBar.add(buttonPipette);

        JButton buttonColor=new JButton(new ImageIcon("icons/color.png")); //Afficher une page avec un choix de couleur
        buttonColor.addActionListener(this::ColorListener);
        buttonColor.setToolTipText("Choissir la couleur");
        toolBar.add(buttonColor);

        toolBar.addSeparator();
        toolBar.add(new JTextField("Hello bg"));
        toolBar.add(new JCheckBox("Si t'es bg, check!"));
        return toolBar;

    }
    //barre d'outil //JFileChooser (sauvegarder)
    public void ColorListener(ActionEvent event){
        JOptionPane.showMessageDialog(this, "Systeme de couleur");
    }


}
