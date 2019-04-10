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
        // song = minim.loadFile("D:\\Users\\joelk\\Music\\All Music\\Matthew Thiessen & The Earthquakes\\Wind Up Bird\\02 - Man of Stone.mp3");
        // song = minim.loadFile("D:\\Users\\joelk\\Music\\All Music\\Switchfoot\\Vice Verses\\06 - Selling The News.mp3");
        // song = minim.loadFile("D:\\Users\\joelk\\Music\\All Music\\Switchfoot\\Vice Verses\\08 - Dark Horses.mp3");
        // song = minim.loadFile("D:\\Users\\joelk\\Music\\All Music\\Switchfoot\\NATIVE TONGUE\\10 - TAKE MY FIRE.mp3");
        //song = minim.loadFile("D:\\Users\\joelk\\Music\\All Music\\John Mayer\\Where The Light Is_ John Mayer Live In Los Angeles\\08 - Who Did You Think I Was (Live at the Nokia Theatre, Los Angeles, CA - December 2007).mp3");
        song = minim.loadFile("D:\\Users\\joelk\\Music\\All Music\\Kings Kaleidoscope\\Zeal\\07 - Aimless Knight.mp3");
        meta = song.getMetaData();
        fileChosen = true;
    }

    VolumeSlider vs;
    public void setup()//create objects and load fonts
    {
        arial = createFont("arial.ttf",10);
        tahoma = createFont("tahoma.ttf",10);
        verdana = createFont("verdana.ttf",10);
        ocra = createFont("OCRAEXT.TTF",10);

        buttons.add(new PlayPause(this, 100, height-80, 60));// play/pause button
        buttons.add(new RewindButton(this, 20, height-80, 60));// rewind button
        buttons.add(new FastForward(this, 180, height-80, 60));// fast forward button
        buttons.add(new ChooseSongButton(this, width - 160, 20, 140));// choose song button
        vs = new VolumeSlider(this, width - 80, 100, width - 80, 370, 20);// volume slider
        buttons.add(vs);
        buttons.add(new TimeSlider(this, 260, height - 50, width - 100, height - 50, 20));// time slider
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
        if(path != null)//if file was  chosen
        {
            song = minim.loadFile(path);//load song
            meta = song.getMetaData();//get song meta data
            song.rewind();//rewind song to start
            song.setGain(volume);
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
        if(song != null)
        {
            song.setGain(volume);
        }
    }

    //decrease volume of song
    public void decreaseVolume()
    {
        volume--;
        if(volume < -80)
        {
            volume = -80;
        }
        if(song != null)
        {
            song.setGain(volume);
        }
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

        //display buttons
        for(int i = buttons.size() - 1; i >= 0; i--)
        {
            Button b = buttons.get(i);
            b.render();
            b.update();
        }

        //check sliders
        volumePress();

        fill(0);
        if(fileChosen == true)
        {
            timeRemaining = time(song.length());//calculate remaining time
            timeElapsed = time(2 * song.position());//calculate time passed
            totalTime = time(song.position() + song.length());//calculate total song length
            text("Time Elapsed: " + timeElapsed, width/2, height/2);
            textAlign(LEFT,CENTER);
            text(timeRemaining, width-90, height - 50);
            text("Title: " + meta.title(), 20, 20);
            text("Artist: " + meta.author(), 20, 40); 
            text("Album: " + meta.album(), 20, 60);
            text("Length: " + totalTime, 20, 80);
        }
        else
        {
            text("Title: ", 20, 20);
            text("Artist: ", 20, 40); 
            text("Album: ", 20, 60);
            text("Length: ", 20, 80); 
        }
    }

    //perform actions when keys are pressed
    public void keyPressed()
    {
        
        if (key =='p')
        {
            selectSong();
        }
        if(key == CODED && keyCode == UP)// up arrow
        {
            increaseVolume();
        }

        if(key == CODED && keyCode == DOWN)// down arrow
        {
            decreaseVolume();
        }

        if(song != null)
        {
            if (key ==' ')//space
            {
                togglePlay();
            }
            if (key == CODED && keyCode == LEFT)//left arrow
            {
                song.rewind();// rewind to start of song
            }
            if (key == CODED && keyCode == RIGHT)//right arrow 
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
            if(mouseX >= b.pos.x && mouseX <= b.pos.x + b.length && mouseY >= b.pos.y && mouseY <= b.pos.y + b.height )
            {
                b.isClicked();
            }
        }
    }

    public void volumePress()//Checks if Volume slider is being moved
    {
        if((mouseX >= vs.pos.x - vs.size/2 && mouseX <= vs.pos.x + vs.size/2 && mouseY >= vs.pos.y && mouseY <= vs.pos2.y) && mousePressed)
        {
            vs.isClicked();
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
     * @return the volume
     */
    public float getVolume() {
        return volume;
    }

    /**
     * @param volume the volume to set
     */
    public void setVolume(float volume) {
        this.volume = volume;
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