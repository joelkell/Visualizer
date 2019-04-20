/**
 * 
 */

package ie.dit;

import processing.core.PImage;

public class Spiderman extends UIElement
{
    PImage[] spiderman = new PImage[183];
    int index;

    public Spiderman(Visualizer visualizer, float x, float y)//constructor
    {
        super(visualizer, x, y);
        index = 0;
        spiderman[0] = visualizer.loadImage("frame_000_delay-0.1s.png");
        for(int i = 1; i < 183; i++)
        {
            spiderman[i] = visualizer.loadImage("frame_" + String.format("%03d" , i) + "_delay-0.07s.png");
        }
    }

    //draw  to screen
    public void render()
    {
        visualizer.imageMode(Visualizer.CENTER);
        visualizer.image(spiderman[index],pos.x,pos.y,visualizer.width/2, visualizer.height/2);
        index++;
        if(index == 183)
        {
            index = 0;
        }
    }

    //
    public void update()
    {
    }
}