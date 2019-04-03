/**
  * Main PApplet sketch file
  */
package ie.dit;

import ddf.minim.AudioMetaData;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PFont;

public class Visualizer extends PApplet {

    public Minim minim;
    public AudioPlayer song;
    public String path = null;
    private boolean fileChosen;
    public PFont arial;
    public PFont tahoma;
    public PFont verdana;
    public PFont ocra;

    public void settings() 
    {
        fileChosen = false;
        size(1228, 692);
        smooth(8);
        // fullScreen();
        // size(displayWidth, displayHeight);
        pixelDensity(displayDensity());

        minim = new Minim(this);
        // song = minim.loadFile("D:\\Users\\joelk\\Music\\All Music\\Starship Amazing\\Ruby Dagger\\01 - Funky Boy in Robo World.mp3");
    }

    public void setup()
    {
        arial = createFont("arial.ttf",10);
        tahoma = createFont("tahoma.ttf",10);
        verdana = createFont("verdana.ttf",10);
        ocra = createFont("OCRAEXT.TTF",10);
    }

    public void selectSong()
    {
        if(fileChosen == true)
        {
            if(song.isPlaying())
            {
                song.pause();
            }
        }
        FileChooser j = new FileChooser();
        j.chooseFile();
        path = j.getPath();
        song = minim.loadFile(path);
        song.rewind();
        fileChosen = true;
    }

    public String time(int length)
    {
        int milliseconds = ( -song.position() + length); //Countdown
        int minutes = milliseconds / 60000;
        int seconds = (milliseconds / 1000) % 60;
        String padded = String.format("%02d" , seconds);
        String strTime =  minutes + ": " + padded;
        return strTime;
    }
    private String timeRemaining;
    private String timeElapsed;
    private String totalTime;

    public void draw()
    {
        background(255);//white background
        //black border
        stroke(0);
        strokeWeight(4);
        noFill();
        rect(1,1,width-4,height-4);
        strokeWeight(1);

        fill(0);
        if(fileChosen == true)
        {
            timeRemaining = time(song.length());
            timeElapsed = time(2 * song.position());
            totalTime = time(song.position() + song.length());
            text("Time Elapsed: " + timeElapsed, width * 0.8f, height / 4);
            text("Time Remaining: " + timeRemaining, width * 0.8f, height / 5);
            text("Length: " + totalTime, 20, 100);
        }
    }

    public void keyPressed()
    {
        if (key ==' ')
        {
            if(song != null)
            {
                if(song.isPlaying())
                {
                    song.pause();
                }
                else
                {
                    song.play();
                }
            }
        }
        if (key =='p')
        {
            selectSong();
        }
    }
}