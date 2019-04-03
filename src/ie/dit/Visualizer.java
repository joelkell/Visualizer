package ie.dit;

import ddf.minim.AudioMetaData;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PFont;

public class Visualizer extends PApplet {

    public Minim minim;
    public AudioPlayer song;

    public void settings() 
    {
        size(1228, 692);
        smooth(8);
        // fullScreen();
        // size(displayWidth, displayHeight);
        pixelDensity(displayDensity());

        minim = new Minim(this);
        song = minim.loadFile("D:\\Users\\joelk\\Music\\All Music\\Starship Amazing\\Ruby Dagger\\01 - Funky Boy in Robo World.mp3");
    }

    public void setup()
    {
    }

    public void draw()
    {
        background(255);//white background
        //black border
        stroke(0);
        strokeWeight(4);
        rect(1,1,width-4,height-4);
        strokeWeight(1);
    }
}