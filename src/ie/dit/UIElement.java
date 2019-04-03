package ie.dit;

import processing.core.PVector;

public abstract class UIElement
{
    protected PVector pos;
    protected Visualizer visualizer;

    public UIElement(Visualizer visualizer, float x, float y)
    {
        this.visualizer = visualizer;
        pos = new PVector(x, y);
    }
    
    public abstract void render();

    public abstract void update();
}