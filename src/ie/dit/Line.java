/**
  * Line drawn from one circle to another
  */

package ie.dit;
public class Line extends UIElement
{
    Circle c1, c2;
    private float x1, y1;
    private float x2, y2;
    private float dx, dy;
    private float speed;
    private boolean removing;
    public Line(Visualizer visualizer, Circle c1, Circle c2)//constructor
    {
        super(visualizer, 0, 0);
        this.c1 = c1;
        this.c2 = c2;
        x1 = c1.pos.x + c1.getRadius();
        y1 = c1.pos.y + c1.getRadius();
        x2 = c2.pos.x + c2.getRadius();
        y2 = c2.pos.y + c2.getRadius();
        dx = x1;
        dy = y1;
        speed = 0.02f;
        removing = false;
    }

    //draw line to screen
    public void render()
    {
        //line
        visualizer.stroke(255);
        visualizer.strokeWeight(4);
        visualizer.line(x1, y1, dx, dy);
    }

    //update line
    public void update()
    {
        //update position of line when both circles are on screen
        if(visualizer.uiElements.contains(c1) && visualizer.uiElements.contains(c2))
        {
            x1 = c1.pos.x + c1.getRadius();
            y1 = c1.pos.y + c1.getRadius();
            x2 = c2.pos.x + c2.getRadius();
            y2 = c2.pos.y + c2.getRadius();
            if(Visualizer.dist(x2,y2,dx, dy) > c2.getRadius())//Move towards circle when not within radius
            {
                dx = Visualizer.lerp(dx, x2, speed);
                dy = Visualizer.lerp(dy, y2, speed);
                speed += 0.0002f;
            }
            else//snap to circle when inside radius
            {
                dx = c2.pos.x + c2.getRadius();
                dy = c2.pos.y + c2.getRadius();
            }
        }
        else if(visualizer.uiElements.contains(c1) && !visualizer.uiElements.contains(c2))//if c2 is removed
        {
            speed -= 0.0002f;
            if(speed < 0.02f)
            {
                speed = 0.02f;
            }
            x1 = c1.pos.x + c1.getRadius();
            y1 = c1.pos.y + c1.getRadius();
            if(Visualizer.dist(x1,y1,dx,dy) > c1.getRadius())//Move towards circle when not within radius
            {
                dx = Visualizer.lerp(dx, x1, speed);
                dy = Visualizer.lerp(dy, y1, speed);
            }
            else//snap to circle when inside radius
            {
                dx = x1;
                dy = y1;
            }
        }
        else if(!visualizer.uiElements.contains(c1) && visualizer.uiElements.contains(c2))//if c1 is removed
        {
            if(removing == false)//swap x and y positions of c1 and c2
            {
                dx = x1;
                dy = y1;
                removing = true;
            }
            speed -= 0.0002f;
            if(speed < 0.02f)
            {
                speed = 0.02f;
            }
            x1 = c2.pos.x + c2.getRadius();
            y1 = c2.pos.y + c2.getRadius();
            if(Visualizer.dist(x1,y1,dx,dy) > c2.getRadius())//Move towards circle when not within radius
            {
                dx = Visualizer.lerp(dx, x1, speed);
                dy = Visualizer.lerp(dy, y1, speed);
            }
            else//snap to circle when inside radius
            {
                dx = x1;
                dy = y1;
                c2.lines.remove(this);
            }
        } 
        
        //remove line from both array lists
        if(!visualizer.uiElements.contains(c1) && !visualizer.uiElements.contains(c2))
        {
            c1.lines.remove(this);
            c2.lines.remove(this);
        }
    }
}