/**
 * 
 */

package ie.dit;

public class QuickSort
{
    public void sort(ColourFreq colours[], int low, int high)
    {
        if(low < high)
        {
            int pivot = partition(colours, low, high);
            sort(colours, low, pivot -1);
            sort(colours, pivot + 1, high);
        }
    }

    public int partition(ColourFreq colours[], int low, int high)
    {
        int pivot = colours[high].getFreq();
        int i = (low - 1);
        for(int j = low; j < high; j++)
        {
            if(colours[j].getFreq() < pivot)
            {
                i++;
                ColourFreq temp = colours[i];
                colours[i] = colours[j];
                colours[j] = temp;
            }
        }
        ColourFreq temp = colours[i+1];
        colours[i+1] = colours[high];
        colours[high] = temp;

        return i + 1;
    }
}