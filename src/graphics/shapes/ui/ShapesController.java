package graphics.shapes.ui;

import graphics.shapes.SCollection;
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
                    System.out.println("Je deviens orange");
                    getView().repaint();
                }
                else if (ca.filledColor == Color.orange){
                    ca.filledColor = Color.yellow;
                    ca.bufferColor = Color.yellow;
                    System.out.println("Je deviens jaune");
                    getView().repaint();
                }
                else if (ca.filledColor == Color.yellow){
                    ca.filledColor = Color.green;
                    ca.bufferColor = Color.green;
                    System.out.println("Je deviens vert");
                    getView().repaint();
                }
                else if (ca.filledColor == Color.green){
                    ca.filledColor = Color.blue;
                    ca.bufferColor = Color.blue;
                    System.out.println("Je deviens bleu");
                    getView().repaint();
                }
                else if (ca.filledColor == Color.blue){
                    ca.filledColor = Color.pink;
                    ca.bufferColor = Color.pink;
                    System.out.println("Je deviens rose");
                    getView().repaint();
                }
                else if (ca.filledColor == Color.pink){
                    ca.filledColor = Color.red;
                    ca.bufferColor = Color.red;
                    System.out.println("Je deviens rouge");
                    getView().repaint();
                }
                else if (ca.filledColor == Color.black){
                    ca.filledColor = Color.red;
                    ca.bufferColor = Color.red;
                    System.out.println("Je deviens rouge");
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
                ca.filledColor=ca.bufferColor;
            }
        });
        Shape s = newTarget(e.getX(),e.getY());
        if (s!=null){
            ColorAttributes ca= (ColorAttributes) s.getAttributes(ColorAttributes.ID);
            if (ca != null){
                ca.filledColor=Color.black;
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

    private Shape newTarget(int x, int y) {
        SCollection coll = (SCollection) getView().getModel();
        for (Iterator<Shape> it = coll.iterator(); it.hasNext(); ) {
            Shape s = it.next();
            if (s.getBounds().contains(new Point(x,y))) {
                return s;
            }
        }

        return null;
    }

    private Shape onTarget(MouseEvent e) {
        Point loc = e.getPoint();
        Shape coll = (Shape) getView().getModel();
        //Shape s = (Shape) coll;
        if (coll.getBounds().contains(loc)) {
            System.out.println("Dessus");
            return coll;
        } else { //ne marche que lorsque la souris rentre ou sort de la fênetre de graphics
            System.out.println("Pas dessus");
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
