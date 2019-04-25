/**
  * Author: Joel Kell
  * Date: 3-04-2019
  * Last Edit: 25-04-2019
  * Student Number: C17431012
  *
  * Visualizer which allows User to select and play an mp3 file on their Computer
  * User can play/pause/rewind/choose songs/change visualizer types/Change Volume
  * Visualizer will select most common colours from album art to use
  * If no album art is available, default colours will be used
  *
  * Hotkeys are:
  * SPACE: Play/Pause
  * LEFT: Rewind to Beginning of Song
  * RIGHT: Fast Forward
  * UP: Volume Up
  * DOWN: Volume Down
  * P: Select Song
  * F: Fullscreen
  * T: Change Visualizer Type
  * S: Change Visualizer Style
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