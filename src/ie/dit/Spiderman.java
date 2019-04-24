/**
 * 
 */

package ie.dit;

import processing.core.PImage;

public class Spiderman extends UIElement
{
    PImage[] spiderman = new PImage[549];
    int index;
    int j, k;

    public Spiderman(Visualizer visualizer, float x, float y)//constructor
    {
        super(visualizer, x, y);
        index = 0;
        spiderman[0] = visualizer.loadImage("frame_000_delay-0.1s.png");
        spiderman[1] = visualizer.loadImage("frame_000_delay-0.1s.png");
        spiderman[2] = visualizer.loadImage("frame_000_delay-0.1s.png");
        j = 1;
        k = 0;
        for(int i = 3; i < 549; i++)
        {
            spiderman[i] = visualizer.loadImage("frame_" + String.format("%03d" , j) + "_delay-0.07s.png");
            k++;
            if(k == 3)
            {
                k = 0;
                j++;
            }
        }
    }

    //draw  to screen
    public void render()
    {
        visualizer.imageMode(Visualizer.CENTER);
        visualizer.image(spiderman[index],pos.x,pos.y,visualizer.width/2, visualizer.height/2);
    }

    //
    public void update()
    {
        index++;
        if(index == 549)
        {
            index = 0;
        }
    }
}