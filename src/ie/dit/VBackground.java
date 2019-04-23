/**
  * 
  */

package ie.dit;

public class VBackground extends UIElement
{
    private float r,g,b;
    private int index;
    private int[] colours;
    private float gap = 100;
    public VBackground(Visualizer visualizer, float x, float y, int[] colours)//constructor
    {
        super(visualizer, x, y);
        r = colours[0];
        g = colours[1];
        b = colours[2];
        this.colours = colours;
        index = 0;
    }

    //draw  to screen
    public void render()
    {
        visualizer.stroke(0);//black border
        visualizer.strokeWeight(4);
        visualizer.fill(r,g,b);//fill colour
        visualizer.rect(gap,gap,visualizer.width - (2 * gap),visualizer.height - (2 * gap));
    }

    //updates fill colour  
    public void update()
    {
        r = Visualizer.lerp(r, (float)colours[(index + 3)], 0.05f);
        g = Visualizer.lerp(g, (float)colours[(index + 4)], 0.05f);
        b = Visualizer.lerp(b, (float)colours[(index + 5)], 0.05f);
        if(Math.round(r) == colours[(index + 3) % 30] && Math.round(g) == colours[(index + 4) % 30] && Math.round(b) == colours[(index + 5) % 30])
        {
            index += 3;
        }
        if(index == 27)
        {
            index = 0;
        }
    }

    public void setGap(float gap)
    {
        this.gap = gap;
    }

    public float getGap()
    {
        return gap;
    }
}