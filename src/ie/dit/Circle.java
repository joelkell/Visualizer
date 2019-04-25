/**
  * Draw circle to screen and add lines from circle to circle
  */

package ie.dit;

import java.util.ArrayList;

public class Circle extends UIElement
{
    private float radius;
    private float dy;
    private int r,g,b;
    VBackground vb;
    Fourier fourier;
    int createLine;
    int chance;
    int type;
    private int numLinesConnected;
    private boolean lineAvailable;

    public ArrayList<Line> lines = new ArrayList<Line>();
    
    public Circle(Visualizer visualizer, VBackground vb, Fourier fourier, float x, float y, int r, int g, int b, float radius, int type)//constructor
    {
        super(visualizer, x, y);
        this.fourier = fourier;
        this.vb = vb;
        this.radius = radius;
        this.type = type;
        this.r = r;
        this.g = g;
        this.b = b;
        createLine = 0;
        chance = 100;
        numLinesConnected = 0;
        dy = visualizer.random(-0.2f,0.2f);
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
        //update radius size
        if(type == 0)//bass band circles 0Hz - 200Hz
        {
            if(fourier.getAverage(fourier.bass) > 0)
            {
                radius = Visualizer.lerp(radius, fourier.getAverage(fourier.bass),0.2f);
            }
            else
            {
                radius = Visualizer.lerp(radius, 20,0.02f);
            }
        }
        if(type == 1)//Low Mids 200Hz - 1kHz
        {
            if(fourier.getAverage(fourier.lowMid) > 0)
            {
                radius = Visualizer.lerp(radius, fourier.getAverage(fourier.lowMid)*2,0.2f);
            }
            else
            {
                radius = Visualizer.lerp(radius, 20,0.02f);
            }
        }
        if(type == 2)//High Mids 1kHz - 5kHz
        {
            if(fourier.getAverage(fourier.highMid) > 0)
            {
                radius = Visualizer.lerp(radius, fourier.getAverage(fourier.highMid)*10,0.2f);
            }
            else
            {
                radius = Visualizer.lerp(radius, 20,0.02f);
            }
        }
        if(type == 3)//Treble 5kHz - 20kHz
        {
            if(fourier.getAverage(fourier.treble) > 0)
            {
                radius = Visualizer.lerp(radius, fourier.getAverage(fourier.treble)*10,0.2f);
            }
            else
            {
                radius = Visualizer.lerp(radius, 20,0.02f);
            }
        }
        if(radius < 8)//min size
        {
            radius = 8;
        }
        if(radius > 30)//max size
        {
            radius = 30;
        }
        
        //get number of lines connected to circle
        numLinesConnected = lines.size();

        //Circles move to left when music is playing and by dy on y axis
        if(visualizer.song.isPlaying())
        {
            pos.x -= 0.25;
            pos.y += dy;
        }

        //remove circles off side of screen
        if(pos.x < vb.getGap())
        {
            visualizer.uiElements.remove(this);
        }
        
        //wrap circles vertically
        if(vb.getFullscreen())
        {
            if(pos.y < vb.getGap())
            {
                pos.y = visualizer.height - vb.getGap();
            }
            if(pos.y > visualizer.height - vb.getGap())
            {
                pos.y = vb.getGap();
            }
        }
        else
        {
            if(pos.y < vb.getGap())
            {
                pos.y = visualizer.height - vb.getGap() - (2 * radius);
            }
            if(pos.y > visualizer.height - vb.getGap() - (2 * radius))
            {
                pos.y = vb.getGap();
            }
        }

        //create Line
        createLine = (int)visualizer.random(0, chance);
        if(createLine == 10 && numLinesConnected < 2)//if lands on chance and has less than 2 connected lines currently
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
                    if(circle.numLinesConnected >= 2)
                    {
                        checked++;
                    }
                    if(visualizer.uiElements.size() - checked <= 1)//if all other circles have full lines connected then break from loop
                    {
                        lineAvailable = false;
                    }
                }
            }while((c == this || c.numLinesConnected > 1) && lineAvailable);

            if(checked != visualizer.uiElements.size())//add line if new circle chosen
            {
                Line l = new Line(visualizer, this, c);
                lines.add(l);
                c.lines.add(l);
                chance *= 10 * (numLinesConnected + 1);
            }
        }
    }

    //display lines to screen
    public void displayLines()
    {
        if(lines.size() > 0)//prevent out of bounds exception
        {
         
            for(int i = lines.size() -1; i >= 0; i--)
            {
                Line l = lines.get(i);
                l.render();
                l.update();
            }   
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