/**
  * Analyzes artwork for most common colours
  */

package ie.dit;

import processing.core.PImage;

public class GetColours
{
    public GetColours()
    {  
    }

    //Array which holds all rgb colours spaced 32 apart each
    private ColourFreq colours[] = new ColourFreq[512];//64 too inacurate & 4096 too accurate. 512 is just right
    public void fillArray()//fill colours array with rgb colours
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

    //checks if an rgb colour is a match
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

    //returns an array of n most common colours found in an image
    public int[] commonColour(PImage image, int numColours)
    {
        fillArray();//fill colour array with colours
        image.resize(80, 0);//resize image to be an 80 * 80 image
        image.loadPixels();//load pixels array of image

        //iterate through every pixel
        for(int i = 0; i < image.pixels.length; i++)
        {
            //converts each pixel to hex value and parse into rgb ints
            String hexPixel = Long.toHexString(256*256*256 - Math.abs(image.pixels[i]));//get Hex value of each pixel
            while(hexPixel.length() < 6)//ensure string length is 6 long
            {
                hexPixel = "0" + hexPixel;
            }
            String hexR = hexPixel.substring(0,2);
            String hexG = hexPixel.substring(2,4);
            String hexB = hexPixel.substring(4,6);
            int r = Integer.parseInt(hexR, 16);
            int g = Integer.parseInt(hexG, 16);
            int b = Integer.parseInt(hexB, 16);

            //iterate through array of colours until match is found and update it's frequency
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

        //quicksort colours array to find most frequent colours
        QuickSort sort = new QuickSort();
        sort.sort(colours, 0, colours.length - 1);

        //create return array
        int freqColours[] = new int[numColours * 3];

        //fill array with most common colours
        int j = 0;
        for(int i = colours.length - 1; i > colours.length - numColours - 1; i--)
        {
            if(colours[i].getFreq() > 0)//ensures only colours who have a frequency get put in array
            {
                freqColours[j] = (int)colours[i].getR();
                j++;
                freqColours[j] = (int)colours[i].getG();
                j++;
                freqColours[j] = (int)colours[i].getB();
                j++;
            }
            else//if not enough colours have a frequency fill remainder of array with most common colour
            {
                freqColours[j] = (int)colours[colours.length - 1].getR();
                j++;
                freqColours[j] = (int)colours[colours.length - 1].getG();
                j++;
                freqColours[j] = (int)colours[colours.length - 1].getB();
                j++;
            }   
        }

        return freqColours;

    }
}
