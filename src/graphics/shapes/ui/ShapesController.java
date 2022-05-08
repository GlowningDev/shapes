package graphics.shapes.ui;

import graphics.shapes.*;
import graphics.shapes.Shape;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.ui.Controller;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Iterator;

public class ShapesController extends Controller {
    private Point lastMouseClick;
    private SelectionAttributes sa;
    private Shape drewShape;

    public ShapesController(Object object) {
        super(object);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!e.isShiftDown())
            unselectAll();

        Shape s = onTarget(e);
        if (s != null)
            ((SelectionAttributes) s.getAttributes(SelectionAttributes.ID)).toggleSelection();

        getView().repaint();
    }

    public void mouseEntered(MouseEvent e) {
        Shape s = onTarget(e);
        if (sa!= null && s!=null) {
            sa.enter();
        }

        getView().repaint();
    }

    public void mouseExited(MouseEvent e) {
        Shape s = onTarget(e);
        if (sa!= null && s!= null){
            sa.exit();
        }

        getView().repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.lastMouseClick = e.getPoint();

        if (e.isControlDown()) {
            drewShape = new SRectangle(e.getPoint(), 1, 1);
            //drewShape = new SCircle(e.getPoint(), 1);
            drewShape.addAttributes(new SelectionAttributes());
            ((SCollection) getView().getModel()).add(drewShape);
        }
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

        if (e.isControlDown() && drewShape != null)
            drewShape.setSize(loc);

        lastMouseClick = loc;
        getView().repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        drewShape = null;
    }

    @Override
    public void keyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            unselectAll();
            getView().repaint();
        }
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
