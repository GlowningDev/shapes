package graphics.shapes;

import graphics.shapes.attributes.SelectionAttributes;
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
        return new Point(getBounds().x, getBounds().y);
    }

    @Override
    public void setLoc(Point p) {
        int x = getLoc().x;
        int y = getLoc().y;

        for (Shape s: shapes) {
            System.out.println();
            s.translate(p.x - x, p.y - y);
        }
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
        SCollection coll = new SCollection();
        for (Shape s: shapes) {
            Shape clone = (Shape) s.clone();
            coll.add(clone);
        }

        coll.addAttributes(new SelectionAttributes());
        return coll;
    }

    public List<Shape> getShapes(){
        return shapes;
    }
}
