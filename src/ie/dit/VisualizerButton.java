/**
 * Button which toggles if visualizer is Visualizer or Spiderman
 */

package ie.dit;

import processing.core.PImage;

public class VisualizerButton extends Button
{
    PImage spiderman;
    public VisualizerButton(Visualizer visualizer, float x, float y, float size)//constructor
    {
        super(visualizer, x, y, size);
        length = size;
        height = size;
        spiderman = visualizer.loadImage("frame_159_delay-0.07s.png");
    }

    //draw button to screen
    public void render()
    {
        visualizer.colorMode(Visualizer.RGB);
        if(visualizer.getToggle() == true)//Spiderman
        {
            visualizer.image(spiderman, pos.x, pos.y, size, size);
        }
        else//colour Grid
        {
            visualizer.noStroke();
            visualizer.fill(255,0,0);
            visualizer.rect(pos.x, pos.y, size/2,size/2);
            visualizer.fill(0,255,0);
            visualizer.rect(pos.x, pos.y + size/2, size/2,size/2);
            visualizer.fill(0,0,255);
            visualizer.rect(pos.x + size/2, pos.y, size/2,size/2);
            visualizer.fill(255,255,0);
            visualizer.rect(pos.x + size/2, pos.y + size/2, size/2,size/2);
        }

        visualizer.noFill();
        visualizer.stroke(0);
        visualizer.strokeWeight(2);
        //Border
        visualizer.rect(pos.x, pos.y, size,size);
    }

    //toggles fullscreen when button is clicked
    public void update()
    {
        if(clicked == true)
        {
            if(visualizer.song!=null)
            {
                visualizer.toggleBackground();
                clicked = false;
            }
        }
    }

    //sets button to clicked
    public void isClicked() 
    {
        clicked = true;
    }
}