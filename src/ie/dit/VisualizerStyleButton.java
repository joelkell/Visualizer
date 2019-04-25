/**
  * Button which toggles if visualizer is Visualizer or Spiderman
  */

package ie.dit;

public class VisualizerStyleButton extends Button
{
    public VisualizerStyleButton(Visualizer visualizer, float x, float y, float size)//constructor
    {
        super(visualizer, x, y, size);
        length = size;
        height = size;
    }

    //draw button to screen
    public void render()
    {
        visualizer.colorMode(Visualizer.RGB);
        if(visualizer.song!=null)
        {
            if(visualizer.background.getSolid() == false)// Solid
            {
                visualizer.fill(visualizer.colours[6],visualizer.colours[7],visualizer.colours[8]);
                visualizer.rect(pos.x, pos.y, size,size);
            }
            else//colour Grid
            {
                visualizer.noStroke();
                visualizer.fill(visualizer.colours[0],visualizer.colours[1],visualizer.colours[2]);
                visualizer.rect(pos.x, pos.y, size/2,size/2);
                visualizer.fill(visualizer.colours[3],visualizer.colours[4],visualizer.colours[5]);
                visualizer.rect(pos.x, pos.y + size/2, size/2,size/2);
                visualizer.fill(visualizer.colours[6],visualizer.colours[7],visualizer.colours[8]);
                visualizer.rect(pos.x + size/2, pos.y, size/2,size/2);
                visualizer.fill(visualizer.colours[9],visualizer.colours[10],visualizer.colours[11]);
                visualizer.rect(pos.x + size/2, pos.y + size/2, size/2,size/2);
            }
        }
        else
        {
            visualizer.fill(255);
            visualizer.rect(pos.x, pos.y, size,size);
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
                visualizer.background.toggleSolid();
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