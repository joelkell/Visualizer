package ie.dit;

public abstract class Button extends UIElement
{
    protected float size;
    protected float length;
    protected float height;
    protected boolean clicked;
    public Button(Visualizer visualizer, float x, float y, float size)//Default Constructor
    {
        super(visualizer, x, y);
        this.size = size;
        clicked = false;
    }

    public abstract void render();

    public abstract void update();

    public abstract void isClicked();
}