package graphics.shapes;

import graphics.shapes.ui.ShapeVisitor;

import java.awt.*;


public class SPolygon extends Shape {
    private int[] xPoints;
    private int[] yPoints;
    private int nbPoints;

    Point point=new Point (xPoints[0], yPoints[0]);


    public  SPolygon(int[] xPoints, int[] yPoints, int nbPoints){
        this.xPoints=xPoints;
        this.yPoints=yPoints;
        this.nbPoints=nbPoints;
        this.point=point;
    }


    @Override
    public Point getLoc() { return null;}

    @Override
    public void setLoc(Point p) {point.setLocation(p);}

    @Override
    public void translate(int x, int y) {point.translate(x,y);}

    @Override
    public Rectangle getBounds() {
        return new Rectangle(point.x, point.y, point.x * 2, point.y * 2); ///x=min(xpoints);y qui va avec le x;l=max(xpoints)-min(xpoints); h=max(ypoints)-min(ypoints)
    }

    @Override
    public void accept(ShapeVisitor sv) { //Faire le visitPolygon dans le Draftman
        sv.visitPolygon(this);

    }
}
