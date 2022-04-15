package graphics.shapes.ui;

import graphics.shapes.SCollection;
import graphics.ui.Controller;
import graphics.ui.View;

import java.awt.*;

public class ShapesView extends View {
    SCollection model;
    ShapeDraftman sd;

    public ShapesView(SCollection model) {
        super(model);
        this.model = model;
        defaultController(model);
    }

    public SCollection getModel() {
        return model;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        sd = new ShapeDraftman(g);
        model.accept(sd);
    }

    @Override
    public Controller defaultController(Object model) {
        return new ShapesController(this.getModel());
    }

    @Override
    public boolean isFocusable() {
        return true;
    }
}
