/**
  * Draw circle to screen and add lines from circle to circle
  */

package ie.dit;

import java.util.ArrayList;

public class Circle extends UIElement
{
    private float radius;
    private int r,g,b;
    VBackground vb;
    int createLine;
    int chance;
    private int numLinesConnected;
    private boolean lineAvailable;

    public ArrayList<Line> lines = new ArrayList<Line>();
    
    public Circle(Visualizer visualizer, VBackground vb, float x, float y, int r, int g, int b, float radius)//constructor
    {
        super(visualizer, x, y);
        this.vb = vb;
        this.radius = radius;
        this.r = r;
        this.g = g;
        this.b = b;
        createLine = 0;
        chance = 100;
        numLinesConnected = 0;
    }

    //draw to screen
    public void render()
    {
        visualizer.pushMatrix();
        visualizer.translate(pos.x, pos.y);

        //colour and fill
        visualizer.colorMode(Visualizer.RGB);
        visualizer.fill(r,g,b);
        visualizer.stroke(0);
        visualizer.strokeWeight(2);

        //circle
        visualizer.ellipseMode(Visualizer.CORNER);
        visualizer.ellipse(0, 0, 2 * radius, 2 * radius);
        visualizer.popMatrix();
    }

    //update circle
    public void update()
    {
        //get number of lines connected to circle
        numLinesConnected = lines.size();

        //Circles move to left when music is playing
        if(visualizer.song.isPlaying())
        {
            pos.x -= 0.2;
        }

        //remove circles off side of screen
        if(pos.x < vb.getGap())
        {
            visualizer.uiElements.remove(this);
        }

        //create Line
        createLine = (int)visualizer.random(0, chance);
        if(createLine == 10 && numLinesConnected < 3)//if lands on chance and has less than 3 connected lines currently
        {
            int checked;
            Circle c;
            do//continue to search for other circles
            {
                c = (Circle) visualizer.uiElements.get((int)visualizer.random(0, visualizer.uiElements.size()));
                checked = 0;
                lineAvailable = true;
                for(int i = 0; i < visualizer.uiElements.size(); i++)
                {
                    Circle circle = (Circle) visualizer.uiElements.get(i);
                    if(circle.numLinesConnected >= 3)
                    {
                        checked++;
                    }
                    if(visualizer.uiElements.size() - checked <= 1)//if all other circles have full lines connected then break from loop
                    {
                        lineAvailable = false;
                    }
                }
            }while((c == this || c.numLinesConnected > 2) && lineAvailable);

            if(checked != visualizer.uiElements.size())//add line if new circle chosen
            {
                Line l = new Line(visualizer, this, c);
                lines.add(l);
                c.lines.add(l);
                chance *= 10;
            }
        }
    }

    //display lines to screen
    public void displayLines()
    {
        for(int i = lines.size() -1; i >= 0; i--)
        {
            Line l = lines.get(i);
            l.render();
            l.update();
        }
    }

    public void setRadius(float radius)
    {
        this.radius = radius;
    }

    public float getRadius()
    {
        return radius;
    }
}