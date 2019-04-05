/**
 * Button which toggles between playing or paused
 */

package ie.dit;
public class PlayPause extends Button
{
    private float verticalGap;
    private float horizontalGap;
    public PlayPause(Visualizer visualizer, float x, float y, float size)//constructor
    {
        super(visualizer, x, y, size);
        length = size;
        height = size;
        horizontalGap = size/5;
        verticalGap = size/6;
    }

    //draw button to screen
    public void render()
    {
        visualizer.pushMatrix();
        visualizer.translate(pos.x, pos.y);
        //colour and fill
        visualizer.colorMode(Visualizer.RGB);
        visualizer.noFill();
        visualizer.stroke(0);
        visualizer.strokeWeight(4);

        //outer circle
        visualizer.ellipse(0,0,size,size);

        visualizer.fill(0);
        visualizer.noStroke();
        if(visualizer.isFileChosen() == true)//check if song selected
        {
            if(visualizer.song.isPlaying())//pause button
            {
                songPlaying();
            }
            else//play button
            {
                songPaused();
            }  
        }
        else//play button
        {
            songPaused();
        }
        visualizer.strokeWeight(1);
        visualizer.popMatrix();
    }

    //draws pause button
    public void songPlaying()
    {
        visualizer.beginShape(Visualizer.QUADS);
        //left rectangle
        visualizer.vertex(0 - 1.5f * horizontalGap, 0 - 2 * verticalGap);
        visualizer.vertex(0 - 1.5f * horizontalGap, 0 + 2 * verticalGap);
        visualizer.vertex(0 - 0.5f * horizontalGap, 0 + 2 * verticalGap);
        visualizer.vertex(0 - 0.5f * horizontalGap, 0 - 2 * verticalGap);
        //right rectangle
        visualizer.vertex(0 + 1.5f * horizontalGap, 0 - 2 * verticalGap);
        visualizer.vertex(0 + 1.5f * horizontalGap, 0 + 2 * verticalGap);
        visualizer.vertex(0 + 0.5f * horizontalGap, 0 + 2 * verticalGap);
        visualizer.vertex(0 + 0.5f * horizontalGap, 0 - 2 * verticalGap);
        visualizer.endShape(Visualizer.CLOSE);
    }

    //draws play button
    public void songPaused()
    {
        float x1 = Visualizer.map((float) Math.cos(Visualizer.TWO_PI / 3),0,1,0,size/2);
        float y1 = Visualizer.map((float) Math.sin(Visualizer.TWO_PI / 3),0,1,0,size/2);
        float x2 = Visualizer.map((float) Math.cos(2 * Visualizer.TWO_PI / 3),0,1,0,size/2);
        float y2 = Visualizer.map((float) Math.sin(2 * Visualizer.TWO_PI / 3),0,1,0,size/2);
        float x3 = size / 2;
        float y3 = 0;
        visualizer.triangle(x1,y1,x2,y2,x3,y3);
    }

    //toggles playing or paused when button is clicked
    public void update()
    {
        if(clicked == true)
        {
            if(visualizer.song!=null)
            {
                visualizer.togglePlay();
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