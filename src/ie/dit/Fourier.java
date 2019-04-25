/**
  * Fast Fourier Transform of song which returns current average amplitude of different bands
  */

package ie.dit;

import java.util.ArrayList;

import ddf.minim.*;
import ddf.minim.analysis.FFT;
  
public class Fourier
{
    AudioPlayer song;
    Minim minim;
    Visualizer visualizer;
    FFT fft;
    
    public static final int FRAME_SIZE = 1024;
    public static final int SAMPLE_RATE = 44100;
    public int index = 0;
  
    public Fourier(Visualizer visualizer, Minim minim, AudioPlayer song)//constructor
    {
        this.visualizer = visualizer;
        this.minim = minim;
        this.song = song;
        fft = new FFT(FRAME_SIZE, SAMPLE_RATE);
    }

    public ArrayList<Float> bass = new ArrayList<Float>();//approx 20Hz - 200Hz
    public ArrayList<Float> lowMid = new ArrayList<Float>();//approx 200Hz - 1kHz
    public ArrayList<Float> highMid = new ArrayList<Float>();//approx 1kHz - 5kHz
    public ArrayList<Float> treble = new ArrayList<Float>();//approx 5kHz - 20kHz
    public void fillLists()
    {
        for(int i = 0; i < 4; i++)
        {
            bass.add(fft.indexToFreq(i));
        }
        for(int i = 4; i < 24; i++)
        {
            lowMid.add(fft.indexToFreq(i));
        }
        for(int i = 24; i < 117; i++)
        {
            highMid.add(fft.indexToFreq(i));
        }
        for(int i = 117; i < 512; i++)
        {
            treble.add(fft.indexToFreq(i));
        }
    }

    //calculate the average amplitude for band
    public float getAverage(ArrayList<Float> list)
    {
        fft.forward(song.mix);
        float total = 0;
        float average = 0;
        for(int i = 0; i < list.size(); i++)
        {
            total += fft.getFreq(list.get(i));           
        }
        average = total/list.size();
        return average;
    }
  }