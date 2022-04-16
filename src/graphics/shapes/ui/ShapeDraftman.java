package graphics.shapes.ui;

import graphics.shapes.*;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;

import java.awt.*;
import java.util.Iterator;

public class ShapeDraftman implements ShapeVisitor {
    public static final ColorAttributes DEFAULTCOLORATTRIBUES = new ColorAttributes(false, true, Color.BLACK, Color.BLACK);
    private final Graphics g;

    public ShapeDraftman(Graphics g) {
        this.g = g;
    }

    @Override
    public void visitRectangle(SRectangle r) {
        ColorAttributes ca = (ColorAttributes) r.getAttributes(ColorAttributes.ID);
        SelectionAttributes sa = (SelectionAttributes) r.getAttributes(SelectionAttributes.ID);
        //new MouseTranslator(r.getRect().x,r.getRect().y);
        Point a = MouseInfo.getPointerInfo().getLocation();
        int x = (int) a.getX();
        int y = (int) a.getY();


        if (sa.isEntered()){
            g.setColor(Color.black);
            g.drawRect(r.getBounds().x,r.getBounds().y,r.getBounds().width,r.getBounds().height);
        }

        /*if(r.getRect().x>x  && x<r.getRect().x+r.getRect().width  && r.getRect().y>y && y<r.getRect().y+r.getRect().height){
            g.setColor(Color.black);
            g.fillRect(r.getRect().x, r.getRect().y, r.getRect().width, r.getRect().height);
        }*/

        else if (ca.filled) {
            g.setColor(ca.filledColor);
            g.fillRect(r.getRect().x, r.getRect().y, r.getRect().width, r.getRect().height);
        }

        else if (ca.stroked) {
            g.setColor(ca.filledColor);
            g.drawRect(r.getRect().x, r.getRect().y, r.getRect().width, r.getRect().height);
        }

        if (sa.isSelected()) {
            g.setColor(DEFAULTCOLORATTRIBUES.strokedColor);
            g.drawRect(r.getBounds().x, r.getBounds().y, r.getBounds().width, r.getBounds().height);
            g.fillRect(r.getBounds().x, r.getBounds().y, 5, 5);
            g.fillRect(r.getBounds().x + r.getBounds().width, r.getBounds().y + r.getBounds().height, 5, 5);
        }


    }

    @Override
    public void visitCircle(SCircle c) {
        ColorAttributes ca = (ColorAttributes) c.getAttributes(ColorAttributes.ID);
        SelectionAttributes sa = (SelectionAttributes) c.getAttributes(SelectionAttributes.ID);

        if (ca.filled) {
            g.setColor(ca.filledColor);
            g.fillOval(c.getBounds().x, c.getBounds().y, c.getBounds().width, c.getBounds().height);
        }

        if (ca.stroked) {
            g.setColor(ca.filledColor);
            g.drawOval(c.getBounds().x, c.getBounds().y, c.getBounds().width, c.getBounds().height);
        }

        if (sa.isSelected()) {
            g.setColor(DEFAULTCOLORATTRIBUES.strokedColor);
            g.drawRect(c.getBounds().x, c.getBounds().y, c.getBounds().width, c.getBounds().height);
            g.fillRect(c.getBounds().x, c.getBounds().y, 5, 5);
            g.fillRect(c.getBounds().x + c.getBounds().width, c.getBounds().y + c.getBounds().height, 5, 5);
        }
    }

    @Override
    public void visitText(SText t) {
        ColorAttributes ca = (ColorAttributes) t.getAttributes(ColorAttributes.ID);
        FontAttributes fa = (FontAttributes) t.getAttributes(FontAttributes.ID);
        SelectionAttributes sa = (SelectionAttributes) t.getAttributes(SelectionAttributes.ID);

        if (ca.filled) {
            g.setColor(ca.filledColor);
            g.fillRect(t.getBounds().x, t.getBounds().y, t.getBounds().width, t.getBounds().height);
        }

        if (ca.stroked) {
            g.setColor(ca.strokedColor);
            g.drawRect(t.getBounds().x, t.getBounds().y, t.getBounds().width, t.getBounds().height);
        }

        if (sa.isSelected()) {
            g.setColor(DEFAULTCOLORATTRIBUES.strokedColor);
            g.drawRect(t.getBounds().x, t.getBounds().y, t.getBounds().width, t.getBounds().height);
            g.fillRect(t.getBounds().x, t.getBounds().y, 5, 5);
            g.fillRect(t.getBounds().x + t.getBounds().width, t.getBounds().y + t.getBounds().height, 5, 5);
        }

        g.setFont(fa.font);
        g.setColor(fa.fontColor);
        g.drawString(t.getText(), t.getBounds().x, t.getBounds().y + t.getBounds().height);
    }

    @Override
    public void visitCollection(SCollection c) {
        SelectionAttributes sa = (SelectionAttributes) c.getAttributes(SelectionAttributes.ID);

        for (Iterator<Shape> it = c.iterator(); it.hasNext(); ) {
            Shape s = it.next();
            s.accept(this);
        }

        if (sa.isSelected()) {
            g.setColor(DEFAULTCOLORATTRIBUES.strokedColor);
            g.drawRect(c.getBounds().x, c.getBounds().y, c.getBounds().width, c.getBounds().height);
            g.fillRect(c.getBounds().x, c.getBounds().y, 5, 5);
            g.fillRect(c.getBounds().x + c.getBounds().width, c.getBounds().y + c.getBounds().height, 5, 5);
        }
    }

}
