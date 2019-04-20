/**
  * Retrieves Album Artwork from selected song
  * Analyzes artwork for most common colours
  */

package ie.dit;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import processing.core.PConstants;
import processing.core.PImage;

public class AlbumArt
{
    private PImage albumArt;
    private BufferedImage bImage;
    Visualizer v;

    public AlbumArt(Visualizer v)
    {
        this.v = v;
    }

    Mp3File mp3file = null;
    public PImage getAlbumArt(String path)//returns PImage from mp3 File
    {
        try 
        {
            mp3file = new Mp3File(path);//creates mp3File from path
        } 
        catch (UnsupportedTagException | InvalidDataException | IOException e) 
        {
            e.printStackTrace();
        }

        if (mp3file.hasId3v2Tag())//checks if mp3File has ID3V2 tags
        {
            ID3v2 id3v2Tag = mp3file.getId3v2Tag();
            byte[] imageData = id3v2Tag.getAlbumImage();//retrieves album art and places in byte array
            if(imageData == null)//if there is no artwork return default artwork
            {
                return null;
            }

            try 
            {
                bImage = ImageIO.read(new ByteArrayInputStream(imageData));//copies byte array into buffered image
            } catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    
        //creates Pimage from buffered image
        try
        {
            albumArt = new PImage(bImage.getWidth(),bImage.getHeight(),PConstants.ARGB);
            bImage.getRGB(0, 0, albumArt.width, albumArt.height, albumArt.pixels, 0, albumArt.width);
            albumArt.updatePixels();
        }
        catch(Exception e) 
        {
            System.err.println("Can't create image from buffer");
            e.printStackTrace();
        }

        return albumArt;//return PImage
    }
}
