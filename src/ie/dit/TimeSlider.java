/**
 * Slider which allows user to change position of song
 */

package ie.dit;
public class TimeSlider extends Slider
{
    float i = 0;
    public TimeSlider(Visualizer visualizer, float x, float y, float x2, float y2, float size)//constructor
    {
        super(visualizer, x, y, x2, y2, size);
    }

    //draws Slider to screen
    public void render()
    {
        //colour mode
        visualizer.colorMode(Visualizer.RGB);
        visualizer.stroke(0);
        visualizer.strokeWeight(4);
        //line
        visualizer.line(pos.x,pos.y,pos2.x,pos2.y);
        //circle
        visualizer.fill(255);
        visualizer.ellipseMode(Visualizer.CORNER);
        visualizer.ellipse(buttonX - size/2, pos.y - size/2, size, size);
    }

    //Update circle and volume
    public void update()
    {
        if(visualizer.song != null)
        {
            buttonX = Visualizer.map(visualizer.song.position(), 0, visualizer.song.length(), pos.x, pos2.x);
        }
    }

    //sets button to clicked
    public void isClicked() 
    {
        clicked = true;
    }
}