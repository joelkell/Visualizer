package ie.dit;

import processing.core.PVector;

public abstract class Slider extends Button
{
    protected PVector pos2;
    protected float buttonY;
    protected float buttonX;
    public Slider(Visualizer visualizer, float x1, float y1, float x2, float y2, float size)
    {
        super(visualizer, x1, y1, size);
        pos2 = new PVector(x2, y2);
        buttonY = y1;
        buttonX = x1;
    }

    public abstract void render();

    public abstract void update();

    public abstract void isClicked();
}