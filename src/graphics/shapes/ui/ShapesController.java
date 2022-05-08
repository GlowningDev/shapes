package graphics.shapes.ui;

import graphics.shapes.SCollection;
import graphics.shapes.SText;
import graphics.shapes.Shape;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.ui.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShapesController extends Controller {
    private Point lastMouseClick;

    public ShapesController(Object object) {
        super(object);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        SCollection coll = (SCollection) getView().getModel();

        Point p = e.getPoint();
        List<Shape> clicked = new ArrayList<>();

        for (Iterator<Shape> it = coll.iterator(); it.hasNext();) {
            Shape s = it.next();
            ((SelectionAttributes) s.getAttributes(SelectionAttributes.ID)).unselect();
            if (s.getBounds().contains(p)) {
                clicked.add(s);
            }

            if (e.getClickCount() == 2) {
                if (s instanceof SText text) {
                    String str = (String) JOptionPane.showInputDialog(getView(), "Nouveau texte :", "Modification du texte", JOptionPane.PLAIN_MESSAGE, null, null, text.getText());
                    text.setText(str != null ? str : text.getText());
                }
            }
        }

        if (clicked.size() > 0) {
            Shape s = clicked.get(0);
            ((SelectionAttributes) s.getAttributes(SelectionAttributes.ID)).toggleSelection();
        }

        getView().repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.lastMouseClick = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point loc = e.getPoint();

        SCollection coll = (SCollection) getView().getModel();
        for (Iterator<Shape> it = coll.iterator(); it.hasNext();) {
            Shape s = it.next();
            SelectionAttributes sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);

            if (sa.isSelected())
                s.translate(loc.x - lastMouseClick.x, loc.y - lastMouseClick.y);
        }

        lastMouseClick = loc;
        getView().repaint();
    }

    @Override
    public void keyPressed(KeyEvent evt) {
        System.out.println(evt.getKeyCode());
        if(evt.isControlDown() && evt.getKeyCode()==evt.VK_A){
            for (Shape s:((SCollection) getView().getModel()).getShapes()){
                SelectionAttributes selected = (SelectionAttributes) s.getAttributes("selection");
                selected.select();
            }
            getView().repaint();
        }
        if(evt.getKeyCode()==evt.VK_UP) {
            translate(0,-1);
        }
        if(evt.getKeyCode()==evt.VK_DOWN) {
            translate(0,1);
        }
        if(evt.getKeyCode()==evt.VK_LEFT) {
            translate(-1,0);
        }
        if(evt.getKeyCode()==evt.VK_RIGHT) {
            translate(1,0);
        }
    }

    public void translate(int x, int y){
        for (Shape s : ((SCollection) getView().getModel()).getShapes()) {
            SelectionAttributes selected = (SelectionAttributes) s.getAttributes("selection");
            if(selected.isSelected()){
                s.translate(x,y);
            }
        }
        getView().repaint();
    }
}
