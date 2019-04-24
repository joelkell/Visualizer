/**
 * 
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

    //
    public void update()
    {
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
        if(createLine == 10)
        {
            Circle c;
            do
            {
                c = (Circle) visualizer.uiElements.get((int)visualizer.random(0, visualizer.uiElements.size()));
            }while(c == this);
            Line l = new Line(visualizer, vb, this, c);
            lines.add(l);
            c.lines.add(l);
            //lines.add(new Line(visualizer, vb, this, c, this));
            //c.lines.add(new Line(visualizer, vb, c, this, this));

            chance *= 10;
        }
    }

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