package graphics.shapes.attributes;

import java.awt.*;

public class ColorAttributes extends Attributes {
    public static final String ID = "color";

    public boolean filled;
    public boolean stroked;
    public Color filledColor;
    public Color strokedColor;
    public Color bufferColor;

    public ColorAttributes(boolean filled, boolean stroked, Color filledColor, Color strokedColor) {
        this.filled = filled;
        this.stroked = stroked;
        this.filledColor = filledColor;
        this.strokedColor = strokedColor;
        this.bufferColor = filledColor;
    }

    @Override
    public String getId() {
        return ID;
    }
}
