package graphics.shapes;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;
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

    public void setSize(Point p) {
        // TODO: La forme est pleine quand les calculs vont dans le négatif
        rect.width = p.x - rect.x;
        rect.height = p.y - rect.y;
    }
    
    @Override
    public Object clone() {
        SRectangle r = new SRectangle(rect.getLocation(), rect.getBounds().width, rect.getBounds().height);
        r.addAttributes(new SelectionAttributes());

        if (this.getAttributes(ColorAttributes.ID) != null)
            r.addAttributes(this.getAttributes(ColorAttributes.ID));

        return r;
    }
}
