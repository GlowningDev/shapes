package graphics.shapes;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.ShapeVisitor;

import java.awt.*;

public class SCircle extends Shape {
    private final Point point;
    private int rad;

    public SCircle(Point point, int rad) {
        this.point = point;
        this.rad = rad;
    }

    @Override
    public Point getLoc() {
        return point;
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

    public void setSize(Point p) {
        int tmpX = (p.x - point.x) / 2;
        int tmpY = (p.y - point.y) / 2;
        rad = Math.max(tmpX, tmpY);
    }
  
    @Override
    public Object clone() {
        SCircle c = new SCircle(point.getLocation(), rad);
        c.addAttributes(new SelectionAttributes());

        if (this.getAttributes(ColorAttributes.ID) != null) {
            ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
            c.addAttributes(new ColorAttributes(ca.filled, ca.stroked, ca.filledColor, ca.strokedColor));
        }

        return c;
    }
}
