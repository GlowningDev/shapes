package graphics.shapes;
import java.lang.Cloneable;

import graphics.shapes.attributes.Attributes;
import graphics.shapes.ui.ShapeVisitor;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public abstract class Shape implements Cloneable {

    Map<String, Attributes> attributes;

    public Shape() {
        this.attributes = new HashMap<>();
    }

    public void addAttributes(Attributes a) {
        this.attributes.put(a.getId(), a);
    }

    public Attributes getAttributes(String attr) {
        if (attributes.containsKey(attr))
            return attributes.get(attr);

        return null;
    }

    public abstract Point getLoc();
    public abstract void setLoc(Point p);
    public abstract void translate(int x, int y);
    public abstract Rectangle getBounds();
    public abstract void accept(ShapeVisitor sv);

}
