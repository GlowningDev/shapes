package graphics.shapes;

import graphics.shapes.ui.ShapeVisitor;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SCollection extends Shape {

    List<Shape> shapes;

    public SCollection() {
        this.shapes = new ArrayList<>();
    }

    @Override
    public Point getLoc() {
        return null;
    }

    @Override
    public void setLoc(Point p) {
        for (Shape s: shapes)
            s.setLoc(p);
    }

    @Override
    public void translate(int x, int y) {
        for (Shape s: shapes)
            s.translate(x, y);
    }

    @Override
    public Rectangle getBounds() {
        Rectangle bounds = this.shapes.get(0).getBounds();
        if (this.shapes.size() > 0) {
            for (Shape s: shapes)
                bounds = bounds.union(s.getBounds());
        }

        return bounds;
    }

    @Override
    public void accept(ShapeVisitor sv) {
        sv.visitCollection(this);
    }

    public Iterator<Shape> iterator() {
        return shapes.iterator();
    }

    public void add(Shape s) {
        this.shapes.add(s);
    }

    public void remove(Shape s) {
        this.shapes.remove(s);
    }

    @Override
    public Object clone() {
        return null;
    }

}
