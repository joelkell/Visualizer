package ie.dit;
public class RewindButton extends Button
{
    public RewindButton(Visualizer visualizer, float x, float y, float size)
    {
        super(visualizer, x, y, size);
    }

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
        //line
        float x1 = Visualizer.map((float) Math.cos(Visualizer.TWO_PI / 3),0,1,0,size/2);
        float y1 = Visualizer.map((float) Math.sin(Visualizer.TWO_PI / 3),0,1,0,size/2);
        float x2 = Visualizer.map((float) Math.cos(2 * Visualizer.TWO_PI / 3),0,1,0,size/2);
        float y2 = Visualizer.map((float) Math.sin(2 * Visualizer.TWO_PI / 3),0,1,0,size/2);
        visualizer.line(x1,y1,x2,y2);
        //triangle
        visualizer.noStroke();
        float x3 = Visualizer.map((float) Math.cos(Visualizer.PI / 3),0,1,0,size/2);
        float y3 = Visualizer.map((float) Math.sin(Visualizer.PI / 3),0,1,0,size/2);
        float x4 = Visualizer.map((float) Math.cos(5 * Visualizer.PI / 3),0,1,0,size/2);
        float y4 = Visualizer.map((float) Math.sin(5 * Visualizer.PI / 3),0,1,0,size/2);
        float x5 = -size/2 - x1;
        float y5 = 0;
        visualizer.triangle(x3,y3,x4,y4,x5,y5);
        visualizer.strokeWeight(1);
        visualizer.popMatrix();
    }

    public void update()
    {
    }

    public boolean isClicked() 
    {
        clicked = true;
        return clicked;
    }
}