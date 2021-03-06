package graphics.shapes.attributes;

public class SelectionAttributes extends Attributes {
    public static final String ID = "selection";
    private boolean selected = false;
    //private boolean entered = false;

    public boolean isSelected() {
        return selected;
    }

    public void select() {
        this.selected = true;
    }

    public void unselect() {
        this.selected = false;
    }

    public void toggleSelection() {
        this.selected = !selected;
    }

    @Override
    public String getId() {
        return ID;
    }
}
