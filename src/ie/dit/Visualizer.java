package ie.dit;

import ddf.minim.AudioMetaData;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PFont;

public class Visualizer extends PApplet {

    public Minim minim;
    public AudioPlayer song;
    public PFont arial;
    public PFont tahoma;
    public PFont verdana;
    public PFont ocra;

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
        arial = createFont("arial.ttf",10);
        tahoma = createFont("tahoma.ttf",10);
        verdana = createFont("verdana.ttf",10);
        ocra = createFont("OCRAEXT.TTF",10);
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