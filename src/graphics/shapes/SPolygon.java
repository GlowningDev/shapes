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
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        int comptmin = 0;
        for (int i = 0; i < xPoints.length; i++) {
            if (xPoints[i] < minX) {
                minX = xPoints[i];
                comptmin = i;
            }
            if (xPoints[i] > maxX) {
                maxX = xPoints[i];
            }
        }
        for (int valuey : yPoints) {
            if (valuey < minY) {
                minY = valuey;
            }
            if (valuey > maxY) {
                maxY = valuey;
            }
        }
        System.out.println(minY);
        System.out.println(maxY);
        return new Rectangle(minX, yPoints[comptmin], maxX - minX, maxY - minY);
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
