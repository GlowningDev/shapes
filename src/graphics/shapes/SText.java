package graphics.shapes;

import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.ui.ShapeVisitor;
import graphics.shapes.ui.ShapesView;

import java.awt.*;

public class SText extends Shape {
    private Point point;
    private String text;

    public SText(Point point, String text) {
        this.point = point;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Point getLoc() {
        return point.getLocation();
    }

    @Override
    public void setLoc(Point p) {
        this.point = p;
    }

    @Override
    public void translate(int x, int y) {
        point.translate(x, y);
    }

    @Override
    public Rectangle getBounds() {
        FontAttributes fa = (FontAttributes) getAttributes(FontAttributes.ID);
        return new Rectangle(getLoc().x, getLoc().y, fa.getBounds(text).width, fa.getBounds(text).height);
    }

    @Override
    public void accept(ShapeVisitor sv) {
        sv.visitText(this);
    }
}
