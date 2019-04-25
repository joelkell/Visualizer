/**
  * 
  */

package ie.dit;

public class VBackgroundBox extends UIElement
{
    VBackground vb;
    private int index;
    private float r, g, b;
    private float vertGap, horGap;
    public VBackgroundBox(Visualizer visualizer, VBackground vb, float x, float y, int position)//constructor
    {
        super(visualizer, x, y);
        this.vb = vb;
        index = (position * 3);
        r = visualizer.colours[(position * 3)];
        g = visualizer.colours[(position * 3) + 1];
        b = visualizer.colours[(position * 3) + 2];
    }

    //draw  to screen
    public void render()
    {
        vertGap = (visualizer.height - (2* vb.getGap()))/2;
        horGap = (visualizer.width - (2 * vb.getGap())) / (float)(visualizer.numColours/2);
        if(pos.y > 1)
        {
            pos.y = vertGap;
        }
        visualizer.fill(r,g,b);
        visualizer.noStroke();
        visualizer.rect(vb.getGap() + (pos.x * horGap),vb.getGap() + pos.y, horGap, vertGap);
    }

    //update background
    public void update()
    {
        //Updates fill colour
        r = Visualizer.lerp(r, (float)visualizer.colours[(index + 3)%30], 0.05f);
        g = Visualizer.lerp(g, (float)visualizer.colours[(index + 4)%30], 0.05f);
        b = Visualizer.lerp(b, (float)visualizer.colours[(index + 5)%30], 0.05f);
        if(Math.round(r) == visualizer.colours[(index + 3) % visualizer.colours.length] && Math.round(g) == visualizer.colours[(index + 4) % visualizer.colours.length] && Math.round(b) == visualizer.colours[(index + 5) % visualizer.colours.length])
        {
            index += 3;
        } 
        if(index == 30)
        {
            index = 0;
        }      
    }
}