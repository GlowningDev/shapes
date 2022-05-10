package graphics.shapes.ui;

import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.ui.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Iterator;

public class ShapesController extends Controller {
    private Point lastMouseClick;
    private SelectionAttributes sa;

    public ShapesController(Object object) {
        super(object);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!e.isShiftDown())
            unselectAll();

        Shape s = getTarget();
        if (s != null) {
            ((SelectionAttributes) s.getAttributes(SelectionAttributes.ID)).toggleSelection();
            ColorAttributes ca = (ColorAttributes) s.getAttributes(ColorAttributes.ID);
            if (ca !=null) {
                if (ca.filledColor == Color.red){
                    ca.filledColor = Color.orange;
                    ca.bufferColor = Color.orange;
                    getView().repaint();
                }
                else if (ca.filledColor == Color.orange){
                    ca.filledColor = Color.yellow;
                    ca.bufferColor = Color.yellow;
                    getView().repaint();
                }
                else if (ca.filledColor == Color.yellow){
                    ca.filledColor = Color.green;
                    ca.bufferColor = Color.green;
                    getView().repaint();
                }
                else if (ca.filledColor == Color.green){
                    ca.filledColor = Color.blue;
                    ca.bufferColor = Color.blue;
                    getView().repaint();
                }
                else if (ca.filledColor == Color.blue){
                    ca.filledColor = Color.pink;
                    ca.bufferColor = Color.pink;
                    getView().repaint();
                }
                else if (ca.filledColor == Color.pink){
                    ca.filledColor = Color.red;
                    ca.bufferColor = Color.red;
                    getView().repaint();
                }
                else if (ca.filledColor == Color.black){
                    ca.filledColor = Color.red;
                    ca.bufferColor = Color.red;
                    getView().repaint();
                }
            }

            if (e.getClickCount() == 2) {
                if (s instanceof SText text) {
                    String str = (String) JOptionPane.showInputDialog(getView(), "Nouveau texte :", "Modification du texte", JOptionPane.PLAIN_MESSAGE, null, null, text.getText());
                    text.setText(str != null ? str : text.getText());
                }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e){
        SCollection coll = (SCollection) getView().getModel();
        coll.iterator().forEachRemaining(shape -> {
            ColorAttributes ca = (ColorAttributes) shape.getAttributes(ColorAttributes.ID);
            if (ca!=null){
                ca.filledColor=ca.bufferColor; //ramène la couleur de la forme à sa couleur de base
            }
        });
        Shape s = onTarget(e);
        if (s!=null){
            if (s instanceof SCollection){
                for (Iterator<Shape> it = coll.iterator(); it.hasNext(); ) {
                    Shape s2 = it.next();
                    ColorAttributes ca= (ColorAttributes) s2.getAttributes(ColorAttributes.ID);
                    if (ca != null){
                        ca.filledColor=Color.black;
                    }
                }
            }
            else{
                ColorAttributes ca= (ColorAttributes) s.getAttributes(ColorAttributes.ID);
                if (ca != null){
                    ca.filledColor=Color.black; //change la couleur de la forme au noir au survol
                }
            }
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
        for (Iterator<Shape> it = coll.iterator(); it.hasNext(); ) {
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

    private Shape getTarget() {
        SCollection coll = (SCollection) getView().getModel();
        for (Iterator<Shape> it = coll.iterator(); it.hasNext(); ) {
            Shape s = it.next();
            if (s.getBounds().contains(lastMouseClick)) {
                return s;
            }
        }

        return null;
    }

    private Shape onTarget(MouseEvent e){
        Point loc = e.getPoint();
        SCollection coll = (SCollection) getView().getModel();
        for (Iterator<Shape> it = coll.iterator(); it.hasNext();) {
            Shape s = it.next();
            if (s.getBounds().contains(loc)) {
                return s;
            }
        }

        return null;
    }

    private void unselectAll() {
        SCollection coll = (SCollection) getView().getModel();
        coll.iterator().forEachRemaining(shape -> {
            SelectionAttributes sa = (SelectionAttributes) shape.getAttributes(SelectionAttributes.ID);
            sa.unselect();
        });
    }
}
