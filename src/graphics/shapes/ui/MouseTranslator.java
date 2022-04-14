package graphics.shapes.ui;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class MouseTranslator extends JFrame implements MouseListener{

    JLabel label;

    MouseTranslator(int x, int y){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(x,y);
        this.setLayout(null);

        label = new JLabel();
        label.setBounds(300, 300, x, y);
        label.setOpaque(true);
        label.addMouseListener(this);

        this.add(label);
        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Invoked when the mouse button has been clicked (pressed and released) on a component
        //System.out.println("You clicked the mouse");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Invoked when a mouse button has been pressed on a component
        System.out.println("You pressed the mouse");
        label.setBackground(Color.yellow);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Invoked when a mouse button has been released on a component
        System.out.println("You released the mouse");
        label.setBackground(Color.green);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Invoked when the mouse enters a component
        System.out.println("You entered the component");
        label.setBackground(Color.blue);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Invoked when the mouse exits a component
        System.out.println("You exited the component");
        label.setBackground(Color.red);
    }

}