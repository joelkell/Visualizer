/**
 * 
 */

package ie.dit;
public class Circle extends UIElement
{
    private float radius;
    private int r,g,b;
    VBackground vb;
    public Circle(Visualizer visualizer, VBackground vb, float x, float y, int r, int g, int b, float radius)//constructor
    {
        super(visualizer, x, y);
        this.vb = vb;
        this.radius = radius;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    //draw to screen
    public void render()
    {
        visualizer.pushMatrix();
        visualizer.translate(pos.x, pos.y);

        //colour and fill
        visualizer.colorMode(Visualizer.RGB);
        visualizer.fill(r,g,b);
        visualizer.stroke(0);
        visualizer.strokeWeight(2);

        //outer circle
        visualizer.ellipseMode(Visualizer.CORNER);
        visualizer.ellipse(0, 0, 2 * radius, 2 * radius);
        visualizer.popMatrix();
    }

    //
    public void update()
    {
        //Circles move to left when music is playing
        if(visualizer.song.isPlaying())
        {
            pos.x--;
        }

        //remove circles off side of screen
        if(pos.x < vb.getGap())
        {
            visualizer.uiElements.remove(this);
        }
    }

    public void setRadius(float radius)
    {
        this.radius = radius;
    }

    public float getRadius()
    {
        return radius;
    }
}