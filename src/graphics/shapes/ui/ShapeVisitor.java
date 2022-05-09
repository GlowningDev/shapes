package graphics.shapes.ui;

import graphics.shapes.*;

public interface ShapeVisitor {

    public void visitRectangle(SRectangle r);
    public void visitCircle(SCircle r);
    public void visitText(SText r);
    public void visitCollection(SCollection r);
    public void visitPolygon(SPolygon r);

}
