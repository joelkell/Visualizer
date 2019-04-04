/**
 * Button which allows user to choose a song to play from anywhere on there computer
 */

package ie.dit;
public class ChooseSongButton extends Button
{
    private float length;
    private float width;
    private float verticalGap;
    private float horizontalGap;
    public ChooseSongButton(Visualizer visualizer, float x, float y, float size)//constructor
    {
        super(visualizer, x, y, size);
        length = size;
        width = size / 3;
        verticalGap = 10;
        horizontalGap = 4;
    }

    //draw button to screen
    public void render()
    {
        visualizer.colorMode(Visualizer.RGB);
        visualizer.fill(255,255,255);
        visualizer.stroke(0);
        visualizer.strokeWeight(4);
        visualizer.rect(pos.x - horizontalGap, pos.y - verticalGap,length,width);
        visualizer.textAlign(Visualizer.LEFT, Visualizer.TOP);
        visualizer.fill(0);
        visualizer.noStroke();
        visualizer.textFont(visualizer.ocra);
        visualizer.textSize(20);
        visualizer.text("Choose Song", pos.x, pos.y,length,width);
    }

    //select song if button is clicked
    public void update()
    {
        if(clicked == true)
        {
            visualizer.selectSong();
            clicked = false;
        }
    }

    //sets button to clicked
    public void isClicked() 
    {
        clicked = true;
    }
}