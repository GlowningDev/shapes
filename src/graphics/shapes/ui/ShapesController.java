package graphics.shapes.ui;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.ui.Controller;

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
            if (ca.filledColor !=null && ca.bufferColor !=null) {
                if (ca.filledColor == Color.black)
                    ca.filledColor = Color.red;
                else if (ca.filledColor == Color.red)
                    ca.filledColor = Color.orange;
                else if (ca.filledColor == Color.orange)
                    ca.filledColor = Color.yellow;
                else if (ca.filledColor == Color.yellow)
                    ca.filledColor = Color.green;
                else if (ca.filledColor == Color.green)
                    ca.filledColor = Color.blue;
                else if (ca.filledColor == Color.blue)
                    ca.filledColor = Color.pink;
                else if (ca.filledColor == Color.pink)
                    ca.filledColor = ca.bufferColor;
                else if (ca.filledColor == ca.bufferColor)
                    ca.filledColor = Color.black;
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e){
        SCollection coll = (SCollection) getView().getModel();
        coll.iterator().forEachRemaining(shape -> {
            ColorAttributes ca = (ColorAttributes) shape.getAttributes(ColorAttributes.ID);
            if (ca!=null){
                ca.strokedColor=ca.bufferColor;
            }
        });
        Shape s = newTarget(e.getX(),e.getY());
        if (s!=null){
            ColorAttributes ca= (ColorAttributes) s.getAttributes(ColorAttributes.ID);
            if (ca != null){
                ca.strokedColor=Color.black;
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
