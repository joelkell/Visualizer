/**
 * Button which allows user to fast forward song being played
 */

package ie.dit;
public class FastForward extends Button
{
    public FastForward(Visualizer visualizer, float x, float y, float size)//constructor
    {
        super(visualizer, x, y, size);
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

        visualizer.noStroke();
        visualizer.fill(0);
        //triangle 1
        float x1 = Visualizer.map((float) Math.cos(Visualizer.TWO_PI / 3),0,1,0,size/2);
        float y1 = Visualizer.map((float) Math.sin(Visualizer.TWO_PI / 3),0,1,0,size/2);
        float x2 = Visualizer.map((float) Math.cos(2 * Visualizer.TWO_PI / 3),0,1,0,size/2);
        float y2 = Visualizer.map((float) Math.sin(2 * Visualizer.TWO_PI / 3),0,1,0,size/2);
        float x3 = x1 + (Visualizer.dist(x1, 0, size/2, 0)/2);
        float y3 = 0;
        visualizer.triangle(x1,y1,x2,y2,x3,y3);
        //triangle 2
        float x4 =x3;
        float y4 = y1;
        float x5 = x3;
        float y5 = y2;
        float x6 = size / 2;
        float y6 = 0;
        visualizer.triangle(x4,y4,x5,y5,x6,y6);
        visualizer.strokeWeight(1);
        visualizer.popMatrix();
    }

    //performs fast forward if button is clicked
    public void update()
    {
        if(clicked == true)
        {
            if(visualizer.song!=null)
            {
                visualizer.song.skip(1000);
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