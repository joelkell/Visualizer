package ie.dit;
public class ChooseSongButton extends Button
{
    private float length;
    private float width;
    private float verticalGap;
    private float horizontalGap;
    public ChooseSongButton(Visualizer visualizer, float x, float y, float size)
    {
        super(visualizer, x, y, size);
        length = size;
        width = size / 3;
        verticalGap = 10;
        horizontalGap = 4;
    }

    public void render()
    {
        visualizer.colorMode(Visualizer.RGB);
        visualizer.fill(255,255,255);
        visualizer.stroke(0);
        visualizer.strokeWeight(4);
        visualizer.rect(pos.x - horizontalGap, pos.y - verticalGap,length,width);
        visualizer.textAlign(Visualizer.LEFT, Visualizer.TOP);
        visualizer.fill(0);
        visualizer.noStroke();
        visualizer.textFont(visualizer.ocra);
        visualizer.textSize(20);
        visualizer.text("Choose Song", pos.x, pos.y,length,width);
    }

    public void update()
    {
    }
}