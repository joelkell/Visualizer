/**
  * Slider which allows user to change volume
  */

package ie.dit;
public class VolumeSlider extends Slider
{
    private int volume;
    public VolumeSlider(Visualizer visualizer, float x, float y, float x2, float y2, float size)//constructor
    {
        super(visualizer, x, y, x2, y2, size);
        volume = (int)Visualizer.map(buttonY, pos.y, pos2.y, 4, -80);
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
        visualizer.ellipse(pos.x - size/2, buttonY - size/2, size, size);
        //volume text
        visualizer.fill(0);
        visualizer.textAlign(Visualizer.CENTER, Visualizer.CENTER);
        visualizer.text(String.format("%02d" , (int)Visualizer.map(visualizer.getVolume(),-80,4,0,100)), pos2.x, pos2.y + 20);
    }

    //Update circle and volume
    public void update()
    {
        if(clicked == true)//move button and change colume when mouse pressed
        {
            buttonY = visualizer.mouseY;
            if(buttonY > pos2.y)
            {
                buttonY = pos2.y;
            }
            if(buttonY < pos.y)
            {
                buttonY = pos.y;
            }
            volume = (int)Visualizer.map(buttonY, pos.y, pos2.y, 4, -80);
            visualizer.setVolume(volume);
            if(visualizer.song!=null)
            {
                visualizer.song.setGain(volume);
            }

            clicked = false;
        }

        buttonY = Visualizer.map(visualizer.getVolume(), 4, -80, pos.y, pos2.y);//set buttonY to position of volume
    }

    //sets button to clicked
    public void isClicked() 
    {
        clicked = true;
    }
}