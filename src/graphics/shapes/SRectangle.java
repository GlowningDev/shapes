package graphics.shapes;

import graphics.shapes.ui.ShapeVisitor;

import java.awt.*;

public class SRectangle extends Shape {
    private final Rectangle rect;

    public SRectangle(Point point, int l, int h) {
        this.rect = new Rectangle(point.x, point.y, l, h);
    }

    public Rectangle getRect() {
        return rect;
    }

    @Override
    public Point getLoc() {
        return rect.getLocation();
    }

    @Override
    public void setLoc(Point p) {
        rect.setLocation(p);
    }

    @Override
    public void translate(int x, int y) {
        rect.translate(x, y);
    }

    @Override
    public Rectangle getBounds() {
        return rect;
    }

    @Override
    public void accept(ShapeVisitor sv) {
        sv.visitRectangle(this);
    }
}
