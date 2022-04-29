package graphics.shapes.attributes;

public class EntranceAttributes extends Attributes {
    public static final String ID = "entrance";
    private boolean entered = false;
    public boolean isEntered()
    {
        return entered;
    }

    public void enter()
    {
        this.entered=true;
        System.out.println("enter() utilisé");
    }

    public void exit()
    {
        this.entered=false;
    }

    @Override
    public String getId() {
        return ID;
    }
}
