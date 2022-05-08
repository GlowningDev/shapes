package graphics.shapes;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.ShapeVisitor;

import java.awt.*;

public class SCircle extends Shape {
    private Point point;
    private int rad;

    public SCircle(Point point, int rad) {
        this.point = point;
        this.rad = rad;
    }

    @Override
    public Point getLoc() {
        return null;
    }

    @Override
    public void setLoc(Point p) {
        point.setLocation(p);
    }

    @Override
    public void translate(int x, int y) {
        point.translate(x, y);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(point.x, point.y, rad * 2, rad * 2);
    }

    @Override
    public void accept(ShapeVisitor sv) {
        sv.visitCircle(this);
    }

    @Override
    public Object clone() {
        SCircle c = new SCircle(point.getLocation(), rad);
        c.addAttributes(new SelectionAttributes());

        if (this.getAttributes(ColorAttributes.ID) != null)
            c.addAttributes(this.getAttributes(ColorAttributes.ID));

        return c;
    }
}
