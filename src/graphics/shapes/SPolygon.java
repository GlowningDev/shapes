package graphics.shapes;

import graphics.shapes.ui.ShapeVisitor;

import java.awt.*;


public class SPolygon extends Shape {
    private int[] xPoints;
    private int[] yPoints;
    private int nbPoints;

    Point point;


    public  SPolygon(int[] xPoints, int[] yPoints, int nbPoints){
        this.xPoints=xPoints;
        this.yPoints=yPoints;
        this.nbPoints=nbPoints;
        this.point=new Point (xPoints[0], yPoints[0]);
    }


    @Override
    public Point getLoc() { return null;}

    @Override
    public void setLoc(Point p) {point.setLocation(p);}

    @Override
    public void translate(int x, int y) {point.translate(x,y);}

    @Override
    public Rectangle getBounds() {
        int minX = Integer.MAX_VALUE; //Faire petite boucle pour calculer le min Math.min) et Math.max pour x et y
        return new Rectangle(point.x, point.y, point.x * 2, point.y * 2); ///x=min(xpoints)(Math.min);y qui va avec le x;l=max(xpoints)-min(xpoints); h=max(ypoints)-min(ypoints)
    }

    public int[] getxPoints() {
        return xPoints;
    }

    public void setxPoints(int[] xPoints) {
        this.xPoints = xPoints;
    }

    public int[] getyPoints() {
        return yPoints;
    }

    public void setyPoints(int[] yPoints) {
        this.yPoints = yPoints;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public void setNbPoints(int nbPoints) {
        this.nbPoints = nbPoints;
    }

    @Override
    public void accept(ShapeVisitor sv) {
        sv.visitPolygon(this);

    }
}
