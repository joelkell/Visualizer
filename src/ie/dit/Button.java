package ie.dit;

public abstract class Button extends UIElement
{
    protected float size;
    protected boolean clicked;
    public Button(Visualizer visualizer, float x, float y, float size)
    {
        super(visualizer, x, y);
        this.size = size;
        clicked = false;
    }

    public abstract void render();

    public abstract void update();

    public abstract void isClicked();
}