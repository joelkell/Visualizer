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
import processing.core.PImage;

public class Visualizer extends PApplet {

    public Minim minim;
    public AudioPlayer song;
    public AudioMetaData meta;
    public Fourier fourier;
    public String path = null;
    private boolean fileChosen;
    private boolean paused;
    private boolean toggle;
    private float volume = 1;
    public PFont ocra;
    public int numColours = 10;
    public int colours[] = new int[numColours * 3];//array of most common colours
    public int circleSizes[] = new int[4];
    PImage albumArt;
    AlbumArt AA;
    GetColours GC;
    VolumeSlider vs;
    Circle circle;

    public ArrayList<Button> buttons = new ArrayList<Button>();
    public ArrayList<UIElement> uiElements = new ArrayList<UIElement>();

    public void settings() 
    {
        fileChosen = false;// no song chosen at launch
        // size(1228, 692); //Smaller Screen size
        fullScreen();
        size(displayWidth, displayHeight);
        smooth(8);
        pixelDensity(displayDensity());

        minim = new Minim(this);
        volume = 4;// sets volume to 100% initially
    }

    Spiderman sm;
    VBackground background;
    public void setup()//create objects and load fonts
    {
        ocra = createFont("OCRAEXT.TTF",10);

        buttons.add(new PlayPause(this, 100, height-80, 60));// play/pause button
        buttons.add(new RewindButton(this, 20, height-80, 60));// rewind button
        buttons.add(new FastForward(this, 180, height-80, 60));// fast forward button
        buttons.add(new ChooseSongButton(this, width - 160, 20, 140));// choose song button
        vs = new VolumeSlider(this, width - 50, 100, width - 50, 370, 20);// volume slider
        buttons.add(vs);
        buttons.add(new TimeSlider(this, 360, height - 50, width - 100, height - 50, 20));// time slider
        buttons.add(new Fullscreen(this, 24, 120, 60));// Fullscreen Button
        buttons.add(new VisualizerButton(this, 24, 220, 60));// Visualizer type Button
        buttons.add(new VisualizerStyleButton(this, 24, 320, 60));// Visualizer Style Button

        AA = new AlbumArt(this);
        GC = new GetColours();
        sm = new Spiderman(this,width/2,height/2);//spiderman gif

        circleSizes[0] = 8;
        circleSizes[1] = 12;
        circleSizes[2] = 16;
        circleSizes[3] = 20;

        frameRate(60);
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

            uiRemove();
        }

        FileChooser chooseFile = new FileChooser();
        chooseFile.chooseFile();
        path = chooseFile.getPath();//returns path of file selected

