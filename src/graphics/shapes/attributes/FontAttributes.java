package graphics.shapes.attributes;

import graphics.shapes.ui.ShapeDraftman;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class FontAttributes extends Attributes {
    public static final String ID = "font";
    public static final Graphics FRAME_GRAPHICS = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB).getGraphics();

    public Font font = new Font("Arial", Font.PLAIN, 10);
    public Color fontColor = ShapeDraftman.DEFAULTCOLORATTRIBUES.strokedColor;

    public Rectangle getBounds(String str) {
        Rectangle2D r2d = FRAME_GRAPHICS.getFontMetrics(font).getStringBounds(str, FRAME_GRAPHICS);
        return new Rectangle(r2d.getBounds().x, r2d.getBounds().y, r2d.getBounds().width, r2d.getBounds().height);
    }

    @Override
    public String getId() {
        return ID;
    }
}
