/**
  * Author: Joel Kell
  * Date: 3-04-2019
  * Last Edit: 3-04-2019
  * Student Number: C17431012
  *
  * Description of Program
  *
  *
  *
  */
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