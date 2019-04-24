/**
  * 
  */

package ie.dit;

public class VBackground extends UIElement
{
    private float r,g,b;
    private int index, index2;
    private int[] colours;
    private float gap = 100;
    private float timePassed;
    private boolean fullscreen;
    public VBackground(Visualizer visualizer, float x, float y, int[] colours)//constructor
    {
        super(visualizer, x, y);
        r = colours[0];
        g = colours[1];
        b = colours[2];
        this.colours = colours;
        index = 0;
        index2 = 0;
        timePassed = 0;
        fullscreen = false;
    }

    //draw  to screen
    public void render()
    {
        visualizer.stroke(0);//black border
        visualizer.strokeWeight(4);
        // visualizer.fill(r,g,b);//fill colour
        visualizer.fill(200);
        visualizer.rect(gap,gap,visualizer.width - (2 * gap),visualizer.height - (2 * gap));
    }

    //update background
    public void update()
    {
        //Updates fill colour
        // r = Visualizer.lerp(r, (float)colours[(index + 3)], 0.05f);
        // g = Visualizer.lerp(g, (float)colours[(index + 4)], 0.05f);
        // b = Visualizer.lerp(b, (float)colours[(index + 5)], 0.05f);
        // if(Math.round(r) == colours[(index + 3) % 30] && Math.round(g) == colours[(index + 4) % 30] && Math.round(b) == colours[(index + 5) % 30])
        // {
        //     index += 3;
        // }
        // if(index == 27)
        // {
        //     index = 0;
        // }

        //Adds more circles to screen when removed or every 3 seconds when song is playing
        timePassed += visualizer.timeDelta;
        if(visualizer.uiElements.size() < 10 || timePassed > 3.0f && visualizer.song.isPlaying())
        {
            float radius = visualizer.random(10,20);
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
            visualizer.uiElements.add(new Circle(visualizer, this, visualizer.width - gap - (2 * radius), y, colours[(3 * index2)], colours[(3 * index2) + 1], colours[(3 * index2) + 2], radius));
            index2++;
            if(index2 > 9)
            {
                index2 = 0;
            }
            timePassed = 0;
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
}