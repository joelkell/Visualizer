package ie.dit;

public class Main
{	

	public void visualizer()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Visualizer());	
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.visualizer();			
	}
}