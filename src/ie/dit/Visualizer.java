/**
  * Main PApplet sketch file
  */
package ie.dit;

import java.util.ArrayList;
import ddf.minim.AudioMetaData;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PFont;

public class Visualizer extends PApplet {

    public Minim minim;
    public AudioPlayer song;
    public AudioMetaData meta;
    public String path = null;
    private boolean fileChosen;
    private float volume = 1;
    public PFont arial;
    public PFont tahoma;
    public PFont verdana;
    public PFont ocra;

    public ArrayList<Button> buttons = new ArrayList<Button>(); 

    public void settings() 
    {
        fileChosen = false;//no song chosen at launch
        size(1228, 692);
        smooth(8);
        // fullScreen();
        // size(displayWidth, displayHeight);
        pixelDensity(displayDensity());

        minim = new Minim(this);
        // song = minim.loadFile("D:\\Users\\joelk\\Music\\All Music\\Starship Amazing\\Ruby Dagger\\01 - Funky Boy in Robo World.mp3");
        //song = minim.loadFile("D:\\Users\\joelk\\Music\\All Music\\Matthew Thiessen & The Earthquakes\\Wind Up Bird\\02 - Man of Stone.mp3");
        // song = minim.loadFile("D:\\Users\\joelk\\Music\\All Music\\Switchfoot\\Vice Verses\\06 - Selling The News.mp3");
        // song = minim.loadFile("D:\\Users\\joelk\\Music\\All Music\\Switchfoot\\Vice Verses\\08 - Dark Horses.mp3");
        // song = minim.loadFile("D:\\Users\\joelk\\Music\\All Music\\Switchfoot\\NATIVE TONGUE\\10 - TAKE MY FIRE.mp3");
        //song = minim.loadFile("D:\\Users\\joelk\\Music\\All Music\\John Mayer\\Where The Light Is_ John Mayer Live In Los Angeles\\08 - Who Did You Think I Was (Live at the Nokia Theatre, Los Angeles, CA - December 2007).mp3");
    }

    PlayPause playButton;
    RewindButton rewind;
    FastForward forward;
    ChooseSongButton chooseSongButton;
    public void setup()//create objects and load fonts
    {
        arial = createFont("arial.ttf",10);
        tahoma = createFont("tahoma.ttf",10);
        verdana = createFont("verdana.ttf",10);
        ocra = createFont("OCRAEXT.TTF",10);
        buttons.add(new PlayPause(this, 300, height-150, 100));
        buttons.add(new RewindButton(this, 150, height-150, 100));
        buttons.add(new FastForward(this, 450, height-150, 100));
        buttons.add(new ChooseSongButton(this, width/2, height/2, 140));
        //playButton = new PlayPause(this, 300, height-150, 100);
        // rewind = new RewindButton(this, 150, height-150, 100);
        // forward = new FastForward(this, 450, height-150, 100);
        // chooseSongButton = new ChooseSongButton(this, width/2, height/2, 140);
    }

    //open JFileChooser and select song
    public void selectSong()
    {
        if(fileChosen == true)//if a song is selected and playing - pause it
        {
            if(song.isPlaying())
            {
                song.pause();
            }
        }
        FileChooser chooseFile = new FileChooser();
        chooseFile.chooseFile();
        path = chooseFile.getPath();//returns path of file selected
        if(path != null)//if file was chosen
        {
            song = minim.loadFile(path);//load song
            meta = song.getMetaData();//get song meta data
            song.rewind();//rewind song to start
            fileChosen = true;
        }
    }

    //switch between playing and paused
    public void togglePlay()
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

    //increase volume of song
    public void increaseVolume()
    {
        volume++;
        if(volume > 4)
        {
            volume = 4;
        }
        song.setGain(volume);
    }

    //decrease volume of song
    public void decreaseVolume()
    {
        volume--;
        if(volume < -80)
        {
            volume = -80;
        }
        song.setGain(volume);
    }

    private String timeRemaining;
    private String timeElapsed;
    private String totalTime;
    public String time(int length)//formats time related functions
    {
        int milliseconds = ( -song.position() + length);
        int minutes = milliseconds / 60000;
        int seconds = (milliseconds / 1000) % 60;
        String padded = String.format("%02d" , seconds);
        String strTime =  minutes + ": " + padded;
        return strTime;
    }

    public void draw()
    {
        background(255);//white background
        //black border
        stroke(0);
        strokeWeight(4);
        noFill();
        rect(1,1,width-4,height-4);
        strokeWeight(1);

        //UI Elements
        // playButton.render();
        // rewind.render();
        // forward.render();
        // chooseSongButton.render();
        for(int i = buttons.size() - 1; i >= 0; i--)
        {
            Button b = buttons.get(i);
            b.render();
            b.update();
        }

        fill(0);
        if(fileChosen == true)
        {
            timeRemaining = time(song.length());//calculate remaining time
            timeElapsed = time(2 * song.position());//calculate time passed
            totalTime = time(song.position() + song.length());//calculate total song length
            text("Time Elapsed: " + timeElapsed, width * 0.6f, height / 4);
            text("Time Remaining: " + timeRemaining, width * 0.6f, height / 5);
            text("Title: " + meta.title(), 20, 40);
            text("Artist: " + meta.author(), 20, 60); 
            text("Album: " + meta.album(), 20, 80);
            text("Length: " + totalTime, 20, 100);
        }
    }

    //perform actions when keys are pressed
    public void keyPressed()
    {
        
        if (key =='p')
        {
            selectSong();
        }
        if(song != null)
        {
            if (key ==' ')
            {
                togglePlay();
            }
            if(key == 'w')
            {
                increaseVolume();
            }

            if(key == 'a')
            {
                decreaseVolume();
            }
            if (key == CODED && keyCode == LEFT) 
            {
                song.rewind();
            }
            if (key == CODED && keyCode == RIGHT) 
            {
                song.skip(1000);//fast forward
            } 
        }

    }

    public void mouseClicked()//Checks if button was clicked
    {
        for(int i = buttons.size() - 1; i >= 0; i--)
        {
            Button b = buttons.get(i);
            if(dist(mouseX, mouseY, b.pos.x, b.pos.y) <= b.size)
            {
                b.isClicked();
            }
        }
    }

    /**
     * @param song the song to set
     */
    public void setSong(AudioPlayer song) {
        this.song = song;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the fileChosen
     */
    public boolean isFileChosen() {
        return fileChosen;
    }

    /**
     * @param fileChosen the fileChosen to set
     */
    public void setFileChosen(boolean fileChosen) {
        this.fileChosen = fileChosen;
    }
}