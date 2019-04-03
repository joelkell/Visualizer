package ie.dit;

public abstract class Button extends UIElement
{
    protected float size;
    public Button(Visualizer visualizer, float x, float y, float size)
    {
        super(visualizer, x, y);
        this.size = size;
    }

    public abstract void render();

    public abstract void update();
}