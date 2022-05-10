package graphics.shapes;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;
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
    public Point getLoc() {
        return new Point(getBounds().x, getBounds().y);
    } //obtenir la localisation

    @Override
    public void setLoc(Point p) {point.setLocation(p);} //definir la localisation

    @Override
    public void translate(int x, int y) {
        for (int i = 0; i < nbPoints; i++) {
            xPoints[i] += x;
            yPoints[i] += y;
        }
    }

    public int[] mini(int[] tab) {
        int min = Integer.MAX_VALUE;
        int [] minimum= new int[2];
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] < min) {
                minimum[0] = tab[i];
                minimum[1] = i;
            }
        }
        return minimum;
    }

    public int maxi(int[] tab){
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] > max) {
                max = tab[i];
            }
        }
        return max;
    }

    @Override
    public Rectangle getBounds() {
        int minX= mini(xPoints)[0];
        int maxX= maxi(xPoints);
        int minY= mini(yPoints)[0];
        int maxY= maxi(yPoints);
        return new Rectangle(minX, minY, maxX - minX, maxY- minY);
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
    @Override
    public Object clone() {
        SPolygon p = new SPolygon(getxPoints(),getyPoints(),getNbPoints());
        p.addAttributes(new SelectionAttributes());

        if (this.getAttributes(ColorAttributes.ID) != null) {
            ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
            p.addAttributes(new ColorAttributes(ca.filled, ca.stroked, ca.filledColor, ca.strokedColor));
        }

        return p;
    }
}
