package graphics.shapes;

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
        return c;
    }
}
