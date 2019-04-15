/**
 * Retrieves Album Artwork from selected song
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
                albumArt = v.loadImage("default-artwork.png");
                return albumArt;
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

    private ColourFreq colours[] = new ColourFreq[512];
    public void fillArray()
    {
        int index = 0;
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                for(int k = 0; k < 8; k++)
                {
                    int r = i * 32;
                    int g = j * 32;
                    int b = k * 32;
                    colours[index] = new ColourFreq(r,g,b);
                    index++;
                }
            }
        }
    }

    public boolean isMatch(int c1, int c2)
    {
        int difference = Math.abs(c1 - c2);
        if(difference <= 32)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public int[] commonColour(PImage image, int numColours)
    {
        fillArray();
        image.resize(80, 0);
        image.loadPixels();
        for(int i = 0; i < image.pixels.length; i++)
        {
            String hexPixel = Long.toHexString(256*256*256 - Math.abs(image.pixels[i]));//get Hex value of each pixel
            while(hexPixel.length() < 6)
            {
                hexPixel = "0" + hexPixel;
            }
            String hexR = hexPixel.substring(0,2);
            String hexG = hexPixel.substring(2,4);
            String hexB = hexPixel.substring(4,6);
            int r = Integer.parseInt(hexR, 16);
            int g = Integer.parseInt(hexG, 16);
            int b = Integer.parseInt(hexB, 16);

            for(int j = 0; j < colours.length; j++)
            {
                if(isMatch(r,colours[j].getR()))
                {
                    if(isMatch(g,colours[j].getG()))
                    {
                        if(isMatch(b,colours[j].getB()))
                        {
                            colours[j].setFreq(colours[j].getFreq() + 1);
                            j = colours.length;
                        }
                    }
                }
            }
        }

        QuickSort sort = new QuickSort();
        sort.sort(colours, 0, colours.length - 1);

        int freqColours[] = new int[numColours * 3];
        int j = 0;
        for(int i = colours.length - 1; i > colours.length - numColours - 1; i--)
        {
            freqColours[j] = (int)colours[i].getR();
            j++;
            freqColours[j] = (int)colours[i].getG();
            j++;
            freqColours[j] = (int)colours[i].getB();
            j++;
        }

        return freqColours;

    }
}
