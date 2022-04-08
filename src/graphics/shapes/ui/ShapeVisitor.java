package graphics.shapes.ui;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;

public interface ShapeVisitor {

    public void visitRectangle(SRectangle r);
    public void visitCircle(SCircle r);
    public void visitText(SText r);
    public void visitCollection(SCollection r);

}