        if(path != null)//if file was  chosen
        {
            song = minim.loadFile(path);//load song
            toggle = true;
            meta = song.getMetaData();//get song meta data
            fourier = new Fourier(this, minim, song);
            fourier.fillLists();
            albumArt = AA.getAlbumArt(path);//get album art

            //Load default colours and album art if no album art
            if(albumArt == null)
            {
                albumArt = loadImage("default-artwork.png");
                colours = GC.rainbowColour();
            }
            else
            {
                colours = GC.commonColour(albumArt, numColours);//load most common colours into array
            }

            albumArt.resize(80, 0);//resize image to 80 * 80
            albumArt.loadPixels();
            background = new VBackground(this, 0, 0, colours);

            //Add circles to list
            for(int i = 0; i < numColours; i++)
            {
                float radius = circleSizes[i%4];
                int type = i%4;
                float x = random(background.getGap(), width - background.getGap() - (2 * radius));//random x value
                float y = random(background.getGap(), height - background.getGap() - (2 * radius));//random y value

                //Make sure circles don't overlap
                if(uiElements.size() > 0)
                {
                    for(int j = 0; j < uiElements.size(); j++)
                    {
                        Circle ui = (Circle) uiElements.get(j);
                        if(x < ui.pos.x + (2 * radius) && x > ui.pos.x - (2 * radius))
                        {
                            x = random(background.getGap(), width - background.getGap() - (2 * radius));
                            j = 0;
                        }
                        if(y < ui.pos.y + (2 * radius) && y > ui.pos.y - (2 * radius))
                        {
                            y = random(background.getGap(), height - background.getGap() - (2 * radius));
                            j = 0;
                        }
                    }
                }
                circle = new Circle(this, background, fourier, x, y, colours[(3 * i)], colours[(3 * i) + 1], colours[(3 * i) + 2], radius, type);
                uiElements.add(circle);
            }

            song.rewind();//rewind song to start
            song.setGain(volume);//set volume
            fileChosen = true;
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

    public float timeDelta;//time passed on each frame
    private float last;
    int i = 0;
    public void draw()
    {
        float now = millis();
        timeDelta = (now - last) / 1000.0f;
        last = now;

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

        //check if slider is being moved
        volumePress();

        fill(0);
        if(fileChosen == true)//If a song has been selected
        {
            timeRemaining = time(song.length());//calculate remaining time
            timeElapsed = time(2 * song.position());//calculate time passed
            totalTime = time(song.position() + song.length());//calculate total song length

            if(!song.isPlaying() && !paused)//Sets time remaining to zero is song has finished playing
            {
                timeRemaining = time(song.position());
            }

            textAlign(LEFT,CENTER);
            text(timeElapsed, 260, height - 50);
            text(timeRemaining, width-90, height - 50);

            String title = meta.title();
            if(title == "")//sets title to unknown if song title is unknown
            {
                title = "Unknown";
            }
            if(title.length() > 60)//sets title to max 60 characters
            {
                title = title.substring(0,61);
            }
            String author = meta.author();
            if(author == "")//sets author to unknown if song author is unknown
            {
                author = "Unknown";
            }
            if(author.length() > 60)//sets length to max 60 characters
            {
                author = author.substring(0,61);
            }
            String album = meta.album();
            if(album == "")//sets album to unknown if song album is unknown
            {
                album = "Unknown";
            }
            if(album.length() > 60)//sets album to max 60 characters
            {
                album = album.substring(0,61);
            }

            //prints to screen song details
            text("Title: " + title, 110, 20);
            text("Artist: " + author, 110, 40); 
            text("Album: " + album, 110, 60);
            text("Length: " + totalTime, 110, 80);
            imageMode(CORNER);
            image(albumArt, 20, 16, 80,80);//display album art to screen
            
            if(toggle == true)//display visualizer background
            {
                background.render();
                background.update();

                //display UIElements
                for(int i = uiElements.size() - 1; i >= 0; i--)
                {
                    UIElement ui = uiElements.get(i);
                    ui.render();
                    ui.update();

                    if(ui instanceof Circle)
                    {
                        Circle c = (Circle) ui;
                        c.displayLines();
                    }
                }
            }
            else//Display Spiderman Background
            {
                sm.render();
                sm.update();
            }
        }
        else//if song is not chosen
        {
            text("Title: ", 110, 20);
            text("Artist: ", 110, 40); 
            text("Album: ", 110, 60);
            text("Length: ", 110, 80);
            albumArt = loadImage("default-artwork.png");
            imageMode(CORNER);
            image(albumArt, 20, 16, 80,80);
        }
    }

    //perform actions when keys are pressed
    public void keyPressed()
    {
        
        if (key =='p')// P key
        {
            selectSong();// Select Song
        }
        if(key == CODED && keyCode == UP)// up arrow
        {
            increaseVolume();// Increase Volume
        }

        if(key == CODED && keyCode == DOWN)// down arrow
        {
            decreaseVolume();// Decrease Volume
        }

        if(song != null)
        {
            if (key ==' ')// spacebar play/pause
            {
                togglePlay();// Toggle Play or Pause
            }
            if (key == CODED && keyCode == LEFT)// left arrow
            {
                song.rewind();// rewind to start of song
            }
            if (key == CODED && keyCode == RIGHT)// right arrow 
            {
                song.skip(1000);// fast forward
            }
            if(key == 'f')// F Key
            {
                background.toggleFullscreen();// fullscreen
            }
            if(key == 's')// S Key
            {
                background.toggleSolid();// solid/boxes
            }
            if(key == 't')// T Key
            {
                toggleBackground();// spiderman/visualizer
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
                b.isClicked();//Set button to clicked
            }
        }
    }

    public void volumePress()//Checks if Volume slider is being moved
    {
        if((mouseX >= vs.pos.x - vs.size/2 && mouseX <= vs.pos.x + vs.size/2 && mouseY >= vs.pos.y && mouseY <= vs.pos2.y) && mousePressed)
        {
            vs.isClicked();//Sets slider to clicked
        }
    }

    //remove all uiElements from list
    public void uiRemove()
    {
        for(int i = uiElements.size()-1; i >= 0; i--)
        {
            uiElements.remove(i);
        }
    }

    //switch between playing and paused
    public void togglePlay()
    {
        if(song.isPlaying())
        {
            song.pause();
            paused = true;
        }
        else
        {
            song.play();
            paused = false;
        }
    }

    //increase volume of song
    public void increaseVolume()
    {
        volume++;
        if(volume > 4)
        {
            volume = 4;//max volume
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
            volume = -80;//min volume
        }
        if(song != null)
        {
            song.setGain(volume);
        }
    }

    public void toggleBackground()//Toggle Visualizer/Spiderman
    {
        toggle = !toggle;
    }

    public boolean getToggle()
    {
        return toggle;
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