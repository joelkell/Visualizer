/**
  * 
  */

package ie.dit;

import java.util.ArrayList;

public class VBackground extends UIElement
{
    private float r, g, b, r2, g2, b2;
    private int index, index2, index3;
    private int[] colours;
    private float gap = 100;
    private float vertGap;
    private float horGap;
    private float timePassed;
    private boolean fullscreen;

    private ArrayList<VBackgroundBox> boxes = new ArrayList<VBackgroundBox>();

    public VBackground(Visualizer visualizer, float x, float y, int[] colours)//constructor
    {
        super(visualizer, x, y);
        r = colours[0];
        g = colours[1];
        b = colours[2];
        this.colours = colours;
        index = 0;
        index2 = 0;
        index3 = 0;
        timePassed = 0;
        fullscreen = false;

        for(int i = 0; i < visualizer.numColours; i++)
        {
            float y2;
            float x2 = i % 5;
            if(i < 5)
            {
                y2 = 0;
            }
            else
            {
                y2 = 2;
            }
            boxes.add(new VBackgroundBox(visualizer, this, x2, y2, i));
        }
    }

    //draw  to screen
    public void render()
    {
        visualizer.stroke(0);//black border
        visualizer.strokeWeight(4);
        // visualizer.fill(r,g,b);//fill colour
        visualizer.fill(255);
        visualizer.rect(gap,gap,visualizer.width - (2 * gap),visualizer.height - (2 * gap));

        //Static colours with loops
        // vertGap = (visualizer.height - (2* gap))/2;
        // horGap = (visualizer.width - (2 * gap)) / (float)(visualizer.numColours/2);
        // visualizer.noStroke();
        // for(int i = 0; i < visualizer.numColours/2; i++)//top row
        // {
        //     r = colours[(i * 3)];
        //     g = colours[(i * 3) + 1];
        //     b = colours[(i * 3) + 2];
        //     visualizer.fill(r,g,b);
        //     visualizer.rect(gap  + (i * horGap), gap, horGap, vertGap);
        //     visualizer.fill(255);
        //     visualizer.text("R: " + r, gap  + (i * horGap), gap);
        //     visualizer.text("G: " + g, gap  + (i * horGap), gap + 20);
        //     visualizer.text("B: " + b, gap  + (i * horGap), gap + 40);

        //     r2 = colours[(i * 3) + 15];
        //     g2 = colours[(i * 3) + 16];
        //     b2 = colours[(i * 3) + 17];
        //     visualizer.fill(r2,g2,b2);
        //     visualizer.rect(gap  + (i * horGap), gap + vertGap, horGap, vertGap);
        //     visualizer.fill(255);
        //     visualizer.text("R: " + r2, gap  + (i * horGap), gap + vertGap);
        //     visualizer.text("G: " + g2, gap  + (i * horGap), gap + vertGap + 20);
        //     visualizer.text("B: " + b2, gap  + (i * horGap), gap + vertGap + 40);
        // }

        for(int i = 0; i < boxes.size(); i++)
        {
            VBackgroundBox vBox = boxes.get(i);
            vBox.render();
            vBox.update();
        }
    }

    //update background
    public void update()
    {
        //Updates fill colour
        r = Visualizer.lerp(r, (float)colours[(index + 3)], 0.05f);
        g = Visualizer.lerp(g, (float)colours[(index + 4)], 0.05f);
        b = Visualizer.lerp(b, (float)colours[(index + 5)], 0.05f);
        if(Math.round(r) == colours[(index + 3) % colours.length] && Math.round(g) == colours[(index + 4) % colours.length] && Math.round(b) == colours[(index + 5) % colours.length])
        {
            index += 3;
        }
        if(index == 27)
        {
            index = 0;
        }

        //Adds more circles every 5 seconds while song is playing
        timePassed += visualizer.timeDelta;
        if(timePassed > 5.0f && visualizer.song.isPlaying())
        {
            float radius = visualizer.circleSizes[index3%4];
            int type = index3%4;
            float y = visualizer.random(gap, visualizer.height - gap - (2 * radius));
            for(int j = visualizer.uiElements.size() - 4; j < visualizer.uiElements.size(); j++)
            {
                Circle ui = (Circle) visualizer.uiElements.get(j);
                if(y < ui.pos.y + (2 * radius) && y > ui.pos.y - (2 * radius))
                {
                    y = visualizer.random(gap, visualizer.height - gap - (2 * radius));
                    j = visualizer.uiElements.size() - 4;
                }
            }
            visualizer.uiElements.add(new Circle(visualizer, this, visualizer.fourier, visualizer.width - gap - (2 * radius), y, colours[(3 * index2)], colours[(3 * index2) + 1], colours[(3 * index2) + 2], radius, type));
            index2++;
            if(index2 > 9)
            {
                index2 = 0;
            }
            timePassed = 0;
            index3++;
        }
    }

    public void toggleFullscreen()
    {
        fullscreen = !fullscreen;

        //toggles fullscreen
        if(fullscreen == false)
        {
            gap = 100;

            //move circles in proportionately
            for(int i = 0; i < visualizer.uiElements.size(); i++)
            {
                Circle ui = (Circle) visualizer.uiElements.get(i);
                ui.pos.x = Visualizer.map(ui.pos.x, 0, visualizer.width, gap, visualizer.width - gap - (2 * ui.getRadius()));
                ui.pos.y = Visualizer.map(ui.pos.y, 0, visualizer.height - (2 * ui.getRadius()), gap, visualizer.height - gap - (2 * ui.getRadius()));
            }
        }
        else
        {
            //move circles out proportionately
            for(int i = 0; i < visualizer.uiElements.size(); i++)
            {
                Circle ui = (Circle) visualizer.uiElements.get(i);
                ui.pos.x = Visualizer.map(ui.pos.x, gap, visualizer.width - gap - (2 * ui.getRadius()), 0, visualizer.width);
                ui.pos.y = Visualizer.map(ui.pos.y, gap, visualizer.height - gap - (2 * ui.getRadius()), 0, visualizer.height  - (2 * ui.getRadius()));
            }

            gap = 0;
        }
    }

    public void setGap(float gap)
    {
        this.gap = gap;
    }

    public float getGap()
    {
        return gap;
    }

    public boolean getFullscreen()
    {
        return fullscreen;
    }
}