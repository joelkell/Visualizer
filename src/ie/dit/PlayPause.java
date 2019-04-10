/**
 * Button which toggles between playing or paused
 */

package ie.dit;
public class PlayPause extends Button
{
    private float verticalGap;
    private float horizontalGap;
    private float x1, y1, x2, y2;
    public PlayPause(Visualizer visualizer, float x, float y, float size)//constructor
    {
        super(visualizer, x, y, size);
        length = size;
        height = size;
        horizontalGap = size/5;
        verticalGap = size/6;
        x1 = Visualizer.map((float) Math.cos(Visualizer.TWO_PI / 3),-1,0,0,size/2);
        y1 = Visualizer.map((float) Math.sin(Visualizer.TWO_PI / 3),-1,0,0,size/2);
        x2 = Visualizer.map((float) Math.cos(Visualizer.PI / 3),-1,0,0,size/2);
        y2 = Visualizer.map((float) Math.sin(2 * Visualizer.TWO_PI / 3),-1,0,0,size/2);
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
        visualizer.ellipseMode(Visualizer.CORNER);
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
        visualizer.vertex(x1, y1);
        visualizer.vertex(x1, y2);
        visualizer.vertex(x1 + horizontalGap, y2);
        visualizer.vertex(x1 + horizontalGap, y1);
        //right rectangle
        visualizer.vertex(x2, y1);
        visualizer.vertex(x2, y2);
        visualizer.vertex(x2 - horizontalGap, y2);
        visualizer.vertex(x2 - horizontalGap, y1);
        visualizer.endShape(Visualizer.CLOSE);
    }

    //draws play button
    public void songPaused()
    {
        float x3 = size;
        float y3 = size/2;
        visualizer.triangle(x1,y1,x1,y2,x3,y3);
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