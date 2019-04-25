/**
 * Button which toggles if visualizer if fullscreen
 */

package ie.dit;
public class Fullscreen extends Button
{
    private float len, wid;
    public Fullscreen(Visualizer visualizer, float x, float y, float size)//constructor
    {
        super(visualizer, x, y, size);
        length = size;
        height = size;
        len = size / 3;
        wid = size / 6;
    }

    //draw button to screen
    public void render()
    {
        visualizer.colorMode(Visualizer.RGB);
        visualizer.fill(0);
        visualizer.noStroke();

        //Top Left Corner
        visualizer.rect(pos.x, pos.y, len, wid);
        visualizer.rect(pos.x, pos.y ,wid, len);

        //Top Right Corner
        visualizer.rect(pos.x + size, pos.y, -len, wid);
        visualizer.rect(pos.x + size, pos.y, -wid, len);

        //Bottom Left Corner
        visualizer.rect(pos.x, pos.y + size, len, -wid);
        visualizer.rect(pos.x, pos.y + size, wid, -len);

        //Bottom Right Corner
        visualizer.rect(pos.x + size, pos.y + size, -len, -wid);
        visualizer.rect(pos.x + size, pos.y + size, -wid, -len);
    }

    //toggles fullscreen when button is clicked
    public void update()
    {
        if(clicked == true)
        {
            if(visualizer.song!=null)
            {
                visualizer.background.toggleFullscreen();
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