/**
 * 
 */

package ie.dit;
public class Line extends UIElement
{
    VBackground vb;
    Circle c1, c2;
    private float x1, y1;
    private float x2, y2;
    private float dx, dy;
    private float speed;
    private boolean removing;
    public Line(Visualizer visualizer, VBackground vb, Circle c1, Circle c2)//constructor
    {
        super(visualizer, 0, 0);
        this.vb = vb;
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

    //draw to screen
    public void render()
    {
        //line
        visualizer.stroke(255);
        visualizer.strokeWeight(4);
        visualizer.line(x1, y1, dx, dy);
    }

    //
    public void update()
    {
        //update position of line
        if(visualizer.uiElements.contains(c1) && visualizer.uiElements.contains(c2))
        {
            x1 = c1.pos.x + c1.getRadius();
            y1 = c1.pos.y + c1.getRadius();
            x2 = c2.pos.x + c2.getRadius();
            y2 = c2.pos.y + c2.getRadius();
            if(Visualizer.dist(x2,y2,dx, dy) > c2.getRadius())
            {
                dx = Visualizer.lerp(dx, x2, speed);
                dy = Visualizer.lerp(dy, y2, speed);
                speed += 0.0002f;
            }
            else
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
            if(Visualizer.dist(x1,y1,dx,dy) > c1.getRadius())
            {
                dx = Visualizer.lerp(dx, x1, speed);
                dy = Visualizer.lerp(dy, y1, speed);
            }
            else
            {
                dx = x1;
                dy = y1;
            }
        }
        else if(!visualizer.uiElements.contains(c1) && visualizer.uiElements.contains(c2))//if c1 is removed
        {
            if(removing == false)
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
            if(Visualizer.dist(x1,y1,dx,dy) > c2.getRadius())
            {
                dx = Visualizer.lerp(dx, x1, speed);
                dy = Visualizer.lerp(dy, y1, speed);
            }
            else
            {
                dx = x1;
                dy = y1;
            }
        } 
        
        //remove line
        if(!visualizer.uiElements.contains(c1) && !visualizer.uiElements.contains(c2))
        {
            System.out.println("REMOVING: " + this);
            c1.lines.remove(this);
            c2.lines.remove(this);
        }

        if(vb.getFullscreen())
        {

        }
    }
}