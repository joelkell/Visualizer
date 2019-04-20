/**
  * 
  */

package ie.dit;

import java.util.Arrays;

public class VBackground extends UIElement
{
    private float c1,c2,c3;
    private int index;
    private int[] colours;
    private float gap = 100;
    public VBackground(Visualizer visualizer, float x, float y, int[] colours)//constructor
    {
        super(visualizer, x, y);
        c1 = colours[0];
        c2 = colours[1];
        c3 = colours[2];
        this.colours = colours;
        index = 0;
    }

    //draw  to screen
    public void render()
    {
        visualizer.stroke(0);
        visualizer.strokeWeight(4);
        visualizer.fill(c1,c2,c3);
        visualizer.rect(gap,gap,visualizer.width - 2 * gap,visualizer.height - (2 * gap));
    }

    //
    public void update()
    {
        c1 = Visualizer.lerp(c1, (float)colours[(index + 3) % 30], 0.05f);
        c2 = Visualizer.lerp(c2, (float)colours[(index + 4) % 30], 0.05f);
        c3 = Visualizer.lerp(c3, (float)colours[(index + 5) % 30], 0.05f);
        if(Math.round(c1) == colours[(index + 3) % 30])
        {
            index += 3;
        }
    }
}