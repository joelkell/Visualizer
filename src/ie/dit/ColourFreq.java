/**
  * Stores rgb colour values and frequency
  */

package ie.dit;

public class ColourFreq
{
    private int freq;
    private int r;
    private int g;
    private int b;

    public ColourFreq()//default constructor
    {
        freq = 0;
    }

    public ColourFreq(int r, int g, int b)//constructor setting rgb values
    {
        this();
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public String toString()
    {
        return "Freq = " + freq + " colour = " + r + "," + g + "," + b + "\n";
    }

    /**
     * @return the freq
     */
    public int getFreq() {
        return freq;
    }

    /**
     * @param freq the freq to set
     */
    public void setFreq(int freq) {
        this.freq = freq;
    }

    /**
     * @return the r
     */
    public int getR() {
        return r;
    }

    /**
     * @param r the r to set
     */
    public void setR(int r) {
        this.r = r;
    }

    /**
     * @return the g
     */
    public int getG() {
        return g;
    }

    /**
     * @param g the g to set
     */
    public void setG(int g) {
        this.g = g;
    }

    /**
     * @return the b
     */
    public int getB() {
        return b;
    }

    /**
     * @param b the b to set
     */
    public void setB(int b) {
        this.b = b;
    }
}