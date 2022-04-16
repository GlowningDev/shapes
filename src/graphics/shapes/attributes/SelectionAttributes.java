package graphics.shapes.attributes;

public class SelectionAttributes extends Attributes {
    public static final String ID = "selection";
    private boolean selected = false;
    private boolean entered = false;

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

    public boolean isEntered(){ return entered;}

    public void enter(){this.entered=true;}

    public void exit(){this.entered=false;}

    @Override
    public String getId() {
        return ID;
    }
}
